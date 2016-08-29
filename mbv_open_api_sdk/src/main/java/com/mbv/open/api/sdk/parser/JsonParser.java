package com.mbv.open.api.sdk.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbv.open.api.sdk.exception.SdkException;
import com.mbv.open.api.sdk.response.BaseResponse;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.util.StringUtil;

public class JsonParser implements Parser{

	  private final ObjectMapper mapper = new ObjectMapper();
	  
	  public JsonParser()
	  {
	    this.mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
	    
	    this.mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	    
	    this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	  }
	  
	  @SuppressWarnings("unchecked")
	public <T extends BaseResponse>T parse(String json, Class<T> responseClass)
	    throws SdkException
	  {
	    BaseResponse response = null;
	    try
	    {
	      if (StringUtil.isEmpty(json)) {
	        throw new SdkException("response json is empty!");
	      }
	      response = JsonUtil.toObject(json, responseClass);
	    }
	    catch (Exception e)
	    {
	      throw new SdkException(e);
	    }
	    return (T)response;
	  }

}
