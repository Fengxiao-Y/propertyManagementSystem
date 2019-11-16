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
 * ���ܣ�֧������
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��21������10:58:48
 */
public class ExpenseDaoImpl implements ExpenseDao {
	/**
	 * ���
	 * @param expense
	 * @return
	 */
	@Override
	public int add(Expense expense) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//����һ����������
		int rows = 0;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����һ��sqlִ������ַ���
			String sql = "insert into expense(expenseItem,expenseMoney,expense_date) values(?,?,?)";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, expense.getExpenseItem());
			pstmt.setDouble(2, expense.getExpenseMoney());
			pstmt.setString(3, expense.getExpense_date());
			//������ִ�У�����Ӱ���������ֵ��rows����
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		
		return rows;
	}
	/**
	 * �޸�
	 * @param expense
	 * @return
	 */
	@Override
	public int update(Expense expense) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//����һ����������
		int rows = 0;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����һ��sqlִ������ַ���
			String sql = "update expense set expenseItem=?,expenseMoney=?,expense_date=? where expenseId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, expense.getExpenseItem());
			pstmt.setDouble(2, expense.getExpenseMoney());
			pstmt.setString(3, expense.getExpense_date());
			pstmt.setInt(4, expense.getExpenseId());
			//������ִ�У�����Ӱ���������ֵ��rows����
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		
		return rows;
	}
	/**
	 * ɾ��
	 * @param expenseId
	 * @return
	 */
	@Override
	public int delete(int expenseId) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//����һ����������
		int rows = 0;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����һ��sqlִ������ַ���
			String sql = "delete from expense where expenseId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, expenseId);
			//������ִ�У�����Ӱ���������ֵ��rows����
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		
		return rows;
	}
	/**
	 * ��ѯָ��������
	 * @param expenseId
	 * @return
	 */
	@Override
	public Expense getIncomeById(int expenseId) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		Expense expense = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from expense where expenseId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, expenseId);
			//ʵ�������������
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
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return expense;
	}
	/**
	 * ��������ҳ��ѯ
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	@Override
	public List<Expense> findByPage(int rows, int page, String condition) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<Expense> expenseList = new ArrayList<>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from expense "+condition+" limit ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
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
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return expenseList;
	}
	/**
	 * ͳ��
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	@Override
	public int count(String condition) {
		//����һ�����ݿ����Ӷ���
		Connection conn = null;
		//ʲô������
		PreparedStatement pstmt = null;
		//����һ��������������
		int rows=0;
		//�������������
		ResultSet rs= null;
		try {
			//ʵ����������
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql = "select count(*) from expense "+condition;
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//ʵ�������������
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
	 * ��������ҳ��ѯ
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	@Override
	public List<Expense> findTotal(int rows, int page, String condition) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<Expense> expenseList = new ArrayList<>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="SELECT expenseItem,SUM(expenseMoney) AS expenseMoney,DATE_FORMAT(expense_date,'%Y-%m-%d') AS expense_date FROM expense "+condition+" GROUP BY expenseItem,DATE_FORMAT(expense_date,'%Y-%m-%d') ORDER BY expenseItem LIMIT ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
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
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return expenseList;
	}

}
