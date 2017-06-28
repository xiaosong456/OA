package cn.itcast.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-30 上午11:26:05
 */

public class Forum {
	private String description;
	private Long id;
	private String name;
	private int position;
	
	private Set<Topic> topics =new HashSet<Topic>();
	private int topicCount;//主题数
	private int articleCount;//文章数（主题+回帖）
	private Topic lastTopic;//最后发表的主题
	
	
	
	public Topic getLastTopic() {
		return lastTopic;
	}
	public void setLastTopic(Topic lastTopic) {
		this.lastTopic = lastTopic;
	}
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	public int getTopicCount() {
		return topicCount;
	}
	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public String getDescription() {
		return description;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getPosition() {
		return position;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
