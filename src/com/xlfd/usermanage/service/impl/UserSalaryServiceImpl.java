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
	@Autowired//@Autowired注解的作用是自动注入标识
	@Qualifier(value="usersalaryDao")//@Qualifier注解的作用是配置自动注入的来源
	private UserSalaryDao dao;

	public void saveUserSalary(UserSalary s) {
		// TODO Auto-generated method stub

	}

	//定时器自动计算员工工资
	//@SuppressWarnings("deprecation")
	public void saveAutoCalcUserSalary() {

		double utsalary; //员工类型基本工资
		double basicsalary = 0.0;//根据工作时间判定基本工资
		//int workDays = 0;
		List<User> userList = queryWorkUser();
		List<UserSalary> salaryList = new ArrayList<UserSalary>();
		int len = userList.size();
		//员工id号
		int uid;
		//总的工作时间
		int totalDays ;
		//当前时间
		Date nowTime = new Date();
		int nowYear = nowTime.getYear()+1900;//118
		int nowMonth = nowTime.getMonth()+1;//5
		int nowDay = nowTime.getDate();//8
		//员工状态
		int state ;
		//入职时间
		int hireYear;
		int hireMonth;
		int hireDay;
		//int totalFirstMonth;//第一个月的工作天数

		//离职时间
		int leaveDays;
		//int totalLastMonth;//最后一个月的工作天数
		for(int i=0;i<len;i++){
			User user = userList.get(i);
			utsalary = user.getBelongUserType().getUtSalary();
			uid = user.getuId();
			Date hireDate = user.getHireDate();
			hireYear = hireDate.getYear()+1900;
			hireMonth = hireDate.getMonth()+1;
			hireDay = hireDate.getDate();
			state = user.getState();
			//1.判断员工是否做满一个月，否则按天数计算基本工资（这里基本工资按员工基本工资/30天），
			//计算工作时间
			if(nowYear == hireYear && nowMonth==hireMonth){
				totalDays = nowDay-hireDay;
				//计算基本工资
				//干的第一个月的时间不满30天
				if(totalDays<30){
					basicsalary = totalDays*utsalary/30;
				}else{
					basicsalary = utsalary;
				}
			}else if(state == 0){
				//这里理发店必须干完第一个月，因为是技术活，教会了就走的，不招
				//将要离职的员工
				Date leaveTime = user.getLeaveDate();
				leaveDays = leaveTime.getDate();
				totalDays = leaveDays;
				//干的最后一个月的时间不满30天，
				if(totalDays<30){
					basicsalary = totalDays*utsalary/30;
				}else{
					basicsalary = utsalary;
				}

			}else{
				//无论是第一个月还是最后一个月,其他月都干满了30天，就按最初的基本工资结算
				basicsalary = utsalary;
			}
			//2.实付金额之和
			double sumMoney = sumRealMoney(nowYear,nowMonth,uid);

			//3.计算员工提成
			//得到提成比例
			double utScale = user.getBelongUserType().getUtScale();
			double deduct = sumMoney*utScale;//总提成
			//4.最终薪资
			double finalSalary = deduct+basicsalary;

			//5.根据员工号查找出服务的次数serveCount
			int serveCount = queryServeCount(nowYear,nowMonth,uid);

			//保存数据
			UserSalary us = new UserSalary();
			us.setUSbelongUser(user);
			us.setSalaryDate(new java.sql.Date(nowTime.getTime()));
			us.setFinalSalary(finalSalary);
			us.setServeCount(serveCount);
			us.setRtMoney(sumMoney);
			dao.saveUserSalary(us);
			System.out.println("哈哈哈哈哈哈");
		}

		System.out.println("工资计算成功");
	}
	//查找所有非离职的员工
	public List<User> queryWorkUser() {
		List paramList = new ArrayList();
		return dao.queryWorkUser(paramList);
	}

	//查找根据员工的id，查找该员工服务的总次数
	public int queryServeCount(int year,int month,int uid){
		String queryStr = "";
		List paramList = new ArrayList();		
		queryStr = " YEAR(payTime) = ? and MONTH(payTime) = ? and belongUser.uId = ?";
		paramList.add(year);
		paramList.add(month);
		paramList.add(uid);
		long count = dao.queryServeCount(queryStr,paramList); 
		return Integer.parseInt(String.valueOf(count));//将long转换成int型
	}

	/*//该员工该月提供服务的消费记录
	public List<Account> queryAccountRecord(int year,int month,int uid) {
		String queryStr = "";
		List paramList = new ArrayList();		
		queryStr = " YEAR(payTime) = ? and MONTH(payTime) = ? and belongUser.uId = ?";
		paramList.add(year);
		paramList.add(month);
		paramList.add(uid);
		return dao.queryAccountRecord(queryStr, paramList);
	}*/

	//实付金额之和
	public double sumRealMoney(int year,int month,int uid) {
		String queryStr = "";
		List paramList = new ArrayList();		
		queryStr = " YEAR(payTime) = ? and MONTH(payTime) = ? and belongUser.uId = ?";
		paramList.add(year);
		paramList.add(month);
		paramList.add(uid);
		return dao.sumRealMoney(queryStr, paramList);
	}

	//展示
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

	//详情页
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

	
	//该员工该月提供服务的消费记录
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
	
	//该员工该月的消费记录总数
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
