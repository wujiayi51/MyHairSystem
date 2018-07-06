package com.xlfd.usermanage.dao;


import java.util.List;
import com.xlfd.common.model.Boss;

public interface BossDao {
	public Boss getBoss(String BossId);
	public void updateBoss(Boss Boss4Update);
	
}
