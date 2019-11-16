package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.hj.dao.IncomeDao;
import com.yidu.focus.hj.domain.Income;
import com.yidu.focus.utils.JdbcUtils;
/**
 * 
 * 功能：实现所有收入汇总
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月31日上午8:53:23
 */
public class IncomeDaoImpl implements IncomeDao {
	/**
	 * 添加
	 * @param income
	 * @return
	 */
	@Override
	public int add(Income income) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//定义一个行数变量
		int rows = 0;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句字符串
			String sql = "insert into income(source,money,source_date) values(?,?,?)";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, income.getSource());
			pstmt.setDouble(2, income.getMoney());
			pstmt.setString(3, income.getSource_date());
			//语句对象执行，并将影响的行数赋值给rows变量
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		
		return rows;
	}
	/**
	 * 修改
	 * @param income
	 * @return
	 */
	@Override
	public int update(Income income) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//定义一个行数变量
		int rows = 0;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句字符串
			String sql = "update income set source=?,money=?,source_date=? where incomeId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, income.getSource());
			pstmt.setDouble(2, income.getMoney());
			pstmt.setString(3, income.getSource_date());
			pstmt.setInt(4, income.getIncomeId());
			//语句对象执行，并将影响的行数赋值给rows变量
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		
		return rows;
	}
	/**
	 * 删除
	 * @param incomeId
	 * @return
	 */
	@Override
	public int delete(int incomeId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//定义一个行数变量
		int rows = 0;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句字符串
			String sql = "delete from income where incomeId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, incomeId);
			//语句对象执行，并将影响的行数赋值给rows变量
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		
		return rows;
	}
	/**
	 * 查询指定行数据
	 * @param incomeId
	 * @return
	 */
	@Override
	public Income getIncomeById(int incomeId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		Income income = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from income where incomeId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, incomeId);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				income = new Income();
				income.setIncomeId(rs.getInt("incomeId"));
				income.setSource(rs.getString("source"));
				income.setMoney(rs.getDouble("money"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				income.setSource_date(sdf.format(rs.getTimestamp("source_date")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return income;
	}
	/**
	 * 按条件分页查询
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	@Override
	public List<Income> findByPage(int rows, int page, String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<Income> incomeList = new ArrayList<>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from income "+condition+" limit ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				Income income = new Income();
				income.setIncomeId(rs.getInt("incomeId"));
				income.setSource(rs.getString("source"));
				income.setMoney(rs.getDouble("money"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				income.setSource_date(sdf.format(rs.getTimestamp("source_date")));
				incomeList.add(income);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return incomeList;
	}
	/**
	 * 按条件统计记录数
	 * @param condition
	 * @return
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
