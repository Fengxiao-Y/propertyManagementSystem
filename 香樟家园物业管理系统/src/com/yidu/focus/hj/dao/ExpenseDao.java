package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.Expense;

public interface ExpenseDao {

	/**
	 * ���
	 * @param expense
	 * @return
	 */
	public int add(Expense expense);
	/**
	 * �޸�
	 * @param expense
	 * @return
	 */
	public int update(Expense expense);
	/**
	 * ɾ��
	 * @param expenseId
	 * @return
	 */
	public int delete(int expenseId);
	/**
	 * ��ѯָ��������
	 * @param expenseId
	 * @return
	 */
	public Expense getIncomeById(int expenseId);
	/**
	 * ��������ҳ��ѯ
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<Expense> findByPage(int rows,int page,String condition);
	/**
	 * ������ͳ�Ƽ�¼��
	 * @param condition
	 * @return
	 */
	public int count(String condition);
	/**
	 * ͳ��
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<Expense> findTotal(int rows,int page,String condition);
}
