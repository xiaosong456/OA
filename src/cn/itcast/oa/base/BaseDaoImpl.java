package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


/**
 *@author 作者：lxs
 *@version 创建时间：2017-5-28 上午1:11:44
 */

//@Transactional注解可以被继承,即对其子类有效
@Transactional
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;
	
	protected Class<T> clazz;//........
	
	public BaseDaoImpl(){
		//通过反射得到T的真实类型
		ParameterizedType pt=(ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz=(Class) pt.getActualTypeArguments()[0];
		
		System.out.println("clazz="+clazz.getName());
	}
	
	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}
	
	public void delete(Long id) {
		Object obj=getSession().get(clazz, id);
		getSession().delete(obj);
	}


	public T getById(Long id) {
		if(id==null){
			return null;
		}
		return (T) getSession().get(clazz, id);
	}

	public List<T> getByIds(Long[] ids) {
		
		if(ids==null||ids.length==0){
			return Collections.EMPTY_LIST;
		}
		
		return getSession().createQuery(
				"from "+clazz.getSimpleName()+" where id in (:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}

	public List<T> findAll() {
		return getSession().createQuery(
				"from "+clazz.getSimpleName())
				.list();
	}
	/**
	 * 获取当前可用的session
	 * @return
	 */
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

}
