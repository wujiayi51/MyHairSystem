package com.xlfd.usermanage.dao.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


import com.xlfd.common.dao.impl.BaseDaoImpl;
import com.xlfd.common.model.Client;
import com.xlfd.common.model.ClientCharge;
import com.xlfd.common.model.ClientGrade;
import com.xlfd.usermanage.dao.ClientDao;

@Repository(value="clientDao")//@Repositoryע���������ע����Դ��ʶ
public class ClientDaoImpl extends BaseDaoImpl implements ClientDao {
	
	public void saveClient(Client c) {
		// TODO Auto-generated method stub
		String sdf = new SimpleDateFormat("yyyyMMdd").format(new Date());
		c.setcTime(sdf);
		save(c);
	}

	public Client getClient(String cId) {
		// TODO Auto-generated method stub
		return (Client)this.getById(Client.class, cId);
	}

	public void updateClient(Client clientUpdate) {
		// TODO Auto-generated method stub
		update(clientUpdate);
	}

	public List<Client> queryClient(String queryStr, List paramList,
			int startIndex, int maxSize) {
		String hql="from Client ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+queryStr;
		}
		
		return (List<Client>)this.queryByHql(hql, paramList,startIndex, maxSize);
	}

	public long queryClientCount(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Client ";
		if(StringUtils.hasText(queryStr)){
			hql+="where "+queryStr;
		}
	
		long clientCount=(Long)this.queryUniqueByHql(hql, paramList);
		return clientCount;
	}

	//��Ա�ȼ�
	public long queryClientGradeCount(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql="select count(*) from ClientGrade ";
		if(StringUtils.hasText(queryStr)){
			hql+="where "+queryStr;
		}
	
		long clientgradeCount=(Long)this.queryUniqueByHql(hql, paramList);
		return clientgradeCount;
	}
	public List<ClientGrade> queryClientGrade(String queryStr, List paramList,int startIndex, int maxSize) {
		// TODO Auto-generated method stub
		String hql="from ClientGrade ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+queryStr;
		}
		
		return (List<ClientGrade>)this.queryByHql(hql, paramList,startIndex, maxSize);
	}

	public ClientGrade getClientGrade(String cgId) {
		// TODO Auto-generated method stub
		return (ClientGrade)this.getById(ClientGrade.class, cgId);
	}
	//����clientcharge��ֵ��¼
	public void saveClientCharge(ClientCharge c) {
		// TODO Auto-generated method stub
		
		save(c);
	}
	//����client�������ȼ�
	public void saveClientCharge(Client c,String chargeMoney) {
		// TODO Auto-generated method stub
		update(c);
	}

	public List<Client> queryClient(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql="from Client ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+queryStr;
		}
		Client c;
		return (List<Client>)this.queryByHql(hql, paramList);
	}
	//��ѯ��Ա��ֵ��¼
	public List<ClientCharge> queryClientCharge(String queryStr,
			List paramList, int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		String hql="from ClientCharge ";
		if(StringUtils.hasText(queryStr)){
			hql+=" where "+queryStr;
		}
		Client c;
		return (List<ClientCharge>)this.queryByHql(hql, paramList);
	}
	//��Ա��ֵ��¼���� 
	public long queryClientChargeCount(String queryStr, List paramList) {
		// TODO Auto-generated method stub
		String hql="select count(*) from ClientCharge ";
		if(StringUtils.hasText(queryStr)){
			hql+="where "+queryStr;
		}
	
		long clientgradeCount=(Long)this.queryUniqueByHql(hql, paramList);
		return clientgradeCount;
	}

}
