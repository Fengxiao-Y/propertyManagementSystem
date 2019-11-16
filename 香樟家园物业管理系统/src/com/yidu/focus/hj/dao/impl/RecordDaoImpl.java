package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.hj.dao.RecordDao;
import com.yidu.focus.hj.domain.Record;
import com.yidu.focus.utils.JdbcUtils;

public class RecordDaoImpl implements RecordDao {

	@Override
	public int add(Record record) {
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
			String sql = "insert into record(recordId,ownerName,ownerTelphone,recordType,recordMoney,recordDate) values(?,?,?,?,?,?)";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, record.getRecordId());
			pstmt.setString(2, record.getOwnerName());
			pstmt.setString(3, record.getOwnerTelphone());
			pstmt.setString(4, record.getRecordType());
			pstmt.setDouble(5, record.getRecordMoney());
			pstmt.setString(6, record.getRecordDate());
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
	public int update(Record record) {
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
			String sql = "update record set ownerName=?,ownerTelphone=?,recordType=?,recordMoney=?,recordDate=? where recordId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			
			pstmt.setString(1, record.getOwnerName());
			pstmt.setString(2, record.getOwnerTelphone());
			pstmt.setString(3, record.getRecordType());
			pstmt.setDouble(4, record.getRecordMoney());
			pstmt.setString(5, record.getRecordDate());
			pstmt.setInt(6, record.getRecordId());
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
	public int delete(int recordId) {
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
			String sql = "delete from record where recordId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			
			pstmt.setInt(1, recordId);
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
	public Record getRecordById(int recordId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		Record record = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from record where recordId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, recordId);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				record = new Record();
				record.setRecordId(rs.getInt("recordId"));
				record.setOwnerName(rs.getString("ownerName"));
				record.setOwnerTelphone(rs.getString("ownerTelphone"));
				record.setRecordType(rs.getString("recordType"));
				record.setRecordMoney(rs.getDouble("recordMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				record.setRecordDate(sdf.format(rs.getTimestamp("recordDate")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return record;	
	}


	@Override
	public List<Record> findByPage(int rows, int page, String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<Record> recordList = new ArrayList<>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from record "+condition+" limit ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.setRecordId(rs.getInt("recordId"));
				record.setOwnerName(rs.getString("ownerName"));
				record.setOwnerTelphone(rs.getString("ownerTelphone"));
				record.setRecordType(rs.getString("recordType"));
				record.setRecordMoney(rs.getDouble("recordMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				record.setRecordDate(sdf.format(rs.getTimestamp("recordDate")));
				recordList.add(record);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return recordList;
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
			String sql = "select count(*) from record "+condition;
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
	public List<Record> getRecordByName(String ownerName) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<Record> recordList = new ArrayList<>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from record where ownerName=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, ownerName);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.setRecordId(rs.getInt("recordId"));
				record.setOwnerName(rs.getString("ownerName"));
				record.setOwnerTelphone(rs.getString("ownerTelphone"));
				record.setRecordType(rs.getString("recordType"));
				record.setRecordMoney(rs.getDouble("recordMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				record.setRecordDate(sdf.format(rs.getTimestamp("recordDate")));
				recordList.add(record);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return recordList;
	}
}
