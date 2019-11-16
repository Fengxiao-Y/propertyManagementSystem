package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.OtherServiceIncome;

/**
 * 
 * ���ܣ����ʱ�ӿ�
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��12������10:34:45
 */
public interface OtherServiceIncomeDao {

	/**
	 * �������
	 * @param payments ���ʶ���
	 * @return Ӱ������
	 */
	public int add(OtherServiceIncome otherServiceIncome);
	/**
	 * �޸�����
	 * @param payments ����
	 * @return Ӱ������
	 */
	public int update(OtherServiceIncome otherServiceIncome);
	/**
	 * ɾ������
	 * @param paymId ���
	 * @return Ӱ������
	 */
	public int delete(String osIcome);
	/**
	 * ����Ա��������ѯĳ������
	 * @param paymId ���
	 * @return ����
	 */
	public OtherServiceIncome getOtherServiceIncomeById(String osIcome);
	/**
	 * ��ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @return ���󼯺�
	 */
	public List<OtherServiceIncome> selectByPage(int rows,int page);
	/**
	 * ��������ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @param condition ����
	 * @return ���϶���
	 */
	public List<OtherServiceIncome> findByPage(int rows,int page,String condition);
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
