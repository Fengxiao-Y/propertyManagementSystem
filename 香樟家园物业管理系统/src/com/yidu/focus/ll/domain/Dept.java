package com.yidu.focus.ll.domain;

import java.io.Serializable;

public class Dept implements Serializable{
	/**
	 * 部门表
	 */
	private static final long serialVersionUID = 5882454917749966677L;
	//部门编号	
	private String deptNo;
	//部门名称
	private String deptName;
	//责任人
	private String empNo;
	//编制
	private int empCount;

	//无参构造方法
	public Dept() {
	}

	//有参构造方法
	public Dept(String deptNo, String deptName, String empNo, int empCount) {
		super();
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.empNo = empNo;
		this.empCount = empCount;
	}


	//自动生成get/set方法
	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public int getEmpCount() {
		return empCount;
	}

	public void setEmpCount(int empCount) {
		this.empCount = empCount;
	}



}
