package com.mbv.open.api.sdk.request.express;
import java.io.IOException;
import com.mbv.open.api.sdk.request.BaseRequest;
import com.mbv.open.api.sdk.response.express.DeleteDeliveryAddressResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class DeleteDeliveryAddressRequest extends BaseRequest<DeleteDeliveryAddressResponse> {


	
	@Override
	public String getApiMethod() {
		return "express/mb.logistics.deliveryaddress.delete";
	}


	@Override
	public String getAppParams() throws IOException {
	    return "";
	}

	@Override
	public Class<DeleteDeliveryAddressResponse> getResponseClass() {
		return DeleteDeliveryAddressResponse.class;
	}

}	