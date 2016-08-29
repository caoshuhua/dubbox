package com.mbv.open.api.sdk.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


public final class JsonUtil
{
  private static final ObjectMapper mapper = new ObjectMapper();
  
   public JsonUtil(){
	    mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
	    
	    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	    
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
   }
 
  
	public static String toJson(Object obj) throws IOException {
		return mapper.writeValueAsString(obj);
	}
	
	public static <T>T toObject(String value,Class<T> valueType) throws IOException {
		return mapper.readValue(value, valueType);
	}
}
