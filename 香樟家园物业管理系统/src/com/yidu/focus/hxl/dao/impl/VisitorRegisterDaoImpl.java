package com.yidu.focus.hxl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hxl.dao.VisitorRegisterDao;
import com.yidu.focus.hxl.domain.VisitorRegister;
/**
 * 
 * ���ܣ����õǼǱ� 
 * ���ߣ���ϣ��
 * ���ڣ�2019��10��31������9:04:47
 * �汾��1.0
 */
public class VisitorRegisterDaoImpl implements VisitorRegisterDao {
	 /**
     * �����Ϣ
     * @param visitorRegister
     * @return
     */
	@Override
	public int add(VisitorRegister visitorRegister) {
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
			
			String sql="INSERT INTO visitorRegister(visitorName,visitorGender,"
					+ "visitorIdNum,visitorTelphone,vistiAddress,vistiReason,"
					+ "cometime,leaveTime,empNo) VALUES(?,?,?,?,?,?,?,?,?)";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			
			pstmt.setString(1, visitorRegister.getVisitorName());
			pstmt.setString(2, visitorRegister.getVisitorGender());
			pstmt.setString(3, visitorRegister.getVisitorIdNum());
			pstmt.setString(4, visitorRegister.getVisitorTelphone());
			pstmt.setString(5, visitorRegister.getVistiAddress());
			pstmt.setString(6, visitorRegister.getVistiReason());
			//���ַ���ת�������ݿ���������
			pstmt.setString(7, visitorRegister.getCometime());
			pstmt.setString(8, visitorRegister.getLeaveTime());
			pstmt.setString(9, visitorRegister.getEmpNo());
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
	 * @param visitorId
	 * @return
	 */
	@Override
	public int deleteById(int visitorId) {
		//����Ӱ����������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//����sql����ַ���		visitorRegister		visitorId
			String sql="delete from visitorRegister where visitorId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, visitorId);
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
	 * @param visitorRegister
	 * @return
	 */
	@Override
	public int update(VisitorRegister visitorRegister) {
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
			String sql="update visitorRegister set visitorName=?,"
					+ " visitorGender=?,visitorIdNum=?,visitorTelphone=?,vistiAddress=?,"
					+ " vistiReason=?,cometime=?,leaveTime=?,empNo=? where visitorId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ			
			
			pstmt.setString(1, visitorRegister.getVisitorName());
			pstmt.setString(2, visitorRegister.getVisitorGender());
			pstmt.setString(3, visitorRegister.getVisitorIdNum());
			pstmt.setString(4, visitorRegister.getVisitorTelphone());
			pstmt.setString(5, visitorRegister.getVistiAddress());
			pstmt.setString(6, visitorRegister.getVistiReason());
			pstmt.setString(7, visitorRegister.getCometime());
			pstmt.setString(8, visitorRegister.getLeaveTime());
			pstmt.setString(9, visitorRegister.getEmpNo());
			pstmt.setInt(10, visitorRegister.getVisitorId());
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
	 * ���ݱ�Ų��ҵ�����Ϣ
	 * @param visitorId
	 * @return
	 */
	@Override
	public VisitorRegister findById(int visitorId) {
		//�����ͻ�����
		VisitorRegister visitorRegister=null;
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
			
			String sql="select visitorId,visitorName,visitorGender,"
					+ "visitorIdNum,visitorTelphone,vistiAddress,vistiReason,"
					+ "cometime,leaveTime,empNo from visitorRegister where visitorId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, visitorId);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�����ͻ�����
				visitorRegister=new VisitorRegister();
				//������������ݱ��浽�ͻ�������
				visitorRegister.setVisitorId(rs.getInt("visitorId"));				
				visitorRegister.setVisitorName(rs.getString("visitorName"));
				visitorRegister.setVisitorGender(rs.getString("visitorGender"));
				visitorRegister.setVisitorGender(rs.getString("visitorGender"));
				visitorRegister.setVisitorIdNum(rs.getString("visitorIdNum"));
				visitorRegister.setVisitorTelphone(rs.getString("visitorTelphone"));
				visitorRegister.setVistiAddress(rs.getString("vistiAddress"));
				visitorRegister.setVistiReason(rs.getString("vistiReason"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				visitorRegister.setCometime(sdf.format(rs.getTimestamp("cometime")));
				visitorRegister.setLeaveTime(sdf.format(rs.getTimestamp("leaveTime")));
				visitorRegister.setEmpNo(rs.getString("empNo"));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return visitorRegister;
	}

	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����г�����Ϣ����
	 */
	@Override
	public List<VisitorRegister> findByPage(int rows, int page) {
		//����ͻ����󼯺�
		List<VisitorRegister> visitorRegisterList=new ArrayList<VisitorRegister>();
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
			String sql="select * from visitorRegister limit ?,?";
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
				VisitorRegister visitorRegister=new VisitorRegister();
				//������������ݱ��浽�ͻ�������
				visitorRegister.setVisitorId(rs.getInt("visitorId"));
				visitorRegister.setVisitorName(rs.getString("visitorName"));
				visitorRegister.setVisitorGender(rs.getString("visitorGender"));
				visitorRegister.setVisitorGender(rs.getString("visitorGender"));
				visitorRegister.setVisitorIdNum(rs.getString("visitorIdNum"));
				visitorRegister.setVisitorTelphone(rs.getString("visitorTelphone"));
				visitorRegister.setVistiAddress(rs.getString("vistiAddress"));
				visitorRegister.setVistiReason(rs.getString("vistiReason"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				visitorRegister.setCometime(sdf.format(rs.getTimestamp("cometime")));
				visitorRegister.setLeaveTime(sdf.format(rs.getTimestamp("leaveTime")));
				visitorRegister.setEmpNo(rs.getString("empNo"));
				//���ͻ�������ӵ��ͻ�������
				visitorRegisterList.add(visitorRegister);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return visitorRegisterList;
	}
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	@Override
	public List<VisitorRegister> findByPage(int rows, int page, String condition) {
		//����ͻ����϶���
		List<VisitorRegister> visitorRegisterList=new ArrayList<VisitorRegister>();
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
			String sql="select * from visitorRegister "+condition+" limit ?,?";
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
				VisitorRegister visitorRegister=new VisitorRegister();
				//��������е����ݱ��浽�ͻ�������
				visitorRegister.setVisitorId(rs.getInt("visitorId"));
				visitorRegister.setVisitorName(rs.getString("visitorName"));
				visitorRegister.setVisitorGender(rs.getString("visitorGender"));
				
				visitorRegister.setVisitorIdNum(rs.getString("visitorIdNum"));
				visitorRegister.setVisitorTelphone(rs.getString("visitorTelphone"));
				visitorRegister.setVistiAddress(rs.getString("vistiAddress"));
				visitorRegister.setVistiReason(rs.getString("vistiReason"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				visitorRegister.setCometime(sdf.format(rs.getTimestamp("cometime")));
				visitorRegister.setLeaveTime(sdf.format(rs.getTimestamp("leaveTime")));
				visitorRegister.setEmpNo(rs.getString("empNo"));
				//��������ӵ��ͻ�������
				visitorRegisterList.add(visitorRegister);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return visitorRegisterList;
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
			String sql="select count(*) from visitorRegister";
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
			String sql="select count(*) from visitorRegister "+condition;
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
