package com.yidu.focus.hxl.dao;

import java.util.List;

import com.yidu.focus.hxl.domain.Regulatory;


public interface RegulatoryDao {
	 /**
     * 添加信息
     * @param attendance
     * @return
     */
	public int add(Regulatory regulatory);
	/**
	 * 删除
	 * @param attendId
	 * @return
	 */
	public int deleteById(String fileId);
	/**
	 * 修改数据
	 * @param attendId
	 * @return
	 */
	public int update(Regulatory regulatory);
	/**
	 * 根据考勤编号查找单个员工信息
	 * @param attendId
	 * @return
	 */
	public Regulatory findById(String fileId);
	
	
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有车辆信息集合
	 */
	public List<Regulatory> findByPage(int rows,int page);
	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	public List<Regulatory> findByPage(int rows,int page,String condition);
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
