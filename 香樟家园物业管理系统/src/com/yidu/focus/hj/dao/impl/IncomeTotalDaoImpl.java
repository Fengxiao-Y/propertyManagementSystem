package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.hj.dao.IncomeTotalDao;
import com.yidu.focus.hj.domain.IncomeTotal;
import com.yidu.focus.utils.JdbcUtils;
/**
 * 
 * 功能：实现所有收入汇总
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月31日上午8:53:23
 */
public class IncomeTotalDaoImpl implements IncomeTotalDao {
	/**
	 * 根据条件查询
	 * @param rows 行数
	 * @param page 列数
	 * @param condition 条件
	 * @return 集合
	 */
	@Override
	public List<IncomeTotal> findByPage(int rows,int page,String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		//创建集合
		List<IncomeTotal> incomeTotalList = new ArrayList<IncomeTotal>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句
			String sql = "SELECT source,SUM(money) as money,DATE_FORMAT(source_date,'%Y-%m-%d') as source_date FROM income "+condition+" GROUP BY source,DATE_FORMAT(source_date,'%Y-%m-%d') ORDER BY source LIMIT ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				IncomeTotal incomeTotal = new IncomeTotal();
				incomeTotal.setSource(rs.getString("source"));
				incomeTotal.setMoney(rs.getDouble("money"));
				incomeTotal.setMoneySum(incomeTotal.getMoney()+incomeTotal.getMoneySum());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				incomeTotal.setSource_date(sdf.format(rs.getDate("source_date")));				
				incomeTotalList.add(incomeTotal);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			JdbcUtils.close(rs, pstmt, conn);
		}
		return incomeTotalList;
	}
	/**
	 * 按条件统计记录数
	 * @param condition 条件
	 * @return 记录数
	 */
	@Override
	public int count(String condition) {
		//声明一个数据库连接对象
		Connection conn = null;
		//什么语句对象
		PreparedStatement pstmt = null;
		//定义一个返回行数变量
		int rows=0;
		//声明结果集对象
		ResultSet rs= null;
		try {
			//实例化语句对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql = "select count(*) from income "+condition;
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				rows = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtils.close(rs, pstmt, conn);
		}
		return rows;
	}

}
