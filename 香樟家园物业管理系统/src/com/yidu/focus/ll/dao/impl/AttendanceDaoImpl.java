package com.yidu.focus.ll.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.ll.dao.AttendanceDao;
import com.yidu.focus.ll.domain.Attendance;
/**
 * 
 * 功能：员工考勤表 
 * 作者：刘李
 * 日期：2019年10月31日上午9:12:45
 * 版本：1.0
 */
public class AttendanceDaoImpl implements AttendanceDao{
	/**
	 * 添加
	 */
	@Override
	public int add(Attendance attendance) {	
		//定义添加行数
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//编写SQL语句
			String sql="insert into Attendance(empNo,"
					+ "onWork,doWork) values(?,?,?)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1, attendance.getEmpNo());
			//将字符串转换成日期数据
			pstmt.setDate(2, java.sql.Date.valueOf(attendance.getOnWork()));
			pstmt.setDate(3, java.sql.Date.valueOf(attendance.getDoWork()));
			//执行SQL操作
			//执行预编译语句对象，得到影响行数
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源
			JdbcUtils.close(null, pstmt, conn);
		}
		//返回影响的行数
		return rows;
	}

	/**
	 * 删除
	 */
	@Override
	public int delete(int attendId ) {
		//定义删除行数
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//编写SQL语句
			String sql="delete from Attendance where attendId=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setInt(1, attendId);
			//执行SQL操作
			//将执行后，在数据库表中影响的行数作为方法值返回
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源
			JdbcUtils.close(null, pstmt, conn);
		}
		//返回影响的行数
		return rows;
	}

	/**
	 * 修改
	 */
	@Override
	public int update(Attendance attendance) {
		//定义删除行数
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//编写SQL语句
			String sql="update Attendance set empNo=?,onWork=?,"
					+ "doWork=? where attendId=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1, attendance.getEmpNo());
			//将字符串转换成日期数据
			pstmt.setDate(2, java.sql.Date.valueOf(attendance.getOnWork()));
			pstmt.setDate(3, java.sql.Date.valueOf(attendance.getDoWork()));
			pstmt.setInt(4, attendance.getAttendId());
			//执行SQL操作
			//将执行后，在数据库表中影响的行数作为方法值返回
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源
			JdbcUtils.close(null, pstmt, conn);
		}
		//返回影响的行数
		return rows;
	}
	/**
	 * 根据考勤的编号，查询相应的上下班信息
	 */    
	@Override
	public Attendance findById(int attendId) {
		//定义一个对象 
		Attendance attendance=null;
		//声明数据库连接对象
		Connection conn=null;
		//声明语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象 
		ResultSet rs=null;

		try {
			//1:实例化连接对象
			conn=JdbcUtils.getConnection();
			//2:A:定义要执行的SQL语句字符串
			String sql="select attendId,empNo,onWork,doWork where attendId=?";
			//2:B:根据SQL命令和数据库对象实例化语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setInt(1, attendId);
			//3:执行语句对象，得到结果集对象
			rs=pstmt.executeQuery();
			//4:对结果集进行处理，将结果集中记录行转换成考勤对象
			if(rs.next()){
				//4A:实例化考勤对象
				attendance=new Attendance();
				//4B:将rs中一行数据保存到Attendance对象的属性中
				attendance.setAttendId(rs.getInt("attendId"));
				attendance.setEmpNo(rs.getString("empNo"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				attendance.setOnWork(sdf.format(rs.getDate("onWork")));
				SimpleDateFormat sdft=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				attendance.setDoWork(sdft.format(rs.getDate("doWork")));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回考勤对象
		return attendance;
	}

	/**
	 * 查询Attendance表中所有考勤信息，并封装成考勤信息集合返回
	 * @return 考勤信息集合
	 */
	@Override
	public List<Attendance> findAll() {
		//声明数据库连接对象
		Connection conn=null;
		//声明语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象 
		ResultSet rs=null;
		//定义一个集合数组对象 
		List<Attendance> attendanceList=new ArrayList<Attendance>();

		try {
			//1:实例化连接对象
			conn=JdbcUtils.getConnection();
			//2:A:定义要执行的SQL语句字符串
			String sql="select  attendId,empNo,onWork,doWork from attendance";
			//2:B:根据SQL命令和数据库对象实例化语句对象
			pstmt=conn.prepareStatement(sql);
			//3:执行语句对象，得到结果集对象
			rs=pstmt.executeQuery();
			//4:对结果集进行遍历处理，将结果集中记录行转换成书籍对象，并添加到集合中
			while(rs.next()){
				//4A:建立一个书籍对象
				Attendance attendance=new Attendance();
				//4B:将rs中一行数据保存到Attendance对象的属性中
				attendance.setAttendId(rs.getInt("attendId"));
				attendance.setEmpNo(rs.getString("empNo"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				attendance.setOnWork(sdf.format(rs.getDate("onWork")));
				SimpleDateFormat sdft=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				attendance.setDoWork(sdft.format(rs.getDate("doWork")));
				//4C:将books对象添加到集合中
				attendanceList.add(attendance);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回考勤信息集合
		return attendanceList;
	}
	
	@Override
	public List<Attendance> findByPage(int rows, int page) {
		List<Attendance> attendanceList=new ArrayList<Attendance>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnection();
			String sql="select * from attendance limit ?,?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			rs=pstmt.executeQuery();
			//4:对结果集进行处理，将结果集中记录行转换成考勤对象
			while(rs.next()){
				//4A:实例化考勤对象
				Attendance attendance=new Attendance();
				attendance.setAttendId(rs.getInt("attendId"));
				attendance.setEmpNo(rs.getString("empNo"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				attendance.setOnWork(sdf.format(rs.getDate("onWork")));
				SimpleDateFormat sdft=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				attendance.setDoWork(sdft.format(rs.getDate("doWork")));
				attendanceList.add(attendance);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
		return attendanceList;
	}

	/**
	 * 按特定条件分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @param condition 查询条件
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<Attendance> findByPage(int rows, int page, String condition) {
		//定义考勤集合对象
		List<Attendance> attendanceList=new ArrayList<Attendance>();
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
			String sql="select * from Attendance "+condition+" limit ?,? ";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中的参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义文件对象
				Attendance attendance=new Attendance();
				attendance.setAttendId(rs.getInt("attendId"));
				attendance.setEmpNo(rs.getString("empNo"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				attendance.setOnWork(sdf.format(rs.getDate("onWork")));
				SimpleDateFormat sdft=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				attendance.setDoWork(sdft.format(rs.getDate("doWork")));
				//将客户对象添加到考勤集合中
				attendanceList.add(attendance);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回考勤集合
		return attendanceList;
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
			String sql="select count(*) from Attendance";
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
			String sql="select count(*) from Attendance "+condition;
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
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回行数
		return rows;
	}

}
