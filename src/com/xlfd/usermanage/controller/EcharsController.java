package com.xlfd.usermanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.xlfd.common.model.Account;
import com.xlfd.common.model.Client;
import com.xlfd.common.model.Serve;
import com.xlfd.common.model.User;
import com.xlfd.usermanage.service.AccountService;



@Controller
@RequestMapping(value="/echars")
public class EcharsController {
	@Autowired//自动注入标识
	@Qualifier(value="accountService")//配置自动注入的来源private NewsService service;
	private AccountService service;
	
	@RequestMapping(value="/chart",method={RequestMethod.GET})
	public String  toqueryLinedate(Model model,@RequestParam(value="keyword",required=false,defaultValue="") String keyword){
		List<Serve> snameList=service.queryServe(keyword);
		addAttribute(model,"sname_list",snameList,true);
		System.out.println(snameList);
		return "report/severyearchars";
	}
	@RequestMapping(value="/chart")
	public void  doqueryLinedate(Model model,HttpServletRequest request, HttpServletResponse response,
		 @RequestParam(value="keyword",required=false,defaultValue="") String dateY,
		 @RequestParam(value="keyword2",required=false,defaultValue="") String selServe) throws IOException
		 {	
		   	System.out.println(123);
		    response.setHeader("Content-type", "textml;charset=UTF-8");  
		    response.setContentType("textml;charset=utf-8");  
		    String keyword=request.getParameter("keyword");   
		    String keyword2=request.getParameter("keyword2");
			System.out.println("date"+keyword);
			System.out.println(keyword2);	
		    List<Account> array = service.queryAccount(keyword,keyword2);
		    System.out.println(keyword2);
	        System.out.println("s"+array);
			JSONArray json = JSONArray.fromObject(array);
	        System.out.println(json.toString());
	        PrintWriter out = response.getWriter();  
	        System.out.println(json);
	        out.println(json);  
	        out.flush();  
	        out.close();
		}
	@RequestMapping(value="/monthchart",method={RequestMethod.GET})
	public String  toqueryMonthLinedate(Model model,@RequestParam(value="keyword",required=false,defaultValue="") String keyword){
		List<Serve> snameList=service.queryServe(keyword);
		addAttribute(model,"sname_list",snameList,true);
		
		return "report/severmonthchars";
	}
	@RequestMapping(value="/monthchart")
	public void  doqueryMonthLinedate(Model model,HttpServletRequest request, HttpServletResponse response,
			 @RequestParam(value="starttime",required=false,defaultValue="") String startmonth,
			 @RequestParam(value="utid",required=false,defaultValue="") String servename,
			 @RequestParam(value="endtime",required=false,defaultValue="") String endmonth) throws IOException
			 {	
			   	System.out.println(123);
			    response.setHeader("Content-type", "textml;charset=UTF-8");  
			    response.setContentType("textml;charset=utf-8"); 

			    String starttime=request.getParameter("startmonth");   
			    String endtime=request.getParameter("endmonth");
			    String utid=request.getParameter("servename");   
			    
				System.out.println("date"+starttime);
				System.out.println(endtime);				
				System.out.println(utid);	
			    List<Account> array = service.queryAccount(starttime,endtime,utid);
		        System.out.println("s"+array);
				JSONArray json = JSONArray.fromObject(array);
		        System.out.println(json.toString());
		        PrintWriter out = response.getWriter();  
		        System.out.println(json);
		        out.println(json);  
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
