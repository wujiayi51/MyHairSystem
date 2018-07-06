package com.xlfd.usermanage.service;

import java.util.List;


import com.xlfd.common.model.Client;
import com.xlfd.common.model.Serve;
import com.xlfd.common.model.UserType;
import com.xlfd.common.model.UtServe;


public interface ServeService {

	public List<Serve> queryServe(String keyword, int pageNum, int pageSize);

	public Serve getServe(int sId);
	
	public long getServePageCount(String keyword,int pagesize);

	public void saveServe(Serve c);

	public void updateServe(Serve serveUpdate);

	public void deleteServe(int sId);

	public UserType getUserType(int utId);
	//Ìí¼Óutserve
	public void saveUtServe(UtServe uts, Serve c, UserType ut);
	
	public List<UserType> queryusertype();

	public void saveServe(Serve c, String sname, Integer sprice, String sproduce);

	//É¾³ýutserve
	public void deleteutslist(int sId);

	public List<UtServe> queryUtServe(int sId);

	public List<UserType> getUserType(String utName);

		
}
