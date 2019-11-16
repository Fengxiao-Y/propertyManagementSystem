
package com.yidu.focus.wzh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.wzh.dao.ProcurementDao;
import com.yidu.focus.wzh.domain.Procurement;

/**
 * 功能：针对数据表的功能操作
 * 作者：伍志华
 * 日期：2019年10月12日下午2:26:57
 * 版本：1.0
 */
public class ProcurementDaoImpl implements ProcurementDao{

	/**
	 * 将实体类对象添加到数据库表中
	 * @param procurement 采购实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int add(Procurement procurement) {
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
			String sql="INSERT INTO procurement VALUES(?,?,?,?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, procurement.getProId());
			pstmt.setString(2, procurement.getProName());
			pstmt.setString(3, procurement.getGoodsName());
			//将字符串转换成数据库日期数据
			pstmt.setString(4, procurement.getProTime());
			pstmt.setInt(5, procurement.getProNum());


			pstmt.setDouble(6, procurement.getProPrice());
			pstmt.setDouble(7, procurement.getProTolal());

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
	 * 按照采购编号（主键）删除数据
	 * @param uId 用户编号
	 * @return rows 影响的行数（1成功，0失败）
	 */
	@Override
	public int delete(String proId) {
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
			String sql="delete from procurement where proId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, proId);
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
	 * @param procurement 采购实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int update(Procurement procurement) {
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
			String sql="update procurement set proName=?,goodsName=?,proTime=?,proNum=?,proPrice=?,proTolal=? where proId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值	
			pstmt.setString(1, procurement.getProName());
			pstmt.setString(2, procurement.getGoodsName());
			//将字符串转换成数据库日期数据
			pstmt.setString(3, procurement.getProTime());
			pstmt.setInt(4, procurement.getProNum());
			pstmt.setDouble(5, procurement.getProPrice());
			pstmt.setDouble(6, procurement.getProTolal());
			//pstmt.setString(7, procurement.getEmpNo());
			pstmt.setString(7, procurement.getProId());
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
	 * 按照采购编号（主键）从数据库表中查找数据
	 * @param proId 采购编号
	 * @return 采购实体对象
	 */
	@Override
	public Procurement findById(String proId) {
		//声明采购对象
		Procurement procurement=null;
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
			String sql="select * from procurement where proId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, proId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化采购对象
				procurement=new Procurement();
				//将结果集中数据保存到对象中
				procurement.setProId(rs.getString("proId"));
				procurement.setProName(rs.getString("proName"));
				procurement.setGoodsName(rs.getString("goodsName"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				procurement.setProTime(sdf.format(rs.getDate("proTime")));
				procurement.setProNum(rs.getInt("proNum"));
				procurement.setProTolal(rs.getDouble("proTolal"));
				procurement.setProTolal(rs.getDouble("proTolal"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return procurement;
	}

	/**
	 * 分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<Procurement> findByPage(int rows, int page) {
		//定义采购对象集合
		List<Procurement> procurementList=new ArrayList<Procurement>();
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
			String sql="select * from  procurement limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义采购对象
				Procurement  procurement=new Procurement();
				//将结果集的数据保存到采购对象中
				procurement=new Procurement();
				//将结果集中数据保存到对象中
				procurement.setProId(rs.getString("proId"));
				procurement.setProName(rs.getString("proName"));
				procurement.setGoodsName(rs.getString("goodsName"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				procurement.setProTime(sdf.format(rs.getDate("proTime")));
				procurement.setProNum(rs.getInt("proNum"));
				procurement.setProPrice(rs.getDouble("proPrice"));
				procurement.setProTolal(rs.getDouble("proTolal"));
				//将采购对象添加到采购集合中
				procurementList.add(procurement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回采购集合
		return procurementList;
	}

	/**
	 * 按特定条件分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @param condition 查询条件
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<Procurement> findByPage(int rows, int page, String condition) {
		//定义采购对象集合
		List<Procurement> procurementList=new ArrayList<Procurement>();
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
			String sql="select * from  procurement "+condition+" limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义采购对象
				Procurement  procurement=new Procurement();
				//将结果集的数据保存到采购对象中
				procurement=new Procurement();
				//将结果集中数据保存到对象中
				procurement.setProId(rs.getString("proId"));
				procurement.setProName(rs.getString("proName"));
				procurement.setGoodsName(rs.getString("goodsName"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				procurement.setProTime(sdf.format(rs.getDate("proTime")));
				procurement.setProNum(rs.getInt("proNum"));
				procurement.setProPrice(rs.getDouble("proPrice"));
				procurement.setProTolal(rs.getDouble("proTolal"));
				//将采购对象添加到采购集合中
				procurementList.add(procurement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回采购集合
		return procurementList;
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
			String sql="select count(*) from procurement";
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
			String sql="select count(*) from procurement "+condition;
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