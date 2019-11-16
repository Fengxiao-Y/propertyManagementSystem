
package com.yidu.focus.wzh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.wzh.dao.GetGoodsDao;
import com.yidu.focus.wzh.domain.GetGoods;

/**
 * ���ܣ�GetGoods������ݿ�Ĺ��ܲ���
 * ���ߣ���־��
 * ���ڣ�2019��10��12������9:37:33
 * �汾��1.0
 */
public class GetGoodsDaoImpl implements GetGoodsDao{

	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param getgoods ��������ʵ�������
	 * @return rows Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int add(GetGoods getgoods) {
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
			String sql="INSERT INTO getgoods(goodsName,ggNum,ggName,ggTime,ggHandli) VALUES(?,?,?,?,?)";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, getgoods.getGoodsName());
			pstmt.setInt(2, getgoods.getggNum());
			pstmt.setString(3, getgoods.getggName());
			pstmt.setString(4, getgoods.getggTime());
			pstmt.setString(5, getgoods.getggHandli());
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
	 * �������ñ�ţ�������ɾ������
	 * @param uId �û����
	 * @return rows Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int delete(int ggId) {
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
			String sql="delete from getgoods where ggId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, ggId);
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
	 * @param getgoods ��������ʵ�������
	 * @return rows Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int update(GetGoods getgoods) {
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
			String sql="update getgoods set goodsName=?,ggNum=?,ggName=?,ggTime=?,ggHandli=? where ggId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ			
			pstmt.setString(1, getgoods.getGoodsName());
			pstmt.setInt(2, getgoods.getggNum());
			pstmt.setString(3, getgoods.getggName());
			pstmt.setString(4, getgoods.getggTime());
			pstmt.setString(5, getgoods.getggHandli());
			pstmt.setInt(6, getgoods.getGgId());
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
	 * �����������ñ�ţ������������ݿ���в�������
	 * @param cid �������ñ��
	 * @return getgoods��������ʵ�����
	 */
	@Override
	public GetGoods findById(int ggId) {
		//�����������ö���
		GetGoods getgoods=null;
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
			String sql="select * from getgoods where ggId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, ggId);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�����������ö���
				getgoods=new GetGoods();
				//������������ݱ��浽�������ö�����
				getgoods.setGgId(rs.getInt("ggId"));
				getgoods.setGoodsName(rs.getString("goodsName"));
				getgoods.setggNum(rs.getInt("ggNum"));
				getgoods.setggName(rs.getString("ggName"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				getgoods.setggTime(sdf.format(rs.getTimestamp("ggTime")));
				getgoods.setggHandli(rs.getString("ggHandli"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return getgoods;
	}
	/**
	 * ��ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @return  getgoodsList ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<GetGoods> findByPage(int rows, int page) {
		//�����������ö��󼯺�
		List<GetGoods> getgoodsList=new ArrayList<GetGoods>();
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
			String sql="select * from getgoods limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//�����������ö���
				GetGoods getgoods=new GetGoods();
				//������������ݱ��浽�������ö�����
				getgoods.setGgId(rs.getInt("ggId"));
				getgoods.setGoodsName(rs.getString("goodsName"));
				getgoods.setggNum(rs.getInt("ggNum"));
				getgoods.setggName(rs.getString("ggName"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				getgoods.setggTime(sdf.format(rs.getTimestamp("ggTime")));
				getgoods.setggHandli(rs.getString("ggHandli"));
				//���������ö�����ӵ��������ü�����
				getgoodsList.add(getgoods);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//�����������ü���
		return getgoodsList;
	}
	/**
	 * ���ض�������ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @param condition ��ѯ����
	 * @return getgoodsList ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<GetGoods> findByPage(int rows, int page, String condition) {
		//�����������ö��󼯺�
		List<GetGoods> getgoodsList=new ArrayList<GetGoods>();
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
			String sql="select * from getgoods "+condition+" limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//�����������ö���
				GetGoods getgoods=new GetGoods();
				//������������ݱ��浽�������ö�����
				getgoods.setGgId(rs.getInt("ggId"));
				getgoods.setGoodsName(rs.getString("goodsName"));
				getgoods.setggNum(rs.getInt("ggNum"));
				getgoods.setggName(rs.getString("ggName"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				getgoods.setggTime(sdf.format(rs.getTimestamp("ggTime")));
				getgoods.setggHandli(rs.getString("ggHandli"));
				//���������ö�����ӵ��������ü�����
				getgoodsList.add(getgoods);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//�����������ü���
		return getgoodsList;
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
			String sql="select count(*) from getgoods";
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
			String sql="select count(*) from getgoods "+condition;
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
