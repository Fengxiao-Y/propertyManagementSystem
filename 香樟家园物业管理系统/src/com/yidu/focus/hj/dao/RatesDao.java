package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.Rates;

/**
 * 
 * ���ܣ����ʱ�ӿ�
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��12������10:34:45
 */
public interface RatesDao {

	/**
	 * �������
	 * @param houseInformation ����
	 * @return Ӱ������
	 */
	public int add(Rates rates);
	/**
	 * �޸�����
	 * @param houseInformation ����
	 * @return Ӱ������
	 */
	public int update(Rates rates);
	/**
	 * ɾ������
	 * @param houseId ���
	 * @return Ӱ������
	 */
	public int delete(int itemId);
	/**
	 * ����Ա��������ѯĳ������
	 * @param houseId ���
	 * @return ����
	 */
	public Rates getRatesById(int itemId);
	/**
	 * ��ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @return ���󼯺�
	 */
	public List<Rates> selectByPage(int rows,int page);
	/**
	 * ��������ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @param condition ����
	 * @return ���϶���
	 */
	public List<Rates> findByPage(int rows,int page,String condition);
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
