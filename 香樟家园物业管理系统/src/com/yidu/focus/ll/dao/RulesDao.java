package com.yidu.focus.ll.dao;

import java.util.List;

import com.yidu.focus.ll.domain.Rules;

public interface RulesDao {
	/**
	 * ���
	 * @param rules
	 * @return
	 */
	public int add(Rules rules);
	/**
	 * ɾ��
	 * @param rulesId
	 * @return
	 */
	public int delete(String rulesId);
	/**
	 * �޸�
	 * @param rules
	 * @return
	 */
	public int update(Rules rules);
	
	/**
	 * �����ļ���Ų��ҵ�����Ϣ
	 * @param rulesId
	 * @return
	 */
	public Rules findById(String rulesId);
	
	/**
	 * ��������
	 * @return
	 */
	public List<Rules> findAll();
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����в�����Ϣ����
	 */
	public List<Rules> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	public List<Rules> findByPage(int rows,int page,String condition);

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
