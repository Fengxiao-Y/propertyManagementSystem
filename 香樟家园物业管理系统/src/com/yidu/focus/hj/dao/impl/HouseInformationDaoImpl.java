package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.HouseInformationDao;
import com.yidu.focus.hj.domain.HouseInformation;
/**
 * 
 * 功能：房屋信息表
 * 编写者：刘超
 * 版本：1.0
 * 日期：2019年10月31日上午8:53:23
 */
public class HouseInformationDaoImpl implements HouseInformationDao {
	/**
	 * 添加数据
	 * @param houseInformation 对象
	 * @return 影响行数
	 */
	@Override
	public int add(HouseInformation houseInformation) {
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
			String sql = "insert into houseInformation values(?,?,?,?,?,?,?)";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, houseInformation.getHouseId());
			pstmt.setString(2, houseInformation.getOwnerName());
			pstmt.setString(3, houseInformation.getHouseType());
			pstmt.setDouble(4, houseInformation.getHouseArea());
			pstmt.setString(5, houseInformation.getHouseNature());
			pstmt.setString(6, houseInformation.getHouseState());
			pstmt.setString(7, houseInformation.getHouseAddress());
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
	 * @param houseInformation 对象
	 * @return 影响行数
	 */
	@Override
	public int update(HouseInformation houseInformation) {
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
			String sql = "update houseInformation set ownerName=?,houseType=?,houseArea=?,houseNature=?,houseState=?,houseAddress=? where houseId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, houseInformation.getOwnerName());
			pstmt.setString(2, houseInformation.getHouseType());
			pstmt.setDouble(3, houseInformation.getHouseArea());
			pstmt.setString(4, houseInformation.getHouseNature());
			pstmt.setString(5, houseInformation.getHouseState());
			pstmt.setString(6, houseInformation.getHouseAddress());
			pstmt.setString(7, houseInformation.getHouseId());
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
	 * @param houseId 编号
	 * @return 影响行数
	 */
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
			String sql = "delete from houseInformation where houseId=?";
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
	/**
	 * 根据房屋编号查询某行数据
	 * @param houseId 编号
	 * @return 对象
	 */
	@Override
	public HouseInformation getHouseInformationById(String houseId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		HouseInformation houseInformation = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from houseInformation where houseId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, houseId);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				houseInformation = new HouseInformation();
				houseInformation.setHouseId(rs.getString("houseId"));
				houseInformation.setOwnerName(rs.getString("ownerName"));
				houseInformation.setHouseType(rs.getString("houseType"));
				houseInformation.setHouseArea(rs.getDouble("houseArea"));
				houseInformation.setHouseNature(rs.getString("houseNature"));
				houseInformation.setHouseState(rs.getString("houseState"));
				houseInformation.setHouseAddress(rs.getString("houseAddress"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return houseInformation;
	}
	/**
	 * 分页查询数据
	 * @param rows 行数
	 * @param page 列数
	 * @return 对象集合
	 */
	@Override
	public List<HouseInformation> selectByPage(int rows, int page) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<HouseInformation> houseInformationList = new ArrayList<HouseInformation>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from houseInformation limit ?,? ";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				HouseInformation houseInformation = new HouseInformation();
				houseInformation.setHouseId(rs.getString("houseId"));
				houseInformation.setOwnerName(rs.getString("ownerName"));
				houseInformation.setHouseType(rs.getString("houseType"));
				houseInformation.setHouseArea(rs.getDouble("houseArea"));
				houseInformation.setHouseNature(rs.getString("houseNature"));
				houseInformation.setHouseState(rs.getString("houseState"));
				houseInformation.setHouseAddress(rs.getString("houseAddress"));
				houseInformationList.add(houseInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return houseInformationList;
	}
	/**
	 * 按条件分页查询数据
	 * @param rows 行数
	 * @param page 列数
	 * @param condition 条件
	 * @return 集合对象
	 */
	@Override
	public List<HouseInformation> findByPage(int rows, int page, String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<HouseInformation> houseInformationList = new ArrayList<HouseInformation>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from houseInformation "+condition+" limit ?,? ";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				HouseInformation houseInformation = new HouseInformation();
				houseInformation.setHouseId(rs.getString("houseId"));
				houseInformation.setOwnerName(rs.getString("ownerName"));
				houseInformation.setHouseType(rs.getString("houseType"));
				houseInformation.setHouseArea(rs.getDouble("houseArea"));
				houseInformation.setHouseNature(rs.getString("houseNature"));
				houseInformation.setHouseState(rs.getString("houseState"));
				houseInformation.setHouseAddress(rs.getString("houseAddress"));
				houseInformationList.add(houseInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return houseInformationList;
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
			String sql = "select count(*) from houseInformation ";
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
			String sql = "select count(*) from houseInformation "+condition;
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
	 * 根据姓名查询某行数据
	 * @param ownerName 业主姓名
	 * @return 对象
	 */
	@Override
	public HouseInformation getHouseInformationByName(String ownerName) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		HouseInformation houseInformation = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from houseInformation where ownerName=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, ownerName);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				houseInformation = new HouseInformation();
				houseInformation.setHouseId(rs.getString("houseId"));
				houseInformation.setOwnerName(rs.getString("ownerName"));
				houseInformation.setHouseType(rs.getString("houseType"));
				houseInformation.setHouseArea(rs.getDouble("houseArea"));
				houseInformation.setHouseNature(rs.getString("houseNature"));
				houseInformation.setHouseState(rs.getString("houseState"));
				houseInformation.setHouseAddress(rs.getString("houseAddress"));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		
		return houseInformation;
	}
	@Override
	public int findIdCard(String name) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		int rows = 0;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from houseInformation wherer ownerName=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, name);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				rows++;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return rows;
	}

}
