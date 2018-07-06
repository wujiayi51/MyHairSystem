package com.xlfd.usermanage.dao;

import java.util.List;

import com.xlfd.common.model.Account;
import com.xlfd.common.model.UserSalary;
import com.xlfd.common.model.User;

public interface UserSalaryDao {
	
	public void saveUserSalary(UserSalary s);
	public UserSalary getUserSalary(int usId);
	public void updateUserSalary(UserSalary Update);
	
	//����user
	public List<User> queryWorkUser(List paramList);
	//ͨ��ʱ�佫account���б��µĽ����¼��ѯ����
	public List<Account> queryAccountRecord(String queryStr, List paramList,int startIndex,int pagesize);
	public long queryAccountRecordCount(String queryStr,List paramList);
	//���Ҹ���Ա����id�����Ҹ�Ա��������ܴ���
	public long queryServeCount(String queryStr,List paramList);
	
	//ʵ�����֮��
	public Double sumRealMoney(String queryStr, List paramList);
	
	//��������ѯ  չʾ
	public List<UserSalary> queryUserSalary(String queryStr,List paramList,int startIndex,int maxSize);
	public long queryUserSalaryCount(String queryStr,List paramList);
	
	//����ҳ
	public List<UserSalary> querySalaryByuId(String queryStr,List paramList);
}
