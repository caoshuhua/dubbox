package com.mbv.open.api.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String getDate(Date date){
		SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
		return format.format(date);
	}

}
