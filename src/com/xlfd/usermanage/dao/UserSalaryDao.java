package com.xlfd.usermanage.dao;

import java.util.List;

import com.xlfd.common.model.Account;
import com.xlfd.common.model.UserSalary;
import com.xlfd.common.model.User;

public interface UserSalaryDao {
	
	public void saveUserSalary(UserSalary s);
	public UserSalary getUserSalary(int usId);
	public void updateUserSalary(UserSalary Update);
	
	//查找user
	public List<User> queryWorkUser(List paramList);
	//通过时间将account表中本月的结算记录查询出来
	public List<Account> queryAccountRecord(String queryStr, List paramList,int startIndex,int pagesize);
	public long queryAccountRecordCount(String queryStr,List paramList);
	//查找根据员工的id，查找该员工服务的总次数
	public long queryServeCount(String queryStr,List paramList);
	
	//实付金额之和
	public Double sumRealMoney(String queryStr, List paramList);
	
	//按条件查询  展示
	public List<UserSalary> queryUserSalary(String queryStr,List paramList,int startIndex,int maxSize);
	public long queryUserSalaryCount(String queryStr,List paramList);
	
	//详情页
	public List<UserSalary> querySalaryByuId(String queryStr,List paramList);
}
