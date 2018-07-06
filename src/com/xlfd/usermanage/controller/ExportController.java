package com.xlfd.usermanage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xlfd.common.model.Account;
import com.xlfd.common.model.ExportExcelUtils;
import com.xlfd.usermanage.service.AccountService;




@Controller
@RequestMapping(value="/export")
public class ExportController {
	
	@Autowired//�Զ�ע���ʶ
	@Qualifier(value="accountService")

	private AccountService service;
	@RequestMapping(value="/excel")
    public ModelAndView exportExcel(HttpServletRequest request,HttpServletResponse response, Model model,
    		@RequestParam(value="keyword",required=false,defaultValue="") String keyword,
    		@RequestParam(value="pageNum",required=false,defaultValue="1") int pageNum,
    		@RequestParam(value="pageSize",required=false,defaultValue="8") int pageSize){  
        try {  
        	
        	   List<Account> userlist = service.queryAccount(keyword,pageNum,pageSize);  
            // ����û�����  
           
            String title ="ÿ���˵�";  
            String[] rowsName=new String[]{"���","��ˮ����","Ӧ�����","ʵ�����","������Ŀ","��Ա���","Ա������","����ʱ��"};  
            List<Object[]>  dataList = new ArrayList<Object[]>();  
            Object[] objs = null;  
            for (int i = 0; i < userlist.size(); i++) {  
            	Account a = userlist.get(i); 
            	objs = new Object[rowsName.length];
            	 objs[0] = i;  
                 objs[1] = a.getFlowNumber();
                 objs[2] = a.getShouldMoney(); 
                 objs[3] = a.getRealMoney() ;
                 objs[4] = a.getBelongServe().getsName(); 
               
                 if(a.getBelongClient()==null){
                	 objs[5] = "�ǻ�Ա";
                 }else{
                	  objs[5] = a.getBelongClient().getcId(); 
                 }
                 objs[6] = a.getBelongUser().getuId();
                 objs[7] = a.getPayTime();
                 dataList.add(objs);  
              
            }  
            //   
            ExportExcelUtils ex =new ExportExcelUtils(title, rowsName, dataList,response);  
            ex.exportData();  
              
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        return null;  
    }   

}
