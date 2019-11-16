package com.yidu.focus.hxl.dao;

import java.util.List;

import com.yidu.focus.hxl.domain.VisitorRegister;



public interface VisitorRegisterDao {
	 /**
     * �����Ϣ
     * @param attendance
     * @return
     */
	public int add(VisitorRegister visitorRegister);
	/**
	 * ɾ��
	 * @param attendId
	 * @return
	 */
	public int deleteById(int visitorId);
	/**
	 * �޸�����
	 * @param attendId
	 * @return
	 */
	public int update(VisitorRegister visitorRegister);
	
	/**
	 * ���ݿ��ڱ�Ų��ҵ���Ա����Ϣ
	 * @param attendId
	 * @return
	 */
	public VisitorRegister findById(int visitorId);


	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����г�����Ϣ����
	 */
	public List<VisitorRegister> findByPage(int rows,int page);
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	public List<VisitorRegister> findByPage(int rows,int page,String condition);
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
