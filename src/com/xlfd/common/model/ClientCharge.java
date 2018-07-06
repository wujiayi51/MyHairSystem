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
@Table(name="clientcharge")
public class ClientCharge {
	@Id //������ʶ
	@GeneratedValue(strategy=GenerationType.AUTO) //���������ֶ�
	@Column(name="id")//�����еĳ�Ա���������ݿ��б���ֶν��й���
	private int id;
	@Column(name="liushui_num")
	private String liushuiNum;
	@Column(name="charge_date")
	private String chargeDate;
	@Column(name="charge_money")
	private String chargeMoney;
	//@OneToMany(mappedBy="belongClientGrade",cascade ={CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	//	private List<Client> clientList;
	//c_id�����client��
	@ManyToOne//����name�Ǳ��ࣨ�������ݿ���ֶ�����referencedColumnName �ǹ����ࣨ�������ݿ��еĹ����ֶ��� 
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
