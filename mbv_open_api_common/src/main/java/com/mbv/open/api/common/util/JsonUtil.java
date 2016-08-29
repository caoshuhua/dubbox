package com.mbv.open.api.common.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.alibaba.fastjson.JSON;


public class JsonUtil {
	
    private static ObjectMapper objectMapper=new ObjectMapper();
	
	 public static <T> T toObject(String json, Class<T> valueType){
	        T result=null;;
	        try {
				result=objectMapper.readValue(json, valueType);
			} catch (Exception e) {
			} 
	        return result;
	 }
	 
	public static String toJson(Object obj){
		String result = "";
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	@SuppressWarnings("unchecked")
	public static <T>  List<T> toList(String json,Class<T> valueType){
		T result=null;;
        try {
        	CollectionType type=objectMapper.getTypeFactory().constructCollectionType(List.class, valueType);
			result=objectMapper.readValue(json, type);
		} catch (Exception e) {
		} 
        return (List<T>)result;
	}
	
	 public static <T> String serialize(T object) {
         return JSON.toJSONString(object);
     }

}
