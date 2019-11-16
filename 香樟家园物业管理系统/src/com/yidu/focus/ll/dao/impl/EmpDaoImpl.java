package com.yidu.focus.ll.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yidu.focus.utils.JdbcUtils;
import com.yidu.focus.ll.dao.EmpDao;
import com.yidu.focus.ll.domain.Emp;
/**
 * 
 * 功能：员工表 
 * 作者：刘李
 * 日期：2019年10月31日上午9:14:13
 * 版本：1.0
 */
public class EmpDaoImpl implements EmpDao{

	/**
	 * 将实体类对象添加到数据库表中
	 * @param emp 实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int add(Emp emp) {
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
			String sql="insert into emp(empNo,empName,"
					+ " empSex,empTelphone,empIdcard,hireDate,"
					+ " salary,commision,deptNo,"
					+ " manager) values(?,?,?,?,?,?,?,?,?,?)";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpSex());
			pstmt.setString(4, emp.getEmpTelphone());
			pstmt.setString(5, emp.getEmpIdcard());
			//将字符串转换成日期数据
			pstmt.setString(6, emp.getHireDate());
			
			pstmt.setDouble(7, emp.getSalary());
			pstmt.setDouble(8, emp.getCommision());
			pstmt.setString(9, emp.getDeptNo());
			pstmt.setString(10, emp.getManager());
			//执行预编译语句对象，得到影响行数
			rows=pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}//返回影响行数
		return rows;
	}

	/**
	 * 按照员工编号（主键）删除数据
	 * @param emp 员工编号
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int delete(String empNo) {
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
			String sql="delete from emp where empNo=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, empNo);
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
	 * @param emp 实体类对象
	 * @return 影响的行数（1成功，0失败）
	 */
	@Override
	public int update(Emp emp) {
		//定义影响行数变量
		int rows=0;
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//定义sql字符串
			String sql="update emp set empName=?,"
					+ "empSex=?,empTelphone=?,empIdcard=?,hireDate=?,"
					+ "salary=?,commision=?,deptNo=?,"
					+ "manager=? where empNo=?";
			//使用sql语句及数据库连接对象实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getEmpSex());
			pstmt.setString(3, emp.getEmpTelphone());
			pstmt.setString(4, emp.getEmpIdcard());
			//将字符串转换成日期数据
			pstmt.setString(5,emp.getHireDate());
			pstmt.setDouble(6, emp.getSalary());
			pstmt.setDouble(7, emp.getCommision());
			pstmt.setString(8, emp.getDeptNo());
			pstmt.setString(9, emp.getManager());
			pstmt.setString(10, emp.getEmpNo());
			
			//执行预编译语句对象，得到影响行数
			rows=pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(null, pstmt, conn);
		}//返回影响行数
		return rows;
	}

	/**
	 * 按照员工编号（主键）从数据库表中查找数据
	 * @param empNo 员工编号
	 * @return 员工实体对象
	 */
	@Override
	public Emp findById(String empNo) {
		//声明文件对象
		Emp emp=null;
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
			String sql="select * from emp where empNo=?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setString(1, empNo);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			if(rs.next()){
				//实例化文件对象
				emp=new Emp();
				//将结果集中数据保存到文件对象中
				emp.setEmpNo(rs.getString("empNo"));
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpSex(rs.getString("empSex"));
				emp.setEmpTelphone(rs.getString("empTelphone"));
				emp.setEmpIdcard(rs.getString("empIdcard"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				emp.setHireDate(sdf.format(rs.getDate("hireDate")));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommision(rs.getDouble("commision"));
				emp.setDeptNo(rs.getString("deptno"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回对象
		return emp;
	}

	/**
	 * 查询Emp表中所有员工信息，并封装成员工信息集合返回
	 * @return 员工信息集合
	 */
	@Override
	public List<Emp> findAll() {
		//声明数据库连接对象
		Connection conn=null;
		//声明语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象 
		ResultSet rs=null;
		//定义一个集合数组对象 
		List<Emp> empList=new ArrayList<Emp>();

		try {
			//1:实例化连接对象
			conn=JdbcUtils.getConnection();
			//2:A:定义要执行的SQL语句字符串
			String sql="select  empNo,empName,empSex,empTelphone,empIdcard,hireDate,"
					+ "salary,commision,deptno from emp";
			//2:B:根据SQL命令和数据库对象实例化语句对象
			pstmt=conn.prepareStatement(sql);
			//3:执行语句对象，得到结果集对象
			rs=pstmt.executeQuery();
			//4:对结果集进行遍历处理，将结果集中记录行转换成车辆对象，并添加到集合中
			while(rs.next()){
				//定义对象
				Emp emp=new Emp();
				//将结果集的数据保存到文件对象中
				emp.setEmpNo(rs.getString("empNo"));
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpSex(rs.getString("empSex"));
				emp.setEmpTelphone(rs.getString("empTelphone"));
				emp.setEmpIdcard(rs.getString("empIdcard"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				emp.setHireDate(sdf.format(rs.getDate("hireDate")));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommision(rs.getDouble("commision"));
				emp.setDeptNo(rs.getString("deptno"));
				empList.add(emp);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//5:关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回员工信息集合
		return empList;
	}

	/**
	 * 分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<Emp> findByPage(int rows, int page) {
		//定义文件对象集合
		List<Emp> empList=new ArrayList<Emp>();
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
			String sql="select * from emp limit ?,?";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义员工对象
				Emp emp=new Emp();
				//将结果集的数据保存到文件对象中
				emp.setEmpNo(rs.getString("empNo"));
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpSex(rs.getString("empSex"));
				emp.setEmpTelphone(rs.getString("empTelphone"));
				emp.setEmpIdcard(rs.getString("empIdcard"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				emp.setHireDate(sdf.format(rs.getDate("hireDate")));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommision(rs.getDouble("commision"));
				emp.setDeptNo(rs.getString("deptno"));
				emp.setManager(rs.getString("manager"));
				empList.add(emp);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回客户集合
		return empList;
	}

	/**
	 * 按特定条件分页查找
	 * @param rows 行数
	 * @param page 页数
	 * @param condition 查询条件
	 * @return 指定页中的数据集合
	 */
	@Override
	public List<Emp> findByPage(int rows, int page, String condition) {
		//定义文件集合对象
		List<Emp> empList=new ArrayList<Emp>();
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
			String sql="select * from emp "+condition+" limit ?,? ";
			//使用数据库连接对象及sql字符串实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给sql字符串中的参数赋值
			pstmt.setInt(1, (page-1)*rows);
			pstmt.setInt(2, rows);
			//执行预编译语句对象，得到结果集
			rs=pstmt.executeQuery();
			//使用循环处理结果集
			while(rs.next()){
				//定义文件对象
				Emp emp=new Emp();
				//将结果集的数据保存到文件对象中
				emp.setEmpNo(rs.getString("empNo"));
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpSex(rs.getString("empSex"));
				emp.setEmpTelphone(rs.getString("empTelphone"));
				emp.setEmpIdcard(rs.getString("empIdcard"));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				emp.setHireDate(sdf.format(rs.getDate("hireDate")));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommision(rs.getDouble("commision"));
				
				if(rs.getString("deptno").equals("xz")){
					emp.setDeptNo("行政部");
				}else if(rs.getString("deptno").equals("ba")){
					emp.setDeptNo("保安部");
				}else if(rs.getString("deptno").equals("gc")){
					emp.setDeptNo("工程部");
				}else if(rs.getString("deptno").equals("cw")){
					emp.setDeptNo("财务部");
				}else if(rs.getString("deptno").equals("kf")){
					emp.setDeptNo("客服部");
				}
				emp.setManager(rs.getString("manager"));
				//将客户对象添加到文件集合中
				empList.add(emp);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回员工集合
		return empList;
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
			String sql="select count(*) from emp";
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
			String sql="select count(*) from emp "+condition;
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
