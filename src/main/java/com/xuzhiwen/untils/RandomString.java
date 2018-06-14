package com.xuzhiwen.untils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomString {
	public static String getRandomStr() {
		return "auto_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
}
