package com.yidu.focus.hxl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hxl.dao.VisitorRegisterDao;
import com.yidu.focus.hxl.domain.VisitorRegister;
/**
 * 
 * 功能：来访登记表 
 * 作者：何希龙
 * 日期：2019年10月31日上午9:04:47
 * 版本：1.0
 */
public class VisitorRegisterDaoImpl implements VisitorRegisterDao {
	 /**
     * 添加信息
     * @param visitorRegister
     * @return
     */
	@Override
	public int add(VisitorRegister visitorRegister) {
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
			
			String sql="INSERT INTO visitorRegister(visitorName,visitorGender,"
					+ "visitorIdNum,visitorTelphone,vistiAddress,vistiReason,"
					+ "cometime,leaveTime,empNo) VALUES(?,?,?,?,?,?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			
			pstmt.setString(1, visitorRegister.getVisitorName());
			pstmt.setString(2, visitorRegister.getVisitorGender());
			pstmt.setString(3, visitorRegister.getVisitorIdNum());
			pstmt.setString(4, visitorRegister.getVisitorTelphone());
			pstmt.setString(5, visitorRegister.getVistiAddress());
			pstmt.setString(6, visitorRegister.getVistiReason());
			//将字符串转换成数据库日期数据
			pstmt.setString(7, visitorRegister.getCometime());
			pstmt.setString(8, visitorRegister.getLeaveTime());
			pstmt.setString(9, visitorRegister.getEmpNo());
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
	 * 删除
	 * @param visitorId
	 * @return
	 */
	@Override
	public int deleteById(int visitorId) {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql语句字符串		visitorRegister		visitorId
			String sql="delete from visitorRegister where visitorId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, visitorId);
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
	 * 修改数据
	 * @param visitorRegister
	 * @return
	 */
	@Override
	public int update(VisitorRegister visitorRegister) {
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
			String sql="update visitorRegister set visitorName=?,"
					+ " visitorGender=?,visitorIdNum=?,visitorTelphone=?,vistiAddress=?,"
					+ " vistiReason=?,cometime=?,leaveTime=?,empNo=? where visitorId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值			
			
			pstmt.setString(1, visitorRegister.getVisitorName());
			pstmt.setString(2, visitorRegister.getVisitorGender());
			pstmt.setString(3, visitorRegister.getVisitorIdNum());
			pstmt.setString(4, visitorRegister.getVisitorTelphone());
			pstmt.setString(5, visitorRegister.getVistiAddress());
			pstmt.setString(6, visitorRegister.getVistiReason());
			pstmt.setString(7, visitorRegister.getCometime());
			pstmt.setString(8, visitorRegister.getLeaveTime());
			pstmt.setString(9, visitorRegister.getEmpNo());
			pstmt.setInt(10, visitorRegister.getVisitorId());
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
	 * 根据编号查找单个信息
	 * @param visitorId
	 * @return
	 */
	@Override
	public VisitorRegister findById(int visitorId) {
		//声明客户对象
		VisitorRegister visitorRegister=null;
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
			
			String sql="select visitorId,visitorName,visitorGender,"
					+ "visitorIdNum,visitorTelphone,vistiAddress,vistiReason,"
					+ "cometime,leaveTime,empNo from visitorRegister where visitorId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, visitorId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化客户对象
				visitorRegister=new VisitorRegister();
				//将结果集中数据保存到客户对象中
				visitorRegister.setVisitorId(rs.getInt("visitorId"));				
				visitorRegister.setVisitorName(rs.getString("visitorName"));
				visitorRegister.setVisitorGender(rs.getString("visitorGender"));
				visitorRegister.setVisitorGender(rs.getString("visitorGender"));
				visitorRegister.setVisitorIdNum(rs.getString("visitorIdNum"));
				visitorRegister.setVisitorTelphone(rs.getString("visitorTelphone"));
				visitorRegister.setVistiAddress(rs.getString("vistiAddress"));
				visitorRegister.setVistiReason(rs.getString("vistiReason"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				visitorRegister.setCometime(sdf.format(rs.getTimestamp("cometime")));
				visitorRegister.setLeaveTime(sdf.format(rs.getTimestamp("leaveTime")));
				visitorRegister.setEmpNo(rs.getString("empNo"));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return visitorRegister;
	}

	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有车辆信息集合
	 */
	@Override
	public List<VisitorRegister> findByPage(int rows, int page) {
		//定义客户对象集合
		List<VisitorRegister> visitorRegisterList=new ArrayList<VisitorRegister>();
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
			String sql="select * from visitorRegister limit ?,?";
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
				VisitorRegister visitorRegister=new VisitorRegister();
				//将结果集的数据保存到客户对象中
				visitorRegister.setVisitorId(rs.getInt("visitorId"));
				visitorRegister.setVisitorName(rs.getString("visitorName"));
				visitorRegister.setVisitorGender(rs.getString("visitorGender"));
				visitorRegister.setVisitorGender(rs.getString("visitorGender"));
				visitorRegister.setVisitorIdNum(rs.getString("visitorIdNum"));
				visitorRegister.setVisitorTelphone(rs.getString("visitorTelphone"));
				visitorRegister.setVistiAddress(rs.getString("vistiAddress"));
				visitorRegister.setVistiReason(rs.getString("vistiReason"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				visitorRegister.setCometime(sdf.format(rs.getTimestamp("cometime")));
				visitorRegister.setLeaveTime(sdf.format(rs.getTimestamp("leaveTime")));
				visitorRegister.setEmpNo(rs.getString("empNo"));
				//将客户对象添加到客户集合中
				visitorRegisterList.add(visitorRegister);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return visitorRegisterList;
	}
	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	@Override
	public List<VisitorRegister> findByPage(int rows, int page, String condition) {
		//定义客户集合对象
		List<VisitorRegister> visitorRegisterList=new ArrayList<VisitorRegister>();
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
			String sql="select * from visitorRegister "+condition+" limit ?,?";
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
				VisitorRegister visitorRegister=new VisitorRegister();
				//将结果集中的数据保存到客户对象中
				visitorRegister.setVisitorId(rs.getInt("visitorId"));
				visitorRegister.setVisitorName(rs.getString("visitorName"));
				visitorRegister.setVisitorGender(rs.getString("visitorGender"));
				
				visitorRegister.setVisitorIdNum(rs.getString("visitorIdNum"));
				visitorRegister.setVisitorTelphone(rs.getString("visitorTelphone"));
				visitorRegister.setVistiAddress(rs.getString("vistiAddress"));
				visitorRegister.setVistiReason(rs.getString("vistiReason"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				visitorRegister.setCometime(sdf.format(rs.getTimestamp("cometime")));
				visitorRegister.setLeaveTime(sdf.format(rs.getTimestamp("leaveTime")));
				visitorRegister.setEmpNo(rs.getString("empNo"));
				//将对象添加到客户集合中
				visitorRegisterList.add(visitorRegister);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return visitorRegisterList;
	}
	/**
	 * 统计记录数
	 * @return 表中所有记录行数
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
			String sql="select count(*) from visitorRegister";
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
	 * 统计符合条件的记录数
	 * @param condition 条件
	 * @return
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
			String sql="select count(*) from visitorRegister "+condition;
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
