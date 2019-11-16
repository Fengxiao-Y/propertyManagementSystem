package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.hj.dao.CarArrearageDao;
import com.yidu.focus.hj.domain.CarArrearage;
import com.yidu.focus.utils.JdbcUtils;

public class CarArrearageDaoImpl implements CarArrearageDao {

	@Override
	public int add(CarArrearage carArrearage) {
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
			String sql = "insert into carArrearage(parkId,ownerName,ownerTelphone,parkStatus,carMoney,carDate) values(?,?,?,?,?,?)";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, carArrearage.getParkId());
			pstmt.setString(2, carArrearage.getOwnerName());
			pstmt.setString(3, carArrearage.getOwnerTelphone());
			pstmt.setString(4, carArrearage.getParkStatus());
			pstmt.setDouble(5, carArrearage.getCarMoney());
			pstmt.setString(6, carArrearage.getCarDate());
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
	public int update(CarArrearage carArrearage) {
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
			String sql = "update carArrearage set ownerName=?,ownerTelphone=?,parkStatus=?,carMoney=?,carDate=? where parkId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			
			pstmt.setString(1, carArrearage.getOwnerName());
			pstmt.setString(2, carArrearage.getOwnerTelphone());
			pstmt.setString(3, carArrearage.getParkStatus());
			pstmt.setDouble(4, carArrearage.getCarMoney());
			pstmt.setString(5, carArrearage.getCarDate());
			pstmt.setString(6, carArrearage.getParkId());
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
	public int delete(String parkId) {
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
			String sql = "delete from carArrearage where parkId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			
			pstmt.setString(1, parkId);
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
	public CarArrearage getCarArrearageById(String parkId) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		CarArrearage carArrearage = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from carArrearage where parkId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, parkId);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				carArrearage = new CarArrearage();
				carArrearage.setParkId(rs.getString("parkId"));
				carArrearage.setOwnerName(rs.getString("ownerName"));
				carArrearage.setOwnerTelphone(rs.getString("ownerTelphone"));
				carArrearage.setParkStatus(rs.getString("parkStatus"));
				carArrearage.setCarMoney(rs.getDouble("carMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carArrearage.setCarDate(sdf.format(rs.getTimestamp("carDate")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return carArrearage;
	}

	@Override
	public List<CarArrearage> findByPage(int rows, int page, String condition) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<CarArrearage> carArrearageList = new ArrayList<>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from carArrearage "+condition+" limit ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				CarArrearage carArrearage = new CarArrearage();
				carArrearage.setParkId(rs.getString("parkId"));
				carArrearage.setOwnerName(rs.getString("ownerName"));
				carArrearage.setOwnerTelphone(rs.getString("ownerTelphone"));
				carArrearage.setParkStatus(rs.getString("parkStatus"));
				carArrearage.setCarMoney(rs.getDouble("carMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carArrearage.setCarDate(sdf.format(rs.getTimestamp("carDate")));
				carArrearageList.add(carArrearage);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return carArrearageList;
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
			String sql = "select count(*) from carArrearage "+condition;
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
	public CarArrearage getCarArrearageByName(String ownerName) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		CarArrearage carArrearage = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from carArrearage where ownerName=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, ownerName);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				carArrearage = new CarArrearage();
				carArrearage.setParkId(rs.getString("parkId"));
				carArrearage.setOwnerName(rs.getString("ownerName"));
				carArrearage.setOwnerTelphone(rs.getString("ownerTelphone"));
				carArrearage.setParkStatus(rs.getString("parkStatus"));
				carArrearage.setCarMoney(rs.getDouble("carMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carArrearage.setCarDate(sdf.format(rs.getTimestamp("carDate")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return carArrearage;
	}
}
