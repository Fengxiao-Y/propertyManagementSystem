package com.yidu.focus.ll.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.ll.dao.DeptDao;
import com.yidu.focus.ll.domain.Dept;
/**
 * 
 * 功能：部门表 
 * 作者：刘李
 * 日期：2019年10月31日上午9:13:50
 * 版本：1.0
 */
public class DeptDaoImpl implements DeptDao {
	/**
	 * 将实体类对象添加到数据库表中
	 * @param dept 部门实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int add(Dept dept) {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql语句字符串
			String sql="INSERT INTO dept(deptNo,deptName,empNo,empCount) "
					+ " VALUES(?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, dept.getDeptNo());
			pstmt.setString(2, dept.getDeptName());
			pstmt.setString(3, dept.getEmpNo());
			pstmt.setInt(4, dept.getEmpCount());
			//执行预编译语句对象，得到影响行数
			rows=pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		//返回影响行数
		return rows;
	}

	/**
	 * 按照部门编号（主键）删除数据
	 * @param deptNo 部门编号
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int delete(int deptNo) {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql语句字符串
			String sql="delete from dept where deptNo=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, deptNo);
			//执行预编译语句对象，得到影响行数
			rows=pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		//返回影响行数
		return rows;
	}

	/**
	 * 将实体类对象更新到数据库
	 * @param dept 部门实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int update(Dept dept) {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql语句字符串
			String sql="update dept set deptName=?,empNo=?,empCount=? "
					+ " where deptNo=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值			
			pstmt.setString(1, dept.getDeptName());
			pstmt.setString(2, dept.getEmpNo());
			pstmt.setInt(3, dept.getEmpCount());
			pstmt.setString(4, dept.getDeptNo());
			System.err.println(1111);
			//执行预编译语句对象，得到影响行数
			rows=pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		//返回影响行数
		return rows;
	}

	/**
	 * 按照部门编号（主键）从数据库表中查找数据
	 * @param deptNo 部门编号
	 * @return 部门实体对象
	 */
	@Override
	public Dept findById(int deptNo) {
		//声明部门对象
		Dept dept=null;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql字符串
			String sql="select deptNo,deptName,empNo,empCount from dept where deptNo=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, deptNo);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化部门对象
				dept=new Dept();
				//将结果集中数据保存到部门对象中
				dept.setDeptNo(rs.getString("deptNo"));
				dept.setDeptName(rs.getString("deptName"));
				dept.setEmpNo(rs.getString("empNo"));
				dept.setEmpCount(rs.getInt("empCount"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return dept;
	}

	/**
	 * 查询Dept表中所有部门信息，并封装成部门信息集合返回
	 * @return 部门信息集合
	 */
	@Override
	public List<Dept> findAll() {
		//声明数据库连接对象
		Connection conn=null;
		//声明语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象 
		ResultSet rs=null;
		//定义一个集合数组对象 
		List<Dept> deptList=new ArrayList<Dept>();

		try {
			//1:实例化连接对象
			conn=JdbcUtils.getConnection();
			//2:A:定义要执行的SQL语句字符串
			String sql="select deptNo,deptName,empNo,empCount from dept";
			//2:B:根据SQL命令和数据库对象实例化语句对象
			pstmt=conn.prepareStatement(sql);
			//3:执行语句对象，得到结果集对象
			rs=pstmt.executeQuery();
			//4:对结果集进行遍历处理，将结果集中记录行转换成部门对象，并添加到集合中
			while(rs.next()){
				//定义对象
				Dept dept=new Dept();
				//将结果集的数据保存到部门对象中
				dept.setDeptNo(rs.getString("deptNo"));
				dept.setDeptName(rs.getString("deptName"));
				dept.setEmpNo(rs.getString("empNo"));
				dept.setEmpCount(rs.getInt("empCount"));
				//将部门对象添加到部门集合中
				deptList.add(dept);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回部门信息集合
		return deptList;
	}

	/**
	 * 分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<Dept> findByPage(int rows, int page) {
		//定义部门对象集合
		List<Dept> deptList=new ArrayList<Dept>();
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果接对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql语句
			String sql="select * from dept limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义客户对象
				Dept dept=new Dept();
				//将结果集的数据保存到客户对象中
				dept.setDeptNo(rs.getString("deptNo"));
				dept.setDeptName(rs.getString("deptName"));
				dept.setEmpNo(rs.getString("empNo"));
				dept.setEmpCount(rs.getInt("empCount"));
				//将客户对象添加到客户集合中
				deptList.add(dept);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return deptList;
	}

	/**
	 * 按特定条件分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @param condition 查询条件
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<Dept> findByPage(int rows, int page, String condition) {
		//定义部门集合对象
		List<Dept> deptList=new ArrayList<Dept>();
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql字符串
			String sql="select * from dept "+condition+" limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中的参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义客户对象
				Dept dept=new Dept();
				//将结果集的数据保存到客户对象中
			
				
				dept.setDeptNo(rs.getString("deptNo"));
				dept.setDeptName(rs.getString("deptName"));
				dept.setEmpNo(rs.getString("empNo"));
				dept.setEmpCount(rs.getInt("empCount"));
				
				
				//将对象添加到客户集合中
				deptList.add(dept);
				
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return deptList;
	}

	/**
	 * 统计数数据库表中数据的总行数
	 * @return 数据总行数
	 */
	@Override
	public int count() {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql字符串
			String sql="select count(*) from dept";
			//使用数据库连接对象及sql字符串实例化语句对象
			pstmt=conn.prepareStatement(sql);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//判断结果集
			if(rs.next()){
				rows=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回行数
		return rows;
	}

	/**
	 * 按特定条件统计数数据库表中符合条件的数据行数
	 * @param condition 统计条件
	 * @return 返回符合条件的数据行数
	 */
	@Override
	public int count(String condition) {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql语句
			String sql="select count(*) from dept "+condition;
			//使用数据库连接对象及 sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行预编译语句对象得到结果集
			rs=pstmt.executeQuery();
			//判断结果集
			if(rs.next()){
				rows=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回行数
		return rows;
	}

}
