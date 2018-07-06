package com.xlfd.usermanage.service;

import java.util.List;

import com.xlfd.common.model.Account;
import com.xlfd.common.model.UserSalary;
import com.xlfd.common.model.User;

public interface UserSalaryService {
	public UserSalary getUserSalary(int usid);
	//计算工资
	public void saveAutoCalcUserSalary();
	
	//查找所有非离职的员工
	public List<User> queryWorkUser();
	
	//通过时间将account表中本月的结算记录查询出来
	//public List<Account> queryAccountRecord(int year,int month,int uid);
	//查找根据员工的id，查找该员工服务的总次数
	public int queryServeCount(int year,int month,int uid);
	
	//实付金额之和
	public double sumRealMoney(int year,int month,int uid);
	
	//展示
	public List<UserSalary> queryUserSalary(String ys,int pagenum,int pagesize);
	
	
	public long queryUserSalaryCount(String ys,int pagesize);
	
	//详情页
	public List<UserSalary> querySalaryByuId(String salarydate,int uid);
	
	//通过时间将account表中本月的结算记录查询出来
	public List<Account> queryAccountRecord(String salarydate,int uid,int pagenum,int pagesize);
	public long getAccountRecordCount(String salarydate, int uid, int pagesize);
	//修改工资
	public void updateSalary(UserSalary updateUSalary);
}
