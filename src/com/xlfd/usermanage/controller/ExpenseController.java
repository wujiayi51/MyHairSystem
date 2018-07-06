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


import com.xlfd.common.model.Expense;
import com.xlfd.usermanage.service.ExpenseService;


@Controller
@RequestMapping(value="/expense")
public class ExpenseController {
	@Autowired//自动注入标识
	@Qualifier(value="expenseService")
	private ExpenseService service;
	
	@RequestMapping(value="/addexpense",method={RequestMethod.GET})
	public String  doAddExpense(Model model){
		addAttribute(model,"expenselist", new Expense(),false);
		return "expense/expensemanage";
	}

	@RequestMapping(value="/addexpense",method={RequestMethod.POST})
	public String  doAddExpense(Model model,@ModelAttribute(value="expense_command")Expense expense){
		service.saveExpense(expense);
		return "redirect:/expense/queryexpense";
	}
	//查询支出账单
	@RequestMapping(value="/queryexpense")	
	public String  queryExpense(Model model,
	@RequestParam(value="keyword",required=false,defaultValue="") String keyword,
	@RequestParam(value="pageNum",required=false,defaultValue="1") int pageNum,
	@RequestParam(value="pageSize",required=false,defaultValue="8") int pageSize)
	{	
		List<Expense> expenseList=service.queryExpense(keyword,pageNum,pageSize);	
		long pageCount= service.getExpensePageCount(keyword,pageSize);
		addAttribute(model,"pageCount",pageCount,true);	
		
		addAttribute(model,"expense_list",expenseList,true);
		addAttribute(model,"pageNum",pageNum,true);
		System.out.println();
		return "expense/expensemanage";
	}
	//修改支出账单
		@RequestMapping(value="/{id}/updateExpense",method={RequestMethod.GET})
		public String  toUpdateClient(@PathVariable(value="id") int id,Model model){
			
			Expense expenseUpdate=service.getExpense(id);
			System.out.println("修改中");
			addAttribute(model,"updateExpense_command",expenseUpdate,true);	
			return "expense/queryExpense";
		}
		@RequestMapping(value="/{id}/updateExpense",method={RequestMethod.POST})
		public String  doUpdateExpense(@PathVariable(value="id") int id,Model model,@ModelAttribute(value="expense_command")Expense expenseUpdate){
			expenseUpdate.setId(id);
			System.out.println("sjdjfos");
			service.updateExpense(expenseUpdate);
			return "redirect:/expense/queryexpense";
		}
	//删除栏目
		@RequestMapping(value="/{id}/deteleExpense")
		public String deleteTopic(@PathVariable(value="id") int id){
			service.deleteExpense(id);
			return "redirect:/expense/queryexpense";
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
