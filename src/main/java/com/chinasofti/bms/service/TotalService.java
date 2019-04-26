package com.chinasofti.bms.service;

import com.chinasofti.bms.domain.BookType;


public interface TotalService extends BookService,ReaderService,UserService{

	boolean addBookType(BookType bookType);

	boolean updateBookType(BookType bookType);

	boolean deleteBookType(int btid);

	

}
