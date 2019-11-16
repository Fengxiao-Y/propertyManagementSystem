package com.yidu.focus.yfx.dao;

import java.util.List;

import com.yidu.focus.yfx.domain.Repairs;

/**
 * 
 * 功能：针对complain表进行操作的数据层接口
 * 编写者：Focus
 * 日期：2019年10月14日上午12:17:35
 * 版本：1.0
 */
public interface RepairsDao {
	/**
	 * 添加数据：将报修对象中的数据保存到报修数据库表中
	 * @param complain 报修对象
	 * @return 影响行数（1：操作成功；0：操作失败）
	 */
	public int add(Repairs repairs);
	/**
	 * 根据报修编号删除数据库表中的数据
	 * @param comId 报修编号
	 * @return 影响行数（1：操作成功；0：操作失败）
	 */
	public int deleteById(int repId);
	/**
	 * 修改数据：将报修对象中的数据更新到数据库表中
	 * @param complain 报修对象
	 * @return 影响行数（1：操作成功；0：操作失败）
	 */
	public int update(Repairs complain);
	
	/**
	 * 根据报修编号查找单次报修信息
	 * @param comId 报修编号
	 * @return 报修对象
	 */
	public Repairs findById(int repId);
	
	/**
	 * 查找所有报修信息
	 * @return 报修集合对象
	 */
	public List<Repairs> findAll();
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有报修信息集合
	 */
	public List<Repairs> findByPage(int rows,int page);

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 查找条件
	 * @return 指定页中所有报修信息集合
	 */
	public List<Repairs> findByPage(int rows,int page,String condition);

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
	public int count(String repairs);
	/**
	 * 根据报修人查找单次报修信息
	 * @param comId 报修编号
	 * @return 报修对象
	 */
	public List<Repairs> findByName(String ownerName);
}
