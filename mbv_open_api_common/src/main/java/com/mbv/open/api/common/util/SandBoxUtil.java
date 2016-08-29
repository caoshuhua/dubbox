package com.mbv.open.api.common.util;
import java.util.HashMap;
import java.util.Map;

import com.mbv.open.api.common.constant.SandBoxConstant;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class SandBoxUtil{

	public static Map<String,String> sandBoxResponses;
	
	static{
		sandBoxResponses=new HashMap<String, String>();
	    sandBoxResponses.put("mb.product.verdorproduct.get", SandBoxConstant.MB_PRODUCT_VERDORPRODUCT_GET);                 
    
	    sandBoxResponses.put("mb.logistics.expresscompany.get", SandBoxConstant.MB_LOGISTICS_EXPRESSCOMPANY_GET);                 
    
	    sandBoxResponses.put("mb.logistics.deliveryaddress.add", SandBoxConstant.MB_LOGISTICS_DELIVERYADDRESS_ADD);                 
    
	    sandBoxResponses.put("mb.order.vendorremark.get", SandBoxConstant.MB_ORDER_VENDORREMARK_GET);                 
    
	    sandBoxResponses.put("mb.order.outstorage", SandBoxConstant.MB_ORDER_OUTSTORAGE);                 
    
	    sandBoxResponses.put("mb.order.batch.get", SandBoxConstant.MB_ORDER_BATCH_GET);                 
    
	    sandBoxResponses.put("mb.logistics.returnaddress.delete", SandBoxConstant.MB_LOGISTICS_RETURNADDRESS_DELETE);                 
    
	    sandBoxResponses.put("mb.workorder.single.get", SandBoxConstant.MB_WORKORDER_SINGLE_GET);                 
    
	    sandBoxResponses.put("mb.logistics.deliveryaddress.get", SandBoxConstant.MB_LOGISTICS_DELIVERYADDRESS_GET);                 
    
	    sandBoxResponses.put("mb.order.remark.get", SandBoxConstant.MB_ORDER_REMARK_GET);                 
    
	    sandBoxResponses.put("mb.stock.sku.single.set", SandBoxConstant.MB_STOCK_SKU_SINGLE_SET);                 
    
	    sandBoxResponses.put("mb.workorder.confirm", SandBoxConstant.MB_WORKORDER_CONFIRM);                 
    
	    sandBoxResponses.put("mb.stock.sku.batch.set", SandBoxConstant.MB_STOCK_SKU_BATCH_SET);                 
    
	    sandBoxResponses.put("mb.stock.verdorstock.get", SandBoxConstant.MB_STOCK_VERDORSTOCK_GET);                 
    
	    sandBoxResponses.put("mb.workorder.add", SandBoxConstant.MB_WORKORDER_ADD);                 
    
	    sandBoxResponses.put("mb.logistics.returnaddress.add", SandBoxConstant.MB_LOGISTICS_RETURNADDRESS_ADD);                 
    
	    sandBoxResponses.put("mb.workorder.batch.get", SandBoxConstant.MB_WORKORDER_BATCH_GET);                 
    
	    sandBoxResponses.put("mb.order.remark.update", SandBoxConstant.MB_ORDER_REMARK_UPDATE);                 
    
	    sandBoxResponses.put("mb.stock.sku.single.get", SandBoxConstant.MB_STOCK_SKU_SINGLE_GET);                 
    
	    sandBoxResponses.put("mb.returnorder.receive.comfirm", SandBoxConstant.MB_RETURNORDER_RECEIVE_COMFIRM);                 
    
	    sandBoxResponses.put("mb.logistics.returnaddress.get", SandBoxConstant.MB_LOGISTICS_RETURNADDRESS_GET);                 
    
	    sandBoxResponses.put("mb.logistics.deliveryaddress.delete", SandBoxConstant.MB_LOGISTICS_DELIVERYADDRESS_DELETE);                 
    
	    sandBoxResponses.put("mb.stock.sku.batch.get", SandBoxConstant.MB_STOCK_SKU_BATCH_GET);                 
    
	    sandBoxResponses.put("mb.product.single.get", SandBoxConstant.MB_PRODUCT_SINGLE_GET);                 
    
	    sandBoxResponses.put("mb.workorder.cancel", SandBoxConstant.MB_WORKORDER_CANCEL);                 
    
	    sandBoxResponses.put("mb.returnorder.single.get", SandBoxConstant.MB_RETURNORDER_SINGLE_GET);                 
    
	    sandBoxResponses.put("mb.logistics.deliveryaddress.update", SandBoxConstant.MB_LOGISTICS_DELIVERYADDRESS_UPDATE);                 
    
	    sandBoxResponses.put("mb.logistics.returnaddress.update", SandBoxConstant.MB_LOGISTICS_RETURNADDRESS_UPDATE);                 
    
	    sandBoxResponses.put("mb.logistics.regionarea.get", SandBoxConstant.MB_LOGISTICS_REGIONAREA_GET);                 
    
	    sandBoxResponses.put("mb.order.single.get", SandBoxConstant.MB_ORDER_SINGLE_GET);                 
    
	    sandBoxResponses.put("mb.order.expressdeliveryorder.update", SandBoxConstant.MB_ORDER_EXPRESSDELIVERYORDER_UPDATE);                 
    
	    sandBoxResponses.put("mb.order.vendorremark.update", SandBoxConstant.MB_ORDER_VENDORREMARK_UPDATE);                 
    
	    sandBoxResponses.put("mb.returnorder.batch.get", SandBoxConstant.MB_RETURNORDER_BATCH_GET);                 
    
	    sandBoxResponses.put("mb.product.batch.get", SandBoxConstant.MB_PRODUCT_BATCH_GET);                 
    
		
	}
	
	public static String getSandBoxResponse(String key){
		return sandBoxResponses.get(key);
	}

}