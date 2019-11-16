package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.WageDao;
import com.yidu.focus.hj.domain.Wage;

/**
 * 
 * 功能：工资表实现类
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月31日下午6:24:21
 */
public class WageDaoImpl implements WageDao {
	/**
	 * 添加数据
	 * @param wage 工资对象
	 * @return 影响行数
	 */
	@Override
	public int add(Wage wage) {
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
			String sql = "insert into wage(empName,salary,commision,withhold,playMoney,wageMonth) values(?,?,?,?,?,?)";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, wage.getEmpName());
			pstmt.setDouble(2, wage.getSalary());
			pstmt.setDouble(3, wage.getCommision());
			pstmt.setDouble(4, wage.getWithhold());
			pstmt.setDouble(5, wage.getPlayMoney());
			pstmt.setString(6, wage.getWageMonth());
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
	 * @param wage 对象
	 * @return 影响行数
	 */
	@Override
	public int update(Wage wage) {
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
			String sql = "update wage set empName=?,salary=?,commision=?,withhold=?,playMoney=?,wageMonth=? where wageid=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, wage.getEmpName());
			pstmt.setDouble(2, wage.getSalary());
			pstmt.setDouble(3, wage.getCommision());
			pstmt.setDouble(4, wage.getWithhold());
			pstmt.setDouble(5, wage.getPlayMoney());
			pstmt.setString(6, wage.getWageMonth());
			pstmt.setInt(7, wage.getWageid());
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
	 * @param wageid 编号
	 * @return 影响行数
	 */
	@Override
	public int delete(int wageid) {
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
			String sql = "delete from wage where wageid=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, wageid);
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
	 * @param wageid 编号
	 * @return 对象
	 */
	@Override
	public Wage getWageById(String empName) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		//声明一个存储数据对象
		Wage wage = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句对象
			String sql = "select * from wage where empName=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, empName);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				wage = new Wage();
				wage.setWageid(rs.getInt("wageid"));
				wage.setEmpName(rs.getString("empName"));
				wage.setSalary(rs.getDouble("salary"));
				wage.setCommision(rs.getDouble("commision"));
				wage.setWithhold(rs.getDouble("withhold"));
				wage.setPlayMoney(rs.getDouble("playMoney"));
				wage.setWageMonth(rs.getString("wageMonth"));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			JdbcUtils.close(rs, pstmt, conn);
		}
		return wage;
	}
	/**
	 * 分页查询数据
	 * @param rows 行数
	 * @param page 列数
	 * @return 对象集合
	 */
	@Override
	public List<Wage> selectByPage(int rows, int page) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		//声明一个对象集合
		List<Wage> wageList = new ArrayList<Wage>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句对象
			String sql="select * from wage limit ?,?";
			
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				Wage wage = new Wage();
				wage.setWageid(rs.getInt("wageid"));
				wage.setEmpName(rs.getString("empName"));
				wage.setSalary(rs.getDouble("salary"));
				wage.setCommision(rs.getDouble("commision"));
				wage.setWithhold(rs.getDouble("withhold"));
				wage.setPlayMoney(rs.getDouble("playMoney"));
				wage.setWageMonth(rs.getString("wageMonth"));
				wageList.add(wage);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return wageList;
	}
	/**
	 * 按条件分页查询数据
	 * @param rows 行数
	 * @param page 列数
	 * @param condition 条件
	 * @return 集合对象
	 */
	@Override
	public List<Wage> findByPage(int rows, int page, String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		//声明一个对象集合
		List<Wage> wageList = new ArrayList<Wage>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句对象
			String sql="select * from wage "+condition+" limit ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				Wage wage = new Wage();
				wage.setWageid(rs.getInt("wageid"));
				wage.setEmpName(rs.getString("empName"));
				wage.setSalary(rs.getDouble("salary"));
				wage.setCommision(rs.getDouble("commision"));
				wage.setWithhold(rs.getDouble("withhold"));
				wage.setPlayMoney(rs.getDouble("playMoney"));
				wage.setWageMonth(rs.getString("wageMonth"));
				wageList.add(wage);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return wageList;
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
			String sql = "select count(*) from wage";
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
			String sql = "select count(*) from wage "+condition;
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
