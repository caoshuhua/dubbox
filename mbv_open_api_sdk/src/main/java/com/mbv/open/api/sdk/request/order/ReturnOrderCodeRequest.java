package com.mbv.open.api.sdk.request.order;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.order.ReturnOrderSingleQueryResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class ReturnOrderCodeRequest extends BaseRequest<ReturnOrderSingleQueryResponse> {

 
	private String returnorderId;                 
    

 

  	
	public String getReturnorderId() {
		return returnorderId;
	}

	public void setReturnorderId(String returnorderId) {
		this.returnorderId = returnorderId;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "returnorder/mb.returnorder.single.get";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("returnorder_id", this.getReturnorderId());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<ReturnOrderSingleQueryResponse> getResponseClass() {
		return ReturnOrderSingleQueryResponse.class;
	}

}	