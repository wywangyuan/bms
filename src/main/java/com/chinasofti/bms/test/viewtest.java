package com.chinasofti.bms.test;

import org.junit.Test;

import com.chinasofti.bms.View.BookView;
import com.chinasofti.bms.View.MainView;
import com.chinasofti.bms.View.ManagerView;
import com.chinasofti.bms.View.ReaderView;



public class viewtest {
	private BookView bookView=new BookView();
	private MainView mainView=new MainView();
	private ManagerView managerView=new ManagerView();
	private ReaderView readerView=new ReaderView();
	@Test
	public void test(){
		mainView.homeView();
	
		mainView.userView();
		
		managerView.mView();
		managerView.vView();
		readerView.welcome();
		bookView.bView();
		bookView.rView();
	}

}
