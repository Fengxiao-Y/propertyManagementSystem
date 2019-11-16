package com.yidu.focus.hxl.dao;

import java.util.List;

import com.yidu.focus.hxl.domain.CarInformation;

public interface CarInformationDao {
	 /**
     * �����Ϣ
     * @param attendance
     * @return
     */
	public int add(CarInformation carInformation);
	/**
	 * ɾ��
	 * @param attendId
	 * @return
	 */
	public int deleteById(String carId);
	/**
	 * �޸�����
	 * @param attendId
	 * @return
	 */
	public int update(CarInformation carInformation);
	/**
	 * ���ݿ��ڱ�Ų��ҵ���Ա����Ϣ
	 * @param attendId
	 * @return
	 */
	public CarInformation findById(String carId);
	
	
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����г�����Ϣ����
	 */
	public List<CarInformation> findByPage(int rows,int page);
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	public List<CarInformation> findByPage(int rows,int page,String condition);
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
}
