package com.yidu.focus.hj.domain;

public class Arrearage {

	//���ݱ��
	private String houseId;
	//ҵ������
	private String ownerName;
	//ҵ���ֻ���
	private String ownerTelphone;
	//״̬
	private String state;
	//Ƿ�ѽ��
	private double arrearageMoney;
	//�ϴνɷ�ʱ��
	private String arrearageDate;
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public Arrearage() {
	}
	/**
	 * ���ι��췽��
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
