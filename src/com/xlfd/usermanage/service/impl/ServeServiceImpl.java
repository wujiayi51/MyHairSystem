package com.xlfd.usermanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xlfd.common.model.Client;
import com.xlfd.common.model.Serve;
import com.xlfd.common.model.UserType;
import com.xlfd.common.model.UtServe;
import com.xlfd.usermanage.dao.ServeDao;
import com.xlfd.usermanage.service.ServeService;




@Service(value="serveService")
public class ServeServiceImpl implements ServeService {
	@Autowired//@Autowired注解的作用是自动注入标识
	@Qualifier(value="serveDao")//@Qualifier注解的作用是配置自动注入的来源
	private ServeDao dao;
	public long getServePageCount(String keyword, int pageSize) {
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			queryStr = "sId like ? ";
			paramList.add("%"+keyword+"%");
		}
		long recordCount =dao.queryServeCount(queryStr, paramList);
		//return (recordCount%pageSize)
		double pageCount=Math.ceil( recordCount/(double)pageSize);
		return (long)pageCount;
	}

	public List<Serve> queryServe(String keyword, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			queryStr = "sId like ? ";
			paramList.add("%"+keyword+"%");
		}
		int startIndex =(pageNum-1)*pageSize ;
		return dao.queryServe(queryStr,paramList,startIndex, pageSize);
	}

	public Serve getServe(int sId) {
		// TODO Auto-generated method stub
		return dao.getServe(sId);
	}

	public void saveServe(Serve c) {
		// TODO Auto-generated method stub

		dao.saveServe(c);
	}

	public void updateServe(Serve serveUpdate) {
		// TODO Auto-generated method stub
		dao.updateServe(serveUpdate);
	}

	public void deleteServe(int sId) {
		// TODO Auto-generated method stub
		dao.deleteServe(sId);
	}

	public UserType getUserType(int utId) {
		// TODO Auto-generated method stub
		return dao.getUserType(utId);
	}

	public void saveUtServe(UtServe uts, Serve c, UserType ut) {
		// TODO Auto-generated method stub
		uts.setUtservebelongServe(c);
		uts.setUtservebelongUserType(ut);
		dao.saveUtServe(uts);
	}

	public List<UserType> queryusertype() {
		// TODO Auto-generated method stub
		return dao.queryUserType();
	}

	public void saveServe(Serve c, String sname, Integer sprice, String sproduce) {
		// TODO Auto-generated method stub
		dao.saveServe(c,sname,sprice,sproduce);
	}

	public void deleteutslist(int sId) {
		// TODO Auto-generated method stub
		dao.deleteutslist(sId);
	}

	public List<UtServe> queryUtServe(int sId) {
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(String.valueOf(sId))){
			System.out.println(sId);
			queryStr = " utservebelongServe.sId = ?";
			paramList.add(sId);
		}
		return dao.queryUtServe(queryStr,paramList);
	}

	public List<UserType> getUserType(String utName) {
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(utName)){
			queryStr = "utName = ? ";
			paramList.add(utName);
		}
		return dao.queryUserType(queryStr,paramList);
	}


}
