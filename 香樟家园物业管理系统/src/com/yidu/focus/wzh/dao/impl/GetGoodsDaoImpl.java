
package com.yidu.focus.wzh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.wzh.dao.GetGoodsDao;
import com.yidu.focus.wzh.domain.GetGoods;

/**
 * 功能：GetGoods表的数据库的功能操作
 * 作者：伍志华
 * 日期：2019年10月12日下午9:37:33
 * 版本：1.0
 */
public class GetGoodsDaoImpl implements GetGoodsDao{

	/**
	 * 将实体类对象添加到数据库表中
	 * @param getgoods 物料领用实体类对象
	 * @return rows 影响的行数（1成功，0失败）
	 */
	@Override
	public int add(GetGoods getgoods) {
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
			String sql="INSERT INTO getgoods(goodsName,ggNum,ggName,ggTime,ggHandli) VALUES(?,?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, getgoods.getGoodsName());
			pstmt.setInt(2, getgoods.getggNum());
			pstmt.setString(3, getgoods.getggName());
			pstmt.setString(4, getgoods.getggTime());
			pstmt.setString(5, getgoods.getggHandli());
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
	 * 按照领用编号（主键）删除数据
	 * @param uId 用户编号
	 * @return rows 影响的行数（1成功，0失败）
	 */
	@Override
	public int delete(int ggId) {
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
			String sql="delete from getgoods where ggId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, ggId);
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
	 * @param getgoods 物料领用实体类对象
	 * @return rows 影响的行数（1成功，0失败）
	 */
	@Override
	public int update(GetGoods getgoods) {
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
			String sql="update getgoods set goodsName=?,ggNum=?,ggName=?,ggTime=?,ggHandli=? where ggId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值			
			pstmt.setString(1, getgoods.getGoodsName());
			pstmt.setInt(2, getgoods.getggNum());
			pstmt.setString(3, getgoods.getggName());
			pstmt.setString(4, getgoods.getggTime());
			pstmt.setString(5, getgoods.getggHandli());
			pstmt.setInt(6, getgoods.getGgId());
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
	 * 按照物料领用编号（主键）从数据库表中查找数据
	 * @param cid 物料领用编号
	 * @return getgoods物料领用实体对象
	 */
	@Override
	public GetGoods findById(int ggId) {
		//声明物料领用对象
		GetGoods getgoods=null;
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
			String sql="select * from getgoods where ggId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, ggId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化物料领用对象
				getgoods=new GetGoods();
				//将结果集中数据保存到物料领用对象中
				getgoods.setGgId(rs.getInt("ggId"));
				getgoods.setGoodsName(rs.getString("goodsName"));
				getgoods.setggNum(rs.getInt("ggNum"));
				getgoods.setggName(rs.getString("ggName"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				getgoods.setggTime(sdf.format(rs.getTimestamp("ggTime")));
				getgoods.setggHandli(rs.getString("ggHandli"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return getgoods;
	}
	/**
	 * 分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @return  getgoodsList 指定页中的数据集合
	 */
	@Override
	public List<GetGoods> findByPage(int rows, int page) {
		//定义物料领用对象集合
		List<GetGoods> getgoodsList=new ArrayList<GetGoods>();
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
			String sql="select * from getgoods limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义物料领用对象
				GetGoods getgoods=new GetGoods();
				//将结果集的数据保存到物料领用对象中
				getgoods.setGgId(rs.getInt("ggId"));
				getgoods.setGoodsName(rs.getString("goodsName"));
				getgoods.setggNum(rs.getInt("ggNum"));
				getgoods.setggName(rs.getString("ggName"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				getgoods.setggTime(sdf.format(rs.getTimestamp("ggTime")));
				getgoods.setggHandli(rs.getString("ggHandli"));
				//将物料领用对象添加到物料领用集合中
				getgoodsList.add(getgoods);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回物料领用集合
		return getgoodsList;
	}
	/**
	 * 按特定条件分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @param condition 查询条件
	 * @return getgoodsList 指定页中的数据集合
	 */
	@Override
	public List<GetGoods> findByPage(int rows, int page, String condition) {
		//定义物料领用对象集合
		List<GetGoods> getgoodsList=new ArrayList<GetGoods>();
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
			String sql="select * from getgoods "+condition+" limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义物料领用对象
				GetGoods getgoods=new GetGoods();
				//将结果集的数据保存到物料领用对象中
				getgoods.setGgId(rs.getInt("ggId"));
				getgoods.setGoodsName(rs.getString("goodsName"));
				getgoods.setggNum(rs.getInt("ggNum"));
				getgoods.setggName(rs.getString("ggName"));
				//日期格式转换
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				getgoods.setggTime(sdf.format(rs.getTimestamp("ggTime")));
				getgoods.setggHandli(rs.getString("ggHandli"));
				//将物料领用对象添加到物料领用集合中
				getgoodsList.add(getgoods);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回物料领用集合
		return getgoodsList;
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
			String sql="select count(*) from getgoods";
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
			String sql="select count(*) from getgoods "+condition;
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
