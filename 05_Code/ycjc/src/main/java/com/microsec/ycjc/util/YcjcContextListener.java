package com.microsec.ycjc.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class YcjcContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("++++++++++++++++++++++++ servletContext init start +++++++++++++++++++++++++");
		//��ʱcontextDestroyed������ִ�У�Ϊ�˷�ֹ�����
		ServletContext context = event.getServletContext();
		WebApplicationContext webApplication = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		//===================================
		System.out.println("++++++++++++++++++++++++ servletContext init end +++++++++++++++++++++++++");
	}
}
