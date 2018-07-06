package com.xlfd.common.model;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity//@Entity注解的作用：映射类的扫描
@Table(name="client")//@Table()注解的作用：是将映射类与数据库表进行关联
public class Client {
	@Id //主键标识
	@Column(name="c_id")
	private String cId;
	@Column(name="c_sex")
	private String cSex;
	@Column(name="c_name")
	private String cName;
	@Column(name="c_discount",columnDefinition="double(10,2) default '0.00'")
	private double cDiscount;
	@Column(name="c_bala",columnDefinition="double(10,2) default '0.00'")
	private double cBala;
	@Column(name="c_state")
	private double cState;
	@Column(name="c_time")
	private String cTime;	
	@ManyToOne					//标注多对一 关联关系
	@JoinColumn(name="cg_id")  //外键字段	
	private ClientGrade belongClientGrade;
	@OneToMany(mappedBy="belongClient",cascade ={CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	private List<Account> accountList;
	@OneToMany(mappedBy="belongClient",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<ClientCharge> clientchargeList;
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcSex() {
		return cSex;
	}
	public void setcSex(String cSex) {
		this.cSex = cSex;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public double getcDiscount() {
		return cDiscount;
	}
	public void setcDiscount(double cDiscount) {
		this.cDiscount = cDiscount;
	}
	public double getcBala() {
		return cBala;
	}
	public void setcBala(double cBala) {
		this.cBala = cBala;
	}
	public double getcState() {
		return cState;
	}
	public void setcState(double cState) {
		this.cState = cState;
	}

	public ClientGrade getBelongClientGrade() {
		return belongClientGrade;
	}
	public void setBelongClientGrade(ClientGrade belongClientGrade) {
		this.belongClientGrade = belongClientGrade;
	}
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	public String getcTime() {
		return cTime;
	}
	public void setcTime(String cTime) {
		this.cTime = cTime;
	}
	public List<ClientCharge> getClientchargeList() {
		return clientchargeList;
	}
	public void setClientchargeList(List<ClientCharge> clientchargeList) {
		this.clientchargeList = clientchargeList;
	}


}
