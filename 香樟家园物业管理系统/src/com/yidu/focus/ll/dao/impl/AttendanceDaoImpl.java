package com.yidu.focus.ll.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.ll.dao.AttendanceDao;
import com.yidu.focus.ll.domain.Attendance;
/**
 * 
 * ���ܣ�Ա�����ڱ� 
 * ���ߣ�����
 * ���ڣ�2019��10��31������9:12:45
 * �汾��1.0
 */
public class AttendanceDaoImpl implements AttendanceDao{
	/**
	 * ���
	 */
	@Override
	public int add(Attendance attendance) {	
		//�����������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//��дSQL���
			String sql="insert into Attendance(empNo,"
					+ "onWork,doWork) values(?,?,?)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1, attendance.getEmpNo());
			//���ַ���ת������������
			pstmt.setDate(2, java.sql.Date.valueOf(attendance.getOnWork()));
			pstmt.setDate(3, java.sql.Date.valueOf(attendance.getDoWork()));
			//ִ��SQL����
			//ִ��Ԥ���������󣬵õ�Ӱ������
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ
			JdbcUtils.close(null, pstmt, conn);
		}
		//����Ӱ�������
		return rows;
	}

	/**
	 * ɾ��
	 */
	@Override
	public int delete(int attendId ) {
		//����ɾ������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//��дSQL���
			String sql="delete from Attendance where attendId=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setInt(1, attendId);
			//ִ��SQL����
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ
			JdbcUtils.close(null, pstmt, conn);
		}
		//����Ӱ�������
		return rows;
	}

	/**
	 * �޸�
	 */
	@Override
	public int update(Attendance attendance) {
		//����ɾ������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//��дSQL���
			String sql="update Attendance set empNo=?,onWork=?,"
					+ "doWork=? where attendId=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1, attendance.getEmpNo());
			//���ַ���ת������������
			pstmt.setDate(2, java.sql.Date.valueOf(attendance.getOnWork()));
			pstmt.setDate(3, java.sql.Date.valueOf(attendance.getDoWork()));
			pstmt.setInt(4, attendance.getAttendId());
			//ִ��SQL����
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ
			JdbcUtils.close(null, pstmt, conn);
		}
		//����Ӱ�������
		return rows;
	}
	/**
	 * ���ݿ��ڵı�ţ���ѯ��Ӧ�����°���Ϣ
	 */    
	@Override
	public Attendance findById(int attendId) {
		//����һ������ 
		Attendance attendance=null;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����������
		PreparedStatement pstmt=null;
		//������������� 
		ResultSet rs=null;

		try {
			//1:ʵ�������Ӷ���
			conn=JdbcUtils.getConnection();
			//2:A:����Ҫִ�е�SQL����ַ���
			String sql="select attendId,empNo,onWork,doWork where attendId=?";
			//2:B:����SQL��������ݿ����ʵ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setInt(1, attendId);
			//3:ִ�������󣬵õ����������
			rs=pstmt.executeQuery();
			//4:�Խ�������д�����������м�¼��ת���ɿ��ڶ���
			if(rs.next()){
				//4A:ʵ�������ڶ���
				attendance=new Attendance();
				//4B:��rs��һ�����ݱ��浽Attendance�����������
				attendance.setAttendId(rs.getInt("attendId"));
				attendance.setEmpNo(rs.getString("empNo"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				attendance.setOnWork(sdf.format(rs.getDate("onWork")));
				SimpleDateFormat sdft=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				attendance.setDoWork(sdft.format(rs.getDate("doWork")));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿ��ڶ���
		return attendance;
	}

	/**
	 * ��ѯAttendance�������п�����Ϣ������װ�ɿ�����Ϣ���Ϸ���
	 * @return ������Ϣ����
	 */
	@Override
	public List<Attendance> findAll() {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����������
		PreparedStatement pstmt=null;
		//������������� 
		ResultSet rs=null;
		//����һ������������� 
		List<Attendance> attendanceList=new ArrayList<Attendance>();

		try {
			//1:ʵ�������Ӷ���
			conn=JdbcUtils.getConnection();
			//2:A:����Ҫִ�е�SQL����ַ���
			String sql="select  attendId,empNo,onWork,doWork from attendance";
			//2:B:����SQL��������ݿ����ʵ����������
			pstmt=conn.prepareStatement(sql);
			//3:ִ�������󣬵õ����������
			rs=pstmt.executeQuery();
			//4:�Խ�������б���������������м�¼��ת�����鼮���󣬲���ӵ�������
			while(rs.next()){
				//4A:����һ���鼮����
				Attendance attendance=new Attendance();
				//4B:��rs��һ�����ݱ��浽Attendance�����������
				attendance.setAttendId(rs.getInt("attendId"));
				attendance.setEmpNo(rs.getString("empNo"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				attendance.setOnWork(sdf.format(rs.getDate("onWork")));
				SimpleDateFormat sdft=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				attendance.setDoWork(sdft.format(rs.getDate("doWork")));
				//4C:��books������ӵ�������
				attendanceList.add(attendance);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿ�����Ϣ����
		return attendanceList;
	}
	
	@Override
	public List<Attendance> findByPage(int rows, int page) {
		List<Attendance> attendanceList=new ArrayList<Attendance>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnection();
			String sql="select * from attendance limit ?,?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			rs=pstmt.executeQuery();
			//4:�Խ�������д�����������м�¼��ת���ɿ��ڶ���
			while(rs.next()){
				//4A:ʵ�������ڶ���
				Attendance attendance=new Attendance();
				attendance.setAttendId(rs.getInt("attendId"));
				attendance.setEmpNo(rs.getString("empNo"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				attendance.setOnWork(sdf.format(rs.getDate("onWork")));
				SimpleDateFormat sdft=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				attendance.setDoWork(sdft.format(rs.getDate("doWork")));
				attendanceList.add(attendance);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
		return attendanceList;
	}

	/**
	 * ���ض�������ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @param condition ��ѯ����
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Attendance> findByPage(int rows, int page, String condition) {
		//���忼�ڼ��϶���
		List<Attendance> attendanceList=new ArrayList<Attendance>();
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
			String sql="select * from Attendance "+condition+" limit ?,? ";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����еĲ�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//�����ļ�����
				Attendance attendance=new Attendance();
				attendance.setAttendId(rs.getInt("attendId"));
				attendance.setEmpNo(rs.getString("empNo"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				attendance.setOnWork(sdf.format(rs.getDate("onWork")));
				SimpleDateFormat sdft=new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
				attendance.setDoWork(sdft.format(rs.getDate("doWork")));
				//���ͻ�������ӵ����ڼ�����
				attendanceList.add(attendance);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿ��ڼ���
		return attendanceList;
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
			String sql="select count(*) from Attendance";
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
			String sql="select count(*) from Attendance "+condition;
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
			JdbcUtils.close(rs, pstmt, conn);
		}
		//��������
		return rows;
	}

}
