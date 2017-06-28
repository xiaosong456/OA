package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.TopicService;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-30 下午4:56:10
 */
@Service
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends BaseDaoImpl<Topic> implements TopicService {

	public List<Topic> findByForum(Forum forum) {
		
		return getSession().createQuery(
				//TODO 排序：所有置顶贴在最上面，然后把最新状态的主题显示在前面
				"from Topic t where t.forum=? order by (case t.type when 2 then 2 else 0 end) desc, t.lastUpdateTime desc")
				.setParameter(0, forum)
				.list();
	}

	@Override
	public void save(Topic topic) { 
		//设置属性并保存
		topic.setLastUpdateTime(topic.getPostTime());	//默认主题的发表时间
//		topic.setReplyCount(0);	  //回复数量  默认是0  可以不写
//		topic.setType(Topic.TYPE_NORMAL);	//类型  默认为0 即 普通
//		topic.setLastReply(null); //默认为null 可以不写
		getSession().save(topic);//保存
		
		//维护相关信息
		Forum forum=topic.getForum();
//		主题数
		forum.setTopicCount(forum.getTopicCount()+1);
//		文章数(主题+回复)
		forum.setArticleCount(forum.getArticleCount()+1);
//		最后发表的主题
		forum.setLastTopic(topic);
		
		getSession().update(forum);

	}
	
	

}
