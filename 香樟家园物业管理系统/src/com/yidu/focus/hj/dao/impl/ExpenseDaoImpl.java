package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.hj.dao.ExpenseDao;
import com.yidu.focus.hj.domain.Expense;
import com.yidu.focus.utils.JdbcUtils;
/**
 * 
 * 功能：支出汇总
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月21日上午10:58:48
 */
public class ExpenseDaoImpl implements ExpenseDao {
	/**
	 * 添加
	 * @param expense
	 * @return
	 */
	@Override
	public int add(Expense expense) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//定义一个行数变量
		int rows = 0;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句字符串
			String sql = "insert into expense(expenseItem,expenseMoney,expense_date) values(?,?,?)";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, expense.getExpenseItem());
			pstmt.setDouble(2, expense.getExpenseMoney());
			pstmt.setString(3, expense.getExpense_date());
			//语句对象执行，并将影响的行数赋值给rows变量
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		
		return rows;
	}
	/**
	 * 修改
	 * @param expense
	 * @return
	 */
	@Override
	public int update(Expense expense) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//定义一个行数变量
		int rows = 0;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句字符串
			String sql = "update expense set expenseItem=?,expenseMoney=?,expense_date=? where expenseId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setString(1, expense.getExpenseItem());
			pstmt.setDouble(2, expense.getExpenseMoney());
			pstmt.setString(3, expense.getExpense_date());
			pstmt.setInt(4, expense.getExpenseId());
			//语句对象执行，并将影响的行数赋值给rows变量
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		
		return rows;
	}
	/**
	 * 删除
	 * @param expenseId
	 * @return
	 */
	@Override
	public int delete(int expenseId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//定义一个行数变量
		int rows = 0;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义一个sql执行语句字符串
			String sql = "delete from expense where expenseId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, expenseId);
			//语句对象执行，并将影响的行数赋值给rows变量
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		
		return rows;
	}
	/**
	 * 查询指定行数据
	 * @param expenseId
	 * @return
	 */
	@Override
	public Expense getIncomeById(int expenseId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		Expense expense = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from expense where expenseId=?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, expenseId);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				expense = new Expense();
				expense.setExpenseId(rs.getInt("expenseId"));
				expense.setExpenseItem(rs.getString("expenseItem"));
				expense.setExpenseMoney(rs.getDouble("expenseMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				expense.setExpense_date(sdf.format(rs.getTimestamp("expense_date")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return expense;
	}
	/**
	 * 按条件分页查询
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	@Override
	public List<Expense> findByPage(int rows, int page, String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<Expense> expenseList = new ArrayList<>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="select * from expense "+condition+" limit ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				Expense expense = new Expense();
				expense.setExpenseId(rs.getInt("expenseId"));
				expense.setExpenseItem(rs.getString("expenseItem"));
				expense.setExpenseMoney(rs.getDouble("expenseMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				expense.setExpense_date(sdf.format(rs.getTimestamp("expense_date")));
				expenseList.add(expense);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return expenseList;
	}
	/**
	 * 统计
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	@Override
	public int count(String condition) {
		//声明一个数据库连接对象
		Connection conn = null;
		//什么语句对象
		PreparedStatement pstmt = null;
		//定义一个返回行数变量
		int rows=0;
		//声明结果集对象
		ResultSet rs= null;
		try {
			//实例化语句对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql = "select count(*) from expense "+condition;
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			if(rs.next()){
				rows = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtils.close(rs, pstmt, conn);
		}
		return rows;
	}
	/**
	 * 按条件分页查询
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	@Override
	public List<Expense> findTotal(int rows, int page, String condition) {
		//声明数据库连接对象
		Connection conn = null;
		//声明语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		List<Expense> expenseList = new ArrayList<>();
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			//定义sql执行语句字符串
			String sql="SELECT expenseItem,SUM(expenseMoney) AS expenseMoney,DATE_FORMAT(expense_date,'%Y-%m-%d') AS expense_date FROM expense "+condition+" GROUP BY expenseItem,DATE_FORMAT(expense_date,'%Y-%m-%d') ORDER BY expenseItem LIMIT ?,?";
			//实例化语句对象
			pstmt = conn.prepareStatement(sql);
			//给sql语句赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//实例化结果集对象
			rs = pstmt.executeQuery();
			while(rs.next()){
				Expense expense = new Expense();
				expense.setExpenseItem(rs.getString("expenseItem"));
				expense.setExpenseMoney(rs.getDouble("expenseMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				expense.setExpense_date(sdf.format(rs.getDate("expense_date")));
				expenseList.add(expense);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		return expenseList;
	}

}
