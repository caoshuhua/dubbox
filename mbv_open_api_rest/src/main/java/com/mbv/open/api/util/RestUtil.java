package com.mbv.open.api.util;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class RestUtil {
	
	public static SecurityContext bulidSecurityContext(final String value){
		return new SecurityContext() {				
			@Override
			public boolean isUserInRole(String role) {
				return false;
			}
			@Override
			public boolean isSecure() {
				return false;
			}
			@Override
			public Principal getUserPrincipal() {
				return null;
			}
			@Override
			public String getAuthenticationScheme() {
				return value;
			}
		};
	}

}
