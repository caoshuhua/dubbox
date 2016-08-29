package com.mbv.open.api.cache;

import org.springframework.stereotype.Service;

import com.mbv.open.api.entity.SellerSecurityEntity;

@Service
public class SellerSecurityCache extends BaseCache<SellerSecurityEntity> {

	@Override
	protected Class<SellerSecurityEntity> getBeanClass() {
		return SellerSecurityEntity.class;
	}


}
