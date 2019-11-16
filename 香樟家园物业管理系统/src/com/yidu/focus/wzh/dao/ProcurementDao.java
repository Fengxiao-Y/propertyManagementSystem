
package com.yidu.focus.wzh.dao;

import java.util.List;

import com.yidu.focus.wzh.domain.Procurement;

/**
 * 功能：procurement的数据库接口
 * 作者：伍志华
 * 日期：2019年10月11日下午6:17:28
 * 版本：1.0
 */
public interface ProcurementDao {
	/**
	 * 将实体类对象添加到数据库表中
	 * @param procurement 后台登录实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	public int add(Procurement procurement);
	
	/**
	 * 将实体类对象更新到数据库
	 * @param procurement 后台登录实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	public int update(Procurement procurement);
	
	/**
	 * 按照后台登录编号（主键）从数据库表中查找数据
	 * @param cid 后台登录编号
	 * @return 后台登录实体对象
	 */
	public Procurement findById(String proId);
	
	
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有员工信息集合
	 */
	public List<Procurement> findByPage(int rows,int page);

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return 指定页中的数据集合
	 */
	public List<Procurement> findByPage(int rows,int page,String condition);

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

	public int delete(String proId);
}

