package com.yidu.focus.ll.dao;

import java.util.List;

import com.yidu.focus.ll.domain.CarTime;


public interface CarTimeDao {
	/**
	 * 添加
	 * @param carTime
	 * @return
	 */
	public int add(CarTime carTime);
	/**
	 * 删除
	 * @param Nub
	 * @return
	 */
	public int delete(int Nub);
	/**
	 * 修改
	 * @param carTime
	 * @return
	 */
	public int update(CarTime carTime);
	
	/**
	 * 根据序号号查找单个车辆信息
	 * @param deptNo
	 * @return
	 */
	public CarTime findById(int Nub);
	
	/**
	 * 查找所有
	 * @return
	 */
	public List<CarTime> findAll();
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有车辆信息集合
	 */
	public List<CarTime> findByPage(int rows,int page);

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	public List<CarTime> findByPage(int rows,int page,String condition);

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
