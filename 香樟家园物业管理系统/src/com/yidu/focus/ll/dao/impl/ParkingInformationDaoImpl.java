package com.yidu.focus.ll.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.ll.dao.ParkingInformationDao;
import com.yidu.focus.ll.domain.ParkingInformation;
/**
 * 
 * 功能：车位信息 
 * 作者：刘李
 * 日期：2019年10月31日上午9:14:30
 * 版本：1.0
 */
public class ParkingInformationDaoImpl implements ParkingInformationDao {

	/**
	 * 将实体类对象添加到数据库表中
	 * @param ParkingInformation 实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int add(ParkingInformation parkingInformation) {
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
			String sql="INSERT INTO ParkingInformation(parkId,parkStatus,parkMoney,"
					+ "houseId) VALUES(?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, parkingInformation.getParkId());
			pstmt.setString(2, parkingInformation.getParkStatus());
			pstmt.setDouble(3, parkingInformation.getParkMoney());
			pstmt.setString(4, parkingInformation.getHouseId());
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
	 * 按照车位编号（主键）删除数据
	 * @param parkId 车位编号
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int delete(String parkId) {
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
			String sql="delete from parkingInformation where parkId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, parkId);
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
	 * 将实体类对象更新到数据库
	 * @param parkingInformation 实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int update(ParkingInformation parkingInformation) {
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
			String sql="update parkingInformation set parkStatus=?,"
					+ "parkMoney=?,houseId=? where parkId=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值			
			
			pstmt.setString(1, parkingInformation.getParkStatus());
			pstmt.setDouble(2, parkingInformation.getParkMoney());
			pstmt.setString(3, parkingInformation.getHouseId());
			pstmt.setString(4, parkingInformation.getParkId());
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
	 * 按照车位信息（主键）从数据库表中查找数据
	 * @param parkId 车位编号
	 * @return 车位信息实体对象
	 */
	@Override
	public ParkingInformation findById(String parkId) {
		//声明车位信息对象
		ParkingInformation parkingInformation=null;
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
			String sql="select parkId,parkStatus,parkMoney,houseId from ParkingInformation where parkId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, parkId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化部门对象
				parkingInformation=new ParkingInformation();
				//将结果集中数据保存到部门对象中
				parkingInformation.setParkId(rs.getString("parkId"));
				parkingInformation.setParkStatus(rs.getString("parkStatus"));
				parkingInformation.setParkMoney(rs.getDouble("parkMoney"));
				parkingInformation.setHouseId(rs.getString("houseId"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return parkingInformation;
	}

	/**
	 * 查询parkingInformation表中所有车位信息，并封装成车位信息集合返回
	 * @return 车位信息集合
	 */
	@Override
	public List<ParkingInformation> findAll() {
		//声明数据库连接对象
		Connection conn=null;
		//声明语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象 
		ResultSet rs=null;
		//定义一个集合数组对象 
		List<ParkingInformation> parkingInformationList=new ArrayList<ParkingInformation>();

		try {
			//1:实例化连接对象
			conn=JdbcUtils.getConnection();
			//2:A:定义要执行的SQL语句字符串
			String sql="select  parkId,parkStatus,parkMoney,houseId from ParkingInformation";
			//2:B:根据SQL命令和数据库对象实例化语句对象
			pstmt=conn.prepareStatement(sql);
			//3:执行语句对象，得到结果集对象
			rs=pstmt.executeQuery();
			//4:对结果集进行遍历处理，将结果集中记录行转换成车位信息对象，并添加到集合中
			while(rs.next()){
				//定义对象
				ParkingInformation parkingInformation=new ParkingInformation();
				//将结果集的数据保存到车位信息对象中
				parkingInformation.setParkId(rs.getString("parkId"));
				parkingInformation.setParkStatus(rs.getString("parkStatus"));
				parkingInformation.setParkMoney(rs.getDouble("parkMoney"));
				parkingInformation.setHouseId(rs.getString("houseId"));
				//将部门对象添加到部门集合中
				parkingInformationList.add(parkingInformation);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回车位信息集合
		return parkingInformationList;

	}

	/**
	 * 分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<ParkingInformation> findByPage(int rows, int page) {
		//定义车位信息对象集合
		List<ParkingInformation> parkingInformationList=new ArrayList<ParkingInformation>();
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
			String sql="select * from ParkingInformation limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义客户对象
				ParkingInformation parkingInformation=new ParkingInformation();
				//将结果集的数据保存到车位信息对象中
				parkingInformation.setParkId(rs.getString("parkId"));
				parkingInformation.setParkStatus(rs.getString("parkStatus"));
				parkingInformation.setParkMoney(rs.getDouble("parkMoney"));
				parkingInformation.setHouseId(rs.getString("houseId"));
				//将部门对象添加到部门集合中
				parkingInformationList.add(parkingInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return parkingInformationList;
	}

	/**
	 * 按特定条件分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @param condition 查询条件
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<ParkingInformation> findByPage(int rows, int page, String condition) {
		//定义车位信息对象集合
		List<ParkingInformation> parkingInformationList=new ArrayList<ParkingInformation>();
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
			String sql="select * from ParkingInformation"+condition+"limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义客户对象
				ParkingInformation parkingInformation=new ParkingInformation();
				//将结果集的数据保存到车位信息对象中
				parkingInformation.setParkId(rs.getString("parkId"));
				parkingInformation.setParkStatus(rs.getString("parkStatus"));
				parkingInformation.setParkMoney(rs.getDouble("parkMoney"));
				parkingInformation.setHouseId(rs.getString("houseId"));
				//将部门对象添加到部门集合中
				parkingInformationList.add(parkingInformation);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return parkingInformationList;
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
			String sql="select count(*) from ParkingInformation";
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
			//定义sql字符串
			String sql="select count(*) from ParkingInformation"+condition;
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
	 * 按照车位信息（主键）从数据库表中查找数据
	 * @param parkId 车位编号
	 * @return 车位信息实体对象
	 */
	@Override
	public ParkingInformation findByName(String houseId) {
		//声明车位信息对象
		ParkingInformation parkingInformation=null;
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
			String sql="select parkId,parkStatus,parkMoney,houseId from ParkingInformation where houseId=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, houseId);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化部门对象
				parkingInformation=new ParkingInformation();
				//将结果集中数据保存到部门对象中
				parkingInformation.setParkId(rs.getString("parkId"));
				parkingInformation.setParkStatus(rs.getString("parkStatus"));
				parkingInformation.setParkMoney(rs.getDouble("parkMoney"));
				parkingInformation.setHouseId(rs.getString("houseId"));

			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return parkingInformation;
	}

}
