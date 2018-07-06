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


import com.xlfd.common.model.Client;
import com.xlfd.common.model.Commodity;
import com.xlfd.usermanage.service.CommodityService;

@Controller
@RequestMapping(value="/commodity")
public class CommodityController {
	@Autowired//�Զ�ע���ʶ
	@Qualifier(value="commodityService")//�����Զ�ע�����Դprivate NewsService service;
	private CommodityService service;
	
	//��ѯ��Ʒ
	@RequestMapping(value="/querycommodity")	
	public String  queryCommodity(Model model,
			@RequestParam(value="keyword",required=false,defaultValue="") String keyword,
			@RequestParam(value="pageNum",required=false,defaultValue="1") int pageNum,
			@RequestParam(value="pageSize",required=false,defaultValue="8") int pageSize)
	{	

		List<Commodity> commodityList=service.queryCommodity(keyword,pageNum,pageSize);
		long pageCount= service.getCommodityPageCount(keyword,pageSize);
		System.out.println("k"+keyword);
		System.out.println("c"+commodityList);
		addAttribute(model,"pageCount",pageCount,true);		
		addAttribute(model,"commodityList",commodityList,true);
		addAttribute(model,"pageNum",pageNum,true);
		addAttribute(model,"pageSize",pageSize,true);
		addAttribute(model,"commodity_command",new Commodity(),false);
		return "commodity/commodity";
	}
	
	//������Ʒ
	@RequestMapping(value="/addcommodity",method={RequestMethod.GET})
	public String  toAddCommodity(Model model){
		addAttribute(model,"commodity_command", new Commodity(),false);
		return "commodity/commodity";
	}
	@RequestMapping(value="/addcommodity",method={RequestMethod.POST})
	public String  doAddCommodity(Model model,@ModelAttribute(value="commodity_command")Commodity c){
		service.saveCommodity(c);
		return "redirect:/commodity/querycommodity";
	}
	
	//ɾ����Ʒ
	@RequestMapping(value="/{com_id}/deletecommodity")
	public String deleteCommodity(@PathVariable(value="com_id") int comId){
		service.deleteCommodity(comId);
		return "redirect:/commodity/querycommodity";
	}
	
	//�޸���Ʒ
	@RequestMapping(value="/{id}/updatecommodity",method={RequestMethod.GET})
	public String  toUpdateCommodity(@PathVariable(value="id") int id,Model model){

		//����һ��Ҫ������  ��ת ����if��
		//if�����������������ж�Attribute���Ƿ��н�Ҫ���ݵ�����
		//if(!model.containsAttribute("news_command")){
		Commodity commodityUpdate=service.getCommodity(id);
		System.out.println("�޸���");
		addAttribute(model,"commodity_command",commodityUpdate,true);

		return "commodity/querycommodity";
	}
	
	@RequestMapping(value="/{id}/updatecommodity",method={RequestMethod.POST})
	public String doUpdateCommodity(@PathVariable(value="id") int comId,Model model,@ModelAttribute(value="update_command")Commodity comUpdate){
		comUpdate.setId(comId);
		service.updateCommodity(comUpdate);
		return "redirect:/commodity/querycommodity";
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
