package com.yidu.focus.hj.domain;

import java.io.Serializable;

public class Expense implements Serializable{

	private static final long serialVersionUID = -4133888388060640807L;
	//序号
	private int expenseId;
	//项目名称
	private String expenseItem;
	//支出金额
	private double expenseMoney;
	//支出时间
	private String expense_date;
	/**
	 * 默认构造方法
	 */
	public Expense() {
		super();
	}
	/**
	 * 带参构造方法
	 * @param expenseId
	 * @param expenseItem
	 * @param expenseMoney
	 * @param expense_date
	 */
	public Expense(int expenseId, String expenseItem, double expenseMoney, String expense_date) {
		super();
		this.expenseId = expenseId;
		this.expenseItem = expenseItem;
		this.expenseMoney = expenseMoney;
		this.expense_date = expense_date;
	}
	public Expense(String expenseItem, double expenseMoney, String expense_date) {
		super();
		this.expenseItem = expenseItem;
		this.expenseMoney = expenseMoney;
		this.expense_date = expense_date;
	}
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	public String getExpenseItem() {
		return expenseItem;
	}
	public void setExpenseItem(String expenseItem) {
		this.expenseItem = expenseItem;
	}
	public double getExpenseMoney() {
		return expenseMoney;
	}
	public void setExpenseMoney(double expenseMoney) {
		this.expenseMoney = expenseMoney;
	}
	public String getExpense_date() {
		return expense_date;
	}
	public void setExpense_date(String expense_date) {
		this.expense_date = expense_date;
	}
	
	
}
