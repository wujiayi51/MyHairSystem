package com.xlfd.usermanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xlfd.common.model.Boss;
import com.xlfd.common.model.Client;
import com.xlfd.common.model.ClientCharge;
import com.xlfd.common.model.ClientGrade;
import com.xlfd.usermanage.service.ClientService;

@Controller
@RequestMapping(value="/client")
public class ClientController {
	@Autowired
	@Qualifier(value="clientService")
	private ClientService service;
	//���ӻ�Ա
	@RequestMapping(value="/addclient",method={RequestMethod.GET})
	public String  doAddClient(Model model){
		addAttribute(model,"client_command", new Client(),false);
		return "client/clientmanage";
	}

	
	@RequestMapping(value="/addclient",method={RequestMethod.POST})
	public String  doAddClient(Model model,@ModelAttribute(value="client_command")Client c) throws UnsupportedEncodingException{
		service.saveClient(c);
		return "redirect:/client/queryclient";
	}
	
	//��ӻ�Աʱ�жϻ�Ա�Ƿ����
	@RequestMapping(value="/testclient",method={RequestMethod.POST})
	public void getClient(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�趨���� 
		response.setCharacterEncoding("UTF-8");
		//��ʾ��json���͵�����
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
	    String cId=request.getParameter("cId");
	    Client client = service.getClient(cId);
	    JSONObject json=new JSONObject();
	    if(client!=null){
			json.put("result","error");
		}else{
			json.put("result","success"); 
	    }
	    out.print(json);
	    out.flush();  
		out.close();   
		 
	}
	//��ѯ��Ա
	@RequestMapping(value="/queryclient")	
	public String  queryClient(Model model,
			@RequestParam(value="keyword",required=false,defaultValue="") String keyword,
			@RequestParam(value="pageNum",required=false,defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",required=false,defaultValue="8") int pageSize)
	{	

		List<Client> clientList=service.queryClient(keyword,pageNum,pageSize);	
		long pageCount= service.getClientPageCount(keyword,pageSize);
		addAttribute(model,"pageCount",pageCount,true);		
		addAttribute(model,"clientList",clientList,true);
		addAttribute(model,"pageNum",pageNum,true);
		addAttribute(model,"client_command",new Client(),false);
		return "client/clientmanage";
	}

	//�޸Ļ�Ա
	@RequestMapping(value="/{c_id}/updateclient",method={RequestMethod.GET})
	public String  toUpdateClient(@PathVariable(value="c_id") String cId,Model model){
		Client clientUpdate=service.getClient(cId);
		addAttribute(model,"client_command",clientUpdate,true);

		return "client/queryclient";
	}


	@RequestMapping(value="/{c_id}/updateclient",method={RequestMethod.POST})
	public String  doUpdateClient(@PathVariable(value="c_id") String cId,Model model,@ModelAttribute(value="update_command")Client clientUpdate){
		clientUpdate.setcId(cId);
		System.out.println("sjdjfos");
		service.updateClient(clientUpdate);
		return "redirect:/client/queryclient";
	}

	//��ѯ��Ա�ȼ�
	@RequestMapping(value="/queryclientgrade")	
	public String  queryClientGrade(Model model,
			@RequestParam(value="keyword",required=false,defaultValue="") String keyword,
			@RequestParam(value="pageNum",required=false,defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",required=false,defaultValue="8") int pageSize)
	{	

		List<ClientGrade> clientgradeList=service.queryClientGrade(keyword,pageNum,pageSize);
		//model.addAttribute("news_list", newsList);
		long pageCount= service.getClientGradePageCount(keyword,pageSize);
		addAttribute(model,"pageCount",pageCount,true);		
		addAttribute(model,"clientgradeList",clientgradeList,true);
		addAttribute(model,"pageNum",pageNum,true);
		addAttribute(model,"client_command",new ClientGrade(),false);
		return "client/clientgrade";
	}

	//��Ա�б��ֵ
	@RequestMapping(value="/clientcharge?id={c_id}" ,method={RequestMethod.GET})
	public String togetccharge(@PathVariable(value="c_id") String cid,HttpServletRequest request, HttpServletResponse response,Model model){
		addAttribute(model,"c_id",new Client(),true);
		return "client/clientcharge";
	}
	//��Ա��ֵҳ��Ajax���ݻ�ȡ
	@RequestMapping(value="/clientcharge")
	public String togetclientcharge(HttpServletRequest request, HttpServletResponse response,Model model){
		addAttribute(model,"c_Id",new Client(),false);
		return "client/clientcharge";
	}
	@RequestMapping(value="/clientcharge",method={RequestMethod.POST})
	public void getclientcharge(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		String keyword=request.getParameter("cId");
		System.out.println("k"+keyword);
		Client array = service.getClient(keyword);
		System.out.println("c"+array);
		JsonConfig jsonConfig = new JsonConfig(); 
		jsonConfig.setIgnoreDefaultExcludes(false); 
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		//jsonConfig.setExcludes(new String[] {"cTime"});
		jsonConfig.registerJsonValueProcessor(Date.class, new JsDateJsonValueProcessor()); 
		JSONArray json = JSONArray.fromObject(array, jsonConfig);
		PrintWriter out=response.getWriter();
		System.out.println(json.toString());
		if(array != null){
			out.print(json);
			System.out.println("k"+keyword);
			System.out.println("c"+array);
			System.out.println(json.toString());
		}else{
			out.print("result:erro");
		}
	}
	//ִ�г�ֵ����
	@RequestMapping(value="/doclientcharge",method={RequestMethod.GET})
	public String  toClientCharge(Model model){
		
		addAttribute(model,"charge_command", new ClientCharge(),false);
		return "client/clientcharge";
	}
	@RequestMapping(value="/doclientcharge",method={RequestMethod.POST})
	public String  doClientCharge(Model model,HttpServletRequest request, HttpServletResponse response,@ModelAttribute(value="charge_command")ClientCharge c){
		
		//����clientcharge��ֵ��¼
		service.saveClientCharge(c);
		//����client�������ȼ�
		Client client = service.getClient(c.getBelongClient().getcId());
		String cDiscount=request.getParameter("cgId");
		System.out.println(cDiscount);
		client.setBelongClientGrade(service.getClientGrade(cDiscount));
		service.saveClientCharge(client,c.getChargeMoney());

		return "redirect:/client/queryclient";
	}

	//��Ա��ֵ��¼
	@RequestMapping(value="/querychargerecord")	
	public String  queryChargeRecord(Model model,
			@RequestParam(value="keyword",required=false,defaultValue="") String keyword,
			@RequestParam(value="pageNum",required=false,defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",required=false,defaultValue="8") int pageSize)
	{	

		List<ClientCharge> clientcharge=service.queryClientCharge(keyword,pageNum,pageSize);
		//model.addAttribute("news_list", newsList);
		long pageCount= service.getClientChargePageCount(keyword,pageSize);
		addAttribute(model,"pageCount",pageCount,true);		
		addAttribute(model,"clientcharge",clientcharge,true);
		addAttribute(model,"pageNum",pageNum,true);
		addAttribute(model,"client_command",new ClientCharge(),false);
		return "client/chargerecord";
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
