package com.xlfd.usermanage.dao;



import java.util.List;

import com.xlfd.common.model.Account;
import com.xlfd.common.model.ClientCharge;
import com.xlfd.common.model.Expense;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserSalary;




public interface ReportDao {
	

	//������ѡ�����ݣ�����ͳ�Ʊ����������仯
	public List<Account> queryAccount(String keyWord,List paramList);
	//������ѡ�����ݣ�����ͳ�Ʊ������֧���仯
	public List<Expense> queryExpense(String queryStr, List paramList);
	//������ѡ�����ʼ�·ݺͽ����·ݣ�����ͳ��������仯
	public List<Expense> queryExpenseM(String queryStr, List paramList);
	//������ѡ�����ʼ�·ݺͽ����·ݣ�����ͳ����֧���仯
	public List<Account> queryAccountM(String queryStr, List paramList);
	//������ѡ�����ݺ�ĳһԱ��������ͳ�Ƹ�Ա�����깤�ʱ仯
	public List<UserSalary> querySalary(String queryStr, List paramList);
	//������ѡ�����ݺ�ĳһԱ��������ͳ�Ƹ�Ա�����괴���ҵ��
	public List<Account> queryAccountUser(String queryStr, List paramList);
	//����Ա��
	public List<User> queryUser(String queryStr, List paramList);
	//������ѡ�����ʼ�·ݺͽ����·��Լ�ĳһԱ��������ͳ�Ƹ�Ա�������ҵ���͹��ʱ仯������״ͼ��ʾ
	public List<Account> queryAccountUserMU(String queryStr, List paramList);
	public List<UserSalary> querySalaryMU(String queryStr, List paramList);

	
}
