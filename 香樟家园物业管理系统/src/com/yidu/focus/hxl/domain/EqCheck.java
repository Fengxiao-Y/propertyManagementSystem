package com.yidu.focus.hxl.domain;

import java.io.Serializable;

public class EqCheck implements Serializable{
	private static final long serialVersionUID = -3663259306768084778L;
	//Ѳ����
	private int checkId;
	//�豸���
	private String eqId;
	//Ѳ����
	private String checkStatues;
	//����
	private String checkDate;
	
	//Ѳ����
	private String empNo;
	private String empName;

	public EqCheck() {
		super();
	}
	
	public EqCheck(String eqId, String checkStatues, String checkDate, String empNo) {
		super();
		this.eqId = eqId;
		this.checkStatues = checkStatues;
		this.checkDate = checkDate;
		this.empNo = empNo;
	}

	public EqCheck(int checkId, String eqId, String checkStatues, String checkDate, String empNo) {
		super();
		this.checkId = checkId;
		this.eqId = eqId;
		this.checkStatues = checkStatues;
		this.checkDate = checkDate;
		this.empNo = empNo;
	}

	public EqCheck(int checkId, String eqId, String checkStatues, String checkDate, String empNo, String empName) {
		super();
		this.checkId = checkId;
		this.eqId = eqId;
		this.checkStatues = checkStatues;
		this.checkDate = checkDate;
		this.empNo = empNo;
		this.empName = empName;
	}

	public int getCheckId() {
		return checkId;
	}

	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}

	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

	public String getCheckStatues() {
		return checkStatues;
	}

	public void setCheckStatues(String checkStatues) {
		this.checkStatues = checkStatues;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
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
