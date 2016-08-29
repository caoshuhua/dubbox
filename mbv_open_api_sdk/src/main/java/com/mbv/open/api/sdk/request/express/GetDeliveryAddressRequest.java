package com.mbv.open.api.sdk.request.express;
import java.io.IOException;
import com.mbv.open.api.sdk.request.BaseRequest;
import com.mbv.open.api.sdk.response.express.GetDeliveryAddressResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class GetDeliveryAddressRequest extends BaseRequest<GetDeliveryAddressResponse> {


	
	@Override
	public String getApiMethod() {
		return "express/mb.logistics.deliveryaddress.get";
	}


	@Override
	public String getAppParams() throws IOException {
	    return "";
	}

	@Override
	public Class<GetDeliveryAddressResponse> getResponseClass() {
		return GetDeliveryAddressResponse.class;
	}

}	