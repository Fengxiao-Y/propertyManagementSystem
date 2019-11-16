package com.yidu.focus.yfx.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.yfx.dao.RepairsDao;
import com.yidu.focus.yfx.domain.Repairs;
/**
 * 
 * 功能：报修记录操作层 
 * 作者：严奉孝
 * 日期：2019年10月31日上午8:56:05
 * 版本：1.0
 */
public class RepairsDaoImpl implements RepairsDao {
	
	/**
	 * 添加数据：将报修对象中的数据保存到报修数据库表中
	 * @param repairs 报修对象
	 * @return 影响行数（1：操作成功；0：操作失败）
	 */
	@Override
	public int add(Repairs repairs) {
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
			String sql="insert into repairs(ownerName,repText, "
					+ " repTime,empName,repResult,repEndTime) "
					+ " values(?,?,?,?,?,?)";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符创中参数赋值
			//业主姓名
			pstmt.setString(1, repairs.getOwnerName());
			//报修内容
			pstmt.setString(2, repairs.getRepText());			
			//报修时间
			pstmt.setString(3, repairs.getRepTime());
			//受理人
			pstmt.setString(4, repairs.getEmpName());
			//处理结果
			pstmt.setString(5, repairs.getRepResult());			
			//回访时间
			pstmt.setString(6, repairs.getrepEndTime());
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
	 * 根据报修编号删除数据库表中的数据
	 * @param repId 报修编号
	 * @return 影响行数（1：操作成功；0：操作失败）
	 */
	@Override
	public int deleteById(int repId) {
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
			String sql="delete from repairs where repId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符创中参数赋值
			pstmt.setInt(1, repId);
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
	 * 修改数据：将报修对象中的数据更新到数据库表中
	 * @param repairs 报修对象
	 * @return 影响行数（1：操作成功；0：操作失败）
	 */
	@Override
	public int update(Repairs repairs) {
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
			String sql="update repairs set ownerName=?,repText=?, "
					+ " repTime=?,empName=?,repResult=?,repEndTime=? "
					+ " where repId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符创中参数赋值
			//业主姓名
			pstmt.setString(1, repairs.getOwnerName());
			//报修内容
			pstmt.setString(2, repairs.getRepText());
			//报修时间
			pstmt.setString(3, repairs.getRepTime());
			//受理人
			pstmt.setString(4, repairs.getEmpName());
			//处理结果
			pstmt.setString(5, repairs.getRepResult());
			//回访时间
			pstmt.setString(6, repairs.getrepEndTime());
			//报修编号
			pstmt.setInt(7, repairs.getRepId());
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
	 * 根据报修编号查找单次报修信息
	 * @param repId 报修编号
	 * @return 报修对象
	 */
	@Override
	public Repairs findById(int repId) {
		//声明报修对象
		Repairs repairs=null;
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
			String sql="select * from repairs where repId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符创中参数赋值
			pstmt.setInt(1, repId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用判断处理结果集
			if(rs.next()){
				//实例化报修对象
				repairs=new Repairs();
				//将结果集中数据保存到报修对象中
				repairs.setRepId(rs.getInt("repId"));
				repairs.setOwnerName(rs.getString("ownerName"));
				repairs.setRepText(rs.getString("repText"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				repairs.setRepTime(sdf.format(rs.getTimestamp("repTime")));
				repairs.setEmpName(rs.getString("empName"));
				repairs.setRepResult(rs.getString("repResult"));
				repairs.setRepEndTime(sdf.format(rs.getTimestamp("repEndTime")));
			}			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回报修对象
		return repairs;
	}

	/**
	 * 查找所有报修信息
	 * @return 报修集合对象
	 */
	@Override
	public List<Repairs> findAll() {
		return null;
	}

	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有报修信息集合
	 */
	@Override
	public List<Repairs> findByPage(int rows, int page) {
		//定义报修集合对象
		List<Repairs> repairsList=new ArrayList<Repairs>();
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
			String sql="select * from repairs limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义报修对象
				Repairs repairs=new Repairs();
				//将结果集中数据保存到报修对象中
				repairs.setRepId(rs.getInt("repId"));
				repairs.setOwnerName(rs.getString("ownerName"));
				repairs.setRepText(rs.getString("repText"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				repairs.setRepTime(sdf.format(rs.getTimestamp("repTime")));
				repairs.setEmpName(rs.getString("empName"));
				repairs.setRepResult(rs.getString("repResult"));
				repairs.setRepEndTime(sdf.format(rs.getTimestamp("repEndTime")));
				//将报修对象添加到报修集合对象中
				repairsList.add(repairs);
			}			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回报修结合对象
		return repairsList;
	}

	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 查找条件
	 * @return 指定页中所有报修信息集合
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
			String sql="select count(*) from repairs";
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
	public List<Repairs> findByPage(int rows, int page, String condition) {
		//定义报修集合对象
		List<Repairs> repairsList=new ArrayList<Repairs>();
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
			String sql="select * from repairs "+condition+" limit ?,? ";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中的参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义报修对象
				Repairs repairs=new Repairs();				
				//将结果集中数据保存到报修对象中
				repairs.setRepId(rs.getInt("repId"));
				repairs.setOwnerName(rs.getString("ownerName"));
				repairs.setRepText(rs.getString("repText"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				repairs.setRepTime(sdf.format(rs.getTimestamp("repTime")));
				repairs.setEmpName(rs.getString("empName"));
				repairs.setRepResult(rs.getString("repResult"));
				repairs.setRepEndTime(sdf.format(rs.getTimestamp("repEndTime")));
				//将报修对象添加到报修集合对象中
				repairsList.add(repairs);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回报修集合对象
		return repairsList;
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
			String sql="select count(*) from repairs "+condition;
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
	 * 根据报修编号查找单次报修信息
	 * @param repId 报修编号
	 * @return 报修对象
	 */
	@Override
	public List<Repairs> findByName(String ownerName) {
		//声明报修集合对象
		List<Repairs> repairsList=null;
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
			String sql="select * from repairs where ownerName=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符创中参数赋值
			pstmt.setString(1, ownerName);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用判断处理结果集
			if(rs.next()){
				//定义报修对象
				Repairs repairs=new Repairs();
				//将结果集中数据保存到报修对象中
				repairs.setRepId(rs.getInt("repId"));
				repairs.setOwnerName(rs.getString("ownerName"));
				repairs.setRepText(rs.getString("repText"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				repairs.setRepTime(sdf.format(rs.getTimestamp("repTime")));
				repairs.setEmpName(rs.getString("empName"));
				repairs.setRepResult(rs.getString("repResult"));
				repairs.setRepEndTime(sdf.format(rs.getTimestamp("repEndTime")));
				//实例化报修集合
				repairsList = new ArrayList<>();
				//将报修对象添加到集合中
				repairsList.add(repairs);
			}			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回报修对象
		return repairsList;
	}

}
