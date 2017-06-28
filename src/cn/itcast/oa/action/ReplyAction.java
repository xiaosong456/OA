package cn.itcast.oa.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

import com.opensymphony.xwork2.ActionContext;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-30 下午4:19:41
 */
@Service
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {
	
	private Long topicId;
	
	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	/**发表新回复页面*/
	public String addUI(){
		Topic topic=topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}
	
	/**发表新回复*/
	public String add(){
		//封装 (已经封装了 title content faceIcon)
		
		model.setAuthor(getCurrentUser());

		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());

		model.setTopic(topicService.getById(topicId));
		
		//保存
		replyService.save(model);
		
		return "toTopicShow";//转到新回复所属主题的显示页面
	}
}
