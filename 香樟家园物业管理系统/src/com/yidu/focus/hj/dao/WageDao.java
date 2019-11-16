package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.Wage;

/**
 * 
 * ���ܣ����ʱ�ӿ�
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��12������10:34:45
 */
public interface WageDao {

	/**
	 * �������
	 * @param wage ���ʶ���
	 * @return Ӱ������
	 */
	public int add(Wage wage);
	/**
	 * �޸�����
	 * @param wage ����
	 * @return Ӱ������
	 */
	public int update(Wage wage);
	/**
	 * ɾ������
	 * @param wageid ���
	 * @return Ӱ������
	 */
	public int delete(int wageid);
	/**
	 * ����Ա��������ѯĳ������
	 * @param wageid ���
	 * @return ����
	 */
	public Wage getWageById(String empName);
	/**
	 * ��ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @return ���󼯺�
	 */
	public List<Wage> selectByPage(int rows,int page);
	/**
	 * ��������ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @param condition ����
	 * @return ���϶���
	 */
	public List<Wage> findByPage(int rows,int page,String condition);
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
