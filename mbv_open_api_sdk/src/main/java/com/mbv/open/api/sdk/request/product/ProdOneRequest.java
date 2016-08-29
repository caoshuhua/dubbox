package com.mbv.open.api.sdk.request.product;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.product.ProdOneResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class ProdOneRequest extends BaseRequest<ProdOneResponse> {

 
	private String productId;                 
    

 

  	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "product/mb.product.single.get";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("product_id", this.getProductId());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<ProdOneResponse> getResponseClass() {
		return ProdOneResponse.class;
	}

}	