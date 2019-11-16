package com.yidu.focus.hj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.hj.dao.IncomeTotalDao;
import com.yidu.focus.hj.domain.IncomeTotal;
import com.yidu.focus.utils.JdbcUtils;
/**
 * 
 * ���ܣ�ʵ�������������
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��31������8:53:23
 */
public class IncomeTotalDaoImpl implements IncomeTotalDao {
	/**
	 * ����������ѯ
	 * @param rows ����
	 * @param page ����
	 * @param condition ����
	 * @return ����
	 */
	@Override
	public List<IncomeTotal> findByPage(int rows,int page,String condition) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		//��������
		List<IncomeTotal> incomeTotalList = new ArrayList<IncomeTotal>();
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			//����sqlִ�����
			String sql = "SELECT source,SUM(money) as money,DATE_FORMAT(source_date,'%Y-%m-%d') as source_date FROM income "+condition+" GROUP BY source,DATE_FORMAT(source_date,'%Y-%m-%d') ORDER BY source LIMIT ?,?";
			//ʵ����������
			pstmt = conn.prepareStatement(sql);
			//��sql��丳ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ʵ�������������
			rs = pstmt.executeQuery();
			while(rs.next()){
				IncomeTotal incomeTotal = new IncomeTotal();
				incomeTotal.setSource(rs.getString("source"));
				incomeTotal.setMoney(rs.getDouble("money"));
				incomeTotal.setMoneySum(incomeTotal.getMoney()+incomeTotal.getMoneySum());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				incomeTotal.setSource_date(sdf.format(rs.getDate("source_date")));				
				incomeTotalList.add(incomeTotal);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			JdbcUtils.close(rs, pstmt, conn);
		}
		return incomeTotalList;
	}
	/**
	 * ������ͳ�Ƽ�¼��
	 * @param condition ����
	 * @return ��¼��
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
			String sql = "select count(*) from income "+condition;
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
