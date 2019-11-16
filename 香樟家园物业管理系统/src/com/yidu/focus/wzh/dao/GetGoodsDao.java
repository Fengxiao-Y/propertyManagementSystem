
package com.yidu.focus.wzh.dao;

import java.util.List;

import com.yidu.focus.wzh.domain.GetGoods;

/**
 * ���ܣ�Getgoods������ݿ�ӿ�
 * ���ߣ���־��
 * ���ڣ�2019��10��11������6:14:24
 * �汾��1.0
 */
public interface GetGoodsDao {
	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param GetGoods ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int add(GetGoods getgoods);
	
	/**
	 * ��ʵ���������µ����ݿ�
	 * @param GetGoods ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int update(GetGoods getgoods);
	/**
	 * ���տͻ���ţ������������ݿ���в�������
	 * @param ggid ���ϱ��
	 * @return ʵ�����
	 */
	public GetGoods findById(int ggId);
	
	
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ������Ա����Ϣ����
	 */
	public List<GetGoods> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return ָ��ҳ�е����ݼ���
	 */
	public List<GetGoods> findByPage(int rows,int page,String condition);

	/**
	 * ͳ�Ƽ�¼��
	 * @return rows �������м�¼����
	 */
	public int count();
	
	/**
	 * ͳ�Ʒ��������ļ�¼��
	 * @param condition ����
	 * @return rows ���ط�����������������
	 */
	public int count(String condition);

	public int delete(int ggId);
}
