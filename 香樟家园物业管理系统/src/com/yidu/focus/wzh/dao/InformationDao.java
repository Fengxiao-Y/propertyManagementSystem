
package com.yidu.focus.wzh.dao;

import java.util.List;

import com.yidu.focus.wzh.domain.Information;

/**
 * ���ܣ�information������ݿ�ӿ�
 * ���ߣ���־��
 * ���ڣ�2019��10��11������6:07:03
 * �汾��1.0
 */
public interface InformationDao {
	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param information �ͻ�ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int doRegister(Information information);
	/**
	 * ���տͻ���ţ�������ɾ������
	 * @param cid �ͻ����
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int delete(String backId);
	/**
	 * ��ʵ���������µ����ݿ�
	 * @param information ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int update(Information information);
	
	/**
	 * ��¼����
	 * @param backId ��¼�ʺ�
	 * @param backId ��¼����
	 * @return ʵ�����
	 */
	public Information doLogin(String empName,String backPwd);
	
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ������Ա����Ϣ����
	 */
	public List<Information> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return ָ��ҳ�е����ݼ���
	 */
	public List<Information> findByPage(int rows,int page,String condition);

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
	
}
