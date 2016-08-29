package com.mbv.open.api.client.demo;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.mbv.open.api.result.MbResult;



public interface MbService {
    MbResult sayHello(@Context SecurityContext  securityContext,String name);
}