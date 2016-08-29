package com.mbv.open.api.client.product;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.mbv.open.api.internal.util.MbvApiException;
import com.mbv.open.api.response.product.ProdBatchResponse;
import com.mbv.open.api.response.product.ProdOneResponse;
import com.mbv.open.api.response.product.ProdSellerResponse;

public interface MbProductService {

	
	/**
	 * 商家商品信息查询
	 * @param params
	 * @return
	 * @throws ApplicationException
	 */
	public ProdSellerResponse queryProductBySeller(@Context SecurityContext  securityContext,String params) throws MbvApiException;
	
	/**
	 *  批量商品信息查询
	 * @param params
	 * @return
	 * @throws ApplicationException
	 */
	public ProdBatchResponse queryBatchProducts(@Context SecurityContext  securityContext,String params) throws MbvApiException;
	
	/**
	 *  单个商品信息查询
	 * @param params
	 * @return
	 * @throws ApplicationException
	 */
	public ProdOneResponse queryProductInfo(@Context SecurityContext  securityContext,String params) throws MbvApiException;
	
}
