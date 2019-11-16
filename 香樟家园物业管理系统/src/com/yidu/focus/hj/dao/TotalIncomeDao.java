package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.TotalIncome;

/**
 * 
 * ���ܣ����ʱ�ӿ�
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��12������10:34:45
 */
public interface TotalIncomeDao {

	/**
	 * �������
	 * @param payments ���ʶ���
	 * @return Ӱ������
	 */
	public int add(TotalIncome totalIncome);
	/**
	 * �޸�����
	 * @param payments ����
	 * @return Ӱ������
	 */
	public int update(TotalIncome totalIncome);
	/**
	 * ɾ������
	 * @param paymId ���
	 * @return Ӱ������
	 */
	public int delete(String totalId);
	/**
	 * ����Ա��������ѯĳ������
	 * @param paymId ���
	 * @return ����
	 */
	public TotalIncome getTotalIncomeById(String totalId);
	/**
	 * ��ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @return ���󼯺�
	 */
	public List<TotalIncome> selectByPage(int rows,int page);
	/**
	 * ��������ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @param condition ����
	 * @return ���϶���
	 */
	public List<TotalIncome> findByPage(int rows,int page,String condition);
	/**
	 * ͳ�Ƽ�¼��
	 * @return ��¼����
	 */
	public int count();
	/**
	 * ������ͳ�Ƽ�¼��
	 * @param condition ����
	 * @return ɸѡ��¼����
	 */
	public int count(String condition);
}
