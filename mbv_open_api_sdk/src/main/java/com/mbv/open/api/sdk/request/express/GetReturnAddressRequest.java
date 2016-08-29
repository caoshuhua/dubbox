package com.mbv.open.api.sdk.request.express;
import java.io.IOException;
import com.mbv.open.api.sdk.request.BaseRequest;
import com.mbv.open.api.sdk.response.express.GetReturnAddressResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class GetReturnAddressRequest extends BaseRequest<GetReturnAddressResponse> {


	
	@Override
	public String getApiMethod() {
		return "express/mb.logistics.returnaddress.get";
	}


	@Override
	public String getAppParams() throws IOException {
	    return "";
	}

	@Override
	public Class<GetReturnAddressResponse> getResponseClass() {
		return GetReturnAddressResponse.class;
	}

}	