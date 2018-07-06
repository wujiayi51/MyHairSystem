package com.xlfd.usermanage.service;

import java.util.List;

import com.xlfd.common.model.Account;
import com.xlfd.common.model.UserSalary;
import com.xlfd.common.model.User;

public interface UserSalaryService {
	public UserSalary getUserSalary(int usid);
	//���㹤��
	public void saveAutoCalcUserSalary();
	
	//�������з���ְ��Ա��
	public List<User> queryWorkUser();
	
	//ͨ��ʱ�佫account���б��µĽ����¼��ѯ����
	//public List<Account> queryAccountRecord(int year,int month,int uid);
	//���Ҹ���Ա����id�����Ҹ�Ա��������ܴ���
	public int queryServeCount(int year,int month,int uid);
	
	//ʵ�����֮��
	public double sumRealMoney(int year,int month,int uid);
	
	//չʾ
	public List<UserSalary> queryUserSalary(String ys,int pagenum,int pagesize);
	
	
	public long queryUserSalaryCount(String ys,int pagesize);
	
	//����ҳ
	public List<UserSalary> querySalaryByuId(String salarydate,int uid);
	
	//ͨ��ʱ�佫account���б��µĽ����¼��ѯ����
	public List<Account> queryAccountRecord(String salarydate,int uid,int pagenum,int pagesize);
	public long getAccountRecordCount(String salarydate, int uid, int pagesize);
	//�޸Ĺ���
	public void updateSalary(UserSalary updateUSalary);
}
