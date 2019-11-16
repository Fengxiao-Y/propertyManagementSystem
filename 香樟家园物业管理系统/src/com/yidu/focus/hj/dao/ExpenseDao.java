package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.Expense;

public interface ExpenseDao {

	/**
	 * 添加
	 * @param expense
	 * @return
	 */
	public int add(Expense expense);
	/**
	 * 修改
	 * @param expense
	 * @return
	 */
	public int update(Expense expense);
	/**
	 * 删除
	 * @param expenseId
	 * @return
	 */
	public int delete(int expenseId);
	/**
	 * 查询指定行数据
	 * @param expenseId
	 * @return
	 */
	public Expense getIncomeById(int expenseId);
	/**
	 * 按条件分页查询
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<Expense> findByPage(int rows,int page,String condition);
	/**
	 * 按条件统计记录数
	 * @param condition
	 * @return
	 */
	public int count(String condition);
	/**
	 * 统计
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<Expense> findTotal(int rows,int page,String condition);
}
