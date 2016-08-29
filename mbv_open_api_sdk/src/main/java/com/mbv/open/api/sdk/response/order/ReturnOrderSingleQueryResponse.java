package com.mbv.open.api.sdk.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.order.ReturnOrder;
import com.mbv.open.api.sdk.response.BaseResponse;

public class ReturnOrderSingleQueryResponse extends BaseResponse {

    @JsonProperty("return_order")
	private ReturnOrder returnOrder;                 
    

 

  	
	public ReturnOrder getReturnOrder() {
		return returnOrder;
	}

	public void setReturnOrder(ReturnOrder returnOrder) {
		this.returnOrder = returnOrder;
	}                
    

}