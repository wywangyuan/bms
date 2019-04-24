package com.chinasofti.bms.service;

import java.util.List;

import com.chinasofti.bms.domain.Book;
import com.chinasofti.bms.domain.BookType;
import com.chinasofti.bms.domain.BorrowBook;

public interface BookService {
	//查询所有图书
	List<Book> getAllBooks();
	//根据图书编号查询
	Book getBooksById(Integer bid);
	//根据图书类型查询
	List<Book> getBooksByTypeId(int typeId);
	//根据图书名称查询
	List<Book> getBooksByName(String bname);
	//根据id删除图书
	boolean deleteBook(Integer id);
	//添加书籍
	boolean addBook(Book book);
	//修改书籍
	boolean updateBook(Book book);
	BorrowBook getBorrowBookById(int rid,int bid);
	//获取添加的图书的信息
	public Book getLastBook();
	List<BookType> getAllBookType();
	//从文本文档里批量导入图书信息
	boolean insertBatchBook(List<Book> books);
	
}
