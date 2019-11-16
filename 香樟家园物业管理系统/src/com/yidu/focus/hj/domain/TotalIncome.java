package com.yidu.focus.hj.domain;

import java.io.Serializable;

public class TotalIncome implements Serializable{

	private static final long serialVersionUID = -5191780543158057436L;
	//
	private String totalId;
	private double propertyFeeIncome;
	private double parkFeeIncome;
	private double otherServiceIncome;
	private double otherIncome;
	private double totalIncome;
	private String totalIncomeMonth;
	/**
	 * 默认构造方法
	 */
	public TotalIncome(){
		
	}
	/**
	 * 带参构造方法
	 * @param totalId
	 * @param propertyFeeIncome
	 * @param parkFeeIncome
	 * @param otherServiceIncome
	 * @param otherIncome
	 * @param totalIncome
	 * @param totalIncomeMonth
	 */
	public TotalIncome(String totalId, double propertyFeeIncome, double parkFeeIncome, double otherServiceIncome,
			double otherIncome, double totalIncome, String totalIncomeMonth) {
		super();
		this.totalId = totalId;
		this.propertyFeeIncome = propertyFeeIncome;
		this.parkFeeIncome = parkFeeIncome;
		this.otherServiceIncome = otherServiceIncome;
		this.otherIncome = otherIncome;
		this.totalIncome = totalIncome;
		this.totalIncomeMonth = totalIncomeMonth;
	}
	//自动生成setter/getter
	public String getTotalId() {
		return totalId;
	}
	public void setTotalId(String totalId) {
		this.totalId = totalId;
	}
	public double getPropertyFeeIncome() {
		return propertyFeeIncome;
	}
	public void setPropertyFeeIncome(double propertyFeeIncome) {
		this.propertyFeeIncome = propertyFeeIncome;
	}
	public double getParkFeeIncome() {
		return parkFeeIncome;
	}
	public void setParkFeeIncome(double parkFeeIncome) {
		this.parkFeeIncome = parkFeeIncome;
	}
	public double getOtherServiceIncome() {
		return otherServiceIncome;
	}
	public void setOtherServiceIncome(double otherServiceIncome) {
		this.otherServiceIncome = otherServiceIncome;
	}
	public double getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(double otherIncome) {
		this.otherIncome = otherIncome;
	}
	public double getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}
	public String getTotalIncomeMonth() {
		return totalIncomeMonth;
	}
	public void setTotalIncomeMonth(String totalIncomeMonth) {
		this.totalIncomeMonth = totalIncomeMonth;
	}
	
	
}
