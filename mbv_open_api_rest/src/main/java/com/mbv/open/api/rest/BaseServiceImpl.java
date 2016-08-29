package com.mbv.open.api.rest;

import javax.ws.rs.core.SecurityContext;

import com.mbv.open.api.bean.UserContext;
import com.mbv.open.api.common.util.JsonUtil;

public class BaseServiceImpl {
	
	@Deprecated
	protected String getSellerCode(SecurityContext securityContext){
		return "";
	}
	
	protected UserContext getUserContext(SecurityContext securityContext){
		String  json=securityContext.getAuthenticationScheme();
		return JsonUtil.toObject(json,UserContext.class);
	}

}
