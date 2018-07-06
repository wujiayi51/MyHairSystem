package com.xlfd.usermanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xlfd.common.model.Account;
import com.xlfd.common.model.Client;
import com.xlfd.common.model.ClientCharge;
import com.xlfd.common.model.Expense;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserSalary;
import com.xlfd.usermanage.service.ReportService;
@Controller
public class ReportController {
	@Autowired//自动注入标识
	@Qualifier(value="reportService")
	private ReportService service;
	//根据所选择的年份，按月统计本年的总收入变化和总支出变化，以柱状图显示
	@RequestMapping(value="/chart",method={RequestMethod.GET})
	public String  doReport(Model model){
		return "report/yearreport";
	}
	@RequestMapping(value="/chart")
	public void  queryReport(HttpServletRequest request, HttpServletResponse response
			/* @RequestParam(value="keyword",required=false,defaultValue="") String keyword,*/) throws IOException
			{	
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		response.setContentType("text/html;charset=utf-8");  
		String keyword=request.getParameter("keyword");
		//总收入
		List<Account> array1 = service.queryAccount(keyword);
		JsonConfig jsonConfig = new JsonConfig(); 
		jsonConfig.setIgnoreDefaultExcludes(false); 
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] {"cId"});
		jsonConfig.registerJsonValueProcessor(Date.class, new JsDateJsonValueProcessor()); 
		//总支出
		List<Expense> array2 = service.queryExpense(keyword);
		Map list=new HashMap();
		list.put("list1", array1);
		list.put("list2", array2);
		JSONArray result = JSONArray.fromObject(list);
		PrintWriter out = response.getWriter();  
		System.out.println(result);
		out.println(result);  
		out.flush();  
		out.close();   
	}
	//	根据所选择的起始月份和结束月份，按月统计总收入变化和总支出变化
	@RequestMapping(value="/mchart",method={RequestMethod.GET})
	public String  toreportm(Model model){
		return "report/monthreport";
	}
	@RequestMapping(value="/mchart")
	public void  doreportm(HttpServletRequest request, HttpServletResponse response) throws IOException
	{	
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		response.setContentType("text/html;charset=utf-8");  
		String keyword=request.getParameter("keyword");
		String keyword2=request.getParameter("keyword2");

		//总收入
		List<Account> array1 = service.queryAccount(keyword,keyword2);
		JsonConfig jsonConfig = new JsonConfig(); 
		jsonConfig.setIgnoreDefaultExcludes(false); 
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] {"cId"});
		jsonConfig.registerJsonValueProcessor(Date.class, new JsDateJsonValueProcessor()); 
		//总支出
		List<Expense> array2 = service.queryExpense(keyword,keyword2);
		Map list=new HashMap();
		list.put("list1", array1);
		list.put("list2", array2);
		JSONArray result = JSONArray.fromObject(list);
		PrintWriter out = response.getWriter();  
		System.out.println(result);
		out.println(result);  
		out.flush();  
		out.close();   
	}
	//	可以根据所选择的年份和某一员工，按月统计该员工本年创造的业绩和工资变化，
	@RequestMapping(value="/yuchart",method={RequestMethod.GET})
	public String  toyureport(Model model,@RequestParam(value="keyword", required=false,defaultValue="") String keyword){
		List<User> userList=service.queryUser(keyword);		
		addAttribute(model,"userList",userList,true);
		return "report/yearuserreport";
	}
	@RequestMapping(value="/yuchart")
	public void  doyureport(HttpServletRequest request, HttpServletResponse response) throws IOException
	{	
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		response.setContentType("text/html;charset=utf-8");  
		String keyword=request.getParameter("keyword");
		String keyword2=request.getParameter("keyword2");
		System.out.println(keyword);
		System.out.println(keyword2);
		//本年创造的业绩
		List<Account> array1 = service.queryAccountUser(keyword,keyword2);
		JsonConfig jsonConfig = new JsonConfig(); 
		jsonConfig.setIgnoreDefaultExcludes(false); 
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] {"cId"});
		jsonConfig.registerJsonValueProcessor(Date.class, new JsDateJsonValueProcessor()); 
		//总支出
		List<UserSalary> array2 = service.querySalary(keyword,keyword2);
		Map list=new HashMap();
		list.put("list1", array1);
		list.put("list2", array2);
		JSONArray result = JSONArray.fromObject(list);
		PrintWriter out = response.getWriter();  
		System.out.println(result);
		out.println(result);  
		out.flush();  
		out.close();   
	}
	//根据所选择的起始月份和结束月份以及某一员工，按月统计该员工创造的业绩和工资变化
	@RequestMapping(value="/muchart",method={RequestMethod.GET})
	public String  tomureport(Model model,@RequestParam(value="keyword", required=false,defaultValue="") String keyword){
		List<User> userList=service.queryUser(keyword);		
		addAttribute(model,"userList",userList,true);
		return "report/monthuserreport";
	}
	@RequestMapping(value="/muchart")
	public void  domureport(HttpServletRequest request, HttpServletResponse response) throws IOException
	{	
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		response.setContentType("text/html;charset=utf-8");  
		String keyword=request.getParameter("keyword");
		String keyword2=request.getParameter("keyword2");
		String keyword3=request.getParameter("keyword3");
		System.out.println(keyword);
		System.out.println(keyword2);
		System.out.println(keyword3);
		//本年创造的业绩
		List<Account> array1 = service.queryAccountUser(keyword,keyword2,keyword3);
		JsonConfig jsonConfig = new JsonConfig(); 
		jsonConfig.setIgnoreDefaultExcludes(false); 
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(new String[] {"cId"});
		jsonConfig.registerJsonValueProcessor(Date.class, new JsDateJsonValueProcessor()); 
		//总支出
		List<UserSalary> array2 = service.querySalary(keyword,keyword2,keyword3);
		Map list=new HashMap();
		list.put("list1", array1);
		list.put("list2", array2);
		JSONArray result = JSONArray.fromObject(list);
		PrintWriter out = response.getWriter();  
		System.out.println(result);
		out.println(result);  
		out.flush();  
		out.close();   
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
