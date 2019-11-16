package com.yidu.focus.ll.domain;

import java.io.Serializable;

public class CarTime implements Serializable{
  
	/**
	 * 车辆出入时间表
	 */
	private static final long serialVersionUID = -3855119572110955855L;
	//序号
	private int Nub;
	//车牌号
	private String carId;
	//进入时间
	private String goTime;
	//离开时间
	private String toTime;
	//金额
	private Double Money;
	//负责人
	private String empNo;
	
	//无参构造方法
	public CarTime() {
	}
    
	//有参构造方法
	
	
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

	//自动生成get/set方法
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
