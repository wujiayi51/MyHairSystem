package com.xlfd.usermanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xlfd.common.model.Account;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserSalary;
import com.xlfd.usermanage.service.UserSalaryService;
@Controller
@RequestMapping(value="/salary")
public class UserSalaryController {
	@Autowired//自动注入标识
	@Qualifier(value="usersalaryService")//配置自动注入的来源private UserService service;
	private UserSalaryService service;
	
	@RequestMapping(value="/querysalary") 
	public String queryUserSalary(Model model,
	 			@RequestParam(value="ym", required=false,defaultValue="") String ym,
	 			@RequestParam(value="pagenum", required=false,defaultValue="1") int pagenum,
	 			@RequestParam(value="pagesize", required=false,defaultValue="8") int pagesize,
	 			HttpServletResponse response) throws IOException{
		
		String ymstr = ym;
		//默认上个月的工资显示
		if(ymstr.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -1);
			ymstr = sdf.format(cal.getTime());
		}
		List<UserSalary> salaryList = service.queryUserSalary(ymstr,pagenum,pagesize);
 		//List<User> userList = service.queryWorkUser();
 		long pagecount = service.queryUserSalaryCount(ymstr, pagesize);
		System.out.println(salaryList);
 		addAttribute(model,"salary_List",salaryList,true);	
 		//addAttribute(model,"user_list",userList,true);
 		addAttribute(model,"ym",ymstr,true);
 		addAttribute(model,"pagenum",pagenum,true);
 		addAttribute(model,"pagesize",pagesize,true);
 		addAttribute(model,"pagecount",pagecount,true);
 		PrintWriter out=response.getWriter();
 		JSONObject json=new JSONObject();
 		out.print(json);
	 	return "/user/usersalary";
	 }
	
	//某员工某月应发工资详情--含该员工本月所有的工作记录
	@RequestMapping(value="/querydetails") 
	public String queryDetails(Model model,
		@RequestParam(value="uid", required=false,defaultValue="") int uid,
		@RequestParam(value="salarydate", required=false,defaultValue="") String salarydate,
		@RequestParam(value="pagenum", required=false,defaultValue="1") int pagenum,
		@RequestParam(value="pagesize", required=false,defaultValue="5") int pagesize){
 
		//某位员工某月应付工资详情
		//1.查询usersalary表返回salary_List
		List<UserSalary> salaryList = service.querySalaryByuId(salarydate,uid);
		addAttribute(model,"salary_List",salaryList,true);	
		
		//2.该员工本月所有的工作记录 查询account结算表
		List<Account> accountList = service.queryAccountRecord(salarydate, uid,pagenum,pagesize);
		long pagecount = service.getAccountRecordCount(salarydate, uid, pagesize);
		
		addAttribute(model,"account_list",accountList,true);
		addAttribute(model,"pagenum",pagenum,true);
 		addAttribute(model,"pagesize",pagesize,true);
 		addAttribute(model,"pagecount",pagecount,true);
	 	return "/user/salaryDetails";
	 }
	
	@RequestMapping(value="/updatesalary",method={RequestMethod.POST}) 
	public String updateDetails(Model model,@RequestParam(value="id") int id,
		@RequestParam(value="finalSalary") double finalSalary,
		@RequestParam(value="addDes") String addDes){
		//根据员工的表现，对工资修改
		UserSalary updateUSalary = service.getUserSalary(id);
		updateUSalary.setAddDes(addDes);
		updateUSalary.setFinalSalary(finalSalary);
		service.updateSalary(updateUSalary);
	 	return "redirect:/salary/querysalary";
	 }
	
	
	private void addAttribute(Model model,String attributeName,Object attributeValue ,boolean replace){
		if(replace==true){
			model.addAttribute(attributeName,attributeValue);
		}else{
			if(!model.containsAttribute(attributeName)){

				model.addAttribute(attributeName,attributeValue);
			}
		}
	}
}
