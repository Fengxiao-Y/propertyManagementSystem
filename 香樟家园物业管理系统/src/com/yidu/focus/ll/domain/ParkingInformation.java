package com.yidu.focus.ll.domain;

import java.io.Serializable;

public class ParkingInformation implements Serializable{	
	/**
	 * ��λ��Ϣ��
	 */
	private static final long serialVersionUID = 6974888943770416259L;
	//��λ���
	public String parkId;
	//״̬
	public String parkStatus;
	//���
	public Double parkMoney;
	//����
	public String houseId;
	
	//�޲ι��췽��
	public ParkingInformation() {
		
	}

	//�вι��췽��
	public ParkingInformation(String parkId, String parkStatus, Double parkMoney, String houseId) {
		super();
		this.parkId = parkId;
		this.parkStatus = parkStatus;
		this.parkMoney = parkMoney;
		this.houseId = houseId;
	}

	//�Զ�����get/set����
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
