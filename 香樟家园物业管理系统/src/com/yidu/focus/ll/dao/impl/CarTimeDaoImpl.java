package com.yidu.focus.ll.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.ll.dao.CarTimeDao;
import com.yidu.focus.ll.domain.CarTime;
/**
 * 
 * 功能：临时停车 
 * 作者：刘李
 * 日期：2019年10月31日上午9:13:22
 * 版本：1.0
 */
public class CarTimeDaoImpl implements CarTimeDao {

	/**
	 * 将实体类对象添加到数据库表中
	 * @param carTime 车辆实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int add(CarTime carTime) {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql语句字符串
			String sql="INSERT INTO carTime(carId,goTime,"
					+ "toTime,Money,empNo) VALUES(?,?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, carTime.getCarId());
			pstmt.setString(2, carTime.getGoTime());
			pstmt.setString(3, carTime.getToTime());
			pstmt.setDouble(4, carTime.getMoney());
			pstmt.setString(5, carTime.getEmpNo());
			//执行预编译语句对象，得到影响行数
			rows=pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		//返回影响行数
		return rows;
	}

	/**
	 * 按照序号（主键）删除数据
	 * @param Nub 文件编号
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int delete(int Nub) {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql语句字符串
			String sql="delete from carTime where Nub=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, Nub);
			//执行预编译语句对象，得到影响行数
			rows=pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		//返回影响行数
		return rows;
	}

	/**
	 * 将进出时间实体类对象更新到数据库
	 * @param carTime 进出时间实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int update(CarTime carTime) {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql语句字符串
			String sql="update carTime set carId=?,goTime=?,toTime=?,"
					+ "Money=?, empNo=? where Nub=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值			
			pstmt.setString(1, carTime.getCarId());
			pstmt.setString(2, carTime.getGoTime());
			pstmt.setString(3, carTime.getToTime());
			pstmt.setDouble(4, carTime.getMoney());
			pstmt.setString(5, carTime.getEmpNo());
			pstmt.setInt(6, carTime.getNub());
			//执行预编译语句对象，得到影响行数
			rows=pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}
		//返回影响行数
		return rows;
	}

	/**
	 * 按照序号（主键）从数据库表中查找数据
	 * @param Nub 序号
	 * @return 车辆时间实体对象
	 */
	@Override
	public CarTime findById(int Nub) {
		//声明对象
		CarTime carTime=null;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql字符串
			String sql="select Nub,carId,goTime,toTime,Money,empNo from carTime where Nub=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, Nub);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化对象
				carTime=new CarTime();
				//将结果集中数据保存到对象中
				carTime.setNub(rs.getInt("Nub"));
				carTime.setCarId(rs.getString("carId"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carTime.setGoTime(sdf.format(rs.getDate("goTime")));
				
				carTime.setToTime(sdf.format(rs.getDate("toTime")));
				carTime.setMoney(rs.getDouble("Money"));
				carTime.setEmpNo(rs.getString("empNo"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return carTime;
	}

	/**
	 * 查询carTime表中所有车辆信息，并封装成车辆信息集合返回
	 * @return 车辆信息集合
	 */
	@Override
	public List<CarTime> findAll() {
		//声明数据库连接对象
		Connection conn=null;
		//声明语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象 
		ResultSet rs=null;
		//定义一个集合数组对象 
		List<CarTime> carTimeList=new ArrayList<CarTime>();

		try {
			//1:实例化连接对象
			conn=JdbcUtils.getConnection();
			//2:A:定义要执行的SQL语句字符串
			String sql="select  Nub,carId,goTime,toTime,Money,empNo from carTime";
			//2:B:根据SQL命令和数据库对象实例化语句对象
			pstmt=conn.prepareStatement(sql);
			//3:执行语句对象，得到结果集对象
			rs=pstmt.executeQuery();
			//4:对结果集进行遍历处理，将结果集中记录行转换成车辆对象，并添加到集合中
			while(rs.next()){
				//定义对象
				CarTime carTime=new CarTime();
				//将结果集中数据保存到对象中
				carTime.setNub(rs.getInt("Nub"));
				carTime.setCarId(rs.getString("carId"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carTime.setGoTime(sdf.format(rs.getTimestamp("goTime")));
				carTime.setToTime(sdf.format(rs.getTimestamp("toTime")));
				carTime.setMoney(rs.getDouble("Money"));
				carTime.setEmpNo(rs.getString("empNo"));
				//将部门对象添加到车辆集合中
				carTimeList.add(carTime);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回车辆信息集合
		return carTimeList;
	}


	/**
	 * 分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<CarTime> findByPage(int rows, int page) {
		//定义对象集合
		List<CarTime> carTimeList=new ArrayList<CarTime>();
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果接对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql语句
			String sql="select * from carTime limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义对象
				CarTime carTime=new CarTime();
				//将结果集中数据保存到对象中
				carTime.setNub(rs.getInt("Nub"));
				carTime.setCarId(rs.getString("carId"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carTime.setGoTime(sdf.format(rs.getTimestamp("goTime")));
				carTime.setToTime(sdf.format(rs.getTimestamp("toTime")));
				carTime.setMoney(rs.getDouble("Money"));
				carTime.setEmpNo(rs.getString("empNo"));
				//将车辆对象添加到集合中
				carTimeList.add(carTime);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return carTimeList;
	}

	/**
	 * 按特定条件分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @param condition 查询条件
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<CarTime> findByPage(int rows, int page, String condition) {
		//定义集合对象
		List<CarTime> carTimeList=new ArrayList<CarTime>();
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql字符串
			String sql="select * from carTime "+condition+" limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中的参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义对象
				CarTime carTime=new CarTime();
				//将结果集中数据保存到对象中
				carTime.setNub(rs.getInt("Nub"));
				carTime.setCarId(rs.getString("carId"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
				carTime.setGoTime(sdf.format(rs.getTimestamp("goTime")));
				carTime.setToTime(sdf.format(rs.getTimestamp("toTime")));
				carTime.setMoney(rs.getDouble("Money"));
				carTime.setEmpNo(rs.getString("empNo"));
				//将车辆对象添加到集合中
				carTimeList.add(carTime);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return carTimeList;
	}

	/**
	 * 统计数数据库表中数据的总行数
	 * @return 数据总行数
	 */
	@Override
	public int count() {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql字符串
			String sql="select count(*) from carTime";
			//使用数据库连接对象及sql字符串实例化语句对象
			pstmt=conn.prepareStatement(sql);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//判断结果集
			if(rs.next()){
				rows=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回行数
		return rows;
	}

	/**
	 * 按特定条件统计数数据库表中符合条件的数据行数
	 * @param condition 统计条件
	 * @return 返回符合条件的数据行数
	 */
	@Override
	public int count(String condition) {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql语句
			String sql="select count(*) from carTime "+condition;
			//使用数据库连接对象及 sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行预编译语句对象得到结果集
			rs=pstmt.executeQuery();
			//判断结果集
			if(rs.next()){
				rows=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回行数
		return rows;
	}

}
