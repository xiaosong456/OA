package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ReplyService;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-30 下午4:57:16
 */
@Service
@SuppressWarnings("unchecked")
public class ReplyServiceImpl extends BaseDaoImpl<Reply> implements ReplyService {

	public List<Reply> findByTopic(Topic topic) {
		
		return getSession().createQuery(
				"from Reply r where r.topic=? order by r.postTime asc")
				.setParameter(0, topic)
				.list();
	}

	@Override
	public void save(Reply reply) {
		
		//保存到数据库
		getSession().save(reply);
		
		
		//维护相关信息
		Topic topic=reply.getTopic();
		Forum forum=topic.getForum();
		
		forum.setArticleCount(forum.getArticleCount()+1);
		
		topic.setReplyCount(topic.getReplyCount()+1);
		topic.setLastReply(reply);
		topic.setLastUpdateTime(reply.getPostTime());
		
		getSession().update(topic);
		getSession().update(forum);
	}
	
	
	
}
