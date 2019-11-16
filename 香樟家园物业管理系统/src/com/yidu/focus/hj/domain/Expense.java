package com.yidu.focus.hj.domain;

import java.io.Serializable;

public class Expense implements Serializable{

	private static final long serialVersionUID = -4133888388060640807L;
	//���
	private int expenseId;
	//��Ŀ����
	private String expenseItem;
	//֧�����
	private double expenseMoney;
	//֧��ʱ��
	private String expense_date;
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public Expense() {
		super();
	}
	/**
	 * ���ι��췽��
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
