package com.yidu.focus.ll.domain;

import java.io.Serializable;

public class ParkingInformation implements Serializable{	
	/**
	 * 车位信息表
	 */
	private static final long serialVersionUID = 6974888943770416259L;
	//车位编号
	public String parkId;
	//状态
	public String parkStatus;
	//金额
	public Double parkMoney;
	//房号
	public String houseId;
	
	//无参构造方法
	public ParkingInformation() {
		
	}

	//有参构造方法
	public ParkingInformation(String parkId, String parkStatus, Double parkMoney, String houseId) {
		super();
		this.parkId = parkId;
		this.parkStatus = parkStatus;
		this.parkMoney = parkMoney;
		this.houseId = houseId;
	}

	//自动生成get/set方法
	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public String getParkStatus() {
		return parkStatus;
	}

	public void setParkStatus(String parkStatus) {
		this.parkStatus = parkStatus;
	}

	public Double getParkMoney() {
		return parkMoney;
	}

	public void setParkMoney(Double parkMoney) {
		this.parkMoney = parkMoney;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
	
}
