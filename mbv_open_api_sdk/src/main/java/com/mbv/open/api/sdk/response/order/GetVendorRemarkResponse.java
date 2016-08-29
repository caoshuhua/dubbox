package com.mbv.open.api.sdk.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;
import com.mbv.open.api.sdk.response.BaseResponse;

public class GetVendorRemarkResponse extends BaseResponse {

    @JsonProperty("vendor_remark")
	private String vendorRemark;                 
    

 

  	
	public String getVendorRemark() {
		return vendorRemark;
	}

	public void setVendorRemark(String vendorRemark) {
		this.vendorRemark = vendorRemark;
	}                
    

}