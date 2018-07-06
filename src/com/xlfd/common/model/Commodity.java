package com.xlfd.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//@Entity注解的作用：映射类的扫描
@Table(name="commodity")
public class Commodity {
	@Id //主键标识
	@GeneratedValue(strategy=GenerationType.AUTO) //配置自增字段
	@Column(name="id")//将类中的成员变量与数据库中表的字段进行关联
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
