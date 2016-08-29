package com.mbv.open.api.common.util;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
	
	static public void write(String path,String filename,String content){
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		try{
			FileOutputStream out = new FileOutputStream(path+filename);
			out.write(content.getBytes());
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
