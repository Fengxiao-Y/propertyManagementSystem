package com.yidu.focus.yfx.domain;

import java.io.Serializable;

public class Complain implements Serializable{
	private static final long serialVersionUID = -6220734961811174880L;
	private int comId;//������
	private String ownerName;//Ͷ����(ҵ������)
	private String comText;//Ͷ������
	private String comTime;//Ͷ��ʱ��
	private String empName;//�����ˣ��ͷ�������Ա��
	private String comResult;//������
	private String comEndTime;//���ʱ��
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public Complain() {
	
	}

	/**
	 * ���ι��췽��
	 * @param ownerName Ͷ����
	 * @param comText Ͷ������
	 * @param comTime Ͷ��ʱ��
	 * @param empName ������
	 * @param comResult ������
	 * @param comEndTime ���ʱ��
	 */
	public Complain(String ownerName, String comText, String comTime, String empName, String comResult,
			String comEndTime) {
		super();
		this.ownerName = ownerName;
		this.comText = comText;
		this.comTime = comTime;
		this.empName = empName;
		this.comResult = comResult;
		this.comEndTime = comEndTime;
	}
	
	/**
	 * ���ι��췽��
	 * @param comId Ͷ�߱��
	 * @param ownerName Ͷ����
	 * @param comText Ͷ������
	 * @param comTime Ͷ��ʱ��
	 * @param empName ������
	 * @param comResult ������
	 * @param comEndTime ���ʱ��
	 */
	public Complain(int comId, String ownerName, String comText, String comTime, String empName, String comResult,
			String comEndTime) {
		super();
		this.comId = comId;
		this.ownerName = ownerName;
		this.comText = comText;
		this.comTime = comTime;
		this.empName = empName;
		this.comResult = comResult;
		this.comEndTime = comEndTime;
	}
	
	//�Զ����ɵ�setter/getter����
	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getComText() {
		return comText;
	}

	public void setComText(String comText) {
		this.comText = comText;
	}

	public String getComTime() {
		return comTime;
	}

	public void setComTime(String comTime) {
		this.comTime = comTime;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getComResult() {
		return comResult;
	}

	public void setComResult(String comResult) {
		this.comResult = comResult;
	}

	public String getComEndTime() {
		return comEndTime;
	}

	public void setComEndTime(String comEndTime) {
		this.comEndTime = comEndTime;
	}
	
}
