package com.mbv.open.api.client.order;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.mbv.open.api.internal.util.MbvApiException;
import com.mbv.open.api.response.returnorder.ReturnOrderBatchQueryResponse;
import com.mbv.open.api.response.returnorder.ReturnOrderConfirmReceivedResponse;
import com.mbv.open.api.response.returnorder.ReturnOrderSingleQueryResponse;

public interface MbReturnOrderService {

	public ReturnOrderBatchQueryResponse batchQueryReturnOrders(@Context SecurityContext  securityContext,String returnOrderBatchQueryRequest) throws MbvApiException;
	
	public ReturnOrderSingleQueryResponse singleQueryReturnOrders(@Context SecurityContext  securityContext,String returnOrderCodeRequest) throws MbvApiException;
	
	public ReturnOrderConfirmReceivedResponse confirmReturnOrderReceived(@Context SecurityContext  securityContext,String returnOrderConfirmReceivedRequest) throws MbvApiException;
}
