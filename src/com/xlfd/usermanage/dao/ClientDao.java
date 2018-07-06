package com.xlfd.usermanage.dao;

import java.util.List;

import com.xlfd.common.model.Client;
import com.xlfd.common.model.ClientCharge;
import com.xlfd.common.model.ClientGrade;


public interface ClientDao {
	
	public void saveClient(Client c);

	public Client getClient(String cId);
	
	public void updateClient(Client clientUpdate);
	
	public List<Client> queryClient(String queryStr,List paramList ,int startIndex, int maxSize);

	public long queryClientCount(String queryStr,List paramList);
	
	//会员等级

	public long queryClientGradeCount(String queryStr,List paramList);

	
	public List<ClientGrade> queryClientGrade(String queryStr, List paramList,int startIndex, int maxSize);

	public ClientGrade getClientGrade(String cgId);

	public void saveClientCharge(ClientCharge c);

	public void saveClientCharge(Client c,String chargeMoney);

	public List<Client> queryClient(String queryStr, List paramList);
	
	public List<ClientCharge> queryClientCharge(String queryStr,
			List paramList, int startIndex, int pageSize);

	public long queryClientChargeCount(String queryStr, List paramList);

/*	public void deleteClientGrade(int cgId);
	
	public void saveClientGrade(ClientGrade cg);

	public void updateClientGrade(ClientGrade clientgradeUpdate);*/

	
}
