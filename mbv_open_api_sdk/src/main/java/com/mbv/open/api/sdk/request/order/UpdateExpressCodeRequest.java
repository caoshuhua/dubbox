package com.mbv.open.api.sdk.request.order;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.order.UpdateExpressCodeResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class UpdateExpressCodeRequest extends BaseRequest<UpdateExpressCodeResponse> {

 
	private String orderId;                 
    
 
	private String expressCompanyId;                 
    
 
	private String trackingNumber;                 
    

 

  	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}                
    
 

  	
	public String getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(String expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}                
    
 

  	
	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "order/mb.order.expressdeliveryorder.update";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("order_id", this.getOrderId());
	    map.put("express_company_id", this.getExpressCompanyId());
	    map.put("tracking_number", this.getTrackingNumber());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<UpdateExpressCodeResponse> getResponseClass() {
		return UpdateExpressCodeResponse.class;
	}

}	