
package com.yidu.focus.wzh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.wzh.dao.UsersDao;
import com.yidu.focus.wzh.domain.Users;

/**
 * ���ܣ�������ݱ�Ĺ��ܲ���
 * ���ߣ���־��
 * ���ڣ�2019��10��12������11:24:45
 * �汾��1.0
 */
public class UsersDaoImpl implements UsersDao{

	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param users �û���ʵ�������
	 * @return  rows Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int doRegister(Users users) {
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
			String sql="INSERT INTO users(uName,uPassword,uEmail,uIphone) VALUES(?,?,?,?)";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, users.getuName());
			pstmt.setString(2, users.getuPassword());
			pstmt.setString(3, users.getuEmail());
			pstmt.setString(4, users.getuIphone());
			//ִ��Ԥ����������                                                                                                                                                                                                                                                                                                                                                                                          �õ�Ӱ������
			rows=pstmt.executeUpdate();
			System.out.println(rows);

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
	 * @param uId �û����
	 * @return rows Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int delete(int uId) {
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
			String sql="delete from users where uId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, uId);
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
	 * @param users �û�ʵ�������
	 * @return rows Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int update(Users users) {
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
			//����sql����ַ���
			String sql="update users set uName=?,uPassword=?,uEmail=?,uIphone=? where uId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, users.getuName());
			pstmt.setString(2, users.getuPassword());
			pstmt.setString(3, users.getuEmail());
			pstmt.setString(4, users.getuIphone());
			pstmt.setInt(5, users.getuId());
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
	 * �����û���ţ������������ݿ���в�������
	 * @param uId �û����
	 * @return users �û�ʵ�����
	 */
	@Override
	public Users findById(int uId) {
		//�����û�����
		Users users=null;
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
			String sql="select * from Users where uId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, uId);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�����û�����
				users=new Users();
				//������������ݱ��浽�û�������
				users.setuName(rs.getString("uName"));
				users.setuPassword(rs.getString("uPassword"));
				users.setuEmail(rs.getString("uEmail"));
				users.setuIphone(rs.getString("uIphone"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return users;
	}

	/**
	 * ��ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @return usersList ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Users> findByPage(int rows, int page) {
		//�����û����󼯺�
		List<Users> usersList=new ArrayList<Users>();
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
			String sql="select * from users limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//�����û�����
				Users users=new Users();
				//������������ݱ��浽�û�������
				users.setuId(rs.getInt("uId"));
				users.setuName(rs.getString("uName"));
				users.setuPassword(rs.getString("uPassword"));
				users.setuEmail(rs.getString("uEmail"));
				users.setuIphone(rs.getString("uIphone"));
				//���û�������ӵ��û�������
				usersList.add(users);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//�����û�����
		return usersList;
	}

	/**
	 * ���ض�������ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @param condition ��ѯ����
	 * @return usersList ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Users> findByPage(int rows, int page, String condition) {
		//�����û����󼯺�
		List<Users> usersList=new ArrayList<Users>();
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
			String sql="select * from users "+condition+" limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//�����û�����
				Users users=new Users();
				//������������ݱ��浽�û�������
				users.setuId(rs.getInt("uId"));
				users.setuName(rs.getString("uName"));
				users.setuPassword(rs.getString("uPassword"));
				users.setuEmail(rs.getString("uEmail"));
				users.setuIphone(rs.getString("uIphone"));
				//���û�������ӵ��û�������
				usersList.add(users);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//�����û�����
		return usersList;
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
			String sql="select count(*) from users";
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
			String sql="select count(*) from users "+condition;
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

	
	/**
	 * �����û��������ݿ���в�������
	 * @param uName �û���
	 * @return users �û�ʵ�����
	 */
	@Override
	public Users findByUIphone(String uIphone) {
		//�����û�����
		Users users=null;
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
			String sql="select * from Users where uIphone=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, uIphone);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�����û�����
				users=new Users();
				//������������ݱ��浽�û�������
				users.setuId(rs.getInt("uId"));
				users.setuName(rs.getString("uName"));
				users.setuPassword(rs.getString("uPassword"));
				users.setuEmail(rs.getString("uEmail"));
				users.setuIphone(rs.getString("uIphone"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return users;
	}
	/**
	 * ��¼
	 * @param uName �û���
	 * @param uPassword �û�����
	 * @return users �û�ʵ�����
	 */
	@Override
	public Users doLogin(String uName,String uPassword) {
		//�����û�����
		Users users=null;
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
			String sql="select * from Users where uName=? and uPassword=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, uName);
			pstmt.setString(2, uPassword);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�����û�����
				users=new Users();
				//������������ݱ��浽�û�������
				users.setuId(rs.getInt("uId"));
				users.setuName(rs.getString("uName"));
				users.setuPassword(rs.getString("uPassword"));
				users.setuEmail(rs.getString("uEmail"));
				users.setuIphone(rs.getString("uIphone"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return users;
	}

}
