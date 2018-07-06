package com.xlfd.usermanage.tark;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xlfd.common.model.UserSalary;
import com.xlfd.usermanage.service.UserSalaryService;

//计算工资
@Component
public class AddSalary {
	@Autowired//自动注入标识
	@Qualifier(value="usersalaryService")//配置自动注入的来源private UserService service;
	private UserSalaryService service;
	
	//@Scheduled(cron="0 0 23 31 * ?")
	public void  doAddUserSalary(){
		System.out.println("1.添加薪资开启");
		service.saveAutoCalcUserSalary();
	}
}
