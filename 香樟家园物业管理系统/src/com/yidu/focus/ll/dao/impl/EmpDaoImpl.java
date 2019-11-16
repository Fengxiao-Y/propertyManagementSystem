package com.yidu.focus.ll.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.ll.dao.EmpDao;
import com.yidu.focus.ll.domain.Emp;
/**
 * 
 * ���ܣ�Ա���� 
 * ���ߣ�����
 * ���ڣ�2019��10��31������9:14:13
 * �汾��1.0
 */
public class EmpDaoImpl implements EmpDao{

	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param emp ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int add(Emp emp) {
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
			String sql="insert into emp(empNo,empName,"
					+ " empSex,empTelphone,empIdcard,hireDate,"
					+ " salary,commision,deptNo,"
					+ " manager) values(?,?,?,?,?,?,?,?,?,?)";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpSex());
			pstmt.setString(4, emp.getEmpTelphone());
			pstmt.setString(5, emp.getEmpIdcard());
			//���ַ���ת������������
			pstmt.setString(6, emp.getHireDate());
			
			pstmt.setDouble(7, emp.getSalary());
			pstmt.setDouble(8, emp.getCommision());
			pstmt.setString(9, emp.getDeptNo());
			pstmt.setString(10, emp.getManager());
			//ִ��Ԥ���������󣬵õ�Ӱ������
			rows=pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(null, pstmt, conn);
		}//����Ӱ������
		return rows;
	}

	/**
	 * ����Ա����ţ�������ɾ������
	 * @param emp Ա�����
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int delete(String empNo) {
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
			String sql="delete from emp where empNo=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, empNo);
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
	 * @param emp ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int update(Emp emp) {
		//����Ӱ����������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//����sql�ַ���
			String sql="update emp set empName=?,"
					+ "empSex=?,empTelphone=?,empIdcard=?,hireDate=?,"
					+ "salary=?,commision=?,deptNo=?,"
					+ "manager=? where empNo=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getEmpSex());
			pstmt.setString(3, emp.getEmpTelphone());
			pstmt.setString(4, emp.getEmpIdcard());
			//���ַ���ת������������
			pstmt.setString(5,emp.getHireDate());
			pstmt.setDouble(6, emp.getSalary());
			pstmt.setDouble(7, emp.getCommision());
			pstmt.setString(8, emp.getDeptNo());
			pstmt.setString(9, emp.getManager());
			pstmt.setString(10, emp.getEmpNo());
			
			//ִ��Ԥ���������󣬵õ�Ӱ������
			rows=pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(null, pstmt, conn);
		}//����Ӱ������
		return rows;
	}

	/**
	 * ����Ա����ţ������������ݿ���в�������
	 * @param empNo Ա�����
	 * @return Ա��ʵ�����
	 */
	@Override
	public Emp findById(String empNo) {
		//�����ļ�����
		Emp emp=null;
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
			String sql="select * from emp where empNo=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, empNo);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�����ļ�����
				emp=new Emp();
				//������������ݱ��浽�ļ�������
				emp.setEmpNo(rs.getString("empNo"));
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpSex(rs.getString("empSex"));
				emp.setEmpTelphone(rs.getString("empTelphone"));
				emp.setEmpIdcard(rs.getString("empIdcard"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				emp.setHireDate(sdf.format(rs.getDate("hireDate")));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommision(rs.getDouble("commision"));
				emp.setDeptNo(rs.getString("deptno"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return emp;
	}

	/**
	 * ��ѯEmp��������Ա����Ϣ������װ��Ա����Ϣ���Ϸ���
	 * @return Ա����Ϣ����
	 */
	@Override
	public List<Emp> findAll() {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����������
		PreparedStatement pstmt=null;
		//������������� 
		ResultSet rs=null;
		//����һ������������� 
		List<Emp> empList=new ArrayList<Emp>();

		try {
			//1:ʵ�������Ӷ���
			conn=JdbcUtils.getConnection();
			//2:A:����Ҫִ�е�SQL����ַ���
			String sql="select  empNo,empName,empSex,empTelphone,empIdcard,hireDate,"
					+ "salary,commision,deptno from emp";
			//2:B:����SQL��������ݿ����ʵ����������
			pstmt=conn.prepareStatement(sql);
			//3:ִ�������󣬵õ����������
			rs=pstmt.executeQuery();
			//4:�Խ�������б���������������м�¼��ת���ɳ������󣬲���ӵ�������
			while(rs.next()){
				//�������
				Emp emp=new Emp();
				//������������ݱ��浽�ļ�������
				emp.setEmpNo(rs.getString("empNo"));
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpSex(rs.getString("empSex"));
				emp.setEmpTelphone(rs.getString("empTelphone"));
				emp.setEmpIdcard(rs.getString("empIdcard"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				emp.setHireDate(sdf.format(rs.getDate("hireDate")));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommision(rs.getDouble("commision"));
				emp.setDeptNo(rs.getString("deptno"));
				empList.add(emp);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//����Ա����Ϣ����
		return empList;
	}

	/**
	 * ��ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Emp> findByPage(int rows, int page) {
		//�����ļ����󼯺�
		List<Emp> empList=new ArrayList<Emp>();
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
			String sql="select * from emp limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//����Ա������
				Emp emp=new Emp();
				//������������ݱ��浽�ļ�������
				emp.setEmpNo(rs.getString("empNo"));
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpSex(rs.getString("empSex"));
				emp.setEmpTelphone(rs.getString("empTelphone"));
				emp.setEmpIdcard(rs.getString("empIdcard"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				emp.setHireDate(sdf.format(rs.getDate("hireDate")));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommision(rs.getDouble("commision"));
				emp.setDeptNo(rs.getString("deptno"));
				emp.setManager(rs.getString("manager"));
				empList.add(emp);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return empList;
	}

	/**
	 * ���ض�������ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @param condition ��ѯ����
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Emp> findByPage(int rows, int page, String condition) {
		//�����ļ����϶���
		List<Emp> empList=new ArrayList<Emp>();
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
			String sql="select * from emp "+condition+" limit ?,? ";
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
				Emp emp=new Emp();
				//������������ݱ��浽�ļ�������
				emp.setEmpNo(rs.getString("empNo"));
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpSex(rs.getString("empSex"));
				emp.setEmpTelphone(rs.getString("empTelphone"));
				emp.setEmpIdcard(rs.getString("empIdcard"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				emp.setHireDate(sdf.format(rs.getDate("hireDate")));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommision(rs.getDouble("commision"));
				
				if(rs.getString("deptno").equals("xz")){
					emp.setDeptNo("������");
				}else if(rs.getString("deptno").equals("ba")){
					emp.setDeptNo("������");
				}else if(rs.getString("deptno").equals("gc")){
					emp.setDeptNo("���̲�");
				}else if(rs.getString("deptno").equals("cw")){
					emp.setDeptNo("����");
				}else if(rs.getString("deptno").equals("kf")){
					emp.setDeptNo("�ͷ���");
				}
				emp.setManager(rs.getString("manager"));
				//���ͻ�������ӵ��ļ�������
				empList.add(emp);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//����Ա������
		return empList;
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
			String sql="select count(*) from emp";
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
			String sql="select count(*) from emp "+condition;
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
