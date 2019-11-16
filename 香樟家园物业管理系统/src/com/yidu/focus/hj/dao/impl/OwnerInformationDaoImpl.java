package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.OwnerInformationDao;
import com.yidu.focus.hj.domain.OwnerInformation;
/**
 * 
 * 功能：业主信息
 * 编写者：刘超
 * 版本：1.0
 * 日期：2019年10月31日上午8:53:23
 */
public class OwnerInformationDaoImpl implements OwnerInformationDao {
	/**
	 * 添加数据
	 * @param OtherServiceIncome 业主对象
	 * @return 影响行数
	 */
	@Override
	public int add(OwnerInformation ownerInformation) {
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
			String sql = "insert into ownerInformation values(?,?,?,?,?,?,?,?)";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, ownerInformation.getOwnerId());
			pstmt.setString(2, ownerInformation.getHouseId());
			pstmt.setString(3, ownerInformation.getOwnerName());
			pstmt.setString(4, ownerInformation.getOwnerSex());
			pstmt.setString(5, ownerInformation.getOwnerIdcard());
			pstmt.setString(6, ownerInformation.getOwnerTelphone());
			pstmt.setString(7, ownerInformation.getOwnerEmail());
			pstmt.setString(8, ownerInformation.getOwnerAddress());
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
	 * @param OtherServiceIncome 对象
	 * @return 影响行数
	 */
	@Override
	public int update(OwnerInformation ownerInformation) {
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
			String sql = "update ownerInformation set houseId=?,ownerName=?,ownerSex=?,ownerIdcard=?,ownerTelphone=?,ownerEmail=?,ownerAddress=? where ownerId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, ownerInformation.getHouseId());
			pstmt.setString(2, ownerInformation.getOwnerName());
			pstmt.setString(3, ownerInformation.getOwnerSex());
			pstmt.setString(4, ownerInformation.getOwnerIdcard());
			pstmt.setString(5, ownerInformation.getOwnerTelphone());
			pstmt.setString(6, ownerInformation.getOwnerEmail());
			pstmt.setString(7, ownerInformation.getOwnerAddress());
			pstmt.setString(8, ownerInformation.getOwnerId());
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
	 * @param osIcome 编号
	 * @return 影响行数
	 */
	@Override
	public int delete(String ownerId) {
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
			String sql = "delete from ownerInformation where ownerId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, ownerId);
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
	 * @param osIcome 编号
	 * @return 对象
	 */
	@Override
	public OwnerInformation getOwnerInformationById(String houseId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		//声明一个存储数据对象
		OwnerInformation ownerInformation = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句对象
			String sql = "select * from ownerInformation houseId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, houseId);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				ownerInformation = new OwnerInformation();
				ownerInformation.setOwnerId(rs.getString("ownerId"));
				ownerInformation.setHouseId(rs.getString("houseId"));
				ownerInformation.setOwnerName(rs.getString("ownerName"));
				ownerInformation.setOwnerSex(rs.getString("ownerSex"));
				ownerInformation.setOwnerIdcard(rs.getString("ownerIdcard"));
				ownerInformation.setOwnerTelphone(rs.getString("ownerTelphone"));
				ownerInformation.setOwnerEmail(rs.getString("ownerEmail"));
				ownerInformation.setOwnerAddress(rs.getString("ownerAddress"));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			JdbcUtils.close(rs, pstmt, conn);
		}
		return ownerInformation;
	}
	/**
	 * 分页查询数据
	 * @param rows 行数
	 * @param page 列数
	 * @return 对象集合
	 */
	@Override
	public List<OwnerInformation> selectByPage(int rows, int page) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		//声明一个对象集合
		List<OwnerInformation> ownerInformationList = new ArrayList<OwnerInformation>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句对象
			String sql="select * from ownerInformation limit ?,?";
			//实例化语句对象 
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				OwnerInformation ownerInformation = new OwnerInformation();
				ownerInformation.setOwnerId(rs.getString("ownerId"));
				ownerInformation.setHouseId(rs.getString("houseId"));
				ownerInformation.setOwnerName(rs.getString("ownerName"));
				ownerInformation.setOwnerSex(rs.getString("ownerSex"));
				ownerInformation.setOwnerIdcard(rs.getString("ownerIdcard"));
				ownerInformation.setOwnerTelphone(rs.getString("ownerTelphone"));
				ownerInformation.setOwnerEmail(rs.getString("ownerEmail"));
				ownerInformation.setOwnerAddress(rs.getString("ownerAddress"));
				ownerInformationList.add(ownerInformation);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return ownerInformationList;
	}
	/**
	 * 按条件分页查询数据
	 * @param rows 行数
	 * @param page 列数
	 * @param condition 条件
	 * @return 集合对象
	 */
	@Override
	public List<OwnerInformation> findByPage(int rows, int page, String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		//声明一个对象集合
		List<OwnerInformation> ownerInformationList = new ArrayList<OwnerInformation>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句对象
			String sql="select * from ownerInformation "+condition+" limit ?,?";
			//实例化语句对象 
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				OwnerInformation ownerInformation = new OwnerInformation();
				ownerInformation.setOwnerId(rs.getString("ownerId"));
				ownerInformation.setHouseId(rs.getString("houseId"));
				ownerInformation.setOwnerName(rs.getString("ownerName"));
				ownerInformation.setOwnerSex(rs.getString("ownerSex"));
				ownerInformation.setOwnerIdcard(rs.getString("ownerIdcard"));
				ownerInformation.setOwnerTelphone(rs.getString("ownerTelphone"));
				ownerInformation.setOwnerEmail(rs.getString("ownerEmail"));
				ownerInformation.setOwnerAddress(rs.getString("ownerAddress"));
				ownerInformationList.add(ownerInformation);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return ownerInformationList;
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
			String sql = "select count(*) from ownerInformation ";
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
			String sql = "select count(*) from ownerInformation "+condition;
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
	public int deleteName(String name) {
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
			String sql = "delete from ownerInformation where ownerName=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, name);
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
	 * @param wageid 编号
	 * @return 对象
	 */
	@Override
	public OwnerInformation findOwnerInformationByName(String ownerName) {
		//声明数据库连接对象
	Connection conn = null;
	//声明语句对象
	PreparedStatement pstmt = null;
	//声明结果集对象
	ResultSet rs = null;
	//声明一个存储数据对象
	OwnerInformation ownerInformation = null;
	try {
		//实例化数据库连接对象
		conn = JdbcUtils.getConnection();
		//定义一个sql执行语句对象
		String sql = "select * from ownerInformation ownerName=?";
		//实例化语句对象
		pstmt = conn.prepareStatement(sql);
		//给sql语句赋值
		pstmt.setString(1, ownerName);
		//实例化结果集对象
		rs = pstmt.executeQuery();
		if(rs.next()){
			ownerInformation = new OwnerInformation();
			ownerInformation.setOwnerId(rs.getString("ownerId"));
			ownerInformation.setHouseId(rs.getString("houseId"));
			ownerInformation.setOwnerName(rs.getString("ownerName"));
			ownerInformation.setOwnerSex(rs.getString("ownerSex"));
			ownerInformation.setOwnerIdcard(rs.getString("ownerIdcard"));
			ownerInformation.setOwnerTelphone(rs.getString("ownerTelphone"));
			ownerInformation.setOwnerEmail(rs.getString("ownerEmail"));
			ownerInformation.setOwnerAddress(rs.getString("ownerAddress"));
		}
		
	} catch (SQLException e) {
		throw new RuntimeException(e.getMessage(),e);
	} finally {
		JdbcUtils.close(rs, pstmt, conn);
	}
		return ownerInformation;
	}

}
