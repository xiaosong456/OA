package cn.itcast.oa.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author 作者：lxs
 * @version 创建时间：2017-5-28 下午2:16:24
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private Long departmentId;
	private Long[] roleIds;

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	/**登录界面*/
	public String loginUI(){
		
		return "loginUI";
	}
	
	/**登录*/
	public String login(){
		
		//查询用户
		User user=userService.getByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		
		if(user==null){
			
			//没有，用户名或密码不正确
			addFieldError("login","用户名或密码不正确");
			
			return "loginUI";
		}else{
			//正确就登录用户
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex"; 
		}
		
	}
	
	/**注销*/
	public String logout(){
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	
	
	/** 列表 */
	public String list() {
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}

	/** 删除 */
	public String delete() {
		userService.delete(model.getId());
		return "toList";
	}

	/** 添加 */
	public String add() {
		// 新建对象并设置属性
		model.setDepartment(departmentService.getById(departmentId));
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		model.setPassword(DigestUtils.md5Hex("1234"));
		// 保存到数据库
		userService.save(model);
		return "toList";
	}

	/** 添加页面 */
	public String addUI() {
		// 准备数据 departmentList
		// 显示树状结构
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备数据roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "addUI";
	}

	/** 修改 */
	public String edit() {
		// 从数据库取出原对象
		User user = userService.getById(model.getId());

		// 设置要修改的属性>>普通属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// 设置要修改的属性>>所属部门
		Department department = departmentService.getById(departmentId);
		user.setDepartment(department);
		//关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		// 更新到数据库
		userService.update(user);
		
		return "toList";
	}

	/** 修改页面 */
	public String editUI() {
		// 准备回显信息
		
		// 准备数据 departmentList  显示树状结构
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		//准备角色信息 roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		// 回显对象信息
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		
		if(user.getDepartment()!=null){
			departmentId=user.getDepartment().getId();
		}
		if(user.getRoles().size()>0){
			roleIds=new Long[user.getRoles().size()];
			int index=0;
			for(Role role:user.getRoles()){
				roleIds[index++]=role.getId();
			}
		}

		return "editUI";
	}

	/** 初始化密码位 1234 */
	public String initPassword() {
		//从数据库中取出原对象
		User user = userService.getById(model.getId());
		//将密码设置为初始密码
		user.setPassword(DigestUtils.md5Hex("1234"));
		//更新到数据库
		userService.save(user);
		return "toList";
	}

}
