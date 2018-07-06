package com.xlfd.common.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.xlfd.common.dao.BaseDao;

public class BaseDaoImpl implements BaseDao{

	@Autowired //自动注入标识
	@Qualifier(value="sessionFactory") //配置自动注入的来源
	private SessionFactory seFa;//seFa变量的作用是Session工厂对象
	
	private Session getSession(){//getSession 是Hibernate操作数据的工具
		//使用声明式事务后，将关闭Session的代码注释：没法确定事务什么时候开始结束
		//return seFa.openSession();
		return seFa.getCurrentSession();
	}

	public void save(Object obj) {
		// TODO Auto-generated method stub
		Session se = getSession();
		//使用声明式事务后，将编程式事物先注释
		//Transaction tr = se.beginTransaction();//编程式事物
		se.save(obj);
		//tr.commit(); //提交事务  rollback():回滚事务
		//使用声明式事务后，将关闭Session的代码注释
		//se.close();
	}

	public void saveOrUpdate(Object obj) {
		// TODO Auto-generated method stub
		Session se = getSession();
		//Transaction tr = se.beginTransaction();
		se.saveOrUpdate(obj);
		//tr.commit();
		//se.close();

	}

	public void update(Object obj) {
		// TODO Auto-generated method stub
		Session se = getSession();
		se.update(obj);
		//tr.commit();
		//se.close();

	}

	public void delete(Object obj) {
		// TODO Auto-generated method stub
		Session se = getSession();
		//Transaction tr = se.beginTransaction();
		se.delete(obj);
		//tr.commit();
		//se.close();
	}

	
	public void deleteById(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		Object obj = getById(clazz, id);
		delete(obj);
	}

	public Object getById(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		Session se = getSession();
		Object obj = se.get(clazz, id);
		//se.close();
		return obj;
	}
	/**
	 * hql 要执行的HQL语句； hql更接近sql语句查询语法
	 * se.createQuery(hql);对比
	 * conn.prepareStetment(sql);
	 */
	public List queryByHql(String hql, List paramList,int startIndex,int maxSize) {
		// TODO Auto-generated method stub
		//Session 不能直接进行多记录查询，查询操作由Query对象实现
		Session se = getSession();
		Query q = se.createQuery(hql);
		for(int i=0;i<paramList.size();i++){ //for循环的作用：将obj填入到q的第i个位置
			Object obj = paramList.get(i);
			if(obj instanceof Date){
				q.setTimestamp(i, (Date)obj);
			}else{
				q.setParameter(i, obj);
			}
		}
		
		//判断分页，如果startIndex<0并且maxSize<0,就不做分页查询
		if(startIndex>=0 && maxSize>0 ){
			//从第几条记录开始查，最多查多少条记录
			q.setFirstResult(startIndex);
			q.setMaxResults(maxSize);
		}
		List result = q.list();
		//se.close();
		return result;
	}
	
	public List queryByHql(String hql, List paramList) {
		// TODO Auto-generated method stub
		//Session 不能直接进行多记录查询，查询操作由Query对象实现
		Session se = getSession();
		Query q = se.createQuery(hql);
		for(int i=0;i<paramList.size();i++){ //for循环的作用：将obj填入到q的第i个位置
			Object obj = paramList.get(i);
			if(obj instanceof Date){
				q.setTimestamp(i, (Date)obj);
			}else{
				q.setParameter(i, obj);
			}
		}
		List result = q.list();
		//se.close();
		return result;
	}

	public Object queryUniqueByHql(String hql, List paramList) {
		Session se = getSession();
		Query q = se.createQuery(hql);
		for(int i=0;i<paramList.size();i++){ //for循环的作用：将obj填入到q的第i个位置
			Object obj = paramList.get(i);
			if(obj instanceof Date){
				q.setTimestamp(i, (Date)obj);
			}else{
				q.setParameter(i, obj);
			}
		}
		Object result = q.uniqueResult();//得到唯一结果；
		//se.close();
		return result;
	}
	public List queryByHql(String hql) {
		// TODO Auto-generated method stub
		//Session 不能直接进行多记录查询，查询操作由Query对象实现
		Session se = getSession();
		Query q = se.createQuery(hql);
		List result = q.list();
		//se.close();
		return result;
	}
	
}
