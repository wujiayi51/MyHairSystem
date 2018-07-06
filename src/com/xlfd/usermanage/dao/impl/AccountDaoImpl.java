package com.xlfd.usermanage.dao.impl;

import java.util.List;



import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.xlfd.common.dao.impl.BaseDaoImpl;
import com.xlfd.common.model.Account;
import com.xlfd.common.model.Client;
import com.xlfd.common.model.ClientGrade;
import com.xlfd.common.model.Serve;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserType;
import com.xlfd.usermanage.dao.AccountDao;

@Repository(value="accountDao")//@Repository注解的作用是注入来源标识
public class AccountDaoImpl extends BaseDaoImpl implements AccountDao{
	
	public Client getClient(String cId) {
		// TODO Auto-generated method stub
		return (Client)this.getById(Client.class, cId);
	}
	public void updateClient(Client clientUpdate) {
		// TODO Auto-generated method stub
		update(clientUpdate);
	}
	public List<Client> queryClient(String queryStr, List paramList) {
			String hql="from Client ";
			if(StringUtils.hasText(queryStr)){
				hql+=" where "+queryStr;
			}else{
				hql+=" where cId='' ";
			}
			return (List<Client>)this.queryByHql(hql, paramList);
	}

	public User getUser(int uId) {
		return (User)this.getById(User.class, uId);
	
	}

	public List<User> queryUser(String queryStr, List paramList) {
			String hql="from User ";
			if(StringUtils.hasText(queryStr)){
				hql+=" where "+queryStr;
			}
			
			return (List<User>)this.queryByHql(hql, paramList);
	}

	public Serve getServe(int sId) {
		return (Serve)this.getById(Serve.class, sId);
	}

	public List<Serve> queryServe(String queryStr, List paramList) {
		String hql="from Serve ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+queryStr;
		}
		
		return (List<Serve>)this.queryByHql(hql, paramList);
	}

	public UserType getUserType(int utId) {
		// TODO Auto-generated method stub
		return (UserType)this.getById(UserType.class, utId);
	}

	public List<UserType> queryUserType(String queryStr, List paramList) {
		String hql="from User  ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+queryStr;
		}
		
		return (List<UserType>)this.queryByHql(hql, paramList);
	}

	public void saveAccount(Account a) {
		save(a);
	}
	public Account getAccount(int id) {
		return (Account)this.getById(Client.class, id);
	}
	public List<Account> queryAccount(String queryStr, List paramList,
			int startIndex, int maxSize) {
		String hql="from Account ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+queryStr;
		}else{
			hql+=" where date_format(payTime,'%Y%m%d')=date_format(now(),'%Y%m%d')";
		}
		System.out.println("ssss"+queryStr);
		return (List<Account>)this.queryByHql(hql, paramList,startIndex, maxSize);
	}
	
	public List<Account> queryAccount(String queryStr,String queryStr2, List paramList) {
		String hql="select Year(payTime),MONTH(payTime),SUM(realMoney)  FROM  Account  ";
		
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+queryStr+queryStr2+" GROUP BY MONTH(payTime)";
		}else{
			hql+=" where Year(payTime) = Year(now()) and belongServe.sId=1 GROUP BY MONTH(payTime)";
		}
		System.out.println(hql);
		return (List<Account>)this.queryByHql(hql, paramList);
	}

	public long queryAccountCount(String queryStr, List paramList) {
		String hql="select count(*) from Account ";
		if(StringUtils.hasText(queryStr)){
			hql+="where "+queryStr;
		}
	
		long accountCount=(Long)this.queryUniqueByHql(hql, paramList);
		return accountCount;
		
	}

	public List<Serve> querySname() {
		String hql=" from Serve ";
		return (List<Serve>)this.queryByHql(hql);
	}

	public List<Account> queryAccount(String Strstartmonth,String Strendmonth,String Strservename,List paramList) {
		String hql="select date_format(payTime,'%Y%m') , SUM(realMoney)  FROM  Account  ";
		if(StringUtils.hasText(Strstartmonth)){
			hql+=" where "+Strstartmonth+Strendmonth+Strservename+" GROUP BY date_format(payTime,'%Y%m')";
		}else{
			hql+=" where date_format(payTime,'%Y%m') >= 201801 and date_format(payTime,'%Y%m') <= '201806' and  belongServe.sId=1 GROUP BY date_format(payTime,'%Y%m')";
		}
		System.out.println(hql);
		return (List<Account>)this.queryByHql(hql, paramList);
		
	}
}
