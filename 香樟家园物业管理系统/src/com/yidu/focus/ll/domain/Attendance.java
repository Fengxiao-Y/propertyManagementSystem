package com.yidu.focus.ll.domain;

import java.io.Serializable;
/**
 * 
 * 功能：考勤表 
 * 作者：刘李
 * 日期：2019年11月1日上午1:58:43
 * 版本：1.0
 */
public class Attendance  implements Serializable {

	private static final long serialVersionUID = -3322461598373407308L;
	//考勤编号
	private int attendId;
	//员工编号
    private String empNo;
	//上班时间
    private String onWork;
	//下班时间
    private String doWork;
    
    //无参构造方法
	public Attendance() {

	}
	
	
	//不带主键的构造方法
    public Attendance(String empNo, String onWork, String doWork) {
		super();
		this.empNo = empNo;
		this.onWork = onWork;
		this.doWork = doWork;
	}



	//带参构造方法
	public Attendance(int attendId, String empNo, String onWork, String doWork) {
		super();
		this.attendId = attendId;
		this.empNo = empNo;
		this.onWork = onWork;
		this.doWork = doWork;
	}

    //get/set自动生成方法
	public int getAttendId() {
		return attendId;
	}

	public void setAttendId(int attendId) {
		this.attendId = attendId;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getOnWork() {
		return onWork;
	}

	public void setOnWork(String onWork) {
		this.onWork = onWork;
	}

	public String getDoWork() {
		return doWork;
	}

	public void setDoWork(String doWork) {
		this.doWork = doWork;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
  
}
