
package com.yidu.focus.wzh.domain;

import java.io.Serializable;

/**
 * 功能：Procurement的实体类
 * 作者：伍志华
 * 日期：2019年10月11日下午4:15:53
 * 版本：1.0
 */
public class Procurement implements Serializable{

	private static final long serialVersionUID = 4505275502621113327L;
	private String proId;	//记录编号
	private String proName;	// 采购人
	private String goodsName; // 物品名称
	private String proTime;	// 时间
	private int proNum; //数量
	private double proPrice; //单价
	private  double proTolal; // 总价
	
	
	
	/**
	 * 默认的构造方法
	 */
	public Procurement() {
		super();
	}
	/**
	 * 带参的构造方法
	 * @param proId
	 * @param proName
	 * @param goodsName
	 * @param proTime
	 * @param proNum
	 * @param proPrice
	 * @param proTolal
	 */
	public Procurement(String proId, String proName, String goodsName, String proTime, int proNum, double proPrice,
			double proTolal) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.goodsName = goodsName;
		this.proTime = proTime;
		this.proNum = proNum;
		this.proPrice = proPrice;
		this.proTolal = proTolal;
	
	}
	//生成getter/setter方法
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getProTime() {
		return proTime;
	}
	public void setProTime(String proTime) {
		this.proTime = proTime;
	}
	public int getProNum() {
		return proNum;
	}
	public void setProNum(int proNum) {
		this.proNum = proNum;
	}
	public double getProPrice() {
		return proPrice;
	}
	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}
	public double getProTolal() {
		return proTolal;
	}
	public void setProTolal(double proTolal) {
		this.proTolal = proTolal;
	}
	
}
