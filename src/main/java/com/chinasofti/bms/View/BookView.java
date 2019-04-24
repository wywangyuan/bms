package com.chinasofti.bms.View;

public class BookView {
	public void bView(){
		System.out.println("************您已进入到图书管理界面*************");
		System.out.println("\t\t1、查询图书");
		System.out.println("\t\t2、添加书籍");
		System.out.println("\t\t3、修改书籍");
		System.out.println("\t\t4、删除书籍");
		System.out.println("\t\t5、批量导入图书信息");
		System.out.println("\t\t6、导出所有图书信息");
		System.out.println("\t\t-1、返回上一层");
		System.out.println("\t\t0、退出系统");
	}
	public void rView(){
		System.out.println("************您已进入到读者管理界面*************");
		System.out.println("\t\t1、查看读者信息");
		System.out.println("\t\t2、修改读者信息");
		System.out.println("\t\t3、删除读者");
		System.out.println("\t\t4、查询读者借阅书籍");
		System.out.println("\t\t5、查看读者归还书籍");
		System.out.println("\t\t-1、返回上一层");
		System.out.println("\t\t0、退出系统");
	}
	//查询图书
	public void selectView(){
		System.out.println("  =============图书查询界面=============");
		System.out.println("\t\t1、图书编号查询");
		System.out.println("\t\t2、图书类型查询");
		System.out.println("\t\t3、图书名称查询");
		System.out.println("\t\t4、查询所有书籍");
		System.out.println("\t\t-1、返回上一层");
		System.out.println("\t\t0、退出系统");
	}
	
	
}
