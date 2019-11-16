package com.yidu.focus.ll.dao;

import java.util.List;

import com.yidu.focus.ll.domain.Dept;

public interface DeptDao {
	/**
	 * 添加
	 * @param dept
	 * @return
	 */
	public int add(Dept dept);
	/**
	 * 删除
	 * @param deptNo
	 * @return
	 */
	public int delete(int deptNo);
	/**
	 * 修改
	 * @param dept
	 * @return
	 */
	public int update(Dept dept);
	
	/**
	 * 根据员工编号查找单个员工信息
	 * @param deptNo
	 * @return
	 */
	public Dept findById(int deptNo);
	
	/**
	 * 查找所有
	 * @return
	 */
	public List<Dept> findAll();
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有部门信息集合
	 */
	public List<Dept> findByPage(int rows,int page);

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	public List<Dept> findByPage(int rows,int page,String condition);

	/**
	 * 统计记录数
	 * @return 表中所有记录行数
	 */
	public int count();
	
	/**
	 * 统计符合条件的记录数
	 * @param condition 条件
	 * @return
	 */
	public int count(String condition);
}
