
package com.yidu.focus.wzh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.wzh.dao.UsersDao;
import com.yidu.focus.wzh.domain.Users;

/**
 * 功能：针对数据表的功能操作
 * 作者：伍志华
 * 日期：2019年10月12日上午11:24:45
 * 版本：1.0
 */
public class UsersDaoImpl implements UsersDao{

	/**
	 * 将实体类对象添加到数据库表中
	 * @param users 用户户实体类对象
	 * @return  rows 影响的行数（1成功，0失败）
	 */
	@Override
	public int doRegister(Users users) {
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
			String sql="INSERT INTO users(uName,uPassword,uEmail,uIphone) VALUES(?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, users.getuName());
			pstmt.setString(2, users.getuPassword());
			pstmt.setString(3, users.getuEmail());
			pstmt.setString(4, users.getuIphone());
			//执行预编译语句对象，                                                                                                                                                                                                                                                                                                                                                                                          得到影响行数
			rows=pstmt.executeUpdate();
			System.out.println(rows);

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
	 * 按库存编号（主键）删除数据
	 * @param uId 用户编号
	 * @return rows 影响的行数（1成功，0失败）
	 */
	@Override
	public int delete(int uId) {
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
			String sql="delete from users where uId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, uId);
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
	 * @param users 用户实体类对象
	 * @return rows 影响的行数（1成功，0失败）
	 */
	@Override
	public int update(Users users) {
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
			//定义sql语句字符串
			String sql="update users set uName=?,uPassword=?,uEmail=?,uIphone=? where uId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, users.getuName());
			pstmt.setString(2, users.getuPassword());
			pstmt.setString(3, users.getuEmail());
			pstmt.setString(4, users.getuIphone());
			pstmt.setInt(5, users.getuId());
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
	 * 按照用户编号（主键）从数据库表中查找数据
	 * @param uId 用户编号
	 * @return users 用户实体对象
	 */
	@Override
	public Users findById(int uId) {
		//声明用户对象
		Users users=null;
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
			String sql="select * from Users where uId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, uId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化用户对象
				users=new Users();
				//将结果集中数据保存到用户对象中
				users.setuName(rs.getString("uName"));
				users.setuPassword(rs.getString("uPassword"));
				users.setuEmail(rs.getString("uEmail"));
				users.setuIphone(rs.getString("uIphone"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return users;
	}

	/**
	 * 分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @return usersList 指定页中的数据集合
	 */
	@Override
	public List<Users> findByPage(int rows, int page) {
		//定义用户对象集合
		List<Users> usersList=new ArrayList<Users>();
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
			String sql="select * from users limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义用户对象
				Users users=new Users();
				//将结果集的数据保存到用户对象中
				users.setuId(rs.getInt("uId"));
				users.setuName(rs.getString("uName"));
				users.setuPassword(rs.getString("uPassword"));
				users.setuEmail(rs.getString("uEmail"));
				users.setuIphone(rs.getString("uIphone"));
				//将用户对象添加到用户集合中
				usersList.add(users);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回用户集合
		return usersList;
	}

	/**
	 * 按特定条件分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @param condition 查询条件
	 * @return usersList 指定页中的数据集合
	 */
	@Override
	public List<Users> findByPage(int rows, int page, String condition) {
		//定义用户对象集合
		List<Users> usersList=new ArrayList<Users>();
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
			String sql="select * from users "+condition+" limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义用户对象
				Users users=new Users();
				//将结果集的数据保存到用户对象中
				users.setuId(rs.getInt("uId"));
				users.setuName(rs.getString("uName"));
				users.setuPassword(rs.getString("uPassword"));
				users.setuEmail(rs.getString("uEmail"));
				users.setuIphone(rs.getString("uIphone"));
				//将用户对象添加到用户集合中
				usersList.add(users);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回用户集合
		return usersList;
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
			String sql="select count(*) from users";
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
			String sql="select count(*) from users "+condition;
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

	
	/**
	 * 按照用户名从数据库表中查找数据
	 * @param uName 用户名
	 * @return users 用户实体对象
	 */
	@Override
	public Users findByUIphone(String uIphone) {
		//声明用户对象
		Users users=null;
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
			String sql="select * from Users where uIphone=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, uIphone);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化用户对象
				users=new Users();
				//将结果集中数据保存到用户对象中
				users.setuId(rs.getInt("uId"));
				users.setuName(rs.getString("uName"));
				users.setuPassword(rs.getString("uPassword"));
				users.setuEmail(rs.getString("uEmail"));
				users.setuIphone(rs.getString("uIphone"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return users;
	}
	/**
	 * 登录
	 * @param uName 用户名
	 * @param uPassword 用户密码
	 * @return users 用户实体对象
	 */
	@Override
	public Users doLogin(String uName,String uPassword) {
		//声明用户对象
		Users users=null;
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
			String sql="select * from Users where uName=? and uPassword=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, uName);
			pstmt.setString(2, uPassword);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化用户对象
				users=new Users();
				//将结果集中数据保存到用户对象中
				users.setuId(rs.getInt("uId"));
				users.setuName(rs.getString("uName"));
				users.setuPassword(rs.getString("uPassword"));
				users.setuEmail(rs.getString("uEmail"));
				users.setuIphone(rs.getString("uIphone"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return users;
	}

}
