package com.xlfd.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity//@Entityע������ã�ӳ�����ɨ��
@Table(name="utserve")
public class UtServe {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) //���������ֶ�
	@Column(name="id")
	private int id;	
	@ManyToOne//����name�Ǳ��ࣨ�������ݿ���ֶ�����referencedColumnName �ǹ����ࣨ�������ݿ��еĹ����ֶ��� 
	@JoinColumn(name ="ut_id",referencedColumnName="ut_id")
	private UserType utservebelongUserType;
	@ManyToOne//����name�Ǳ��ࣨ�������ݿ���ֶ�����referencedColumnName �ǹ����ࣨ�������ݿ��еĹ����ֶ��� 
	@JoinColumn(name ="s_id",referencedColumnName="s_id")
	private Serve utservebelongServe;
	public UserType getUtservebelongUserType() {
		return utservebelongUserType;
	}
	public void setUtservebelongUserType(UserType utservebelongUserType) {
		this.utservebelongUserType = utservebelongUserType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Serve getUtservebelongServe() {
		return utservebelongServe;
	}
	public void setUtservebelongServe(Serve utservebelongServe) {
		this.utservebelongServe = utservebelongServe;
	}

	
}
