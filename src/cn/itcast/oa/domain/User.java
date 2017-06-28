package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

public class User implements Serializable {

	private Department  department;
	private String description;
	private String email;
	private String gender;
	private Long id;
	private String loginName;
	private String name;

	private String password;

	private String phoneNumber;

	private Set<Role> roles;
	
	/**
	 * 判断呢本用户是否有指定名称的权限
	 * @param privilegeName
	 * @return
	 */
	public boolean hasPrivilegeByName(String privilegeName){
		//超级管理员有所有的权限
		if(isAdmin()){
			return true;
		}
		
		
		//一般用户要有权限才能返回true
		for(Role role:roles){
			for(Privilege privilege:role.getPrivileges()){
				if(privilege.getName().equals(privilegeName)){
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 通过url判断用户是否有权限访问
	 * 
	 * @param privilegeUrl
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String privilegeUrl) {
		if(isAdmin()){
			return true;
		}
		//如果以UI后缀结尾，就去掉UI后缀，以得到对应权限(例如：addUI和add是同一个权限)
		if(privilegeUrl.endsWith("UI")){
			privilegeUrl=privilegeUrl.substring(0, privilegeUrl.length()-2);
		}
		
		
		
		//其他用户要是有权限才返回true
		
		List<String> allPrivilegeUrls=(List<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		if(!allPrivilegeUrls.contains(privilegeUrl)){
			
			//如果是不需要控制的功能，则所有用户都可以访问
			return true;
			
		}
		
		for(Role role:roles){
			for(Privilege privilege:role.getPrivileges()){
				if(privilegeUrl.equals(privilege.getUrl())){
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	private boolean isAdmin() {
			return "admin".equals(loginName);
	}


	public Department getDepartment() {
		return department;
	}

	public String getDescription() {
		return description;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public Long getId() {
		return id;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	

}
