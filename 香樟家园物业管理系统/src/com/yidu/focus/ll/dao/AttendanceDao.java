package com.yidu.focus.ll.dao;

import java.util.List;

import com.yidu.focus.ll.domain.Attendance;

public interface AttendanceDao {
    /**
     * 添加信息
     * @param attendance
     * @return
     */
	public int add(Attendance attendance);
	
	/**
	 * 删除
	 * @param attendId
	 * @return
	 */
	public int delete(int attendId);
	
	/**
	 * 修改数据
	 * @param attendId
	 * @return
	 */
	public int update(Attendance attendance);
	
	/**
	 * 根据考勤编号查找单个员工信息
	 * @param attendId
	 * @return
	 */
	public Attendance findById(int attendId);
	
	/**
	 * 查找所有
	 * @return
	 */
	public List<Attendance> findAll();
	
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有员工信息集合
	 */
	public List<Attendance> findByPage(int rows,int page);

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	public List<Attendance> findByPage(int rows,int page,String condition);

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
