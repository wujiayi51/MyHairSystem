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


@Entity//@Entityע������ã�ӳ�����ɨ��
@Table(name="usersalary")//@Table()ע������ã��ǽ�ӳ���������ݿ����й���
public class UserSalary {
	@Id //������ʶ
	@GeneratedValue(strategy=GenerationType.AUTO) //���������ֶ�
	@Column(name="id")//�����еĳ�Ա���������ݿ��б���ֶν��й���
	private int id;
	@ManyToOne//����name�Ǳ��ࣨ�������ݿ���ֶ�����referencedColumnName �ǹ����ࣨ�������ݿ��еĹ����ֶ��� 
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
