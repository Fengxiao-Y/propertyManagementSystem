package com.yidu.focus.hj.domain;

import java.io.Serializable;

public class Income implements Serializable{

	private static final long serialVersionUID = -1589961172762733527L;
	//序号
	private int incomeId;
	//来源
	private String source;
	//金额
	private double money;
	//来源时间
	private String source_date;
	/**
	 * 默认构造方法
	 */
	public Income(){
		
	}
	/**
	 * 带参构造方法 
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
	 * 带参构造方法
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
