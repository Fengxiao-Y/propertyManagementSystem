package com.yidu.focus.ll.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.ll.dao.DeptDao;
import com.yidu.focus.ll.domain.Dept;
/**
 * 
 * ���ܣ����ű� 
 * ���ߣ�����
 * ���ڣ�2019��10��31������9:13:50
 * �汾��1.0
 */
public class DeptDaoImpl implements DeptDao {
	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param dept ����ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int add(Dept dept) {
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
			String sql="INSERT INTO dept(deptNo,deptName,empNo,empCount) "
					+ " VALUES(?,?,?,?)";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, dept.getDeptNo());
			pstmt.setString(2, dept.getDeptName());
			pstmt.setString(3, dept.getEmpNo());
			pstmt.setInt(4, dept.getEmpCount());
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
	 * ���ղ��ű�ţ�������ɾ������
	 * @param deptNo ���ű��
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int delete(int deptNo) {
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
			String sql="delete from dept where deptNo=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, deptNo);
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
	 * @param dept ����ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int update(Dept dept) {
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
			String sql="update dept set deptName=?,empNo=?,empCount=? "
					+ " where deptNo=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ			
			pstmt.setString(1, dept.getDeptName());
			pstmt.setString(2, dept.getEmpNo());
			pstmt.setInt(3, dept.getEmpCount());
			pstmt.setString(4, dept.getDeptNo());
			System.err.println(1111);
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
	 * ���ղ��ű�ţ������������ݿ���в�������
	 * @param deptNo ���ű��
	 * @return ����ʵ�����
	 */
	@Override
	public Dept findById(int deptNo) {
		//�������Ŷ���
		Dept dept=null;
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
			String sql="select deptNo,deptName,empNo,empCount from dept where deptNo=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, deptNo);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�������Ŷ���
				dept=new Dept();
				//������������ݱ��浽���Ŷ�����
				dept.setDeptNo(rs.getString("deptNo"));
				dept.setDeptName(rs.getString("deptName"));
				dept.setEmpNo(rs.getString("empNo"));
				dept.setEmpCount(rs.getInt("empCount"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return dept;
	}

	/**
	 * ��ѯDept�������в�����Ϣ������װ�ɲ�����Ϣ���Ϸ���
	 * @return ������Ϣ����
	 */
	@Override
	public List<Dept> findAll() {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����������
		PreparedStatement pstmt=null;
		//������������� 
		ResultSet rs=null;
		//����һ������������� 
		List<Dept> deptList=new ArrayList<Dept>();

		try {
			//1:ʵ�������Ӷ���
			conn=JdbcUtils.getConnection();
			//2:A:����Ҫִ�е�SQL����ַ���
			String sql="select deptNo,deptName,empNo,empCount from dept";
			//2:B:����SQL��������ݿ����ʵ����������
			pstmt=conn.prepareStatement(sql);
			//3:ִ�������󣬵õ����������
			rs=pstmt.executeQuery();
			//4:�Խ�������б���������������м�¼��ת���ɲ��Ŷ��󣬲���ӵ�������
			while(rs.next()){
				//�������
				Dept dept=new Dept();
				//������������ݱ��浽���Ŷ�����
				dept.setDeptNo(rs.getString("deptNo"));
				dept.setDeptName(rs.getString("deptName"));
				dept.setEmpNo(rs.getString("empNo"));
				dept.setEmpCount(rs.getInt("empCount"));
				//�����Ŷ�����ӵ����ż�����
				deptList.add(dept);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ز�����Ϣ����
		return deptList;
	}

	/**
	 * ��ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Dept> findByPage(int rows, int page) {
		//���岿�Ŷ��󼯺�
		List<Dept> deptList=new ArrayList<Dept>();
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
			String sql="select * from dept limit ?,?";
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
				Dept dept=new Dept();
				//������������ݱ��浽�ͻ�������
				dept.setDeptNo(rs.getString("deptNo"));
				dept.setDeptName(rs.getString("deptName"));
				dept.setEmpNo(rs.getString("empNo"));
				dept.setEmpCount(rs.getInt("empCount"));
				//���ͻ�������ӵ��ͻ�������
				deptList.add(dept);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return deptList;
	}

	/**
	 * ���ض�������ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @param condition ��ѯ����
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Dept> findByPage(int rows, int page, String condition) {
		//���岿�ż��϶���
		List<Dept> deptList=new ArrayList<Dept>();
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
			String sql="select * from dept "+condition+" limit ?,?";
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
				Dept dept=new Dept();
				//������������ݱ��浽�ͻ�������
			
				
				dept.setDeptNo(rs.getString("deptNo"));
				dept.setDeptName(rs.getString("deptName"));
				dept.setEmpNo(rs.getString("empNo"));
				dept.setEmpCount(rs.getInt("empCount"));
				
				
				//��������ӵ��ͻ�������
				deptList.add(dept);
				
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return deptList;
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
			String sql="select count(*) from dept";
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
			String sql="select count(*) from dept "+condition;
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
