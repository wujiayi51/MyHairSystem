package com.xlfd.usermanage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.xlfd.common.dao.impl.BaseDaoImpl;
import com.xlfd.common.model.Account;
import com.xlfd.common.model.UserSalary;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserType;
import com.xlfd.usermanage.dao.UserSalaryDao;

@Repository(value="usersalaryDao")
public class UserSalaryDaoImpl extends BaseDaoImpl implements UserSalaryDao{

	public void saveUserSalary(UserSalary s) {
		// TODO Auto-generated method stub
		save(s);
	}

	public UserSalary getUserSalary(int usId) {
		// TODO Auto-generated method stub
		return (UserSalary)this.getById(UserSalary.class, usId);
	}

	public void updateUserSalary(UserSalary Update) {
		// TODO Auto-generated method stub
		update(Update);
	}

	public List<User> queryWorkUser(List paramList) {

		String hql = " from User where state!=-1";
		return (List<User>)this.queryByHql(hql, paramList);
	}

	//通过时间将account表中本月的结算记录查询出来
	public List<Account> queryAccountRecord(String queryStr, List paramList,int startIndex,int pagesize){

		String hql = " from Account ";//User是映射类
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		System.out.println(hql);
		return (List<Account>)this.queryByHql(hql, paramList,startIndex,pagesize);
	}
	public long queryAccountRecordCount(String queryStr, List paramList) {
		String hql = "select count(*) from Account ";//User是映射类
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		long recordCount=(Long)this.queryUniqueByHql(hql, paramList);
		return recordCount;
	}
	//查找根据员工的id，查找该员工服务的总次数
	public long queryServeCount(String queryStr, List paramList) {
		String hql = "select count(*) from Account ";
		hql+=" where " + queryStr;
		long ServeCount = (Long) this.queryUniqueByHql(hql, paramList);
		return ServeCount;
	}
	
	public Double sumRealMoney(String queryStr, List paramList) {
		// ifnull(SUM(realMoney),0)如果没有记录就为0，因为 null+number = null;
		//String hql = " SELECT  IFNULL(SUM(realMoney),0) from Account ";
		/*IFNULL(SUM(realMoney),0)*/
		String hql = " SELECT  SUM(realMoney) from Account ";
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		System.out.println(hql);
		if(this.queryUniqueByHql(hql, paramList)==null){
			return 0.0;
		}
		double sumMoney =  (Double) this.queryUniqueByHql(hql, paramList);
		System.out.println(sumMoney);
		return sumMoney;
	}

	//按条件查询  展示 输入年份 月份  员工工号
	public List<UserSalary> queryUserSalary(String queryStr, List paramList,
			int startIndex, int maxSize) {

		String hql = " from UserSalary";
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		System.out.println(hql);
		return (List<UserSalary>)this.queryByHql(hql, paramList,startIndex,maxSize);
	}

	public long queryUserSalaryCount(String queryStr, List paramList) {

		String hql = "select count(*) from UserSalary ";
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		System.out.println(hql);
		long UserCount = (Long)this.queryUniqueByHql(hql, paramList);
		return  UserCount;
	}

	public List<UserSalary> querySalaryByuId(String queryStr, List paramList) {
		String hql = " from UserSalary ";
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		return (List<UserSalary>)this.queryByHql(hql, paramList);
	}

	



}
