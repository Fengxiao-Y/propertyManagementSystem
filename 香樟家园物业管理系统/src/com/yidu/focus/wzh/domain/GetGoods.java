
package com.yidu.focus.wzh.domain;

import java.io.Serializable;

/**
 * ���ܣ�getgoods��ʵ����
 * ���ߣ���־��
 * ���ڣ�2019��10��11������4:27:06
 * �汾��1.0
 */
public class GetGoods implements Serializable{
	
	private static final long serialVersionUID = -3614044952359682498L;
	private int ggId;//��¼���
    private String goodsName; //��Ʒ����
    private int ggNum; //��������
    private String ggName;	//������
	private String ggTime;//����ʱ��
    private String ggHandli;//������
   

    /**
     * ���εĹ��췽��
     * @param ggId ��¼���
	 * @param goodsName ��Ʒ����
	 * @param ggNum    ��������
	 * @param ggName	������
	 * @param ggTime	����ʱ��
	 * @param ggHandli	������
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
     * �����������Ĺ��췽��
	 * @param goodsName ��Ʒ����
	 * @param ggNum    ��������
	 * @param ggName	������
	 * @param ggTime	����ʱ��
	 * @param ggHandli	������
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
	 * Ĭ�ϵĹ��췽��
	 */
	public GetGoods() {
		super();
	}
	
    
    //�Զ�����getter/setter����
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
