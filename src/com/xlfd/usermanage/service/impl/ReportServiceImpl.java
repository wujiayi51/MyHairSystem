package com.xlfd.usermanage.service.impl;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Session;

import net.sf.json.JSONArray;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xlfd.common.model.Account;
import com.xlfd.common.model.ClientCharge;
import com.xlfd.common.model.Expense;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserSalary;
import com.xlfd.usermanage.dao.ReportDao;
import com.xlfd.usermanage.service.ReportService;



@Service(value="reportService")
public class ReportServiceImpl   implements ReportService {
	@Autowired//@Autowired注解的作用是自动注入标识
	@Qualifier(value="reportDao")//@Qualifier注解的作用是配置自动注入的来源
	
	private ReportDao dao;
	
	public List<Account> queryAccount(String keyWord){
		String queryStr="";
		List paramList=new ArrayList();
		System.out.println(keyWord);
		if(StringUtils.hasText(keyWord)){
			queryStr = " YEAR(payTime) = ? GROUP BY MONTH(payTime) ";
			paramList.add(Integer.valueOf(keyWord));
		}
		return dao.queryAccount(queryStr,paramList);
		
	}

	public List<Expense> queryExpense(String keyword) {
		// TODO Auto-generated method stub
		String queryStr="";
		List paramList=new ArrayList();
		System.out.println(keyword);
		if(StringUtils.hasText(keyword)){
			queryStr = " YEAR(expenseTime) = ? GROUP BY MONTH(expenseTime) ";
			paramList.add(Integer.valueOf(keyword));
		}
		return dao.queryExpense(queryStr,paramList);
	}

	public List<Account> queryAccount(String keyword, String keyword2) {
		// TODO Auto-generated method stub
		String queryStr="";
		List paramList=new ArrayList();
		System.out.println(keyword);
		if(StringUtils.hasText(keyword)){
			queryStr = " date_format(payTime,'%Y%m') >= ? and date_format(payTime,'%Y%m') <= ? GROUP BY date_format(payTime,'%Y%m')";
			paramList.add(keyword);
			paramList.add(keyword2);
		}
		return dao.queryAccountM(queryStr,paramList);
	}

	public List<Expense> queryExpense(String keyword, String keyword2) {
		// TODO Auto-generated method stub
		String queryStr="";
		List paramList=new ArrayList();
		System.out.println(keyword);
		if(StringUtils.hasText(keyword)){
			queryStr = "date_format(expenseTime,'%Y%m') >= ? and date_format(expenseTime,'%Y%m') <= ?  GROUP BY date_format(expenseTime,'%Y%m') ";
			paramList.add(keyword);
			paramList.add(keyword2);
		}
		return dao.queryExpenseM(queryStr,paramList);
	}
	//根据所选择的年份和某一员工，按月统计该员工本年业绩变化
	public List<Account> queryAccountUser(String keyword, String keyword2) {
		// TODO Auto-generated method stub
		String queryStr="";
		List paramList=new ArrayList();
		System.out.println(keyword);
		if(StringUtils.hasText(keyword)){
			queryStr = " YEAR(payTime)= ? and belongUser.uId = ? GROUP BY date_format(payTime,'%Y%m')";
			paramList.add(Integer.valueOf(keyword));
			paramList.add(Integer.valueOf(keyword2));
		}
		return dao.queryAccountUser(queryStr,paramList);
	}
	//根据所选择的年份和某一员工，按月统计该员工本年工资变化
	public List<UserSalary> querySalary(String keyword, String keyword2) {
		// TODO Auto-generated method stub
		String queryStr="";
		List paramList=new ArrayList();
		System.out.println(keyword);
		if(StringUtils.hasText(keyword)){
			queryStr = "YEAR(salaryDate)= ? and USbelongUser.uId = ? GROUP BY date_format(salaryDate,'%Y%m') ";
			paramList.add(Integer.valueOf(keyword));
			paramList.add(Integer.valueOf(keyword2));
		}
		return dao.querySalary(queryStr,paramList);
	}

	public List<User> queryUser(String keyword) {
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList = new ArrayList();
		//判断keyword是否有内容：null,"",空格------>为没有内容；
		if(StringUtils.hasText(keyword)){
			queryStr = "uName like ?";
			paramList.add("%"+keyword+"%");
		}
		
		return dao.queryUser(queryStr, paramList);
	}
	//根据所选择的起始月份和结束月份以及某一员工，按月统计该员工创造的业绩和工资变化
	public List<Account> queryAccountUser(String keyword, String keyword2,
			String keyword3) {
		// TODO Auto-generated method stub
		String queryStr="";
		List paramList=new ArrayList();
		System.out.println(keyword);
		if(StringUtils.hasText(keyword)){
			queryStr = " date_format(payTime,'%Y%m') >= ? and date_format(payTime,'%Y%m') <= ? and belongUser.uId = ? GROUP BY date_format(payTime,'%Y%m')  ";
			paramList.add(keyword);
			paramList.add(keyword2);
			paramList.add(Integer.valueOf(keyword3));
		}
		return dao.queryAccountUserMU(queryStr,paramList);
	}

	public List<UserSalary> querySalary(String keyword, String keyword2,
			String keyword3) {
		// TODO Auto-generated method stub
		String queryStr="";
		List paramList=new ArrayList();
		System.out.println(keyword);
		if(StringUtils.hasText(keyword)){
			queryStr = " date_format(salaryDate,'%Y%m') >= ? and date_format(salaryDate,'%Y%m') <= ? and USbelongUser.uId = ? GROUP BY date_format(salaryDate,'%Y%m')   ";
			paramList.add(keyword);
			paramList.add(keyword2);
			paramList.add(Integer.valueOf(keyword3));
		}
		return dao.querySalaryMU(queryStr,paramList);
	}
	
	
	
}
