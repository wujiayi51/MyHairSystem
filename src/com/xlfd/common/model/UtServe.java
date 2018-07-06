package com.xlfd.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity//@Entity注解的作用：映射类的扫描
@Table(name="utserve")
public class UtServe {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) //配置自增字段
	@Column(name="id")
	private int id;	
	@ManyToOne//这里name是本类（表）在数据库的字段名，referencedColumnName 是关联类（表）在数据库中的关联字段名 
	@JoinColumn(name ="ut_id",referencedColumnName="ut_id")
	private UserType utservebelongUserType;
	@ManyToOne//这里name是本类（表）在数据库的字段名，referencedColumnName 是关联类（表）在数据库中的关联字段名 
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
