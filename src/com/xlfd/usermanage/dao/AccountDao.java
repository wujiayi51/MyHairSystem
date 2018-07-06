package com.xlfd.usermanage.dao;

import java.util.List;



import com.xlfd.common.model.Account;
import com.xlfd.common.model.Client;
import com.xlfd.common.model.ClientGrade;
import com.xlfd.common.model.Serve;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserType;

public interface AccountDao {
	public void saveAccount(Account a);
	//»áÔ±ÐÞ¸Ä
	public Client getClient(String cId);
	public void updateClient(Client clientUpdate);
	public List<Client> queryClient(String queryStr,List paramList );

	public User getUser(int uId);
	
	public List<User> queryUser(String queryStr,List paramList );

	public UserType getUserType(int utId);
	
	public List<UserType> queryUserType(String queryStr,List paramList );
	
	public Serve getServe(int sId);
	
	public List<Serve> queryServe(String queryStr,List paramList);
	public List<Serve> querySname();
	public Account getAccount(int id);
	
	public List<Account> queryAccount(String queryStr,List paramList,int startIndex, int maxSize);
	public List<Account> queryAccount(String queryStr,String queryStr2,List paramList);
	public List<Account> queryAccount(String Strstartmonth,String Strendmonth,String Strservename,List paramList) ;

	public long queryAccountCount(String queryStr,List paramList);



}
