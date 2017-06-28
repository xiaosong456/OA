package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-30 下午4:55:40
 */

public interface ReplyService extends BaseDao<Reply> {
	
	/**
	 * 查询制定主题中所有的回复列表，排序：最前面的时最早的
	 * @param topic
	 * @return
	 */
	List<Reply> findByTopic(Topic topic);

}
