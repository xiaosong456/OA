package cn.itcast.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.util.DepartmentUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author 作者：lxs
 * @version 创建时间：2017-5-28 下午1:27:29
 */
@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	private Long parentId;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/** 列表 */
	public String list() {
		List<Department> departmentList=null;
		if(parentId==null){//查询所有顶级部门的列表
			departmentList = departmentService.findTopList();			
		}else{//查询子部门列表
			departmentList = departmentService.findChildren(parentId);
			Department parent=departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/** 删除 */
	public String delete() {
		departmentService.delete(model.getId());
		return "toList";
	}

	/** 添加 */
	public String add() {
		// 1、新建对象并封装属性，也可以使用model
		model.setParent(departmentService.getById(parentId));
		// 2、保存到数据库中
		departmentService.save(model);
		return "toList";
	}

	/** 添加页面 */
	public String addUI() {
		// 显示树状结构
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "addUI";
	}

	/** 修改 */
	public String edit() {
		// 从数据库取出原对象
		Department department = departmentService.getById(model.getId());

		// 设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));
		// 更新到数据库
		departmentService.update(department);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() {
		// 准备数据 departmentList
		//显示树状结构
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备回显信息
		Department department = departmentService.getById(model.getId());
		if(department.getParent()!=null){
			parentId=department.getParent().getId();
		}

		// 把对象放到值栈的栈顶
		ActionContext.getContext().getValueStack().push(department);
		return "editUI";
	}

}
