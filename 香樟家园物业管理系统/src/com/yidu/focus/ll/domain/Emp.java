package com.yidu.focus.ll.domain;

import java.io.Serializable;

public class Emp implements Serializable{
	/**
	 * 员工表
	 */
	private static final long serialVersionUID = 7678413890805248498L;
	//编号
    private String empNo;
    //姓名
    private String empName;
    //性别
    private String empSex;
    //电话
    private String empTelphone;
    //身份证号码
    private String empIdcard;
    //入职时间
    private String hireDate;
    //离职时间
    private String resignationDate;
    //薪水
    private Double salary;
	//补贴
    private Double commision;
    //部门
    private String deptNo;
	//上司
    private String manager;
    //无参构造方法
	public Emp() {

	}
    //有参构造方法
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

	
	//自动生成get/set方法
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
