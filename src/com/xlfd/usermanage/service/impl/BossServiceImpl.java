package com.xlfd.usermanage.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.xlfd.common.model.Boss;
import com.xlfd.usermanage.dao.BossDao;
import com.xlfd.usermanage.service.BossService;

@Service(value="bossService")//@Repository注解的作用是注入来源标识
public class BossServiceImpl implements BossService {
	@Autowired//自动注入标识
	@Qualifier(value="bossDao")//配置自动注入的来源
	private BossDao dao;
	public Boss getBoss(String BossId) {
		// TODO Auto-generated method stub
		return dao.getBoss(BossId);
	}
	public void updateBoss(Boss Boss4Update) {
		// TODO Auto-generated method stub
		dao.updateBoss(Boss4Update);
	}
	
}
