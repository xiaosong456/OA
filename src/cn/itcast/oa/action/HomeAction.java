package cn.itcast.oa.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-29 下午7:00:55
 */
@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport {
	
	public String index(){
		
		return "index";
	}
	public String top(){
		
		return "top";
	}
	public String bottom(){
		
		return "bottom";
	}
	public String left(){
		
		return "left";
	}
	public String right(){
		
		return "right";
	}
	
	
}
