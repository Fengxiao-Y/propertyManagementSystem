package com.yidu.focus.hxl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hxl.dao.CarInformationDao;
import com.yidu.focus.hxl.domain.CarInformation;

/**
 * 
 * ���ܣ�������Ϣ 
 * ���ߣ���ϡ�� 
 * ���ڣ�2019��10��31������9:02:32 
 * �汾��1.0
 */
public class CarInformationDaoImpl implements CarInformationDao {
	/**
     * �����Ϣ
     * @param carInformation
     * @return
     */
	@Override
	public int add(CarInformation carInformation) {
		// ����Ӱ����������
		int rows = 0;
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql����ַ��� carId,carName,carTelphone,carportId carInformation
			String sql = "INSERT INTO carInformation(carId,carName,carTelphone,carportId) VALUES(?,?,?,?)";
			// ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ��sql�ַ����в�����ֵ
			pstmt.setString(1, carInformation.getCarId());
			pstmt.setString(2, carInformation.getCarName());
			pstmt.setString(3, carInformation.getCarTelphone());
			pstmt.setString(4, carInformation.getCarportId());
			// ִ��Ԥ���������󣬵õ�Ӱ������
			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		// ����Ӱ������
		return rows;
	}
	/**
	 * ɾ��
	 * @param carId
	 * @return
	 */
	@Override
	public int deleteById(String carId) {
		// ����Ӱ����������
		int rows = 0;
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql����ַ��� carId,carName,carTelphone,carportId carInformation
			String sql = "delete from carInformation where carId=?";
			// ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ��sql�ַ����в�����ֵ
			pstmt.setString(1, carId);
			// ִ��Ԥ���������󣬵õ�Ӱ������
			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		// ����Ӱ������
		return rows;
	}
	/**
	 * �޸�����
	 * @param carInformation
	 * @return
	 */
	@Override
	public int update(CarInformation carInformation) {
		// ����Ӱ����������
		int rows = 0;
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql����ַ��� carId,carName,carTelphone,carportId carInformation
			String sql = "update carInformation set carName=?,carTelphone=?,carportId=? where carId=?";
			// ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ��sql�ַ����в�����ֵ

			pstmt.setString(1, carInformation.getCarName());
			pstmt.setString(2, carInformation.getCarTelphone());
			pstmt.setString(3, carInformation.getCarportId());
			pstmt.setString(4, carInformation.getCarId());
			// ִ��Ԥ���������󣬵õ�Ӱ������
			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		// ����Ӱ������
		return rows;
	}
	/**
	 * ���ݿ��ڱ�Ų��ҵ���Ա����Ϣ
	 * @param carId
	 * @return
	 */
	@Override
	public CarInformation findById(String carId) {
		// �����ͻ�����
		CarInformation carInformation = null;
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		// �������������
		ResultSet rs = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql�ַ��� carId,carName,carTelphone,carportId carInformation
			String sql = "select carId,carName,carTelphone,carportId from carInformation where carId=?";
			// ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ��sql�ַ����в�����ֵ
			pstmt.setString(1, carId);
			// ִ��Ԥ���������󣬵õ������
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// ʵ�����ͻ�����
				carInformation = new CarInformation();
				// ������������ݱ��浽�ͻ�������
				carInformation.setCarId(rs.getString("carId"));
				carInformation.setCarName(rs.getString("carName"));
				carInformation.setCarTelphone(rs.getString("carTelphone"));
				carInformation.setCarportId(rs.getString("carportId"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		// ���ض���
		return carInformation;
	}
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����г�����Ϣ����
	 */
	@Override
	public List<CarInformation> findByPage(int rows, int page) {
		// ����ͻ����󼯺�
		List<CarInformation> carInformationList = new ArrayList<CarInformation>();
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		// ��������Ӷ���
		ResultSet rs = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql��� carId,carName,carTelphone,carportId carInformation
			String sql = "select * from carInformation limit ?,?";
			// ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page - 1) * rows);
			pstmt.setInt(2, rows);
			// ִ��Ԥ���������󣬵õ������
			rs = pstmt.executeQuery();
			// ʹ��ѭ����������
			while (rs.next()) {
				// ����ͻ�����
				CarInformation carInformation = new CarInformation();
				// ������������ݱ��浽�ͻ�������
				carInformation.setCarId(rs.getString("carId"));
				carInformation.setCarName(rs.getString("carName"));
				carInformation.setCarTelphone(rs.getString("carTelphone"));
				carInformation.setCarportId(rs.getString("carportId"));
				// ���ͻ�������ӵ��ͻ�������
				carInformationList.add(carInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		// ���ؿͻ�����
		return carInformationList;
	}
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	@Override
	public List<CarInformation> findByPage(int rows, int page, String condition) {
		// ����ͻ����϶���
		List<CarInformation> carInformationList = new ArrayList<CarInformation>();
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		// �������������
		ResultSet rs = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql�ַ��� carId,carName,carTelphone,carportId carInformation
			String sql = "select * from carInformation " + condition + " limit ?,?";
			// ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ��sql�ַ����еĲ�����ֵ
			pstmt.setInt(1, (page - 1) * rows);
			pstmt.setInt(2, rows);
			// ִ��Ԥ���������󣬵õ������
			rs = pstmt.executeQuery();
			// ʹ��ѭ����������
			while (rs.next()) {
				// ����ͻ�����
				CarInformation carInformation = new CarInformation();
				// ��������е����ݱ��浽�ͻ�������
				carInformation.setCarId(rs.getString("carId"));
				carInformation.setCarName(rs.getString("carName"));
				carInformation.setCarTelphone(rs.getString("carTelphone"));
				carInformation.setCarportId(rs.getString("carportId"));
				// ��������ӵ��ͻ�������
				carInformationList.add(carInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		// ���ؿͻ�����
		return carInformationList;
	}
	/**
	 * ͳ�Ƽ�¼��
	 * @return �������м�¼����
	 */
	@Override
	public int count() {
		// ����Ӱ����������
		int rows = 0;
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		// �������������
		ResultSet rs = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql�ַ��� carId,carName,carTelphone,carportId carInformation
			String sql = "select count(*) from carInformation";
			// ʹ�����ݿ����Ӷ���sql�ַ���ʵ����������
			pstmt = conn.prepareStatement(sql);
			// ִ��Ԥ���������󣬵õ������
			rs = pstmt.executeQuery();
			// �жϽ����
			if (rs.next()) {
				rows = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		// ��������
		return rows;
	}
	/**
	 * ͳ�Ʒ��������ļ�¼��
	 * @param condition ����
	 * @return
	 */
	@Override
	public int count(String condition) {
		// ����Ӱ����������
		int rows = 0;
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		// �������������
		ResultSet rs = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql��� carId,carName,carTelphone,carportId carInformation
			String sql = "select count(*) from carInformation " + condition;
			// ʹ�����ݿ����Ӷ��� sql�ַ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ִ��Ԥ����������õ������
			rs = pstmt.executeQuery();
			// �жϽ����
			if (rs.next()) {
				rows = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		// ��������
		return rows;
	}

}
