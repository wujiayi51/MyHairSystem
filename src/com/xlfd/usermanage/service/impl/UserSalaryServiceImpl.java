package com.xlfd.usermanage.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xlfd.common.model.Account;
import com.xlfd.common.model.UserSalary;
import com.xlfd.common.model.User;
import com.xlfd.usermanage.dao.UserSalaryDao;
import com.xlfd.usermanage.service.UserSalaryService;

@Service(value="usersalaryService")
public class UserSalaryServiceImpl implements UserSalaryService{
	@Autowired//@Autowiredע����������Զ�ע���ʶ
	@Qualifier(value="usersalaryDao")//@Qualifierע��������������Զ�ע�����Դ
	private UserSalaryDao dao;

	public void saveUserSalary(UserSalary s) {
		// TODO Auto-generated method stub

	}

	//��ʱ���Զ�����Ա������
	//@SuppressWarnings("deprecation")
	public void saveAutoCalcUserSalary() {

		double utsalary; //Ա�����ͻ�������
		double basicsalary = 0.0;//���ݹ���ʱ���ж���������
		//int workDays = 0;
		List<User> userList = queryWorkUser();
		List<UserSalary> salaryList = new ArrayList<UserSalary>();
		int len = userList.size();
		//Ա��id��
		int uid;
		//�ܵĹ���ʱ��
		int totalDays ;
		//��ǰʱ��
		Date nowTime = new Date();
		int nowYear = nowTime.getYear()+1900;//118
		int nowMonth = nowTime.getMonth()+1;//5
		int nowDay = nowTime.getDate();//8
		//Ա��״̬
		int state ;
		//��ְʱ��
		int hireYear;
		int hireMonth;
		int hireDay;
		//int totalFirstMonth;//��һ���µĹ�������

		//��ְʱ��
		int leaveDays;
		//int totalLastMonth;//���һ���µĹ�������
		for(int i=0;i<len;i++){
			User user = userList.get(i);
			utsalary = user.getBelongUserType().getUtSalary();
			uid = user.getuId();
			Date hireDate = user.getHireDate();
			hireYear = hireDate.getYear()+1900;
			hireMonth = hireDate.getMonth()+1;
			hireDay = hireDate.getDate();
			state = user.getState();
			//1.�ж�Ա���Ƿ�����һ���£�������������������ʣ�����������ʰ�Ա����������/30�죩��
			//���㹤��ʱ��
			if(nowYear == hireYear && nowMonth==hireMonth){
				totalDays = nowDay-hireDay;
				//�����������
				//�ɵĵ�һ���µ�ʱ�䲻��30��
				if(totalDays<30){
					basicsalary = totalDays*utsalary/30;
				}else{
					basicsalary = utsalary;
				}
			}else if(state == 0){
				//���������������һ���£���Ϊ�Ǽ�����̻��˾��ߵģ�����
				//��Ҫ��ְ��Ա��
				Date leaveTime = user.getLeaveDate();
				leaveDays = leaveTime.getDate();
				totalDays = leaveDays;
				//�ɵ����һ���µ�ʱ�䲻��30�죬
				if(totalDays<30){
					basicsalary = totalDays*utsalary/30;
				}else{
					basicsalary = utsalary;
				}

			}else{
				//�����ǵ�һ���»������һ����,�����¶�������30�죬�Ͱ�����Ļ������ʽ���
				basicsalary = utsalary;
			}
			//2.ʵ�����֮��
			double sumMoney = sumRealMoney(nowYear,nowMonth,uid);

			//3.����Ա�����
			//�õ���ɱ���
			double utScale = user.getBelongUserType().getUtScale();
			double deduct = sumMoney*utScale;//�����
			//4.����н��
			double finalSalary = deduct+basicsalary;

			//5.����Ա���Ų��ҳ�����Ĵ���serveCount
			int serveCount = queryServeCount(nowYear,nowMonth,uid);

			//��������
			UserSalary us = new UserSalary();
			us.setUSbelongUser(user);
			us.setSalaryDate(new java.sql.Date(nowTime.getTime()));
			us.setFinalSalary(finalSalary);
			us.setServeCount(serveCount);
			us.setRtMoney(sumMoney);
			dao.saveUserSalary(us);
			System.out.println("������������");
		}

		System.out.println("���ʼ���ɹ�");
	}
	//�������з���ְ��Ա��
	public List<User> queryWorkUser() {
		List paramList = new ArrayList();
		return dao.queryWorkUser(paramList);
	}

