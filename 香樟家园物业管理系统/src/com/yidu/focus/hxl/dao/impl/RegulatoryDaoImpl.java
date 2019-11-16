package com.yidu.focus.hxl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hxl.dao.RegulatoryDao;
import com.yidu.focus.hxl.domain.Regulatory;
/**
 * 
 * 功能：公告信息表 
 * 作者：何稀龙
 * 日期：2019年10月31日上午9:06:02
 * 版本：1.0
 */
public class RegulatoryDaoImpl implements RegulatoryDao {
	 /**
     * 添加信息
     * @param regulatory
     * @return
     */
	@Override
	public int add(Regulatory regulatory) {
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
			String sql="INSERT INTO regulatory(fileId,fileTitle,fileTheme,fileState,executionTime) VALUES(?,?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, regulatory.getFileId());
			pstmt.setString(2, regulatory.getFileTitle());
			pstmt.setString(3, regulatory.getFileTheme());
			pstmt.setString(4, regulatory.getFileState());
			pstmt.setString(5, regulatory.getExecutionTime());
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
	 * @param fileId
	 * @return
	 */
	@Override
	public int deleteById(String fileId) {
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
			String sql="delete from regulatory where fileId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, fileId);
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
	 * @param regulatory
	 * @return
	 */
	@Override
	public int update(Regulatory regulatory) {
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
			String sql="update regulatory set fileId=?,fileTitle=?,"
					+ "fileTheme=?,fileState=?,executionTime=? where fileId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值			
			pstmt.setString(1, regulatory.getFileId());
			pstmt.setString(2, regulatory.getFileTitle());
			pstmt.setString(3, regulatory.getFileTheme());
			pstmt.setString(4, regulatory.getFileState());
			//将字符串转换成数据库日期数据
			pstmt.setString(5, regulatory.getExecutionTime());
			pstmt.setString(6, regulatory.getFileId());
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
	 * 根据考勤查找单个信息
	 * @param fileId
	 * @return
	 */
	@Override
	public Regulatory findById(String fileId) {
		//声明客户对象
		Regulatory regulatory=null;
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
			String sql="select fileId,fileTitle,fileTheme,fileState,executionTime from regulatory where fileId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, fileId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化客户对象
				regulatory=new Regulatory();
				//将结果集中数据保存到客户对象中
				regulatory.setFileId(rs.getString("fileId"));
				regulatory.setFileTitle(rs.getString("fileTitle"));
				regulatory.setFileTheme(rs.getString("fileTheme"));
				regulatory.setFileState(rs.getString("fileState"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				regulatory.setExecutionTime(sdf.format(rs.getDate("executionTime")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return regulatory;
	}
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有车辆信息集合
	 */
	@Override
	public List<Regulatory> findByPage(int rows, int page) {
		//定义客户对象集合
		List<Regulatory> regulatoryList=new ArrayList<Regulatory>();
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
			String sql="select * from regulatory limit ?,?";
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
				Regulatory regulatory=new Regulatory();
				//将结果集的数据保存到客户对象中
				regulatory.setFileId(rs.getString("fileId"));
				regulatory.setFileTitle(rs.getString("fileTitle"));
				regulatory.setFileTheme(rs.getString("fileTheme"));
				regulatory.setFileState(rs.getString("fileState"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				regulatory.setExecutionTime(sdf.format(rs.getDate("executionTime")));
				//将客户对象添加到客户集合中
				regulatoryList.add(regulatory);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return regulatoryList;
	}
	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	@Override
	public List<Regulatory> findByPage(int rows, int page, String condition) {
		//定义客户集合对象
		List<Regulatory> regulatoryList=new ArrayList<Regulatory>();
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
			String sql="select * from regulatory "+condition+" limit ?,?";
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
				Regulatory regulatory=new Regulatory();
				//将结果集中的数据保存到客户对象中
				regulatory.setFileId(rs.getString("fileId"));
				regulatory.setFileTitle(rs.getString("fileTitle"));
				regulatory.setFileTheme(rs.getString("fileTheme"));
				regulatory.setFileState(rs.getString("fileState"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				regulatory.setExecutionTime(sdf.format(rs.getDate("executionTime")));
				//将对象添加到客户集合中
				regulatoryList.add(regulatory);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return regulatoryList;
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
			String sql="select count(*) from regulatory";
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
			String sql="select count(*) from regulatory "+condition;
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
