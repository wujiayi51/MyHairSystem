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

	@Autowired //�Զ�ע���ʶ
	@Qualifier(value="sessionFactory") //�����Զ�ע�����Դ
	private SessionFactory seFa;//seFa������������Session��������
	
	private Session getSession(){//getSession ��Hibernate�������ݵĹ���
		//ʹ������ʽ����󣬽��ر�Session�Ĵ���ע�ͣ�û��ȷ������ʲôʱ��ʼ����
		//return seFa.openSession();
		return seFa.getCurrentSession();
	}

	public void save(Object obj) {
		// TODO Auto-generated method stub
		Session se = getSession();
		//ʹ������ʽ����󣬽����ʽ������ע��
		//Transaction tr = se.beginTransaction();//���ʽ����
		se.save(obj);
		//tr.commit(); //�ύ����  rollback():�ع�����
		//ʹ������ʽ����󣬽��ر�Session�Ĵ���ע��
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
	 * hql Ҫִ�е�HQL��䣻 hql���ӽ�sql����ѯ�﷨
	 * se.createQuery(hql);�Ա�
	 * conn.prepareStetment(sql);
	 */
	public List queryByHql(String hql, List paramList,int startIndex,int maxSize) {
		// TODO Auto-generated method stub
		//Session ����ֱ�ӽ��ж��¼��ѯ����ѯ������Query����ʵ��
		Session se = getSession();
		Query q = se.createQuery(hql);
		for(int i=0;i<paramList.size();i++){ //forѭ�������ã���obj���뵽q�ĵ�i��λ��
			Object obj = paramList.get(i);
			if(obj instanceof Date){
				q.setTimestamp(i, (Date)obj);
			}else{
				q.setParameter(i, obj);
			}
		}
		
		//�жϷ�ҳ�����startIndex<0����maxSize<0,�Ͳ�����ҳ��ѯ
		if(startIndex>=0 && maxSize>0 ){
			//�ӵڼ�����¼��ʼ�飬�����������¼
			q.setFirstResult(startIndex);
			q.setMaxResults(maxSize);
		}
		List result = q.list();
		//se.close();
		return result;
	}
	
	public List queryByHql(String hql, List paramList) {
		// TODO Auto-generated method stub
		//Session ����ֱ�ӽ��ж��¼��ѯ����ѯ������Query����ʵ��
		Session se = getSession();
		Query q = se.createQuery(hql);
		for(int i=0;i<paramList.size();i++){ //forѭ�������ã���obj���뵽q�ĵ�i��λ��
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
		for(int i=0;i<paramList.size();i++){ //forѭ�������ã���obj���뵽q�ĵ�i��λ��
			Object obj = paramList.get(i);
			if(obj instanceof Date){
				q.setTimestamp(i, (Date)obj);
			}else{
				q.setParameter(i, obj);
			}
		}
		Object result = q.uniqueResult();//�õ�Ψһ�����
		//se.close();
		return result;
	}
	public List queryByHql(String hql) {
		// TODO Auto-generated method stub
		//Session ����ֱ�ӽ��ж��¼��ѯ����ѯ������Query����ʵ��
		Session se = getSession();
		Query q = se.createQuery(hql);
		List result = q.list();
		//se.close();
		return result;
	}
	
}
