package com.yidu.focus.yfx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.yfx.dao.ComplainDao;
import com.yidu.focus.yfx.domain.Complain;
/**
 * 
 * 功能：投诉操作层
 * 作者：严奉孝
 * 日期：2019年10月31日上午8:53:13
 * 版本：1.0
 */
public class ComplainDaoImpl implements ComplainDao {
	
	/**
	 * 添加数据：将投诉对象中的数据保存到投诉数据库表中
	 * @param complain 投诉对象
	 * @return 影响行数（1：操作成功；0：操作失败）
	 */
	@Override
	public int add(Complain complain) {
		//定义行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//创建数据库执行语句sql字符串
			String sql="insert into complain(ownerName,comText, "
					+ " comTime,empName,comResult,comEndTime) "
					+ " values(?,?,?,?,?,?)";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符创中参数赋值
			//业主姓名
			pstmt.setString(1, complain.getOwnerName());
			//投诉内容
			pstmt.setString(2, complain.getComText());
			//投诉时间
			pstmt.setString(3, complain.getComTime());
			//受理人
			pstmt.setString(4, complain.getEmpName());
			//处理结果
			pstmt.setString(5, complain.getComResult());
			//回访时间
			pstmt.setString(6, complain.getComEndTime());
			//执行预编译语句对象得到影响行数值
			rows=pstmt.executeUpdate();			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		//返回影响行数值
		return rows;
	}

