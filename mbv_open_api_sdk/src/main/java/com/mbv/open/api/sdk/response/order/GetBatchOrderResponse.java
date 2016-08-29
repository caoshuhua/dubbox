package com.mbv.open.api.sdk.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.order.OrderInfo;
import java.util.List;
import com.mbv.open.api.sdk.response.BaseResponse;

public class GetBatchOrderResponse extends BaseResponse {

    @JsonProperty("total_results")
	private int totalResults;                 
    
    @JsonProperty("orders")
	private List<OrderInfo> orders;                 
    

 

  	
	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}                
    
 

  	
	public List<OrderInfo> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderInfo> orders) {
		this.orders = orders;
	}                
    

}