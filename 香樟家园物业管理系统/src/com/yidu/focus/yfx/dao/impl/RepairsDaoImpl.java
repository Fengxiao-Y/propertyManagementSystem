package com.yidu.focus.yfx.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.yfx.dao.RepairsDao;
import com.yidu.focus.yfx.domain.Repairs;
/**
 * 
 * ���ܣ����޼�¼������ 
 * ���ߣ��Ϸ�Т
 * ���ڣ�2019��10��31������8:56:05
 * �汾��1.0
 */
public class RepairsDaoImpl implements RepairsDao {
	
	/**
	 * ������ݣ������޶����е����ݱ��浽�������ݿ����
	 * @param repairs ���޶���
	 * @return Ӱ��������1�������ɹ���0������ʧ�ܣ�
	 */
	@Override
	public int add(Repairs repairs) {
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
			String sql="insert into repairs(ownerName,repText, "
					+ " repTime,empName,repResult,repEndTime) "
					+ " values(?,?,?,?,?,?)";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			//ҵ������
			pstmt.setString(1, repairs.getOwnerName());
			//��������
			pstmt.setString(2, repairs.getRepText());			
			//����ʱ��
			pstmt.setString(3, repairs.getRepTime());
			//������
			pstmt.setString(4, repairs.getEmpName());
			//������
			pstmt.setString(5, repairs.getRepResult());			
			//�ط�ʱ��
			pstmt.setString(6, repairs.getrepEndTime());
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
	 * ���ݱ��ޱ��ɾ�����ݿ���е�����
	 * @param repId ���ޱ��
	 * @return Ӱ��������1�������ɹ���0������ʧ�ܣ�
	 */
	@Override
	public int deleteById(int repId) {
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
			String sql="delete from repairs where repId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, repId);
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
	 * �޸����ݣ������޶����е����ݸ��µ����ݿ����
	 * @param repairs ���޶���
	 * @return Ӱ��������1�������ɹ���0������ʧ�ܣ�
	 */
	@Override
	public int update(Repairs repairs) {
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
			String sql="update repairs set ownerName=?,repText=?, "
					+ " repTime=?,empName=?,repResult=?,repEndTime=? "
					+ " where repId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			//ҵ������
			pstmt.setString(1, repairs.getOwnerName());
			//��������
			pstmt.setString(2, repairs.getRepText());
			//����ʱ��
			pstmt.setString(3, repairs.getRepTime());
			//������
			pstmt.setString(4, repairs.getEmpName());
			//������
			pstmt.setString(5, repairs.getRepResult());
			//�ط�ʱ��
			pstmt.setString(6, repairs.getrepEndTime());
			//���ޱ��
			pstmt.setInt(7, repairs.getRepId());
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
	 * ���ݱ��ޱ�Ų��ҵ��α�����Ϣ
	 * @param repId ���ޱ��
	 * @return ���޶���
	 */
	@Override
	public Repairs findById(int repId) {
		//�������޶���
		Repairs repairs=null;
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
			String sql="select * from repairs where repId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, repId);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ���жϴ�������
			if(rs.next()){
				//ʵ�������޶���
				repairs=new Repairs();
				//������������ݱ��浽���޶�����
				repairs.setRepId(rs.getInt("repId"));
				repairs.setOwnerName(rs.getString("ownerName"));
				repairs.setRepText(rs.getString("repText"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				repairs.setRepTime(sdf.format(rs.getTimestamp("repTime")));
				repairs.setEmpName(rs.getString("empName"));
				repairs.setRepResult(rs.getString("repResult"));
				repairs.setRepEndTime(sdf.format(rs.getTimestamp("repEndTime")));
			}			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ر��޶���
		return repairs;
	}

	/**
	 * �������б�����Ϣ
	 * @return ���޼��϶���
	 */
	@Override
	public List<Repairs> findAll() {
		return null;
	}

	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����б�����Ϣ����
	 */
	@Override
	public List<Repairs> findByPage(int rows, int page) {
		//���屨�޼��϶���
		List<Repairs> repairsList=new ArrayList<Repairs>();
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
			String sql="select * from repairs limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//���屨�޶���
				Repairs repairs=new Repairs();
				//������������ݱ��浽���޶�����
				repairs.setRepId(rs.getInt("repId"));
				repairs.setOwnerName(rs.getString("ownerName"));
				repairs.setRepText(rs.getString("repText"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				repairs.setRepTime(sdf.format(rs.getTimestamp("repTime")));
				repairs.setEmpName(rs.getString("empName"));
				repairs.setRepResult(rs.getString("repResult"));
				repairs.setRepEndTime(sdf.format(rs.getTimestamp("repEndTime")));
				//�����޶�����ӵ����޼��϶�����
				repairsList.add(repairs);
			}			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ر��޽�϶���
		return repairsList;
	}

	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ��������
	 * @return ָ��ҳ�����б�����Ϣ����
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
			String sql="select count(*) from repairs";
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
	public List<Repairs> findByPage(int rows, int page, String condition) {
		//���屨�޼��϶���
		List<Repairs> repairsList=new ArrayList<Repairs>();
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
			String sql="select * from repairs "+condition+" limit ?,? ";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����еĲ�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//���屨�޶���
				Repairs repairs=new Repairs();				
				//������������ݱ��浽���޶�����
				repairs.setRepId(rs.getInt("repId"));
				repairs.setOwnerName(rs.getString("ownerName"));
				repairs.setRepText(rs.getString("repText"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				repairs.setRepTime(sdf.format(rs.getTimestamp("repTime")));
				repairs.setEmpName(rs.getString("empName"));
				repairs.setRepResult(rs.getString("repResult"));
				repairs.setRepEndTime(sdf.format(rs.getTimestamp("repEndTime")));
				//�����޶�����ӵ����޼��϶�����
				repairsList.add(repairs);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ر��޼��϶���
		return repairsList;
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
			String sql="select count(*) from repairs "+condition;
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
	 * ���ݱ��ޱ�Ų��ҵ��α�����Ϣ
	 * @param repId ���ޱ��
	 * @return ���޶���
	 */
	@Override
	public List<Repairs> findByName(String ownerName) {
		//�������޼��϶���
		List<Repairs> repairsList=null;
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
			String sql="select * from repairs where ownerName=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, ownerName);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ���жϴ�������
			if(rs.next()){
				//���屨�޶���
				Repairs repairs=new Repairs();
				//������������ݱ��浽���޶�����
				repairs.setRepId(rs.getInt("repId"));
				repairs.setOwnerName(rs.getString("ownerName"));
				repairs.setRepText(rs.getString("repText"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				repairs.setRepTime(sdf.format(rs.getTimestamp("repTime")));
				repairs.setEmpName(rs.getString("empName"));
				repairs.setRepResult(rs.getString("repResult"));
				repairs.setRepEndTime(sdf.format(rs.getTimestamp("repEndTime")));
				//ʵ�������޼���
				repairsList = new ArrayList<>();
				//�����޶�����ӵ�������
				repairsList.add(repairs);
			}			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ر��޶���
		return repairsList;
	}

}
