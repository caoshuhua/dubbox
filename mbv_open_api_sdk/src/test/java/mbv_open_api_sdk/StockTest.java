package mbv_open_api_sdk;

import java.util.ArrayList;
import java.util.List;

import com.mbv.open.api.sdk.DefaultMbClient;
import com.mbv.open.api.sdk.bean.stock.SkuId;
//import com.mbv.open.api.sdk.DefaultMbClient;
//import com.mbv.open.api.sdk.MbParam;
//import com.mbv.open.api.sdk.MbResult;
//import com.mbv.open.api.sdk.SkuId;
import com.mbv.open.api.sdk.exception.SdkException;
import com.mbv.open.api.sdk.request.stock.StockBatchQueryBySkusRequest;
import com.mbv.open.api.sdk.request.stock.StockSingleQueryBySkuRequest;
import com.mbv.open.api.sdk.response.stock.StockBatchQueryBySkusResponse;
import com.mbv.open.api.sdk.response.stock.StockSingleQueryBySkuResponse;

public class StockTest {
	
	  public static void main(String[] args) throws SdkException {
		  String appKey="12345";
		  String token="abcde";
		DefaultMbClient defaultMbClient=new DefaultMbClient("https://open.metersbonwe.com/sandbox",token, appKey);
		StockSingleQueryBySkuRequest request=new StockSingleQueryBySkuRequest();
		request.setSkuId("50967790148");
		StockSingleQueryBySkuResponse response= defaultMbClient.execute(request);
		System.out.println(response.getSkuStock().getActualStock());
		StockBatchQueryBySkusRequest request1=new StockBatchQueryBySkusRequest();
		SkuId skuId=new SkuId();
		skuId.setSkuId("51422890152");
		SkuId skuId1=new SkuId();
		skuId1.setSkuId("50967790148");
		List<SkuId> skuIds=new ArrayList<SkuId>();
		skuIds.add(skuId);
		skuIds.add(skuId1);
		request1.setSkuIds(skuIds);
		StockBatchQueryBySkusResponse response1= defaultMbClient.execute(request1);
		System.out.println(response1.getSkuStocks().get(0).getActualStock());
	  }

}
