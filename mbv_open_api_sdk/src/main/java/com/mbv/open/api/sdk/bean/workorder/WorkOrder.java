package com.mbv.open.api.sdk.bean.workorder;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;
import com.mbv.open.api.sdk.bean.workorder.OosItem;
import java.util.List;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class WorkOrder{

 
	
	@JsonProperty("workorder_id")
	private String workorderId;                 
    
 
	
	@JsonProperty("workorder_create_date")
	private String workorderCreateDate;                 
    
 
	
	@JsonProperty("workorder_close_date")
	private String workorderCloseDate;                 
    
 
	
	@JsonProperty("workorder_state")
	private String workorderState;                 
    
 
	
	@JsonProperty("workorder_type")
	private String workorderType;                 
    
 
	
	@JsonProperty("content")
	private String content;                 
    
 
	
	@JsonProperty("reply_content")
	private String replyContent;                 
    
 
	
	@JsonProperty("exception_reason")
	private String exceptionReason;                 
    
 
	
	@JsonProperty("remark")
	private String remark;                 
    
 
	
	@JsonProperty("order_id")
	private String orderId;                 
    
 
	
	@JsonProperty("mb_order_id")
	private String mbOrderId;                 
    
 
	
	@JsonProperty("vendor_id")
	private String vendorId;                 
    
 
	
	@JsonProperty("oos_items")
	private List<OosItem> oosItems;                 
    
 
	
	@JsonProperty("last_modified_user")
	private String lastModifiedUser;                 
    
 
	
	@JsonProperty("last_modified_date")
	private String lastModifiedDate;                 
    

 
	
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
    
 
	
	public String getWorkorderCloseDate() {
		return workorderCloseDate;
	}

	public void setWorkorderCloseDate(String workorderCloseDate) {
		this.workorderCloseDate = workorderCloseDate;
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
    
 
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}                
    
 
	
	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}                
    
 
	
	public String getExceptionReason() {
		return exceptionReason;
	}

	public void setExceptionReason(String exceptionReason) {
		this.exceptionReason = exceptionReason;
	}                
    
 
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
    
 
	
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}                
    
 
	
	public List<OosItem> getOosItems() {
		return oosItems;
	}

	public void setOosItems(List<OosItem> oosItems) {
		this.oosItems = oosItems;
	}                
    
 
	
	public String getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}                
    
 
	
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}                
    

}