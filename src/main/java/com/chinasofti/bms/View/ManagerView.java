package com.chinasofti.bms.View;

public class ManagerView {
	// admin管理员登录
	public void aView() {
		System.out.println("************您已进入到管理员界面*************");
		System.out.println("\t\t1、图书信息管理");
		System.out.println("\t\t2、读者信息管理");
		System.out.println("\t\t3、管理员信息管理");
		System.out.println("\t\t4、读者充值");
		System.out.println("\t\t5、图书类型管理");
		System.out.println("\t\t-1、返回上一层");
		System.out.println("\t\t0、退出系统");
	}

	// 管理管理员
	public void mView() {
		System.out.println("************您已进入到管理员界面*************");
		System.out.println("\t\t1、图书信息管理");
		System.out.println("\t\t2、读者信息管理");
		System.out.println("\t\t3、读者充值");
		System.out.println("\t\t4、图书类型管理");
		System.out.println("\t\t0、退出系统");
	}

	// 管理管理员
	public void vView() {
		System.out.println("  =============管理员信息=============");
		System.out.println("\t\t1、查看管理员信息");
		System.out.println("\t\t2、添加管理员");
		System.out.println("\t\t3、修改管理员信息");
		System.out.println("\t\t4、删除管理员");
		System.out.println("\t\t-1、返回上一层");
	}

	// 管理图书
	public void mbookView() {
		System.out.println("   ==============图书信息=============");
		System.out.println("\t\t1、查询图书");
		System.out.println("\t\t2、添加图书信息");
		System.out.println("\t\t3、修改图书信息");
		System.out.println("\t\t4、删除图书信息");
		System.out.println("\t\t-1、返回上一层");
	}

	// 管理图书类型
	public void mtbookView() {
		System.out.println("   ==============图书类型信息=============");
		System.out.println("\t\t1、查询图书类型");
		System.out.println("\t\t2、添加图书类型");
		System.out.println("\t\t3、修改图书类型");
		System.out.println("\t\t4、删除图书类型");
		System.out.println("\t\t-1、返回上一层");
	}

	// 管理读者
	public void readerView() {
		System.out.println("   ==============读者信息=============");
		System.out.println("\t\t1、查询读者信息");
		System.out.println("\t\t2、添加读者");
		System.out.println("\t\t3、修改读者信息");
		System.out.println("\t\t4、删除读者");
		System.out.println("\t\t5、批量添加读者");
		System.out.println("\t\t-1、返回上一层");
	}
}
