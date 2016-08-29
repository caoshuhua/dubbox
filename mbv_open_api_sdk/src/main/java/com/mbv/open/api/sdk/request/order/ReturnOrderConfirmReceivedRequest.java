package com.mbv.open.api.sdk.request.order;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.order.ReturnOrderConfirmReceivedResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class ReturnOrderConfirmReceivedRequest extends BaseRequest<ReturnOrderConfirmReceivedResponse> {

 
	private String returnorderId;                 
    

 

  	
	public String getReturnorderId() {
		return returnorderId;
	}

	public void setReturnorderId(String returnorderId) {
		this.returnorderId = returnorderId;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "returnorder/mb.returnorder.receive.comfirm";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("returnorder_id", this.getReturnorderId());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<ReturnOrderConfirmReceivedResponse> getResponseClass() {
		return ReturnOrderConfirmReceivedResponse.class;
	}

}	