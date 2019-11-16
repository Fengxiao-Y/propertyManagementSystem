package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.HouseInformationDao;
import com.yidu.focus.hj.domain.HouseInformation;
/**
 * 
 * ���ܣ�������Ϣ��
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��31������8:53:23
 */
public class HouseInformationDaoImpl implements HouseInformationDao {
	/**
	 * �������
	 * @param houseInformation ����
	 * @return Ӱ������
	 */
	@Override
	public int add(HouseInformation houseInformation) {
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
			String sql = "insert into houseInformation values(?,?,?,?,?,?,?)";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, houseInformation.getHouseId());
			pstmt.setString(2, houseInformation.getOwnerName());
			pstmt.setString(3, houseInformation.getHouseType());
			pstmt.setDouble(4, houseInformation.getHouseArea());
			pstmt.setString(5, houseInformation.getHouseNature());
			pstmt.setString(6, houseInformation.getHouseState());
			pstmt.setString(7, houseInformation.getHouseAddress());
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
	 * @param houseInformation ����
	 * @return Ӱ������
	 */
	@Override
	public int update(HouseInformation houseInformation) {
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
			String sql = "update houseInformation set ownerName=?,houseType=?,houseArea=?,houseNature=?,houseState=?,houseAddress=? where houseId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, houseInformation.getOwnerName());
			pstmt.setString(2, houseInformation.getHouseType());
			pstmt.setDouble(3, houseInformation.getHouseArea());
			pstmt.setString(4, houseInformation.getHouseNature());
			pstmt.setString(5, houseInformation.getHouseState());
			pstmt.setString(6, houseInformation.getHouseAddress());
			pstmt.setString(7, houseInformation.getHouseId());
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
	 * @param houseId ���
	 * @return Ӱ������
	 */
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
			String sql = "delete from houseInformation where houseId=?";
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
	/**
	 * ���ݷ��ݱ�Ų�ѯĳ������
	 * @param houseId ���
	 * @return ����
	 */
	@Override
	public HouseInformation getHouseInformationById(String houseId) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		HouseInformation houseInformation = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from houseInformation where houseId=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, houseId);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				houseInformation = new HouseInformation();
				houseInformation.setHouseId(rs.getString("houseId"));
				houseInformation.setOwnerName(rs.getString("ownerName"));
				houseInformation.setHouseType(rs.getString("houseType"));
				houseInformation.setHouseArea(rs.getDouble("houseArea"));
				houseInformation.setHouseNature(rs.getString("houseNature"));
				houseInformation.setHouseState(rs.getString("houseState"));
				houseInformation.setHouseAddress(rs.getString("houseAddress"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return houseInformation;
	}
	/**
	 * ��ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @return ���󼯺�
	 */
	@Override
	public List<HouseInformation> selectByPage(int rows, int page) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<HouseInformation> houseInformationList = new ArrayList<HouseInformation>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from houseInformation limit ?,? ";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				HouseInformation houseInformation = new HouseInformation();
				houseInformation.setHouseId(rs.getString("houseId"));
				houseInformation.setOwnerName(rs.getString("ownerName"));
				houseInformation.setHouseType(rs.getString("houseType"));
				houseInformation.setHouseArea(rs.getDouble("houseArea"));
				houseInformation.setHouseNature(rs.getString("houseNature"));
				houseInformation.setHouseState(rs.getString("houseState"));
				houseInformation.setHouseAddress(rs.getString("houseAddress"));
				houseInformationList.add(houseInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return houseInformationList;
	}
	/**
	 * ��������ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @param condition ����
	 * @return ���϶���
	 */
	@Override
	public List<HouseInformation> findByPage(int rows, int page, String condition) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		List<HouseInformation> houseInformationList = new ArrayList<HouseInformation>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from houseInformation "+condition+" limit ?,? ";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				HouseInformation houseInformation = new HouseInformation();
				houseInformation.setHouseId(rs.getString("houseId"));
				houseInformation.setOwnerName(rs.getString("ownerName"));
				houseInformation.setHouseType(rs.getString("houseType"));
				houseInformation.setHouseArea(rs.getDouble("houseArea"));
				houseInformation.setHouseNature(rs.getString("houseNature"));
				houseInformation.setHouseState(rs.getString("houseState"));
				houseInformation.setHouseAddress(rs.getString("houseAddress"));
				houseInformationList.add(houseInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return houseInformationList;
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
			String sql = "select count(*) from houseInformation ";
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
			String sql = "select count(*) from houseInformation "+condition;
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
	 * ����������ѯĳ������
	 * @param ownerName ҵ������
	 * @return ����
	 */
	@Override
	public HouseInformation getHouseInformationByName(String ownerName) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		HouseInformation houseInformation = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from houseInformation where ownerName=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, ownerName);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				houseInformation = new HouseInformation();
				houseInformation.setHouseId(rs.getString("houseId"));
				houseInformation.setOwnerName(rs.getString("ownerName"));
				houseInformation.setHouseType(rs.getString("houseType"));
				houseInformation.setHouseArea(rs.getDouble("houseArea"));
				houseInformation.setHouseNature(rs.getString("houseNature"));
				houseInformation.setHouseState(rs.getString("houseState"));
				houseInformation.setHouseAddress(rs.getString("houseAddress"));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		
		return houseInformation;
	}
	@Override
	public int findIdCard(String name) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		int rows = 0;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ������ַ���
			String sql="select * from houseInformation wherer ownerName=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, name);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				rows++;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return rows;
	}

}
