package cn.itcast.oa.service;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.User;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-28 下午2:20:39
 */

public interface UserService extends BaseDao<User> {

	/**
	 * 查询用户
	 * @param loginName
	 * @param password 明文密码
	 * @return
	 */
	User getByLoginNameAndPassword(String loginName, String password);


	
}
