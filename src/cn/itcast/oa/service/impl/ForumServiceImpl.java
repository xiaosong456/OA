package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.service.ForumService;

/**
 * @author 作者：lxs
 * @version 创建时间：2017-5-30 上午11:44:23
 */
@Service
@SuppressWarnings("unchecked")
public class ForumServiceImpl extends BaseDaoImpl<Forum> implements
		ForumService {

	@Override
	public void save(Forum forum) {
		// 指定position的值为最大 SELECT MAX(f.position) from Forum f 或
		// 将position的值设置为和主键一样
		getSession().save(forum);

		forum.setPosition(forum.getId().intValue());

		// 因为是持久化状态,所以不需要调用update()方法
	}

	@Override
	public List<Forum> findAll() {
		return getSession().createQuery("from Forum f order by f.position asc")
				.list();
	}

	public void moveUp(Long id) {
		// 获取要交换的两个forum
		Forum forum = getById(id);// 当前操作的forum

		Forum other = (Forum) getSession()
				.createQuery(
						"from Forum f where f.position<? order by position desc")
				.setParameter(0, forum.getPosition()).setFirstResult(0)
				.setMaxResults(1).uniqueResult();// 我上面那个forum

		// 对上面的不能上移
		if (other == null) {
			return;
		}

		// 交换position的值
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);

		// 更新到数据库
		// 因为是持久化状态，所以不需要调用update()方法

	}

	public void moveDown(Long id) {

		// 获取要交换的两个forum
		Forum forum = getById(id);// 当前操作的forum

		Forum other = (Forum) getSession()
				.createQuery(
						"from Forum f where f.position>? order by position asc")
				.setParameter(0, forum.getPosition()).setFirstResult(0)
				.setMaxResults(1).uniqueResult();// 我上面那个forum

		// 对上面的不能上移
		if (other == null) {
			return;
		}

		// 交换position的值
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);

		// 更新到数据库
		// 因为是持久化状态，所以不需要调用update()方法

	}

}
