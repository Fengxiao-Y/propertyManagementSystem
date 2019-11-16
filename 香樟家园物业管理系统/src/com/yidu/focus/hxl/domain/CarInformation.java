package com.yidu.focus.hxl.domain;

import java.io.Serializable;

public class CarInformation implements Serializable{
	private static final long serialVersionUID = -6912791754342354050L;
	  //车牌号
	  private String carId;
	  //车名
	  private String carName;
	  //电话
	  private String carTelphone;
	  //车位编号
	  private String carportId;
	  
	//默认构造方法
	public CarInformation() {
	}

	public CarInformation(String carId, String carName, String carTelphone, String carportId) {
		super();
		this.carId = carId;
		this.carName = carName;
		this.carTelphone = carTelphone;
		this.carportId = carportId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarTelphone() {
		return carTelphone;
	}

	public void setCarTelphone(String carTelphone) {
		this.carTelphone = carTelphone;
	}

	public String getCarportId() {
		return carportId;
	}

	public void setCarportId(String carportId) {
		this.carportId = carportId;
	}
	  
	  
}
