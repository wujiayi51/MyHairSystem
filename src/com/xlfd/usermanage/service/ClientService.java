package com.xlfd.usermanage.service;



import java.util.List;


import com.xlfd.common.model.Client;
import com.xlfd.common.model.ClientCharge;
import com.xlfd.common.model.ClientGrade;


public interface ClientService {
	//��Ա�����
	public void saveClient(Client c);
	
	public Client getClient(String cId);
	
	public void updateClient(Client clientUpdate); 
	
	public List<Client> queryClient(String keyword ,int pageNum,int pageSize);
	
	public long getClientPageCount(String keyword,int pageSize);
	//��Ա�ȼ���ѯ

	public ClientGrade getClientGrade(String cgId);

	public long getClientGradePageCount(String keyword, int pageSize);

	public List<ClientGrade> queryClientGrade(String keyword, int pageNum,
			int pageSize);
	
	//��Ա��ֵ ����
	public void saveClientCharge(ClientCharge c);
	
	public void saveClientCharge(Client c,String chargeMoney);
	
	public List<Client> queryClient(String keyword);
	//��Ա��ֵ��¼
	public List<ClientCharge> queryClientCharge(String keyword, int pageNum,
			int pageSize);

	public long getClientChargePageCount(String keyword, int pageSize);

/*	public void updateClientGrade(ClientGrade clientgradeUpdate); 
	
	public void deleteClientGrade(int cgId);
	
	
	public void saveClientGrade(ClientGrade cg);*/
	
}
