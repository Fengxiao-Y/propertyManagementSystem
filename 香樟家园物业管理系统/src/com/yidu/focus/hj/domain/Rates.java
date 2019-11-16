package com.yidu.focus.hj.domain;

import java.io.Serializable;

public class Rates implements Serializable{

	private static final long serialVersionUID = -8177583897406945982L;
	//收费标准表
	private int itemId;
	private String itemName;
	private String itemDesc;
	private double ratesMoney;
	/**
	 * 默认构造方法
	 */
	public Rates(){
		
	}
	/**
	 * 带参构造方法
	 * @param itemId
	 * @param itemName
	 * @param itemDesc
	 * @param ratesMoney
	 */
	public Rates(int itemId, String itemName, String itemDesc, double ratesMoney) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.ratesMoney = ratesMoney;
	}
	/**
	 * 缺损构造方法
	 * @param itemName
	 * @param itemDesc
	 * @param ratesMoney
	 */
	public Rates(String itemName, String itemDesc, double ratesMoney) {
		super();
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.ratesMoney = ratesMoney;
	}
	//自动生成setter/getter
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public double getRatesMoney() {
		return ratesMoney;
	}
	public void setRatesMoney(double ratesMoney) {
		this.ratesMoney = ratesMoney;
	}
	
	
}
