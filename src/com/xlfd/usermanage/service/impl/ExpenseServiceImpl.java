package com.xlfd.usermanage.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xlfd.common.model.Expense;
import com.xlfd.usermanage.dao.AccountDao;
import com.xlfd.usermanage.dao.ExpenseDao;
import com.xlfd.usermanage.service.ExpenseService;



@Service(value="expenseService")
public class ExpenseServiceImpl implements ExpenseService {
	@Autowired//@Autowired注解的作用是自动注入标识
	@Qualifier(value="expenseDao")//@Qualifier注解的作用是配置自动注入的来源
	private ExpenseDao dao;
	
	public void saveExpense(Expense e) {
		
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String expenseTime = sdf.format(date);
//		//e.setExpenseTime(expenseTime);
//		e.setExpenseTime(new java.sql.Date(date.getTime()));
		//System.out.println(a.getcDiscount());
		
		dao.saveExpense(e);
		
	}

	public Expense getExpense(int Id) {
		return dao.getExpense(Id);
	}

	public List<Expense> queryExpense(String keyword, int pageNum, int pageSize) {
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			 queryStr = "expenseTime like ? ";
			 paramList.add("%"+keyword+"%");
		}
		int startIndex =(pageNum-1)*pageSize ;
		return dao.queryExpense(queryStr,paramList , startIndex, pageSize);
	}

	public long getExpensePageCount(String keyword, int pageSize) {
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			 queryStr = "expenseTime like ? ";
			 paramList.add("%"+keyword+"%");
		}
		long recordCount =dao.queryExpenseCount(queryStr, paramList);
		
		//return (recordCount%pageSize)
		double pageCount=Math.ceil( recordCount/(double)pageSize);
		return (long)pageCount;
	}

	public void updateExpense(Expense ExpenseUpdate) {
		dao.updateExpense(ExpenseUpdate);
		
	}

	public void deleteExpense(int id) {
		dao.deleteExpense(id);
		
	}

}
