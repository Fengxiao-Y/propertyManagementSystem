package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.TotalIncomeDao;
import com.yidu.focus.hj.domain.TotalIncome;

public class TotalIncomeDaoImpl implements TotalIncomeDao {

	@Override
	public int add(TotalIncome totalIncome) {
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
			String sql = "insert into totalIncome values(?,?,?,?,?,?,?)";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, totalIncome.getTotalId());
			pstmt.setDouble(2, totalIncome.getPropertyFeeIncome());
			pstmt.setDouble(3, totalIncome.getParkFeeIncome());
			pstmt.setDouble(4, totalIncome.getOtherServiceIncome());
			pstmt.setDouble(5, totalIncome.getOtherIncome());
			pstmt.setDouble(6, totalIncome.getTotalIncome());
			pstmt.setString(7, totalIncome.getTotalIncomeMonth());
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

	@Override
	public int update(TotalIncome totalIncome) {
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
			String sql = "update totalIncome set propertyFeeIncome=?,parkFeeIncome=?,otherServiceIncome=?,otherIncome=?,totalIncome=?,totalIncomeMonth=? where totalId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ  
			pstmt.setDouble(1, totalIncome.getPropertyFeeIncome());
			pstmt.setDouble(2, totalIncome.getParkFeeIncome());
			pstmt.setDouble(3, totalIncome.getOtherServiceIncome());
			pstmt.setDouble(4, totalIncome.getOtherIncome());
			pstmt.setDouble(5, totalIncome.getTotalIncome());
			pstmt.setString(6, totalIncome.getTotalIncomeMonth());
			pstmt.setString(7, totalIncome.getTotalId());
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

	@Override
	public int delete(String totalId) {
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
			String sql = "delete from totalIncome where totalId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ  
			pstmt.setString(1, totalId);
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

	@Override
	public TotalIncome getTotalIncomeById(String totalId) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		TotalIncome totalIncome = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from totalIncome where totalId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, totalId);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				totalIncome = new TotalIncome();
				totalIncome.setTotalId(rs.getString("totalId"));
				totalIncome.setPropertyFeeIncome(rs.getDouble("propertyFeeIncome"));
				totalIncome.setParkFeeIncome(rs.getDouble("parkFeeIncome"));
				totalIncome.setOtherServiceIncome(rs.getDouble("otherServiceIncome"));
				totalIncome.setOtherIncome(rs.getDouble("otherIncome"));
				totalIncome.setTotalIncome(rs.getDouble("totalIncome"));
				totalIncome.setTotalIncomeMonth(rs.getString("totalIncomeMonth"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return totalIncome;
	}

	@Override
	public List<TotalIncome> selectByPage(int rows, int page) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<TotalIncome> totalIncomeList = new ArrayList<>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from totalIncome limit ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				TotalIncome totalIncome = new TotalIncome();
				totalIncome.setTotalId(rs.getString("totalId"));
				totalIncome.setPropertyFeeIncome(rs.getDouble("propertyFeeIncome"));
				totalIncome.setParkFeeIncome(rs.getDouble("parkFeeIncome"));
				totalIncome.setOtherServiceIncome(rs.getDouble("otherServiceIncome"));
				totalIncome.setOtherIncome(rs.getDouble("otherIncome"));
				totalIncome.setTotalIncome(rs.getDouble("totalIncome"));
				totalIncome.setTotalIncomeMonth(rs.getString("totalIncomeMonth"));
				totalIncomeList.add(totalIncome);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return totalIncomeList;
	}

	@Override
	public List<TotalIncome> findByPage(int rows, int page, String condition) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<TotalIncome> totalIncomeList = new ArrayList<>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from totalIncome "+condition+" limit ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				TotalIncome totalIncome = new TotalIncome();
				totalIncome.setTotalId(rs.getString("totalId"));
				totalIncome.setPropertyFeeIncome(rs.getDouble("propertyFeeIncome"));
				totalIncome.setParkFeeIncome(rs.getDouble("parkFeeIncome"));
				totalIncome.setOtherServiceIncome(rs.getDouble("otherServiceIncome"));
				totalIncome.setOtherIncome(rs.getDouble("otherIncome"));
				totalIncome.setTotalIncome(rs.getDouble("totalIncome"));
				totalIncome.setTotalIncomeMonth(rs.getString("totalIncomeMonth"));
				totalIncomeList.add(totalIncome);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return totalIncomeList;
	}

	@Override
	public int count() {
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
			String sql = "select count(*) from totalIncome ";
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
			String sql = "select count(*) from totalIncome "+condition;
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
