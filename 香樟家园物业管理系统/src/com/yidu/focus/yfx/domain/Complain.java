package com.yidu.focus.yfx.domain;

import java.io.Serializable;

public class Complain implements Serializable{
	private static final long serialVersionUID = -6220734961811174880L;
	private int comId;//受理编号
	private String ownerName;//投诉人(业主姓名)
	private String comText;//投诉内容
	private String comTime;//投诉时间
	private String empName;//受理人（客服部门人员）
	private String comResult;//处理结果
	private String comEndTime;//完成时间
	
	/**
	 * 默认构造方法
	 */
	public Complain() {
	
	}

	/**
	 * 带参构造方法
	 * @param ownerName 投诉人
	 * @param comText 投诉内容
	 * @param comTime 投诉时间
	 * @param empName 受理人
	 * @param comResult 处理结果
	 * @param comEndTime 完成时间
	 */
	public Complain(String ownerName, String comText, String comTime, String empName, String comResult,
			String comEndTime) {
		super();
		this.ownerName = ownerName;
		this.comText = comText;
		this.comTime = comTime;
		this.empName = empName;
		this.comResult = comResult;
		this.comEndTime = comEndTime;
	}
	
	/**
	 * 带参构造方法
	 * @param comId 投诉编号
	 * @param ownerName 投诉人
	 * @param comText 投诉类容
	 * @param comTime 投诉时间
	 * @param empName 受理人
	 * @param comResult 处理结果
	 * @param comEndTime 完成时间
	 */
	public Complain(int comId, String ownerName, String comText, String comTime, String empName, String comResult,
			String comEndTime) {
		super();
		this.comId = comId;
		this.ownerName = ownerName;
		this.comText = comText;
		this.comTime = comTime;
		this.empName = empName;
		this.comResult = comResult;
		this.comEndTime = comEndTime;
	}
	
	//自动生成的setter/getter方法
	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getComText() {
		return comText;
	}

	public void setComText(String comText) {
		this.comText = comText;
	}

	public String getComTime() {
		return comTime;
	}

	public void setComTime(String comTime) {
		this.comTime = comTime;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getComResult() {
		return comResult;
	}

	public void setComResult(String comResult) {
		this.comResult = comResult;
	}

	public String getComEndTime() {
		return comEndTime;
	}

	public void setComEndTime(String comEndTime) {
		this.comEndTime = comEndTime;
	}
	
}
