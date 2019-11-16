package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.HouseInformation;

/**
 * 
 * ���ܣ����ʱ�ӿ�
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��12������10:34:45
 */
public interface HouseInformationDao {

	/**
	 * �������
	 * @param houseInformation ����
	 * @return Ӱ������
	 */
	public int add(HouseInformation houseInformation);
	/**
	 * �޸�����
	 * @param houseInformation ����
	 * @return Ӱ������
	 */
	public int update(HouseInformation houseInformation);
	/**
	 * ɾ������
	 * @param houseId ���
	 * @return Ӱ������
	 */
	public int delete(String houseId);
	/**
	 * ����Ա��������ѯĳ������
	 * @param houseId ���
	 * @return ����
	 */
	public HouseInformation getHouseInformationById(String houseId);
	/**
	 * ��ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @return ���󼯺�
	 */
	public List<HouseInformation> selectByPage(int rows,int page);
	/**
	 * ��������ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @param condition ����
	 * @return ���϶���
	 */
	public List<HouseInformation> findByPage(int rows,int page,String condition);
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
	/**
	 * ����������ѯĳ������
	 * @param ownerName ҵ������
	 * @return ����
	 */
	public HouseInformation getHouseInformationByName(String ownerName);
	public int findIdCard(String name);
}
