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
 * 功能：设备巡检表 
 * 作者：何稀龙 
 * 日期：2019年10月31日上午9:03:06
 * 版本：1.0
 */
public class EqCheckDaoImpl implements EqCheckDao {
	/**
     * 添加信息
     * @param eqCheck
     * @return
     */
	@Override
	public int add(EqCheck eqCheck) {
		// 定义影响行数变量
		int rows = 0;
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql语句字符串
			String sql = "INSERT INTO eqCheck(eqId,checkStatues,checkDate,empNo) VALUES(?,?,?,?)";
			// 使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			// 给sql字符串中参数赋值
			pstmt.setString(1, eqCheck.getEqId());

			pstmt.setString(2, eqCheck.getCheckStatues());
			pstmt.setString(3, eqCheck.getCheckDate());
			pstmt.setString(4, eqCheck.getEmpNo());

			// 执行预编译语句对象，得到影响行数
			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// 关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		// 返回影响行数
		return rows;
	}
	/**
	 * 删除
	 * @param checkId
	 * @return
	 */
	@Override
	public int deleteById(int checkId) {
		// 定义影响行数变量
		int rows = 0;
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql语句字符串
			String sql = "delete from eqCheck where checkId=?";
			// 使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			// 给sql字符串中参数赋值
			pstmt.setInt(1, checkId);
			// 执行预编译语句对象，得到影响行数
			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// 关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		// 返回影响行数
		return rows;
	}
	/**
	 * 根据编号查找单个信息
	 * @param checkId
	 * @return
	 */
	@Override
	public int update(EqCheck eqCheck) {
		// 定义影响行数变量
		int rows = 0;
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql语句字符串
			String sql = "update eqCheck set eqId=?,checkStatues=?,checkDate=?,empNo=? where checkId=?";
			// 使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			// 给sql字符串中参数赋值
			pstmt.setString(1, eqCheck.getEqId());
			pstmt.setString(2, eqCheck.getCheckStatues());
			// 将字符串转换成数据库日期数据

			pstmt.setString(3, eqCheck.getCheckDate());
			pstmt.setString(4, eqCheck.getEmpNo());
			pstmt.setInt(5, eqCheck.getCheckId());
			// 执行预编译语句对象，得到影响行数
			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// 关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		// 返回影响行数
		return rows;
	}
	/**
	 * 根据编号查找单个信息
	 * @param attendId
	 * @return
	 */
	@Override
	public EqCheck findById(int checkId) {
		// 声明客户对象
		EqCheck eqCheck = null;
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		// 声明结果集对象
		ResultSet rs = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql字符串
			String sql = "select eqId,checkId,checkStatues,checkDate,empNo from eqCheck where checkId=?";
			// 使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			// 给sql字符串中参数赋值
			pstmt.setInt(1, checkId);
			// 执行预编译语句对象，得到结果集
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 实例化客户对象
				eqCheck = new EqCheck();
				// 将结果集中数据保存到客户对象中
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
			// 关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		// 返回对象
		return eqCheck;
	}
	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	@Override
	public List<EqCheck> findByPage(int rows, int page) {
		// 定义客户对象集合
		List<EqCheck> eqCheckList = new ArrayList<EqCheck>();
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		// 声明结果接对象
		ResultSet rs = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql语句
			String sql = "select * from eqCheck limit ?,?";
			// 使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			// 给sql字符串中参数赋值
			pstmt.setInt(1, (page - 1) * rows);
			pstmt.setInt(2, rows);
			// 执行预编译语句对象，得到结果集
			rs = pstmt.executeQuery();
			// 使用循环处理结果集
			while (rs.next()) {
				// 定义客户对象
				EqCheck eqCheck = new EqCheck();
				// 将结果集的数据保存到客户对象中
				eqCheck.setEqId(rs.getString("eqId"));
				eqCheck.setCheckId(rs.getInt("checkId"));
				eqCheck.setCheckStatues(rs.getString("checkStatues"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				eqCheck.setCheckDate(sdf.format(rs.getDate("checkDate")));
				eqCheck.setEmpNo(rs.getString("empNo"));
				// 将客户对象添加到客户集合中
				eqCheckList.add(eqCheck);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// 关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		// 返回客户集合
		return eqCheckList;
	}
	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	@Override
	public List<EqCheck> findByPage(int rows, int page, String condition) {
		// 定义客户集合对象
		List<EqCheck> eqCheckList = new ArrayList<EqCheck>();
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		// 声明结果集对象
		ResultSet rs = null;
		String empNo = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql字符串
			String sql = "select * from eqCheck " + condition + " limit ?,?";
			// 使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			// 给sql字符串中的参数赋值
			pstmt.setInt(1, (page - 1) * rows);
			pstmt.setInt(2, rows);
			// 执行预编译语句对象，得到结果集
			rs = pstmt.executeQuery();
			// 使用循环处理结果集
			while (rs.next()) {
				// 定义客户对象
				EqCheck eqCheck = new EqCheck();
				// 将结果集中的数据保存到客户对象中
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
				// 将对象添加到客户集合中
				eqCheckList.add(eqCheck);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// 关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		// 返回客户集合
		return eqCheckList;
	}
	/**
	 * 统计记录数
	 * @return 表中所有记录行数
	 */
	@Override
	public int count() {
		// 定义影响行数变量
		int rows = 0;
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		// 声明结果集对象
		ResultSet rs = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql字符串
			String sql = "select count(*) from eqCheck";
			// 使用数据库连接对象及sql字符串实例化语句对象
			pstmt = conn.prepareStatement(sql);
			// 执行预编译语句对象，得到结果集
			rs = pstmt.executeQuery();
			// 判断结果集
			if (rs.next()) {
				rows = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// 关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		// 返回行数
		return rows;
	}
	/**
	 * 统计符合条件的记录数
	 * @param condition 条件
	 * @return
	 */
	@Override
	public int count(String condition) {
		// 定义影响行数变量
		int rows = 0;
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		// 声明结果集对象
		ResultSet rs = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql语句
			String sql = "select count(*) from eqCheck " + condition;
			// 使用数据库连接对象及 sql字符串实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			// 执行预编译语句对象得到结果集
			rs = pstmt.executeQuery();
			// 判断结果集
			if (rs.next()) {
				rows = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// 关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		// 返回行数
		return rows;
	}

}
