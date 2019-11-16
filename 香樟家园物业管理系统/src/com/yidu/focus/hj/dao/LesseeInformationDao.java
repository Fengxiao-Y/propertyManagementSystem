package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.LesseeInformation;

/**
 * 
 * ���ܣ����ʱ�ӿ�
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��12������10:34:45
 */
public interface LesseeInformationDao {

	/**
	 * �������
	 * @param lesseeInformation ����
	 * @return Ӱ������
	 */
	public int add(LesseeInformation lesseeInformation);
	/**
	 * �޸�����
	 * @param lesseeInformation ����
	 * @return Ӱ������
	 */
	public int update(LesseeInformation lesseeInformation);
	/**
	 * ɾ������
	 * @param paymId ���
	 * @return Ӱ������
	 */
	public int delete(int leaseContractId);
	/**
	 * ����Ա��������ѯĳ������
	 * @param paymId ���
	 * @return ����
	 */
	public LesseeInformation getLesseeInformationById(String houseId);
	/**
	 * ��ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @return ���󼯺�
	 */
	public List<LesseeInformation> selectByPage(int rows,int page);
	/**
	 * ��������ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @param condition ����
	 * @return ���϶���
	 */
	public List<LesseeInformation> findByPage(int rows,int page,String condition);
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
	public int deleteA(String houseId);
}
