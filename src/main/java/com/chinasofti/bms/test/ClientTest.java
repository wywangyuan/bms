package com.chinasofti.bms.test;

import org.junit.Test;

import com.chinasofti.controller.ClientController;

public class ClientTest {
	@Test
	public void test(){
		new ClientController().start();
	}
}
