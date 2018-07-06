package com.xlfd.usermanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xlfd.common.model.Client;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UserType;
import com.xlfd.usermanage.service.UserService;



@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired//自动注入标识
	@Qualifier(value="userService")//配置自动注入的来源private UserService service;
	private UserService service;
	//添加员工时判断员工工号是否存在
	@RequestMapping(value="/testuser",method={RequestMethod.POST})
	public void getClient(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//设定编码 
		response.setCharacterEncoding("UTF-8");
		//表示是json类型的数据
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
	    String uId=request.getParameter("uId");
	    User user = service.getUser(Integer.valueOf(uId));
	    System.out.println(user);
	    JSONObject json=new JSONObject();
	    if(user!=null){
			json.put("result","error");
		}else{
			json.put("result","success"); 
	    }
	    out.print(json);
	    out.flush();  
		out.close();    
	}
	
	@RequestMapping(value="/adduser",method={RequestMethod.GET})
	public String  toAddUser(Model model){
		addAttribute(model,"user_command", new User(),false);
		return "user/usermanage";
	}
	
	@RequestMapping(value="/adduser",method={RequestMethod.POST})
	public String  doAddUser(Model model,@ModelAttribute(value="user_command")User u,HttpSession session){
		ServletContext context = session.getServletContext();
		String userImgUploadPath = context.getRealPath("/upload/userImg");
		service.saveUser(u,userImgUploadPath);
		return "redirect:/user/queryuser";
	}

 //删除员工
 	//@PathVariable 注解的作用：从url中截取对应部分作为方法参数的值
 	@RequestMapping(value="/{u_id}/deleteuser")//{User_id}自动识别数据库的主键
 	public String deleteUser(@PathVariable(value="u_id") int uId){
 		service.deleteUser(uId);
 		return "redirect:/user/queryuser";
 	}
 	
 	//查询要修改的员工
 	@RequestMapping(value="/{u_id}/updateuser",method={RequestMethod.GET})
 	public String toUpdateUser(@PathVariable(value="u_id") int uId,Model model){
 		User user4Update = service.getUser(uId);
 		addAttribute(model,"update_command ",user4Update,true);
 		return "/user/usermanage";
 	}

 	//@ModelAttribute(value="User_cotmmand")User User4Update 获得界面上的User对象；
 	@RequestMapping(value="/{u_id}/updateuser",method={RequestMethod.POST})  
 	public String doUpdateUser(@PathVariable(value="u_id") int uId,Model model,@ModelAttribute(value="update_command")User User4Update){
 		User u = service.getUser(uId);
 		service.updateUser(User4Update,u);
 		return "redirect:/user/queryuser";
 	}
 	//离职办理
 	@RequestMapping(value="/{u_id}/leaveuser",method={RequestMethod.POST})  
 	public String doLeaveUser(@PathVariable(value="u_id") int uId,Model model,
 			@RequestParam(value="state")int state,
 			@RequestParam(value="leaveDate")String leaveTimeStr){
 		User u = service.getUser(uId);
 		service.updateUser(u,state,leaveTimeStr);
 		return "redirect:/user/queryuser";
 	}
 	
 	@RequestMapping(value="/queryuser") 
 	public String queryUser(Model model,
 			@RequestParam(value="keyword", required=false,defaultValue="") String keyword,
 			@RequestParam(value="pagenum", required=false,defaultValue="1") int pagenum,
 			@RequestParam(value="pagesize", required=false,defaultValue="2") int pagesize){
 		List<User> userList = service.queryUser(keyword,pagenum,pagesize);
 		List<UserType> usertypeList = service.queryAllType();
 		long pagecount = service.getUserPageCount(keyword, pagesize);
 		addAttribute(model,"usertype_list",usertypeList,true);	
 		addAttribute(model,"user_list",userList,true);	
 		addAttribute(model,"pagenum",pagenum,true);//传到前台，前台使用
 		addAttribute(model,"pagesize",pagesize,true);
 		addAttribute(model,"pagecount",pagecount,true);
 		addAttribute(model,"user_command",new User(),false);
 		addAttribute(model,"update_command",new User(),false);
 		addAttribute(model,"leave_command",new User(),false);

 		return "/user/usermanage";
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
