package com.mbv.open.api.sdk.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.order.ReturnOrder;
import java.util.List;
import com.mbv.open.api.sdk.response.BaseResponse;

public class ReturnOrderBatchQueryResponse extends BaseResponse {

    @JsonProperty("total_results")
	private int totalResults;                 
    
    @JsonProperty("return_orders")
	private List<ReturnOrder> returnOrders;                 
    

 

  	
	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}                
    
 

  	
	public List<ReturnOrder> getReturnOrders() {
		return returnOrders;
	}

	public void setReturnOrders(List<ReturnOrder> returnOrders) {
		this.returnOrders = returnOrders;
	}                
    

}