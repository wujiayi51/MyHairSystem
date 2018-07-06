package com.xlfd.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//@Entityע������ã�ӳ�����ɨ��
@Table(name="boss")//@Table()ע������ã��ǽ�ӳ���������ݿ����й���
public class Boss {
	@Id //������ʶ
	@Column(name="boss_id")//�����еĳ�Ա���������ݿ��б���ֶν��й���
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
