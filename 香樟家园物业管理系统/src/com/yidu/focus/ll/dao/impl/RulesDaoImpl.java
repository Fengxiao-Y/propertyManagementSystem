package com.yidu.focus.ll.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.ll.dao.RulesDao;
import com.yidu.focus.ll.domain.Rules;
/**
 * 
 * 功能：规章制度表 
 * 作者：刘李
 * 日期：2019年10月31日上午9:14:52
 * 版本：1.0
 */
public class RulesDaoImpl implements RulesDao {

	/**
	 * 将实体类对象添加到数据库表中
	 * @param rules 实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int add(Rules rules) {
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
			String sql="INSERT INTO Rules(rulesId,rulesTitle,rulesTheme,"
					+ "rulesStatus,rulesTime) VALUES(default,?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			
			pstmt.setString(1, rules.getRulesTitle());
			pstmt.setString(2, rules.getRulesTheme());
			pstmt.setString(3, rules.getRulesStatus());
			pstmt.setString(4, rules.getRulesTime());
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
	 * 按照文件编号（主键）删除数据
	 * @param rulesId 文件编号
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int delete(String rulesId) {
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
			String sql="delete from rules where rulesId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, rulesId);
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
	 * @param rules 实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int update(Rules rules) {
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
			String sql="update rules set rulesTitle=?,rulesTheme=?,"
					+ "rulesStatus=?,rulesTime=? where rulesId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值			
			
			pstmt.setString(1, rules.getRulesTitle());
			pstmt.setString(2, rules.getRulesTheme());
			pstmt.setString(3, rules.getRulesStatus());
			//pstmt.setDate(4, java.sql.Date.valueOf(rules.getRulesTime()));
			pstmt.setString(4, rules.getRulesTime());
			pstmt.setString(5, rules.getRulesId());
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
	 * 按照文件编号（主键）从数据库表中查找数据
	 * @param rulesId 文件编号
	 * @return 部门实体对象
	 */
	@Override
	public Rules findById(String rulesId) {
		//声明文件对象
		Rules rules=null;
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
			String sql="select rulesId,rulesTitle,rulesTheme,rulesStatus,"
					+ "rulesTime from Rules where rulesId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, rulesId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化文件对象
				rules=new Rules();
				//将结果集中数据保存到文件对象中
				rules.setRulesId(rs.getString("rulesId"));
				rules.setRulesTitle(rs.getString("rulesTitle"));
                rules.setRulesTheme(rs.getString("rulesTheme"));
                rules.setRulesStatus(rs.getString("rulesStatus"));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				rules.setRulesTime(sdf.format(rs.getDate("rulesTime")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return rules;
	}

	/**
	 * 查询Rules表中所有考勤信息，并封装成集合返回
	 * @return 
	 */
	@Override
	public List<Rules> findAll() {
		//声明数据库连接对象
		Connection conn=null;
		//声明语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象 
		ResultSet rs=null;
		//定义一个集合数组对象 
		List<Rules> rulesList=new ArrayList<Rules>();

		try {
			//1:实例化连接对象
			conn=JdbcUtils.getConnection();
			//2:A:定义要执行的SQL语句字符串
			String sql="select  rulesId,rulesTitle,rulesTheme,rulesStatus,"
					            + "rulesTime from rules";
			//2:B:根据SQL命令和数据库对象实例化语句对象
			pstmt=conn.prepareStatement(sql);
			//3:执行语句对象，得到结果集对象
			rs=pstmt.executeQuery();
			//4:对结果集进行遍历处理，将结果集中记录行转换成文件对象，并添加到集合中
			while(rs.next()){
				//定义文件对象
				Rules rules=new Rules();
				//将结果集的数据保存到文件对象中
				rules.setRulesId(rs.getString("rulesId"));
				rules.setRulesTitle(rs.getString("rulesTitle"));
                rules.setRulesTheme(rs.getString("rulesTheme"));
                rules.setRulesStatus(rs.getString("rulesStatus"));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				rules.setRulesTime(sdf.format(rs.getDate("rulesTime")));
				//将客户对象添加到文件集合中
				rulesList.add(rules);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回考勤信息集合
		return rulesList;
	}

	/**
	 * 分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<Rules> findByPage(int rows, int page) {
		//定义文件对象集合
		List<Rules> rulesList=new ArrayList<Rules>();
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
			String sql="select * from rules limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义文件对象
				Rules rules=new Rules();
				//将结果集的数据保存到文件对象中
				rules.setRulesId(rs.getString("rulesId"));
				rules.setRulesTitle(rs.getString("rulesTitle"));
                rules.setRulesTheme(rs.getString("rulesTheme"));
                rules.setRulesStatus(rs.getString("rulesStatus"));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				rules.setRulesTime(sdf.format(rs.getDate("rulesTime")));
				//将客户对象添加到文件集合中
				rulesList.add(rules);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return rulesList;
	}

	/**
	 * 按特定条件分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @param condition 查询条件
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<Rules> findByPage(int rows, int page, String condition) {
		//定义文件集合对象
		List<Rules> rulesList=new ArrayList<Rules>();
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
			String sql="select * from rules "+condition+" limit ?,?";
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
				Rules rules=new Rules();
				//将结果集的数据保存到文件对象中
				rules.setRulesId(rs.getString("rulesId"));
				rules.setRulesTitle(rs.getString("rulesTitle"));
                rules.setRulesTheme(rs.getString("rulesTheme"));
                rules.setRulesStatus(rs.getString("rulesStatus"));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				rules.setRulesTime(sdf.format(rs.getDate("rulesTime")));
				//将客户对象添加到文件集合中
				rulesList.add(rules);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回文件集合
		return rulesList;
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
			String sql="select count(*) from rules";
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
			String sql="select count(*) from rules "+condition;
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
