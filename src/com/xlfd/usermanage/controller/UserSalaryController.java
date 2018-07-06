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
	@Autowired//�Զ�ע���ʶ
	@Qualifier(value="usersalaryService")//�����Զ�ע�����Դprivate UserService service;
	private UserSalaryService service;
	
	@RequestMapping(value="/querysalary") 
	public String queryUserSalary(Model model,
	 			@RequestParam(value="ym", required=false,defaultValue="") String ym,
	 			@RequestParam(value="pagenum", required=false,defaultValue="1") int pagenum,
	 			@RequestParam(value="pagesize", required=false,defaultValue="8") int pagesize,
	 			HttpServletResponse response) throws IOException{
		
		String ymstr = ym;
		//Ĭ���ϸ��µĹ�����ʾ
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
	
	//ĳԱ��ĳ��Ӧ����������--����Ա���������еĹ�����¼
	@RequestMapping(value="/querydetails") 
	public String queryDetails(Model model,
		@RequestParam(value="uid", required=false,defaultValue="") int uid,
		@RequestParam(value="salarydate", required=false,defaultValue="") String salarydate,
		@RequestParam(value="pagenum", required=false,defaultValue="1") int pagenum,
		@RequestParam(value="pagesize", required=false,defaultValue="5") int pagesize){
 
		//ĳλԱ��ĳ��Ӧ����������
		//1.��ѯusersalary����salary_List
		List<UserSalary> salaryList = service.querySalaryByuId(salarydate,uid);
		addAttribute(model,"salary_List",salaryList,true);	
		
		//2.��Ա���������еĹ�����¼ ��ѯaccount�����
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
		//����Ա���ı��֣��Թ����޸�
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
