package com.xlfd.usermanage.dao;

import java.util.List;

import com.xlfd.common.model.UserType;


public interface UserTypeDao {
	
	public void saveUserType(UserType u);
	public void deleteUserType(int utId);//ɾ��ֻ��Ҫid����
	public UserType getUserType(int utId);
	public void updateUserType(UserType UserType4Update);
	//��������ѯ
	public List<UserType> queryUserType(String queryStr,List paramList,int startIndex,int maxSize);
	public long queryUserTypeCount(String queryStr,List paramList);
}
