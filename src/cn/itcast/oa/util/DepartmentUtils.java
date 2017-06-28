package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.itcast.oa.domain.Department;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-28 下午9:07:34
 */

public class DepartmentUtils {
	
	/**
	 * 遍历所有部门树，得到所有部门列表，并修改了名称以表示层次
	 * @param topList
	 * @return
	 */
	public static List<Department> getAllDepartments(List<Department> topList) {
		List<Department> list=new ArrayList<Department>();
		walkDepartmentTrees(topList,"|-",list);
		return list;
	}
	//遍历部门树，把遍历出来的部门树都放在置顶的集合中
	private static void walkDepartmentTrees(Collection<Department> topList,String prefix, List<Department> list){
		for(Department top:topList){
			Department copy=new Department();//原对象是session中的对象，是持久化状态，所以要使用副本。如果不使用副本或刷新缓有可能会将修改的值存入数据库
			copy.setId(top.getId());
			copy.setName(prefix+top.getName());
			list.add(copy);
			walkDepartmentTrees(top.getChildren(),"　"+prefix,list);//使用全角的空格
		}
	}
	
}
