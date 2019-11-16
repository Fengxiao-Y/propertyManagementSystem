/**
 * 
 */
package com.yidu.focus.wzh.domain;

import java.io.Serializable;

/**
 * ���ܣ�users��ʵ����
 * ���ߣ���־��
 * ���ڣ�2019��10��11������4:51:17
 * �汾��1.0
 */
public class Users implements Serializable{

	private static final long serialVersionUID = -352246539672680192L;
	private int uId; //�û����
	private String uName;//�û���
	private String uPassword;	//�û�����
	private String uEmail; //�û�����
	/**
	 * Ĭ�ϵĹ��췽��
	 */
	public Users() {
		super();
	}
	private String uIphone; //�绰
	
	/**
	 * �����������Ĺ��췽��
	 * @param uName�û���
	 * @param uPassword�û�����
	 * @param uEmail�û�����
	 * @param uIphone�绰
	 */
	public Users(String uName, String uPassword, String uEmail, String uIphone) {
		super();
		this.uName = uName;
		this.uPassword = uPassword;
		this.uEmail = uEmail;
		this.uIphone = uIphone;
	}
	/**
	 * ���εĹ��췽��
	 * @param uId
	 * @param uName
	 * @param uPassword
	 * @param uEmail
	 * @param uIphone
	 */
	public Users(int uId, String uName, String uPassword, String uEmail, String uIphone) {
		super();
		this.uId = uId;
		this.uName = uName;
		this.uPassword = uPassword;
		this.uEmail = uEmail;
		this.uIphone = uIphone;
	}
	//�Զ�����getter/setter����
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public String getuIphone() {
		return uIphone;
	}
	public void setuIphone(String uIphone) {
		this.uIphone = uIphone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