	//���Ҹ���Ա����id�����Ҹ�Ա��������ܴ���
	public int queryServeCount(int year,int month,int uid){
		String queryStr = "";
		List paramList = new ArrayList();		
		queryStr = " YEAR(payTime) = ? and MONTH(payTime) = ? and belongUser.uId = ?";
		paramList.add(year);
		paramList.add(month);
		paramList.add(uid);
		long count = dao.queryServeCount(queryStr,paramList); 
		return Integer.parseInt(String.valueOf(count));//��longת����int��
	}

	/*//��Ա�������ṩ��������Ѽ�¼
	public List<Account> queryAccountRecord(int year,int month,int uid) {
		String queryStr = "";
		List paramList = new ArrayList();		
		queryStr = " YEAR(payTime) = ? and MONTH(payTime) = ? and belongUser.uId = ?";
		paramList.add(year);
		paramList.add(month);
		paramList.add(uid);
		return dao.queryAccountRecord(queryStr, paramList);
	}*/

	//ʵ�����֮��
	public double sumRealMoney(int year,int month,int uid) {
		String queryStr = "";
		List paramList = new ArrayList();		
		queryStr = " YEAR(payTime) = ? and MONTH(payTime) = ? and belongUser.uId = ?";
		paramList.add(year);
		paramList.add(month);
		paramList.add(uid);
		return dao.sumRealMoney(queryStr, paramList);
	}

	//չʾ
	public List<UserSalary> queryUserSalary(String ys,int pagenum,int pagesize){
		// TODO Auto-generated method stub
		String queryStr = "";
		List paramList = new ArrayList();
		System.out.println(ys);
		if(StringUtils.hasText(ys)){
			queryStr = " date_format(salaryDate,'%Y%m') = ?";
			paramList.add(ys);
		}
		int startIndex = (pagenum-1)*pagesize;
		return dao.queryUserSalary(queryStr, paramList,startIndex,pagesize);
	}

	public long queryUserSalaryCount(String ys,int pagesize){
		String queryStr = "";
		List paramList = new ArrayList();
		if(StringUtils.hasText(ys)){
			queryStr = " date_format(salaryDate,'%Y%m') = ?";
			paramList.add(ys);
		}
		long recordCount = dao.queryUserSalaryCount(queryStr, paramList);
		long pagecount = (long)Math.ceil(recordCount/(double)pagesize);
		return (long)pagecount;
	}

	//����ҳ
	public List<UserSalary> querySalaryByuId(String salarydate, int uid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(salarydate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int year = date.getYear()+1900;
		int month = date.getMonth()+1;
		String queryStr = " YEAR(salaryDate) = ? AND MONTH(salaryDate) = ? AND USbelongUser.uId = ?";
		List paramList = new ArrayList();
		paramList.add(year);
		paramList.add(month);
		paramList.add(uid);

		return dao.querySalaryByuId(queryStr,paramList);
	}

	
	//��Ա�������ṩ��������Ѽ�¼
	public List<Account> queryAccountRecord(String salarydate,int uid,int pagenum,int pagesize) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(salarydate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int year = date.getYear()+1900;
		int month = date.getMonth()+1;
		String queryStr = "";
		List paramList = new ArrayList();		
		queryStr = " YEAR(payTime) = ? and MONTH(payTime) = ? and belongUser.uId = ?";
		paramList.add(year);
		paramList.add(month);
		paramList.add(uid);
		int startIndex = (pagenum-1)*pagesize;
		return dao.queryAccountRecord(queryStr, paramList,startIndex,pagesize);
	}
	
	//��Ա�����µ����Ѽ�¼����
	public long getAccountRecordCount(String salarydate, int uid, int pagesize){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(salarydate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int year = date.getYear()+1900;
		int month = date.getMonth()+1;
		String queryStr = "";
		List paramList = new ArrayList();		
		queryStr = " YEAR(payTime) = ? and MONTH(payTime) = ? and belongUser.uId = ?";
		paramList.add(year);
		paramList.add(month);
		paramList.add(uid);
		
		long recordCount = dao.queryAccountRecordCount(queryStr, paramList);
		long pagecount = (long)Math.ceil(recordCount/(double)pagesize);
		return (long)pagecount;
	}

	public UserSalary getUserSalary(int usid) {
		return dao.getUserSalary(usid);
	}

	public void updateSalary(UserSalary updateUSalary) {
		dao.updateUserSalary(updateUSalary);
	}
}
