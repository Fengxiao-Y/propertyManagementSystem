package com.yidu.focus.hxl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hxl.dao.EqCheckDao;
import com.yidu.focus.hxl.domain.EqCheck;
import com.yidu.focus.ll.dao.EmpDao;
import com.yidu.focus.ll.dao.impl.EmpDaoImpl;
import com.yidu.focus.ll.domain.Emp;

/**
 * 
 * ���ܣ��豸Ѳ��� 
 * ���ߣ���ϡ�� 
 * ���ڣ�2019��10��31������9:03:06
 * �汾��1.0
 */
public class EqCheckDaoImpl implements EqCheckDao {
	/**
     * �����Ϣ
     * @param eqCheck
     * @return
     */
	@Override
	public int add(EqCheck eqCheck) {
		// ����Ӱ����������
		int rows = 0;
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql����ַ���
			String sql = "INSERT INTO eqCheck(eqId,checkStatues,checkDate,empNo) VALUES(?,?,?,?)";
			// ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ��sql�ַ����в�����ֵ
			pstmt.setString(1, eqCheck.getEqId());

			pstmt.setString(2, eqCheck.getCheckStatues());
			pstmt.setString(3, eqCheck.getCheckDate());
			pstmt.setString(4, eqCheck.getEmpNo());

			// ִ��Ԥ���������󣬵õ�Ӱ������
			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		// ����Ӱ������
		return rows;
	}
	/**
	 * ɾ��
	 * @param checkId
	 * @return
	 */
	@Override
	public int deleteById(int checkId) {
		// ����Ӱ����������
		int rows = 0;
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql����ַ���
			String sql = "delete from eqCheck where checkId=?";
			// ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ��sql�ַ����в�����ֵ
			pstmt.setInt(1, checkId);
			// ִ��Ԥ���������󣬵õ�Ӱ������
			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		// ����Ӱ������
		return rows;
	}
	/**
	 * ���ݱ�Ų��ҵ�����Ϣ
	 * @param checkId
	 * @return
	 */
	@Override
	public int update(EqCheck eqCheck) {
		// ����Ӱ����������
		int rows = 0;
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql����ַ���
			String sql = "update eqCheck set eqId=?,checkStatues=?,checkDate=?,empNo=? where checkId=?";
			// ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ��sql�ַ����в�����ֵ
			pstmt.setString(1, eqCheck.getEqId());
			pstmt.setString(2, eqCheck.getCheckStatues());
			// ���ַ���ת�������ݿ���������

			pstmt.setString(3, eqCheck.getCheckDate());
			pstmt.setString(4, eqCheck.getEmpNo());
			pstmt.setInt(5, eqCheck.getCheckId());
			// ִ��Ԥ���������󣬵õ�Ӱ������
			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(null, pstmt, conn);
		}
		// ����Ӱ������
		return rows;
	}
	/**
	 * ���ݱ�Ų��ҵ�����Ϣ
	 * @param attendId
	 * @return
	 */
	@Override
	public EqCheck findById(int checkId) {
		// �����ͻ�����
		EqCheck eqCheck = null;
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		// �������������
		ResultSet rs = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql�ַ���
			String sql = "select eqId,checkId,checkStatues,checkDate,empNo from eqCheck where checkId=?";
			// ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ��sql�ַ����в�����ֵ
			pstmt.setInt(1, checkId);
			// ִ��Ԥ���������󣬵õ������
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// ʵ�����ͻ�����
				eqCheck = new EqCheck();
				// ������������ݱ��浽�ͻ�������
				eqCheck.setEqId(rs.getString("eqId"));
				eqCheck.setCheckId(rs.getInt("checkId"));
				eqCheck.setCheckStatues(rs.getString("checkStatues"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				eqCheck.setCheckDate(sdf.format(rs.getDate("checkDate")));
				eqCheck.setEmpNo(rs.getString("empNo"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		// ���ض���
		return eqCheck;
	}
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	@Override
	public List<EqCheck> findByPage(int rows, int page) {
		// ����ͻ����󼯺�
		List<EqCheck> eqCheckList = new ArrayList<EqCheck>();
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		// ��������Ӷ���
		ResultSet rs = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql���
			String sql = "select * from eqCheck limit ?,?";
			// ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page - 1) * rows);
			pstmt.setInt(2, rows);
			// ִ��Ԥ���������󣬵õ������
			rs = pstmt.executeQuery();
			// ʹ��ѭ����������
			while (rs.next()) {
				// ����ͻ�����
				EqCheck eqCheck = new EqCheck();
				// ������������ݱ��浽�ͻ�������
				eqCheck.setEqId(rs.getString("eqId"));
				eqCheck.setCheckId(rs.getInt("checkId"));
				eqCheck.setCheckStatues(rs.getString("checkStatues"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				eqCheck.setCheckDate(sdf.format(rs.getDate("checkDate")));
				eqCheck.setEmpNo(rs.getString("empNo"));
				// ���ͻ�������ӵ��ͻ�������
				eqCheckList.add(eqCheck);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		// ���ؿͻ�����
		return eqCheckList;
	}
	/**
	 * ��������ҳ����
	 * @param rows ÿҳ��ʾ�ļ�¼��
	 * @param page ��ǰ��ʾ��ҳ����
	 * @param condition ����
	 * @return
	 */
	@Override
	public List<EqCheck> findByPage(int rows, int page, String condition) {
		// ����ͻ����϶���
		List<EqCheck> eqCheckList = new ArrayList<EqCheck>();
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		// �������������
		ResultSet rs = null;
		String empNo = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql�ַ���
			String sql = "select * from eqCheck " + condition + " limit ?,?";
			// ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ��sql�ַ����еĲ�����ֵ
			pstmt.setInt(1, (page - 1) * rows);
			pstmt.setInt(2, rows);
			// ִ��Ԥ���������󣬵õ������
			rs = pstmt.executeQuery();
			// ʹ��ѭ����������
			while (rs.next()) {
				// ����ͻ�����
				EqCheck eqCheck = new EqCheck();
				// ��������е����ݱ��浽�ͻ�������
				eqCheck.setEqId(rs.getString("eqId"));
				eqCheck.setCheckId(rs.getInt("checkId"));
				eqCheck.setCheckStatues(rs.getString("checkStatues"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				eqCheck.setCheckDate(sdf.format(rs.getDate("checkDate")));
				eqCheck.setEmpNo(rs.getString("empNo"));
				empNo = eqCheck.getEmpNo();
				EmpDao empDao = new EmpDaoImpl();
				Emp emp = empDao.findById(empNo);
				eqCheck.setEmpName(emp.getEmpName());
				// ��������ӵ��ͻ�������
				eqCheckList.add(eqCheck);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		// ���ؿͻ�����
		return eqCheckList;
	}
	/**
	 * ͳ�Ƽ�¼��
	 * @return �������м�¼����
	 */
	@Override
	public int count() {
		// ����Ӱ����������
		int rows = 0;
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		// �������������
		ResultSet rs = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql�ַ���
			String sql = "select count(*) from eqCheck";
			// ʹ�����ݿ����Ӷ���sql�ַ���ʵ����������
			pstmt = conn.prepareStatement(sql);
			// ִ��Ԥ���������󣬵õ������
			rs = pstmt.executeQuery();
			// �жϽ����
			if (rs.next()) {
				rows = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		// ��������
		return rows;
	}
	/**
	 * ͳ�Ʒ��������ļ�¼��
	 * @param condition ����
	 * @return
	 */
	@Override
	public int count(String condition) {
		// ����Ӱ����������
		int rows = 0;
		// �������ݿ����Ӷ���
		Connection conn = null;
		// ����Ԥ����������
		PreparedStatement pstmt = null;
		// �������������
		ResultSet rs = null;
		try {
			// ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnection();
			// ����sql���
			String sql = "select count(*) from eqCheck " + condition;
			// ʹ�����ݿ����Ӷ��� sql�ַ���ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			// ִ��Ԥ����������õ������
			rs = pstmt.executeQuery();
			// �жϽ����
			if (rs.next()) {
				rows = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// �ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		// ��������
		return rows;
	}

}
