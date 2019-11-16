package com.yidu.focus.ll.domain;

import java.io.Serializable;
/**
 * 
 * ���ܣ����ڱ� 
 * ���ߣ�����
 * ���ڣ�2019��11��1������1:58:43
 * �汾��1.0
 */
public class Attendance  implements Serializable {

	private static final long serialVersionUID = -3322461598373407308L;
	//���ڱ��
	private int attendId;
	//Ա�����
    private String empNo;
	//�ϰ�ʱ��
    private String onWork;
	//�°�ʱ��
    private String doWork;
    
    //�޲ι��췽��
	public Attendance() {

	}
	
	
	//���������Ĺ��췽��
    public Attendance(String empNo, String onWork, String doWork) {
		super();
		this.empNo = empNo;
		this.onWork = onWork;
		this.doWork = doWork;
	}



	//���ι��췽��
	public Attendance(int attendId, String empNo, String onWork, String doWork) {
		super();
		this.attendId = attendId;
		this.empNo = empNo;
		this.onWork = onWork;
		this.doWork = doWork;
	}

    //get/set�Զ����ɷ���
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
