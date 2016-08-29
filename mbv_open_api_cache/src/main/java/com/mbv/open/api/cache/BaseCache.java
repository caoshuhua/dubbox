package com.mbv.open.api.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbv.open.api.common.constant.Constant;
import com.mbv.open.api.common.util.JsonUtil;
import com.mtsbw.ump.cache.client.CacheClient;

@Service
public abstract class BaseCache<T> {
	
	@Autowired
	private CacheClient cacheClient;

	public T getObject(String key){
		T value=null;
		String json="";
		try {
			 json=cacheClient.hget(Constant.CACHE_BUSINESS_CODE, Constant.CACHE_TYPE, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		value=JsonUtil.toObject(json, getBeanClass());
		return value;
	}
	
	public void deleteAll(){
		try {
			cacheClient.delHash(Constant.CACHE_BUSINESS_CODE, Constant.CACHE_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void setObject(String key,T value){
		String json=JsonUtil.toJson(value);
		try {
			cacheClient.hsetJsonValue(Constant.CACHE_BUSINESS_CODE, Constant.CACHE_TYPE, key,json , 6000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected abstract Class<T> getBeanClass();
	

}
