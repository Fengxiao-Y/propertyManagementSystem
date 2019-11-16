package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.hj.dao.IncomeDao;
import com.yidu.focus.hj.domain.Income;
import com.yidu.focus.utils.JdbcUtils;
/**
 * 
 * ���ܣ�ʵ�������������
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��31������8:53:23
 */
public class IncomeDaoImpl implements IncomeDao {
	/**
	 * ���
	 * @param income
	 * @return
	 */
	@Override
	public int add(Income income) {
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
			String sql = "insert into income(source,money,source_date) values(?,?,?)";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, income.getSource());
			pstmt.setDouble(2, income.getMoney());
			pstmt.setString(3, income.getSource_date());
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
	 * @param income
	 * @return
	 */
	@Override
	public int update(Income income) {
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
			String sql = "update income set source=?,money=?,source_date=? where incomeId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, income.getSource());
			pstmt.setDouble(2, income.getMoney());
			pstmt.setString(3, income.getSource_date());
			pstmt.setInt(4, income.getIncomeId());
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
	 * @param incomeId
	 * @return
	 */
	@Override
	public int delete(int incomeId) {
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
			String sql = "delete from income where incomeId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, incomeId);
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
	 * @param incomeId
	 * @return
	 */
	@Override
	public Income getIncomeById(int incomeId) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		Income income = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from income where incomeId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, incomeId);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				income = new Income();
				income.setIncomeId(rs.getInt("incomeId"));
				income.setSource(rs.getString("source"));
				income.setMoney(rs.getDouble("money"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				income.setSource_date(sdf.format(rs.getTimestamp("source_date")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return income;
	}
	/**
	 * ��������ҳ��ѯ
	 * @param rows
	 * @param page
	 * @param condition
	 * @return
	 */
	@Override
	public List<Income> findByPage(int rows, int page, String condition) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<Income> incomeList = new ArrayList<>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from income "+condition+" limit ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				Income income = new Income();
				income.setIncomeId(rs.getInt("incomeId"));
				income.setSource(rs.getString("source"));
				income.setMoney(rs.getDouble("money"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				income.setSource_date(sdf.format(rs.getTimestamp("source_date")));
				incomeList.add(income);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return incomeList;
	}
	/**
	 * ������ͳ�Ƽ�¼��
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
			String sql = "select count(*) from income "+condition;
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

}
