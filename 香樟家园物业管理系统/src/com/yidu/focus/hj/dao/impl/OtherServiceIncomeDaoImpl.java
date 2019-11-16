package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.OtherServiceIncomeDao;
import com.yidu.focus.hj.domain.OtherServiceIncome;

public class OtherServiceIncomeDaoImpl implements OtherServiceIncomeDao {

	@Override
	public int add(OtherServiceIncome otherServiceIncome) {
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
			String sql = "insert into otherServiceIncome values(?,?,?,?,?,?)";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, otherServiceIncome.getOsIcome());
			pstmt.setDouble(2, otherServiceIncome.getMaterialCost());
			pstmt.setDouble(3, otherServiceIncome.getLabor());
			pstmt.setDouble(4, otherServiceIncome.getOther());
			pstmt.setDouble(5, otherServiceIncome.getOsmoney());
			pstmt.setDate(6, java.sql.Date.valueOf(otherServiceIncome.getOsmonth()));
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
	public int update(OtherServiceIncome otherServiceIncome) {
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
			String sql = "update otherServiceIncome set materialCost=?,labor=?,other=?,osmoney=?,osmonth=? where osIcome=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值

			pstmt.setDouble(1, otherServiceIncome.getMaterialCost());
			pstmt.setDouble(2, otherServiceIncome.getLabor());
			pstmt.setDouble(3, otherServiceIncome.getOther());
			pstmt.setDouble(4, otherServiceIncome.getOsmoney());
			pstmt.setDate(5, java.sql.Date.valueOf(otherServiceIncome.getOsmonth()));
			pstmt.setString(6, otherServiceIncome.getOsIcome());
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
	public int delete(String osIcome) {
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
			String sql = "delete from otherServiceIncome where osIcome=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, osIcome);
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
	public OtherServiceIncome getOtherServiceIncomeById(String osIcome) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		OtherServiceIncome otherServiceIncome = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from otherServiceIncome where osIcome=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, osIcome);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				otherServiceIncome = new OtherServiceIncome();
				otherServiceIncome.setOsIcome(rs.getString("osIcome"));
				otherServiceIncome.setMaterialCost(rs.getDouble("materialCost"));
				otherServiceIncome.setLabor(rs.getDouble("labor"));
				otherServiceIncome.setOther(rs.getDouble("other"));
				otherServiceIncome.setOsmoney(rs.getDouble("osmoney"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				otherServiceIncome.setOsmonth(sdf.format(rs.getDate("osmonth")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return otherServiceIncome;
	}

	@Override
	public List<OtherServiceIncome> selectByPage(int rows, int page) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<OtherServiceIncome> otherServiceIncomeList = new ArrayList<>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from otherServiceIncome limit ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				OtherServiceIncome otherServiceIncome = new OtherServiceIncome();
				otherServiceIncome.setOsIcome(rs.getString("osIcome"));
				otherServiceIncome.setMaterialCost(rs.getDouble("materialCost"));
				otherServiceIncome.setLabor(rs.getDouble("labor"));
				otherServiceIncome.setOther(rs.getDouble("other"));
				otherServiceIncome.setOsmoney(rs.getDouble("osmoney"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				otherServiceIncome.setOsmonth(sdf.format(rs.getDate("osmonth")));
				otherServiceIncomeList.add(otherServiceIncome);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return otherServiceIncomeList;
	}

	@Override
	public List<OtherServiceIncome> findByPage(int rows, int page, String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<OtherServiceIncome> otherServiceIncomeList = new ArrayList<>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from otherServiceIncome "+condition+" limit ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				OtherServiceIncome otherServiceIncome = new OtherServiceIncome();
				otherServiceIncome.setOsIcome(rs.getString("osIcome"));
				otherServiceIncome.setMaterialCost(rs.getDouble("materialCost"));
				otherServiceIncome.setLabor(rs.getDouble("labor"));
				otherServiceIncome.setOther(rs.getDouble("other"));
				otherServiceIncome.setOsmoney(rs.getDouble("osmoney"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				otherServiceIncome.setOsmonth(sdf.format(rs.getDate("osmonth")));
				otherServiceIncomeList.add(otherServiceIncome);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return otherServiceIncomeList;
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
			String sql = "select count(*) from otherServiceIncome ";
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
			String sql = "select count(*) from otherServiceIncome "+condition;
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
