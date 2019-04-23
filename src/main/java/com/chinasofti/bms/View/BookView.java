package com.chinasofti.bms.View;

public class BookView {
	public void bView(){
		System.out.println("1、查询图书");
		System.out.println("2、添加书籍");
		System.out.println("3、修改书籍");
		System.out.println("4、删除书籍");
		System.out.println("-1、返回上一层");
		System.out.println("0、退出系统");
	}
	public void rView(){
		System.out.println("1、查看读者信息");
		System.out.println("2、修改读者信息");
		System.out.println("3、删除读者");
		System.out.println("4、查询读者借阅书籍");
		System.out.println("5、查看读者归还书籍");
		System.out.println("-1、返回上一层");
		System.out.println("0、退出系统");
	}
	//查询图书
	public void selectView(){
		System.out.println("1、图书编号查询");
		System.out.println("2、图书类型查询");
		System.out.println("3、图书名称查询");
		System.out.println("4、查询所有书籍");
		System.out.println("-1、返回上一层");
		System.out.println("0、退出系统");
	}
	
	
}
