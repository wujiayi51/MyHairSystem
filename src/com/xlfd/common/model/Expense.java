package com.xlfd.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity//@Entity注解的作用：映射类的扫描
@Table(name="Expense")
public class Expense {
	@Id //主键标识
	@GeneratedValue(strategy=GenerationType.AUTO) //配置自增字段
	@Column(name="id")
	private int id;
	@Column(name="item")
	private String item;

	@Column(name="item_expense",columnDefinition="double(10,1) default '0.0'")
	private double itemExpense;
	
	@Column(name="expense_time")
	private String expenseTime;
	
	@Column(name="expense_remark")
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getItemExpense() {
		return itemExpense;
	}

	public void setItemExpense(double itemExpense) {
		this.itemExpense = itemExpense;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExpenseTime() {
		return expenseTime;
	}

	public void setExpenseTime(String expenseTime) {
		this.expenseTime = expenseTime;
	}

	


	/*private double Waterandelec;//水电费
	
	private double Rent;//房租
	
	private double Meals;//伙食费
	
	private double Stocks;//进货费
*/

}
