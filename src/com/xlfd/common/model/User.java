package com.xlfd.common.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity//@Entity注解的作用：映射类的扫描
@Table(name="user")
public class User {
	@Id //主键标识
	@Column(name="u_id")
	private int uId;
	@Column(name="u_name")
	private String  uName;
	@Column(name="u_sex")
	private String uSex;
	@Column(name="img_name")
	private String imgName;
	@Column(name="advance_utname")
	private String advanceUtname;
	@Transient
	private MultipartFile userImg; 
	@Column(name="hire_date")
	private Date hireDate;
	@Column(name="state")
	private int state;
	@Column(name="leave_date")
	private Date leaveDate;
	//外键到员工类型	
	@ManyToOne//这里name是本类（表）在数据库的字段名，referencedColumnName 是关联类（表）在数据库中的关联字段名 
	@JoinColumn(name ="ut_id",referencedColumnName="ut_id")
	private UserType belongUserType;
	@OneToMany(mappedBy="belongUser",cascade ={CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	private List<Account> accountList;
	@OneToMany(mappedBy="USbelongUser",cascade ={CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	private List<UserSalary> USList;
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuSex() {
		return uSex;
	}
	public void setuSex(String uSex) {
		this.uSex = uSex;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getAdvanceUtname() {
		return advanceUtname;
	}
	public void setAdvanceUtname(String advanceUtname) {
		this.advanceUtname = advanceUtname;
	}
	public MultipartFile getUserImg() {
		return userImg;
	}
	public void setUserImg(MultipartFile userImg) {
		this.userImg = userImg;
	}
	public UserType getBelongUserType() {
		return belongUserType;
	}
	public void setBelongUserType(UserType belongUserType) {
		this.belongUserType = belongUserType;
	}
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	public List<UserSalary> getUSList() {
		return USList;
	}
	public void setUSList(List<UserSalary> uSList) {
		USList = uSList;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	
}
