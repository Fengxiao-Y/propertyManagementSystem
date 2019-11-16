package com.yidu.focus.ll.dao;

import java.util.List;

import com.yidu.focus.ll.domain.ParkingInformation;

public interface ParkingInformationDao {

	/**
	 * ���
	 * @param parkingInformation
	 * @return
	 */
	public int add(ParkingInformation parkingInformation);
	/**
	 * ɾ��
	 * @param parkId
	 * @return
	 */
	public int delete(String parkId);
	/**
	 * �޸�
	 * @param parkingInformation
	 * @return
	 */
	public int update(ParkingInformation parkingInformation);

	/**
	 * ���ݳ�λ��Ų��ҵ���������Ϣ
	 * @param parkId
	 * @return
	 */
	public ParkingInformation findById(String parkId);

	/**
	 * ��������
	 * @return
	 */
	public List<ParkingInformation> findAll();
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����г�����Ϣ����
	 */
	public List<ParkingInformation> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	public List<ParkingInformation> findByPage(int rows,int page,String condition);

	/**
	 * ͳ�Ƽ�¼��
	 * @return �������м�¼����
	 */
	public int count();

	/**
	 * ͳ�Ʒ��������ļ�¼��
	 * @param condition ����
	 * @return
	 */
	public int count(String condition);
	
	/**
	 * ���ݷ��ű�Ų��ҵ�����λ��Ϣ
	 * @param parkId
	 * @return
	 */
	public ParkingInformation findByName(String houseId);
}
