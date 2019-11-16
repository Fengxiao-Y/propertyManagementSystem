package com.yidu.focus.yfx.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.yfx.dao.ComplainDao;
import com.yidu.focus.yfx.domain.Complain;
/**
 * 
 * ���ܣ�Ͷ�߲�����
 * ���ߣ��Ϸ�Т
 * ���ڣ�2019��10��31������8:53:13
 * �汾��1.0
 */
public class ComplainDaoImpl implements ComplainDao {
	
	/**
	 * ������ݣ���Ͷ�߶����е����ݱ��浽Ͷ�����ݿ����
	 * @param complain Ͷ�߶���
	 * @return Ӱ��������1�������ɹ���0������ʧ�ܣ�
	 */
	@Override
	public int add(Complain complain) {
		//������������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//�������ݿ�ִ�����sql�ַ���
			String sql="insert into complain(ownerName,comText, "
					+ " comTime,empName,comResult,comEndTime) "
					+ " values(?,?,?,?,?,?)";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			//ҵ������
			pstmt.setString(1, complain.getOwnerName());
			//Ͷ������
			pstmt.setString(2, complain.getComText());
			//Ͷ��ʱ��
			pstmt.setString(3, complain.getComTime());
			//������
			pstmt.setString(4, complain.getEmpName());
			//������
			pstmt.setString(5, complain.getComResult());
			//�ط�ʱ��
			pstmt.setString(6, complain.getComEndTime());
			//ִ��Ԥ����������õ�Ӱ������ֵ
			rows=pstmt.executeUpdate();			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		//����Ӱ������ֵ
		return rows;
	}

