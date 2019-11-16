package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.hj.dao.ArrearageDao;
import com.yidu.focus.hj.domain.Arrearage;
import com.yidu.focus.utils.JdbcUtils;

public class ArrearageDaoImpl implements ArrearageDao {

	@Override
	public int add(Arrearage arrearage) {
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
			String sql = "insert into arrearage(houseId,ownerName,ownerTelphone,state,arrearageMoney,arrearageDate) values(?,?,?,?,?,?)";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, arrearage.getHouseId());
			pstmt.setString(2, arrearage.getOwnerName());
			pstmt.setString(3, arrearage.getOwnerTelphone());
			pstmt.setString(4, arrearage.getState());
			pstmt.setDouble(5, arrearage.getArrearageMoney());
			pstmt.setString(6, arrearage.getArrearageDate());
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
	public int update(Arrearage arrearage) {
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
			String sql = "update arrearage set ownerName=?,ownerTelphone=?,state=?,arrearageMoney=?,arrearageDate=? where houseId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			
			pstmt.setString(1, arrearage.getOwnerName());
			pstmt.setString(2, arrearage.getOwnerTelphone());
			pstmt.setString(3, arrearage.getState());
			pstmt.setDouble(4, arrearage.getArrearageMoney());
			pstmt.setString(5, arrearage.getArrearageDate());
			pstmt.setString(6, arrearage.getHouseId());
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
	public int delete(String houseId) {
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
			String sql = "delete from arrearage where houseId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			
			pstmt.setString(1, houseId);
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
	public Arrearage getArrearageById(String houseId) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		Arrearage arrearage = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from arrearage where houseId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, houseId);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				arrearage = new Arrearage();
				arrearage.setHouseId(rs.getString("houseId"));
				arrearage.setOwnerName(rs.getString("ownerName"));
				arrearage.setOwnerTelphone(rs.getString("ownerTelphone"));
				arrearage.setState(rs.getString("state"));
				arrearage.setArrearageMoney(rs.getDouble("arrearageMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				arrearage.setArrearageDate(sdf.format(rs.getTimestamp("arrearageDate")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return arrearage;
	}

	@Override
	public List<Arrearage> findByPage(int rows, int page, String condition) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<Arrearage> arrearageList = new ArrayList<>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from arrearage "+condition+" limit ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				Arrearage arrearage = new Arrearage();
				arrearage.setHouseId(rs.getString("houseId"));
				arrearage.setOwnerName(rs.getString("ownerName"));
				arrearage.setOwnerTelphone(rs.getString("ownerTelphone"));
				arrearage.setState(rs.getString("state"));
				arrearage.setArrearageMoney(rs.getDouble("arrearageMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				arrearage.setArrearageDate(sdf.format(rs.getTimestamp("arrearageDate")));
				arrearageList.add(arrearage);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return arrearageList;
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
			String sql = "select count(*) from arrearage "+condition;
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
	public Arrearage getArrearageByName(String ownerName) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		Arrearage arrearage = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from arrearage where ownerName=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, ownerName);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				arrearage = new Arrearage();
				arrearage.setHouseId(rs.getString("houseId"));
				arrearage.setOwnerName(rs.getString("ownerName"));
				arrearage.setOwnerTelphone(rs.getString("ownerTelphone"));
				arrearage.setState(rs.getString("state"));
				arrearage.setArrearageMoney(rs.getDouble("arrearageMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				arrearage.setArrearageDate(sdf.format(rs.getTimestamp("arrearageDate")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return arrearage;
	}
	
}
