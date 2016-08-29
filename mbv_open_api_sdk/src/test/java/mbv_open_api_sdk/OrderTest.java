package mbv_open_api_sdk;

import com.mbv.open.api.sdk.DefaultMbClient;
import com.mbv.open.api.sdk.exception.SdkException;
import com.mbv.open.api.sdk.request.order.GetOrderRemarkRequest;
import com.mbv.open.api.sdk.response.order.GetOrderRemarkResponse;

public class OrderTest {
	
	public static void main(String[] args) throws SdkException{
		String appKey="12345";
		  String token="abcde";
		//DefaultMbClient defaultMbClient=new DefaultMbClient("http://localhost:8080",token, appKey);
		DefaultMbClient defaultMbClient=new DefaultMbClient("https://open.metersbonwe.com/sandbox",token, appKey);
		GetOrderRemarkRequest getOrderRemarkRequest=new GetOrderRemarkRequest();
		getOrderRemarkRequest.setOrderId("000032266");
		GetOrderRemarkResponse getOrderRemarkResponse=defaultMbClient.execute(getOrderRemarkRequest);
		System.out.println(getOrderRemarkResponse.getOrderRemark());
		
	}

}
