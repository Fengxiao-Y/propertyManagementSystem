
package com.yidu.focus.wzh.dao;

import java.util.List;

import com.yidu.focus.wzh.domain.Users;

/**
 * 功能：users表的数据库接口
 * 作者：伍志华
 * 日期：2019年10月11日下午5:20:18
 * 版本：1.0
 */
public interface UsersDao {
	/**
	 * 将实体类对象添加到数据库表中
	 * @param uId 客户实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	public int doRegister(Users users);
	/**
	 * 按照客户编号（主键）删除数据
	 * @param uId 客户编号
	 * @return 影响的行数（1成功，0失败）
	 */
	public int delete(int uId);
	/**
	 * 将实体类对象更新到数据库
	 * @param Users 用户实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	public int update(Users users);
	
	/**
	 * 登录功能
	 * @param uName 用户名
	 * @param uPassword 用户密码
	 * @return 用户户实体对象
	 */
	public Users doLogin(String uName,String uPassword);
	/**
	 * 登录功能
	 * @param uIphone 注册号码
	 * @return 用户户实体对象
	 */
	public Users findByUIphone(String uIphone);
	
	
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有员工信息集合
	 */
	public List<Users> findByPage(int rows,int page);

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return 指定页中的数据集合
	 */
	public List<Users> findByPage(int rows,int page,String condition);

	/**
	 * 统计记录数
	 * @return 表中所有记录行数
	 */
	public int count();
	
	/**
	 * 统计符合条件的记录数
	 * @param condition 条件
	 * @return 返回符合条件的数据行数
	 */
	public int count(String condition);

	public Users findById(int uId);
}
