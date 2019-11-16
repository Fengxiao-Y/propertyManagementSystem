package com.yidu.focus.hxl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hxl.dao.EquipmentInformationDao;
import com.yidu.focus.hxl.domain.EquipmentInformation;
/**
 * 
 * ���ܣ��豸������Ϣ�� 
 * ���ߣ���ϣ��
 * ���ڣ�2019��10��31������9:03:52
 * �汾��1.0
 */
public class EquipmentInformationDaoImpl implements EquipmentInformationDao {
	 /**
     * �����Ϣ
     * @param attendance
     * @return
     */
	@Override
	public int add(EquipmentInformation EquipmentInformation) {
		//����Ӱ����������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//����sql����ַ���		
			String sql="INSERT INTO EquipmentInformation(eqId,eqName,eqPurpose,manufacturer,"
					+ "producedDate,eqNum,usePosition,eqStatus) VALUES(?,?,?,?,?,?,?,?)";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, EquipmentInformation.getEqId());
			pstmt.setString(2, EquipmentInformation.getEqName());
			pstmt.setString(3, EquipmentInformation.getEqPurpose());
			pstmt.setString(4, EquipmentInformation.getManufacturer());
			pstmt.setString(5, EquipmentInformation.getProducedDate());
			pstmt.setInt(6, EquipmentInformation.getEqNum());
			pstmt.setString(7, EquipmentInformation.getUsePosition());
			pstmt.setString(8, EquipmentInformation.getEqStatus());
			//ִ��Ԥ���������󣬵õ�Ӱ������
			rows=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		//����Ӱ������
		return rows;
	}
	/**
	 * ɾ��
	 * @param attendId
	 * @return
	 */
	@Override
	public int deleteById(String eqId) {
		//����Ӱ����������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//����sql����ַ���		
			String sql="delete from EquipmentInformation where eqId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, eqId);
			//ִ��Ԥ���������󣬵õ�Ӱ������
			rows=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		//����Ӱ������
		return rows;
	}
	/**
	 * �޸�����
	 * @param attendId
	 * @return
	 */
	@Override
	public int update(EquipmentInformation EquipmentInformation) {
		//����Ӱ����������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//����sql����ַ���		
			String sql="update EquipmentInformation set eqName=?,eqPurpose=?,manufacturer=?,"
					+ "producedDate=?,eqNum=?,usePosition=?,eqStatus=? where eqId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ			
			
			pstmt.setString(1, EquipmentInformation.getEqName());
			pstmt.setString(2, EquipmentInformation.getEqPurpose());
			pstmt.setString(3, EquipmentInformation.getManufacturer());
			pstmt.setString(4, EquipmentInformation.getProducedDate());
			pstmt.setInt(5, EquipmentInformation.getEqNum());
			pstmt.setString(6, EquipmentInformation.getUsePosition());
			pstmt.setString(7, EquipmentInformation.getEqStatus());
			pstmt.setString(8, EquipmentInformation.getEqId());
			//ִ��Ԥ���������󣬵õ�Ӱ������
			rows=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		//����Ӱ������
		return rows;
	}
	/**
	 * ���ݿ��ڱ�Ų��ҵ���Ա����Ϣ
	 * @param attendId
	 * @return
	 */
	@Override
	public EquipmentInformation findById(String eqId) {
		//�����ͻ�����
		EquipmentInformation EquipmentInformation=null;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//����sql�ַ���
			String sql="select eqId,eqName,eqPurpose,manufacturer,producedDate,"
					+ "eqNum,usePosition,eqStatus from EquipmentInformation where eqId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, eqId);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�����ͻ�����
				EquipmentInformation=new EquipmentInformation();
				//������������ݱ��浽�ͻ�������
				EquipmentInformation.setEqId(rs.getString("eqId"));
				EquipmentInformation.setEqName(rs.getString("eqName"));
				EquipmentInformation.setEqPurpose(rs.getString("eqPurpose"));
				EquipmentInformation.setManufacturer(rs.getString("manufacturer"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				EquipmentInformation.setProducedDate(sdf.format(rs.getDate("producedDate")));
				EquipmentInformation.setEqNum(rs.getInt("eqNum"));
				EquipmentInformation.setUsePosition(rs.getString("usePosition"));
				EquipmentInformation.setEqStatus(rs.getString("eqStatus"));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return EquipmentInformation;
	}
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	@Override
	public List<EquipmentInformation> findByPage(int rows, int page) {
		//����ͻ����󼯺�
		List<EquipmentInformation> equipmentList=new ArrayList<EquipmentInformation>();
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//��������Ӷ���
		ResultSet rs=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//����sql���
			String sql="select * from EquipmentInformation limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//����ͻ�����
				EquipmentInformation EquipmentInformation=new EquipmentInformation();
				//������������ݱ��浽�ͻ�������
				EquipmentInformation.setEqId(rs.getString("eqId"));
				EquipmentInformation.setEqName(rs.getString("eqName"));
				EquipmentInformation.setEqPurpose(rs.getString("eqPurpose"));
				EquipmentInformation.setManufacturer(rs.getString("manufacturer"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				EquipmentInformation.setProducedDate(sdf.format(rs.getDate("producedDate")));
				EquipmentInformation.setEqNum(rs.getInt("eqNum"));
				EquipmentInformation.setUsePosition(rs.getString("usePosition"));
				EquipmentInformation.setEqStatus(rs.getString("eqStatus"));
				//���ͻ�������ӵ��ͻ�������
				equipmentList.add(EquipmentInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return equipmentList;
	}
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	@Override
	public List<EquipmentInformation> findByPage(int rows, int page, String condition) {
		//����ͻ����϶���
		List<EquipmentInformation> equipmentList=new ArrayList<EquipmentInformation>();
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//����sql�ַ���
			String sql="select * from EquipmentInformation "+condition+" limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����еĲ�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//����ͻ�����
				EquipmentInformation EquipmentInformation=new EquipmentInformation();
				//��������е����ݱ��浽�ͻ�������
				EquipmentInformation.setEqId(rs.getString("eqId"));
				EquipmentInformation.setEqName(rs.getString("eqName"));
				EquipmentInformation.setEqPurpose(rs.getString("eqPurpose"));
				EquipmentInformation.setManufacturer(rs.getString("manufacturer"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				EquipmentInformation.setProducedDate(sdf.format(rs.getDate("producedDate")));
				EquipmentInformation.setEqNum(rs.getInt("eqNum"));
				EquipmentInformation.setUsePosition(rs.getString("usePosition"));
				EquipmentInformation.setEqStatus(rs.getString("eqStatus"));
				//��������ӵ��ͻ�������
				equipmentList.add(EquipmentInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return equipmentList;
	}
	/**
	 * ͳ�Ƽ�¼��
	 * @return �������м�¼����
	 */
	@Override
	public int count() {
		//����Ӱ����������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//����sql�ַ���
			String sql="select count(*) from EquipmentInformation";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//�жϽ����
			if(rs.next()){
				rows=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//��������
		return rows;
	}
	/**
	 * ͳ�Ʒ��������ļ�¼��
	 * @param condition ����
	 * @return
	 */
	@Override
	public int count(String condition) {
		//����Ӱ����������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//����sql���
			String sql="select count(*) from EquipmentInformation "+condition;
			//ʹ�����ݿ����Ӷ��� sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��Ԥ����������õ������
			rs=pstmt.executeQuery();
			//�жϽ����
			if(rs.next()){
				rows=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//��������
		return rows;
	}

}
