package com.xlfd.usermanage.dao;

import java.util.List;


import com.xlfd.common.model.Serve;
import com.xlfd.common.model.UserType;
import com.xlfd.common.model.UtServe;

public interface ServeDao {
	
	public long queryServeCount(String queryStr, List paramList);
	
	public List<Serve> queryServe(String queryStr, List paramList,int startIndex, int maxSize); 

    public Serve getServe(int sId);

	public void saveServe(Serve c);

	public void updateServe(Serve serveUpdate);

	public void deleteServe(int sId);

	public UserType getUserType(int utId);

	public void saveUtServe(UtServe uts);

	public List<UserType> queryUserType();

	public void saveServe(Serve c, String sname, Integer sprice, String sproduce);

	public void deleteutslist(int sId);

	public List<UserType> queryUserType(String queryStr,List paramList);

	public List<UtServe> queryUtServe(String queryStr,List paramList);


	
}
