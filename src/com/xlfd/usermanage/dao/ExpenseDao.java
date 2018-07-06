package com.xlfd.usermanage.dao;

import java.util.List;



import com.xlfd.common.model.Expense;

public interface ExpenseDao {
	public void saveExpense(Expense e);
	
	public void updateExpense(Expense expenseUpdate);
	
	public void deleteExpense(int id);
	
	public Expense getExpense(int Id);
	public List<Expense> queryExpense(String queryStr,List paramList,int startIndex, int maxSize );
	public long queryExpenseCount(String queryStr,List paramList);
}
