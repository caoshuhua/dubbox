package com.mbv.open.api.tool.context;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mbv.open.api.common.util.ClassUtil;
import com.mbv.open.api.internal.util.Api;
import com.mbv.open.api.tool.bean.Provider;

@Service
public class CodeContext {
	
	private static String outPath; 
	
	private static String pkgName="com.mbv.open.api.rest";
	
	public List<Provider> providers;
	
	@SuppressWarnings("unchecked")
	public static List<Provider> getProvoiders(){
		List<Class<?>>classes= ClassUtil.getClasses(pkgName);				
		int size=0;
    	for(int i=0;i<classes.size();i++){
    		Class<?> classa=classes.get(i);
    		String type=classa.getSimpleName();
    		if(type.equals("BaseServiceImpl")||type.equals("MbServiceImpl")){
    			continue;
    		}
    		size++;
    	}
    	Provider[] providers=new Provider[size];
    	for(int i=0;i<classes.size();i++){
    		Class<?> classa=classes.get(i);
    		String type=classa.getSimpleName();
    		if(type.equals("BaseServiceImpl")||type.equals("MbServiceImpl")){
    			continue;
    		}
        	Provider provider=new Provider();
        	provider.setName(classa.getSimpleName());
        	provider.setType(classa.getName());
        	Path path=classa.getAnnotation(Path.class);
        	String url="";
        	if(path!=null){
        		url=path.value().replace("rest/", "");
        	}
        	Api api=classa.getAnnotation(Api.class);
        	if(api!=null){
                provider.setbName(api.name());
                provider.setDesc(api.desc());
        	}
        	provider.setUrl(url);
        	int index=api.index()-1;
        	providers[index]=provider;
    	}
    	return CollectionUtils.arrayToList(providers);
	}
	
	public static String getPkgName() {
		return pkgName;
	}
	public static void setPkgName(String pkgName) {
		CodeContext.pkgName = pkgName;
	}
	public static String getOutPath() {
		return outPath;
	}
	public static void setOutPath(String outPath) {
		CodeContext.outPath = outPath;
	}
	
	
}
