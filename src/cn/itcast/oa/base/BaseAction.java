package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.ForumService;
import cn.itcast.oa.service.PrivilegeService;
import cn.itcast.oa.service.ReplyService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.TopicService;
import cn.itcast.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-28 下午3:19:15
 */

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	@Resource
	protected RoleService roleService;
	
	@Resource
	protected DepartmentService departmentService;
	
	@Resource
	protected UserService userService;
	
	@Resource
	protected PrivilegeService privilegeService;
	
	@Resource
	protected ForumService forumService;
	
	@Resource
	protected TopicService topicService;
	
	@Resource
	protected ReplyService  replyService;
	
	
	protected T model;//new T();
	
	public BaseAction(){
		try {
			
			//得到model的类型信息
			ParameterizedType pt= (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz=(Class) pt.getActualTypeArguments()[0];
			
			//通过反射生成model实例
			model=(T) clazz.newInstance();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public T getModel(){
		return model;
	}
	
	/**
	 * 获取当前登录对象
	 * @return
	 */
	protected User getCurrentUser(){
		return (User)ActionContext.getContext().getSession().get("user");
	}
	
	
}
