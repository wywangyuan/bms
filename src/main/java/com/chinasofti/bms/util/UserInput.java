package com.chinasofti.bms.util;

import java.util.Scanner;

public class UserInput {
	//�����û����������String
		public String getString(String msg){
			System.out.println(msg);
			Scanner sc=new Scanner(System.in);
			return sc.next();
		}
		//��������
		public int getInt(String msg){
			while(true){
			try {
				System.out.println(msg);
				Scanner sc=new Scanner(System.in);
				return sc.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�������ݸ�ʽ����ȷ����������������");
			}
		}
		}
		//���ո�����
		public double getDouble(String  msg){
			while(true){
				try {
					System.out.println(msg);
					Scanner sc=new Scanner(System.in);
					return sc.nextDouble();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("�������ݸ�ʽ����ȷ��������С������");
				}
			}
		}
}
