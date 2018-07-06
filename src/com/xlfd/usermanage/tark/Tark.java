package com.xlfd.usermanage.tark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xlfd.usermanage.service.UserService;

@Component
public class Tark {
	@Autowired//�Զ�ע���ʶ
	@Qualifier(value="userService")//�����Զ�ע�����Դprivate UserService service;
	private UserService service;
	//�޸�Ա���ȼ�	�ڼ����깤���Ժ�  ��50���޸�Ա���ȼ�
	//@Scheduled(cron="0 0 1 1 * ?")
	@Scheduled(cron="0 0 1 1 * ?")//ÿ���µ�1��1�㿪ʼ�޸ĵȼ�
	 	public void doUpdateUser(){
	 		System.out.println("�޸ĵȼ�������");
	 		service.updateUserGrade();
	 	}
}
