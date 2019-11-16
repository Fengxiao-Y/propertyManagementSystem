
package com.yidu.focus.wzh.domain;

import java.io.Serializable;

/**
 * 功能：getgoods的实体类
 * 作者：伍志华
 * 日期：2019年10月11日下午4:27:06
 * 版本：1.0
 */
public class GetGoods implements Serializable{
	
	private static final long serialVersionUID = -3614044952359682498L;
	private int ggId;//记录编号
    private String goodsName; //物品名称
    private int ggNum; //领用数量
    private String ggName;	//领用人
	private String ggTime;//领用时间
    private String ggHandli;//经办人
   

    /**
     * 带参的构造方法
     * @param ggId 记录编号
	 * @param goodsName 物品名称
	 * @param ggNum    领用数量
	 * @param ggName	领用人
	 * @param ggTime	领用时间
	 * @param ggHandli	经办人
	 */
	public GetGoods(int ggId, String goodsName, int ggNum, String ggName, String ggTime, String ggHandli) {
		super();
		this.ggId = ggId;
		this.goodsName = goodsName;
		this.ggNum = ggNum;
		this.ggName = ggName;
		this.ggTime = ggTime;
		this.ggHandli = ggHandli;
	}

	/**
     * 不带自增长的构造方法
	 * @param goodsName 物品名称
	 * @param ggNum    领用数量
	 * @param ggName	领用人
	 * @param ggTime	领用时间
	 * @param ggHandli	经办人
	 */
	public GetGoods(String goodsName, int ggNum, String ggName, String ggTime, String ggHandli) {
		super();
		this.goodsName = goodsName;
		this.ggNum = ggNum;
		this.ggName = ggName;
		this.ggTime = ggTime;
		this.ggHandli = ggHandli;
	}

    /**
	 * 默认的构造方法
	 */
	public GetGoods() {
		super();
	}
	
    
    //自动生成getter/setter方法
	 public int getGgId() {
			return ggId;
	}
	public void setGgId(int ggId) {
			this.ggId = ggId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getggNum() {
		return ggNum;
	}
	public void setggNum(int ggNum) {
		this.ggNum = ggNum;
	}
	public String getggName() {
		return ggName;
	}
	public void setggName(String ggName) {
		this.ggName = ggName;
	}
	public String getggTime() {
		return ggTime;
	}
	public void setggTime(String ggTime) {
		this.ggTime = ggTime;
	}
	public String getggHandli() {
		return ggHandli;
	}
	public void setggHandli(String ggHandli) {
		this.ggHandli = ggHandli;
	}
}
