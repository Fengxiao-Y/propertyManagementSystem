package com.yidu.focus.hj.domain;

public class Arrearage {

	//房屋编号
	private String houseId;
	//业主姓名
	private String ownerName;
	//业主手机号
	private String ownerTelphone;
	//状态
	private String state;
	//欠费金额
	private double arrearageMoney;
	//上次缴费时间
	private String arrearageDate;
	/**
	 * 默认构造方法
	 */
	public Arrearage() {
	}
	/**
	 * 带参构造方法
	 * @param houseId
	 * @param ownerName
	 * @param ownerTelphone
	 * @param state
	 * @param arrearageMoney
	 * @param arrearageDate
	 */
	public Arrearage(String houseId, String ownerName, String ownerTelphone, String state, double arrearageMoney,
			String arrearageDate) {
		super();
		this.houseId = houseId;
		this.ownerName = ownerName;
		this.ownerTelphone = ownerTelphone;
		this.state = state;
		this.arrearageMoney = arrearageMoney;
		this.arrearageDate = arrearageDate;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getArrearageMoney() {
		return arrearageMoney;
	}
	public void setArrearageMoney(double arrearageMoney) {
		this.arrearageMoney = arrearageMoney;
	}
	public String getArrearageDate() {
		return arrearageDate;
	}
	public void setArrearageDate(String arrearageDate) {
		this.arrearageDate = arrearageDate;
	}
	
}
