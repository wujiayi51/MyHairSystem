package com.xlfd.usermanage.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.xlfd.common.dao.impl.BaseDaoImpl;
import com.xlfd.common.model.Account;
import com.xlfd.common.model.ClientCharge;
import com.xlfd.common.model.Expense;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserSalary;
import com.xlfd.usermanage.dao.ReportDao;

@Repository(value="reportDao")
public class ReportDaoImpl extends BaseDaoImpl implements ReportDao {
	
	//������ѡ�����ݣ�����ͳ�Ʊ����������仯
	public List<Account> queryAccount(String queryStr,List paramList) {
		// TODO Auto-generated method stub
		/* MONTH(charge_date),SUM(charge_money)*/
		
		String hql=" select  Year(payTime),MONTH(payTime),SUM(realMoney) from Account ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+ queryStr ;
		}else{
			hql+=" where Year(pay_time)=Year(CURDATE()) GROUP BY MONTH(payTime)";
		}
		System.out.println(hql);
		return (List<Account>)this.queryByHql(hql,paramList);
	}
	//������ѡ�����ݣ�����ͳ�Ʊ������֧���仯
	public List<Expense> queryExpense(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql=" select  Year(expenseTime),MONTH(expenseTime),SUM(itemExpense) from Expense ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+ queryStr ;
		}else{
			hql+=" where Year(expenseTime) = Year(CURDATE()) GROUP BY MONTH(expenseTime)";
		}
		System.out.println(hql);
		return (List<Expense>)this.queryByHql(hql,paramList);
	}
	//������ѡ�����ʼ�·ݺͽ����·ݣ�����ͳ��������仯
	public List<Account> queryAccountM(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql=" select   date_format(payTime,'%Y%m'),sum(realMoney) from Account ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+ queryStr ;
		}else{
			hql+="  where  date_format(payTime,'%Y%m') >= 201801 and date_format(payTime,'%Y%m') <= date_format(NOW(),'%Y%m') GROUP BY date_format(payTime,'%Y%m')";
		}
		System.out.println(hql);
		return (List<Account>)this.queryByHql(hql,paramList);
	}
	//������ѡ�����ʼ�·ݺͽ����·ݣ�����ͳ����֧���仯
	public List<Expense> queryExpenseM(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql=" select  date_format(expenseTime,'%Y%m'),sum(itemExpense) from Expense ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+ queryStr ;
		}else{
			hql+=" where date_format(expenseTime,'%Y%m') >= 201801  and date_format(expenseTime,'%Y%m') <= date_format(NOW(),'%Y%m')  GROUP BY date_format(expenseTime,'%Y%m')";
		}
		System.out.println(hql);
		return (List<Expense>)this.queryByHql(hql,paramList);
	}
	//������ѡ�����ݺ�ĳһԱ��������ͳ�Ƹ�Ա�����깤�ʱ仯
	public List<UserSalary> querySalary(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql=" select  date_format(salaryDate,'%Y%m'),sum(finalSalary) from UserSalary  ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+ queryStr ;
		}else{
			hql+=" where YEAR(salaryDate)=2018 and USbelongUser.uId = 11 GROUP BY date_format(salaryDate,'%Y%m')";
		}
		System.out.println(hql);
		return (List<UserSalary>)this.queryByHql(hql,paramList);
	}
	//������ѡ�����ݺ�ĳһԱ��������ͳ�Ƹ�Ա�����괴���ҵ��
	public List<Account> queryAccountUser(String queryStr, List paramList) {
		String hql=" select  date_format(payTime,'%Y%m'),sum(realMoney) from Account   ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+ queryStr ;
		}else{
			hql+="  where  YEAR(payTime)= Year(CURDATE()) GROUP BY date_format(payTime,'%Y%m')";
		}
		System.out.println(hql);
		return (List<Account>)this.queryByHql(hql,paramList);
	}
	public List<User> queryUser(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql = "from User";//User��ӳ����
		//select * from tUser;
		if(StringUtils.hasText(queryStr)){
			hql+= " where "+queryStr;
		}
		return (List<User>)this.queryByHql(hql, paramList);
	}
	public List<Account> queryAccountUserMU(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql=" select  date_format(payTime,'%Y%m'),sum(realMoney) from Account   ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+ queryStr ;
		}else{
			hql+="   where   date_format(payTime,'%Y%m') >= 201801 and date_format(payTime,'%Y%m') <= date_format(NOW(),'%Y%m')  GROUP BY date_format(payTime,'%Y%m') ";
		}
		System.out.println(hql);
		return (List<Account>)this.queryByHql(hql,paramList);
	}
	public List<UserSalary> querySalaryMU(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql=" select  date_format(salaryDate,'%Y%m'),sum(finalSalary) from UserSalary  ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+ queryStr ;
		}else{
			hql+=" where   date_format(salaryDate,'%Y%m') >= 201801 and date_format(salaryDate,'%Y%m') <= date_format(NOW(),'%Y%m') and USbelongUser.uId = 11 GROUP BY date_format(salaryDate,'%Y%m')";
		}
		System.out.println(hql);
		return (List<UserSalary>)this.queryByHql(hql,paramList);
	}
	
	
}
