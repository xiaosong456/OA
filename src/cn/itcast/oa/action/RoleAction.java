package cn.itcast.oa.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.PrivilegeService;
import cn.itcast.oa.service.RoleService;

import com.opensymphony.xwork2.ActionContext;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-28 上午10:10:40
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	
	private Long[] privilegeIds;
	
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	/** 列表*/
	public String list(){
		List<Role> roleList=roleService.findAll();
		ActionContext.getContext().put("roleList",roleList );
		return "list";
	}
	
	/**删除*/
	public String delete(){
		roleService.delete(model.getId());
		return "toList";
	}
	
	/**添加*/
	public String add(){
		//得到参数，封装成对象,当使用实体作为model时，可以直接使用model
//		Role role=new Role();
//		role.setName(name);
//		role.setDescription(description);
//		roleService.save(role);
		//保存到数据库中
		roleService.save(model);
		return "toList";
	}
	
	/**添加页面*/
	public String addUI(){
		
		return "addUI";
	}
	
	/**修改*/
	public String edit(){
		//从数据库中取出原对象
		Role role=roleService.getById(model.getId());
		//设置要修改的属性
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		
		//更新到数据库中
		roleService.update(role);
		
		return "toList";
	}
	
	/**修改页面*/
	public String editUI(){
		Role role=roleService.getById(model.getId());
//		this.name=role.getName();
//		this.description=role.getDescription();
		
		//将要显示的对象放到栈顶
		ActionContext.getContext().getValueStack().push(role);
		return "editUI";
	}
	
	/**设置权限*/
	public String setPrivilege(){
		//从数据库中取出原对象
		Role role=roleService.getById(model.getId());
		
		// 设置要修改的属性
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		
		//更新到数据库中
		roleService.update(role);
		
		return "toList";
	}
	
	/**设置权限页面*/
	public String setPrivilegeUI(){
		//准备权限数据
		Role role=roleService.getById(model.getId());
		ActionContext.getContext().put("role", role);
		
		List<Privilege> privilegeList=privilegeService.findTopList();
		ActionContext.getContext().put("privilegeList", privilegeList);
		
		
		//准备回显数据
		privilegeIds = new Long[role.getPrivileges().size()];
		int index = 0;
		for (Privilege privilege : role.getPrivileges()) {
			privilegeIds[index++] = privilege.getId();
		}
		
		return "setPrivilegeUI";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
