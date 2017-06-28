package cn.itcast.oa.service;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Forum;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-30 上午11:43:28
 */

public interface ForumService extends BaseDao<Forum> {

	/**
	 * 上移,最上面的不能上移
	 * @param id
	 */
	void moveUp(Long id);

	/**
	 * 下移，最下面的部门能下移
	 * @param id
	 */
	void moveDown(Long id);

}
