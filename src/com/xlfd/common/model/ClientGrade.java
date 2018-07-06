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
@Table(name="clientgrade")
public class ClientGrade {
	@Id //主键标识
	@Column(name="cg_id")
	private String cgId;
	@Column(name="c_bala")
	private int cBala;
	@Column(name="c_discount",columnDefinition="double(10,2) default '0.00'")
	private double cDiscount;
	@OneToMany(mappedBy="belongClientGrade",cascade ={CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	private List<Client> ClientList;
	public String getCgId() {
		return cgId;
	}
	public void setCgId(String cgId) {
		this.cgId = cgId;
	}
	public int getcBala() {
		return cBala;
	}
	public void setcBala(int cBala) {
		this.cBala = cBala;
	}
	public double getcDiscount() {
		return cDiscount;
	}
	public void setcDiscount(double cDiscount) {
		this.cDiscount = cDiscount;
	}
	public List<Client> getClientList() {
		return ClientList;
	}
	public void setClientList(List<Client> clientList) {
		ClientList = clientList;
	}
	
	
	
	
}
