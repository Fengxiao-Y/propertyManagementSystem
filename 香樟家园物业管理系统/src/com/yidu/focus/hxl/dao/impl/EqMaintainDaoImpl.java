package com.yidu.focus.hxl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hxl.dao.EqMaintainDao;
import com.yidu.focus.hxl.domain.EqMaintain;
import com.yidu.focus.ll.dao.EmpDao;
import com.yidu.focus.ll.dao.impl.EmpDaoImpl;
import com.yidu.focus.ll.domain.Emp;

/**
 * 
 * ���ܣ��豸Ѳ��� 
 * ���ߣ���ϣ��
 * ���ڣ�2019��10��31������9:03:35
 * �汾��1.0
 */
public class EqMaintainDaoImpl implements EqMaintainDao {
	 /**
     * �����Ϣ
     * @param eqMaintain
     * @return
     */
	@Override
	public int add(EqMaintain eqMaintain) {
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
			String sql="INSERT INTO eqMaintain(maintainId,eqId,maintainStatues,maintainDate,consumable,consumNum,checkCost,empNo) VALUES(?,?,?,?,?,?,?,?)";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, eqMaintain.getMaintainId());
			pstmt.setString(2, eqMaintain.getEqId());
			pstmt.setString(3, eqMaintain.getMaintainStatues());		
			pstmt.setString(4, eqMaintain.getMaintainDate());
			pstmt.setString(5, eqMaintain.getConsumable());
			pstmt.setInt(6, eqMaintain.getConsumNum());
			pstmt.setDouble(7, eqMaintain.getCheckCost());
			pstmt.setString(8, eqMaintain.getEmpNo());
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
	 * ɾ��
	 * @param maintainId
	 * @return
	 */
	@Override
	public int deleteById(String maintainId) {
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
			String sql="delete from eqMaintain where maintainId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, maintainId);
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
	 * �޸�����
	 * @param eqMaintain
	 * @return
	 */
	@Override
	public int update(EqMaintain eqMaintain) {
		//����Ӱ����������
		int rows=0;
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//����sql����ַ���		eqId,maintainStatues,maintainDate,consumable,checkCost,empNo   eqMaintain
			String sql="update eqMaintain set eqId=?,maintainStatues=?,maintainDate=?,consumable=?,consumNum=?,checkCost=?,empNo=? where maintainId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ			
			pstmt.setString(1, eqMaintain.getEqId());
			pstmt.setString(2, eqMaintain.getMaintainStatues());
			
			pstmt.setString(3, eqMaintain.getMaintainDate());
			pstmt.setString(4, eqMaintain.getConsumable());
			pstmt.setInt(5, eqMaintain.getConsumNum());
			pstmt.setDouble(6, eqMaintain.getCheckCost());
			pstmt.setString(7, eqMaintain.getEmpNo());
			pstmt.setString(8, eqMaintain.getMaintainId());
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
	 * ���ݿ��ڱ�Ų��ҵ���Ա����Ϣ
	 * @param maintainId
	 * @return
	 */
	@Override
	public EqMaintain findById(String maintainId) {
		//�����ͻ�����
		EqMaintain eqMaintain=null;
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
			String sql="select maintainId,eqId,maintainStatues,maintainDate,consumable,consumNum,checkCost,empNo from eqMaintain where maintainId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, maintainId);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�����ͻ�����
				eqMaintain=new EqMaintain();
				//������������ݱ��浽�ͻ�������
				eqMaintain.setMaintainId(rs.getString("maintainId"));
				eqMaintain.setEqId(rs.getString("eqId"));
				eqMaintain.setMaintainStatues(rs.getString("maintainStatues"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				eqMaintain.setMaintainDate(sdf.format(rs.getDate("maintainDate")));
				eqMaintain.setConsumable(rs.getString("consumable"));
				eqMaintain.setConsumNum(rs.getInt("consumNum"));
				eqMaintain.setCheckCost(rs.getDouble("checkCost"));
				eqMaintain.setEmpNo(rs.getString("empNo"));
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return eqMaintain;
	}
	/**
	 * ��ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @return ָ��ҳ�����г�����Ϣ����
	 */
	@Override
	public List<EqMaintain> findByPage(int rows, int page) {
		//����ͻ����󼯺�
		List<EqMaintain> eqMaintainList=new ArrayList<EqMaintain>();
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
			String sql="select * from eqMaintain limit ?,?";
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
				EqMaintain eqMaintain=new EqMaintain();
				//������������ݱ��浽�ͻ�������
				eqMaintain.setMaintainId(rs.getString("maintainId"));
				eqMaintain.setEqId(rs.getString("eqId"));
				eqMaintain.setMaintainStatues(rs.getString("maintainStatues"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				eqMaintain.setMaintainDate(sdf.format(rs.getDate("maintainDate")));
				eqMaintain.setConsumable(rs.getString("consumable"));
				eqMaintain.setConsumNum(rs.getInt("consumNum"));
				eqMaintain.setCheckCost(rs.getDouble("checkCost"));
				eqMaintain.setEmpNo(rs.getString("empNo"));
				//���ͻ�������ӵ��ͻ�������
				eqMaintainList.add(eqMaintain);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return eqMaintainList;
	}
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	@Override
	public List<EqMaintain> findByPage(int rows, int page, String condition) {
		//����ͻ����϶���
		List<EqMaintain> eqMaintainList=new ArrayList<EqMaintain>();
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		String empNo=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//����sql�ַ���
			String sql="select * from eqMaintain "+condition+" limit ?,?";
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
				EqMaintain eqMaintain=new EqMaintain();
				//��������е����ݱ��浽�ͻ�������
				eqMaintain.setMaintainId(rs.getString("maintainId"));
				eqMaintain.setEqId(rs.getString("eqId"));
				eqMaintain.setMaintainStatues(rs.getString("maintainStatues"));
				//���ڸ�ʽת��
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				eqMaintain.setMaintainDate(sdf.format(rs.getDate("maintainDate")));
				eqMaintain.setConsumable(rs.getString("consumable"));
				eqMaintain.setConsumNum(rs.getInt("consumNum"));
				eqMaintain.setCheckCost(rs.getDouble("checkCost"));
				eqMaintain.setEmpNo(rs.getString("empNo"));
				empNo= eqMaintain.getEmpNo();
				EmpDao empDao = new EmpDaoImpl();
				Emp emp = empDao.findById(empNo);
				eqMaintain.setEmpName(emp.getEmpName());
				
				//��������ӵ��ͻ�������
				eqMaintainList.add(eqMaintain);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return eqMaintainList;
	}
	/**
	 * ͳ�Ƽ�¼��
	 * @return �������м�¼����
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
			String sql="select count(*) from eqMaintain";
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
	 * ͳ�Ʒ��������ļ�¼��
	 * @param condition ����
	 * @return
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
			String sql="select count(*) from eqMaintain "+condition;
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
