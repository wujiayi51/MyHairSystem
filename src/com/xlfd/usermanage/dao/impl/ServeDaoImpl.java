package com.xlfd.usermanage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.xlfd.common.dao.impl.BaseDaoImpl;
import com.xlfd.common.model.Client;
import com.xlfd.common.model.Commodity;
import com.xlfd.common.model.Serve;
import com.xlfd.common.model.UserType;
import com.xlfd.common.model.UtServe;
import com.xlfd.usermanage.dao.ServeDao;

@Repository(value="serveDao")
public class ServeDaoImpl extends BaseDaoImpl implements ServeDao {
	public long queryServeCount(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Serve ";
		if(StringUtils.hasText(queryStr)){
			hql+="where "+queryStr;
		}
	
		long serveCount=(Long)this.queryUniqueByHql(hql, paramList);
		return serveCount;
	}
	public List<Serve> queryServe(String queryStr, List paramList,int startIndex, int maxSize) {
		// TODO Auto-generated method stub
		String hql="from Serve ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+queryStr;
		}
		
		return (List<Serve>)this.queryByHql(hql, paramList,startIndex, maxSize);
	}

	public Serve getServe(int sId) {
		// TODO Auto-generated method stub
		return (Serve)this.getById(Serve.class, sId);
	}
	public void saveServe(Serve c) {
		// TODO Auto-generated method stub
		save(c);
	}
	public void updateServe(Serve serveUpdate) {
		// TODO Auto-generated method stub
		update(serveUpdate);
	}
	public void deleteServe(int sId) {
		// TODO Auto-generated method stub
		deleteById(Serve.class, sId);
	}
	public UserType getUserType(int utId) {
		// TODO Auto-generated method stub
		return (UserType)this.getById(UserType.class, utId);
	}
	public void saveUtServe(UtServe uts) {
		// TODO Auto-generated method stub
		save(uts);
	}
	public List<UserType> queryUserType() {
		// TODO Auto-generated method stub
		String hql="from UserType ";
		return (List<UserType>)this.queryByHql(hql);
	}
	public void saveServe(Serve c, String sname, Integer sprice, String sproduce) {
		// TODO Auto-generated method stub
		c.setsName(sname);
		c.setsPrice(sprice);
		c.setsProduce(sproduce);
		save(c);
	}
	public void deleteutslist(int sId) {
		// TODO Auto-generated method stub
		deleteById(UtServe.class, sId);
	}
	public List<UtServe> queryUtServe(String queryStr,List paramList) {
		// TODO Auto-generated method stub
		String hql=" from UtServe ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+queryStr;
		}
		System.out.println(hql);
		return (List<UtServe>)this.queryByHql(hql,paramList);
	}
	public List<UserType> queryUserType(String queryStr,List paramList) {
		// TODO Auto-generated method stub
		String hql="from UserType ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+queryStr;
		}
		return (List<UserType>)this.queryByHql(hql,paramList);
	}
	
}
