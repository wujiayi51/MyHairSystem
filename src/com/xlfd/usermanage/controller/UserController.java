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
	@Autowired//�Զ�ע���ʶ
	@Qualifier(value="userService")//�����Զ�ע�����Դprivate UserService service;
	private UserService service;
	//���Ա��ʱ�ж�Ա�������Ƿ����
	@RequestMapping(value="/testuser",method={RequestMethod.POST})
	public void getClient(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�趨���� 
		response.setCharacterEncoding("UTF-8");
		//��ʾ��json���͵�����
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

 //ɾ��Ա��
 	//@PathVariable ע������ã���url�н�ȡ��Ӧ������Ϊ����������ֵ
 	@RequestMapping(value="/{u_id}/deleteuser")//{User_id}�Զ�ʶ�����ݿ������
 	public String deleteUser(@PathVariable(value="u_id") int uId){
 		service.deleteUser(uId);
 		return "redirect:/user/queryuser";
 	}
 	
 	//��ѯҪ�޸ĵ�Ա��
 	@RequestMapping(value="/{u_id}/updateuser",method={RequestMethod.GET})
 	public String toUpdateUser(@PathVariable(value="u_id") int uId,Model model){
 		User user4Update = service.getUser(uId);
 		addAttribute(model,"update_command ",user4Update,true);
 		return "/user/usermanage";
 	}

 	//@ModelAttribute(value="User_cotmmand")User User4Update ��ý����ϵ�User����
 	@RequestMapping(value="/{u_id}/updateuser",method={RequestMethod.POST})  
 	public String doUpdateUser(@PathVariable(value="u_id") int uId,Model model,@ModelAttribute(value="update_command")User User4Update){
 		User u = service.getUser(uId);
 		service.updateUser(User4Update,u);
 		return "redirect:/user/queryuser";
 	}
 	//��ְ����
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
 		addAttribute(model,"pagenum",pagenum,true);//����ǰ̨��ǰ̨ʹ��
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
