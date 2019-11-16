package com.yidu.focus.ll.dao;

import java.util.List;

import com.yidu.focus.ll.domain.Attendance;

public interface AttendanceDao {
    /**
     * �����Ϣ
     * @param attendance
     * @return
     */
	public int add(Attendance attendance);
	
	/**
	 * ɾ��
	 * @param attendId
	 * @return
	 */
	public int delete(int attendId);
	
	/**
	 * �޸�����
	 * @param attendId
	 * @return
	 */
	public int update(Attendance attendance);
	
	/**
	 * ���ݿ��ڱ�Ų��ҵ���Ա����Ϣ
	 * @param attendId
	 * @return
	 */
	public Attendance findById(int attendId);
	
	/**
	 * ��������
	 * @return
	 */
	public List<Attendance> findAll();
	
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ������Ա����Ϣ����
	 */
	public List<Attendance> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	public List<Attendance> findByPage(int rows,int page,String condition);

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
