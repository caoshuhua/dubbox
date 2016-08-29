package com.mbv.open.api.sdk.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.order.OrderInfo;
import com.mbv.open.api.sdk.response.BaseResponse;

public class GetSingleOrderResponse extends BaseResponse {

    @JsonProperty("order")
	private OrderInfo order;                 
    

 

  	
	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}                
    

}