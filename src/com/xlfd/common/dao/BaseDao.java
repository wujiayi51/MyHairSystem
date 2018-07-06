package com.xlfd.common.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao {
	public void save(Object obj);//添加数据
	
	public void saveOrUpdate(Object obj);
	
	/**
	 * update  修改数据
	 * @param obj 即将被修改的数据（该条数据修改后的最新值）
	 */
	public void update(Object obj);
	
	public void delete(Object obj);
	/**
	 * deleteById 根据id删除数据
	 * @param clazz要删除数据的类名
	 * @param id 要删除数据的主键值
	 */
	public void deleteById(Class clazz,Serializable id);//根据主键id删除数据
	
	/**
	 * getById 根据主键查数据
	 * @param clazz 要查找数据的类名
	 * @param id 要查找数据的主键值
	 * @return
	 */
	public Object getById(Class clazz,Serializable id); //根据主键查数据
	
	/**
	 * 根据HQL语句查数据
	 * @param hql 要执行的Hql语句
	 * @param paramList  Hql语句的“？”中要填的参数集合
	 * @param startIndex 要查找数据的起始位置
	 * @param maxSize 要查找数据的最大记录数
	 * @return
	 */
	public List queryByHql(String hql,List paramList,int startIndex,int maxSize);//根据HQL语句查数据
	/**
	 * 查询得到一条结果，一般用于统计（聚合）查询
	 * @param hql
	 * @param paramList
	 * @return
	 */
	public List queryByHql(String hql, List paramList);
	
	//重写方法 只需要一个参数
	//重写方法 
	public List queryByHql(String hql);
	
	
	/**
	 * 查询得到一条结果，一般用于统计（聚合）查询
	 * @param hql
	 * @param paramList
	 * @return
	 */
	public Object queryUniqueByHql(String hql,List paramList);
}
	