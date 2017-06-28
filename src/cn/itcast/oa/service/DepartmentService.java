package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Department;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-28 下午1:37:58
 */

public interface DepartmentService extends BaseDao<Department> {

	/**
	 * 查询所有顶级部门
	 * @return
	 */
	List<Department> findTopList();
	
	/**
	 * 查询子部门列表
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);

	
}
