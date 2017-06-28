package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-28 下午1:38:26
 */
@Service
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends BaseDaoImpl<Department> implements DepartmentService {

	public List<Department> findTopList() {
		
		return getSession().createQuery(
				"from Department d where d.parent is NULL")
				.list();
	}

	public List<Department> findChildren(Long parentId) {
		
		return getSession().createQuery(
				"from Department d where d.parent.id=?")
				.setParameter(0, parentId)
				.list();
	}
	
}
