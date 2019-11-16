package com.yidu.focus.hxl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hxl.dao.EqMaintainDao;
import com.yidu.focus.hxl.domain.EqMaintain;
import com.yidu.focus.ll.dao.EmpDao;
import com.yidu.focus.ll.dao.impl.EmpDaoImpl;
import com.yidu.focus.ll.domain.Emp;

/**
 * 
 * 功能：设备巡检表 
 * 作者：何希龙
 * 日期：2019年10月31日上午9:03:35
 * 版本：1.0
 */
public class EqMaintainDaoImpl implements EqMaintainDao {
	 /**
     * 添加信息
     * @param eqMaintain
     * @return
     */
	@Override
	public int add(EqMaintain eqMaintain) {
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
			String sql="INSERT INTO eqMaintain(maintainId,eqId,maintainStatues,maintainDate,consumable,consumNum,checkCost,empNo) VALUES(?,?,?,?,?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, eqMaintain.getMaintainId());
			pstmt.setString(2, eqMaintain.getEqId());
			pstmt.setString(3, eqMaintain.getMaintainStatues());		
			pstmt.setString(4, eqMaintain.getMaintainDate());
			pstmt.setString(5, eqMaintain.getConsumable());
			pstmt.setInt(6, eqMaintain.getConsumNum());
			pstmt.setDouble(7, eqMaintain.getCheckCost());
			pstmt.setString(8, eqMaintain.getEmpNo());
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
	 * @param maintainId
	 * @return
	 */
	@Override
	public int deleteById(String maintainId) {
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
			String sql="delete from eqMaintain where maintainId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, maintainId);
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
	 * @param eqMaintain
	 * @return
	 */
	@Override
	public int update(EqMaintain eqMaintain) {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql语句字符串		eqId,maintainStatues,maintainDate,consumable,checkCost,empNo   eqMaintain
			String sql="update eqMaintain set eqId=?,maintainStatues=?,maintainDate=?,consumable=?,consumNum=?,checkCost=?,empNo=? where maintainId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值			
			pstmt.setString(1, eqMaintain.getEqId());
			pstmt.setString(2, eqMaintain.getMaintainStatues());
			
			pstmt.setString(3, eqMaintain.getMaintainDate());
			pstmt.setString(4, eqMaintain.getConsumable());
			pstmt.setInt(5, eqMaintain.getConsumNum());
			pstmt.setDouble(6, eqMaintain.getCheckCost());
			pstmt.setString(7, eqMaintain.getEmpNo());
			pstmt.setString(8, eqMaintain.getMaintainId());
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
	 * @param maintainId
	 * @return
	 */
	@Override
	public EqMaintain findById(String maintainId) {
		//声明客户对象
		EqMaintain eqMaintain=null;
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
			String sql="select maintainId,eqId,maintainStatues,maintainDate,consumable,consumNum,checkCost,empNo from eqMaintain where maintainId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, maintainId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化客户对象
				eqMaintain=new EqMaintain();
				//将结果集中数据保存到客户对象中
				eqMaintain.setMaintainId(rs.getString("maintainId"));
				eqMaintain.setEqId(rs.getString("eqId"));
				eqMaintain.setMaintainStatues(rs.getString("maintainStatues"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				eqMaintain.setMaintainDate(sdf.format(rs.getDate("maintainDate")));
				eqMaintain.setConsumable(rs.getString("consumable"));
				eqMaintain.setConsumNum(rs.getInt("consumNum"));
				eqMaintain.setCheckCost(rs.getDouble("checkCost"));
				eqMaintain.setEmpNo(rs.getString("empNo"));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return eqMaintain;
	}
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有车辆信息集合
	 */
	@Override
	public List<EqMaintain> findByPage(int rows, int page) {
		//定义客户对象集合
		List<EqMaintain> eqMaintainList=new ArrayList<EqMaintain>();
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
			String sql="select * from eqMaintain limit ?,?";
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
				EqMaintain eqMaintain=new EqMaintain();
				//将结果集中数据保存到客户对象中
				eqMaintain.setMaintainId(rs.getString("maintainId"));
				eqMaintain.setEqId(rs.getString("eqId"));
				eqMaintain.setMaintainStatues(rs.getString("maintainStatues"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				eqMaintain.setMaintainDate(sdf.format(rs.getDate("maintainDate")));
				eqMaintain.setConsumable(rs.getString("consumable"));
				eqMaintain.setConsumNum(rs.getInt("consumNum"));
				eqMaintain.setCheckCost(rs.getDouble("checkCost"));
				eqMaintain.setEmpNo(rs.getString("empNo"));
				//将客户对象添加到客户集合中
				eqMaintainList.add(eqMaintain);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return eqMaintainList;
	}
	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	@Override
	public List<EqMaintain> findByPage(int rows, int page, String condition) {
		//定义客户集合对象
		List<EqMaintain> eqMaintainList=new ArrayList<EqMaintain>();
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		String empNo=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql字符串
			String sql="select * from eqMaintain "+condition+" limit ?,?";
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
				EqMaintain eqMaintain=new EqMaintain();
				//将结果集中的数据保存到客户对象中
				eqMaintain.setMaintainId(rs.getString("maintainId"));
				eqMaintain.setEqId(rs.getString("eqId"));
				eqMaintain.setMaintainStatues(rs.getString("maintainStatues"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				eqMaintain.setMaintainDate(sdf.format(rs.getDate("maintainDate")));
				eqMaintain.setConsumable(rs.getString("consumable"));
				eqMaintain.setConsumNum(rs.getInt("consumNum"));
				eqMaintain.setCheckCost(rs.getDouble("checkCost"));
				eqMaintain.setEmpNo(rs.getString("empNo"));
				empNo= eqMaintain.getEmpNo();
				EmpDao empDao = new EmpDaoImpl();
				Emp emp = empDao.findById(empNo);
				eqMaintain.setEmpName(emp.getEmpName());
				
				//将对象添加到客户集合中
				eqMaintainList.add(eqMaintain);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return eqMaintainList;
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
			String sql="select count(*) from eqMaintain";
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
			String sql="select count(*) from eqMaintain "+condition;
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
