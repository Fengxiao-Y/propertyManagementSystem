package com.yidu.focus.hj.domain;

public class Record {

	//���
	private int recordId;
	//ҵ������
	private String ownerName;
	//ҵ���绰
	private String ownerTelphone;
	//�ɷ�����
	private String recordType;
	//�ɷѽ��
	private double recordMoney;
	//�ɷ�ʱ��
	private String recordDate;
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public Record(){
		
	}
	
	public Record(String ownerName, String ownerTelphone, String recordType, double recordMoney, String recordDate) {
		super();
		this.ownerName = ownerName;
		this.ownerTelphone = ownerTelphone;
		this.recordType = recordType;
		this.recordMoney = recordMoney;
		this.recordDate = recordDate;
	}

	/**
	 * ���ι��췽��
	 * @param recordId
	 * @param ownerName
	 * @param ownerTelphone
	 * @param recordType
	 * @param recordMoney
	 * @param carDate
	 */
	public Record(int recordId, String ownerName, String ownerTelphone, String recordType, double recordMoney,
			String recordDate) {
		super();
		this.recordId = recordId;
		this.ownerName = ownerName;
		this.ownerTelphone = ownerTelphone;
		this.recordType = recordType;
		this.recordMoney = recordMoney;
		this.recordDate = recordDate;
	}
	//�Զ�����setter/getter
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
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
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public double getRecordMoney() {
		return recordMoney;
	}
	public void setRecordMoney(double recordMoney) {
		this.recordMoney = recordMoney;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	
	
	
}
