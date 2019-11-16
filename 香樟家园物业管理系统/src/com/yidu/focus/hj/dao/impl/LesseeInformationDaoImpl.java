package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.LesseeInformationDao;
import com.yidu.focus.hj.domain.LesseeInformation;
/**
 * 
 * ���ܣ��⻧��Ϣ��
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��31������6:17:30
 */
public class LesseeInformationDaoImpl implements LesseeInformationDao {

	@Override
	public int add(LesseeInformation lesseeInformation) {
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
			String sql = "insert into lesseeInformation(houseId,lesseeName,lesseeGender,lesseeIdcard,lesseeTelphone,startTime,endTime) values(?,?,?,?,?,?,?)";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, lesseeInformation.getHouseId());
			pstmt.setString(2, lesseeInformation.getLesseeName());
			pstmt.setString(3, lesseeInformation.getLesseeGender());
			pstmt.setString(4, lesseeInformation.getLesseeIdcard());
			pstmt.setString(5, lesseeInformation.getLesseeTelphone());
			pstmt.setString(6, lesseeInformation.getStartTime());
			pstmt.setString(7, lesseeInformation.getEndTime());
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
	public int update(LesseeInformation lesseeInformation) {
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
			String sql = "update lesseeInformation set houseId=?,lesseeName=?,lesseeGender=?,lesseeIdcard=?,lesseeTelphone=?,startTime=?,endTime=? where leaseContractId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, lesseeInformation.getHouseId());
			pstmt.setString(2, lesseeInformation.getLesseeName());
			pstmt.setString(3, lesseeInformation.getLesseeGender());
			pstmt.setString(4, lesseeInformation.getLesseeIdcard());
			pstmt.setString(5, lesseeInformation.getLesseeTelphone());
			pstmt.setString(6, lesseeInformation.getStartTime());
			pstmt.setString(7, lesseeInformation.getEndTime());
			pstmt.setInt(8, lesseeInformation.getLeaseContractId());
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
	public int delete(int leaseContractId) {
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
			String sql = "delete from lesseeInformation where leaseContractId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, leaseContractId);
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
	public LesseeInformation getLesseeInformationById(String houseId) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		//����һ���⻧����
		LesseeInformation lesseeInformation = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����һ��sqlִ�����
			String sql = "select * from lesseeInformation where houseId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, houseId);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				//ʵ�����⻧����
				lesseeInformation = new LesseeInformation();
				//�����ݱ��浽������
				lesseeInformation.setLeaseContractId(rs.getInt("leaseContractId"));
				lesseeInformation.setHouseId(rs.getString("houseId"));
				lesseeInformation.setLesseeName(rs.getString("lesseeName"));
				lesseeInformation.setLesseeGender(rs.getString("lesseeGender"));
				lesseeInformation.setLesseeIdcard(rs.getString("lesseeIdcard"));
				lesseeInformation.setLesseeTelphone(rs.getString("lesseeTelphone"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				lesseeInformation.setStartTime(sdf.format(rs.getDate("startTime")));
				lesseeInformation.setEndTime(sdf.format(rs.getDate("endTime")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return lesseeInformation;
	}

	@Override
	public List<LesseeInformation> selectByPage(int rows, int page) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		//����һ���⻧����
		List<LesseeInformation> lesseeInformationList = new ArrayList<LesseeInformation>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����һ��sqlִ�����
			String sql = "select * from lesseeInformation limit ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				//ʵ�����⻧����
				LesseeInformation lesseeInformation = new LesseeInformation();
				//�����ݱ��浽������
				lesseeInformation.setLeaseContractId(rs.getInt("leaseContractId"));
				lesseeInformation.setHouseId(rs.getString("houseId"));
				lesseeInformation.setLesseeName(rs.getString("lesseeName"));
				lesseeInformation.setLesseeGender(rs.getString("lesseeGender"));
				lesseeInformation.setLesseeIdcard(rs.getString("lesseeIdcard"));
				lesseeInformation.setLesseeTelphone(rs.getString("lesseeTelphone"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				lesseeInformation.setStartTime(sdf.format(rs.getDate("startTime")));
				lesseeInformation.setEndTime(sdf.format(rs.getDate("endTime")));
				lesseeInformationList.add(lesseeInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return lesseeInformationList;
	}

	@Override
	public List<LesseeInformation> findByPage(int rows, int page, String condition) {
		
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		//����һ���⻧����
		List<LesseeInformation> lesseeInformationList = new ArrayList<LesseeInformation>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����һ��sqlִ�����
			String sql = "select * from lesseeInformation "+condition+" limit ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				//ʵ�����⻧����
				LesseeInformation lesseeInformation = new LesseeInformation();
				//�����ݱ��浽������
				lesseeInformation.setLeaseContractId(rs.getInt("leaseContractId"));
				lesseeInformation.setHouseId(rs.getString("houseId"));
				lesseeInformation.setLesseeName(rs.getString("lesseeName"));
				lesseeInformation.setLesseeGender(rs.getString("lesseeGender"));
				lesseeInformation.setLesseeIdcard(rs.getString("lesseeIdcard"));
				lesseeInformation.setLesseeTelphone(rs.getString("lesseeTelphone"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				lesseeInformation.setStartTime(sdf.format(rs.getDate("startTime")));
				lesseeInformation.setEndTime(sdf.format(rs.getDate("endTime")));
				lesseeInformationList.add(lesseeInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return lesseeInformationList;
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
			String sql = "select count(*) from lesseeInformation ";
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
			String sql = "select count(*) from lesseeInformation "+condition;
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
	public int deleteA(String houseId) {
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
			String sql = "delete from lesseeInformation where houseId=?";
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

}
