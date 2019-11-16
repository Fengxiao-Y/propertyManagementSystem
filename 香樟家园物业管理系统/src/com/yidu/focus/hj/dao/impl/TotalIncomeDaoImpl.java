package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.TotalIncomeDao;
import com.yidu.focus.hj.domain.TotalIncome;

public class TotalIncomeDaoImpl implements TotalIncomeDao {

	@Override
	public int add(TotalIncome totalIncome) {
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
			String sql = "insert into totalIncome values(?,?,?,?,?,?,?)";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, totalIncome.getTotalId());
			pstmt.setDouble(2, totalIncome.getPropertyFeeIncome());
			pstmt.setDouble(3, totalIncome.getParkFeeIncome());
			pstmt.setDouble(4, totalIncome.getOtherServiceIncome());
			pstmt.setDouble(5, totalIncome.getOtherIncome());
			pstmt.setDouble(6, totalIncome.getTotalIncome());
			pstmt.setString(7, totalIncome.getTotalIncomeMonth());
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

	@Override
	public int update(TotalIncome totalIncome) {
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
			String sql = "update totalIncome set propertyFeeIncome=?,parkFeeIncome=?,otherServiceIncome=?,otherIncome=?,totalIncome=?,totalIncomeMonth=? where totalId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值  
			pstmt.setDouble(1, totalIncome.getPropertyFeeIncome());
			pstmt.setDouble(2, totalIncome.getParkFeeIncome());
			pstmt.setDouble(3, totalIncome.getOtherServiceIncome());
			pstmt.setDouble(4, totalIncome.getOtherIncome());
			pstmt.setDouble(5, totalIncome.getTotalIncome());
			pstmt.setString(6, totalIncome.getTotalIncomeMonth());
			pstmt.setString(7, totalIncome.getTotalId());
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

	@Override
	public int delete(String totalId) {
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
			String sql = "delete from totalIncome where totalId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值  
			pstmt.setString(1, totalId);
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

	@Override
	public TotalIncome getTotalIncomeById(String totalId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		TotalIncome totalIncome = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from totalIncome where totalId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, totalId);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				totalIncome = new TotalIncome();
				totalIncome.setTotalId(rs.getString("totalId"));
				totalIncome.setPropertyFeeIncome(rs.getDouble("propertyFeeIncome"));
				totalIncome.setParkFeeIncome(rs.getDouble("parkFeeIncome"));
				totalIncome.setOtherServiceIncome(rs.getDouble("otherServiceIncome"));
				totalIncome.setOtherIncome(rs.getDouble("otherIncome"));
				totalIncome.setTotalIncome(rs.getDouble("totalIncome"));
				totalIncome.setTotalIncomeMonth(rs.getString("totalIncomeMonth"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return totalIncome;
	}

	@Override
	public List<TotalIncome> selectByPage(int rows, int page) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<TotalIncome> totalIncomeList = new ArrayList<>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from totalIncome limit ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				TotalIncome totalIncome = new TotalIncome();
				totalIncome.setTotalId(rs.getString("totalId"));
				totalIncome.setPropertyFeeIncome(rs.getDouble("propertyFeeIncome"));
				totalIncome.setParkFeeIncome(rs.getDouble("parkFeeIncome"));
				totalIncome.setOtherServiceIncome(rs.getDouble("otherServiceIncome"));
				totalIncome.setOtherIncome(rs.getDouble("otherIncome"));
				totalIncome.setTotalIncome(rs.getDouble("totalIncome"));
				totalIncome.setTotalIncomeMonth(rs.getString("totalIncomeMonth"));
				totalIncomeList.add(totalIncome);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return totalIncomeList;
	}

	@Override
	public List<TotalIncome> findByPage(int rows, int page, String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<TotalIncome> totalIncomeList = new ArrayList<>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from totalIncome "+condition+" limit ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				TotalIncome totalIncome = new TotalIncome();
				totalIncome.setTotalId(rs.getString("totalId"));
				totalIncome.setPropertyFeeIncome(rs.getDouble("propertyFeeIncome"));
				totalIncome.setParkFeeIncome(rs.getDouble("parkFeeIncome"));
				totalIncome.setOtherServiceIncome(rs.getDouble("otherServiceIncome"));
				totalIncome.setOtherIncome(rs.getDouble("otherIncome"));
				totalIncome.setTotalIncome(rs.getDouble("totalIncome"));
				totalIncome.setTotalIncomeMonth(rs.getString("totalIncomeMonth"));
				totalIncomeList.add(totalIncome);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return totalIncomeList;
	}

	@Override
	public int count() {
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
			String sql = "select count(*) from totalIncome ";
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
			String sql = "select count(*) from totalIncome "+condition;
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
