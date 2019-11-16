package com.yidu.focus.hxl.dao;

import java.util.List;

import com.yidu.focus.hxl.domain.EqCheck;


public interface EqCheckDao {
	/**
     * 添加信息
     * @param attendance
     * @return
     */
	public int add(EqCheck eqCheck);
	/**
	 * 删除
	 * @param attendId
	 * @return
	 */
	public int deleteById(int checkId);
	/**
	 * 修改数据
	 * @param attendId
	 * @return
	 */
	public int update(EqCheck eqCheck);
	/**
	 * 根据考勤编号查找单个员工信息
	 * @param attendId
	 * @return
	 */
	public EqCheck findById(int checkId);
	
	
	/**
	 * 根据考勤编号查找单个员工信息
	 * @param attendId
	 * @return
	 */
	public List<EqCheck> findByPage(int rows,int page);
	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	public List<EqCheck> findByPage(int rows,int page,String condition);
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
