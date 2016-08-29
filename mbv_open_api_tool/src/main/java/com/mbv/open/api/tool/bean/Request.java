package com.mbv.open.api.tool.bean;

import java.util.List;

public class Request {
	
	private String path;
	
	private List<String> importObjects;
	
	private String name;
	
	private String responseName;
	
	private List<Field> fields;
	
	private String comment;
	
	private String childPackage;
	
	private String fullPackage;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getImportObjects() {
		return importObjects;
	}

	public void setImportObjects(List<String> importObjects) {
		this.importObjects = importObjects;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResponseName() {
		return responseName;
	}

	public void setResponseName(String responseName) {
		this.responseName = responseName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public String getChildPackage() {
		return childPackage;
	}

	public void setChildPackage(String childPackage) {
		this.childPackage = childPackage;
	}

	public String getFullPackage() {
		return fullPackage;
	}

	public void setFullPackage(String fullPackage) {
		this.fullPackage = fullPackage;
	}
	
	

}
