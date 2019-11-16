
package com.yidu.focus.wzh.domain;

import java.io.Serializable;

/**
 * ���ܣ�Procurement��ʵ����
 * ���ߣ���־��
 * ���ڣ�2019��10��11������4:15:53
 * �汾��1.0
 */
public class Procurement implements Serializable{

	private static final long serialVersionUID = 4505275502621113327L;
	private String proId;	//��¼���
	private String proName;	// �ɹ���
	private String goodsName; // ��Ʒ����
	private String proTime;	// ʱ��
	private int proNum; //����
	private double proPrice; //����
	private  double proTolal; // �ܼ�
	
	
	
	/**
	 * Ĭ�ϵĹ��췽��
	 */
	public Procurement() {
		super();
	}
	/**
	 * ���εĹ��췽��
	 * @param proId
	 * @param proName
	 * @param goodsName
	 * @param proTime
	 * @param proNum
	 * @param proPrice
	 * @param proTolal
	 */
	public Procurement(String proId, String proName, String goodsName, String proTime, int proNum, double proPrice,
			double proTolal) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.goodsName = goodsName;
		this.proTime = proTime;
		this.proNum = proNum;
		this.proPrice = proPrice;
		this.proTolal = proTolal;
	
	}
	//����getter/setter����
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getProTime() {
		return proTime;
	}
	public void setProTime(String proTime) {
		this.proTime = proTime;
	}
	public int getProNum() {
		return proNum;
	}
	public void setProNum(int proNum) {
		this.proNum = proNum;
	}
	public double getProPrice() {
		return proPrice;
	}
	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}
	public double getProTolal() {
		return proTolal;
	}
	public void setProTolal(double proTolal) {
		this.proTolal = proTolal;
	}
	
}
