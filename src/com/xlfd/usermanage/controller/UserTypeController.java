package com.xlfd.usermanage.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xlfd.common.model.User;
import com.xlfd.common.model.UserType;
import com.xlfd.usermanage.service.UserTypeService;



@Controller
@RequestMapping(value="/usertype")
public class UserTypeController {
	@Autowired//自动注入标识
	@Qualifier(value="userTypeService")//配置自动注入的来源private UserService service;
	private UserTypeService service;
	
	@RequestMapping(value="/addusertype",method={RequestMethod.GET})
	public String  toAddUser(Model model){
		addAttribute(model,"usertype_command", new UserType(),false);
		return "user/typemanage";
	}
	
	@RequestMapping(value="/addusertype",method={RequestMethod.POST})
	public String  doAddUserType(Model model,@ModelAttribute(value="usertype_command")UserType ut){
		service.saveUserType(ut);
		return "redirect:/usertype/queryusertype";
	}
	
 	@RequestMapping(value="/{ut_id}/deleteusertype")//{User_id}自动识别数据库的主键
 	public String deleteUserType(@PathVariable(value="ut_id") int utId){
 		service.deleteUserType(utId);
 		return "redirect:/usertype/queryusertype";
 	}
 	

 	@RequestMapping(value="/{ut_id}/updateusertype",method={RequestMethod.GET})
 	public String toUpdateUserType(@PathVariable(value="ut_id") int utId,Model model){
 		UserType userType4Update = service.getUserType(utId);
 		addAttribute(model,"updatetype_command ",userType4Update,true);
 		return "/user/typemanage";
 	}
 	//修改新闻
 	//@ModelAttribute(value="User_cotmmand")User UserType4Update 获得界面上的User对象；
 	@RequestMapping(value="/{ut_id}/updateusertype",method={RequestMethod.POST})  
 	public String doUpdateUserType(@PathVariable(value="ut_id") int utId,Model model,@ModelAttribute(value="updatetype_command")UserType UserType4Update){
 		UserType4Update.setUtId(utId);
 		service.updateUserType(UserType4Update);
 		return "redirect:/usertype/queryusertype";
 	}
 	
 	@RequestMapping(value="/queryusertype") 
 	public String queryUserType(Model model,
 			@RequestParam(value="keyword", required=false,defaultValue="") String keyword,
 			@RequestParam(value="pagenum", required=false,defaultValue="1") int pagenum,
 			@RequestParam(value="pagesize", required=false,defaultValue="8") int pagesize){
 		List<UserType> userTypeList = service.queryUserType(keyword,pagenum,pagesize);
 		long pagecount = service.getUserTypePageCount(keyword, pagesize);
 		System.out.print(keyword);
 		addAttribute(model,"usertype_list",userTypeList,true);	
 		addAttribute(model,"pagenum",pagenum,true);//传到前台，前台使用
 		addAttribute(model,"pagesize",pagesize,true);
 		addAttribute(model,"pagecount",pagecount,true);
 		addAttribute(model,"usertype_command",new UserType(),false);
 		addAttribute(model,"updatetype_command",new UserType(),false);

 		return "/user/typemanage";
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
