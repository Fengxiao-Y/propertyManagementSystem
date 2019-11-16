package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.OwnerInformationDao;
import com.yidu.focus.hj.domain.OwnerInformation;
/**
 * 
 * ���ܣ�ҵ����Ϣ
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��31������8:53:23
 */
public class OwnerInformationDaoImpl implements OwnerInformationDao {
	/**
	 * �������
	 * @param OtherServiceIncome ҵ������
	 * @return Ӱ������
	 */
	@Override
	public int add(OwnerInformation ownerInformation) {
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
			String sql = "insert into ownerInformation values(?,?,?,?,?,?,?,?)";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, ownerInformation.getOwnerId());
			pstmt.setString(2, ownerInformation.getHouseId());
			pstmt.setString(3, ownerInformation.getOwnerName());
			pstmt.setString(4, ownerInformation.getOwnerSex());
			pstmt.setString(5, ownerInformation.getOwnerIdcard());
			pstmt.setString(6, ownerInformation.getOwnerTelphone());
			pstmt.setString(7, ownerInformation.getOwnerEmail());
			pstmt.setString(8, ownerInformation.getOwnerAddress());
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
	 * �޸�����
	 * @param OtherServiceIncome ����
	 * @return Ӱ������
	 */
	@Override
	public int update(OwnerInformation ownerInformation) {
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
			String sql = "update ownerInformation set houseId=?,ownerName=?,ownerSex=?,ownerIdcard=?,ownerTelphone=?,ownerEmail=?,ownerAddress=? where ownerId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, ownerInformation.getHouseId());
			pstmt.setString(2, ownerInformation.getOwnerName());
			pstmt.setString(3, ownerInformation.getOwnerSex());
			pstmt.setString(4, ownerInformation.getOwnerIdcard());
			pstmt.setString(5, ownerInformation.getOwnerTelphone());
			pstmt.setString(6, ownerInformation.getOwnerEmail());
			pstmt.setString(7, ownerInformation.getOwnerAddress());
			pstmt.setString(8, ownerInformation.getOwnerId());
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
	 * ɾ������
	 * @param osIcome ���
	 * @return Ӱ������
	 */
	@Override
	public int delete(String ownerId) {
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
			String sql = "delete from ownerInformation where ownerId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, ownerId);
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
	 * ����Ա��������ѯĳ������
	 * @param osIcome ���
	 * @return ����
	 */
	@Override
	public OwnerInformation getOwnerInformationById(String houseId) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		//����һ���洢���ݶ���
		OwnerInformation ownerInformation = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����һ��sqlִ��������
			String sql = "select * from ownerInformation houseId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, houseId);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				ownerInformation = new OwnerInformation();
				ownerInformation.setOwnerId(rs.getString("ownerId"));
				ownerInformation.setHouseId(rs.getString("houseId"));
				ownerInformation.setOwnerName(rs.getString("ownerName"));
				ownerInformation.setOwnerSex(rs.getString("ownerSex"));
				ownerInformation.setOwnerIdcard(rs.getString("ownerIdcard"));
				ownerInformation.setOwnerTelphone(rs.getString("ownerTelphone"));
				ownerInformation.setOwnerEmail(rs.getString("ownerEmail"));
				ownerInformation.setOwnerAddress(rs.getString("ownerAddress"));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			JdbcUtils.close(rs, pstmt, conn);
		}
		return ownerInformation;
	}
	/**
	 * ��ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @return ���󼯺�
	 */
	@Override
	public List<OwnerInformation> selectByPage(int rows, int page) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		//����һ�����󼯺�
		List<OwnerInformation> ownerInformationList = new ArrayList<OwnerInformation>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����һ��sqlִ��������
			String sql="select * from ownerInformation limit ?,?";
			//ʵ���������� 
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				OwnerInformation ownerInformation = new OwnerInformation();
				ownerInformation.setOwnerId(rs.getString("ownerId"));
				ownerInformation.setHouseId(rs.getString("houseId"));
				ownerInformation.setOwnerName(rs.getString("ownerName"));
				ownerInformation.setOwnerSex(rs.getString("ownerSex"));
				ownerInformation.setOwnerIdcard(rs.getString("ownerIdcard"));
				ownerInformation.setOwnerTelphone(rs.getString("ownerTelphone"));
				ownerInformation.setOwnerEmail(rs.getString("ownerEmail"));
				ownerInformation.setOwnerAddress(rs.getString("ownerAddress"));
				ownerInformationList.add(ownerInformation);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return ownerInformationList;
	}
	/**
	 * ��������ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @param condition ����
	 * @return ���϶���
	 */
	@Override
	public List<OwnerInformation> findByPage(int rows, int page, String condition) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		//����һ�����󼯺�
		List<OwnerInformation> ownerInformationList = new ArrayList<OwnerInformation>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����һ��sqlִ��������
			String sql="select * from ownerInformation "+condition+" limit ?,?";
			//ʵ���������� 
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				OwnerInformation ownerInformation = new OwnerInformation();
				ownerInformation.setOwnerId(rs.getString("ownerId"));
				ownerInformation.setHouseId(rs.getString("houseId"));
				ownerInformation.setOwnerName(rs.getString("ownerName"));
				ownerInformation.setOwnerSex(rs.getString("ownerSex"));
				ownerInformation.setOwnerIdcard(rs.getString("ownerIdcard"));
				ownerInformation.setOwnerTelphone(rs.getString("ownerTelphone"));
				ownerInformation.setOwnerEmail(rs.getString("ownerEmail"));
				ownerInformation.setOwnerAddress(rs.getString("ownerAddress"));
				ownerInformationList.add(ownerInformation);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return ownerInformationList;
	}
	/**
	 * ͳ�Ƽ�¼��
	 * @return ��¼����
	 */
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
			String sql = "select count(*) from ownerInformation ";
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
	 * ������ͳ�Ƽ�¼��
	 * @param condition ����
	 * @return ɸѡ��¼����
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
			String sql = "select count(*) from ownerInformation "+condition;
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
	public int deleteName(String name) {
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
			String sql = "delete from ownerInformation where ownerName=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, name);
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
	 * ���ݷ��ݱ�Ų�ѯĳ������
	 * @param wageid ���
	 * @return ����
	 */
	@Override
	public OwnerInformation findOwnerInformationByName(String ownerName) {
		//�������ݿ����Ӷ���
	Connection conn = null;
	//����������
	PreparedStatement pstmt = null;
	//�������������
	ResultSet rs = null;
	//����һ���洢���ݶ���
	OwnerInformation ownerInformation = null;
	try {
		//ʵ�������ݿ����Ӷ���
		conn = JdbcUtils.getConnection();
		//����һ��sqlִ��������
		String sql = "select * from ownerInformation ownerName=?";
		//ʵ����������
		pstmt = conn.prepareStatement(sql);
		//��sql��丳ֵ
		pstmt.setString(1, ownerName);
		//ʵ�������������
		rs = pstmt.executeQuery();
		if(rs.next()){
			ownerInformation = new OwnerInformation();
			ownerInformation.setOwnerId(rs.getString("ownerId"));
			ownerInformation.setHouseId(rs.getString("houseId"));
			ownerInformation.setOwnerName(rs.getString("ownerName"));
			ownerInformation.setOwnerSex(rs.getString("ownerSex"));
			ownerInformation.setOwnerIdcard(rs.getString("ownerIdcard"));
			ownerInformation.setOwnerTelphone(rs.getString("ownerTelphone"));
			ownerInformation.setOwnerEmail(rs.getString("ownerEmail"));
			ownerInformation.setOwnerAddress(rs.getString("ownerAddress"));
		}
		
	} catch (SQLException e) {
		throw new RuntimeException(e.getMessage(),e);
	} finally {
		JdbcUtils.close(rs, pstmt, conn);
	}
		return ownerInformation;
	}

}
