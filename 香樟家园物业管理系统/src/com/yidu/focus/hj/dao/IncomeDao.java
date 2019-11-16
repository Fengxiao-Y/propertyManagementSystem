package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.Income;

public interface IncomeDao {
	/**
	 * 添加
	 * @param income
	 * @return
	 */
	public int add(Income income);
	/**
	 * 修改
	 * @param income
	 * @return
	 */
	public int update(Income income);
	/**
	 * 删除
	 * @param incomeId
	 * @return
	 */
	public int delete(int incomeId);
	/**
	 * 查询指定行数据
	 * @param incomeId
	 * @return
	 */
	public Income getIncomeById(int incomeId);
	/**
	 * 按条件分页查询
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<Income> findByPage(int rows,int page,String condition);
	/**
	 * 按条件统计记录数
	 * @param condition
	 * @return
	 */
	public int count(String condition);
}
