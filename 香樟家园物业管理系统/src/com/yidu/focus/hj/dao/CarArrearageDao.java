package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.CarArrearage;


public interface CarArrearageDao {

	
	/**
	 * ���
	 * @param arrearage
	 * @return
	 */
	public int add(CarArrearage carArrearage);
	/**
	 * �޸�
	 * @param arrearage
	 * @return
	 */
	public int update(CarArrearage carArrearage);
	/**
	 * ɾ��
	 * @param houseId
	 * @return
	 */
	public int delete(String parkId);
	/**
	 * �鵥��
	 * @param houseId
	 * @return
	 */
	public CarArrearage getCarArrearageById(String parkId);
	/**
	 * ��ȫ��
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<CarArrearage> findByPage(int rows,int page,String condition);
	/**
	 * ͳ��
	 * @param condition
	 * @return
	 */
	public int count(String condition);
	/**
	 * �������ֲ鵥��
	 * @param houseId
	 * @return
	 */
	public CarArrearage getCarArrearageByName(String ownerName);
}
