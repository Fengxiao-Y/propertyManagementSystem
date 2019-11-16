package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.Record;

public interface RecordDao {
	
	/**
	 * ���
	 * @param record
	 * @return
	 */
	public int add(Record record);
	/**
	 * �޸�
	 * @param record
	 * @return
	 */
	public int update(Record record);
	/**
	 * ɾ��
	 * @param recordId
	 * @return
	 */
	public int delete(int recordId);
	/**
	 * �鵥��
	 * @param recordId
	 * @return
	 */
	public Record getRecordById(int recordId);
	/**
	 * ��ȫ��
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<Record> findByPage(int rows,int page,String condition);
	/**
	 * ͳ��
	 * @param condition
	 * @return
	 */
	public int count(String condition);
	/**
	 * �������ֲ鵥��
	 * @param recordId
	 * @return
	 */
	public List<Record> getRecordByName(String ownerName);
}
