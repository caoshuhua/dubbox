package com.mbv.open.api.sdk.response.workorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;
import com.mbv.open.api.sdk.response.BaseResponse;

public class WorkOrderConfirmResponse extends BaseResponse {

    @JsonProperty("workorder_id")
	private String workorderId;                 
    
    @JsonProperty("workorder_create_date")
	private String workorderCreateDate;                 
    
    @JsonProperty("workorder_state")
	private String workorderState;                 
    
    @JsonProperty("workorder_type")
	private String workorderType;                 
    
    @JsonProperty("workorder_confirm_date")
	private String workorderConfirmDate;                 
    

 

  	
	public String getWorkorderId() {
		return workorderId;
	}

	public void setWorkorderId(String workorderId) {
		this.workorderId = workorderId;
	}                
    
 

  	
	public String getWorkorderCreateDate() {
		return workorderCreateDate;
	}

	public void setWorkorderCreateDate(String workorderCreateDate) {
		this.workorderCreateDate = workorderCreateDate;
	}                
    
 

  	
	public String getWorkorderState() {
		return workorderState;
	}

	public void setWorkorderState(String workorderState) {
		this.workorderState = workorderState;
	}                
    
 

  	
	public String getWorkorderType() {
		return workorderType;
	}

	public void setWorkorderType(String workorderType) {
		this.workorderType = workorderType;
	}                
    
 

  	
	public String getWorkorderConfirmDate() {
		return workorderConfirmDate;
	}

	public void setWorkorderConfirmDate(String workorderConfirmDate) {
		this.workorderConfirmDate = workorderConfirmDate;
	}                
    

}