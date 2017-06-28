package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Parent;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-29 上午11:53:18
 */

public class Privilege implements Serializable {
	private Set<Privilege> children=new HashSet<Privilege>();
	private String icon;
	private Long id;
	private String name;
	private Privilege parent;
	private Set<Role> roles=new HashSet<Role>();
	private String url;
	
	public Privilege(){
		super();
	}
	
	public Privilege(String name, String url, String icon,
			Privilege parent) {
		super();
		this.url = url;
		this.name = name;
		this.icon = icon;
		this.parent = parent;
	}
	public Set<Privilege> getChildren() {
		return children;
	}
	
	
	public String getIcon() {
		return icon;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Privilege getParent() {
		return parent;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public String getUrl() {
		return url;
	}
	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
