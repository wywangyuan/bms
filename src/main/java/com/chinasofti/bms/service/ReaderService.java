package com.chinasofti.bms.service;

import java.util.List;

import com.chinasofti.bms.domain.BorrowBook;
import com.chinasofti.bms.domain.Reader;
import com.chinasofti.bms.domain.ReaderType;

public interface ReaderService {
	public Reader readLogin(Integer rid,String password);
	/**
	 * 查询所有读者
	 * @return
	 */
	public List<Reader> getAllReader();
	/**
	 * 通过id查询读者
	 * @param id
	 * @return
	 */
	public Reader getReaderById(int id);
	/**
	 * 添加读者
	 * @param reader
	 * @return
	 */
	public boolean addReader(Reader reader);
	/**
	 * 修改读者信息
	 * @param reader
	 * @return
	 */
	public boolean updateReader(Reader reader);
	/**
	 * 删除读者信息
	 * @param id
	 * @return
	 */
	public boolean deleteReader(int id);
	//借书的方法
	public boolean borrowBook(Reader reader);
	//还书的方法
	/**
	 * 归还图书
	 * @param reader
	 * @return
	 */
	public boolean returnBook(BorrowBook borrowBook);
	/**
	 * 获取最后插入的读者
	 */
	public Reader getLastReader();
	public boolean batchAddReader(int count,int readerType) ;
	public List<Reader> getLastReader(int count);
	public List<ReaderType> getAllReadType();

}
