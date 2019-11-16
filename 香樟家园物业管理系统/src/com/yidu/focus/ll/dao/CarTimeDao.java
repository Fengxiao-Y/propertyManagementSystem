package com.yidu.focus.ll.dao;

import java.util.List;

import com.yidu.focus.ll.domain.CarTime;


public interface CarTimeDao {
	/**
	 * ���
	 * @param carTime
	 * @return
	 */
	public int add(CarTime carTime);
	/**
	 * ɾ��
	 * @param Nub
	 * @return
	 */
	public int delete(int Nub);
	/**
	 * �޸�
	 * @param carTime
	 * @return
	 */
	public int update(CarTime carTime);
	
	/**
	 * ������źŲ��ҵ���������Ϣ
	 * @param deptNo
	 * @return
	 */
	public CarTime findById(int Nub);
	
	/**
	 * ��������
	 * @return
	 */
	public List<CarTime> findAll();
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����г�����Ϣ����
	 */
	public List<CarTime> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	public List<CarTime> findByPage(int rows,int page,String condition);

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
