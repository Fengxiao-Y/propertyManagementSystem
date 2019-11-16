package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.hj.dao.CarArrearageDao;
import com.yidu.focus.hj.domain.CarArrearage;
import com.yidu.focus.utils.JdbcUtils;

public class CarArrearageDaoImpl implements CarArrearageDao {

	@Override
	public int add(CarArrearage carArrearage) {
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
			String sql = "insert into carArrearage(parkId,ownerName,ownerTelphone,parkStatus,carMoney,carDate) values(?,?,?,?,?,?)";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, carArrearage.getParkId());
			pstmt.setString(2, carArrearage.getOwnerName());
			pstmt.setString(3, carArrearage.getOwnerTelphone());
			pstmt.setString(4, carArrearage.getParkStatus());
			pstmt.setDouble(5, carArrearage.getCarMoney());
			pstmt.setString(6, carArrearage.getCarDate());
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
	public int update(CarArrearage carArrearage) {
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
			String sql = "update carArrearage set ownerName=?,ownerTelphone=?,parkStatus=?,carMoney=?,carDate=? where parkId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			
			pstmt.setString(1, carArrearage.getOwnerName());
			pstmt.setString(2, carArrearage.getOwnerTelphone());
			pstmt.setString(3, carArrearage.getParkStatus());
			pstmt.setDouble(4, carArrearage.getCarMoney());
			pstmt.setString(5, carArrearage.getCarDate());
			pstmt.setString(6, carArrearage.getParkId());
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
	public int delete(String parkId) {
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
			String sql = "delete from carArrearage where parkId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			
			pstmt.setString(1, parkId);
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
	public CarArrearage getCarArrearageById(String parkId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		CarArrearage carArrearage = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from carArrearage where parkId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, parkId);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				carArrearage = new CarArrearage();
				carArrearage.setParkId(rs.getString("parkId"));
				carArrearage.setOwnerName(rs.getString("ownerName"));
				carArrearage.setOwnerTelphone(rs.getString("ownerTelphone"));
				carArrearage.setParkStatus(rs.getString("parkStatus"));
				carArrearage.setCarMoney(rs.getDouble("carMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carArrearage.setCarDate(sdf.format(rs.getTimestamp("carDate")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return carArrearage;
	}

	@Override
	public List<CarArrearage> findByPage(int rows, int page, String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<CarArrearage> carArrearageList = new ArrayList<>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from carArrearage "+condition+" limit ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				CarArrearage carArrearage = new CarArrearage();
				carArrearage.setParkId(rs.getString("parkId"));
				carArrearage.setOwnerName(rs.getString("ownerName"));
				carArrearage.setOwnerTelphone(rs.getString("ownerTelphone"));
				carArrearage.setParkStatus(rs.getString("parkStatus"));
				carArrearage.setCarMoney(rs.getDouble("carMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carArrearage.setCarDate(sdf.format(rs.getTimestamp("carDate")));
				carArrearageList.add(carArrearage);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return carArrearageList;
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
			String sql = "select count(*) from carArrearage "+condition;
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
	public CarArrearage getCarArrearageByName(String ownerName) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		CarArrearage carArrearage = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from carArrearage where ownerName=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, ownerName);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				carArrearage = new CarArrearage();
				carArrearage.setParkId(rs.getString("parkId"));
				carArrearage.setOwnerName(rs.getString("ownerName"));
				carArrearage.setOwnerTelphone(rs.getString("ownerTelphone"));
				carArrearage.setParkStatus(rs.getString("parkStatus"));
				carArrearage.setCarMoney(rs.getDouble("carMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carArrearage.setCarDate(sdf.format(rs.getTimestamp("carDate")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return carArrearage;
	}
}
