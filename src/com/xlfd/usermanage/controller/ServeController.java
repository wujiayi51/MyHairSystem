package com.xlfd.usermanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import com.xlfd.common.model.Client;
import com.xlfd.common.model.Serve;
import com.xlfd.common.model.UserType;
import com.xlfd.common.model.UtServe;
import com.xlfd.usermanage.service.ServeService;

@Controller
@RequestMapping(value="/serve")
public class ServeController {
	@Autowired//自动注入标识
	@Qualifier(value="serveService")//配置自动注入的来源private NewsService service;
	private ServeService service;
	//添加服务项目
	@RequestMapping(value="/addserve",method={RequestMethod.GET})
	public String  toAddServe(Model model){
		addAttribute(model,"serve_command", new Serve(),false);
		return "serve/servelist";
	}

	@RequestMapping(value="/addserve",method={RequestMethod.POST})
	public void  doAddServe(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//ajax传参
		String sname = request.getParameter("sname");
		Integer sprice=Integer.valueOf(request.getParameter("sprice"));  
		String sproduce = request.getParameter("sproduce");
		Serve ser = new Serve();
		//添加项目
		service.saveServe(ser,sname,sprice,sproduce);
		String[] array = request.getParameterValues("array[]");
		for (String utId : array) {
			UserType ut = service.getUserType(Integer.valueOf(utId));
			UtServe uts = new UtServe();
			//添加utserve
			service.saveUtServe(uts,ser,ut);
	    }
		response.setCharacterEncoding("UTF-8");
		//表示是json类型的数据
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		JSONObject json=new JSONObject();
		if(ser!=null){
		       json.put("result","success");
		}else{
		       json.put("result","error");
		}
		out.print(json);
	    out.flush();  
		out.close();   
	}
	//服务项目列表
	@RequestMapping(value="/queryserve")	
	public String  queryServe(Model model,
			@RequestParam(value="keyword",required=false,defaultValue="") String keyword,
			@RequestParam(value="pageNum",required=false,defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",required=false,defaultValue="8") int pageSize)
	{	

		List<Serve> serveList=service.queryServe(keyword,pageNum,pageSize);
		List<UserType> utList=service.queryusertype();
		long pageCount= service.getServePageCount(keyword,pageSize);
		addAttribute(model,"pageCount",pageCount,true);		
		addAttribute(model,"serveList",serveList,true);
		addAttribute(model,"utList",utList,true);
		addAttribute(model,"pageNum",pageNum,true);
		addAttribute(model,"serve_command",new Serve(),false);
		return "serve/servelist";
	}
	//修改服务项目
	@RequestMapping(value="/{s_id}/updateserve",method={RequestMethod.GET})
	public String  toUpdateServe(@PathVariable(value="s_id") int sId,Model model){
		Serve serveUpdate=service.getServe(sId);
		addAttribute(model,"serve_command",serveUpdate,true);
		return "serve/queryserve";
	}
	@RequestMapping(value="/{s_id}/updateserve",method={RequestMethod.POST})
	public void  doUpdateServe(@PathVariable(value="s_id") int sId,HttpServletRequest request, HttpServletResponse response,Model model) throws IOException{
		//修改项目服务
		String[] array = request.getParameterValues("array[]");
		String sname = request.getParameter("sname");
		Integer sprice=Integer.valueOf(request.getParameter("sprice"));  
		String sproduce = request.getParameter("sproduce");
		Serve serveUpdate= service.getServe(sId);
		serveUpdate.setsId(sId);
		serveUpdate.setsName(sname);
		serveUpdate.setsPrice(sprice);
		serveUpdate.setsProduce(sproduce);
		//System.out.println(sId);
		service.updateServe(serveUpdate);
		//删除utserve里的sId的list
		List<UtServe> utservelist = service.queryUtServe(sId);
		//System.out.println(utservelist);
		for(int i=0;i<utservelist.size();i++){
			int id = utservelist.get(i).getId();
				 service.deleteutslist(id);      
		}
		System.out.println("ajax"+array);
		for (String utName : array) {
			List<UserType> ut = service.getUserType(utName);
			UtServe uts = new UtServe();
			//添加utserve
			service.saveUtServe(uts,serveUpdate,ut.get(0));
	    }
		response.setCharacterEncoding("UTF-8");
		//表示是json类型的数据
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		JSONObject json=new JSONObject();
		if(utservelist!=null){
	       json.put("result","success");
		}else{
		       json.put("result","error");
		}
		out.print(json);
	    out.flush();  
		out.close(); 
	}
	//删除项目
	@RequestMapping(value="/{s_id}/deleteserve")
	public String deleteServe(@PathVariable(value="s_id") int sId){
		service.deleteServe(sId);
		return "redirect:/serve/queryserve";
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
