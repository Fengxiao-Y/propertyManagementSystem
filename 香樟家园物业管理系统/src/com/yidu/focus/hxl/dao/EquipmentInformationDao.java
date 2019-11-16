package com.yidu.focus.hxl.dao;

import java.util.List;

import com.yidu.focus.hxl.domain.EquipmentInformation;


public interface EquipmentInformationDao {
	 /**
     * �����Ϣ
     * @param attendance
     * @return
     */
	public int add(EquipmentInformation EquipmentInformation);
	/**
	 * ɾ��
	 * @param attendId
	 * @return
	 */
	public int deleteById(String eqId);
	/**
	 * �޸�����
	 * @param attendId
	 * @return
	 */
	public int update(EquipmentInformation EquipmentInformation);
	/**
	 * ���ݿ��ڱ�Ų��ҵ���Ա����Ϣ
	 * @param attendId
	 * @return
	 */
	public EquipmentInformation findById(String eqId);
	
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	public List<EquipmentInformation> findByPage(int rows,int page);
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	public List<EquipmentInformation> findByPage(int rows,int page,String condition);
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
