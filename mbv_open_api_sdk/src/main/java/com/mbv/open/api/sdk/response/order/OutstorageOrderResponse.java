package com.mbv.open.api.sdk.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;
import com.mbv.open.api.sdk.response.BaseResponse;

public class OutstorageOrderResponse extends BaseResponse {

    @JsonProperty("order_id")
	private String orderId;                 
    
    @JsonProperty("order_state")
	private String orderState;                 
    
    @JsonProperty("outstorage_date")
	private String outstorageDate;                 
    

 

  	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}                
    
 

  	
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}                
    
 

  	
	public String getOutstorageDate() {
		return outstorageDate;
	}

	public void setOutstorageDate(String outstorageDate) {
		this.outstorageDate = outstorageDate;
	}                
    

}