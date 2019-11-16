package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.LesseeInformationDao;
import com.yidu.focus.hj.domain.LesseeInformation;
/**
 * 
 * 功能：租户信息表
 * 编写者：刘超
 * 版本：1.0
 * 日期：2019年10月31日下午6:17:30
 */
public class LesseeInformationDaoImpl implements LesseeInformationDao {

	@Override
	public int add(LesseeInformation lesseeInformation) {
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
			String sql = "insert into lesseeInformation(houseId,lesseeName,lesseeGender,lesseeIdcard,lesseeTelphone,startTime,endTime) values(?,?,?,?,?,?,?)";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, lesseeInformation.getHouseId());
			pstmt.setString(2, lesseeInformation.getLesseeName());
			pstmt.setString(3, lesseeInformation.getLesseeGender());
			pstmt.setString(4, lesseeInformation.getLesseeIdcard());
			pstmt.setString(5, lesseeInformation.getLesseeTelphone());
			pstmt.setString(6, lesseeInformation.getStartTime());
			pstmt.setString(7, lesseeInformation.getEndTime());
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
	public int update(LesseeInformation lesseeInformation) {
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
			String sql = "update lesseeInformation set houseId=?,lesseeName=?,lesseeGender=?,lesseeIdcard=?,lesseeTelphone=?,startTime=?,endTime=? where leaseContractId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, lesseeInformation.getHouseId());
			pstmt.setString(2, lesseeInformation.getLesseeName());
			pstmt.setString(3, lesseeInformation.getLesseeGender());
			pstmt.setString(4, lesseeInformation.getLesseeIdcard());
			pstmt.setString(5, lesseeInformation.getLesseeTelphone());
			pstmt.setString(6, lesseeInformation.getStartTime());
			pstmt.setString(7, lesseeInformation.getEndTime());
			pstmt.setInt(8, lesseeInformation.getLeaseContractId());
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
	public int delete(int leaseContractId) {
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
			String sql = "delete from lesseeInformation where leaseContractId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, leaseContractId);
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
	public LesseeInformation getLesseeInformationById(String houseId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		//创建一个租户对象
		LesseeInformation lesseeInformation = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句
			String sql = "select * from lesseeInformation where houseId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, houseId);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				//实例化租户对象
				lesseeInformation = new LesseeInformation();
				//将数据保存到对象中
				lesseeInformation.setLeaseContractId(rs.getInt("leaseContractId"));
				lesseeInformation.setHouseId(rs.getString("houseId"));
				lesseeInformation.setLesseeName(rs.getString("lesseeName"));
				lesseeInformation.setLesseeGender(rs.getString("lesseeGender"));
				lesseeInformation.setLesseeIdcard(rs.getString("lesseeIdcard"));
				lesseeInformation.setLesseeTelphone(rs.getString("lesseeTelphone"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				lesseeInformation.setStartTime(sdf.format(rs.getDate("startTime")));
				lesseeInformation.setEndTime(sdf.format(rs.getDate("endTime")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return lesseeInformation;
	}

	@Override
	public List<LesseeInformation> selectByPage(int rows, int page) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		//创建一个租户对象
		List<LesseeInformation> lesseeInformationList = new ArrayList<LesseeInformation>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句
			String sql = "select * from lesseeInformation limit ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				//实例化租户对象
				LesseeInformation lesseeInformation = new LesseeInformation();
				//将数据保存到对象中
				lesseeInformation.setLeaseContractId(rs.getInt("leaseContractId"));
				lesseeInformation.setHouseId(rs.getString("houseId"));
				lesseeInformation.setLesseeName(rs.getString("lesseeName"));
				lesseeInformation.setLesseeGender(rs.getString("lesseeGender"));
				lesseeInformation.setLesseeIdcard(rs.getString("lesseeIdcard"));
				lesseeInformation.setLesseeTelphone(rs.getString("lesseeTelphone"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				lesseeInformation.setStartTime(sdf.format(rs.getDate("startTime")));
				lesseeInformation.setEndTime(sdf.format(rs.getDate("endTime")));
				lesseeInformationList.add(lesseeInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return lesseeInformationList;
	}

	@Override
	public List<LesseeInformation> findByPage(int rows, int page, String condition) {
		
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		//创建一个租户集合
		List<LesseeInformation> lesseeInformationList = new ArrayList<LesseeInformation>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句
			String sql = "select * from lesseeInformation "+condition+" limit ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				//实例化租户对象
				LesseeInformation lesseeInformation = new LesseeInformation();
				//将数据保存到对象中
				lesseeInformation.setLeaseContractId(rs.getInt("leaseContractId"));
				lesseeInformation.setHouseId(rs.getString("houseId"));
				lesseeInformation.setLesseeName(rs.getString("lesseeName"));
				lesseeInformation.setLesseeGender(rs.getString("lesseeGender"));
				lesseeInformation.setLesseeIdcard(rs.getString("lesseeIdcard"));
				lesseeInformation.setLesseeTelphone(rs.getString("lesseeTelphone"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				lesseeInformation.setStartTime(sdf.format(rs.getDate("startTime")));
				lesseeInformation.setEndTime(sdf.format(rs.getDate("endTime")));
				lesseeInformationList.add(lesseeInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return lesseeInformationList;
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
			String sql = "select count(*) from lesseeInformation ";
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
			String sql = "select count(*) from lesseeInformation "+condition;
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
	public int deleteA(String houseId) {
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
			String sql = "delete from lesseeInformation where houseId=?";
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

}
