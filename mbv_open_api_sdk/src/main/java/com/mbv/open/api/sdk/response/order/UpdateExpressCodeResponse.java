package com.mbv.open.api.sdk.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;
import com.mbv.open.api.sdk.response.BaseResponse;

public class UpdateExpressCodeResponse extends BaseResponse {

    @JsonProperty("order_id")
	private String orderId;                 
    

 

  	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}                
    

}