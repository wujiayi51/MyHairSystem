package com.xlfd.usermanage.dao;

import java.util.List;

import com.xlfd.common.model.User;
import com.xlfd.common.model.UserType;

public interface UserDao {
	
	public void saveUser(User u);
	public void deleteUser(int uId);//ɾ��ֻ��Ҫid����
	public User getUser(int uId);
	public void updateUser(User User4Update);
	//��������ѯ
	public List<User> queryUser(String queryStr,List paramList,int startIndex,int maxSize);
	public long queryUserCount(String queryStr,List paramList);
	
	//����usertype
	public List<UserType> queryAllType(List paramList);
	//�޸ĵȼ�
	public List<UserType> getUserType(String queryStr,List paramList);
	public List<User> updateUserGrade(String queryStr);
}
