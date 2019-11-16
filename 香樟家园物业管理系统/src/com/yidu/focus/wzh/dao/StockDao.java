
package com.yidu.focus.wzh.dao;

import java.util.List;

import com.yidu.focus.wzh.domain.Stock;



/**
 * 功能：Stock表的数据库接口
 * 作者：伍志华
 * 日期：2019年10月11日下午5:12:22
 * 版本：1.0
 */
public interface StockDao {
	/**
	 * 将实体类对象添加到数据库表中
	 * @param stock 库存实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	public int add(Stock stock);
	
	/**
	 * 将实体类对象更新到数据库
	 * @param cust 库存实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	public int update(Stock stock);
	
	/**
	 * 按照库存编号（主键）从数据库表中查找数据
	 * @param stockId 库存编号
	 * @return 库存实体对象
	 */
	public Stock findById(int stockId);
	/**
	 * 查看所有
	 * @return 所有显示的库存信息
	 */
	public List<Stock> findAll();
	
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有库存信息集合
	 */
	public List<Stock> findByPage(int rows,int page);

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return 指定页中的数据集合
	 */
	public List<Stock> findByPage(int rows,int page,String condition);

	/**
	 * 统计记录数
	 * @return 表中所有记录行数
	 */
	public int count();
	
	/**
	 * 统计符合条件的记录数
	 * @param condition 条件
	 * @return 返回符合条件的数据行数
	 */
	public int count(String condition);
	
	/**
	 * 按照物品名从数据库表中查找数据
	 * @param goodsName 物品名称
	 * @return
	 */
	public Stock findByName(String goodsName);
	
}
