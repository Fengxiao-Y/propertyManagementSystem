
package com.yidu.focus.wzh.dao;

import java.util.List;

import com.yidu.focus.wzh.domain.Procurement;

/**
 * ���ܣ�procurement�����ݿ�ӿ�
 * ���ߣ���־��
 * ���ڣ�2019��10��11������6:17:28
 * �汾��1.0
 */
public interface ProcurementDao {
	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param procurement ��̨��¼ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int add(Procurement procurement);
	
	/**
	 * ��ʵ���������µ����ݿ�
	 * @param procurement ��̨��¼ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int update(Procurement procurement);
	
	/**
	 * ���պ�̨��¼��ţ������������ݿ���в�������
	 * @param cid ��̨��¼���
	 * @return ��̨��¼ʵ�����
	 */
	public Procurement findById(String proId);
	
	
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ������Ա����Ϣ����
	 */
	public List<Procurement> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return ָ��ҳ�е����ݼ���
	 */
	public List<Procurement> findByPage(int rows,int page,String condition);

	/**
	 * ͳ�Ƽ�¼��
	 * @return �������м�¼����
	 */
	public int count();
	
	/**
	 * ͳ�Ʒ��������ļ�¼��
	 * @param condition ����
	 * @return ���ط�����������������
	 */
	public int count(String condition);

	public int delete(String proId);
}

