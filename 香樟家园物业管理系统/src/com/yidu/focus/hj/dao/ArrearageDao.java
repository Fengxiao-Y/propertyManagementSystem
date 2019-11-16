package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.Arrearage;

public interface ArrearageDao {
	
	/**
	 * ���
	 * @param arrearage
	 * @return
	 */
	public int add(Arrearage arrearage);
	/**
	 * �޸�
	 * @param arrearage
	 * @return
	 */
	public int update(Arrearage arrearage);
	/**
	 * ɾ��
	 * @param houseId
	 * @return
	 */
	public int delete(String houseId);
	/**
	 * �鵥��
	 * @param houseId
	 * @return
	 */
	public Arrearage getArrearageById(String houseId);
	/**
	 * ��ȫ��
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<Arrearage> findByPage(int rows,int page,String condition);
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
	public Arrearage getArrearageByName(String ownerName);
}
