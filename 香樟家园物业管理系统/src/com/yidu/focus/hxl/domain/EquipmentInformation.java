package com.yidu.focus.hxl.domain;

import java.io.Serializable;

public class EquipmentInformation implements Serializable{
	private static final long serialVersionUID = 4088367882746815061L;
	 private String eqId;//�豸���
	 private String eqName;//�豸����
	 private String eqPurpose;//��;
	 private String manufacturer;//��������
	 private String producedDate;//��������
	 private int eqNum;//����
	 private String usePosition;//λ��
	 private String eqStatus;//״̬
	 
	 //�޲ι��췽��
	public EquipmentInformation() {
	}

	//�вι��췽��
	public EquipmentInformation(String eqId, String eqName, String eqPurpose, String manufacturer, String producedDate, int eqNum,
			String usePosition, String eqStatus) {
		this.eqId = eqId;
		this.eqName = eqName;
		this.eqPurpose = eqPurpose;
		this.manufacturer = manufacturer;
		this.producedDate = producedDate;
		this.eqNum = eqNum;
		this.usePosition = usePosition;
		this.eqStatus = eqStatus;
	}

	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public String getEqPurpose() {
		return eqPurpose;
	}

	public void setEqPurpose(String eqPurpose) {
		this.eqPurpose = eqPurpose;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getProducedDate() {
		return producedDate;
	}

	public void setProducedDate(String producedDate) {
		this.producedDate = producedDate;
	}

	public int getEqNum() {
		return eqNum;
	}

	public void setEqNum(int eqNum) {
		this.eqNum = eqNum;
	}

	public String getUsePosition() {
		return usePosition;
	}

	public void setUsePosition(String usePosition) {
		this.usePosition = usePosition;
	}

	public String getEqStatus() {
		return eqStatus;
	}

	public void setEqStatus(String eqStatus) {
		this.eqStatus = eqStatus;
	}
	 
	 
}
