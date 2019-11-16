package com.yidu.focus.hxl.dao;

import java.util.List;

import com.yidu.focus.hxl.domain.EqCheck;


public interface EqCheckDao {
	/**
     * �����Ϣ
     * @param attendance
     * @return
     */
	public int add(EqCheck eqCheck);
	/**
	 * ɾ��
	 * @param attendId
	 * @return
	 */
	public int deleteById(int checkId);
	/**
	 * �޸�����
	 * @param attendId
	 * @return
	 */
	public int update(EqCheck eqCheck);
	/**
	 * ���ݿ��ڱ�Ų��ҵ���Ա����Ϣ
	 * @param attendId
	 * @return
	 */
	public EqCheck findById(int checkId);
	
	
	/**
	 * ���ݿ��ڱ�Ų��ҵ���Ա����Ϣ
	 * @param attendId
	 * @return
	 */
	public List<EqCheck> findByPage(int rows,int page);
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	public List<EqCheck> findByPage(int rows,int page,String condition);
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
