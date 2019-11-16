package com.yidu.focus.hj.domain;

import java.io.Serializable;

/**
 * 
 * ���ܣ����ʱ�ʵ����
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��12������10:27:57
 */
public class Wage implements Serializable{

	private static final long serialVersionUID = 1074089175381578091L;
	//���
	private int wageid;
	//Ա������
	private String empName;
	//����
	private double salary;
	//����
	private double commision;
	//�ۿ�
	private double withhold;
	//ʵ������
	private double playMoney;
	//�·�
	private String wageMonth;
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public Wage(){
		
	}
	/**
	 * ���ι��췽��
	 * @param wageid ���
	 * @param empName ����
	 * @param salary ����
	 * @param commision ����
	 * @param withhold �ۿ�
	 * @param playMoney ʵ������
	 * @param wageMonth �·�
	 */
	public Wage(int wageid, String empName, double salary, double commision, double withhold, double playMoney,
			String wageMonth) {
		this.wageid = wageid;
		this.empName = empName;
		this.salary = salary;
		this.commision = commision;
		this.withhold = withhold;
		this.playMoney = playMoney;
		this.wageMonth = wageMonth;
	}
	/**
	 * ȱ���췽��
	 * @param empName ����
	 * @param salary ����
	 * @param commision ����
	 * @param withhold �ۿ�
	 * @param playMoney ʵ������
	 * @param wageMonth �·�
	 */
	public Wage(String empName, double salary, double commision, double withhold, double playMoney, String wageMonth) {
		super();
		this.empName = empName;
		this.salary = salary;
		this.commision = commision;
		this.withhold = withhold;
		this.playMoney = playMoney;
		this.wageMonth = wageMonth;
	}
	//�Զ�����setter/getter
	public int getWageid() {
		return wageid;
	}
	public void setWageid(int wageid) {
		this.wageid = wageid;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getCommision() {
		return commision;
	}
	public void setCommision(double commision) {
		this.commision = commision;
	}
	public double getWithhold() {
		return withhold;
	}
	public void setWithhold(double withhold) {
		this.withhold = withhold;
	}
	public double getPlayMoney() {
		return playMoney;
	}
	public void setPlayMoney(double playMoney) {
		this.playMoney = playMoney;
	}
	public String getWageMonth() {
		return wageMonth;
	}
	public void setWageMonth(String wageMonth) {
		this.wageMonth = wageMonth;
	}
	
}
