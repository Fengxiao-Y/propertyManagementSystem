package com.yidu.focus.hj.domain;

import java.io.Serializable;

public class OtherServiceIncome implements Serializable{

	private static final long serialVersionUID = -5192831905787988751L;
	private String osIcome;
	private double materialCost;
	private double labor;
	private double other;
	private double osmoney;
	private String osmonth;
	/**
	 * 默认构造方法
	 */
	public OtherServiceIncome(){
		
	}
	/**
	 * 带参构造方法
	 * @param osIcome
	 * @param materialCost
	 * @param labor
	 * @param other
	 * @param osmoney
	 * @param osmonth
	 */
	public OtherServiceIncome(String osIcome, double materialCost, double labor, double other, double osmoney,
			String osmonth) {
		super();
		this.osIcome = osIcome;
		this.materialCost = materialCost;
		this.labor = labor;
		this.other = other;
		this.osmoney = osmoney;
		this.osmonth = osmonth;
	}
	//自动生成setter/getter
	public String getOsIcome() {
		return osIcome;
	}
	public void setOsIcome(String osIcome) {
		this.osIcome = osIcome;
	}
	public double getMaterialCost() {
		return materialCost;
	}
	public void setMaterialCost(double materialCost) {
		this.materialCost = materialCost;
	}
	public double getLabor() {
		return labor;
	}
	public void setLabor(double labor) {
		this.labor = labor;
	}
	public double getOther() {
		return other;
	}
	public void setOther(double other) {
		this.other = other;
	}
	public double getOsmoney() {
		return osmoney;
	}
	public void setOsmoney(double osmoney) {
		this.osmoney = osmoney;
	}
	public String getOsmonth() {
		return osmonth;
	}
	public void setOsmonth(String osmonth) {
		this.osmonth = osmonth;
	}
	
	
}
