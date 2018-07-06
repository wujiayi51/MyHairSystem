package com.xlfd.usermanage.service;

import java.util.List;

import com.xlfd.common.model.User;
import com.xlfd.common.model.UserType;

public interface UserService {
	public void saveUser(User u,String rootPath);
	public void deleteUser(int uId);
	public User getUser(int uId);
	public void updateUser(User User4Update,User u);
	public void updateUser(User u,int state,String leaveTimeStr);
	public List<User> queryUser(String keyword,int startIndex,int maxSize);
	//������ҳ��
	public long getUserPageCount(String keyword,int pagesize);
	
	//��������
	public List<UserType> queryAllType();

	//�޸ĵȼ�
	public void updateUserGrade();
}
