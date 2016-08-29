package com.mbv.open.api.sdk.request.workorder;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.bean.workorder.OosItem;
import java.util.List;
import com.mbv.open.api.sdk.response.workorder.WorkOrderAddResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class WorkOrderAddRequest extends BaseRequest<WorkOrderAddResponse> {

 
	private String workorderType;                 
    
 
	private String orderId;                 
    
 
	private String remark;                 
    
 
	private String mbOrderId;                 
    
 
	private List<OosItem> oosItems;                 
    

 

  	
	public String getWorkorderType() {
		return workorderType;
	}

	public void setWorkorderType(String workorderType) {
		this.workorderType = workorderType;
	}                
    
 

  	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}                
    
 

  	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}                
    
 

  	
	public String getMbOrderId() {
		return mbOrderId;
	}

	public void setMbOrderId(String mbOrderId) {
		this.mbOrderId = mbOrderId;
	}                
    
 

  	
	public List<OosItem> getOosItems() {
		return oosItems;
	}

	public void setOosItems(List<OosItem> oosItems) {
		this.oosItems = oosItems;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "workorder/mb.workorder.add";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("workorder_type", this.getWorkorderType());
	    map.put("order_id", this.getOrderId());
	    map.put("remark", this.getRemark());
	    map.put("mb_order_id", this.getMbOrderId());
	    map.put("oos_items", this.getOosItems());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<WorkOrderAddResponse> getResponseClass() {
		return WorkOrderAddResponse.class;
	}

}	