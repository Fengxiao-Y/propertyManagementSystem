package com.yidu.focus.yfx.domain;

import java.io.Serializable;

public class Repairs implements Serializable{
	private static final long serialVersionUID = -6220734961811174880L;
	private int repId;//������
	private String ownerName;//������(ҵ������)
	private String repText;//��������
	private String repTime;//����ʱ��
	private String empName;//�����ˣ��ͷ�������Ա��
	private String repResult;//������
	private String repEndTime;//���ʱ��
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public Repairs() {
	
	}

	/**
	 * ���ι��췽��
	 * @param ownerName ������
	 * @param repText ��������
	 * @param repTime ����ʱ��
	 * @param empName ������
	 * @param repResult ������
	 * @param repEndTime ���ʱ��
	 */
	public Repairs(String ownerName, String repText, String repTime, String empName, String repResult,
			String repEndTime) {
		super();
		this.ownerName = ownerName;
		this.repText = repText;
		this.repTime = repTime;
		this.empName = empName;
		this.repResult = repResult;
		this.repEndTime = repEndTime;
	}
	
	/**
	 * ���ι��췽��
	 * @param repId ���ޱ��
	 * @param ownerName ������
	 * @param repText ��������
	 * @param repTime ����ʱ��
	 * @param empName ������
	 * @param repResult ������
	 * @param repEndTime ���ʱ��
	 */
	public Repairs(int repId, String ownerName, String repText, String repTime, String empName, String repResult,
			String repEndTime) {
		super();
		this.repId = repId;
		this.ownerName = ownerName;
		this.repText = repText;
		this.repTime = repTime;
		this.empName = empName;
		this.repResult = repResult;
		this.repEndTime = repEndTime;
	}
	
	//�Զ����ɵ�setter/getter����
	public int getRepId() {
		return repId;
	}

	public void setRepId(int repId) {
		this.repId = repId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getRepText() {
		return repText;
	}

	public void setRepText(String repText) {
		this.repText = repText;
	}

	public String getRepTime() {
		return repTime;
	}

	public void setRepTime(String repTime) {
		this.repTime = repTime;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getRepResult() {
		return repResult;
	}

	public void setRepResult(String repResult) {
		this.repResult = repResult;
	}

	public String getrepEndTime() {
		return repEndTime;
	}

	public void setRepEndTime(String repEndTime) {
		this.repEndTime = repEndTime;
	}
	
}
