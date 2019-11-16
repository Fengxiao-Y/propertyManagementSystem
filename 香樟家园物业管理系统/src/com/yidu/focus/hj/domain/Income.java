package com.yidu.focus.hj.domain;

import java.io.Serializable;

public class Income implements Serializable{

	private static final long serialVersionUID = -1589961172762733527L;
	//���
	private int incomeId;
	//��Դ
	private String source;
	//���
	private double money;
	//��Դʱ��
	private String source_date;
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public Income(){
		
	}
	/**
	 * ���ι��췽�� 
	 * @param incomeId
	 * @param source
	 * @param money
	 * @param source_date
	 */
	public Income(int incomeId, String source, double money, String source_date) {
		super();
		this.incomeId = incomeId;
		this.source = source;
		this.money = money;
		this.source_date = source_date;
	}
	/**
	 * ���ι��췽��
	 * @param source
	 * @param money
	 * @param source_date
	 */
	public Income(String source, double money, String source_date) {
		super();
		this.source = source;
		this.money = money;
		this.source_date = source_date;
	}
	public int getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getSource_date() {
		return source_date;
	}
	public void setSource_date(String source_date) {
		this.source_date = source_date;
	}
	
}
