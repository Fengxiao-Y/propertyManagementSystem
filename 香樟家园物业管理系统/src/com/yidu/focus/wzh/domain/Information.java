
package com.yidu.focus.wzh.domain;

import java.io.Serializable;

/**
 * 功能：information的实体类
 * 作者：伍志华
 * 日期：2019年10月11日下午4:54:43
 * 版本：1.0
 */
public class Information implements Serializable {
	private static final long serialVersionUID = -285797355818850828L;
	private String backId;//后台登录账号
	private String backPwd;//密码
	private String empName;//姓名
	private String backPost;//职位
	
	/**
	 * 带参的构造方法
	 * @param backId 后台登录账号
	 * @param backPwd 密码
	 * @param empName 姓名
	 * @param backPost 职位
	 */
	public Information(String backId, String backPwd, String empName, String backPost) {
		super();
		this.backId = backId;
		this.backPwd = backPwd;
		this.empName = empName;
		this.backPost = backPost;
	}
	/**
	 * 默认的构造方法
	 */
	public Information() {
		super();
	}
	//自动生成getter/setter方法
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
