package com.chinasofti.bms.domain;

import java.io.Serializable;
import java.sql.Date;



public class BorrowBook implements Serializable {
	private Integer rid;// 读者编号
	private Integer bid;// 图书编号
	private Date borrowdate;// 借阅日期
	private Date returndate;// 归还日期
	private Double money;// 罚金
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Date getBorrowdate() {
		return borrowdate;
	}
	public void setBorrowdate(Date borrowdate) {
		this.borrowdate = borrowdate;
	}
	public Date getReturndate() {
		return returndate;
	}
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "BorrowBook [rid=" + rid + ", bid=" + bid + ", money=" + money
				+ "]";
	}
	
}
