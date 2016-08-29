package com.mbv.open.api.service;

import com.mbv.open.api.entity.SellerSecurityEntity;

public interface SellerSecurityService {

	public abstract SellerSecurityEntity queryByAppKey(String key);
	
	public void updateIsLimit(SellerSecurityEntity securityEntity,Integer isLimit);
	
	public void updateIsLimit(Integer isLimit);

}