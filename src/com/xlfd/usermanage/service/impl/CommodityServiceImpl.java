package com.xlfd.usermanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xlfd.common.model.Commodity;
import com.xlfd.usermanage.dao.CommodityDao;
import com.xlfd.usermanage.dao.ServeDao;
import com.xlfd.usermanage.service.CommodityService;


@Service(value="commodityService")
public class CommodityServiceImpl implements CommodityService {
	
	@Autowired//@Autowired注解的作用是自动注入标识
	@Qualifier(value="commodityDao")//@Qualifier注解的作用是配置自动注入的来源
	private CommodityDao dao;
	public void saveCommodity(Commodity c) {
		// TODO Auto-generated method stub
		dao.saveCommodity(c);
	}

	public Commodity getCommodity(int comId) {
		// TODO Auto-generated method stub
		return dao.getCommodity(comId);
	}

	public void updateCommodity(Commodity commodityUpdate) {
		// TODO Auto-generated method stub
		dao.updateCommodity(commodityUpdate);
	}

	public List<Commodity> queryCommodity(String keyword, int pageNum,
			int pageSize) {
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			queryStr = "comId like ? ";
			paramList.add("%"+keyword+"%");
		}
		int startIndex =(pageNum-1)*pageSize ;
		return dao.queryCommodity(queryStr,paramList , startIndex, pageSize);
	}

	public long getCommodityPageCount(String keyword, int pageSize) {
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList= new ArrayList();
		if(StringUtils.hasText(keyword)){
			queryStr = "comId like ? ";
			paramList.add("%"+keyword+"%");
		}
		long recordCount =dao.queryCommodityCount(queryStr, paramList);

		//return (recordCount%pageSize)
		double pageCount=Math.ceil( recordCount/(double)pageSize);
		return (long)pageCount;

	}

	public void deleteCommodity(int comId) {
		// TODO Auto-generated method stub
		dao.deleteCommodity(comId);
	}

}
