
package com.yidu.focus.wzh.domain;

import java.io.Serializable;

/**
 * ���ܣ�stock��ʵ����
 * ���ߣ���־��
 * ���ڣ�2019��10��11������4:22:05
 * �汾��1.0
 */
public class Stock implements Serializable{

	private static final long serialVersionUID = -4247177927299816866L;
	private int stockId;	// �����
	private String goodsName;//��Ʒ����
	private int stockNum;//�����
	
	
	/**
	 * �����������Ĺ��췽��
	 * @param goodsName
	 * @param stockNum
	 */
	public Stock(String goodsName, int stockNum) {
		super();
		this.goodsName = goodsName;
		this.stockNum = stockNum;
	}
	/**
	 * ���εĹ��췽��
	 * @param stockId  ��Ʒ���
	 * @param goodsName ��Ʒ����
	 * @param stockNum	�����
	 */
	public Stock(int stockId, String goodsName, int stockNum) {
		super();
		this.stockId = stockId;
		this.goodsName = goodsName;
		this.stockNum = stockNum;
		
	}
	/**
	 * Ĭ�ϵĹ��췽��
	 */
	public Stock() {
		super();
	}
	/**
	 * ������Ʒ���ƵĹ��췽��
	 * @param stockId
	 * @param stockNum
	 */
	public Stock(int stockId, int stockNum) {
		super();
		this.stockId = stockId;
		this.stockNum = stockNum;
	}
	//�Զ�����getter/setter����
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getStockNum() {
		return stockNum;
	}
	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}

}
