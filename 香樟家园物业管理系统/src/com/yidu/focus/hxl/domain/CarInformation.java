package com.yidu.focus.hxl.domain;

import java.io.Serializable;

public class CarInformation implements Serializable{
	private static final long serialVersionUID = -6912791754342354050L;
	  //���ƺ�
	  private String carId;
	  //����
	  private String carName;
	  //�绰
	  private String carTelphone;
	  //��λ���
	  private String carportId;
	  
	//Ĭ�Ϲ��췽��
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
