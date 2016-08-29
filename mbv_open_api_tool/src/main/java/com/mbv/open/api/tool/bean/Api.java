package com.mbv.open.api.tool.bean;

public class Api {
	
	private String name;
	
	private String path;
	
	private String parentPath;
	
	private Request request;
	
	private Response response;
	
	private Bean bean;
	
	private String childPackage;
	
	private String bName;
	
	private String desc;
	
    private String demoJson;

	public String getDemoJson() {
		return demoJson;
	}

	public void setDemoJson(String demoJson) {
		this.demoJson = demoJson;
	}
	
	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Bean getBean() {
		return bean;
	}

	public void setBean(Bean bean) {
		this.bean = bean;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getChildPackage() {
		return childPackage;
	}

	public void setChildPackage(String childPackage) {
		this.childPackage = childPackage;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

}
