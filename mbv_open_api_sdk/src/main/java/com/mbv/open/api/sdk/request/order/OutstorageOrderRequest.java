package com.mbv.open.api.sdk.request.order;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import com.mbv.open.api.sdk.bean.order.OrderRequest;
import com.mbv.open.api.sdk.response.order.OutstorageOrderResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class OutstorageOrderRequest extends BaseRequest<OutstorageOrderResponse> {

 
	private OrderRequest orderRequest;                 
    

 

  	
	public OrderRequest getOrderRequest() {
		return orderRequest;
	}

	public void setOrderRequest(OrderRequest orderRequest) {
		this.orderRequest = orderRequest;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "order/mb.order.outstorage";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("order_request", this.getOrderRequest());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<OutstorageOrderResponse> getResponseClass() {
		return OutstorageOrderResponse.class;
	}

}	