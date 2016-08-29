package com.mbv.open.api.tool.gen;

import java.io.File;
import java.io.FileOutputStream;

import com.mbv.open.api.tool.context.CodeContext;

public class FileGen {
	static public void write(String path,String content){
		File file = new File(buildPath(path));
		if(!file.exists()){
			file.mkdirs();
		}
		try{
			FileOutputStream out = new FileOutputStream(buildFileName(path));
			out.write(content.getBytes());
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	static private String buildPath(String path){
		String ret = buildFileName(path);
		while(ret.indexOf("\\")!=-1)
			ret = ret.replace('\\', '/');
		int index = ret.lastIndexOf("/");
		
		
		return ret.substring(0,index);
	}
	
	static private String buildFileName(String path){
		StringBuffer sb = new StringBuffer();
		sb.append(CodeContext.getOutPath());
		if(!CodeContext.getOutPath().endsWith("/")
				&& !CodeContext.getOutPath().endsWith("\\"))
			sb.append("/");
		sb.append(path);
		return sb.toString();
	}
}
