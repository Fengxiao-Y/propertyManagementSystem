package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.OtherServiceIncomeDao;
import com.yidu.focus.hj.domain.OtherServiceIncome;

public class OtherServiceIncomeDaoImpl implements OtherServiceIncomeDao {

	@Override
	public int add(OtherServiceIncome otherServiceIncome) {
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
			String sql = "insert into otherServiceIncome values(?,?,?,?,?,?)";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, otherServiceIncome.getOsIcome());
			pstmt.setDouble(2, otherServiceIncome.getMaterialCost());
			pstmt.setDouble(3, otherServiceIncome.getLabor());
			pstmt.setDouble(4, otherServiceIncome.getOther());
			pstmt.setDouble(5, otherServiceIncome.getOsmoney());
			pstmt.setDate(6, java.sql.Date.valueOf(otherServiceIncome.getOsmonth()));
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
	public int update(OtherServiceIncome otherServiceIncome) {
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
			String sql = "update otherServiceIncome set materialCost=?,labor=?,other=?,osmoney=?,osmonth=? where osIcome=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ

			pstmt.setDouble(1, otherServiceIncome.getMaterialCost());
			pstmt.setDouble(2, otherServiceIncome.getLabor());
			pstmt.setDouble(3, otherServiceIncome.getOther());
			pstmt.setDouble(4, otherServiceIncome.getOsmoney());
			pstmt.setDate(5, java.sql.Date.valueOf(otherServiceIncome.getOsmonth()));
			pstmt.setString(6, otherServiceIncome.getOsIcome());
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
	public int delete(String osIcome) {
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
			String sql = "delete from otherServiceIncome where osIcome=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, osIcome);
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
	public OtherServiceIncome getOtherServiceIncomeById(String osIcome) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		OtherServiceIncome otherServiceIncome = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from otherServiceIncome where osIcome=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, osIcome);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				otherServiceIncome = new OtherServiceIncome();
				otherServiceIncome.setOsIcome(rs.getString("osIcome"));
				otherServiceIncome.setMaterialCost(rs.getDouble("materialCost"));
				otherServiceIncome.setLabor(rs.getDouble("labor"));
				otherServiceIncome.setOther(rs.getDouble("other"));
				otherServiceIncome.setOsmoney(rs.getDouble("osmoney"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				otherServiceIncome.setOsmonth(sdf.format(rs.getDate("osmonth")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return otherServiceIncome;
	}

	@Override
	public List<OtherServiceIncome> selectByPage(int rows, int page) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<OtherServiceIncome> otherServiceIncomeList = new ArrayList<>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from otherServiceIncome limit ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				OtherServiceIncome otherServiceIncome = new OtherServiceIncome();
				otherServiceIncome.setOsIcome(rs.getString("osIcome"));
				otherServiceIncome.setMaterialCost(rs.getDouble("materialCost"));
				otherServiceIncome.setLabor(rs.getDouble("labor"));
				otherServiceIncome.setOther(rs.getDouble("other"));
				otherServiceIncome.setOsmoney(rs.getDouble("osmoney"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				otherServiceIncome.setOsmonth(sdf.format(rs.getDate("osmonth")));
				otherServiceIncomeList.add(otherServiceIncome);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return otherServiceIncomeList;
	}

	@Override
	public List<OtherServiceIncome> findByPage(int rows, int page, String condition) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<OtherServiceIncome> otherServiceIncomeList = new ArrayList<>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from otherServiceIncome "+condition+" limit ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				OtherServiceIncome otherServiceIncome = new OtherServiceIncome();
				otherServiceIncome.setOsIcome(rs.getString("osIcome"));
				otherServiceIncome.setMaterialCost(rs.getDouble("materialCost"));
				otherServiceIncome.setLabor(rs.getDouble("labor"));
				otherServiceIncome.setOther(rs.getDouble("other"));
				otherServiceIncome.setOsmoney(rs.getDouble("osmoney"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				otherServiceIncome.setOsmonth(sdf.format(rs.getDate("osmonth")));
				otherServiceIncomeList.add(otherServiceIncome);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return otherServiceIncomeList;
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
			String sql = "select count(*) from otherServiceIncome ";
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
			String sql = "select count(*) from otherServiceIncome "+condition;
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
