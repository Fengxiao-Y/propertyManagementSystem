package com.yidu.focus.ll.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.ll.dao.ParkingInformationDao;
import com.yidu.focus.ll.domain.ParkingInformation;
/**
 * 
 * ���ܣ���λ��Ϣ 
 * ���ߣ�����
 * ���ڣ�2019��10��31������9:14:30
 * �汾��1.0
 */
public class ParkingInformationDaoImpl implements ParkingInformationDao {

	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param ParkingInformation ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int add(ParkingInformation parkingInformation) {
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
			String sql="INSERT INTO ParkingInformation(parkId,parkStatus,parkMoney,"
					+ "houseId) VALUES(?,?,?,?)";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, parkingInformation.getParkId());
			pstmt.setString(2, parkingInformation.getParkStatus());
			pstmt.setDouble(3, parkingInformation.getParkMoney());
			pstmt.setString(4, parkingInformation.getHouseId());
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
	 * ���ճ�λ��ţ�������ɾ������
	 * @param parkId ��λ���
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int delete(String parkId) {
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
			String sql="delete from parkingInformation where parkId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, parkId);
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
	 * ��ʵ���������µ����ݿ�
	 * @param parkingInformation ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int update(ParkingInformation parkingInformation) {
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
			String sql="update parkingInformation set parkStatus=?,"
					+ "parkMoney=?,houseId=? where parkId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ			
			
			pstmt.setString(1, parkingInformation.getParkStatus());
			pstmt.setDouble(2, parkingInformation.getParkMoney());
			pstmt.setString(3, parkingInformation.getHouseId());
			pstmt.setString(4, parkingInformation.getParkId());
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
	 * ���ճ�λ��Ϣ�������������ݿ���в�������
	 * @param parkId ��λ���
	 * @return ��λ��Ϣʵ�����
	 */
	@Override
	public ParkingInformation findById(String parkId) {
		//������λ��Ϣ����
		ParkingInformation parkingInformation=null;
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
			String sql="select parkId,parkStatus,parkMoney,houseId from ParkingInformation where parkId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, parkId);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�������Ŷ���
				parkingInformation=new ParkingInformation();
				//������������ݱ��浽���Ŷ�����
				parkingInformation.setParkId(rs.getString("parkId"));
				parkingInformation.setParkStatus(rs.getString("parkStatus"));
				parkingInformation.setParkMoney(rs.getDouble("parkMoney"));
				parkingInformation.setHouseId(rs.getString("houseId"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return parkingInformation;
	}

	/**
	 * ��ѯparkingInformation�������г�λ��Ϣ������װ�ɳ�λ��Ϣ���Ϸ���
	 * @return ��λ��Ϣ����
	 */
	@Override
	public List<ParkingInformation> findAll() {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����������
		PreparedStatement pstmt=null;
		//������������� 
		ResultSet rs=null;
		//����һ������������� 
		List<ParkingInformation> parkingInformationList=new ArrayList<ParkingInformation>();

		try {
			//1:ʵ�������Ӷ���
			conn=JdbcUtils.getConnection();
			//2:A:����Ҫִ�е�SQL����ַ���
			String sql="select  parkId,parkStatus,parkMoney,houseId from ParkingInformation";
			//2:B:����SQL��������ݿ����ʵ����������
			pstmt=conn.prepareStatement(sql);
			//3:ִ�������󣬵õ����������
			rs=pstmt.executeQuery();
			//4:�Խ�������б���������������м�¼��ת���ɳ�λ��Ϣ���󣬲���ӵ�������
			while(rs.next()){
				//�������
				ParkingInformation parkingInformation=new ParkingInformation();
				//������������ݱ��浽��λ��Ϣ������
				parkingInformation.setParkId(rs.getString("parkId"));
				parkingInformation.setParkStatus(rs.getString("parkStatus"));
				parkingInformation.setParkMoney(rs.getDouble("parkMoney"));
				parkingInformation.setHouseId(rs.getString("houseId"));
				//�����Ŷ�����ӵ����ż�����
				parkingInformationList.add(parkingInformation);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���س�λ��Ϣ����
		return parkingInformationList;

	}

	/**
	 * ��ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<ParkingInformation> findByPage(int rows, int page) {
		//���峵λ��Ϣ���󼯺�
		List<ParkingInformation> parkingInformationList=new ArrayList<ParkingInformation>();
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
			String sql="select * from ParkingInformation limit ?,?";
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
				ParkingInformation parkingInformation=new ParkingInformation();
				//������������ݱ��浽��λ��Ϣ������
				parkingInformation.setParkId(rs.getString("parkId"));
				parkingInformation.setParkStatus(rs.getString("parkStatus"));
				parkingInformation.setParkMoney(rs.getDouble("parkMoney"));
				parkingInformation.setHouseId(rs.getString("houseId"));
				//�����Ŷ�����ӵ����ż�����
				parkingInformationList.add(parkingInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return parkingInformationList;
	}

	/**
	 * ���ض�������ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @param condition ��ѯ����
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<ParkingInformation> findByPage(int rows, int page, String condition) {
		//���峵λ��Ϣ���󼯺�
		List<ParkingInformation> parkingInformationList=new ArrayList<ParkingInformation>();
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
			String sql="select * from ParkingInformation"+condition+"limit ?,?";
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
				ParkingInformation parkingInformation=new ParkingInformation();
				//������������ݱ��浽��λ��Ϣ������
				parkingInformation.setParkId(rs.getString("parkId"));
				parkingInformation.setParkStatus(rs.getString("parkStatus"));
				parkingInformation.setParkMoney(rs.getDouble("parkMoney"));
				parkingInformation.setHouseId(rs.getString("houseId"));
				//�����Ŷ�����ӵ����ż�����
				parkingInformationList.add(parkingInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return parkingInformationList;
	}

	/**
	 * ͳ�������ݿ�������ݵ�������
	 * @return ����������
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
			String sql="select count(*) from ParkingInformation";
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
	 * ���ض�����ͳ�������ݿ���з�����������������
	 * @param condition ͳ������
	 * @return ���ط�����������������
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
			//����sql�ַ���
			String sql="select count(*) from ParkingInformation"+condition;
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
	 * ���ճ�λ��Ϣ�������������ݿ���в�������
	 * @param parkId ��λ���
	 * @return ��λ��Ϣʵ�����
	 */
	@Override
	public ParkingInformation findByName(String houseId) {
		//������λ��Ϣ����
		ParkingInformation parkingInformation=null;
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
			String sql="select parkId,parkStatus,parkMoney,houseId from ParkingInformation where houseId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, houseId);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�������Ŷ���
				parkingInformation=new ParkingInformation();
				//������������ݱ��浽���Ŷ�����
				parkingInformation.setParkId(rs.getString("parkId"));
				parkingInformation.setParkStatus(rs.getString("parkStatus"));
				parkingInformation.setParkMoney(rs.getDouble("parkMoney"));
				parkingInformation.setHouseId(rs.getString("houseId"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return parkingInformation;
	}

}
