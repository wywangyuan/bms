package com.chinasofti.controller;

import java.lang.reflect.Proxy;

public class ClientProxy {
	public static <T> T getClient(Class<T> clazz,String ip,int port){
		return (T)Proxy.newProxyInstance(ClientProxy.class.getClassLoader(), new Class[]{clazz}, new ClientHandler(ip, port));
	}

}
