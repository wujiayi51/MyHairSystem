package com.xlfd.usermanage.tark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xlfd.usermanage.service.UserService;

@Component
public class Tark {
	@Autowired//自动注入标识
	@Qualifier(value="userService")//配置自动注入的来源private UserService service;
	private UserService service;
	//修改员工等级	在计算完工资以后  后50秒修改员工等级
	//@Scheduled(cron="0 0 1 1 * ?")
	@Scheduled(cron="0 0 1 1 * ?")//每个月的1号1点开始修改等级
	 	public void doUpdateUser(){
	 		System.out.println("修改等级开启中");
	 		service.updateUserGrade();
	 	}
}
