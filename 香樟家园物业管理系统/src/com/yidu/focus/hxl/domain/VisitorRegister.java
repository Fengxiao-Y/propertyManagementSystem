package com.yidu.focus.hxl.domain;

import java.io.Serializable;

public class VisitorRegister implements Serializable{
	private static final long serialVersionUID = -538977449185463406L;
	private int visitorId;//���ñ��
	
	private String visitorName;//����
	private String visitorGender;//�Ա�
	private String visitorIdNum;//���֤��
	private String visitorTelphone;//�绰
	private String vistiAddress;//���õص�
	private String vistiReason;//��������
	private String cometime;//�Ǽ�ʱ��
	private String leaveTime;//�뿪ʱ��
	private String empNo;//ֵ��Ա
	
	//�޲ι��췽��
	public VisitorRegister() {
	}

	//�вι��췽��	
	public VisitorRegister( String visitorName, String visitorGender, String visitorIdNum,
			String visitorTelphone, String vistiAddress, String vistiReason, String cometime, String leaveTime,
			String empNo) {
		
		this.visitorName = visitorName;
		this.visitorGender = visitorGender;
		this.visitorIdNum = visitorIdNum;
		this.visitorTelphone = visitorTelphone;
		this.vistiAddress = vistiAddress;
		this.vistiReason = vistiReason;
		this.cometime = cometime;
		this.leaveTime = leaveTime;
		this.empNo = empNo;
	}



	public VisitorRegister(int visitorId, String visitorName, String visitorGender,
			String visitorIdNum, String visitorTelphone, String vistiAddress, String vistiReason, String cometime,
			String leaveTime, String empNo) {
		super();
		this.visitorId = visitorId;
		
		this.visitorName = visitorName;
		this.visitorGender = visitorGender;
		this.visitorIdNum = visitorIdNum;
		this.visitorTelphone = visitorTelphone;
		this.vistiAddress = vistiAddress;
		this.vistiReason = vistiReason;
		this.cometime = cometime;
		this.leaveTime = leaveTime;
		this.empNo = empNo;
	}

	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}

	
	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getVisitorGender() {
		return visitorGender;
	}

	public void setVisitorGender(String visitorGender) {
		this.visitorGender = visitorGender;
	}

	public String getVisitorIdNum() {
		return visitorIdNum;
	}

	public void setVisitorIdNum(String visitorIdNum) {
		this.visitorIdNum = visitorIdNum;
	}

	public String getVisitorTelphone() {
		return visitorTelphone;
	}

	public void setVisitorTelphone(String visitorTelphone) {
		this.visitorTelphone = visitorTelphone;
	}

	public String getVistiAddress() {
		return vistiAddress;
	}

	public void setVistiAddress(String vistiAddress) {
		this.vistiAddress = vistiAddress;
	}

	public String getVistiReason() {
		return vistiReason;
	}

	public void setVistiReason(String vistiReason) {
		this.vistiReason = vistiReason;
	}

	public String getCometime() {
		return cometime;
	}

	public void setCometime(String cometime) {
		this.cometime = cometime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	
	
}
