package com.chinasofti.bms.domain;

import java.io.Serializable;



public class Book implements Serializable {
	private Integer bid;// 图书编号
	private Integer btid;// 图书类型编号
	private String bname;// 图书名称
	private String author;// 图书作者
	private String publish;// 出版社
	private Integer bnumber;// 图书数量
	private Double price;// 图书价格
	private BookType bookType;
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getBtid() {
		return btid;
	}
	public void setBtid(Integer btid) {
		this.btid = btid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public Integer getBnumber() {
		return bnumber;
	}
	public void setBnumber(Integer bnumber) {
		this.bnumber = bnumber;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public BookType getBookType() {
		return bookType;
	}
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", btid=" + btid + ", bname=" + bname
				+ ", author=" + author + ", publish=" + publish + ", bnumber="
				+ bnumber + ", price=" + price + ", bookType=" + bookType + "]";
	}
	
	
	
}
