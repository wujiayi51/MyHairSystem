package com.xlfd.usermanage.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.xlfd.common.model.UserType;
import com.xlfd.usermanage.dao.UserTypeDao;
import com.xlfd.usermanage.service.UserTypeService;

@Service(value="userTypeService")
public class UserTypeServiceImpl implements UserTypeService {
	@Autowired//@Autowired注解的作用是自动注入标识
	@Qualifier(value="userTypeDao")//@Qualifier注解的作用是配置自动注入的来源
	private UserTypeDao dao;
	public void saveUserType(UserType u) {
			dao.saveUserType(u);
	}
	public void deleteUserType(int utId) {
		// TODO Auto-generated method stub
		dao.deleteUserType(utId);
	}
	public UserType getUserType(int utId) {
		// TODO Auto-generated method stub
		return dao.getUserType(utId);
	}
	public void updateUserType(UserType UserType4Update) {
		// TODO Auto-generated method stub
		dao.updateUserType(UserType4Update);
	}
	public List<UserType> queryUserType(String keyword,int pagenum,int pagesize) {
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList = new ArrayList();
		//判断keyword是否有内容：null,"",空格------>为没有内容；
		if(StringUtils.hasText(keyword)){
			queryStr = "utName like ?";
			paramList.add("%"+keyword+"%");
		}
		int startIndex = (pagenum-1)*pagesize;//根据自增id的来看
		return dao.queryUserType(queryStr, paramList,startIndex,pagesize);
	}
	public long getUserTypePageCount(String keyword,int pagesize) {
		String queryStr = "";
		List paramList = new ArrayList();
		//判断keyword是否有内容：null,"",空格------>为没有内容；
		if(StringUtils.hasText(keyword)){
			queryStr = "utName like ?";
			paramList.add("%"+keyword+"%");
		}
		
		long recordCount = dao.queryUserTypeCount(queryStr, paramList);
		long pagecount = (long)Math.ceil(recordCount/(double)pagesize);
		return (long)pagecount;
	}


}
