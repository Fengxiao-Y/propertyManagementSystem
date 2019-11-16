package com.yidu.focus.hj.domain;

public class CarArrearage {
	//车位编号
	private String parkId;
	//业主姓名
	private String ownerName;
	//业主手机号
	private String ownerTelphone;
	//状态
	private String parkStatus;
	//欠费金额
	private double carMoney;
	//上次缴费时间
	private String carDate;
	/**
	 * 带参构造方法
	 * @param parkId
	 * @param ownerName
	 * @param ownerTelphone
	 * @param parkStatus
	 * @param carMoney
	 * @param carDate
	 */
	public CarArrearage(String parkId, String ownerName, String ownerTelphone, String parkStatus, double carMoney,
			String carDate) {
		super();
		this.parkId = parkId;
		this.ownerName = ownerName;
		this.ownerTelphone = ownerTelphone;
		this.parkStatus = parkStatus;
		this.carMoney = carMoney;
		this.carDate = carDate;
	}
	/**
	 * 默认构造方法
	 */
	public CarArrearage() {
		super();
	}
	/**
	 * 自动生成setter/getter
	 * @return
	 */
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerTelphone() {
		return ownerTelphone;
	}
	public void setOwnerTelphone(String ownerTelphone) {
		this.ownerTelphone = ownerTelphone;
	}
	public String getParkStatus() {
		return parkStatus;
	}
	public void setParkStatus(String parkStatus) {
		this.parkStatus = parkStatus;
	}
	public double getCarMoney() {
		return carMoney;
	}
	public void setCarMoney(double carMoney) {
		this.carMoney = carMoney;
	}
	public String getCarDate() {
		return carDate;
	}
	public void setCarDate(String carDate) {
		this.carDate = carDate;
	}
	
	
}
