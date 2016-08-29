package com.mbv.open.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbv.open.api.cache.SellerSecurityCache;
import com.mbv.open.api.dao.SellerSecurityEntityMapper;
import com.mbv.open.api.entity.SellerSecurityEntity;
import com.mbv.open.api.service.SellerSecurityService;

@Service
public class SellerSecurityServiceImpl implements SellerSecurityService {
	
	@Autowired
	private SellerSecurityCache sellerSecurityCache;
	
	@Autowired
	private SellerSecurityEntityMapper sellerSecurityEntityMapper;
	
	@Override
	public SellerSecurityEntity queryByAppKey(String key){
		SellerSecurityEntity result=sellerSecurityCache.getObject(key);
		if(result==null){
			SellerSecurityEntity queryVo=new SellerSecurityEntity();
			queryVo.setAppKey(key);
			List<SellerSecurityEntity> list=sellerSecurityEntityMapper.selectByAppKey(key);
			if(list!=null&&!list.isEmpty()){
				result=list.get(0);
				sellerSecurityCache.setObject(key,result);
			}
		}
        return result;
	}
	
	public void updateIsLimit(SellerSecurityEntity securityEntity,Integer isLimit){
		sellerSecurityEntityMapper.updateIsLimitById(securityEntity.getId(),isLimit);
		securityEntity.setIsLimit(isLimit);
		sellerSecurityCache.setObject(securityEntity.getAppKey(),securityEntity);
	}

	@Override
	public void updateIsLimit(Integer isLimit) {
		sellerSecurityEntityMapper.updateIsLimit(isLimit);
		sellerSecurityCache.deleteAll();
	}

}
