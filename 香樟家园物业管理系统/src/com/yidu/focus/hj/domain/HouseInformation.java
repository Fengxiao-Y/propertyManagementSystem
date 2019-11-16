
package com.yidu.focus.hj.domain;

import java.io.Serializable;

public class HouseInformation implements Serializable{

	private static final long serialVersionUID = 5403004677746937538L;
	//���ݱ��
	private String houseId;
	//��������
	private String ownerName;
	//��������
	private String houseType;
	//���
	private double houseArea;
	//��������
	private String houseNature;
	//����״̬
	private String houseState;
	//��ַ
	private String houseAddress;
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public HouseInformation(){
		
	}
	/**
	 * ���ι��췽��
	 * @param houseId
	 * @param ownerName
	 * @param houseType
	 * @param houseArea
	 * @param houseNature
	 * @param houseState
	 * @param houseAddress
	 */
	public HouseInformation(String houseId, String ownerName, String houseType, double houseArea, String houseNature,
			String houseState, String houseAddress) {
		super();
		this.houseId = houseId;
		this.ownerName = ownerName;
		this.houseType = houseType;
		this.houseArea = houseArea;
		this.houseNature = houseNature;
		this.houseState = houseState;
		this.houseAddress = houseAddress;
	}
	//�Զ�����setter/getter
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
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public double getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(double houseArea) {
		this.houseArea = houseArea;
	}
	public String getHouseNature() {
		return houseNature;
	}
	public void setHouseNature(String houseNature) {
		this.houseNature = houseNature;
	}
	public String getHouseState() {
		return houseState;
	}
	public void setHouseState(String houseState) {
		this.houseState = houseState;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	
}
