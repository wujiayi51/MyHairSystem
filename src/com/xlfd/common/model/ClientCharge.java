package com.xlfd.common.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;


@Entity//@Entity注解的作用：映射类的扫描
@Table(name="clientcharge")
public class ClientCharge {
	@Id //主键标识
	@GeneratedValue(strategy=GenerationType.AUTO) //配置自增字段
	@Column(name="id")//将类中的成员变量与数据库中表的字段进行关联
	private int id;
	@Column(name="liushui_num")
	private String liushuiNum;
	@Column(name="charge_date")
	private String chargeDate;
	@Column(name="charge_money")
	private String chargeMoney;
	//@OneToMany(mappedBy="belongClientGrade",cascade ={CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	//	private List<Client> clientList;
	//c_id外键到client表
	@ManyToOne//这里name是本类（表）在数据库的字段名，referencedColumnName 是关联类（表）在数据库中的关联字段名 
	@JoinColumn(name ="c_id",referencedColumnName="c_id")
	private Client belongClient;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLiushuiNum() {
		return liushuiNum;
	}
	public void setLiushuiNum(String liushuiNum) {
		this.liushuiNum = liushuiNum;
	}
	public String getChargeDate() {
		return chargeDate;
	}
	public void setChargeDate(String chargeDate) {
		this.chargeDate = chargeDate;
	}
	public String getChargeMoney() {
		return chargeMoney;
	}
	public void setChargeMoney(String chargeMoney) {
		this.chargeMoney = chargeMoney;
	}
	public Client getBelongClient() {
		return belongClient;
	}
	public void setBelongClient(Client belongClient) {
		this.belongClient = belongClient;
	}


}