	/**
	 * 根据投诉编号删除数据库表中的数据
	 * @param comId 投诉编号
	 * @return 影响行数（1：操作成功；0：操作失败）
	 */
	@Override
	public int deleteById(int comId) {
		//定义行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//创建数据库执行语句sql字符串
			String sql="delete from complain where comId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符创中参数赋值
			pstmt.setInt(1, comId);
			//执行预编译语句对象得到影响行数值
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
	 * 修改数据：将投诉对象中的数据更新到数据库表中
	 * @param complain 投诉对象
	 * @return 影响行数（1：操作成功；0：操作失败）
	 */
	@Override
	public int update(Complain complain) {
		//定义行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//创建数据库执行语句sql字符串
			String sql="update complain set ownerName=?,comText=?, "
					+ " comTime=?,empName=?,comResult=?,comEndTime=? "
					+ " where comId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符创中参数赋值
			//业主姓名
			pstmt.setString(1, complain.getOwnerName());
			//投诉内容
			pstmt.setString(2, complain.getComText());
			//投诉时间
			pstmt.setString(3, complain.getComTime());
			//受理人
			pstmt.setString(4, complain.getEmpName());
			//处理结果
			pstmt.setString(5, complain.getComResult());
			//回访时间
			pstmt.setString(6, complain.getComEndTime());
			//投诉编号
			pstmt.setInt(7, complain.getComId());
			//执行预编译语句对象得到影响行数值
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
	 * 根据投诉编号查找单次投诉信息
	 * @param comId 投诉编号
	 * @return 投诉对象
	 */
	@Override
	public Complain findById(int comId) {
		//声明投诉对象
		Complain complain=null;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//创建数据库执行语句sql字符串
			String sql="select * from complain where comId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符创中参数赋值
			pstmt.setInt(1, comId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用判断处理结果集
			if(rs.next()){
				//实例化投诉对象
				complain=new Complain();
				//将结果集中数据保存到投诉对象中
				complain.setComId(rs.getInt("comId"));
				complain.setOwnerName(rs.getString("ownerName"));
				complain.setComText(rs.getString("comText"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				complain.setComTime(sdf.format(rs.getTimestamp("comTime")));
				complain.setEmpName(rs.getString("empName"));
				complain.setComResult(rs.getString("comResult"));
				complain.setComEndTime(sdf.format(rs.getTimestamp("comEndTime")));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回投诉对象
		return complain;
	}

	/**
	 * 查找所有投诉信息
	 * @return 投诉集合对象
	 */
	@Override
	public List<Complain> findAll() {
		return null;
	}

	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有投诉信息集合
	 */
	@Override
	public List<Complain> findByPage(int rows, int page) {
		//定义投诉集合对象
		List<Complain> complainList=new ArrayList<Complain>();
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//创建数据库执行语句sql字符串
			String sql="select * from complain limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义投诉对象
				Complain complain=new Complain();
				//将结果集中数据保存到投诉对象中
				complain.setComId(rs.getInt("comId"));
				complain.setOwnerName(rs.getString("ownerName"));
				complain.setComText(rs.getString("comText"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				complain.setComTime(sdf.format(rs.getTimestamp("comTime")));
				complain.setEmpName(rs.getString("empName"));
				complain.setComResult(rs.getString("comResult"));
				complain.setComEndTime(sdf.format(rs.getTimestamp("comEndTime")));
				//将投诉对象添加到投诉集合对象中
				complainList.add(complain);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回投诉结合对象
		return complainList;
	}

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 查找条件
	 * @return 指定页中所有投诉信息集合
	 */
	@Override
	public int count() {
		//定义行数变量
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
			//创建数据库执行语句sql字符串
			String sql="select count(*) from complain";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//判断结果集
			if(rs.next()){
				//将结果集中的数据行数赋值给行数变量
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
	 * 统计记录数
	 * @return 表中所有记录行数
	 */
	@Override
	public List<Complain> findByPage(int rows, int page, String condition) {
		//定义投诉集合对象
		List<Complain> complainList=new ArrayList<Complain>();
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();			
			//创建数据库执行语句sql字符串
			String sql="select * from complain "+condition+" limit ?,? ";			
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中的参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义投诉对象
				Complain complain=new Complain();				
				//将结果集中数据保存到投诉对象中
				complain.setComId(rs.getInt("comId"));
				complain.setOwnerName(rs.getString("ownerName"));
				complain.setComText(rs.getString("comText"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				complain.setComTime(sdf.format(rs.getTimestamp("comTime")));
				complain.setEmpName(rs.getString("empName"));
				complain.setComResult(rs.getString("comResult"));
				complain.setComEndTime(sdf.format(rs.getTimestamp("comEndTime")));
				//将投诉对象添加到投诉集合对象中
				complainList.add(complain);
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		
		//返回投诉集合对象
		return complainList;
		
	}

	/**
	 * 统计符合条件的记录数
	 * @param condition 统计条件
	 * @return 表中符合统计条件记录行数
	 */
	@Override
	public int count(String condition) {
		//定义行数变量
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
			//创建数据库执行语句sql字符串
			String sql="select count(*) from complain "+condition;
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//判断结果集
			if(rs.next()){
				//将结果集中的数据行数赋值给行数变量
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
	 * 根据投诉编号查找单次投诉信息
	 * @param comId 投诉编号
	 * @return 投诉对象
	 */
	@Override
	public List<Complain> findByName(String ownerName) {
		//声明投诉对象
		List<Complain> complainList=null;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//创建数据库执行语句sql字符串
			String sql="select * from complain where ownerName=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符创中参数赋值
			pstmt.setString(1, ownerName);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用判断处理结果集
			while(rs.next()){
				//定义投诉对象
				Complain complain=new Complain();
				//将结果集中数据保存到投诉对象中
				complain.setComId(rs.getInt("comId"));
				complain.setOwnerName(rs.getString("ownerName"));
				complain.setComText(rs.getString("comText"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				complain.setComTime(sdf.format(rs.getTimestamp("comTime")));
				complain.setEmpName(rs.getString("empName"));
				complain.setComResult(rs.getString("comResult"));
				complain.setComEndTime(sdf.format(rs.getTimestamp("comEndTime")));
				//实例化集合对象
				complainList = new ArrayList<>();
				//将对象添加到集合中
				complainList.add(complain);
			}			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回投诉对象
		return complainList;
	}

}
