package com.xlfd.usermanage.service;

import java.util.List;


import com.xlfd.common.model.Account;
import com.xlfd.common.model.ClientCharge;
import com.xlfd.common.model.Expense;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserSalary;


public interface ReportService {
	//������ѡ�����ݣ�����ͳ�Ʊ����������仯
	public List<Account> queryAccount(String key);
	//������ѡ�����ݣ�����ͳ�Ʊ������֧���仯
	public List<Expense> queryExpense(String keyword);
	//������ѡ�����ʼ�·ݺͽ����·ݣ�����ͳ��������仯
	public List<Account> queryAccount(String keyword, String keyword2);
	//������ѡ�����ʼ�·ݺͽ����·ݣ�����ͳ����֧���仯
	public List<Expense> queryExpense(String keyword, String keyword2);
	//	���Ը�����ѡ�����ݺ�ĳһԱ��������ͳ�Ƹ�Ա�����괴���ҵ���͹��ʱ仯��
	public List<Account> queryAccountUser(String keyword, String keyword2);
	//	���Ը�����ѡ�����ݺ�ĳһԱ��������ͳ�Ƹ�Ա�����깤�ʱ仯��
	public List<UserSalary> querySalary(String keyword, String keyword2);
	public List<User> queryUser(String keyword);
	//������ѡ�����ʼ�·ݺͽ����·��Լ�ĳһԱ��������ͳ�Ƹ�Ա�������ҵ���͹��ʱ仯������״ͼ��ʾ
	public List<Account> queryAccountUser(String keyword, String keyword2,
			String keyword3);
	public List<UserSalary> querySalary(String keyword, String keyword2,
			String keyword3);
}
