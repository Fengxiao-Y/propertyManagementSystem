package com.yidu.focus.ll.dao;

import java.util.List;

import com.yidu.focus.ll.domain.Rules;

public interface RulesDao {
	/**
	 * 添加
	 * @param rules
	 * @return
	 */
	public int add(Rules rules);
	/**
	 * 删除
	 * @param rulesId
	 * @return
	 */
	public int delete(String rulesId);
	/**
	 * 修改
	 * @param rules
	 * @return
	 */
	public int update(Rules rules);
	
	/**
	 * 根据文件编号查找单个信息
	 * @param rulesId
	 * @return
	 */
	public Rules findById(String rulesId);
	
	/**
	 * 查找所有
	 * @return
	 */
	public List<Rules> findAll();
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有部门信息集合
	 */
	public List<Rules> findByPage(int rows,int page);

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	public List<Rules> findByPage(int rows,int page,String condition);

	/**
	 * 统计记录数
	 * @return 表中所有记录行数
	 */
	public int count();
	
	/**
	 * 统计符合条件的记录数
	 * @param condition 条件
	 * @return
	 */
	public int count(String condition);
}
