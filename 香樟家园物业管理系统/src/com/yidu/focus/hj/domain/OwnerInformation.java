package com.yidu.focus.hj.domain;

import java.io.Serializable;

/**
 * 
 * 功能：业主信息表实体类
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月14日上午11:05:36
 */
public class OwnerInformation implements Serializable{

	private static final long serialVersionUID = -6373007227227128588L;
	//业主编号
	private String ownerId;
	//房屋编号
	private String houseId;
	//业主姓名
	private String ownerName;
	//性别
	private String ownerSex;
	//身份证号码
	private String ownerIdcard;
	//电话号码
	private String ownerTelphone;
	//邮箱
	private String ownerEmail;
	//邮寄地址
	private String ownerAddress;
	/**
	 * 默认构造方法 
	 */
	public OwnerInformation(){
		
	}
	/**
	 * 带参构造方法
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
	//自动生成setter/getter
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
