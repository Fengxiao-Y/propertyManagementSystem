package com.yidu.focus.ll.dao;

import java.util.List;

import com.yidu.focus.ll.domain.Dept;

public interface DeptDao {
	/**
	 * ���
	 * @param dept
	 * @return
	 */
	public int add(Dept dept);
	/**
	 * ɾ��
	 * @param deptNo
	 * @return
	 */
	public int delete(int deptNo);
	/**
	 * �޸�
	 * @param dept
	 * @return
	 */
	public int update(Dept dept);
	
	/**
	 * ����Ա����Ų��ҵ���Ա����Ϣ
	 * @param deptNo
	 * @return
	 */
	public Dept findById(int deptNo);
	
	/**
	 * ��������
	 * @return
	 */
	public List<Dept> findAll();
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����в�����Ϣ����
	 */
	public List<Dept> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	public List<Dept> findByPage(int rows,int page,String condition);

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
