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
	@Autowired//自动注入标识
	@Qualifier(value="commodityService")//配置自动注入的来源private NewsService service;
	private CommodityService service;
	
	//查询商品
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
	
	//增加商品
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
	
	//删除商品
	@RequestMapping(value="/{com_id}/deletecommodity")
	public String deleteCommodity(@PathVariable(value="com_id") int comId){
		service.deleteCommodity(comId);
		return "redirect:/commodity/querycommodity";
	}
	
	//修改商品
	@RequestMapping(value="/{id}/updatecommodity",method={RequestMethod.GET})
	public String  toUpdateCommodity(@PathVariable(value="id") int id,Model model){

		//这里一定要传数据  跳转 不用if了
		//if语句在这里的作用是判断Attribute中是否有将要传递的数据
		//if(!model.containsAttribute("news_command")){
		Commodity commodityUpdate=service.getCommodity(id);
		System.out.println("修改中");
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
