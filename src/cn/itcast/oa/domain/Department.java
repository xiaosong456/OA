package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.Set;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-28 上午9:03:41
 */

public class Department implements Serializable {
	private Set<Department> children;
	private String description;
	private Long id;
	private String name;
	private Department parent;
	private Set<User> users;
	public Set<Department> getChildren() {
		return children;
	}
	public String getDescription() {
		return description;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Department getParent() {
		return parent;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setChildren(Set<Department> children) {
		this.children = children;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setParent(Department parent) {
		this.parent = parent;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
