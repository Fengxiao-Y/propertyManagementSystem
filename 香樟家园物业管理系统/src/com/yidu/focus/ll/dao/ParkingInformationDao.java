package com.yidu.focus.ll.dao;

import java.util.List;

import com.yidu.focus.ll.domain.ParkingInformation;

public interface ParkingInformationDao {

	/**
	 * 添加
	 * @param parkingInformation
	 * @return
	 */
	public int add(ParkingInformation parkingInformation);
	/**
	 * 删除
	 * @param parkId
	 * @return
	 */
	public int delete(String parkId);
	/**
	 * 修改
	 * @param parkingInformation
	 * @return
	 */
	public int update(ParkingInformation parkingInformation);

	/**
	 * 根据车位编号查找单个车辆信息
	 * @param parkId
	 * @return
	 */
	public ParkingInformation findById(String parkId);

	/**
	 * 查找所有
	 * @return
	 */
	public List<ParkingInformation> findAll();
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有车辆信息集合
	 */
	public List<ParkingInformation> findByPage(int rows,int page);

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	public List<ParkingInformation> findByPage(int rows,int page,String condition);

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
	
	/**
	 * 根据房号编号查找单个车位信息
	 * @param parkId
	 * @return
	 */
	public ParkingInformation findByName(String houseId);
}