	/**
	 * ����Ͷ�߱��ɾ�����ݿ���е�����
	 * @param comId Ͷ�߱��
	 * @return Ӱ��������1�������ɹ���0������ʧ�ܣ�
	 */
	@Override
	public int deleteById(int comId) {
		//������������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//�������ݿ�ִ�����sql�ַ���
			String sql="delete from complain where comId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, comId);
			//ִ��Ԥ����������õ�Ӱ������ֵ
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
	 * �޸����ݣ���Ͷ�߶����е����ݸ��µ����ݿ����
	 * @param complain Ͷ�߶���
	 * @return Ӱ��������1�������ɹ���0������ʧ�ܣ�
	 */
	@Override
	public int update(Complain complain) {
		//������������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//�������ݿ�ִ�����sql�ַ���
			String sql="update complain set ownerName=?,comText=?, "
					+ " comTime=?,empName=?,comResult=?,comEndTime=? "
					+ " where comId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			//ҵ������
			pstmt.setString(1, complain.getOwnerName());
			//Ͷ������
			pstmt.setString(2, complain.getComText());
			//Ͷ��ʱ��
			pstmt.setString(3, complain.getComTime());
			//������
			pstmt.setString(4, complain.getEmpName());
			//������
			pstmt.setString(5, complain.getComResult());
			//�ط�ʱ��
			pstmt.setString(6, complain.getComEndTime());
			//Ͷ�߱��
			pstmt.setInt(7, complain.getComId());
			//ִ��Ԥ����������õ�Ӱ������ֵ
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
	 * ����Ͷ�߱�Ų��ҵ���Ͷ����Ϣ
	 * @param comId Ͷ�߱��
	 * @return Ͷ�߶���
	 */
	@Override
	public Complain findById(int comId) {
		//����Ͷ�߶���
		Complain complain=null;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//�������ݿ�ִ�����sql�ַ���
			String sql="select * from complain where comId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, comId);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ���жϴ�������
			if(rs.next()){
				//ʵ����Ͷ�߶���
				complain=new Complain();
				//������������ݱ��浽Ͷ�߶�����
				complain.setComId(rs.getInt("comId"));
				complain.setOwnerName(rs.getString("ownerName"));
				complain.setComText(rs.getString("comText"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				complain.setComTime(sdf.format(rs.getTimestamp("comTime")));
				complain.setEmpName(rs.getString("empName"));
				complain.setComResult(rs.getString("comResult"));
				complain.setComEndTime(sdf.format(rs.getTimestamp("comEndTime")));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//����Ͷ�߶���
		return complain;
	}

	/**
	 * ��������Ͷ����Ϣ
	 * @return Ͷ�߼��϶���
	 */
	@Override
	public List<Complain> findAll() {
		return null;
	}

	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ������Ͷ����Ϣ����
	 */
	@Override
	public List<Complain> findByPage(int rows, int page) {
		//����Ͷ�߼��϶���
		List<Complain> complainList=new ArrayList<Complain>();
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//�������ݿ�ִ�����sql�ַ���
			String sql="select * from complain limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//����Ͷ�߶���
				Complain complain=new Complain();
				//������������ݱ��浽Ͷ�߶�����
				complain.setComId(rs.getInt("comId"));
				complain.setOwnerName(rs.getString("ownerName"));
				complain.setComText(rs.getString("comText"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				complain.setComTime(sdf.format(rs.getTimestamp("comTime")));
				complain.setEmpName(rs.getString("empName"));
				complain.setComResult(rs.getString("comResult"));
				complain.setComEndTime(sdf.format(rs.getTimestamp("comEndTime")));
				//��Ͷ�߶�����ӵ�Ͷ�߼��϶�����
				complainList.add(complain);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//����Ͷ�߽�϶���
		return complainList;
	}

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ��������
	 * @return ָ��ҳ������Ͷ����Ϣ����
	 */
	@Override
	public int count() {
		//������������
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
			//�������ݿ�ִ�����sql�ַ���
			String sql="select count(*) from complain";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//�жϽ����
			if(rs.next()){
				//��������е�����������ֵ����������
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
	 * ͳ�Ƽ�¼��
	 * @return �������м�¼����
	 */
	@Override
	public List<Complain> findByPage(int rows, int page, String condition) {
		//����Ͷ�߼��϶���
		List<Complain> complainList=new ArrayList<Complain>();
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();			
			//�������ݿ�ִ�����sql�ַ���
			String sql="select * from complain "+condition+" limit ?,? ";			
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����еĲ�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//����Ͷ�߶���
				Complain complain=new Complain();				
				//������������ݱ��浽Ͷ�߶�����
				complain.setComId(rs.getInt("comId"));
				complain.setOwnerName(rs.getString("ownerName"));
				complain.setComText(rs.getString("comText"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				complain.setComTime(sdf.format(rs.getTimestamp("comTime")));
				complain.setEmpName(rs.getString("empName"));
				complain.setComResult(rs.getString("comResult"));
				complain.setComEndTime(sdf.format(rs.getTimestamp("comEndTime")));
				//��Ͷ�߶�����ӵ�Ͷ�߼��϶�����
				complainList.add(complain);
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		
		//����Ͷ�߼��϶���
		return complainList;
		
	}

	/**
	 * ͳ�Ʒ��������ļ�¼��
	 * @param condition ͳ������
	 * @return ���з���ͳ��������¼����
	 */
	@Override
	public int count(String condition) {
		//������������
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
			//�������ݿ�ִ�����sql�ַ���
			String sql="select count(*) from complain "+condition;
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//�жϽ����
			if(rs.next()){
				//��������е�����������ֵ����������
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
	 * ����Ͷ�߱�Ų��ҵ���Ͷ����Ϣ
	 * @param comId Ͷ�߱��
	 * @return Ͷ�߶���
	 */
	@Override
	public List<Complain> findByName(String ownerName) {
		//����Ͷ�߶���
		List<Complain> complainList=null;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//�������ݿ�ִ�����sql�ַ���
			String sql="select * from complain where ownerName=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, ownerName);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ���жϴ�������
			while(rs.next()){
				//����Ͷ�߶���
				Complain complain=new Complain();
				//������������ݱ��浽Ͷ�߶�����
				complain.setComId(rs.getInt("comId"));
				complain.setOwnerName(rs.getString("ownerName"));
				complain.setComText(rs.getString("comText"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				complain.setComTime(sdf.format(rs.getTimestamp("comTime")));
				complain.setEmpName(rs.getString("empName"));
				complain.setComResult(rs.getString("comResult"));
				complain.setComEndTime(sdf.format(rs.getTimestamp("comEndTime")));
				//ʵ�������϶���
				complainList = new ArrayList<>();
				//��������ӵ�������
				complainList.add(complain);
			}			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//����Ͷ�߶���
		return complainList;
	}

}
