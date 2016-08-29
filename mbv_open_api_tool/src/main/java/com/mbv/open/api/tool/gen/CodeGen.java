package com.mbv.open.api.tool.gen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbv.open.api.tool.code.ProviderCode;

@Service
public class CodeGen {
	
   @Autowired
   private ProviderCode providerCode;
   
   public void gen(){
	   providerCode.process();
   }

}

