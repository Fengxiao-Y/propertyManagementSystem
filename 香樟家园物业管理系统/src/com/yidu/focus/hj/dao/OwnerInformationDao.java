package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.OwnerInformation;

/**
 * 
 * ���ܣ�ҵ����ӿ�
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��12������10:34:45
 */
public interface OwnerInformationDao {

	/**
	 * �������
	 * @param wage ҵ������
	 * @return Ӱ������
	 */
	public int add(OwnerInformation ownerInformation);
	/**
	 * �޸�����
	 * @param wage ����
	 * @return Ӱ������
	 */
	public int update(OwnerInformation ownerInformation);
	/**
	 * ɾ������
	 * @param wageid ���
	 * @return Ӱ������
	 */
	public int delete(String ownerId);
	/**
	 * ���ݷ��ݱ�Ų�ѯĳ������
	 * @param wageid ���
	 * @return ����
	 */
	public OwnerInformation getOwnerInformationById(String houseId);
	/**
	 * ��ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @return ���󼯺�
	 */
	public List<OwnerInformation> selectByPage(int rows,int page);
	/**
	 * ��������ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @param condition ����
	 * @return ���϶���
	 */
	public List<OwnerInformation> findByPage(int rows,int page,String condition);
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
	public int deleteName(String name);
	/**
	 * ���ݷ��ݱ�Ų�ѯĳ������
	 * @param wageid ���
	 * @return ����
	 */
	public OwnerInformation findOwnerInformationByName(String ownerName);
}
