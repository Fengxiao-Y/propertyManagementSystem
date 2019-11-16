package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hj.dao.WageDao;
import com.yidu.focus.hj.domain.Wage;

/**
 * 
 * ���ܣ����ʱ�ʵ����
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��31������6:24:21
 */
public class WageDaoImpl implements WageDao {
	/**
	 * �������
	 * @param wage ���ʶ���
	 * @return Ӱ������
	 */
	@Override
	public int add(Wage wage) {
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
			String sql = "insert into wage(empName,salary,commision,withhold,playMoney,wageMonth) values(?,?,?,?,?,?)";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, wage.getEmpName());
			pstmt.setDouble(2, wage.getSalary());
			pstmt.setDouble(3, wage.getCommision());
			pstmt.setDouble(4, wage.getWithhold());
			pstmt.setDouble(5, wage.getPlayMoney());
			pstmt.setString(6, wage.getWageMonth());
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
	 * @param wage ����
	 * @return Ӱ������
	 */
	@Override
	public int update(Wage wage) {
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
			String sql = "update wage set empName=?,salary=?,commision=?,withhold=?,playMoney=?,wageMonth=? where wageid=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, wage.getEmpName());
			pstmt.setDouble(2, wage.getSalary());
			pstmt.setDouble(3, wage.getCommision());
			pstmt.setDouble(4, wage.getWithhold());
			pstmt.setDouble(5, wage.getPlayMoney());
			pstmt.setString(6, wage.getWageMonth());
			pstmt.setInt(7, wage.getWageid());
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
	 * @param wageid ���
	 * @return Ӱ������
	 */
	@Override
	public int delete(int wageid) {
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
			String sql = "delete from wage where wageid=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, wageid);
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
	 * @param wageid ���
	 * @return ����
	 */
	@Override
	public Wage getWageById(String empName) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		//����һ���洢���ݶ���
		Wage wage = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����һ��sqlִ��������
			String sql = "select * from wage where empName=?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setString(1, empName);
			//ʵ�������������
			rs = pstmt.executeQuery();
			if(rs.next()){
				wage = new Wage();
				wage.setWageid(rs.getInt("wageid"));
				wage.setEmpName(rs.getString("empName"));
				wage.setSalary(rs.getDouble("salary"));
				wage.setCommision(rs.getDouble("commision"));
				wage.setWithhold(rs.getDouble("withhold"));
				wage.setPlayMoney(rs.getDouble("playMoney"));
				wage.setWageMonth(rs.getString("wageMonth"));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			JdbcUtils.close(rs, pstmt, conn);
		}
		return wage;
	}
	/**
	 * ��ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @return ���󼯺�
	 */
	@Override
	public List<Wage> selectByPage(int rows, int page) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		//����һ�����󼯺�
		List<Wage> wageList = new ArrayList<Wage>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����һ��sqlִ��������
			String sql="select * from wage limit ?,?";
			
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				Wage wage = new Wage();
				wage.setWageid(rs.getInt("wageid"));
				wage.setEmpName(rs.getString("empName"));
				wage.setSalary(rs.getDouble("salary"));
				wage.setCommision(rs.getDouble("commision"));
				wage.setWithhold(rs.getDouble("withhold"));
				wage.setPlayMoney(rs.getDouble("playMoney"));
				wage.setWageMonth(rs.getString("wageMonth"));
				wageList.add(wage);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return wageList;
	}
	/**
	 * ��������ҳ��ѯ����
	 * @param rows ����
	 * @param page ����
	 * @param condition ����
	 * @return ���϶���
	 */
	@Override
	public List<Wage> findByPage(int rows, int page, String condition) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		//����һ�����󼯺�
		List<Wage> wageList = new ArrayList<Wage>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����һ��sqlִ��������
			String sql="select * from wage "+condition+" limit ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				Wage wage = new Wage();
				wage.setWageid(rs.getInt("wageid"));
				wage.setEmpName(rs.getString("empName"));
				wage.setSalary(rs.getDouble("salary"));
				wage.setCommision(rs.getDouble("commision"));
				wage.setWithhold(rs.getDouble("withhold"));
				wage.setPlayMoney(rs.getDouble("playMoney"));
				wage.setWageMonth(rs.getString("wageMonth"));
				wageList.add(wage);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		return wageList;
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
			String sql = "select count(*) from wage";
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
			String sql = "select count(*) from wage "+condition;
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

}
