package com.mbv.open.api.client.workorder;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.mbv.open.api.internal.util.MbvApiException;
import com.mbv.open.api.response.wn.WorkOrderAddResponse;
import com.mbv.open.api.response.wn.WorkOrderBatchQueryResponse;
import com.mbv.open.api.response.wn.WorkOrderCancelResponse;
import com.mbv.open.api.response.wn.WorkOrderConfirmResponse;
import com.mbv.open.api.response.wn.WorkOrderSingleQueryResponse;

public interface MbWorkOrderService {

	public WorkOrderBatchQueryResponse batchQueryWorkOrder(@Context SecurityContext  securityContext,String workOrderBatchQueryRequest) throws MbvApiException;

	public WorkOrderSingleQueryResponse singleQueryWorkOrder(@Context SecurityContext  securityContext,String workOrderSingleQueryRequest) throws MbvApiException;
	
	public WorkOrderAddResponse addWorkOrder(@Context SecurityContext  securityContext,String workOrderAddRequest) throws MbvApiException;
	
	public WorkOrderConfirmResponse confirmWorkOrder(@Context SecurityContext  securityContext,String workOrderConfirmRequest) throws MbvApiException;

	public WorkOrderCancelResponse cancelWorkOrder(@Context SecurityContext  securityContext,String workOrderCancelRequest) throws MbvApiException;
}
