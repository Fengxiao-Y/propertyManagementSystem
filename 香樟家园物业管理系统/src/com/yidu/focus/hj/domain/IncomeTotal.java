package com.yidu.focus.hj.domain;

import java.io.Serializable;

public class IncomeTotal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 821237464880915799L;
	//��������
	private String source;
	//������
	private double money;
	//����ʱ��
	private String source_date;
	//�ܼ�
	private double moneySum;
	public IncomeTotal() {
		super();
	}

	public IncomeTotal(String source, double money, String source_date) {
		super();
		this.source = source;
		this.money = money;
		this.source_date = source_date;
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

	public double getMoneySum() {
		return moneySum;
	}

	public void setMoneySum(double moneySum) {
		this.moneySum = moneySum;
	}
	
	
}
