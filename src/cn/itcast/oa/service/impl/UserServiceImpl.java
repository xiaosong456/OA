package cn.itcast.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.UserService;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-28 下午2:21:40
 */
@Service
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

	public User getByLoginNameAndPassword(String loginName, String password) {
		
		return (User) getSession().createQuery(
				"from User u where u.loginName=? and u.password=?")
				.setParameter(0, loginName)
				.setParameter(1, DigestUtils.md5Hex(password))
				.uniqueResult();

	}
	
	
}
