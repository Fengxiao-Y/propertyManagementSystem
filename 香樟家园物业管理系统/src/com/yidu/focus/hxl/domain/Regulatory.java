package com.yidu.focus.hxl.domain;

import java.io.Serializable;

public class Regulatory implements Serializable{
	private static final long serialVersionUID = 5514982375255674594L;

	private String fileId;//������
	
	private String fileTitle;//���⢘
	
	private String fileTheme;//���⢘
	
	private String fileState;//״̬
	
	private String executionTime;//ִ��ʱ��

	
	//默认构�?�方�?
	public Regulatory() {
	}


	public Regulatory(String fileId, String fileTitle, String fileTheme, String fileState, String executionTime) {
		super();
		this.fileId = fileId;
		this.fileTitle = fileTitle;
		this.fileTheme = fileTheme;
		this.fileState = fileState;
		this.executionTime = executionTime;
	}


	public String getFileId() {
		return fileId;
	}


	public void setFileId(String fileId) {
		this.fileId = fileId;
	}


	public String getFileTitle() {
		return fileTitle;
	}


	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}


	public String getFileTheme() {
		return fileTheme;
	}


	public void setFileTheme(String fileTheme) {
		this.fileTheme = fileTheme;
	}


	public String getFileState() {
		return fileState;
	}


	public void setFileState(String fileState) {
		this.fileState = fileState;
	}


	public String getExecutionTime() {
		return executionTime;
	}


	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}
	
	
}
