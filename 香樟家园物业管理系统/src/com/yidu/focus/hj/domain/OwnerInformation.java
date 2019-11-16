package com.yidu.focus.hj.domain;

import java.io.Serializable;

/**
 * 
 * ���ܣ�ҵ����Ϣ��ʵ����
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��14������11:05:36
 */
public class OwnerInformation implements Serializable{

	private static final long serialVersionUID = -6373007227227128588L;
	//ҵ�����
	private String ownerId;
	//���ݱ��
	private String houseId;
	//ҵ������
	private String ownerName;
	//�Ա�
	private String ownerSex;
	//���֤����
	private String ownerIdcard;
	//�绰����
	private String ownerTelphone;
	//����
	private String ownerEmail;
	//�ʼĵ�ַ
	private String ownerAddress;
	/**
	 * Ĭ�Ϲ��췽�� 
	 */
	public OwnerInformation(){
		
	}
	/**
	 * ���ι��췽��
	 * @param ownerId
	 * @param houseId
	 * @param ownerName
	 * @param ownerSex
	 * @param ownerIdcard
	 * @param ownerTelphone
	 * @param ownerEmail
	 * @param ownerAddress
	 */
	public OwnerInformation(String ownerId, String houseId, String ownerName, String ownerSex, String ownerIdcard,
			String ownerTelphone, String ownerEmail, String ownerAddress) {
		super();
		this.ownerId = ownerId;
		this.houseId = houseId;
		this.ownerName = ownerName;
		this.ownerSex = ownerSex;
		this.ownerIdcard = ownerIdcard;
		this.ownerTelphone = ownerTelphone;
		this.ownerEmail = ownerEmail;
		this.ownerAddress = ownerAddress;
	}
	//�Զ�����setter/getter
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
	public String getOwnerSex() {
		return ownerSex;
	}
	public void setOwnerSex(String ownerSex) {
		this.ownerSex = ownerSex;
	}
	public String getOwnerIdcard() {
		return ownerIdcard;
	}
	public void setOwnerIdcard(String ownerIdcard) {
		this.ownerIdcard = ownerIdcard;
	}
	public String getOwnerTelphone() {
		return ownerTelphone;
	}
	public void setOwnerTelphone(String ownerTelphone) {
		this.ownerTelphone = ownerTelphone;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	
	
}
