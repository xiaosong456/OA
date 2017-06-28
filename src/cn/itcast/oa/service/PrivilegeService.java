package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Privilege;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-29 下午1:53:03
 */

public interface PrivilegeService extends BaseDao<Privilege> {

	/**
	 * 查询所有顶级权限的集合
	 * @return
	 */
	List<Privilege> findTopList();

	/**
	 * 查询所有权限url集合(不能有null且不重复)
	 * @return
	 */
	List<String> getAllPrivilegeUrls();

}
