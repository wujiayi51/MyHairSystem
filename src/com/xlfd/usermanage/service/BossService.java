package com.xlfd.usermanage.service;

import java.util.List;
import com.xlfd.common.model.Boss;

public interface BossService {
	public Boss getBoss(String BossId);
	public void updateBoss(Boss Boss4Update);

}
