package cn.itcast.oa.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-29 下午7:15:50
 */

public class InitServletContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sc) {
		
		
	}

	public void contextInitialized(ServletContextEvent sc) {
		ServletContext application=sc.getServletContext();
		
		//得到Service的实例对象
		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(application);
		PrivilegeService privilegeService=(PrivilegeService)ac.getBean("privilegeServiceImpl");
		
		
		//准备所有顶级权限的集合（顶级菜单）
		List<Privilege> topPrivilegeList=privilegeService.findTopList();
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		
		
		//准备所有权限url集合
		List<String> allPrivilegeUrls=privilegeService.getAllPrivilegeUrls();
		
		application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		
	}

}
