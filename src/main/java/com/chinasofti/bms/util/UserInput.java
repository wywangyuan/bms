package com.chinasofti.bms.util;

import java.util.Scanner;

public class UserInput {
	//接收用户输入的内容String
		public String getString(String msg){
			System.out.println(msg);
			Scanner sc=new Scanner(System.in);
			return sc.next();
		}
		//接收整数
		public int getInt(String msg){
			while(true){
			try {
				System.out.println(msg);
				Scanner sc=new Scanner(System.in);
				return sc.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("输入内容格式不正确，请输入整数类型");
			}
		}
		}
		//接收浮点数
		public double getDouble(String  msg){
			while(true){
				try {
					System.out.println(msg);
					Scanner sc=new Scanner(System.in);
					return sc.nextDouble();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("输入内容格式不正确，请输入小数类型");
				}
			}
		}
}
