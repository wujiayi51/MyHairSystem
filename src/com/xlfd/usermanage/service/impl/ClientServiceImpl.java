package com.xlfd.usermanage.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xlfd.common.model.Client;
import com.xlfd.common.model.ClientCharge;
import com.xlfd.common.model.ClientGrade;
import com.xlfd.usermanage.dao.ClientDao;
import com.xlfd.usermanage.service.ClientService;

@Service(value="clientService")//@Repository注解的作用是注入来源标识
public class ClientServiceImpl implements ClientService {
	@Autowired//@Autowired注解的作用是自动注入标识
	@Qualifier(value="clientDao")//@Qualifier注解的作用是配置自动注入的来源
	private ClientDao dao;
	public void saveClient(Client c) {
		// TODO Auto-generated method stub
		dao.saveClient(c);
	}

	public Client getClient(String cId) {
		// TODO Auto-generated method stub
		return dao.getClient(cId);
	}

	public void updateClient(Client clientUpdate) {
		// TODO Auto-generated method stub
		dao.updateClient(clientUpdate);
	}

	public List<Client> queryClient(String keyword, int pageNum, int pageSize) {
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			 queryStr = "cId like ? ";
			 paramList.add("%"+keyword+"%");
		}
		int startIndex =(pageNum-1)*pageSize ;
		return dao.queryClient(queryStr,paramList , startIndex, pageSize);
	}

	public long getClientPageCount(String keyword, int pageSize) {
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			 queryStr = "cId like ? ";
			 paramList.add("%"+keyword+"%");
		}
		long recordCount =dao.queryClientCount(queryStr, paramList);
		
		//return (recordCount%pageSize)
		double pageCount=Math.ceil( recordCount/(double)pageSize);
		return (long)pageCount;
	}
	public void saveClientCharge(ClientCharge c) {
		// TODO Auto-generated method stub
		String orderNo = "" ; 
		String trandNo = String.valueOf((Math.random() * 4 + 1) * 1000000); 
		String sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); 
		String sdf1 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
		orderNo = trandNo.toString().substring(0, 4); 
		orderNo = "XLFD" + orderNo + sdf1 ; 
		c.setLiushuiNum(orderNo);
		System.out.println(orderNo);
		c.setChargeDate(sdf);
		dao.saveClientCharge(c);
		
	}

	public void saveClientCharge(Client c,String chargeMoney) {
		// TODO Auto-generated method stub
		double cBala=c.getcBala();
		double chargeAfter =Integer.parseInt(chargeMoney) + cBala;
		c.setcName(c.getcName());
		c.setcId(c.getcId()); 
		c.setcState(c.getcState()); 
		c.setcSex(c.getcSex());
		c.setcBala(chargeAfter);
		c.setcTime(c.getcTime());
		if(chargeAfter<=200){
			c.setcDiscount(0.95);  
			 
		}else if(chargeAfter<=400)
		{
			c.setcDiscount(0.85);  
			
		}else if(chargeAfter<=600)
		{
			c.setcDiscount(0.75);  
			
		}else if(chargeAfter<=1000)
		{
			c.setcDiscount(0.65);  
		
		}else{
			c.setcDiscount( 0.5);  
		
		}
		dao.saveClientCharge(c,chargeMoney);
		
	}
/*
	public void saveClientGrade(ClientGrade cg) {
		// TODO Auto-generated method stub
		dao.saveClientGrade(cg);
	}*/


	public ClientGrade getClientGrade(String cgId) {
		// TODO Auto-generated method stub
		return dao.getClientGrade(cgId);
	}


	public long getClientGradePageCount(String keyword, int pageSize) {
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			 queryStr = "cgId like ? ";
			 paramList.add("%"+keyword+"%");
		}
		long recordCount =dao.queryClientGradeCount(queryStr, paramList);
		
		//return (recordCount%pageSize)
		double pageCount=Math.ceil( recordCount/(double)pageSize);
		return (long)pageCount;
	}

	public List<ClientGrade> queryClientGrade(String keyword, int pageNum,
			int pageSize) {
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			 queryStr = "cgId like ? ";
			 paramList.add("%"+keyword+"%");
		}
		int startIndex =(pageNum-1)*pageSize ;
		return dao.queryClientGrade(queryStr,paramList,startIndex, pageSize);
	}

	public List<Client> queryClient(String keyword) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			queryStr = "cId like ? ";
			paramList.add("%"+keyword+"%");
		}
		return dao.queryClient(queryStr,paramList);
	}

	//会员充值记录
	public List<ClientCharge> queryClientCharge(String keyword, int pageNum,
			int pageSize) {
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			 queryStr = "cId like ? ";
			 paramList.add("%"+keyword+"%");
		}
		int startIndex =(pageNum-1)*pageSize ;
		return dao.queryClientCharge(queryStr,paramList,startIndex, pageSize);
	}

	public long getClientChargePageCount(String keyword, int pageSize) {
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			 queryStr = "cId like ? ";
			 paramList.add("%"+keyword+"%");
		}
		long recordCount =dao.queryClientChargeCount(queryStr, paramList);
		double pageCount=Math.ceil( recordCount/(double)pageSize);
		return (long)pageCount;
	}



}
