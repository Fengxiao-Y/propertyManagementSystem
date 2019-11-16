package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.Income;

public interface IncomeDao {
	/**
	 * ���
	 * @param income
	 * @return
	 */
	public int add(Income income);
	/**
	 * �޸�
	 * @param income
	 * @return
	 */
	public int update(Income income);
	/**
	 * ɾ��
	 * @param incomeId
	 * @return
	 */
	public int delete(int incomeId);
	/**
	 * ��ѯָ��������
	 * @param incomeId
	 * @return
	 */
	public Income getIncomeById(int incomeId);
	/**
	 * ��������ҳ��ѯ
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	public List<Income> findByPage(int rows,int page,String condition);
	/**
	 * ������ͳ�Ƽ�¼��
	 * @param condition
	 * @return
	 */
	public int count(String condition);
}
