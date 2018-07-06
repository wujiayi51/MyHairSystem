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



@Entity//@Entity注解的作用：映射类的扫描
@Table(name="usertype")
public class UserType {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) //配置自增字段
	@Column(name="ut_id")
	private int utId;
	
	@Column(name="ut_name")
	private String utName;
	@Column(name="ut_salary")
	private Double utSalary;
	@Column(name="ut_scale")
	private Double utScale;
	@Column(name="ut_weight")
	private Double utWeight;//CascadeType.MERGE,CascadeType.REMOVE
	//mappedBy：多方的外键字段对应的成员变量名  cascade：级联操作   fetch：加载策略  急加载，懒加载 --该查的地方才查
	@OneToMany(mappedBy="belongUserType",cascade ={CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	private List<User> userList;
	@OneToMany(mappedBy="utservebelongUserType",cascade ={CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	private List<UtServe> utserveList;
	public int getUtId() {
		return utId;
	}
	public void setUtId(int utId) {
		this.utId = utId;
	}
	public String getUtName() {
		return utName;
	}
	public void setUtName(String utName) {
		this.utName = utName;
	}
	public Double getUtSalary() {
		return utSalary;
	}
	public void setUtSalary(Double utSalary) {
		this.utSalary = utSalary;
	}
	public Double getUtScale() {
		return utScale;
	}
	public void setUtScale(Double utScale) {
		this.utScale = utScale;
	}
	public Double getUtWeight() {
		return utWeight;
	}
	public void setUtWeight(Double utWeight) {
		this.utWeight = utWeight;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public List<UtServe> getUtserveList() {
		return utserveList;
	}
	public void setUtserveList(List<UtServe> utserveList) {
		this.utserveList = utserveList;
	}


}
