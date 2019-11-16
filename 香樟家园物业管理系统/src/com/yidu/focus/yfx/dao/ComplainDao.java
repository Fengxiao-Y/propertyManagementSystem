package com.yidu.focus.yfx.dao;

import java.util.List;

import com.yidu.focus.yfx.domain.Complain;

/**
 * 
 * ���ܣ����complain����в��������ݲ�ӿ�
 * ��д�ߣ�Focus
 * ���ڣ�2019��10��14������12:17:35
 * �汾��1.0
 */
public interface ComplainDao {
	/**
	 * ������ݣ���Ͷ�߶����е����ݱ��浽Ͷ�����ݿ����
	 * @param complain Ͷ�߶���
	 * @return Ӱ��������1�������ɹ���0������ʧ�ܣ�
	 */
	public int add(Complain complain);
	/**
	 * ����Ͷ�߱��ɾ�����ݿ���е�����
	 * @param comId Ͷ�߱��
	 * @return Ӱ��������1�������ɹ���0������ʧ�ܣ�
	 */
	public int deleteById(int comId);
	/**
	 * �޸����ݣ���Ͷ�߶����е����ݸ��µ����ݿ����
	 * @param complain Ͷ�߶���
	 * @return Ӱ��������1�������ɹ���0������ʧ�ܣ�
	 */
	public int update(Complain complain);
	
	/**
	 * ����Ͷ�߱�Ų��ҵ���Ͷ����Ϣ
	 * @param comId Ͷ�߱��
	 * @return Ͷ�߶���
	 */
	public Complain findById(int comId);
	
	/**
	 * ��������Ͷ����Ϣ
	 * @return Ͷ�߼��϶���
	 */
	public List<Complain> findAll();
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ������Ͷ����Ϣ����
	 */
	public List<Complain> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ��������
	 * @return ָ��ҳ������Ͷ����Ϣ����
	 */
	public List<Complain> findByPage(int rows,int page,String condition);

	/**
	 * ͳ�Ƽ�¼��
	 * @return �������м�¼����
	 */
	public int count();
	
	/**
	 * ͳ�Ʒ��������ļ�¼��
	 * @param condition ͳ������
	 * @return ���з���ͳ��������¼����
	 */
	public int count(String condition);
	/**
	 * ����Ͷ���˺Ų��ҵ���Ͷ����Ϣ
	 * @param comId Ͷ�߱��
	 * @return Ͷ�߶���
	 */
	public List<Complain> findByName(String ownerName);
}
