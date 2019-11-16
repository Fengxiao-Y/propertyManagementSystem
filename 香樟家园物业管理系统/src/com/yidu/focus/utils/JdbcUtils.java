package com.yidu.focus.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * �������������ݿ����ӹ����࣬ʵ��Java���ӵ����ݿ⣬���ر�����
 * @author t262
 * ���ڣ�2018��10��29�� ����10:33:59
 * version:1.0
 */
public class JdbcUtils {
	//���ݿ���������
	private static String driver="com.mysql.cj.jdbc.Driver";
	//�����ַ���
	private static String url="jdbc:mysql://localhost:3306/focusdb?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
	//�û���
	private static String user="root";
	//��������
	private static String password="hxl760320";
	
	//��̬�飬ʵ�������ʱ���������ݿ����������
	static{
		try {
			//ע����������
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���췽��
	 */
	private JdbcUtils(){
		
	}
	
	/**
	 * ���ݿ����ӷ���
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * �ͷ����ݿ����ӡ������󡢽����������Դ
	 * @param rs ���������
	 * @param pstmt ������
	 * @param conn ���ݿ����Ӷ���
	 */
	public static void close(ResultSet rs,Statement pstmt,Connection conn){
		try {
			//������������Ϊ�գ���ر�
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//���������Ϊ�գ���ر�
			try {
				if(pstmt!=null){
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				//������Ӷ���Ϊ�գ���ر�
				try {
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
