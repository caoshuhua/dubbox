package com.mbv.open.api.sdk.response.express;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;
import com.mbv.open.api.sdk.response.BaseResponse;

public class AddReturnAddressResponse extends BaseResponse {

    @JsonProperty("vendor_id")
	private String vendorId;                 
    

 

  	
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}                
    

}