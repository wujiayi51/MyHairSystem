package com.xlfd.usermanage.service;

import java.util.List;

import com.xlfd.common.model.Commodity;

public interface CommodityService {

	public void saveCommodity(Commodity c);

	public Commodity getCommodity(int comId);

	public void updateCommodity(Commodity commodityUpdate); 

	public List<Commodity> queryCommodity(String keyword ,int pageNum,int pageSize);

	public long getCommodityPageCount(String keyword,int pageSize);
	
	public void deleteCommodity(int comId);
}
