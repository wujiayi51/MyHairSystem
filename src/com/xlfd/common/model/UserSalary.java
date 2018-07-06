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
@Table(name="usersalary")//@Table()注解的作用：是将映射类与数据库表进行关联
public class UserSalary {
	@Id //主键标识
	@GeneratedValue(strategy=GenerationType.AUTO) //配置自增字段
	@Column(name="id")//将类中的成员变量与数据库中表的字段进行关联
	private int id;
	@ManyToOne//这里name是本类（表）在数据库的字段名，referencedColumnName 是关联类（表）在数据库中的关联字段名 
	@JoinColumn(name="u_id")
	private User USbelongUser;
	@Column(name="salary_date")
	private Date salaryDate;
	@Column(name="final_salary")
	private double finalSalary;
	@Column(name="serve_count")
	private int serveCount;
	@Column(name="real_totalmoney")
	private double rtMoney;
	@Column(name="add_des")
	private String addDes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getSalaryDate() {
		return salaryDate;
	}
	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}
	public int getServeCount() {
		return serveCount;
	}
	public void setServeCount(int serveCount) {
		this.serveCount = serveCount;
	}
	public double getFinalSalary() {
		return finalSalary;
	}
	public void setFinalSalary(double finalSalary) {
		this.finalSalary = finalSalary;
	}
	public double getRtMoney() {
		return rtMoney;
	}
	public void setRtMoney(double rtMoney) {
		this.rtMoney = rtMoney;
	}
	public User getUSbelongUser() {
		return USbelongUser;
	}
	public void setUSbelongUser(User uSbelongUser) {
		USbelongUser = uSbelongUser;
	}
	public String getAddDes() {
		return addDes;
	}
	public void setAddDes(String addDes) {
		this.addDes = addDes;
	}
	
}
