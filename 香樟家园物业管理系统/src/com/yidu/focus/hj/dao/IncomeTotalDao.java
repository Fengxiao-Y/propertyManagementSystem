package com.yidu.focus.hj.dao;

import java.util.List;

import com.yidu.focus.hj.domain.IncomeTotal;

public interface IncomeTotalDao {

	//����������ѯ
	public List<IncomeTotal> findByPage(int rows,int page,String condition);
	public int count(String condition);
}
