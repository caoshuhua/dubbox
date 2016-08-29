package com.mbv.open.api.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class HttpUtil {
	
	public static String getParameterString(HttpServletRequest request,String name){
		String value=request.getParameter(name);
		if(StringUtils.isEmpty(value)){
			value="";
		}
		return value;
	}
	
	public static String getIpAddress(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    if(ip.equals("0:0:0:0:0:0:0:1")){
	    	ip="127.0.0.1";
	    }
	    return ip; 
	  }


}
