package com.xlfd.usermanage.controller;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xlfd.common.model.Boss;
import com.xlfd.usermanage.service.BossService;

@Controller
@SessionAttributes("bossid")
@RequestMapping(value="/boss")

public class BossController {
	@Autowired//�Զ�ע���ʶ
	@Qualifier(value="bossService")//�����Զ�ע�����Դ
	private BossService service;
	@RequestMapping(value="/login")
	public String togetBoss(HttpServletRequest request, HttpServletResponse response,Model model){
		addAttribute(model,"boss_name",new Boss(),false);
		return "/bossLogin/login";
	}
	@RequestMapping(value="/login",method={RequestMethod.POST})
	public void getBoss(ModelMap map,HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�趨���� 
		response.setCharacterEncoding("UTF-8");
		//��ʾ��json���͵�����
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("bossId");
		String password=request.getParameter("bossPwd");

		Boss boss = service.getBoss(username);
		System.out.println(boss);
		String getId = "";
		String getPwd = "";
		JSONObject json=new JSONObject();
		if(boss!=null){
			getId = boss.getBossId();
			getPwd = boss.getBossPwd();
			if(username.equals(getId)&&password.equals(getPwd)){
				map.addAttribute("bossid", boss.getBossId()); 
				System.out.println(boss);
				json.put("result","success");
			}else{
				json.put("result","error");
			}
		}else{
			json.put("result","error");
		}
		out.print(json);

		//ע�������request.getParameter("username")ȡ����data������Ǹ�json�����username,����<input>�����Ǹ���ͬ��passwordҲһ����
		//����
		System.out.println(json.get("result"));

	}

	//�޸�����
	@RequestMapping(value="/updateboss")
	public String toupdateBoss(HttpServletRequest request, HttpServletResponse response,Model model){
		addAttribute(model,"bossPwd",new Boss(),false);
		return "/bossLogin/changepwd";
	}
	@RequestMapping(value="/updateboss",method={RequestMethod.POST})
	public void updateBoss(@ModelAttribute("bossid")String bossid,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Boss boss = service.getBoss(bossid);
		System.out.println(boss);	
		String id=boss.getBossId();  
		String password=request.getParameter("bossPwd");
		boss.setBossPwd(password);
		boss.setBossId(id);
		service.updateBoss(boss);
		response.setCharacterEncoding("UTF-8");
		//��ʾ��json���͵�����
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		JSONObject json=new JSONObject();
		if(boss.getBossPwd().equals(password)){
			json.put("result","success");
		}else{
			json.put("result","error");
		}
		out.print(json);
	}
	
	@RequestMapping(value="/testboss",method={RequestMethod.POST})
	public void testBoss(@ModelAttribute("bossid")String bossid,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Boss boss = service.getBoss(bossid);
		System.out.println(boss);	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		JSONObject json=new JSONObject();
		String pwd = boss.getBossPwd();
		String password=request.getParameter("bossPwd1");
		if(password.equals(pwd)){
			json.put("result","success");
		}else{
			json.put("result","error");
		}
		out.print(json);
	}

	private void addAttribute(Model model,String attributeName,Object attributeValue,boolean replace){

		if(replace==true){

			model.addAttribute(attributeName,attributeValue);
		}else{
			//if�������ã��ж�attribute���Ƿ��н�Ҫ���ݵ�����
			if(!model.containsAttribute(attributeName)){
				model.addAttribute(attributeName,attributeValue);
			}
		}

	}
}
