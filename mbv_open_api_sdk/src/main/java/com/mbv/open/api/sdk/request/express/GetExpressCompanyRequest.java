package com.mbv.open.api.sdk.request.express;
import java.io.IOException;
import com.mbv.open.api.sdk.request.BaseRequest;
import com.mbv.open.api.sdk.response.express.GetExpressCompanyResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class GetExpressCompanyRequest extends BaseRequest<GetExpressCompanyResponse> {


	
	@Override
	public String getApiMethod() {
		return "express/mb.logistics.expresscompany.get";
	}


	@Override
	public String getAppParams() throws IOException {
	    return "";
	}

	@Override
	public Class<GetExpressCompanyResponse> getResponseClass() {
		return GetExpressCompanyResponse.class;
	}

}	