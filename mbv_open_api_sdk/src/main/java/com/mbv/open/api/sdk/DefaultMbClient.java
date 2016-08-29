package com.mbv.open.api.sdk;

import java.util.Map;

import com.mbv.open.api.sdk.exception.SdkException;
import com.mbv.open.api.sdk.parser.ParserFactory;
import com.mbv.open.api.sdk.request.BaseRequest;
import com.mbv.open.api.sdk.response.BaseResponse;
import com.mbv.open.api.sdk.util.HttpUtil;

public class DefaultMbClient implements MbClient {
	public static final String CHARSET_UTF8 = "UTF-8";

	private String serverUrl;
	private String token;
	private int connectTimeout = 0;
	private int readTimeout = 0;
	private String appKey;

	public DefaultMbClient(String serverUrl, String token, String appKey) {
		this.serverUrl = serverUrl;
		this.token = token;
		this.appKey = appKey;
	}

	public DefaultMbClient(String serverUrl, String token, String appKey,
			int connectTimeout, int readTimeout) {
		this(serverUrl, token, appKey);
	}

	public <T extends BaseResponse> T execute(BaseRequest<T> request)
			throws SdkException {
		try {
			String url = buildUrl(request);
			
			String rsp = HttpUtil.doPost(url, null, this.connectTimeout,
					this.readTimeout);
			T resp = parse(rsp, request.getResponseClass());
			StringBuffer sb = new StringBuffer();
			sb.append(url);
			resp.setUrl(sb.toString());

			return resp;
		} catch (Exception e) {
			throw new SdkException("server error!");
		}
	}

	private <T extends BaseResponse> String buildUrl(BaseRequest<T> request)
			throws Exception {
		Map<String, String> params = request.getSysParams();
		String json=request.getAppParams();
		StringBuilder sb = new StringBuilder(this.serverUrl);
		sb.append("/rest/").append(request.getApiMethod());
		params.put("token", this.token);
		params.put("appkey", this.appKey);		
		params.put("params", json);		
		sb.append("?");
		sb.append(HttpUtil.buildQuery(params, "UTF-8"));
		return sb.toString();
	}

	private <T extends BaseResponse> T parse(String rsp, Class<T> responseClass)
			throws SdkException {
		return ParserFactory.getJsonParser().parse(rsp, responseClass);
	}

}
