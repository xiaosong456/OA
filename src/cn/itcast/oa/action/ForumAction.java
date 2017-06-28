package cn.itcast.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;

import com.opensymphony.xwork2.ActionContext;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-30 下午4:19:17
 */
@Service
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {
	
	/**板块列表*/
	public String list(){
		List<Forum> forumList= forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		
		return "list";
	}
	
	
	/**显示板块*/
	public String show(){
		
		//准备数据 forum
		Forum forum=forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		
		//准备数据：主题列表
		List<Topic> topicList=topicService.findByForum(forum);
		ActionContext.getContext().put("topicList", topicList);

		
		return "show";
	}
}
