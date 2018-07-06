package com.xlfd.usermanage.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.xlfd.common.dao.impl.BaseDaoImpl;
import com.xlfd.common.model.Commodity;
import com.xlfd.usermanage.dao.CommodityDao;
@Repository(value="commodityDao")//@Repository注解的作用是注入来源标识
public class CommodityDaoImpl extends BaseDaoImpl implements CommodityDao {
	
	@Resource  
	private SessionFactory sessionFactory;  
	public void saveCommodity(Commodity c) {
		// TODO Auto-generated method stub
		save(c);
	}

	public Commodity getCommodity(int comId) {
		// TODO Auto-generated method stub
		return (Commodity)this.getById(Commodity.class, comId);
	}

	public void updateCommodity(Commodity commodityUpdate) {
		// TODO Auto-generated method stub
		update(commodityUpdate);
	}

	public List<Commodity> queryCommodity(String keyword, List paramList,
			int startIndex, int maxSize) {
		// TODO Auto-generated method stub
		String hql="from Commodity ";
		if(StringUtils.hasText(keyword)){
			hql+=" where "+keyword;
		}
		return (List<Commodity>)this.queryByHql(hql, paramList,startIndex, maxSize);
	}

	public long queryCommodityCount(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Commodity ";
		if(StringUtils.hasText(queryStr)){
			hql+="where "+queryStr;
		}
	
		long commodityCount=(Long)this.queryUniqueByHql(hql, paramList);
		return commodityCount;
	}

	public void deleteCommodity(int comId) {
		// TODO Auto-generated method stub
		deleteById(Commodity.class, comId);
	}


}
