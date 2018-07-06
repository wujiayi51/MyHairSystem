package com.xlfd.usermanage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.xlfd.common.dao.impl.BaseDaoImpl;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserType;
import com.xlfd.usermanage.dao.UserTypeDao;
@Repository(value="userTypeDao")
public class UserTypeDaoImpl extends BaseDaoImpl implements UserTypeDao{
	public void saveUserType(UserType u) {
		save(u);
	}
	public void deleteUserType(int utId) {
		// TODO Auto-generated method stub
		deleteById(UserType.class,utId);
	}

	public UserType getUserType(int utId) {
		// TODO Auto-generated method stub
		return (UserType)this.getById(UserType.class, utId);	//��Ϊ���ҵ�����UserType����Object����ǿת��UserType
	}
	public void updateUserType(UserType UserType4Update) {
		// TODO Auto-generated method stub
		update(UserType4Update);
	}
	public List<UserType> queryUserType(String queryStr, List paramList,int startIndex,int maxSize) {
		// TODO Auto-generated method stub
				//new ArrayList()����Ϊ0�����飬����Ϊnull
		String hql = "from UserType";//UserType��ӳ����
		//select * from tUserType;
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		return (List<UserType>)this.queryByHql(hql, paramList,startIndex,maxSize);
	}
	public long queryUserTypeCount(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from UserType";//UserType��ӳ����
		//select * from tUserType;
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		//��Ϊcount�õ����ǳ����͵Ķ�������ǿ��ת���ɳ�����
		long UserTypeCount = (Long)this.queryUniqueByHql(hql, paramList);
		return  UserTypeCount;
	}
}
