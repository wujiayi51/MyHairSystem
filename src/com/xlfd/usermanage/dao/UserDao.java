package com.xlfd.usermanage.dao;

import java.util.List;

import com.xlfd.common.model.User;
import com.xlfd.common.model.UserType;

public interface UserDao {
	
	public void saveUser(User u);
	public void deleteUser(int uId);//删除只需要id即可
	public User getUser(int uId);
	public void updateUser(User User4Update);
	//按条件查询
	public List<User> queryUser(String queryStr,List paramList,int startIndex,int maxSize);
	public long queryUserCount(String queryStr,List paramList);
	
	//查找usertype
	public List<UserType> queryAllType(List paramList);
	//修改等级
	public List<UserType> getUserType(String queryStr,List paramList);
	public List<User> updateUserGrade(String queryStr);
}
