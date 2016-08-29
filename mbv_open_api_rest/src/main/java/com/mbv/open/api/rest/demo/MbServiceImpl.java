package com.mbv.open.api.rest.demo;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.mbv.open.api.annotation.Parameter;
import com.mbv.open.api.bean.MbParam;
import com.mbv.open.api.bean.UserContext;
import com.mbv.open.api.client.demo.MbService;
import com.mbv.open.api.common.util.JsonUtil;
import com.mbv.open.api.rest.BaseServiceImpl;
import com.mbv.open.api.result.MbResponseResult;
import com.mbv.open.api.result.MbResult;

@Path("")
public class MbServiceImpl extends BaseServiceImpl implements MbService {

    @GET
    @POST  
    @Path("index.html")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
    public MbResponseResult index(){    	
    	MbResponseResult result = new MbResponseResult();
        return result;
    }
    
    @GET
    @POST  
    @Path("")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
    public MbResponseResult root(){    	
    	MbResponseResult result = new MbResponseResult();
        return result;
    }

    @GET
    @POST  
    @Path("hello")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(MbParam.class)
    public MbResult sayHello(@Context SecurityContext  securityContext,@QueryParam("params") String mbResult){    	
        UserContext userContext=getUserContext(securityContext);
		MbParam params=JsonUtil.toObject(mbResult, MbParam.class);
        MbResult result = new MbResult();
        result.setResultName(userContext.getSellerCode());
        result.setResultValue(params.getParamValue());
        return result;
    }
}
