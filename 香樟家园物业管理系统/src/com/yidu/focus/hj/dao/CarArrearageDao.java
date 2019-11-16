package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.CarArrearage;


public interface CarArrearageDao {

	
	/**
	 * 添加
	 * @param arrearage
	 * @return
	 */
	public int add(CarArrearage carArrearage);
	/**
	 * 修改
	 * @param arrearage
	 * @return
	 */
	public int update(CarArrearage carArrearage);
	/**
	 * 删除
	 * @param houseId
	 * @return
	 */
	public int delete(String parkId);
	/**
	 * 查单个
	 * @param houseId
	 * @return
	 */
	public CarArrearage getCarArrearageById(String parkId);
	/**
	 * 查全部
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<CarArrearage> findByPage(int rows,int page,String condition);
	/**
	 * 统计
	 * @param condition
	 * @return
	 */
	public int count(String condition);
	/**
	 * 根据名字查单个
	 * @param houseId
	 * @return
	 */
	public CarArrearage getCarArrearageByName(String ownerName);
}
