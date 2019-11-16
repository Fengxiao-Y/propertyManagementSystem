package com.yidu.focus.hxl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.hxl.dao.CarInformationDao;
import com.yidu.focus.hxl.domain.CarInformation;

/**
 * 
 * 功能：车辆信息 
 * 作者：何稀龙 
 * 日期：2019年10月31日上午9:02:32 
 * 版本：1.0
 */
public class CarInformationDaoImpl implements CarInformationDao {
	/**
     * 添加信息
     * @param carInformation
     * @return
     */
	@Override
	public int add(CarInformation carInformation) {
		// 定义影响行数变量
		int rows = 0;
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql语句字符串 carId,carName,carTelphone,carportId carInformation
			String sql = "INSERT INTO carInformation(carId,carName,carTelphone,carportId) VALUES(?,?,?,?)";
			// 使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			// 给sql字符串中参数赋值
			pstmt.setString(1, carInformation.getCarId());
			pstmt.setString(2, carInformation.getCarName());
			pstmt.setString(3, carInformation.getCarTelphone());
			pstmt.setString(4, carInformation.getCarportId());
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
	 * @param carId
	 * @return
	 */
	@Override
	public int deleteById(String carId) {
		// 定义影响行数变量
		int rows = 0;
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql语句字符串 carId,carName,carTelphone,carportId carInformation
			String sql = "delete from carInformation where carId=?";
			// 使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			// 给sql字符串中参数赋值
			pstmt.setString(1, carId);
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
	 * 修改数据
	 * @param carInformation
	 * @return
	 */
	@Override
	public int update(CarInformation carInformation) {
		// 定义影响行数变量
		int rows = 0;
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql语句字符串 carId,carName,carTelphone,carportId carInformation
			String sql = "update carInformation set carName=?,carTelphone=?,carportId=? where carId=?";
			// 使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			// 给sql字符串中参数赋值

			pstmt.setString(1, carInformation.getCarName());
			pstmt.setString(2, carInformation.getCarTelphone());
			pstmt.setString(3, carInformation.getCarportId());
			pstmt.setString(4, carInformation.getCarId());
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
	 * 根据考勤编号查找单个员工信息
	 * @param carId
	 * @return
	 */
	@Override
	public CarInformation findById(String carId) {
		// 声明客户对象
		CarInformation carInformation = null;
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		// 声明结果集对象
		ResultSet rs = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql字符串 carId,carName,carTelphone,carportId carInformation
			String sql = "select carId,carName,carTelphone,carportId from carInformation where carId=?";
			// 使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			// 给sql字符串中参数赋值
			pstmt.setString(1, carId);
			// 执行预编译语句对象，得到结果集
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 实例化客户对象
				carInformation = new CarInformation();
				// 将结果集中数据保存到客户对象中
				carInformation.setCarId(rs.getString("carId"));
				carInformation.setCarName(rs.getString("carName"));
				carInformation.setCarTelphone(rs.getString("carTelphone"));
				carInformation.setCarportId(rs.getString("carportId"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// 关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		// 返回对象
		return carInformation;
	}
	/**
	 * 分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @return 指定页中所有车辆信息集合
	 */
	@Override
	public List<CarInformation> findByPage(int rows, int page) {
		// 定义客户对象集合
		List<CarInformation> carInformationList = new ArrayList<CarInformation>();
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		// 声明结果接对象
		ResultSet rs = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql语句 carId,carName,carTelphone,carportId carInformation
			String sql = "select * from carInformation limit ?,?";
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
				CarInformation carInformation = new CarInformation();
				// 将结果集的数据保存到客户对象中
				carInformation.setCarId(rs.getString("carId"));
				carInformation.setCarName(rs.getString("carName"));
				carInformation.setCarTelphone(rs.getString("carTelphone"));
				carInformation.setCarportId(rs.getString("carportId"));
				// 将客户对象添加到客户集合中
				carInformationList.add(carInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// 关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		// 返回客户集合
		return carInformationList;
	}
	/**
	 * 按条件分页查找
	 * @param rows 每页显示的记录数
	 * @param page 当前显示的页码数
	 * @param condition 条件
	 * @return
	 */
	@Override
	public List<CarInformation> findByPage(int rows, int page, String condition) {
		// 定义客户集合对象
		List<CarInformation> carInformationList = new ArrayList<CarInformation>();
		// 声明数据库连接对象
		Connection conn = null;
		// 声明预编译语句对象
		PreparedStatement pstmt = null;
		// 声明结果集对象
		ResultSet rs = null;
		try {
			// 实例化数据库连接对象
			conn = JdbcUtils.getConnection();
			// 定义sql字符串 carId,carName,carTelphone,carportId carInformation
			String sql = "select * from carInformation " + condition + " limit ?,?";
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
				CarInformation carInformation = new CarInformation();
				// 将结果集中的数据保存到客户对象中
				carInformation.setCarId(rs.getString("carId"));
				carInformation.setCarName(rs.getString("carName"));
				carInformation.setCarTelphone(rs.getString("carTelphone"));
				carInformation.setCarportId(rs.getString("carportId"));
				// 将对象添加到客户集合中
				carInformationList.add(carInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			// 关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		// 返回客户集合
		return carInformationList;
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
			// 定义sql字符串 carId,carName,carTelphone,carportId carInformation
			String sql = "select count(*) from carInformation";
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
			// 定义sql语句 carId,carName,carTelphone,carportId carInformation
			String sql = "select count(*) from carInformation " + condition;
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
