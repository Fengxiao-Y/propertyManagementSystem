package com.yidu.focus.ll.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.ll.dao.CarTimeDao;
import com.yidu.focus.ll.domain.CarTime;
/**
 * 
 * ���ܣ���ʱͣ�� 
 * ���ߣ�����
 * ���ڣ�2019��10��31������9:13:22
 * �汾��1.0
 */
public class CarTimeDaoImpl implements CarTimeDao {

	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param carTime ����ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int add(CarTime carTime) {
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
			String sql="INSERT INTO carTime(carId,goTime,"
					+ "toTime,Money,empNo) VALUES(?,?,?,?,?)";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, carTime.getCarId());
			pstmt.setString(2, carTime.getGoTime());
			pstmt.setString(3, carTime.getToTime());
			pstmt.setDouble(4, carTime.getMoney());
			pstmt.setString(5, carTime.getEmpNo());
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
	 * ������ţ�������ɾ������
	 * @param Nub �ļ����
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int delete(int Nub) {
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
			String sql="delete from carTime where Nub=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, Nub);
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
	 * ������ʱ��ʵ���������µ����ݿ�
	 * @param carTime ����ʱ��ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int update(CarTime carTime) {
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
			String sql="update carTime set carId=?,goTime=?,toTime=?,"
					+ "Money=?, empNo=? where Nub=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ			
			pstmt.setString(1, carTime.getCarId());
			pstmt.setString(2, carTime.getGoTime());
			pstmt.setString(3, carTime.getToTime());
			pstmt.setDouble(4, carTime.getMoney());
			pstmt.setString(5, carTime.getEmpNo());
			pstmt.setInt(6, carTime.getNub());
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
	 * ������ţ������������ݿ���в�������
	 * @param Nub ���
	 * @return ����ʱ��ʵ�����
	 */
	@Override
	public CarTime findById(int Nub) {
		//��������
		CarTime carTime=null;
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
			String sql="select Nub,carId,goTime,toTime,Money,empNo from carTime where Nub=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, Nub);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ��������
				carTime=new CarTime();
				//������������ݱ��浽������
				carTime.setNub(rs.getInt("Nub"));
				carTime.setCarId(rs.getString("carId"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carTime.setGoTime(sdf.format(rs.getDate("goTime")));
				
				carTime.setToTime(sdf.format(rs.getDate("toTime")));
				carTime.setMoney(rs.getDouble("Money"));
				carTime.setEmpNo(rs.getString("empNo"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return carTime;
	}

	/**
	 * ��ѯcarTime�������г�����Ϣ������װ�ɳ�����Ϣ���Ϸ���
	 * @return ������Ϣ����
	 */
	@Override
	public List<CarTime> findAll() {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����������
		PreparedStatement pstmt=null;
		//������������� 
		ResultSet rs=null;
		//����һ������������� 
		List<CarTime> carTimeList=new ArrayList<CarTime>();

		try {
			//1:ʵ�������Ӷ���
			conn=JdbcUtils.getConnection();
			//2:A:����Ҫִ�е�SQL����ַ���
			String sql="select  Nub,carId,goTime,toTime,Money,empNo from carTime";
			//2:B:����SQL��������ݿ����ʵ����������
			pstmt=conn.prepareStatement(sql);
			//3:ִ�������󣬵õ����������
			rs=pstmt.executeQuery();
			//4:�Խ�������б���������������м�¼��ת���ɳ������󣬲���ӵ�������
			while(rs.next()){
				//�������
				CarTime carTime=new CarTime();
				//������������ݱ��浽������
				carTime.setNub(rs.getInt("Nub"));
				carTime.setCarId(rs.getString("carId"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carTime.setGoTime(sdf.format(rs.getTimestamp("goTime")));
				carTime.setToTime(sdf.format(rs.getTimestamp("toTime")));
				carTime.setMoney(rs.getDouble("Money"));
				carTime.setEmpNo(rs.getString("empNo"));
				//�����Ŷ�����ӵ�����������
				carTimeList.add(carTime);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���س�����Ϣ����
		return carTimeList;
	}


	/**
	 * ��ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<CarTime> findByPage(int rows, int page) {
		//������󼯺�
		List<CarTime> carTimeList=new ArrayList<CarTime>();
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
			String sql="select * from carTime limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//�������
				CarTime carTime=new CarTime();
				//������������ݱ��浽������
				carTime.setNub(rs.getInt("Nub"));
				carTime.setCarId(rs.getString("carId"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carTime.setGoTime(sdf.format(rs.getTimestamp("goTime")));
				carTime.setToTime(sdf.format(rs.getTimestamp("toTime")));
				carTime.setMoney(rs.getDouble("Money"));
				carTime.setEmpNo(rs.getString("empNo"));
				//������������ӵ�������
				carTimeList.add(carTime);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return carTimeList;
	}

	/**
	 * ���ض�������ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @param condition ��ѯ����
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<CarTime> findByPage(int rows, int page, String condition) {
		//���弯�϶���
		List<CarTime> carTimeList=new ArrayList<CarTime>();
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
			String sql="select * from carTime "+condition+" limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����еĲ�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//�������
				CarTime carTime=new CarTime();
				//������������ݱ��浽������
				carTime.setNub(rs.getInt("Nub"));
				carTime.setCarId(rs.getString("carId"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carTime.setGoTime(sdf.format(rs.getTimestamp("goTime")));
				carTime.setToTime(sdf.format(rs.getTimestamp("toTime")));
				carTime.setMoney(rs.getDouble("Money"));
				carTime.setEmpNo(rs.getString("empNo"));
				//������������ӵ�������
				carTimeList.add(carTime);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return carTimeList;
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
			String sql="select count(*) from carTime";
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
			//����sql���
			String sql="select count(*) from carTime "+condition;
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
