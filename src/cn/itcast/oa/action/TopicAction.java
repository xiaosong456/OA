package cn.itcast.oa.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

import com.opensymphony.xwork2.ActionContext;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-30 下午4:19:03
 */
@Service
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {
	
	public Long forumId;
	
	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	/**显示单个主题(主题+回帖列表)*/
	public String show(){
		//准备数据 topic
		Topic topic=topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		
		//准备数据 replyList
		List<Reply> replyList=replyService.findByTopic(topic);
		ActionContext.getContext().put("replyList", replyList);
		
		return "show";
	}

	/**发表新主题页面*/
	public String addUI(){
		//准备数据：forum
		Forum forum=forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		
		return "addUI";
	}
	
	
	/**发表新主题*/
	public String add(){
		//封装
		
		// 表单中已经封装了 title content faceIcon
		model.setForum(forumService.getById(forumId));
	
		model.setAuthor(getCurrentUser()); 	//登录用户
	
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());	//用户ip地址
		
		model.setPostTime(new Date());	//发表时间
		
		//应放到业务方法中的设置
//		model.setLastUpdateTime(lastUpdateTime)	
//		model.setReplyCount(replyCount);	//回复数量
//		model.setType(type);	//类型
//		model.setLastReply(lastReply);
		
		//保存
		topicService.save(model);
		
		return "toShow";//转到新主题的显示页面
	}
	
	
}
