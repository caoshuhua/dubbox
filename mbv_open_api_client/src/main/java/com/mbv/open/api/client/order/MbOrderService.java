package com.mbv.open.api.client.order;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.mbv.open.api.internal.util.MbvApiException;
import com.mbv.open.api.response.order.GetBatchOrderResponse;
import com.mbv.open.api.response.order.GetOrderRemarkResponse;
import com.mbv.open.api.response.order.GetSingleOrderResponse;
import com.mbv.open.api.response.order.GetVendorRemarkResponse;
import com.mbv.open.api.response.order.OutstorageOrderResponse;
import com.mbv.open.api.response.order.UpdateExpressCodeResponse;
import com.mbv.open.api.response.order.UpdateOrderRemarkResponse;
import com.mbv.open.api.response.order.UpdateVendorRemarkResponse;

public interface MbOrderService {
	
	/**
	 * 批量订单检索
	 * @param params
	 * @return
	 */
	public GetBatchOrderResponse getBatchOrder(@Context SecurityContext  securityContext,String params) throws MbvApiException;
	
	/**
	 * 单个订单检索
	 * @param params
	 * @return
	 */
	public GetSingleOrderResponse getSingleOrder(@Context SecurityContext  securityContext,String params) throws MbvApiException;

	
	/**
	 * 订单出库
	 * @param params
	 * @return
	 */
	public OutstorageOrderResponse outstorageOrder(@Context SecurityContext  securityContext,String params) throws MbvApiException;
	
	
	/**
	 * 修改运单号
	 * @param params
	 * @return
	 */
	public UpdateExpressCodeResponse updateExpressCode(@Context SecurityContext  securityContext,String params) throws MbvApiException;
	
	
	/**
	 * 查询商家备注
	 * @param params
	 * @return
	 */
	public GetVendorRemarkResponse getVendorRemark(@Context SecurityContext  securityContext,String params) throws MbvApiException;
	
	
	/**
	 * 查询订单备注
	 * @param params
	 * @return
	 */
	public GetOrderRemarkResponse getOrderRemark(@Context SecurityContext  securityContext,String params) throws MbvApiException;
	
	
	/**
	 * 修改商家备注
	 * @param params
	 * @return
	 */
	public UpdateVendorRemarkResponse updateVendorRemark(@Context SecurityContext  securityContext,String params) throws MbvApiException;
	
	
	/**
	 * 追加订单备注
	 * @param params
	 * @return
	 */
	public UpdateOrderRemarkResponse updateOrderRemark(@Context SecurityContext  securityContext,String params) throws MbvApiException;
}
