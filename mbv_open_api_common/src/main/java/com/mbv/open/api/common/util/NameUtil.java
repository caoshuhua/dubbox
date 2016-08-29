package com.mbv.open.api.common.util;

public class NameUtil {
	private static NameUtil instance = new NameUtil();
	public static NameUtil get(){
		return instance;
	}
	private NameUtil(){
		
	}
	
	public String pasical(String name){
		return firstLower(firstUpperPasical(name));
	}
	public String firstUpperPasical(String name){
		StringBuffer sb = new StringBuffer();
		String[] names = name.split("_");
		for(String n:names){
			sb.append(firstUpper(n));
		}
		return sb.toString();
	}
	
	public String firstUpper(String name){
		StringBuffer sb = new StringBuffer();
		sb.append(name.substring(0,1).toUpperCase())
			.append(name.substring(1));
		return sb.toString();
	}
	
	public String firstLower(String name){
		StringBuffer sb = new StringBuffer();
		sb.append(name.substring(0,1).toLowerCase())
			.append(name.substring(1));
		return sb.toString();
	}
	
	public String toUnderline(String name){
		StringBuffer sb = new StringBuffer();
		for(char n:name.toCharArray()){
			if(Character.isUpperCase(n)){
				sb.append("_");
				n=Character.toLowerCase(n);
			}
			sb.append(n);
		}
		return sb.toString();
	}
	
}
