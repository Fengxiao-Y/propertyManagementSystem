package com.yidu.focus.hxl.dao;

import java.util.List;

import com.yidu.focus.hxl.domain.Regulatory;


public interface RegulatoryDao {
	 /**
     * �����Ϣ
     * @param attendance
     * @return
     */
	public int add(Regulatory regulatory);
	/**
	 * ɾ��
	 * @param attendId
	 * @return
	 */
	public int deleteById(String fileId);
	/**
	 * �޸�����
	 * @param attendId
	 * @return
	 */
	public int update(Regulatory regulatory);
	/**
	 * ���ݿ��ڱ�Ų��ҵ���Ա����Ϣ
	 * @param attendId
	 * @return
	 */
	public Regulatory findById(String fileId);
	
	
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����г�����Ϣ����
	 */
	public List<Regulatory> findByPage(int rows,int page);
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	public List<Regulatory> findByPage(int rows,int page,String condition);
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
