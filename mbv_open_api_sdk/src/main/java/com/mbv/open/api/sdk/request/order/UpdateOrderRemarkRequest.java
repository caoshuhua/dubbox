package com.mbv.open.api.sdk.request.order;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.order.UpdateOrderRemarkResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class UpdateOrderRemarkRequest extends BaseRequest<UpdateOrderRemarkResponse> {

 
	private String orderId;                 
    
 
	private String orderRemark;                 
    

 

  	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}                
    
 

  	
	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "order/mb.order.remark.update";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("order_id", this.getOrderId());
	    map.put("order_remark", this.getOrderRemark());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<UpdateOrderRemarkResponse> getResponseClass() {
		return UpdateOrderRemarkResponse.class;
	}

}	