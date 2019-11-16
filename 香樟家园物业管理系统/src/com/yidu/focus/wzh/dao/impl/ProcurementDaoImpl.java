
package com.yidu.focus.wzh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.wzh.dao.ProcurementDao;
import com.yidu.focus.wzh.domain.Procurement;

/**
 * ���ܣ�������ݱ�Ĺ��ܲ���
 * ���ߣ���־��
 * ���ڣ�2019��10��12������2:26:57
 * �汾��1.0
 */
public class ProcurementDaoImpl implements ProcurementDao{

	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param procurement �ɹ�ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int add(Procurement procurement) {
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
			String sql="INSERT INTO procurement VALUES(?,?,?,?,?,?,?)";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, procurement.getProId());
			pstmt.setString(2, procurement.getProName());
			pstmt.setString(3, procurement.getGoodsName());
			//���ַ���ת�������ݿ���������
			pstmt.setString(4, procurement.getProTime());
			pstmt.setInt(5, procurement.getProNum());


			pstmt.setDouble(6, procurement.getProPrice());
			pstmt.setDouble(7, procurement.getProTolal());

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
	 * ���ղɹ���ţ�������ɾ������
	 * @param uId �û����
	 * @return rows Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int delete(String proId) {
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
			String sql="delete from procurement where proId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, proId);
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
	 * @param procurement �ɹ�ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int update(Procurement procurement) {
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
			String sql="update procurement set proName=?,goodsName=?,proTime=?,proNum=?,proPrice=?,proTolal=? where proId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ	
			pstmt.setString(1, procurement.getProName());
			pstmt.setString(2, procurement.getGoodsName());
			//���ַ���ת�������ݿ���������
			pstmt.setString(3, procurement.getProTime());
			pstmt.setInt(4, procurement.getProNum());
			pstmt.setDouble(5, procurement.getProPrice());
			pstmt.setDouble(6, procurement.getProTolal());
			//pstmt.setString(7, procurement.getEmpNo());
			pstmt.setString(7, procurement.getProId());
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
	 * ���ղɹ���ţ������������ݿ���в�������
	 * @param proId �ɹ����
	 * @return �ɹ�ʵ�����
	 */
	@Override
	public Procurement findById(String proId) {
		//�����ɹ�����
		Procurement procurement=null;
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
			String sql="select * from procurement where proId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, proId);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�����ɹ�����
				procurement=new Procurement();
				//������������ݱ��浽������
				procurement.setProId(rs.getString("proId"));
				procurement.setProName(rs.getString("proName"));
				procurement.setGoodsName(rs.getString("goodsName"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				procurement.setProTime(sdf.format(rs.getDate("proTime")));
				procurement.setProNum(rs.getInt("proNum"));
				procurement.setProTolal(rs.getDouble("proTolal"));
				procurement.setProTolal(rs.getDouble("proTolal"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return procurement;
	}

	/**
	 * ��ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Procurement> findByPage(int rows, int page) {
		//����ɹ����󼯺�
		List<Procurement> procurementList=new ArrayList<Procurement>();
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
			String sql="select * from  procurement limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//����ɹ�����
				Procurement  procurement=new Procurement();
				//������������ݱ��浽�ɹ�������
				procurement=new Procurement();
				//������������ݱ��浽������
				procurement.setProId(rs.getString("proId"));
				procurement.setProName(rs.getString("proName"));
				procurement.setGoodsName(rs.getString("goodsName"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				procurement.setProTime(sdf.format(rs.getDate("proTime")));
				procurement.setProNum(rs.getInt("proNum"));
				procurement.setProPrice(rs.getDouble("proPrice"));
				procurement.setProTolal(rs.getDouble("proTolal"));
				//���ɹ�������ӵ��ɹ�������
				procurementList.add(procurement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���زɹ�����
		return procurementList;
	}

	/**
	 * ���ض�������ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @param condition ��ѯ����
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Procurement> findByPage(int rows, int page, String condition) {
		//����ɹ����󼯺�
		List<Procurement> procurementList=new ArrayList<Procurement>();
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
			String sql="select * from  procurement "+condition+" limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//����ɹ�����
				Procurement  procurement=new Procurement();
				//������������ݱ��浽�ɹ�������
				procurement=new Procurement();
				//������������ݱ��浽������
				procurement.setProId(rs.getString("proId"));
				procurement.setProName(rs.getString("proName"));
				procurement.setGoodsName(rs.getString("goodsName"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				procurement.setProTime(sdf.format(rs.getDate("proTime")));
				procurement.setProNum(rs.getInt("proNum"));
				procurement.setProPrice(rs.getDouble("proPrice"));
				procurement.setProTolal(rs.getDouble("proTolal"));
				//���ɹ�������ӵ��ɹ�������
				procurementList.add(procurement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���زɹ�����
		return procurementList;
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
			String sql="select count(*) from procurement";
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
			String sql="select count(*) from procurement "+condition;
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