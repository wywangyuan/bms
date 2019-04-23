package com.chinasofti.controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ClientHandler implements InvocationHandler{
	private String ip;
	private int port;

	public ClientHandler(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}


	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//创建套接字对象
		Socket client = new Socket(ip, port);
		//获取输输出流
		ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
		oos.writeUTF(method.getName());	
		oos.flush();
		oos.writeObject(method.getParameterTypes());
		oos.flush();
		oos.writeObject(args);
		oos.flush();
		//服务器
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		return ois.readObject();
	}

}
