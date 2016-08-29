package com.mbv.open.api.tool.bean;

import java.util.List;

public class Bean {
	
	private String fullPackage;
	
	private List<String> importObjects;
	
	private String name;
	
	private List<Field> fields;
	
	private String comment;
	
	private String childPackage;

	public String getFullPackage() {
		return fullPackage;
	}

	public void setFullPackage(String fullPackage) {
		this.fullPackage = fullPackage;
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

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getChildPackage() {
		return childPackage;
	}

	public void setChildPackage(String childPackage) {
		this.childPackage = childPackage;
	}
	

}
