package com.chinasofti.bms.domain;

import java.io.Serializable;




public class ReaderType implements Serializable {
	private Integer tid; // 读者类型编号
	private String typename; // 读者类型名称
	private Integer maxborrownum;// 最大借阅量
	private Integer limitday;// 最大借阅天数
	
	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Integer getMaxborrownum() {
		return maxborrownum;
	}

	public void setMaxborrownum(Integer maxborrownum) {
		this.maxborrownum = maxborrownum;
	}

	public Integer getLimitday() {
		return limitday;
	}

	public void setLimitday(Integer limitday) {
		this.limitday = limitday;
	}

	@Override
	public String toString() {
		return "ReadType [tid=" + tid + ", typename=" + typename
				+ ", maxborrownum=" + maxborrownum + ", limitday=" + limitday
				+ "]";
	}
	
}
