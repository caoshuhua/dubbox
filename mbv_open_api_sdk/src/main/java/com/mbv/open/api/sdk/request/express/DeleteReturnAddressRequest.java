package com.mbv.open.api.sdk.request.express;
import java.io.IOException;
import com.mbv.open.api.sdk.request.BaseRequest;
import com.mbv.open.api.sdk.response.express.DeleteReturnAddressResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class DeleteReturnAddressRequest extends BaseRequest<DeleteReturnAddressResponse> {


	
	@Override
	public String getApiMethod() {
		return "express/mb.logistics.returnaddress.delete";
	}


	@Override
	public String getAppParams() throws IOException {
	    return "";
	}

	@Override
	public Class<DeleteReturnAddressResponse> getResponseClass() {
		return DeleteReturnAddressResponse.class;
	}

}	