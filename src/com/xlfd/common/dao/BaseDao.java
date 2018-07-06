package com.xlfd.common.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao {
	public void save(Object obj);//�������
	
	public void saveOrUpdate(Object obj);
	
	/**
	 * update  �޸�����
	 * @param obj �������޸ĵ����ݣ����������޸ĺ������ֵ��
	 */
	public void update(Object obj);
	
	public void delete(Object obj);
	/**
	 * deleteById ����idɾ������
	 * @param clazzҪɾ�����ݵ�����
	 * @param id Ҫɾ�����ݵ�����ֵ
	 */
	public void deleteById(Class clazz,Serializable id);//��������idɾ������
	
	/**
	 * getById ��������������
	 * @param clazz Ҫ�������ݵ�����
	 * @param id Ҫ�������ݵ�����ֵ
	 * @return
	 */
	public Object getById(Class clazz,Serializable id); //��������������
	
	/**
	 * ����HQL��������
	 * @param hql Ҫִ�е�Hql���
	 * @param paramList  Hql���ġ�������Ҫ��Ĳ�������
	 * @param startIndex Ҫ�������ݵ���ʼλ��
	 * @param maxSize Ҫ�������ݵ�����¼��
	 * @return
	 */
	public List queryByHql(String hql,List paramList,int startIndex,int maxSize);//����HQL��������
	/**
	 * ��ѯ�õ�һ�������һ������ͳ�ƣ��ۺϣ���ѯ
	 * @param hql
	 * @param paramList
	 * @return
	 */
	public List queryByHql(String hql, List paramList);
	
	//��д���� ֻ��Ҫһ������
	//��д���� 
	public List queryByHql(String hql);
	
	
	/**
	 * ��ѯ�õ�һ�������һ������ͳ�ƣ��ۺϣ���ѯ
	 * @param hql
	 * @param paramList
	 * @return
	 */
	public Object queryUniqueByHql(String hql,List paramList);
}
	