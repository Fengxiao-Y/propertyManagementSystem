package com.yidu.focus.ll.domain;

import java.io.Serializable;

public class Dept implements Serializable{
	/**
	 * ���ű�
	 */
	private static final long serialVersionUID = 5882454917749966677L;
	//���ű��	
	private String deptNo;
	//��������
	private String deptName;
	//������
	private String empNo;
	//����
	private int empCount;

	//�޲ι��췽��
	public Dept() {
	}

	//�вι��췽��
	public Dept(String deptNo, String deptName, String empNo, int empCount) {
		super();
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.empNo = empNo;
		this.empCount = empCount;
	}


	//�Զ�����get/set����
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
