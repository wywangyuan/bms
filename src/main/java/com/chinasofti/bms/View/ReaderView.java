package com.chinasofti.bms.View;

public class ReaderView {
	//读者登录
	public void welcome(){
		System.out.println("***********欢迎来到唐山学院图书馆************");
		System.out.println("\t\t1、查询图书");
		System.out.println("\t\t2、借阅图书");
		System.out.println("\t\t3、归还图书");
		System.out.println("\t\t4、修改登录密码");
		System.out.println("\t\t5、查询个人账户信息");
		System.out.println("\t\t-1、返回上一层");
	}
	public void book(){
		System.out.println("****************图书******************");
		System.out.println("\t\t1、图书类型");
		System.out.println("\t\t2、图书名称");
		System.out.println("\t\t3、所有图书");
		System.out.println("\t\t-1、返回上一层");
	}
}
