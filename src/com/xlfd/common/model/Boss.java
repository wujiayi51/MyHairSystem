package com.xlfd.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//@Entity注解的作用：映射类的扫描
@Table(name="boss")//@Table()注解的作用：是将映射类与数据库表进行关联
public class Boss {
	@Id //主键标识
	@Column(name="boss_id")//将类中的成员变量与数据库中表的字段进行关联
	private String bossId;
	@Column(name="boss_pwd")
	private String bossPwd;
	public String getBossId() {
		return bossId;
	}
	public void setBossId(String bossId) {
		this.bossId = bossId;
	}
	public String getBossPwd() {
		return bossPwd;
	}
	public void setBossPwd(String bossPwd) {
		this.bossPwd = bossPwd;
	}

	
}
