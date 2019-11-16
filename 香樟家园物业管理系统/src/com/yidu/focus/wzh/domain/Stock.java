
package com.yidu.focus.wzh.domain;

import java.io.Serializable;

/**
 * 功能：stock的实体类
 * 作者：伍志华
 * 日期：2019年10月11日下午4:22:05
 * 版本：1.0
 */
public class Stock implements Serializable{

	private static final long serialVersionUID = -4247177927299816866L;
	private int stockId;	// 库存编号
	private String goodsName;//物品名称
	private int stockNum;//库存量
	
	
	/**
	 * 不带自增长的构造方法
	 * @param goodsName
	 * @param stockNum
	 */
	public Stock(String goodsName, int stockNum) {
		super();
		this.goodsName = goodsName;
		this.stockNum = stockNum;
	}
	/**
	 * 带参的构造方法
	 * @param stockId  物品编号
	 * @param goodsName 物品名称
	 * @param stockNum	库存量
	 */
	public Stock(int stockId, String goodsName, int stockNum) {
		super();
		this.stockId = stockId;
		this.goodsName = goodsName;
		this.stockNum = stockNum;
		
	}
	/**
	 * 默认的构造方法
	 */
	public Stock() {
		super();
	}
	/**
	 * 不带物品名称的构造方法
	 * @param stockId
	 * @param stockNum
	 */
	public Stock(int stockId, int stockNum) {
		super();
		this.stockId = stockId;
		this.stockNum = stockNum;
	}
	//自动生成getter/setter方法
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getStockNum() {
		return stockNum;
	}
	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}

}
