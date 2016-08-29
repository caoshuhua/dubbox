package mbv_open_api_sdk;

import java.util.ArrayList;
import java.util.List;

import com.mbv.open.api.sdk.DefaultMbClient;
import com.mbv.open.api.sdk.bean.product.ProductId;
import com.mbv.open.api.sdk.exception.SdkException;
import com.mbv.open.api.sdk.request.product.ProdBatchRequest;
import com.mbv.open.api.sdk.response.product.ProdBatchResponse;

public class ProductTest {
	
	public static void main(String[] args) throws SdkException{
		String appKey="12345";
		  String token="abcde";
		//DefaultMbClient defaultMbClient=new DefaultMbClient("http://localhost:8080",token, appKey);
		  DefaultMbClient defaultMbClient=new DefaultMbClient("https://open.metersbonwe.com/sandbox",token, appKey);
		ProdBatchRequest request=new ProdBatchRequest();
		ProductId productId=new ProductId();
		productId.setProductId("881718");
		ProductId productId1=new ProductId();
		List<ProductId> productIds=new ArrayList<ProductId>();
		productIds.add(productId);
		productIds.add(productId1);
		request.setProductIds(productIds);
		ProdBatchResponse response= defaultMbClient.execute(request);
		System.out.println(response.getProducts().get(0).getSkus().get(0).getBarcodeId());
	}

}
