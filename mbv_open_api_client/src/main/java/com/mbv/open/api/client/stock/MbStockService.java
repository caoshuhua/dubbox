package com.mbv.open.api.client.stock;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.mbv.open.api.internal.util.MbvApiException;
import com.mbv.open.api.response.stock.StockBatchQueryBySkusResponse;
import com.mbv.open.api.response.stock.StockBatchQueryResponse;
import com.mbv.open.api.response.stock.StockSetBySkuResponse;
import com.mbv.open.api.response.stock.StockSingleQueryBySkuResponse;

public interface MbStockService {

	
	/**
	 * 商家库存查询
	 * @param params
	 * @return
	 * @throws ApplicationException
	 */
	public StockBatchQueryResponse queryBatchStockInfo(@Context SecurityContext  securityContext,String stockBatchQueryRequest) throws MbvApiException;
	
	/**
	 * 批量SKU库存查询
	 * @param params
	 * @return
	 * @throws ApplicationException
	 */
	public StockBatchQueryBySkusResponse queryBatchStockInfoBySkus(@Context SecurityContext  securityContext,String stockBatchQueryBySkusRequest) throws MbvApiException;
	
	/**
	 * 单个SKU库存查询
	 * @param params
	 * @return
	 * @throws ApplicationException
	 */
	public StockSingleQueryBySkuResponse querySingleStockBySku(@Context SecurityContext  securityContext,String stockSingleSetBySkuRequest) throws MbvApiException;
	
	/**
	 * 单个SKU库存设置
	 * @param params
	 * @return
	 * @throws ApplicationException
	 */
	public StockSetBySkuResponse updateSingleStockBySku(@Context SecurityContext  securityContext,String stockSingleSetBySkuRequest) throws MbvApiException;
	
	/**
	 * 批量SKU库存设置
	 * @param params
	 * @return
	 * @throws ApplicationException
	 */
	public StockSetBySkuResponse updateBatchStockBySkus(@Context SecurityContext  securityContext,String stockBatchSetBySkuRequest) throws MbvApiException;
	
}
