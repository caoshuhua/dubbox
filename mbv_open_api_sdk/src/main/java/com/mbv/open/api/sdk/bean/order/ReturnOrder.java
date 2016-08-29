package com.mbv.open.api.sdk.bean.order;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;
import com.mbv.open.api.sdk.bean.order.ReturnSku;
import java.util.List;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class ReturnOrder{

 
	
	@JsonProperty("returnorder_id")
	private String returnorderId;                 
    
 
	
	@JsonProperty("returnorder_status")
	private String returnorderStatus;                 
    
 
	
	@JsonProperty("return_person")
	private String returnPerson;                 
    
 
	
	@JsonProperty("return_value")
	private String returnValue;                 
    
 
	
	@JsonProperty("apply_date")
	private String applyDate;                 
    
 
	
	@JsonProperty("close_date")
	private String closeDate;                 
    
 
	
	@JsonProperty("return_reason_id")
	private String returnReasonId;                 
    
 
	
	@JsonProperty("return_reason")
	private String returnReason;                 
    
 
	
	@JsonProperty("tracking_number")
	private String trackingNumber;                 
    
 
	
	@JsonProperty("express_company")
	private String expressCompany;                 
    
 
	
	@JsonProperty("order_id")
	private String orderId;                 
    
 
	
	@JsonProperty("mb_order_id")
	private String mbOrderId;                 
    
 
	
	@JsonProperty("return_skus")
	private List<ReturnSku> returnSkus;                 
    

 
	
	public String getReturnorderId() {
		return returnorderId;
	}

	public void setReturnorderId(String returnorderId) {
		this.returnorderId = returnorderId;
	}                
    
 
	
	public String getReturnorderStatus() {
		return returnorderStatus;
	}

	public void setReturnorderStatus(String returnorderStatus) {
		this.returnorderStatus = returnorderStatus;
	}                
    
 
	
	public String getReturnPerson() {
		return returnPerson;
	}

	public void setReturnPerson(String returnPerson) {
		this.returnPerson = returnPerson;
	}                
    
 
	
	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}                
    
 
	
	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}                
    
 
	
	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}                
    
 
	
	public String getReturnReasonId() {
		return returnReasonId;
	}

	public void setReturnReasonId(String returnReasonId) {
		this.returnReasonId = returnReasonId;
	}                
    
 
	
	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}                
    
 
	
	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}                
    
 
	
	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}                
    
 
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}                
    
 
	
	public String getMbOrderId() {
		return mbOrderId;
	}

	public void setMbOrderId(String mbOrderId) {
		this.mbOrderId = mbOrderId;
	}                
    
 
	
	public List<ReturnSku> getReturnSkus() {
		return returnSkus;
	}

	public void setReturnSkus(List<ReturnSku> returnSkus) {
		this.returnSkus = returnSkus;
	}                
    

}