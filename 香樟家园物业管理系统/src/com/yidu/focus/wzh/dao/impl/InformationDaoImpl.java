  
package com.yidu.focus.wzh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.wzh.dao.InformationDao;
import com.yidu.focus.wzh.domain.Information;
import com.yidu.focus.utils.JdbcUtils;

/**
 * 功能：针对数据表的功能操作
 * 作者：伍志华
 * 日期：2019年10月12日下午3:44:28
 * 版本：1.0
 */
public class InformationDaoImpl implements InformationDao {

	/**
	 * 将实体类对象添加到数据库表中
	 * @param information 后端登录实体类对象
	 * @return rows 影响的行数（1成功，0失败）
	 */
	public int doRegister(Information information) {
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
			String sql="INSERT INTO information VALUES(?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, information.getBackId());
			pstmt.setString(2, information.getBackPwd());
			pstmt.setString(3, information.getEmpName());
			pstmt.setString(4, information.getBackPost());
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
	 * 按照后端登录编号（主键）删除数据
	 * @param cid 后端登录编号
	 * @return rows 影响的行数（1成功，0失败）
	 */
	@Override
	public int delete(String backId) {
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
			String sql="delete from information where backId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, backId);
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
	 * @param information 后端登录实体类对象
	 * @return rows 影响的行数（1成功，0失败）
	 */
	@Override
	public int update(Information information) {
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
			String sql="update information set backPwd=?,empName=?,backPost=? where backId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值			
			pstmt.setString(1, information.getBackPwd());
			pstmt.setString(2, information.getEmpName());
			pstmt.setString(3, information.getBackPost());
			pstmt.setString(4, information.getBackId());
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
	 * 按照后端登录用户和密码（从数据库表中查找数据
	 * @param empName 后端登录用户
	 * @param backPwd 后端登录密码
	 * @return information 后端登录实体对象
	 */
	@Override
	public Information doLogin(String empName,String backPwd) {
		//声明后端登录对象
		Information information=null;
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
			String sql="select * from information where empName=? and backPwd=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, empName);
			pstmt.setString(2, backPwd);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化后端登录对象
				information=new Information();
				//将结果集中数据保存到后端登录对象中
				information.setBackId(rs.getString("backId"));
				information.setBackPwd(rs.getString("backPwd"));
				information.setEmpName(rs.getString("empName"));
				information.setBackPost(rs.getString("backPost"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return information;
	}
	/**
	 * 分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @return informationList 指定页中的数据集合
	 */
	@Override
	public List<Information> findByPage(int rows, int page) {
		//定义后端登录对象集合
		List<Information> informationList=new ArrayList<Information>();
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
			String sql="select * from information limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义后端登录对象
				Information information=new Information();
				//将结果集的数据保存到后端登录对象中
				information.setBackId(rs.getString("backId"));
				information.setBackPwd(rs.getString("backPwd"));
				information.setEmpName(rs.getString("empName"));
				information.setBackPost(rs.getString("backPost"));
				//将后端登录对象添加到后端登录集合中
				informationList.add(information);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回后端登录集合
		return informationList;
	}
	/**
	 * 按特定条件分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @param condition 查询条件
	 * @return informationList 指定页中的数据集合
	 */
	@Override
	public List<Information> findByPage(int rows, int page, String condition) {
		//定义后端登录对象集合
		List<Information> informationList=new ArrayList<Information>();
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
			String sql="select * from information "+condition+" limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义后端登录对象
				Information information=new Information();
				//将结果集的数据保存到后端登录对象中
				information.setBackId(rs.getString("backId"));
				information.setBackPwd(rs.getString("backPwd"));
				information.setEmpName(rs.getString("empName"));
				information.setBackPost(rs.getString("backPost"));
				//将后端登录对象添加到后端登录集合中
				informationList.add(information);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回后端登录集合
		return informationList;
	}

	/**
	 * 统计数数据库表中数据的总行数
	 * @return rows 数据总行数
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
			String sql="select count(*) from information";
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
	 * @return rows 返回符合条件的数据行数
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
			String sql="select count(*) from information "+condition;
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
