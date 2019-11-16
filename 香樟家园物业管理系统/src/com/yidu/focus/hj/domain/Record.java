package com.yidu.focus.hj.domain;

public class Record {

	//编号
	private int recordId;
	//业主姓名
	private String ownerName;
	//业主电话
	private String ownerTelphone;
	//缴费类型
	private String recordType;
	//缴费金额
	private double recordMoney;
	//缴费时间
	private String recordDate;
	/**
	 * 默认构造方法
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
	 * 带参构造方法
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
	//自动生成setter/getter
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
