package com.xlfd.usermanage.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
//import org.springframework.util.StringUtils;


import com.xlfd.common.model.Account;
import com.xlfd.common.model.Client;
import com.xlfd.common.model.Serve;

import com.xlfd.common.model.User;
import com.xlfd.common.model.UserType;
import com.xlfd.usermanage.dao.AccountDao;
import com.xlfd.usermanage.service.AccountService;
@Service(value="accountService")
public class AccountServiceImpl implements AccountService {
	@Autowired//@Autowired注解的作用是自动注入标识
	@Qualifier(value="accountDao")//@Qualifier注解的作用是配置自动注入的来源
	private AccountDao dao;

	public Client getClient(String cId) {
		// TODO Auto-generated method stub
		return dao.getClient(cId);
	}

	public List<Client> queryClient(String cid) {
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(cid)){
			queryStr = "cId= ? ";
			paramList.add(cid);
		}

		return dao.queryClient(queryStr,paramList );
	}
	public User getUser(int uId) {
		return dao.getUser(uId);
	}

	public List<User> queryUser(String uukeyword) {
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(uukeyword)){
			queryStr = "uName like ? ";
			paramList.add("%"+uukeyword+"%");
		}

		return dao.queryUser(queryStr,paramList );
	}

	public Serve getServe(int sId) {
		// TODO Auto-generated method stub
		return dao.getServe(sId);
	}

	public List<Serve> queryServe(String sskeyword ) {
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(sskeyword)){
			queryStr = "sName like ? ";
			paramList.add("%"+sskeyword+"%");
		}

		return dao.queryServe(queryStr, paramList);
	}

	public UserType getUserType(int utId) {
		// TODO Auto-generated method stub
		return dao.getUserType(utId);
	}

	public List<UserType> queryUserType(String utkeyword) {
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(utkeyword)){
			queryStr = "uName like ? ";
			paramList.add("%"+utkeyword+"%");
		}

		return dao.queryUserType(queryStr, paramList);
	}

	public void saveAccount(Account a) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String flowNumber = sdf.format(date);
		a.setFlowNumber(flowNumber);
		a.setPayTime(sdf.format(date));
		dao.saveAccount(a);	
	}
	//修改会员
	public void updateClient(String cid,double realmoney){
		Client c = dao.getClient(cid);
		double bala = c.getcBala();
		double abala = bala-realmoney;
		c.setcBala(abala);
		dao.updateClient(c);
	}

	public Account getAccount(int id) {
		// TODO Auto-generated method stub
		return dao.getAccount(id);
	}
//根据时间选择查询每日账单
	public List<Account> queryAccount(String keyword, int pageNum, int pageSize) {
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			queryStr = "payTime like ? ";   
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date= null;
			try {
				date= sdf.parse(keyword);  
				System.out.println("时间是---"+date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}*/
			paramList.add("%"+keyword+"%");
		}
		System.out.println("ssss"+queryStr);
		int startIndex =(pageNum-1)*pageSize ;
		return dao.queryAccount(queryStr,paramList , startIndex, pageSize);
	}

	public long getAccountPageCount(String keyword, int pageSize) {
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			queryStr = "payTime like ? ";
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date= null;
			try {
				date= sdf.parse(keyword);  
				System.out.println("时间是---"+date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}*/
			paramList.add("%"+keyword+"%");
		}

		long recordCount =dao.queryAccountCount(queryStr, paramList);
		double pageCount=Math.ceil( recordCount/(double)pageSize);
		return (long)pageCount;
	}

	public List<Serve> querySname() {
		return dao.querySname();
	}
	//按年查服务与收入的折线图
	public List<Account> queryAccount(String keyword,String keyword2) {
		String queryStr = "";
		String queryStr2 = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword2)){
			queryStr = " Year(payTime) = ? ";
			paramList.add(Integer.parseInt(keyword2));
		}
		if(StringUtils.hasText(keyword)){
			queryStr2 = " and belongServe.sId = ? ";
			int b =0;
			try {
				b = Integer.parseInt(keyword);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			paramList.add(b);
		}
		return dao.queryAccount(queryStr, queryStr2, paramList);
	}
	
//选择起止年月和服务项目 按月查服务与收入的折线图
	public List<Account> queryAccount(String startmonth,String endmonth,String servename) {
		String Strstartmonth = "";
		String Strendmonth = "";
		String Strservename = "";
		List paramList= new ArrayList();
		
		if(StringUtils.hasText(startmonth)){
			Strstartmonth = " date_format(payTime,'%Y%m') >= ? ";
			/*paramList.add(Integer.parseInt(startmonth));*/
			paramList.add(startmonth);
		}
		if(StringUtils.hasText(endmonth)){
			Strendmonth = " and date_format(payTime,'%Y%m') <= ? ";
			paramList.add(endmonth);
		}
		if(StringUtils.hasText(servename)){
			Strservename = "and belongServe.sId = ? ";
			paramList.add(Integer.parseInt(servename));
		}
		return dao.queryAccount(Strstartmonth,Strendmonth,Strservename,paramList);
	}
}
