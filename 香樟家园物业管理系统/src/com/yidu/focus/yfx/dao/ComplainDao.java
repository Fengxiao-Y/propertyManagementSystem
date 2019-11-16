package com.yidu.focus.yfx.dao;

import java.util.List;

import com.yidu.focus.yfx.domain.Complain;

/**
 * 
 * 功能：针对complain表进行操作的数据层接口
 * 编写者：Focus
 * 日期：2019年10月14日上午12:17:35
 * 版本：1.0
 */
public interface ComplainDao {
	/**
	 * 添加数据：将投诉对象中的数据保存到投诉数据库表中
	 * @param complain 投诉对象
	 * @return 影响行数（1：操作成功；0：操作失败）
	 */
	public int add(Complain complain);
	/**
	 * 根据投诉编号删除数据库表中的数据
	 * @param comId 投诉编号
	 * @return 影响行数（1：操作成功；0：操作失败）
	 */
	public int deleteById(int comId);
	/**
	 * 修改数据：将投诉对象中的数据更新到数据库表中
	 * @param complain 投诉对象
	 * @return 影响行数（1：操作成功；0：操作失败）
	 */
	public int update(Complain complain);
	
	/**
	 * 根据投诉编号查找单次投诉信息
	 * @param comId 投诉编号
	 * @return 投诉对象
	 */
	public Complain findById(int comId);
	
	/**
	 * 查找所有投诉信息
	 * @return 投诉集合对象
	 */
	public List<Complain> findAll();
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有投诉信息集合
	 */
	public List<Complain> findByPage(int rows,int page);

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 查找条件
	 * @return 指定页中所有投诉信息集合
	 */
	public List<Complain> findByPage(int rows,int page,String condition);

	/**
	 * 统计记录数
	 * @return 表中所有记录行数
	 */
	public int count();
	
	/**
	 * 统计符合条件的记录数
	 * @param condition 统计条件
	 * @return 表中符合统计条件记录行数
	 */
	public int count(String condition);
	/**
	 * 根据投诉人号查找单次投诉信息
	 * @param comId 投诉编号
	 * @return 投诉对象
	 */
	public List<Complain> findByName(String ownerName);
}
