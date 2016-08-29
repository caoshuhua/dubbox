package com.mbv.open.api.sdk.response.workorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.workorder.WorkOrder;
import java.util.List;
import com.mbv.open.api.sdk.response.BaseResponse;

public class WorkOrderBatchQueryResponse extends BaseResponse {

    @JsonProperty("total_results")
	private int totalResults;                 
    
    @JsonProperty("work_orders")
	private List<WorkOrder> workOrders;                 
    

 

  	
	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}                
    
 

  	
	public List<WorkOrder> getWorkOrders() {
		return workOrders;
	}

	public void setWorkOrders(List<WorkOrder> workOrders) {
		this.workOrders = workOrders;
	}                
    

}