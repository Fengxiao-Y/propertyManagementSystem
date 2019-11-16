package com.yidu.focus.yfx.domain;

import java.io.Serializable;

public class Repairs implements Serializable{
	private static final long serialVersionUID = -6220734961811174880L;
	private int repId;//受理编号
	private String ownerName;//报修人(业主姓名)
	private String repText;//报修内容
	private String repTime;//报修时间
	private String empName;//受理人（客服部门人员）
	private String repResult;//处理结果
	private String repEndTime;//完成时间
	
	/**
	 * 默认构造方法
	 */
	public Repairs() {
	
	}

	/**
	 * 带参构造方法
	 * @param ownerName 报修人
	 * @param repText 报修内容
	 * @param repTime 报修时间
	 * @param empName 受理人
	 * @param repResult 处理结果
	 * @param repEndTime 完成时间
	 */
	public Repairs(String ownerName, String repText, String repTime, String empName, String repResult,
			String repEndTime) {
		super();
		this.ownerName = ownerName;
		this.repText = repText;
		this.repTime = repTime;
		this.empName = empName;
		this.repResult = repResult;
		this.repEndTime = repEndTime;
	}
	
	/**
	 * 带参构造方法
	 * @param repId 报修编号
	 * @param ownerName 报修人
	 * @param repText 报修类容
	 * @param repTime 报修时间
	 * @param empName 受理人
	 * @param repResult 处理结果
	 * @param repEndTime 完成时间
	 */
	public Repairs(int repId, String ownerName, String repText, String repTime, String empName, String repResult,
			String repEndTime) {
		super();
		this.repId = repId;
		this.ownerName = ownerName;
		this.repText = repText;
		this.repTime = repTime;
		this.empName = empName;
		this.repResult = repResult;
		this.repEndTime = repEndTime;
	}
	
	//自动生成的setter/getter方法
	public int getRepId() {
		return repId;
	}

	public void setRepId(int repId) {
		this.repId = repId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getRepText() {
		return repText;
	}

	public void setRepText(String repText) {
		this.repText = repText;
	}

	public String getRepTime() {
		return repTime;
	}

	public void setRepTime(String repTime) {
		this.repTime = repTime;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getRepResult() {
		return repResult;
	}

	public void setRepResult(String repResult) {
		this.repResult = repResult;
	}

	public String getrepEndTime() {
		return repEndTime;
	}

	public void setRepEndTime(String repEndTime) {
		this.repEndTime = repEndTime;
	}
	
}
