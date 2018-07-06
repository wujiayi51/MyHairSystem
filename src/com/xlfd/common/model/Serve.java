package com.xlfd.common.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity//@Entityע������ã�ӳ�����ɨ��
@Table(name="serve")
public class Serve {
	@Id //������ʶ
	@GeneratedValue(strategy=GenerationType.AUTO) //���������ֶ�
	@Column(name="s_id")//�����еĳ�Ա���������ݿ��б���ֶν��й���
	private int sId;

	@Column(name="s_name")
	private String sName;
	@Column(name="s_price")
	private int sPrice;
	@Column(name="s_produce")
	private String sProduce;
	@OneToMany(mappedBy="belongServe",cascade ={CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	private List<Account> accountList;
	@OneToMany(mappedBy="utservebelongServe",cascade ={CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	private List<UtServe> utserveLists;
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public int getsPrice() {
		return sPrice;
	}
	public void setsPrice(int sPrice) {
		this.sPrice = sPrice;
	}
	public String getsProduce() {
		return sProduce;
	}
	public void setsProduce(String sProduce) {
		this.sProduce = sProduce;
	}
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	public List<UtServe> getUtserveLists() {
		return utserveLists;
	}
	public void setUtserveLists(List<UtServe> utserveLists) {
		this.utserveLists = utserveLists;
	}
	
}
