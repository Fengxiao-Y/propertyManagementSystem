package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.RatesDao;
import com.yidu.focus.hj.domain.Rates;
/**
 * 
 * 功能：收费标准表
 * 编写者：刘超
 * 版本：1.0
 * 日期：2019年10月31日下午6:20:31
 */
public class RatesDaoImpl implements RatesDao {
	/**
	 * 添加数据
	 * @param rates 对象
	 * @return 影响行数
	 */
	@Override
	public int add(Rates rates) {
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
			String sql = "insert into rates(itemName,itemDesc,ratesMoney) values(?,?,?)";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, rates.getItemName());
			pstmt.setString(2, rates.getItemDesc());
			pstmt.setDouble(3, rates.getRatesMoney());
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
	 * 修改数据
	 * @param rates 对象
	 * @return 影响行数
	 */
	@Override
	public int update(Rates rates) {
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
			String sql = "update rates set itemName=?,itemDesc=?,ratesMoney=? where itemId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, rates.getItemName());
			pstmt.setString(2, rates.getItemDesc());
			pstmt.setDouble(3, rates.getRatesMoney());
			pstmt.setInt(4, rates.getItemId());
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
	 * 删除数据
	 * @param itemId 编号
	 * @return 影响行数
	 */
	@Override
	public int delete(int itemId) {
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
			String sql = "delete from rates where itemId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, itemId);
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
	 * 根据员工姓名查询某行数据
	 * @param itemId 编号
	 * @return 对象
	 */
	@Override
	public Rates getRatesById(int itemId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		Rates rates = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from rates where itemId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, itemId);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				rates = new Rates();
				rates.setItemId(rs.getInt("itemId"));
				rates.setItemName(rs.getString("itemName"));
				rates.setItemDesc(rs.getString("itemDesc"));
				rates.setRatesMoney(rs.getDouble("ratesMoney"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return rates;
	}
	/**
	 * 分页查询数据
	 * @param rows 行数
	 * @param page 列数
	 * @return 对象集合
	 */
	@Override
	public List<Rates> selectByPage(int rows, int page) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<Rates> ratesList = new ArrayList<Rates>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from rates limit ?,? ";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				Rates rates = new Rates();
				rates.setItemId(rs.getInt("itemId"));
				rates.setItemName(rs.getString("itemName"));
				rates.setItemDesc(rs.getString("itemDesc"));
				rates.setRatesMoney(rs.getDouble("ratesMoney"));
				ratesList.add(rates);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return ratesList;
	}
	/**
	 * 按条件分页查询数据
	 * @param rows 行数
	 * @param page 列数
	 * @param condition 条件
	 * @return 集合对象
	 */
	@Override
	public List<Rates> findByPage(int rows, int page, String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<Rates> ratesList = new ArrayList<Rates>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from rates "+condition+" limit ?,? ";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				Rates rates = new Rates();
				rates.setItemId(rs.getInt("itemId"));
				rates.setItemName(rs.getString("itemName"));
				rates.setItemDesc(rs.getString("itemDesc"));
				rates.setRatesMoney(rs.getDouble("ratesMoney"));
				ratesList.add(rates);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return ratesList;
	}
	/**
	 * 统计记录数
	 * @return 记录行数
	 */
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
			String sql = "select count(*) from rates ";
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
	/**
	 * 按条件统计记录数
	 * @param condition 条件
	 * @return 筛选记录行数
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
			String sql = "select count(*) from rates "+condition;
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
