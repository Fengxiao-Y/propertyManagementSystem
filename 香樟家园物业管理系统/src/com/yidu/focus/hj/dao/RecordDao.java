package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.Record;

public interface RecordDao {
	
	/**
	 * 添加
	 * @param record
	 * @return
	 */
	public int add(Record record);
	/**
	 * 修改
	 * @param record
	 * @return
	 */
	public int update(Record record);
	/**
	 * 删除
	 * @param recordId
	 * @return
	 */
	public int delete(int recordId);
	/**
	 * 查单个
	 * @param recordId
	 * @return
	 */
	public Record getRecordById(int recordId);
	/**
	 * 查全部
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<Record> findByPage(int rows,int page,String condition);
	/**
	 * 统计
	 * @param condition
	 * @return
	 */
	public int count(String condition);
	/**
	 * 根据名字查单个
	 * @param recordId
	 * @return
	 */
	public List<Record> getRecordByName(String ownerName);
}
