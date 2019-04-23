package com.chinasofti.bms.domain;

import java.io.Serializable;



public class BookType implements Serializable {
	private Integer btid; // 图书类型编号
	private String typename; // 图书类型名称
	public Integer getBtid() {
		return btid;
	}
	public void setBtid(Integer btid) {
		this.btid = btid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	@Override
	public String toString() {
		return "BookType [btid=" + btid + ", typename=" + typename + "]";
	}
	
}
