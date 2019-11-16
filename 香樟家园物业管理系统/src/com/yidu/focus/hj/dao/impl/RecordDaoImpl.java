package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.hj.dao.RecordDao;
import com.yidu.focus.hj.domain.Record;
import com.yidu.focus.utils.JdbcUtils;

public class RecordDaoImpl implements RecordDao {

	@Override
	public int add(Record record) {
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
			String sql = "insert into record(recordId,ownerName,ownerTelphone,recordType,recordMoney,recordDate) values(?,?,?,?,?,?)";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, record.getRecordId());
			pstmt.setString(2, record.getOwnerName());
			pstmt.setString(3, record.getOwnerTelphone());
			pstmt.setString(4, record.getRecordType());
			pstmt.setDouble(5, record.getRecordMoney());
			pstmt.setString(6, record.getRecordDate());
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
	public int update(Record record) {
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
			String sql = "update record set ownerName=?,ownerTelphone=?,recordType=?,recordMoney=?,recordDate=? where recordId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			
			pstmt.setString(1, record.getOwnerName());
			pstmt.setString(2, record.getOwnerTelphone());
			pstmt.setString(3, record.getRecordType());
			pstmt.setDouble(4, record.getRecordMoney());
			pstmt.setString(5, record.getRecordDate());
			pstmt.setInt(6, record.getRecordId());
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
	public int delete(int recordId) {
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
			String sql = "delete from record where recordId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			
			pstmt.setInt(1, recordId);
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
	public Record getRecordById(int recordId) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		Record record = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from record where recordId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, recordId);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				record = new Record();
				record.setRecordId(rs.getInt("recordId"));
				record.setOwnerName(rs.getString("ownerName"));
				record.setOwnerTelphone(rs.getString("ownerTelphone"));
				record.setRecordType(rs.getString("recordType"));
				record.setRecordMoney(rs.getDouble("recordMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				record.setRecordDate(sdf.format(rs.getTimestamp("recordDate")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return record;	
	}


	@Override
	public List<Record> findByPage(int rows, int page, String condition) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<Record> recordList = new ArrayList<>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from record "+condition+" limit ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.setRecordId(rs.getInt("recordId"));
				record.setOwnerName(rs.getString("ownerName"));
				record.setOwnerTelphone(rs.getString("ownerTelphone"));
				record.setRecordType(rs.getString("recordType"));
				record.setRecordMoney(rs.getDouble("recordMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				record.setRecordDate(sdf.format(rs.getTimestamp("recordDate")));
				recordList.add(record);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return recordList;
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
			String sql = "select count(*) from record "+condition;
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
	public List<Record> getRecordByName(String ownerName) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<Record> recordList = new ArrayList<>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from record where ownerName=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, ownerName);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.setRecordId(rs.getInt("recordId"));
				record.setOwnerName(rs.getString("ownerName"));
				record.setOwnerTelphone(rs.getString("ownerTelphone"));
				record.setRecordType(rs.getString("recordType"));
				record.setRecordMoney(rs.getDouble("recordMoney"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				record.setRecordDate(sdf.format(rs.getTimestamp("recordDate")));
				recordList.add(record);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return recordList;
	}
}
