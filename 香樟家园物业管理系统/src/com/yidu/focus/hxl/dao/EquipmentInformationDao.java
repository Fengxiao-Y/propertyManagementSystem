package com.yidu.focus.hxl.dao;

import java.util.List;

import com.yidu.focus.hxl.domain.EquipmentInformation;


public interface EquipmentInformationDao {
	 /**
     * 添加信息
     * @param attendance
     * @return
     */
	public int add(EquipmentInformation EquipmentInformation);
	/**
	 * 删除
	 * @param attendId
	 * @return
	 */
	public int deleteById(String eqId);
	/**
	 * 修改数据
	 * @param attendId
	 * @return
	 */
	public int update(EquipmentInformation EquipmentInformation);
	/**
	 * 根据考勤编号查找单个员工信息
	 * @param attendId
	 * @return
	 */
	public EquipmentInformation findById(String eqId);
	
	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	public List<EquipmentInformation> findByPage(int rows,int page);
	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	public List<EquipmentInformation> findByPage(int rows,int page,String condition);
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
