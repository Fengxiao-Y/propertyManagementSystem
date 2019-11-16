/**
 * 
 */
package com.yidu.focus.wzh.domain;

import java.io.Serializable;

/**
 * 功能：users的实体类
 * 作者：伍志华
 * 日期：2019年10月11日下午4:51:17
 * 版本：1.0
 */
public class Users implements Serializable{

	private static final long serialVersionUID = -352246539672680192L;
	private int uId; //用户编号
	private String uName;//用户名
	private String uPassword;	//用户密码
	private String uEmail; //用户邮箱
	/**
	 * 默认的构造方法
	 */
	public Users() {
		super();
	}
	private String uIphone; //电话
	
	/**
	 * 不带自增长的构造方法
	 * @param uName用户名
	 * @param uPassword用户密码
	 * @param uEmail用户邮箱
	 * @param uIphone电话
	 */
	public Users(String uName, String uPassword, String uEmail, String uIphone) {
		super();
		this.uName = uName;
		this.uPassword = uPassword;
		this.uEmail = uEmail;
		this.uIphone = uIphone;
	}
	/**
	 * 带参的构造方法
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
	//自动生成getter/setter方法
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
