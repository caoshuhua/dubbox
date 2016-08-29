package com.mbv.open.api.sdk.request.order;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.order.GetSingleOrderResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class GetSingleOrderRequest extends BaseRequest<GetSingleOrderResponse> {

 
	private String orderId;                 
    

 

  	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "order/mb.order.single.get";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("order_id", this.getOrderId());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<GetSingleOrderResponse> getResponseClass() {
		return GetSingleOrderResponse.class;
	}

}	