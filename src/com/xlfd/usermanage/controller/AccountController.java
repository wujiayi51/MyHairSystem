package com.xlfd.usermanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xlfd.common.model.Account;
import com.xlfd.common.model.Client;
import com.xlfd.common.model.Serve;
import com.xlfd.common.model.User;
import com.xlfd.common.model.UtServe;

import com.xlfd.usermanage.service.AccountService;



@Controller
@RequestMapping(value="/account")
public class AccountController {
	@Autowired//�Զ�ע���ʶ
	@Qualifier(value="accountService")//�����Զ�ע�����Դprivate NewsService service;
	private AccountService service;
	
	@RequestMapping(value="/ajaxtest")
	public String togetclientcharge(HttpServletRequest request, HttpServletResponse response,Model model){
		addAttribute(model,"text",new Client(),false);
		return "account/accountmanage";
	}
	
	@RequestMapping(value="/ajaxtest",method={RequestMethod.POST})
	public void getaccountInfo(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
	    int sid=Integer.valueOf(request.getParameter("sid"));
	    Serve serve = service.getServe(sid);
	    //����һЩ���ݵ�jsp
	    //���ؼ���userList
	    List<UtServe> utserveList = serve.getUtserveLists();
	    int len = utserveList.size();
	    User user=null;
		Map map1=new HashMap();
		for(int i=0;i<len ;i++){
			List<User> userList = utserveList.get(i).getUtservebelongUserType().getUserList();
	    	int len2 = userList.size();
	    	for(int j=0;j<len2;j++){
	    		 int len3 = userList.size();
	    		 for(int x=0;x<len3;x++){
	    			user = userList.get(x);
	    			int uId = user.getuId();
	    			String uName = user.getuName();
	    			map1.put(uId, uName);
	    		 }
	    	}
		}
	    Map map=new HashMap();
	    //account������    
	    map.put("sName", serve.getsName());
	    map.put("sId", serve.getsId());
	    map.put("sPrice",String.valueOf(serve.getsPrice()));
	    map.put("map1",map1);
	    PrintWriter out=response.getWriter();
	    JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setIgnoreDefaultExcludes(false); //����Ĭ�Ϻ��� 
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//����ѭ������Ϊ����    ���json��ѭ��
        JSONArray ccjson = JSONArray.fromObject(map, jsonConfig);
        out.print(ccjson);
	}

	@RequestMapping(value="/ajaxClient")
	public void getaccount(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
	    PrintWriter out=response.getWriter();
        String cid=request.getParameter("cId");  
        System.out.println("�ؼ���"+cid);
	    List <Client> clientList= service.queryClient(cid);
	    Map map=new HashMap();
	 
	    //���õ������ݽ��д���
	    int len = clientList.size();
	    for(int i=0;i<len;i++){
	    	double cBala = clientList.get(i).getcBala();
	    	double cDiscount = clientList.get(i).getcDiscount();
	    	String cId = clientList.get(i).getcId();
	    	map.put("cId", cId);
	    	map.put("cBala", cBala);
	    	map.put("cDiscount", cDiscount);
	    	System.out.println(cId);
	    }

	    JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setIgnoreDefaultExcludes(false); //����Ĭ�Ϻ��� 
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//����ѭ������Ϊ����    ���json��ѭ��
        JSONArray ccjson = JSONArray.fromObject(map, jsonConfig); 
        out.print(ccjson);    
	}
	

	@RequestMapping(value="/user")
	public void getusertype(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
        String uukeyword=request.getParameter("uname");
	    List<User> UserList =service.queryUser(uukeyword) ; 
	    //����õ�������
	    Map map=new HashMap();
	    int len = UserList.size();
	    for(int i=0;i<len;i++){
	    	String utName = UserList.get(i).getBelongUserType().getUtName();
	    	double utScale = UserList.get(i).getBelongUserType().getUtScale();
	    	double utWeight = UserList.get(i).getBelongUserType().getUtWeight();
	    	map.put("utName", utName);
	    	map.put("utScale", utScale);
	    	map.put("utWeight", utWeight);
	    }
	    //�����������������json��ѭ��
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setIgnoreDefaultExcludes(false); //����Ĭ�Ϻ��� 
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//����ѭ������Ϊ����    ���json��ѭ��
        JSONArray json = JSONArray.fromObject(map, jsonConfig);  
        PrintWriter out=response.getWriter();
        out.print(json);    
	}
	
	//��ӽ��˵�����
	@RequestMapping(value="/addaccount",method={RequestMethod.GET})
	public String  toAddAccount(Model model){
		List<Serve> serveList = service.queryServe(null);
		List<User> userList = service.queryUser(null);
		addAttribute(model,"serve_list",serveList,true);
		addAttribute(model,"user_list",userList,true);
		addAttribute(model,"account_command", new Account(),false);
		return "account/accountmanage";
	}
	@RequestMapping(value="/check")
	public void doAccountList(HttpServletRequest req, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		//��������
		 String doList=req.getParameter("doList");
		 System.out.println(doList);
		//�Ȱ��ַ���ת��JSONArray
		JSONArray jsonArray = JSONArray.fromObject(doList);
		//����jsonArray
		Iterator<Object> it = jsonArray.iterator();
		 List<Account> list=new ArrayList<Account>();
		 while (it.hasNext()) {
		     JSONObject ob = (JSONObject) it.next();//ȡ��ÿ��JSONObject����
		     Account ac = null;
		     ac=new Account();
		     //int uid = Integer.valueOf((String) ob.get("uId"));.parseInt
		    // int sid = Integer.valueOf((String) ob.get("sId")) ;
		     int uid = Integer.parseInt((String) ob.get("uId"));
		     int sid = Integer.parseInt((String) ob.get("sId")) ;
		     String cid = (String) ob.get("cId");
		     double realmoney = Double.valueOf((String) ob.get("realMoney")) ;
		     double shouldmoney = Double.valueOf((String) ob.get("shouldMoney"));
		     ac.setRealMoney(realmoney);
		     ac.setShouldMoney(shouldmoney);
		     if(cid != null && cid.length() != 0){
		    	  ac.setBelongClient(service.getClient(cid));
		     }
		     ac.setBelongServe(service.getServe(sid));
		     ac.setBelongUser(service.getUser(uid));
		     if(ac!=null){
		         service.saveAccount(ac);
		    }
		     //�޸Ļ�Ա�����
		     if(cid != null && cid.length() != 0){
		    	 service.updateClient(cid,realmoney);
		     }		     
		 }
	    //�����������������json��ѭ��
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setIgnoreDefaultExcludes(false); //����Ĭ�Ϻ��� 
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//����ѭ������Ϊ����    ���json��ѭ��
        JSONObject json=new JSONObject();
        json.put("result","success");
        PrintWriter out=response.getWriter();
        out.print(json); 
       
	}
	//��ѯÿ���˵�
		@RequestMapping(value="/queryaccountbill")	
		public String  queryAccountBill(Model model,
		@RequestParam(value="keyword",required=false,defaultValue="") String keyword,
		@RequestParam(value="pageNum",required=false,defaultValue="1") int pageNum,
		@RequestParam(value="pageSize",required=false,defaultValue="8") int pageSize)
		{	
			List<Account> AccountList=service.queryAccount(keyword,pageNum,pageSize);
			long pageCount= service.getAccountPageCount(keyword,pageSize);
			addAttribute(model,"pageCount",pageCount,true);		
			addAttribute(model,"AccountList",AccountList,true);
			addAttribute(model,"pageNum",pageNum,true);
			System.out.println(keyword);
			System.out.println(AccountList);
			return "account/accountbill";
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
