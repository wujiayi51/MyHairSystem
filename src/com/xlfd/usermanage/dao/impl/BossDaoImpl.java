package com.xlfd.usermanage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import com.xlfd.common.dao.impl.BaseDaoImpl;
import com.xlfd.common.model.Boss;
import com.xlfd.usermanage.dao.BossDao;
@Repository(value="bossDao")//@Repository注解的作用是注入来源标识
public class BossDaoImpl extends BaseDaoImpl implements BossDao {
	public Boss getBoss(String BossId) {
		// TODO Auto-generated method stub
		return (Boss)this.getById(Boss.class, BossId);	//因为查找到的是Boss所以Object可以强转成Boss
	}
	public void updateBoss(Boss Boss4Update) {
		// TODO Auto-generated method stub
		update(Boss4Update);
	}
}
