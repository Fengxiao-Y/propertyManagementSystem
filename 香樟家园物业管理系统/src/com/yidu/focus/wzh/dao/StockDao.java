
package com.yidu.focus.wzh.dao;

import java.util.List;

import com.yidu.focus.wzh.domain.Stock;



/**
 * ���ܣ�Stock������ݿ�ӿ�
 * ���ߣ���־��
 * ���ڣ�2019��10��11������5:12:22
 * �汾��1.0
 */
public interface StockDao {
	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param stock ���ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int add(Stock stock);
	
	/**
	 * ��ʵ���������µ����ݿ�
	 * @param cust ���ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int update(Stock stock);
	
	/**
	 * ���տ���ţ������������ݿ���в�������
	 * @param stockId �����
	 * @return ���ʵ�����
	 */
	public Stock findById(int stockId);
	/**
	 * �鿴����
	 * @return ������ʾ�Ŀ����Ϣ
	 */
	public List<Stock> findAll();
	
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����п����Ϣ����
	 */
	public List<Stock> findByPage(int rows,int page);

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return ָ��ҳ�е����ݼ���
	 */
	public List<Stock> findByPage(int rows,int page,String condition);

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
	
	/**
	 * ������Ʒ�������ݿ���в�������
	 * @param goodsName ��Ʒ����
	 * @return
	 */
	public Stock findByName(String goodsName);
	
}
