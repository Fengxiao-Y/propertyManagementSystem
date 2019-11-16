
package com.yidu.focus.wzh.dao;

import java.util.List;

import com.yidu.focus.wzh.domain.Users;

/**
 * ���ܣ�users������ݿ�ӿ�
 * ���ߣ���־��
 * ���ڣ�2019��10��11������5:20:18
 * �汾��1.0
 */
public interface UsersDao {
	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param uId �ͻ�ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int doRegister(Users users);
	/**
	 * ���տͻ���ţ�������ɾ������
	 * @param uId �ͻ����
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int delete(int uId);
	/**
	 * ��ʵ���������µ����ݿ�
	 * @param Users �û�ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int update(Users users);
	
	/**
	 * ��¼����
	 * @param uName �û���
	 * @param uPassword �û�����
	 * @return �û���ʵ�����
	 */
	public Users doLogin(String uName,String uPassword);
	/**
	 * ��¼����
	 * @param uIphone ע�����
	 * @return �û���ʵ�����
	 */
	public Users findByUIphone(String uIphone);
	
	
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ������Ա����Ϣ����
	 */
	public List<Users> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return ָ��ҳ�е����ݼ���
	 */
	public List<Users> findByPage(int rows,int page,String condition);

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

	public Users findById(int uId);
}
