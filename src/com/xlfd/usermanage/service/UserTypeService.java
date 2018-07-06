package com.xlfd.usermanage.service;

import java.util.List;

import com.xlfd.common.model.UserType;


public interface UserTypeService {
	public void saveUserType(UserType u);
	public void deleteUserType(int utId);
	public UserType getUserType(int utId);
	public void updateUserType(UserType UserType4Update);
	public List<UserType> queryUserType(String keyword,int startIndex,int maxSize);
	//·µ»Ø×ÜÒ³Êý
	public long getUserTypePageCount(String keyword,int pagesize);
	
	
}
