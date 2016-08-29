package com.mbv.open.api.sdk.request.order;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.order.UpdateVendorRemarkResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class UpdateVendorRemarkRequest extends BaseRequest<UpdateVendorRemarkResponse> {

 
	private String orderId;                 
    
 
	private String vendorRemark;                 
    

 

  	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}                
    
 

  	
	public String getVendorRemark() {
		return vendorRemark;
	}

	public void setVendorRemark(String vendorRemark) {
		this.vendorRemark = vendorRemark;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "order/mb.order.vendorremark.update";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("order_id", this.getOrderId());
	    map.put("vendor_remark", this.getVendorRemark());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<UpdateVendorRemarkResponse> getResponseClass() {
		return UpdateVendorRemarkResponse.class;
	}

}	