package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.Arrearage;

public interface ArrearageDao {
	
	/**
	 * 添加
	 * @param arrearage
	 * @return
	 */
	public int add(Arrearage arrearage);
	/**
	 * 修改
	 * @param arrearage
	 * @return
	 */
	public int update(Arrearage arrearage);
	/**
	 * 删除
	 * @param houseId
	 * @return
	 */
	public int delete(String houseId);
	/**
	 * 查单个
	 * @param houseId
	 * @return
	 */
	public Arrearage getArrearageById(String houseId);
	/**
	 * 查全部
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<Arrearage> findByPage(int rows,int page,String condition);
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
	public Arrearage getArrearageByName(String ownerName);
}
