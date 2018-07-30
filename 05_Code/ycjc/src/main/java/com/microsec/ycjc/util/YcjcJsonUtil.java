package com.microsec.ycjc.util;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class YcjcJsonUtil {

	/**
	 * 将对象转换成JSON字符串，并响应回前台
	 * 
	 * @param object
	 * @throws IOException
	 */
	public static void writeJson(Object object,HttpServletResponse response) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			System.out.println(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
