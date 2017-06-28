package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-30 下午4:55:27
 */

public interface TopicService extends BaseDao<Topic> {
	
	/**
	 * 查询指定板块中的主题列表 排序：把所有置顶贴先是在最上面   紧挨着显示最新状态的主题  最后是一般帖
	 * @param forum
	 * @return
	 */
	List<Topic> findByForum(Forum forum);

}
