package com.xlfd.usermanage.service;

import java.util.List;


import com.xlfd.common.model.Account;
import com.xlfd.common.model.ClientCharge;
import com.xlfd.common.model.Expense;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserSalary;


public interface ReportService {
	//根据所选择的年份，按月统计本年的总收入变化
	public List<Account> queryAccount(String key);
	//根据所选择的年份，按月统计本年的总支出变化
	public List<Expense> queryExpense(String keyword);
	//根据所选择的起始月份和结束月份，按月统计总收入变化
	public List<Account> queryAccount(String keyword, String keyword2);
	//根据所选择的起始月份和结束月份，按月统计总支出变化
	public List<Expense> queryExpense(String keyword, String keyword2);
	//	可以根据所选择的年份和某一员工，按月统计该员工本年创造的业绩和工资变化，
	public List<Account> queryAccountUser(String keyword, String keyword2);
	//	可以根据所选择的年份和某一员工，按月统计该员工本年工资变化，
	public List<UserSalary> querySalary(String keyword, String keyword2);
	public List<User> queryUser(String keyword);
	//根据所选择的起始月份和结束月份以及某一员工，按月统计该员工创造的业绩和工资变化，以柱状图显示
	public List<Account> queryAccountUser(String keyword, String keyword2,
			String keyword3);
	public List<UserSalary> querySalary(String keyword, String keyword2,
			String keyword3);
}
