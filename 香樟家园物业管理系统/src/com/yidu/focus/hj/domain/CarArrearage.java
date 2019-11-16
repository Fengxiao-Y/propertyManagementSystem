package com.yidu.focus.hj.domain;

public class CarArrearage {
	//��λ���
	private String parkId;
	//ҵ������
	private String ownerName;
	//ҵ���ֻ���
	private String ownerTelphone;
	//״̬
	private String parkStatus;
	//Ƿ�ѽ��
	private double carMoney;
	//�ϴνɷ�ʱ��
	private String carDate;
	/**
	 * ���ι��췽��
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
	 * Ĭ�Ϲ��췽��
	 */
	public CarArrearage() {
		super();
	}
	/**
	 * �Զ�����setter/getter
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
