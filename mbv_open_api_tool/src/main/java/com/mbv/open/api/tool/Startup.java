package com.mbv.open.api.tool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mbv.open.api.tool.gen.CodeGen;

public class Startup {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		CodeGen codeGen=applicationContext.getBean(CodeGen.class);
		codeGen.gen();
	}

}
