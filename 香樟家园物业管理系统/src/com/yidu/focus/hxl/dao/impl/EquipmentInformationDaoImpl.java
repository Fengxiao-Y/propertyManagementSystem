package com.yidu.focus.hxl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hxl.dao.EquipmentInformationDao;
import com.yidu.focus.hxl.domain.EquipmentInformation;
/**
 * 
 * 功能：设备基本信息表 
 * 作者：何希龙
 * 日期：2019年10月31日上午9:03:52
 * 版本：1.0
 */
public class EquipmentInformationDaoImpl implements EquipmentInformationDao {
	 /**
     * 添加信息
     * @param attendance
     * @return
     */
	@Override
	public int add(EquipmentInformation EquipmentInformation) {
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
			String sql="INSERT INTO EquipmentInformation(eqId,eqName,eqPurpose,manufacturer,"
					+ "producedDate,eqNum,usePosition,eqStatus) VALUES(?,?,?,?,?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, EquipmentInformation.getEqId());
			pstmt.setString(2, EquipmentInformation.getEqName());
			pstmt.setString(3, EquipmentInformation.getEqPurpose());
			pstmt.setString(4, EquipmentInformation.getManufacturer());
			pstmt.setString(5, EquipmentInformation.getProducedDate());
			pstmt.setInt(6, EquipmentInformation.getEqNum());
			pstmt.setString(7, EquipmentInformation.getUsePosition());
			pstmt.setString(8, EquipmentInformation.getEqStatus());
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
	 * @param attendId
	 * @return
	 */
	@Override
	public int deleteById(String eqId) {
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
			String sql="delete from EquipmentInformation where eqId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, eqId);
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
	 * @param attendId
	 * @return
	 */
	@Override
	public int update(EquipmentInformation EquipmentInformation) {
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
			String sql="update EquipmentInformation set eqName=?,eqPurpose=?,manufacturer=?,"
					+ "producedDate=?,eqNum=?,usePosition=?,eqStatus=? where eqId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值			
			
			pstmt.setString(1, EquipmentInformation.getEqName());
			pstmt.setString(2, EquipmentInformation.getEqPurpose());
			pstmt.setString(3, EquipmentInformation.getManufacturer());
			pstmt.setString(4, EquipmentInformation.getProducedDate());
			pstmt.setInt(5, EquipmentInformation.getEqNum());
			pstmt.setString(6, EquipmentInformation.getUsePosition());
			pstmt.setString(7, EquipmentInformation.getEqStatus());
			pstmt.setString(8, EquipmentInformation.getEqId());
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
	 * 根据考勤编号查找单个员工信息
	 * @param attendId
	 * @return
	 */
	@Override
	public EquipmentInformation findById(String eqId) {
		//声明客户对象
		EquipmentInformation EquipmentInformation=null;
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
			String sql="select eqId,eqName,eqPurpose,manufacturer,producedDate,"
					+ "eqNum,usePosition,eqStatus from EquipmentInformation where eqId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, eqId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化客户对象
				EquipmentInformation=new EquipmentInformation();
				//将结果集中数据保存到客户对象中
				EquipmentInformation.setEqId(rs.getString("eqId"));
				EquipmentInformation.setEqName(rs.getString("eqName"));
				EquipmentInformation.setEqPurpose(rs.getString("eqPurpose"));
				EquipmentInformation.setManufacturer(rs.getString("manufacturer"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				EquipmentInformation.setProducedDate(sdf.format(rs.getDate("producedDate")));
				EquipmentInformation.setEqNum(rs.getInt("eqNum"));
				EquipmentInformation.setUsePosition(rs.getString("usePosition"));
				EquipmentInformation.setEqStatus(rs.getString("eqStatus"));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return EquipmentInformation;
	}
	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	@Override
	public List<EquipmentInformation> findByPage(int rows, int page) {
		//定义客户对象集合
		List<EquipmentInformation> equipmentList=new ArrayList<EquipmentInformation>();
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
			String sql="select * from EquipmentInformation limit ?,?";
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
				EquipmentInformation EquipmentInformation=new EquipmentInformation();
				//将结果集的数据保存到客户对象中
				EquipmentInformation.setEqId(rs.getString("eqId"));
				EquipmentInformation.setEqName(rs.getString("eqName"));
				EquipmentInformation.setEqPurpose(rs.getString("eqPurpose"));
				EquipmentInformation.setManufacturer(rs.getString("manufacturer"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				EquipmentInformation.setProducedDate(sdf.format(rs.getDate("producedDate")));
				EquipmentInformation.setEqNum(rs.getInt("eqNum"));
				EquipmentInformation.setUsePosition(rs.getString("usePosition"));
				EquipmentInformation.setEqStatus(rs.getString("eqStatus"));
				//将客户对象添加到客户集合中
				equipmentList.add(EquipmentInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return equipmentList;
	}
	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	@Override
	public List<EquipmentInformation> findByPage(int rows, int page, String condition) {
		//定义客户集合对象
		List<EquipmentInformation> equipmentList=new ArrayList<EquipmentInformation>();
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
			String sql="select * from EquipmentInformation "+condition+" limit ?,?";
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
				EquipmentInformation EquipmentInformation=new EquipmentInformation();
				//将结果集中的数据保存到客户对象中
				EquipmentInformation.setEqId(rs.getString("eqId"));
				EquipmentInformation.setEqName(rs.getString("eqName"));
				EquipmentInformation.setEqPurpose(rs.getString("eqPurpose"));
				EquipmentInformation.setManufacturer(rs.getString("manufacturer"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				EquipmentInformation.setProducedDate(sdf.format(rs.getDate("producedDate")));
				EquipmentInformation.setEqNum(rs.getInt("eqNum"));
				EquipmentInformation.setUsePosition(rs.getString("usePosition"));
				EquipmentInformation.setEqStatus(rs.getString("eqStatus"));
				//将对象添加到客户集合中
				equipmentList.add(EquipmentInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return equipmentList;
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
			String sql="select count(*) from EquipmentInformation";
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
			String sql="select count(*) from EquipmentInformation "+condition;
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
