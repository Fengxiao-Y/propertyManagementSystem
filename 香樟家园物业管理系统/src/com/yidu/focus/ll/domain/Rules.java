package com.yidu.focus.ll.domain;

import java.io.Serializable;

public class Rules implements Serializable{

	/**
	 * 制度表
	 */
	private static final long serialVersionUID = -8649436210796459590L;
    //文件编号
	private String rulesId;
	//文件标题
	private String rulesTitle;
	//主题
	private String rulesTheme;
	//状态
	private String rulesStatus;
	//执行时间
	private String rulesTime;
	
	//无参构造方法
	public Rules() {
		super();
	}
    
	//有参构造方法
	public Rules(String rulesId, String rulesTitle, String rulesTheme,
			String rulesStatus, String rulesTime) {
		super();
		this.rulesId = rulesId;
		this.rulesTitle = rulesTitle;
		this.rulesTheme = rulesTheme;
		this.rulesStatus = rulesStatus;
		this.rulesTime = rulesTime;
	}
    
	//自动生成get/set方法
	public String getRulesId() {
		return rulesId;
	}

	public void setRulesId(String rulesId) {
		this.rulesId = rulesId;
	}

	public String getRulesTitle() {
		return rulesTitle;
	}

	public void setRulesTitle(String rulesTitle) {
		this.rulesTitle = rulesTitle;
	}

	public String getRulesTheme() {
		return rulesTheme;
	}

	public void setRulesTheme(String rulesTheme) {
		this.rulesTheme = rulesTheme;
	}

	public String getRulesStatus() {
		return rulesStatus;
	}

	public void setRulesStatus(String rulesStatus) {
		this.rulesStatus = rulesStatus;
	}

	public String getRulesTime() {
		return rulesTime;
	}

	public void setRulesTime(String rulesTime) {
		this.rulesTime = rulesTime;
	}

	
}
