package com.yidu.focus.ll.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.ll.dao.RulesDao;
import com.yidu.focus.ll.domain.Rules;
/**
 * 
 * ���ܣ������ƶȱ� 
 * ���ߣ�����
 * ���ڣ�2019��10��31������9:14:52
 * �汾��1.0
 */
public class RulesDaoImpl implements RulesDao {

	/**
	 * ��ʵ���������ӵ����ݿ����
	 * @param rules ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int add(Rules rules) {
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
			String sql="INSERT INTO Rules(rulesId,rulesTitle,rulesTheme,"
					+ "rulesStatus,rulesTime) VALUES(default,?,?,?,?)";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			
			pstmt.setString(1, rules.getRulesTitle());
			pstmt.setString(2, rules.getRulesTheme());
			pstmt.setString(3, rules.getRulesStatus());
			pstmt.setString(4, rules.getRulesTime());
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
	 * �����ļ���ţ�������ɾ������
	 * @param rulesId �ļ����
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int delete(String rulesId) {
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
			String sql="delete from rules where rulesId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, rulesId);
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
	 * @param rules ʵ�������
	 * @return Ӱ���������1�ɹ���0ʧ�ܣ�
	 */
	@Override
	public int update(Rules rules) {
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
			String sql="update rules set rulesTitle=?,rulesTheme=?,"
					+ "rulesStatus=?,rulesTime=? where rulesId=?";
			//ʹ��sql��估���ݿ����Ӷ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ			
			
			pstmt.setString(1, rules.getRulesTitle());
			pstmt.setString(2, rules.getRulesTheme());
			pstmt.setString(3, rules.getRulesStatus());
			//pstmt.setDate(4, java.sql.Date.valueOf(rules.getRulesTime()));
			pstmt.setString(4, rules.getRulesTime());
			pstmt.setString(5, rules.getRulesId());
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
	 * �����ļ���ţ������������ݿ���в�������
	 * @param rulesId �ļ����
	 * @return ����ʵ�����
	 */
	@Override
	public Rules findById(String rulesId) {
		//�����ļ�����
		Rules rules=null;
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
			String sql="select rulesId,rulesTitle,rulesTheme,rulesStatus,"
					+ "rulesTime from Rules where rulesId=?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setString(1, rulesId);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			if(rs.next()){
				//ʵ�����ļ�����
				rules=new Rules();
				//������������ݱ��浽�ļ�������
				rules.setRulesId(rs.getString("rulesId"));
				rules.setRulesTitle(rs.getString("rulesTitle"));
                rules.setRulesTheme(rs.getString("rulesTheme"));
                rules.setRulesStatus(rs.getString("rulesStatus"));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				rules.setRulesTime(sdf.format(rs.getDate("rulesTime")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ض���
		return rules;
	}

	/**
	 * ��ѯRules�������п�����Ϣ������װ�ɼ��Ϸ���
	 * @return 
	 */
	@Override
	public List<Rules> findAll() {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����������
		PreparedStatement pstmt=null;
		//������������� 
		ResultSet rs=null;
		//����һ������������� 
		List<Rules> rulesList=new ArrayList<Rules>();

		try {
			//1:ʵ�������Ӷ���
			conn=JdbcUtils.getConnection();
			//2:A:����Ҫִ�е�SQL����ַ���
			String sql="select  rulesId,rulesTitle,rulesTheme,rulesStatus,"
					            + "rulesTime from rules";
			//2:B:����SQL��������ݿ����ʵ����������
			pstmt=conn.prepareStatement(sql);
			//3:ִ�������󣬵õ����������
			rs=pstmt.executeQuery();
			//4:�Խ�������б���������������м�¼��ת�����ļ����󣬲���ӵ�������
			while(rs.next()){
				//�����ļ�����
				Rules rules=new Rules();
				//������������ݱ��浽�ļ�������
				rules.setRulesId(rs.getString("rulesId"));
				rules.setRulesTitle(rs.getString("rulesTitle"));
                rules.setRulesTheme(rs.getString("rulesTheme"));
                rules.setRulesStatus(rs.getString("rulesStatus"));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				rules.setRulesTime(sdf.format(rs.getDate("rulesTime")));
				//���ͻ�������ӵ��ļ�������
				rulesList.add(rules);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿ�����Ϣ����
		return rulesList;
	}

	/**
	 * ��ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Rules> findByPage(int rows, int page) {
		//�����ļ����󼯺�
		List<Rules> rulesList=new ArrayList<Rules>();
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
			String sql="select * from rules limit ?,?";
			//ʹ�����ݿ����Ӷ���sql�ַ���ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��sql�ַ����в�����ֵ
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//ִ��Ԥ���������󣬵õ������
			rs=pstmt.executeQuery();
			//ʹ��ѭ����������
			while(rs.next()){
				//�����ļ�����
				Rules rules=new Rules();
				//������������ݱ��浽�ļ�������
				rules.setRulesId(rs.getString("rulesId"));
				rules.setRulesTitle(rs.getString("rulesTitle"));
                rules.setRulesTheme(rs.getString("rulesTheme"));
                rules.setRulesStatus(rs.getString("rulesStatus"));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				rules.setRulesTime(sdf.format(rs.getDate("rulesTime")));
				//���ͻ�������ӵ��ļ�������
				rulesList.add(rules);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ؿͻ�����
		return rulesList;
	}

	/**
	 * ���ض�������ҳ����
	 * @param rows ����
	 * @param page ҳ��
	 * @param condition ��ѯ����
	 * @return ָ��ҳ�е����ݼ���
	 */
	@Override
	public List<Rules> findByPage(int rows, int page, String condition) {
		//�����ļ����϶���
		List<Rules> rulesList=new ArrayList<Rules>();
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
			String sql="select * from rules "+condition+" limit ?,?";
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
				Rules rules=new Rules();
				//������������ݱ��浽�ļ�������
				rules.setRulesId(rs.getString("rulesId"));
				rules.setRulesTitle(rs.getString("rulesTitle"));
                rules.setRulesTheme(rs.getString("rulesTheme"));
                rules.setRulesStatus(rs.getString("rulesStatus"));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				rules.setRulesTime(sdf.format(rs.getDate("rulesTime")));
				//���ͻ�������ӵ��ļ�������
				rulesList.add(rules);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�
			JdbcUtils.close(rs, pstmt, conn);
		}
		//�����ļ�����
		return rulesList;
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
			String sql="select count(*) from rules";
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
			String sql="select count(*) from rules "+condition;
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
