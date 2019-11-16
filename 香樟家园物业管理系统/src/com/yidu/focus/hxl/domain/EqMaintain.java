package com.yidu.focus.hxl.domain;

import java.io.Serializable;

public class EqMaintain implements Serializable{
	private static final long serialVersionUID = -4119012530212629331L;
	private String maintainId;//维修编号 
	private String eqId;//设备编号
	private String maintainStatues;//维护结果
	private String maintainDate;//维护日期
	private String consumable;//耗材
	private int consumNum;//耗材数量
	private double checkCost;//费用
	private String empNo;//维护人
	private String empName;
	
	public EqMaintain() {
		super();
	}
	public EqMaintain(String maintainId, String eqId, String maintainStatues, String maintainDate, String consumable,
			int consumNum, double checkCost, String empNo) {
		super();
		this.maintainId = maintainId;
		this.eqId = eqId;
		this.maintainStatues = maintainStatues;
		this.maintainDate = maintainDate;
		this.consumable = consumable;
		this.consumNum = consumNum;
		this.checkCost = checkCost;
		this.empNo = empNo;
	}
	
	public EqMaintain(String maintainId, String eqId, String maintainStatues, String maintainDate, String consumable,
			int consumNum, double checkCost, String empNo, String empName) {
		super();
		this.maintainId = maintainId;
		this.eqId = eqId;
		this.maintainStatues = maintainStatues;
		this.maintainDate = maintainDate;
		this.consumable = consumable;
		this.consumNum = consumNum;
		this.checkCost = checkCost;
		this.empNo = empNo;
		this.empName = empName;
	}
	public String getMaintainId() {
		return maintainId;
	}
	public void setMaintainId(String maintainId) {
		this.maintainId = maintainId;
	}
	public String getEqId() {
		return eqId;
	}
	public void setEqId(String eqId) {
		this.eqId = eqId;
	}
	public String getMaintainStatues() {
		return maintainStatues;
	}
	public void setMaintainStatues(String maintainStatues) {
		this.maintainStatues = maintainStatues;
	}
	public String getMaintainDate() {
		return maintainDate;
	}
	public void setMaintainDate(String maintainDate) {
		this.maintainDate = maintainDate;
	}
	public String getConsumable() {
		return consumable;
	}
	public void setConsumable(String consumable) {
		this.consumable = consumable;
	}
	public int getConsumNum() {
		return consumNum;
	}
	public void setConsumNum(int consumNum) {
		this.consumNum = consumNum;
	}
	public double getCheckCost() {
		return checkCost;
	}
	public void setCheckCost(double checkCost) {
		this.checkCost = checkCost;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	
}
