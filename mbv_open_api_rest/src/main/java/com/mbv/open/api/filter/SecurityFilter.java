package com.mbv.open.api.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mbv.open.api.bean.UserContext;
import com.mbv.open.api.common.constant.Constant;
import com.mbv.open.api.common.util.JsonUtil;
import com.mbv.open.api.common.util.StringUtil;
import com.mbv.open.api.entity.AccessLog;
import com.mbv.open.api.entity.SellerSecurityEntity;
import com.mbv.open.api.service.AccessLogService;
import com.mbv.open.api.service.SellerPvService;
import com.mbv.open.api.service.SellerSecurityService;
import com.mbv.open.api.util.HttpUtil;
import com.mbv.open.api.util.RestUtil;

@Component
public class SecurityFilter implements ContainerRequestFilter{
	
	private  Logger logger = LoggerFactory.getLogger(SecurityFilter.class);
	
	@Context
	private transient HttpServletRequest servletRequest;
	
	private SellerSecurityService sellerSecurityService;
	
	private AccessLogService accessLogService;
	
	private SellerPvService  sellerPvService;
	
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		
		   Date now=new Date();

		   String appKey=HttpUtil.getParameterString(servletRequest,"appkey");
		   logger.info("appKey:"+appKey);
		   
		   if(StringUtil.isEmpty(appKey)){
			   Response response=bulidUnauthResponse(Constant.missAppKeyResponse);
			   requestContext.abortWith(response);
			   return;
		   }	
		   
		   String token=HttpUtil.getParameterString(servletRequest,"token");
		   logger.info("token:"+token);
		   
		   if(StringUtil.isEmpty(token)){
			   Response response=bulidUnauthResponse(Constant.missTokenResponse);
			   requestContext.abortWith(response);
			   return;
		   }
		   
		   getService();		   
		   
		   String ip=HttpUtil.getIpAddress(servletRequest);
		   logger.info("ip:"+ip);
		   
		   SellerSecurityEntity sellerSecurity= sellerSecurityService.queryByAppKey(appKey);
		   logger.info("sellerSecurity:"+sellerSecurity);
		   
		   String userCode=sellerSecurity==null||sellerSecurity.getUserCode()==null?"":sellerSecurity.getUserCode();

		   AccessLog accessLog=buildAccessLog(appKey, ip, servletRequest, now, userCode);
		   
		   accessLogService.log(accessLog);
		   
		   if(sellerSecurity==null||!sellerSecurity.getToken().equals(token)){
			   Response response=bulidUnauthResponse(Constant.noSellerResponse);
			   requestContext.abortWith(response);
			   return;
	       }
		   
		   if(sellerSecurity.getStatus().intValue()<1){
			   Response response=bulidUnauthResponse(Constant.statusErrorResponse);
			   requestContext.abortWith(response);
		   }else if(!sellerSecurity.getIp().contains(ip)){
			   Response response=bulidUnauthResponse(Constant.ipUnAuthResponse);
			   requestContext.abortWith(response);
		   }else{
			   if(sellerSecurity.getIsLimit().intValue()==0){
				   UserContext userContext=new UserContext();
				   userContext.setSellerCode(sellerSecurity.getSellerCode());
				   userContext.setUserCode(sellerSecurity.getUserCode());
				   String info=JsonUtil.toJson(userContext);
				   SecurityContext securityContext=RestUtil.bulidSecurityContext(info);
				   requestContext.setSecurityContext(securityContext);   
				   
				   Boolean isLimit=sellerPvService.updatePv(appKey, userCode, sellerSecurity.getRequestLimit(), now);
				   
				   if(isLimit){
					   sellerSecurityService.updateIsLimit(sellerSecurity, 1);
				   }
				   
			   }else{
				   Response response=bulidUnauthResponse(Constant.overRequestLimitResponse);
				   requestContext.abortWith(response);
			   }
		   }
	}
	
	private AccessLog buildAccessLog(String appKey,String ip,HttpServletRequest servletRequest,Date date,String userCode){
		String params=HttpUtil.getParameterString(servletRequest,"params");
		
		AccessLog accessLog=new AccessLog();
		accessLog.setAppKey(appKey);
		accessLog.setIp(ip);
		accessLog.setParams(params);		
		accessLog.setMethod(servletRequest.getMethod());
        accessLog.setRequestTime(date);
        accessLog.setUserCode(userCode);
		return accessLog;
	}
	
	private Response  bulidUnauthResponse(String context){
				return Response.ok().status(Constant.unAuthCode).entity(context).build();
	}

	public void getService() {
		if(sellerSecurityService!=null){
			return;
		}
	    WebApplicationContext wac=WebApplicationContextUtils.getWebApplicationContext(servletRequest.getServletContext());
	    sellerSecurityService=wac.getBean(SellerSecurityService.class);
	    accessLogService=wac.getBean(AccessLogService.class);
	    sellerPvService=wac.getBean(SellerPvService.class);
	}

}
