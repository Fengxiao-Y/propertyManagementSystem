package com.yidu.focus.ll.domain;

import java.io.Serializable;

public class Rules implements Serializable{

	/**
	 * �ƶȱ�
	 */
	private static final long serialVersionUID = -8649436210796459590L;
    //�ļ����
	private String rulesId;
	//�ļ�����
	private String rulesTitle;
	//����
	private String rulesTheme;
	//״̬
	private String rulesStatus;
	//ִ��ʱ��
	private String rulesTime;
	
	//�޲ι��췽��
	public Rules() {
		super();
	}
    
	//�вι��췽��
	public Rules(String rulesId, String rulesTitle, String rulesTheme,
			String rulesStatus, String rulesTime) {
		super();
		this.rulesId = rulesId;
		this.rulesTitle = rulesTitle;
		this.rulesTheme = rulesTheme;
		this.rulesStatus = rulesStatus;
		this.rulesTime = rulesTime;
	}
    
	//�Զ�����get/set����
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
