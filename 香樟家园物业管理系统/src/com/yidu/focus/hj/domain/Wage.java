package com.yidu.focus.hj.domain;

import java.io.Serializable;

/**
 * 
 * 功能：工资表实体类
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月12日上午10:27:57
 */
public class Wage implements Serializable{

	private static final long serialVersionUID = 1074089175381578091L;
	//编号
	private int wageid;
	//员工姓名
	private String empName;
	//工资
	private double salary;
	//补贴
	private double commision;
	//扣款
	private double withhold;
	//实发工资
	private double playMoney;
	//月份
	private String wageMonth;
	/**
	 * 默认构造方法
	 */
	public Wage(){
		
	}
	/**
	 * 带参构造方法
	 * @param wageid 编号
	 * @param empName 姓名
	 * @param salary 工资
	 * @param commision 补贴
	 * @param withhold 扣款
	 * @param playMoney 实发工资
	 * @param wageMonth 月份
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
	 * 缺损构造方法
	 * @param empName 姓名
	 * @param salary 工资
	 * @param commision 补贴
	 * @param withhold 扣款
	 * @param playMoney 实发工资
	 * @param wageMonth 月份
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
	//自动生成setter/getter
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
