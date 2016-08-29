package com.mbv.open.api.sdk.request.product;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import com.mbv.open.api.sdk.bean.product.ProductId;
import java.util.List;
import com.mbv.open.api.sdk.response.product.ProdBatchResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class ProdBatchRequest extends BaseRequest<ProdBatchResponse> {

 
	private List<ProductId> productIds;                 
    

 

  	
	public List<ProductId> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<ProductId> productIds) {
		this.productIds = productIds;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "product/mb.product.batch.get";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("product_ids", this.getProductIds());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<ProdBatchResponse> getResponseClass() {
		return ProdBatchResponse.class;
	}

}	