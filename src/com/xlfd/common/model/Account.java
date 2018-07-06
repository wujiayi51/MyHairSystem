package com.xlfd.common.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity//@Entity注解的作用：映射类的扫描
@Table(name="account")
public class Account {
	@Id //主键标识
	@GeneratedValue(strategy=GenerationType.AUTO) //配置自增字段
	@Column(name="id")
	private int id;	
	@Column(name="pay_time")
	private  String payTime;
	@Column(name="real_money" )
	private double realMoney;	
	@Column(name="should_money")
	private double shouldMoney;	
	@Column(name="flow_number")
	private String flowNumber;
	@ManyToOne					//标注多对一 关联关系
	@JoinColumn(name="u_id")  //外键字段	
	private User belongUser;
	@ManyToOne					//标注多对一 关联关系
	@JoinColumn(name="s_id")  //外键字段	
	private Serve belongServe;
	@ManyToOne					//标注多对一 关联关系
	@JoinColumn(name="c_id")  //外键字段	
	private Client belongClient;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public double getRealMoney() {
		return realMoney;
	}
	public void setRealMoney(double realMoney) {
		this.realMoney = realMoney;
	}
	public double getShouldMoney() {
		return shouldMoney;
	}
	public void setShouldMoney(double shouldMoney) {
		this.shouldMoney = shouldMoney;
	}
	public String getFlowNumber() {
		return flowNumber;
	}
	public void setFlowNumber(String flowNumber) {
		this.flowNumber = flowNumber;
	}
	public User getBelongUser() {
		return belongUser;
	}
	public void setBelongUser(User belongUser) {
		this.belongUser = belongUser;
	}
	public Serve getBelongServe() {
		return belongServe;
	}
	public void setBelongServe(Serve belongServe) {
		this.belongServe = belongServe;
	}
	public Client getBelongClient() {
		return belongClient;
	}
	public void setBelongClient(Client belongClient) {
		this.belongClient = belongClient;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	

}
