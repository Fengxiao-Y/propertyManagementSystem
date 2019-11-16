package com.yidu.focus.ll.domain;

import java.io.Serializable;

public class Emp implements Serializable{
	/**
	 * Ա����
	 */
	private static final long serialVersionUID = 7678413890805248498L;
	//���
    private String empNo;
    //����
    private String empName;
    //�Ա�
    private String empSex;
    //�绰
    private String empTelphone;
    //���֤����
    private String empIdcard;
    //��ְʱ��
    private String hireDate;
    //��ְʱ��
    private String resignationDate;
    //нˮ
    private Double salary;
	//����
    private Double commision;
    //����
    private String deptNo;
	//��˾
    private String manager;
    //�޲ι��췽��
	public Emp() {

	}
    //�вι��췽��
	public Emp(String empNo, String empName, String empSex, String empTelphone, String empIdcard, String hireDate,
			 Double salary, Double commision, String deptNo, String manager) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.empSex = empSex;
		this.empTelphone = empTelphone;
		this.empIdcard = empIdcard;
		this.hireDate = hireDate;
		this.salary = salary;
		this.commision = commision;
		this.deptNo = deptNo;
		this.manager = manager;
	}

	
	//�Զ�����get/set����
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

	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public String getEmpTelphone() {
		return empTelphone;
	}

	public void setEmpTelphone(String empTelphone) {
		this.empTelphone = empTelphone;
	}

	public String getEmpIdcard() {
		return empIdcard;
	}

	public void setEmpIdcard(String empIdcard) {
		this.empIdcard = empIdcard;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(String resignationDate) {
		this.resignationDate = resignationDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getCommision() {
		return commision;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
    
}
