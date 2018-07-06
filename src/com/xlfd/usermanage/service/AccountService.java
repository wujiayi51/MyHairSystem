package com.xlfd.usermanage.service;

import java.util.List;


import com.xlfd.common.model.Account;
import com.xlfd.common.model.User;
import com.xlfd.common.model.Client;
import com.xlfd.common.model.Serve;
import com.xlfd.common.model.UserType;

public interface AccountService {
	public Client getClient(String cId);
	public void updateClient(String cid,double realmoney);
	public List<Client> queryClient(String cckeyword );

	public User getUser(int uId);
	
	public List<User> queryUser(String cid );

	public Serve getServe(int sId);
	
	public List<Serve> queryServe(String sskeyword);
	public List<Serve> querySname();
	public UserType getUserType(int utId);
	
	public List<UserType> queryUserType(String utkeyword);
	
	public void saveAccount(Account a);
	
	public Account getAccount(int id);
	
	public List<Account> queryAccount(String keyword,int pageNum,int pageSize);
	public List<Account> queryAccount(String keyword,String keyword2);
	public List<Account> queryAccount(String startmonth,String endmonth,String servename) ;
	public long getAccountPageCount(String keyword,int pageSize);


}
