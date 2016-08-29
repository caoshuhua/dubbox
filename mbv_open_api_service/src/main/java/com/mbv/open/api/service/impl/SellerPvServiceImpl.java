package com.mbv.open.api.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbv.open.api.common.constant.Constant;
import com.mbv.open.api.common.util.DateUtils;
import com.mbv.open.api.dao.SellerPvMapper;
import com.mbv.open.api.entity.SellerPv;
import com.mbv.open.api.service.SellerPvService;

@Service
public class SellerPvServiceImpl implements SellerPvService {
	
	@Autowired
	private SellerPvMapper sellerPvMapper;
	
	public Boolean updatePv(String appKey,String userCode,Integer requestLimit,Date now){
		Boolean isLimit=false;
		Integer date=Integer.parseInt(DateUtils.getDate(now));
		int result=updateRequestNum(appKey, date);
		if(result==0){
			SellerPv sellerPv=sellerPvMapper.selectByAppKeyAndDate(appKey, date);
			if(sellerPv==null){
				insert(appKey, userCode, requestLimit, date ,now);
			}else{
				isLimit=true;
			}
		}
		return isLimit;
	}
	
	public int insert(String appKey,String userCode,Integer requestLimit,Integer date,Date now){
		SellerPv sellerPv=new SellerPv();
		sellerPv.setAppKey(appKey);
		sellerPv.setCreateDate(now);
		sellerPv.setCreateUser(Constant.updateUserName);
		sellerPv.setDate(date);
		sellerPv.setLastModifiedUser(Constant.updateUserName);
		sellerPv.setRequestLimit(requestLimit);
		sellerPv.setRequestNum(1);
		sellerPv.setUserCode(userCode);
		
		return sellerPvMapper.insert(sellerPv);
	}
	 
	private int updateRequestNum(String appKey,Integer date){
		SellerPv sellerPv=new SellerPv();
		sellerPv.setLastModifiedUser(Constant.updateUserName);
		sellerPv.setAppKey(appKey);
		sellerPv.setDate(date);
		return  sellerPvMapper.updateByAppKeyAndDate(sellerPv);
	}

	@Override
	public void initDataByDate(Integer date) {
		SellerPv sellerPv=new SellerPv();
		sellerPv.setDate(date);
		sellerPv.setCreateUser(Constant.updateUserName);
		sellerPv.setLastModifiedUser(Constant.updateUserName);
		sellerPvMapper.insertTodayData(sellerPv);
	}

}
