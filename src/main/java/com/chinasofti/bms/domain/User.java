package com.chinasofti.bms.domain;

import java.io.Serializable;


public class User implements Serializable {
	private Integer mid;// 用户编号
	private String uname;// 用户姓名
	private String password;// 用户密码
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [mid=" + mid + ", uname=" + uname + ", password="
				+ password + "]";
	}
	
}
