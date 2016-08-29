package com.mbv.open.api.tool.code;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mbv.open.api.annotation.Parameter;
import com.mbv.open.api.common.util.NameUtil;
import com.mbv.open.api.common.util.StringUtil;
import com.mbv.open.api.internal.util.FieldType;
import com.mbv.open.api.tool.bean.Api;
import com.mbv.open.api.tool.bean.Bean;
import com.mbv.open.api.tool.bean.Provider;
import com.mbv.open.api.tool.bean.Request;
import com.mbv.open.api.tool.bean.Response;
import com.mbv.open.api.tool.context.CodeContext;

@Service
public  class ProviderCode extends BaseCode {
	
	
	public static void main(String[] args) {
		ProviderCode providerCode=new ProviderCode();
		providerCode.process();
	}
	
	public void process() {
		List<Provider> providers=CodeContext.getProvoiders();
		genIndexPage("index.vm","","api", providers);
		for(Provider provider:providers){
			if(StringUtil.isEmpty(provider.getType())){
				return;
			}
			List<Api> apis=processProvider(provider,providers);
			genListPage("list.vm",provider.getUrl(), providers, provider, apis);
			for(Api api:apis){
				if(api != null){
					genDetailPage("detail.vm", provider.getUrl(), api.getName(), apis,provider, api);
				}
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	protected List<Api> processProvider(Provider provider,List<Provider> providers){
		Class<?> providerClass=null;
		try {
			providerClass=Class.forName(provider.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		String fullPackage=providerClass.getPackage().getName();
		String childPackage=fullPackage.substring(fullPackage.lastIndexOf(".")+1);
		Path path;
		Method[] methods=providerClass.getMethods();
		int size=0;
		for (int i = 0; i < methods.length; i++) {
			Method method=methods[i];
			path=method.getAnnotation(Path.class);
			if(path==null){
				continue;
			}
			size++;
		}
		Api[] apis=new Api[size];
		for (int i = 0; i < methods.length; i++) {
			Method method=methods[i];
			path=method.getAnnotation(Path.class);
			if(path==null){
				continue;
			}
			String apiPath=path.value().replace("/{params}","");
			Parameter param=method.getAnnotation(Parameter.class);
			Class<?> paramClass=null;
			Class<?> responseBean=null;
			com.mbv.open.api.internal.util.Api apiInfo=method.getAnnotation(com.mbv.open.api.internal.util.Api.class);
			try {
				if(param!=null){
					paramClass = Class.forName(param.value().getName());
				}
				responseBean=Class.forName(method.getReturnType().getName());				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			String requestName=paramClass.getSimpleName();
			String responseName=responseBean.getSimpleName();
			Api api=new Api();
			
			if(apiInfo!=null){
				api.setbName(apiInfo.name());
				api.setDesc(apiInfo.desc());
				api.setDemoJson(apiInfo.resultJson());
			}
			
			api.setChildPackage(childPackage);
			api.setName(method.getName());
			api.setPath(apiPath);
			api.setParentPath(provider.getUrl());
			
			Request request=new Request();
			request.setName(requestName);
			request.setResponseName(responseName);
			request.setChildPackage(childPackage);
			request.setFullPackage("com.mbv.open.api.sdk.request."+childPackage);
			setFieldsAndImports(request,paramClass,api,provider);			
			
			Response response=new Response();
			response.setName(responseName);
			response.setFullPackage("com.mbv.open.api.sdk.response."+childPackage);
			response.setChildPackage(childPackage);
			setFieldsAndImports(response,responseBean,api,provider);
			
			request.getImportObjects().add(response.getFullPackage()+"."+response.getName());
			
			api.setRequest(request);
			api.setResponse(response);
			gen("request.vm", request.getFullPackage() ,requestName, api);
			gen("response.vm", response.getFullPackage(),responseName, api);
			
//			genCSharp("CSharpRequest.vm", request.getFullPackage() ,requestName, api);
//			genCSharp("CSharpResponse.vm", response.getFullPackage(),responseName, api);
			
			genPage("tool.vm", "/tool/"+provider.getUrl() , api.getName(), api, request);
			genPage("example.vm", "/example/"+provider.getUrl() , api.getName(), api, request);
			int index=apiInfo.index()-1;
			apis[index]=api;	
		}
		return CollectionUtils.arrayToList(apis);
	}
	
	private void setFieldsAndImports(Request request,Class<?> paramClass,Api api,Provider provider){
		List<com.mbv.open.api.tool.bean.Field> fields=new ArrayList<com.mbv.open.api.tool.bean.Field>();
		Field[] originFields=paramClass.getDeclaredFields();
		List<String> imports=new ArrayList<String>(); 
		for(Field originField:originFields){
			if(originField.getName().equals("serialVersionUID")){
				continue;
			}
			com.mbv.open.api.tool.bean.Field field=toField(originField);
			if(field.getFullType().startsWith("com.mbv")){
				genBean(field.getType(),field.getFullType(), api,provider);
				field.setFullType("com.mbv.open.api.sdk.bean."+request.getChildPackage()+"."+field.getType());
				field.setObjectType(field.getType());
			}else if(field.getType().contains("List")){
				FieldType fieldType=originField.getAnnotation(FieldType.class);
				if(fieldType!=null){
					Class<?> beanClass=fieldType.value();
					String entryType=beanClass.getSimpleName();
					String entryFullType="com.mbv.open.api.sdk.bean."+request.getChildPackage()+"."+entryType;
					genBean(entryType,beanClass.getName(), api,provider);
					field.setType(field.getType()+"<"+entryType+">");
					field.setObjectType(entryType);
					putImport(imports, entryFullType);
				}
			}
			fields.add(field);
			putImport(imports, field.getFullType());
		}		
		request.setImportObjects(imports);
		request.setFields(fields);
	}
	
	private void setFieldsAndImports(Response response,Class<?> paramClass,Api api,Provider provider){
		List<com.mbv.open.api.tool.bean.Field> fields=new ArrayList<com.mbv.open.api.tool.bean.Field>();
		Field[] originFields=paramClass.getDeclaredFields();
		List<String> imports=new ArrayList<String>(); 
		for(Field originField:originFields){
			if(originField.getName().equals("serialVersionUID")){
				continue;
			}
			com.mbv.open.api.tool.bean.Field field=toField(originField);
			if(field.getFullType().startsWith("com.mbv")){
				genBean(field.getType(),field.getFullType(), api,provider);					
				field.setFullType("com.mbv.open.api.sdk.bean."+response.getChildPackage()+"."+field.getType());	
				field.setObjectType(field.getType());
			}else if(field.getType().contains("List")){
				FieldType fieldType=originField.getAnnotation(FieldType.class);
				if(fieldType!=null){
					Class<?> beanClass=fieldType.value();
					String entryType=beanClass.getSimpleName();
					String entryFullType="com.mbv.open.api.sdk.bean."+response.getChildPackage()+"."+entryType;
					genBean(entryType,beanClass.getName(), api,provider);
					field.setType(field.getType()+"<"+entryType+">");
					field.setObjectType(entryType);
					putImport(imports, entryFullType);
				}
			}
			fields.add(field);
			putImport(imports, field.getFullType());
		}		
		response.setImportObjects(imports);
		response.setFields(fields);
	}
	
	private void setFieldsAndImports(Bean bean,Class<?> paramClass,Api api,Provider provider){
		List<com.mbv.open.api.tool.bean.Field> fields=new ArrayList<com.mbv.open.api.tool.bean.Field>();
		Field[] originFields=paramClass.getDeclaredFields();
		List<String> imports=new ArrayList<String>(); 
		for(Field originField:originFields){
			if(originField.getName().equals("serialVersionUID")){
				continue;
			}
			com.mbv.open.api.tool.bean.Field field=toField(originField);
			if(field.getFullType().startsWith("com.mbv")){
				genBean(field.getType(),field.getFullType(), api,provider);	
				field.setFullType("com.mbv.open.api.sdk.bean."+bean.getChildPackage()+"."+field.getType());							
				field.setObjectType(field.getType());
			}else if(field.getType().contains("List")){
				FieldType fieldType=originField.getAnnotation(FieldType.class);
				if(fieldType!=null){
					Class<?> beanClass=fieldType.value();
					String entryType=beanClass.getSimpleName();
					String entryFullType="com.mbv.open.api.sdk.bean."+bean.getChildPackage()+"."+entryType;
					genBean(entryType,beanClass.getName(), api,provider);
					field.setType(field.getType()+"<"+entryType+">");
					field.setObjectType(entryType);
					putImport(imports, entryFullType);
				}
			}
			fields.add(field);
			putImport(imports, field.getFullType());
		}		
		bean.setImportObjects(imports);
		bean.setFields(fields);
	}
	
	private void genBean(String  name,String type,Api api,Provider provider){
		Class<?> beanClass=null;
		try {
			beanClass=Class.forName(type);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Bean bean=new Bean();
		bean.setChildPackage(api.getChildPackage());
		setFieldsAndImports(bean, beanClass,api,provider);
		bean.setFullPackage("com.mbv.open.api.sdk.bean."+bean.getChildPackage());
		bean.setName(name);
		api.setBean(bean);
		gen("bean.vm", bean.getFullPackage() ,name, api);
//		genCSharp("CSharpEntity.vm", bean.getFullPackage() ,name, api);
		genBeanPage("object.vm", provider.getUrl() ,name, provider,api);
	}
	
	private void putImport(List<String> imports,String importClass){
		if(importClass.contains(".")){
			if(!imports.contains(importClass)){
				imports.add(importClass);
			}
		}
	}
	
	private com.mbv.open.api.tool.bean.Field toField(Field field){
		com.mbv.open.api.tool.bean.Field result=new com.mbv.open.api.tool.bean.Field();
		String jsonName=NameUtil.get().toUnderline(field.getName());
		Class<?> type=field.getType();
		result.setName(field.getName());
		result.setType(type.getSimpleName());
		result.setFullType(type.getName());
		result.setJsonName(jsonName);
		com.mbv.open.api.internal.util.Field fieldDesc=field.getAnnotation(com.mbv.open.api.internal.util.Field.class);
		if(fieldDesc!=null){
			result.setDesc(fieldDesc.value());
			result.setNullable(fieldDesc.nullable());
			result.setDefaultValue(fieldDesc.defaultValue());
		}
		return result;
	}


	
//
//	public String getFileName() {
//		return fileName;
//	}
//
//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}
//	public String getFullFileName(Table table){
//		VelocityContext context = new VelocityContext();
//		context.put("table", getTableNameHolder(table));
//		StringWriter writer = new StringWriter();  
//	    try {
//			engine.evaluate(context, writer, "", fileName);
//		} catch (ParseErrorException | MethodInvocationException
//				| ResourceNotFoundException | IOException e) {
//			e.printStackTrace();
//		}  
//	    return writer.toString();
//	}
//	protected String getTableNameHolder(Table table){
//		return table.getName();
//	}
//	

	
}
