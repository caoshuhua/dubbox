package com.mbv.open.api.tool.code;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.mbv.open.api.common.util.FileUtil;
import com.mbv.open.api.common.util.NameUtil;
import com.mbv.open.api.tool.bean.Api;
import com.mbv.open.api.tool.bean.Provider;
import com.mbv.open.api.tool.bean.Request;


public abstract class BaseCode {
	{
		initVelocity("classpath");
	}
	
	private void initVelocity(String loader){
		engine=new VelocityEngine();
		props = new Properties(); 
		props.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
		props.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
		props.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
		props.setProperty(VelocityEngine.RESOURCE_LOADER,loader);
		if(loader.equals("classpath")){
			props.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		}else{
			props.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "/");
		}
		engine = new VelocityEngine(props);
	}
	
	private VelocityEngine engine;

	private Template template;
	
	private VelocityContext context;
	
	protected static Properties props ;
	
	protected  Template getTemplate(String templateName) throws Exception{
		try
		{ 
		   template = engine.getTemplate("template/"+templateName,"UTF-8");
		}
		catch(ResourceNotFoundException e ){
			initVelocity("file");
			getTemplate(templateName);
		}
		return template;
			
	}
	
	protected String buildContent(String template,Api api){
		StringWriter sw = new StringWriter();
		try {
			this.getTemplate(template).merge(buildVelocityContext(api), sw );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	protected String buildContent(String template,Provider provider,Api api){
		StringWriter sw = new StringWriter();
		try {
			this.getTemplate(template).merge(buildVelocityContext(provider,api), sw );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	protected String buildContent(String template,Map<String,String> sandboxResponses){
		StringWriter sw = new StringWriter();
		try {
			this.getTemplate(template).merge(buildVelocityContext(sandboxResponses), sw );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	protected String buildContent(String template,Api api,Request request){
		StringWriter sw = new StringWriter();
		try {
			this.getTemplate(template).merge(buildVelocityContext(api,request), sw );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	protected String buildContent(String template,List<Api> apis,Provider provider,Api api){
		StringWriter sw = new StringWriter();
		try {
			this.getTemplate(template).merge(buildVelocityContext(apis,provider,api), sw );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	protected String buildContent(String template,List<Provider>  providers){
		StringWriter sw = new StringWriter();
		try {
			this.getTemplate(template).merge(buildVelocityContext(providers), sw );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}	

	protected String buildContent(String template,List<Provider> providers,Provider provider,List<Api> apis){
		StringWriter sw = new StringWriter();
		try {
			this.getTemplate(template).merge(buildVelocityContext(providers,provider,apis), sw );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	protected Context buildVelocityContext(Api api){
		context = new VelocityContext();
		context.put("nameUtil", NameUtil.get() );
		context.put("request",api.getRequest());
		context.put("response",api.getResponse());
		context.put("api",api);
		context.put("bean",api.getBean());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=sdf.format(new Date());
		context.put("date",date);
		return context;
	}
	
	protected Context buildVelocityContext(Provider provider,Api api){
		context = new VelocityContext();
		context.put("api",api);
		context.put("bean",api.getBean());
		context.put("provider",provider);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=sdf.format(new Date());
		context.put("date",date);
		return context;
	}
	
	protected Context buildVelocityContext(Map<String,String> sandboxResponses){
		context = new VelocityContext();
		context.put("sandboxResponses",sandboxResponses);
		return context;
	}
	
	protected Context buildVelocityContext(Api api,Request request){
		context = new VelocityContext();
		context.put("nameUtil", NameUtil.get() );
		context.put("api",api);
		context.put("request",request);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=sdf.format(new Date());
		context.put("date",date);
		return context;
	}
	
	protected Context buildVelocityContext(List<Provider> providers){
		context = new VelocityContext();
		context.put("providers",providers);
		return context;
	}
	
	protected Context buildVelocityContext(List<Provider> providers,Provider provider,List<Api> apis){
		context = new VelocityContext();
		context.put("providers",providers);
		context.put("provider",provider);
		context.put("apis",apis);
		return context;
	}
	
	protected Context buildVelocityContext(List<Api> apis,Provider provider,Api api){
		context = new VelocityContext();
		context.put("apis",apis);
		context.put("provider",provider);
		context.put("api",api);
		context.put("response",api.getResponse());
		context.put("request",api.getRequest());
		return context;
	}
	
	protected void gen(String templete,String path,String name,Api api){
		FileUtil.write("../mbv_open_api_sdk/src/main/java/"+path.replace(".","/")+"/",name+".java", buildContent(templete,api));
	}
	
	protected void genCSharp(String templete,String path,String name,Api api){
		FileUtil.write("../mbv_open_api_sdk_csharp/mbv_open_api_sdk_csharp/"+path.replace(".","/").replace("bean", "model")+"/",name+".cs", buildContent(templete,api));
	}
	
	protected void genPage(String templete,String path,String name,Api api,Request request){
		FileUtil.write("../mbv_open_api_web/src/main/webapp/api/"+path+"/",name+".html", buildContent(templete,api,request));
	}
	
	protected void genSandBoxUtil(Map<String,String> sandboxResponses){
		FileUtil.write("../mbv_open_api_common/src/main/java/com/mbv/open/api/common/util/","SandBoxUtil.java", buildContent("sandboxutil.vm",sandboxResponses));
	}

	protected void genBeanPage(String templete,String path,String name,Provider provider,Api api){
		FileUtil.write("../mbv_open_api_web/src/main/webapp/api/bean/"+path+"/",name+".html", buildContent(templete,provider,api));
	}
	
	protected void genDetailPage(String templete,String path,String name,List<Api> apis,Provider provider,Api api){
		FileUtil.write("../mbv_open_api_web/src/main/webapp/api/detail/"+path+"/",name+".html", buildContent(templete,apis,provider,api));
	}
	
	protected void genListPage(String templete,String name,List<Provider> providers,Provider provider,List<Api> apis){
		FileUtil.write("../mbv_open_api_web/src/main/webapp/api/list/",name+".html", buildContent(templete,providers,provider,apis));
	}
	
	protected void genIndexPage(String templete,String path,String name,List<Provider> providers){
		FileUtil.write("../mbv_open_api_web/src/main/webapp/api/"+path+"/",name+".html", buildContent(templete,providers));
	}

}
