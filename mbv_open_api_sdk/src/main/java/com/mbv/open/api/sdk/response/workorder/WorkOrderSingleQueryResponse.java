package com.mbv.open.api.sdk.response.workorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.workorder.WorkOrder;
import com.mbv.open.api.sdk.response.BaseResponse;

public class WorkOrderSingleQueryResponse extends BaseResponse {

    @JsonProperty("work_order")
	private WorkOrder workOrder;                 
    

 

  	
	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}                
    

}