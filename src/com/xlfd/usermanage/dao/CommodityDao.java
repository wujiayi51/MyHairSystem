package com.xlfd.usermanage.dao;

import java.util.List;

import com.xlfd.common.model.Commodity;

public interface CommodityDao {

	public void saveCommodity(Commodity c);

	public Commodity getCommodity(int comId);

	public void updateCommodity(Commodity commodityUpdate); 

	public List<Commodity> queryCommodity(String queryStr, List paramList,
			int startIndex, int maxSize);

	public long queryCommodityCount(String queryStr,List paramList);
	
	public void deleteCommodity(int comId);
}
