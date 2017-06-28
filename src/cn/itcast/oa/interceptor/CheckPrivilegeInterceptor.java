package cn.itcast.oa.interceptor;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-29 下午11:06:30
 */

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		
		//获取当前登录用户
		User user=(User)ActionContext.getContext().getSession().get("user");
		
		//获取当前访问的url，并去掉前缀
		String actionName=invocation.getProxy().getActionName();
		String namspace=invocation.getProxy().getNamespace();
		String privilegeUrl="";
		if(namspace.endsWith("/")){
			privilegeUrl=namspace+actionName;			
		}else{
			privilegeUrl=namspace+"/"+actionName;			
		}
		
		//要去掉开头的'/'
		if(privilegeUrl.startsWith("/")){
			privilegeUrl=privilegeUrl.substring(1);
		}
		
		//如果用户未登录
		if(user==null){
			if(!privilegeUrl.startsWith("userAction_login")){
				//如果不是去登录，就转到登录界面
				return "loginUI";
			}else{
				//如果正在登录就放行
				return invocation.invoke();
			}
		}
		//如果用户已登录
		else{
			if(user.hasPrivilegeByUrl(privilegeUrl)){
				//如果有权限就放行
				return invocation.invoke();
			}else{
				//如果没有权限,就转到提示页面
				return "noPrivilegeError";
			}
		}
		
	}

}
