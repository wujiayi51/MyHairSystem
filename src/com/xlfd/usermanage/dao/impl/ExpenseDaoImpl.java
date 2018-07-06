package com.xlfd.usermanage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.xlfd.common.dao.impl.BaseDaoImpl;
import com.xlfd.common.model.Expense;
import com.xlfd.usermanage.dao.ExpenseDao;


@Repository(value="expenseDao")
public class ExpenseDaoImpl extends BaseDaoImpl implements ExpenseDao{

	public void saveExpense(Expense e) {
		save(e);
		
	}

	public Expense getExpense(int Id) {
		// TODO Auto-generated method stub
		return (Expense)this.getById(Expense.class, Id);
	}

	public List<Expense> queryExpense(String queryStr, List paramList,int startIndex, int maxSize) {
		// TODO Auto-generated method stub
		String hql="from Expense ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+queryStr;
		}
		
		return (List<Expense>)this.queryByHql(hql, paramList, startIndex,  maxSize);

	}

	public long queryExpenseCount(String queryStr, List paramList) {
		// TODO Auto-generated method stub
				String hql="select count(*) from Expense ";
				if(StringUtils.hasText(queryStr)){
					hql+="where "+queryStr;
				}
			
				long ExpenseCount=(Long)this.queryUniqueByHql(hql, paramList);
				return ExpenseCount;
	}

	public void updateExpense(Expense expenseUpdate) {
		update(expenseUpdate);
		
	}

	public void deleteExpense(int id) {
		deleteById(Expense.class, id);
		
	}

}
