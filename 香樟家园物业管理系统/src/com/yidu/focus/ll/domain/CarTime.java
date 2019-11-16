package com.yidu.focus.ll.domain;

import java.io.Serializable;

public class CarTime implements Serializable{
  
	/**
	 * ��������ʱ���
	 */
	private static final long serialVersionUID = -3855119572110955855L;
	//���
	private int Nub;
	//���ƺ�
	private String carId;
	//����ʱ��
	private String goTime;
	//�뿪ʱ��
	private String toTime;
	//���
	private Double Money;
	//������
	private String empNo;
	
	//�޲ι��췽��
	public CarTime() {
	}
    
	//�вι��췽��
	
	
	public CarTime(String carId, String goTime, String toTime, Double money, String empNo) {
		super();
		this.carId = carId;
		this.goTime = goTime;
		this.toTime = toTime;
		Money = money;
		this.empNo = empNo;
	}

	public CarTime(int nub, String carId, String goTime, String toTime, Double money, String empNo) {
		super();
		Nub = nub;
		this.carId = carId;
		this.goTime = goTime;
		this.toTime = toTime;
		Money = money;
		this.empNo = empNo;
	}

	//�Զ�����get/set����
	public int getNub() {
		return Nub;
	}

	public void setNub(int nub) {
		Nub = nub;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getGoTime() {
		return goTime;
	}

	public void setGoTime(String goTime) {
		this.goTime = goTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public Double getMoney() {
		return Money;
	}

	public void setMoney(Double money) {
		Money = money;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	
	
	
	
}
