package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.hj.dao.ArrearageDao;
import com.yidu.focus.hj.domain.Arrearage;
import com.yidu.focus.utils.JdbcUtils;

public class ArrearageDaoImpl implements ArrearageDao {

	@Override
	public int add(Arrearage arrearage) {
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
			String sql = "insert into arrearage(houseId,ownerName,ownerTelphone,state,arrearageMoney,arrearageDate) values(?,?,?,?,?,?)";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, arrearage.getHouseId());
			pstmt.setString(2, arrearage.getOwnerName());
			pstmt.setString(3, arrearage.getOwnerTelphone());
			pstmt.setString(4, arrearage.getState());
			pstmt.setDouble(5, arrearage.getArrearageMoney());
			pstmt.setString(6, arrearage.getArrearageDate());
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
	public int update(Arrearage arrearage) {
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
			String sql = "update arrearage set ownerName=?,ownerTelphone=?,state=?,arrearageMoney=?,arrearageDate=? where houseId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			
			pstmt.setString(1, arrearage.getOwnerName());
			pstmt.setString(2, arrearage.getOwnerTelphone());
			pstmt.setString(3, arrearage.getState());
			pstmt.setDouble(4, arrearage.getArrearageMoney());
			pstmt.setString(5, arrearage.getArrearageDate());
			pstmt.setString(6, arrearage.getHouseId());
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
	public int delete(String houseId) {
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
			String sql = "delete from arrearage where houseId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			
			pstmt.setString(1, houseId);
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
	public Arrearage getArrearageById(String houseId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		Arrearage arrearage = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from arrearage where houseId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, houseId);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				arrearage = new Arrearage();
				arrearage.setHouseId(rs.getString("houseId"));
				arrearage.setOwnerName(rs.getString("ownerName"));
				arrearage.setOwnerTelphone(rs.getString("ownerTelphone"));
				arrearage.setState(rs.getString("state"));
				arrearage.setArrearageMoney(rs.getDouble("arrearageMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				arrearage.setArrearageDate(sdf.format(rs.getTimestamp("arrearageDate")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return arrearage;
	}

	@Override
	public List<Arrearage> findByPage(int rows, int page, String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<Arrearage> arrearageList = new ArrayList<>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from arrearage "+condition+" limit ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				Arrearage arrearage = new Arrearage();
				arrearage.setHouseId(rs.getString("houseId"));
				arrearage.setOwnerName(rs.getString("ownerName"));
				arrearage.setOwnerTelphone(rs.getString("ownerTelphone"));
				arrearage.setState(rs.getString("state"));
				arrearage.setArrearageMoney(rs.getDouble("arrearageMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				arrearage.setArrearageDate(sdf.format(rs.getTimestamp("arrearageDate")));
				arrearageList.add(arrearage);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return arrearageList;
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
			String sql = "select count(*) from arrearage "+condition;
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
	public Arrearage getArrearageByName(String ownerName) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		Arrearage arrearage = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from arrearage where ownerName=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, ownerName);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				arrearage = new Arrearage();
				arrearage.setHouseId(rs.getString("houseId"));
				arrearage.setOwnerName(rs.getString("ownerName"));
				arrearage.setOwnerTelphone(rs.getString("ownerTelphone"));
				arrearage.setState(rs.getString("state"));
				arrearage.setArrearageMoney(rs.getDouble("arrearageMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				arrearage.setArrearageDate(sdf.format(rs.getTimestamp("arrearageDate")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return arrearage;
	}
	
}
