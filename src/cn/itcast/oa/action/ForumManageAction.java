package cn.itcast.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;

/**
 * @author 作者：lxs
 * @version 创建时间：2017-5-30 上午11:30:29
 */
@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum> {

	/** 列表 */
	public String list() {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	/** 删除 */
	public String delete() {
		
		forumService.delete(model.getId());
		
		return "toList";
	}

	/** 添加页面 */
	public String addUI() {

		return "saveUI";
	}

	/** 添加 */
	public String add() {
		forumService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() {

		// 准备回显数据
		Forum forum = forumService.getById(model.getId());

		// 放到值栈的栈顶，直接写属性或名称会从栈顶找相应对象属性或名称，例如：如果前台页面有
		// <s:textfield name="description"> name再回显时相当于一个ognl表达式。会先找对象栈中的属性，
		// 把对象放到栈顶就刚好被他找到就会回显，栈顶找不到，会从map中找。
		ActionContext.getContext().getValueStack().push(forum);

		return "saveUI";
	}

	/** 修改 */
	public String edit() {
		//1、从数据库中取出原对象
		Forum forum=forumService.getById(model.getId());
		
		//2、设置要修改的属性
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		
		//3、保存到数据库
		forumService.update(forum);
		
		return "toList";
	}

	/** 上移 */
	public String moveUp() {
		
		forumService.moveUp(model.getId());
		
		return "toList";
	}

	/** 下移 */
	public String moveDown() {
		
		forumService.moveDown(model.getId());
		
		return "toList";
	}
}
