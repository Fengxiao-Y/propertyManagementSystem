package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.TotalIncome;

/**
 * 
 * 功能：工资表接口
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月12日上午10:34:45
 */
public interface TotalIncomeDao {

	/**
	 * 添加数据
	 * @param payments 工资对象
	 * @return 影响行数
	 */
	public int add(TotalIncome totalIncome);
	/**
	 * 修改数据
	 * @param payments 对象
	 * @return 影响行数
	 */
	public int update(TotalIncome totalIncome);
	/**
	 * 删除数据
	 * @param paymId 编号
	 * @return 影响行数
	 */
	public int delete(String totalId);
	/**
	 * 根据员工姓名查询某行数据
	 * @param paymId 编号
	 * @return 对象
	 */
	public TotalIncome getTotalIncomeById(String totalId);
	/**
	 * 分页查询数据
	 * @param rows 行数
	 * @param page 列数
	 * @return 对象集合
	 */
	public List<TotalIncome> selectByPage(int rows,int page);
	/**
	 * 按条件分页查询数据
	 * @param rows 行数
	 * @param page 列数
	 * @param condition 条件
	 * @return 集合对象
	 */
	public List<TotalIncome> findByPage(int rows,int page,String condition);
	/**
	 * 统计记录数
	 * @return 记录行数
	 */
	public int count();
	/**
	 * 按条件统计记录数
	 * @param condition 条件
	 * @return 筛选记录行数
	 */
	public int count(String condition);
}
