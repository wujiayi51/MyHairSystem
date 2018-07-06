package com.xlfd.usermanage.dao;



import java.util.List;

import com.xlfd.common.model.Account;
import com.xlfd.common.model.ClientCharge;
import com.xlfd.common.model.Expense;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserSalary;




public interface ReportDao {
	

	//根据所选择的年份，按月统计本年的总收入变化
	public List<Account> queryAccount(String keyWord,List paramList);
	//根据所选择的年份，按月统计本年的总支出变化
	public List<Expense> queryExpense(String queryStr, List paramList);
	//根据所选择的起始月份和结束月份，按月统计总收入变化
	public List<Expense> queryExpenseM(String queryStr, List paramList);
	//根据所选择的起始月份和结束月份，按月统计总支出变化
	public List<Account> queryAccountM(String queryStr, List paramList);
	//根据所选择的年份和某一员工，按月统计该员工本年工资变化
	public List<UserSalary> querySalary(String queryStr, List paramList);
	//根据所选择的年份和某一员工，按月统计该员工本年创造的业绩
	public List<Account> queryAccountUser(String queryStr, List paramList);
	//查找员工
	public List<User> queryUser(String queryStr, List paramList);
	//根据所选择的起始月份和结束月份以及某一员工，按月统计该员工创造的业绩和工资变化，以柱状图显示
	public List<Account> queryAccountUserMU(String queryStr, List paramList);
	public List<UserSalary> querySalaryMU(String queryStr, List paramList);

	
}
