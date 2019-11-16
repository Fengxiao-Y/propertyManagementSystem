package com.yidu.focus.hj.domain;

import java.io.Serializable;

public class LesseeInformation implements Serializable{
	
	private static final long serialVersionUID = 5789505330533328564L;
	//���
	private int leaseContractId;
	//����
	private String houseId;
	//�⻧����
	private String lesseeName;
	//�⻧�Ա�
	private String lesseeGender;
	//���֤��
	private String lesseeIdcard;
	//�绰
	private String lesseeTelphone;
	//����ʱ��
	private String startTime;
	//����ʱ��
	private String endTime;
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public LesseeInformation(){
		
	}
	/**
	 * ���ι��췽��
	 * @param leaseContractId
	 * @param houseId
	 * @param lesseeName
	 * @param lesseeGender
	 * @param lesseeIdcard
	 * @param lesseeTelphone
	 * @param startTime
	 * @param endTime
	 */
	public LesseeInformation(int leaseContractId, String houseId, String lesseeName, String lesseeGender,
			String lesseeIdcard, String lesseeTelphone, String startTime, String endTime) {
		super();
		this.leaseContractId = leaseContractId;
		this.houseId = houseId;
		this.lesseeName = lesseeName;
		this.lesseeGender = lesseeGender;
		this.lesseeIdcard = lesseeIdcard;
		this.lesseeTelphone = lesseeTelphone;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	/**
	 * ȱ���췽��
	 * @param houseId
	 * @param lesseeName
	 * @param lesseeGender
	 * @param lesseeIdcard
	 * @param lesseeTelphone
	 * @param startTime
	 * @param endTime
	 */
	public LesseeInformation(String houseId, String lesseeName, String lesseeGender, String lesseeIdcard,
			String lesseeTelphone, String startTime, String endTime) {
		super();
		this.houseId = houseId;
		this.lesseeName = lesseeName;
		this.lesseeGender = lesseeGender;
		this.lesseeIdcard = lesseeIdcard;
		this.lesseeTelphone = lesseeTelphone;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public int getLeaseContractId() {
		return leaseContractId;
	}
	public void setLeaseContractId(int leaseContractId) {
		this.leaseContractId = leaseContractId;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getLesseeName() {
		return lesseeName;
	}
	public void setLesseeName(String lesseeName) {
		this.lesseeName = lesseeName;
	}
	public String getLesseeGender() {
		return lesseeGender;
	}
	public void setLesseeGender(String lesseeGender) {
		this.lesseeGender = lesseeGender;
	}
	public String getLesseeIdcard() {
		return lesseeIdcard;
	}
	public void setLesseeIdcard(String lesseeIdcard) {
		this.lesseeIdcard = lesseeIdcard;
	}
	public String getLesseeTelphone() {
		return lesseeTelphone;
	}
	public void setLesseeTelphone(String lesseeTelphone) {
		this.lesseeTelphone = lesseeTelphone;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
}
