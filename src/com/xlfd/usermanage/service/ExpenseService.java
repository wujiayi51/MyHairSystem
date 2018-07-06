package com.xlfd.usermanage.service;

import java.util.List;


import com.xlfd.common.model.Expense;

public interface ExpenseService {
	public void saveExpense(Expense e);
	
	public Expense getExpense(int Id);
	
	public List<Expense> queryExpense(String keyword ,int pageNum,int pageSize);
	
	public long getExpensePageCount(String keyword,int pageSize);
	
	public void updateExpense(Expense ExpenseUpdate); 
	
	public void deleteExpense(int id);

}
