
package com.yidu.focus.wzh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.wzh.dao.StockDao;
import com.yidu.focus.wzh.domain.Stock;

/**
 * 功能：针对数据表的功能操作
 * 作者：伍志华
 * 日期：2019年10月12日下午1:55:13
 * 版本：1.0
 */
public class StockDaoImpl implements StockDao{

	/**
	 * 将实体类对象添加到数据库表中
	 * @param Stock 库存实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int add(Stock stock) {
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
			String sql="INSERT INTO stock(goodsName,stockNum) VALUES(?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, stock.getGoodsName());
			pstmt.setInt(2, stock.getStockNum());
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
	 * @param stock 库存实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int update(Stock stock) {
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
			String sql="update stock set stockNum=? where stockId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值			
			pstmt.setInt(1, stock.getStockNum());
			pstmt.setInt(2, stock.getStockId());
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
	 * 按照库存编号（主键）从数据库表中查找数据
	 * @param stockId 库存编号
	 * @return 库存实体对象
	 */
	@Override
	public Stock findById(int stockId) {
		//声明库存对象
		Stock stock=null;
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
			String sql="select * from Stock where stockId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, stockId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化库存对象
				stock=new Stock();
				//将结果集中数据保存到库存对象中
				stock.setStockId(rs.getInt("stockId"));
				stock.setGoodsName(rs.getString("goodsName"));
				stock.setStockNum(rs.getInt("stockNum"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return stock;
	}
		
	/*
	 * 根据商品名字查找指定信息
	 * @param stockId 库存编号
	 * @return 库存实体对象
	 */
	@Override
	public Stock findByName(String goodsName) {
		//声明库存对象
		Stock stock=null;
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
			String sql="select * from Stock where goodsName=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, goodsName);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化库存对象
				stock=new Stock();
				//将结果集中数据保存到库存对象中
				stock.setStockId(rs.getInt("stockId"));
				stock.setGoodsName(rs.getString("goodsName"));
				stock.setStockNum(rs.getInt("stockNum"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return stock;
	}
	
	/**
	 * 查找所有
	 * @param rows 行数
	 * @param page 页数
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<Stock> findAll() {
		//定义库存对象集合
		List<Stock> stockList=new ArrayList<Stock>();
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
			String sql="select * from stock";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义库存对象
				Stock stock=new Stock();
				//将结果集的数据保存到库存对象中
				stock.setStockId(rs.getInt("stockId"));
				stock.setGoodsName(rs.getString("goodsName"));
				stock.setStockNum(rs.getInt("stockNum"));
				//将库存对象添加到库存集合中
				stockList.add(stock);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回库存集合
		return stockList;
	}

	/**
	 * 分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<Stock> findByPage(int rows, int page) {
		//定义库存对象集合
		List<Stock> stockList=new ArrayList<Stock>();
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
			String sql="select * from stock limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义库存对象
				Stock stock=new Stock();
				//将结果集的数据保存到库存对象中
				stock.setStockId(rs.getInt("stockId"));
				stock.setGoodsName(rs.getString("goodsName"));
				stock.setStockNum(rs.getInt("stockNum"));
				//将库存对象添加到库存集合中
				stockList.add(stock);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回库存集合
		return stockList;
	}

	/**
	 * 按特定条件分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @param condition 查询条件
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<Stock> findByPage(int rows, int page, String condition) {
		//定义库存对象集合
		List<Stock> stockList=new ArrayList<Stock>();
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
			String sql="select * from stock "+condition+" limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义库存对象
				Stock stock=new Stock();
				//将结果集的数据保存到库存对象中
				stock.setStockId(rs.getInt("stockId"));
				stock.setGoodsName(rs.getString("goodsName"));
				stock.setStockNum(rs.getInt("stockNum"));
				
				//将库存对象添加到库存集合中
				stockList.add(stock);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回库存集合
		return stockList;
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
			String sql="select count(*) from stock";
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
			String sql="select count(*) from stock "+condition;
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
