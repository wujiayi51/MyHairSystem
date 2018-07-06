package com.xlfd.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//@Entityע������ã�ӳ�����ɨ��
@Table(name="commodity")
public class Commodity {
	@Id //������ʶ
	@GeneratedValue(strategy=GenerationType.AUTO) //���������ֶ�
	@Column(name="id")//�����еĳ�Ա���������ݿ��б���ֶν��й���
	private int id;
	@Column(name="com_id")
	private String comId;
	@Column(name="com_num")
	private String comNum;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getComNum() {
		return comNum;
	}
	public void setComNum(String comNum) {
		this.comNum = comNum;
	}
}
