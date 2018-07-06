package com.xlfd.usermanage.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import com.xlfd.common.dao.impl.BaseDaoImpl;

import com.xlfd.common.model.User;
import com.xlfd.common.model.UserType;
import com.xlfd.usermanage.dao.UserDao;

@Repository(value="userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	public void saveUser(User u) {
		save(u);
	}
	public void deleteUser(int uId) {
		// TODO Auto-generated method stub
		deleteById(User.class,uId);
	}

	public User getUser(int uId) {
		// TODO Auto-generated method stub
		return (User)this.getById(User.class, uId);	//��Ϊ���ҵ�����User����Object����ǿת��User
	}
	public void updateUser(User User4Update) {
		// TODO Auto-generated method stub
		update(User4Update);
	}
	public List<User> queryUser(String queryStr, List paramList,int startIndex,int maxSize) {
		// TODO Auto-generated method stub
				//new ArrayList()����Ϊ0�����飬����Ϊnull
		String hql = "from User";//User��ӳ����
		//select * from tUser;
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		return (List<User>)this.queryByHql(hql, paramList,startIndex,maxSize);
	}
	public long queryUserCount(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User";//User��ӳ����
		//select * from tUser;
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		//��Ϊcount�õ����ǳ����͵Ķ�������ǿ��ת���ɳ�����
		long UserCount = (Long)this.queryUniqueByHql(hql, paramList);
		return  UserCount;
	}
	public List<UserType> queryAllType(List paramList) {
		String hql = "from UserType";//User��ӳ����
		
		return (List<UserType>)this.queryByHql(hql, paramList,0,100);
	}
	//�޸�Ա���ȼ�
	public List<UserType> getUserType(String queryStr, List paramList) {
		String hql = " from UserType";
		hql+= " where "+queryStr;      
		return (List<UserType>)this.queryByHql(hql, paramList);
	}
	public List<User> updateUserGrade(String queryStr) {
		// TODO Auto-generated method stub
		String hql = " from User";//User��ӳ����
		hql+= " where "+queryStr;
		return (List<User>)this.queryByHql(hql);
	}
	
}
