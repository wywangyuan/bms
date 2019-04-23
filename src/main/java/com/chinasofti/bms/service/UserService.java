package com.chinasofti.bms.service;

import java.util.List;

import com.chinasofti.bms.domain.User;

public interface UserService {
	User userLogin(int id, String pwd);
/**
	 * 获取所有管理员
	 * @return
	 */
	public List<User> getAllUser();
	/**
	 * 通过id查询管理员
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
	/**
	 * 修改管理员信息
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);
	/**
	 * 添加管理员信息
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);
	/**
	 * 通过id删除管理员
	 * @param id
	 * @return
	 */
	public boolean deleteUser(int id);
}
