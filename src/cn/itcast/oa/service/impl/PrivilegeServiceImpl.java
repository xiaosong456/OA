package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-29 下午1:54:00
 */
@Service
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {

	public List<Privilege> findTopList() {
		return getSession().createQuery(
				"from Privilege p where p.parent is NULL")
				.list();
	}

	public List<String> getAllPrivilegeUrls() {
		return getSession().createQuery(
				"select distinct p.url from Privilege p where p.url is not null")
				.list();
	}


}
