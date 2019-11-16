  
package com.yidu.focus.wzh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.wzh.dao.InformationDao;
import com.yidu.focus.wzh.domain.Information;
import com.yidu.focus.utils.JdbcUtils;

/**
 * ���ܣ�������ݱ�Ĺ��ܲ���
 * ���ߣ���־��
 * ���ڣ�2019��10��12������3:44:28
 * �汾��1.0
 */
public class InformationDaoImpl implements InformationDao {

	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param information ��˵�¼ʵ�������
	 * @return rows Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	public int doRegister(Information information) {
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
			String sql="INSERT INTO information VALUES(?,?,?,?)";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, information.getBackId());
			pstmt.setString(2, information.getBackPwd());
			pstmt.setString(3, information.getEmpName());
			pstmt.setString(4, information.getBackPost());
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
	 * ���պ�˵�¼��ţ�������ɾ������
	 * @param cid ��˵�¼���
	 * @return rows Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int delete(String backId) {
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
			String sql="delete from information where backId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, backId);
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
	 * @param information ��˵�¼ʵ�������
	 * @return rows Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int update(Information information) {
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
			String sql="update information set backPwd=?,empName=?,backPost=? where backId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ			
			pstmt.setString(1, information.getBackPwd());
			pstmt.setString(2, information.getEmpName());
			pstmt.setString(3, information.getBackPost());
			pstmt.setString(4, information.getBackId());
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
	 * ���պ�˵�¼�û������루�����ݿ���в�������
	 * @param empName ��˵�¼�û�
	 * @param backPwd ��˵�¼����
	 * @return information ��˵�¼ʵ�����
	 */
	@Override
	public Information doLogin(String empName,String backPwd) {
		//������˵�¼����
		Information information=null;
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
			String sql="select * from information where empName=? and backPwd=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, empName);
			pstmt.setString(2, backPwd);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ������˵�¼����
				information=new Information();
				//������������ݱ��浽��˵�¼������
				information.setBackId(rs.getString("backId"));
				information.setBackPwd(rs.getString("backPwd"));
				information.setEmpName(rs.getString("empName"));
				information.setBackPost(rs.getString("backPost"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return information;
	}
	/**
	 * ��ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @return informationList ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Information> findByPage(int rows, int page) {
		//�����˵�¼���󼯺�
		List<Information> informationList=new ArrayList<Information>();
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
			String sql="select * from information limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//�����˵�¼����
				Information information=new Information();
				//������������ݱ��浽��˵�¼������
				information.setBackId(rs.getString("backId"));
				information.setBackPwd(rs.getString("backPwd"));
				information.setEmpName(rs.getString("empName"));
				information.setBackPost(rs.getString("backPost"));
				//����˵�¼������ӵ���˵�¼������
				informationList.add(information);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���غ�˵�¼����
		return informationList;
	}
	/**
	 * ���ض�������ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @param condition ��ѯ����
	 * @return informationList ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Information> findByPage(int rows, int page, String condition) {
		//�����˵�¼���󼯺�
		List<Information> informationList=new ArrayList<Information>();
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
			String sql="select * from information "+condition+" limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//�����˵�¼����
				Information information=new Information();
				//������������ݱ��浽��˵�¼������
				information.setBackId(rs.getString("backId"));
				information.setBackPwd(rs.getString("backPwd"));
				information.setEmpName(rs.getString("empName"));
				information.setBackPost(rs.getString("backPost"));
				//����˵�¼������ӵ���˵�¼������
				informationList.add(information);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���غ�˵�¼����
		return informationList;
	}

	/**
	 * ͳ�������ݿ�������ݵ�������
	 * @return rows ����������
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
			String sql="select count(*) from information";
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
	 * @return rows ���ط�����������������
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
			String sql="select count(*) from information "+condition;
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
