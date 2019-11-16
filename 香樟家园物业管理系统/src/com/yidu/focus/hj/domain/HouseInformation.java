
package com.yidu.focus.hj.domain;

import java.io.Serializable;

public class HouseInformation implements Serializable{

	private static final long serialVersionUID = 5403004677746937538L;
	//房屋编号
	private String houseId;
	//户主姓名
	private String ownerName;
	//房屋类型
	private String houseType;
	//面积
	private double houseArea;
	//房屋性质
	private String houseNature;
	//房屋状态
	private String houseState;
	//地址
	private String houseAddress;
	/**
	 * 默认构造方法
	 */
	public HouseInformation(){
		
	}
	/**
	 * 带参构造方法
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
	//自动生成setter/getter
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
