
package com.yidu.focus.wzh.domain;

import java.io.Serializable;

/**
 * ���ܣ�information��ʵ����
 * ���ߣ���־��
 * ���ڣ�2019��10��11������4:54:43
 * �汾��1.0
 */
public class Information implements Serializable {
	private static final long serialVersionUID = -285797355818850828L;
	private String backId;//��̨��¼�˺�
	private String backPwd;//����
	private String empName;//����
	private String backPost;//ְλ
	
	/**
	 * ���εĹ��췽��
	 * @param backId ��̨��¼�˺�
	 * @param backPwd ����
	 * @param empName ����
	 * @param backPost ְλ
	 */
	public Information(String backId, String backPwd, String empName, String backPost) {
		super();
		this.backId = backId;
		this.backPwd = backPwd;
		this.empName = empName;
		this.backPost = backPost;
	}
	/**
	 * Ĭ�ϵĹ��췽��
	 */
	public Information() {
		super();
	}
	//�Զ�����getter/setter����
	public String getBackId() {
		return backId;
	}
	public void setBackId(String backId) {
		this.backId = backId;
	}
	public String getBackPwd() {
		return backPwd;
	}
	public void setBackPwd(String backPwd) {
		this.backPwd = backPwd;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getBackPost() {
		return backPost;
	}
	public void setBackPost(String backPost) {
		this.backPost = backPost;
	}

}
