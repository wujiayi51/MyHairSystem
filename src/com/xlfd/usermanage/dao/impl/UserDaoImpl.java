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
		return (User)this.getById(User.class, uId);	//因为查找到的是User所以Object可以强转成User
	}
	public void updateUser(User User4Update) {
		// TODO Auto-generated method stub
		update(User4Update);
	}
	public List<User> queryUser(String queryStr, List paramList,int startIndex,int maxSize) {
		// TODO Auto-generated method stub
				//new ArrayList()长度为0的数组，不能为null
		String hql = "from User";//User是映射类
		//select * from tUser;
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		return (List<User>)this.queryByHql(hql, paramList,startIndex,maxSize);
	}
	public long queryUserCount(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User";//User是映射类
		//select * from tUser;
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		//因为count得到的是长整型的对象，所以强制转换成长整型
		long UserCount = (Long)this.queryUniqueByHql(hql, paramList);
		return  UserCount;
	}
	public List<UserType> queryAllType(List paramList) {
		String hql = "from UserType";//User是映射类
		
		return (List<UserType>)this.queryByHql(hql, paramList,0,100);
	}
	//修改员工等级
	public List<UserType> getUserType(String queryStr, List paramList) {
		String hql = " from UserType";
		hql+= " where "+queryStr;      
		return (List<UserType>)this.queryByHql(hql, paramList);
	}
	public List<User> updateUserGrade(String queryStr) {
		// TODO Auto-generated method stub
		String hql = " from User";//User是映射类
		hql+= " where "+queryStr;
		return (List<User>)this.queryByHql(hql);
	}
	
}
