package com.yidu.focus.yfx.dao;

import java.util.List;

import com.yidu.focus.yfx.domain.Repairs;

/**
 * 
 * ���ܣ����complain����в��������ݲ�ӿ�
 * ��д�ߣ�Focus
 * ���ڣ�2019��10��14������12:17:35
 * �汾��1.0
 */
public interface RepairsDao {
	/**
	 * ������ݣ������޶����е����ݱ��浽�������ݿ����
	 * @param complain ���޶���
	 * @return Ӱ��������1�������ɹ���0������ʧ�ܣ�
	 */
	public int add(Repairs repairs);
	/**
	 * ���ݱ��ޱ��ɾ�����ݿ���е�����
	 * @param comId ���ޱ��
	 * @return Ӱ��������1�������ɹ���0������ʧ�ܣ�
	 */
	public int deleteById(int repId);
	/**
	 * �޸����ݣ������޶����е����ݸ��µ����ݿ����
	 * @param complain ���޶���
	 * @return Ӱ��������1�������ɹ���0������ʧ�ܣ�
	 */
	public int update(Repairs complain);
	
	/**
	 * ���ݱ��ޱ�Ų��ҵ��α�����Ϣ
	 * @param comId ���ޱ��
	 * @return ���޶���
	 */
	public Repairs findById(int repId);
	
	/**
	 * �������б�����Ϣ
	 * @return ���޼��϶���
	 */
	public List<Repairs> findAll();
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����б�����Ϣ����
	 */
	public List<Repairs> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ��������
	 * @return ָ��ҳ�����б�����Ϣ����
	 */
	public List<Repairs> findByPage(int rows,int page,String condition);

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
	public int count(String repairs);
	/**
	 * ���ݱ����˲��ҵ��α�����Ϣ
	 * @param comId ���ޱ��
	 * @return ���޶���
	 */
	public List<Repairs> findByName(String ownerName);
}
