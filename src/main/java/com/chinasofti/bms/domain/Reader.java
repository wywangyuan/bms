package com.chinasofti.bms.domain;

import java.io.Serializable;
import java.util.List;

public class Reader implements Serializable {
	private Integer rid; // 读者编号
	private Integer tid; // 读者类型编号
	private String rname; // 读者姓名
	private Integer age; // 读者年龄
	private String sex; // 读者性别
	private String phone; // 读者电话
	private String dept; // 所在系部
	private String password; // 讀者登录密码
	private Double money; //读者卡上余额
	private ReaderType readType; // 读者类型
	private List<BorrowBook> borrowBook; // 读者借阅信息
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public ReaderType getReadType() {
		return readType;
	}
	public void setReadType(ReaderType readType) {
		this.readType = readType;
	}
	public List<BorrowBook> getBorrowBook() {
		return borrowBook;
	}
	public void setBorrowBook(List<BorrowBook> borrowBook) {
		this.borrowBook = borrowBook;
	}
	@Override
	public String toString() {
		return "Reader [rid=" + rid + ", tid=" + tid + ", rname=" + rname
				+ ", age=" + age + ", sex=" + sex + ", phone=" + phone
				+ ", dept=" + dept + ", password=" + password + ", money="
				+ money + ", readType=" + readType + ", borrowBook="
				+ borrowBook + "]";
	}
}
