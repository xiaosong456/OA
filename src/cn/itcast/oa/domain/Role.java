package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 实体：岗位
 * @author xiao-song
 *
 */
public class Role implements Serializable {
	private String description;
	private Long id;
	private String name;
	private Set<User> users=new HashSet<User>();
	
	private Set<Privilege> privileges=new HashSet<Privilege>();
	
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
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
	public Set<User> getUsers() {
		return users;
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
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
