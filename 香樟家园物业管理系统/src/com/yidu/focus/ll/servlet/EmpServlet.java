package com.yidu.focus.ll.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yidu.focus.ll.dao.DeptDao;
import com.yidu.focus.ll.dao.EmpDao;
import com.yidu.focus.ll.dao.impl.DeptDaoImpl;
import com.yidu.focus.ll.dao.impl.EmpDaoImpl;
import com.yidu.focus.ll.domain.Dept;
import com.yidu.focus.ll.domain.Emp;
/**
 * 
 * 功能：员工表 
 * 作者：刘李
 * 日期：2019年10月31日上午9:10:59
 * 版本：1.0
 */
@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求和响应的字符集及内容类型
		//设置请求的字符集
		request.setCharacterEncoding("utf-8");
		//设置响应的字符集和内容类型
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//获取请求路径中的操作参数值
		String method=request.getParameter("method");
		
		//判断
		if("findAll".equals(method)){
			this.findAll(request,response);
		}else if("add".equals(method)){
			this.add(request,response);
		}else if("update".equals(method)){
			this.update(request,response);
		}else if("delete".equals(method)){
			this.delete(request,response);
		}
	}

	/**
	 * 根据主键删除数据
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 抛出异常
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//获取来自请中所传递过来的员工编号字符串
		String empNoStr=request.getParameter("empNoStr");
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] empNos=empNoStr.split(",");
		//创建数据层操作对象
		EmpDao empdao=new EmpDaoImpl();
		//由于是批量删除，有可能失败，此处使用异常处理来实现
		try{
			//使用循环进行批量删除
			for(int i=0;i<empNos.length;i++){
				//获取当前子字符串
				String empNo=empNos[i];
				//执行删除操作
				empdao.delete(empNo);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}
		//关闭输出对象
		out.close();
	}

	/**
	 * 修改
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 抛出异常
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//编号
		String empNo=request.getParameter("empNo");
		//姓名
		String empName=request.getParameter("empName");
		//性别
		String empSex="";
		int sex=Integer.parseInt(request.getParameter("empSex"));
		if(sex==1){
			empSex="男";
		}else{
			empSex="女";
		}
		//电话
		String empTelphone=request.getParameter("empTelphone");
		//身份证号码
		String empIdcard=request.getParameter("empIdcard");
		//入职时间
		String hireDate=request.getParameter("hireDate");
		//薪水
		double salary=Double.parseDouble(request.getParameter("salary"));
		//补贴
		double commision=Double.parseDouble(request.getParameter("commision"));
		//部门
		String deptNo=request.getParameter("deptNo");
		//上司
		String manager=request.getParameter("manager");
		//数据封装成对象
		Emp emp=new Emp(empNo, empName, empSex, empTelphone, empIdcard, hireDate, salary, commision, deptNo, manager);
		//调用数据层对象执行添加方法
		EmpDao empdao=new EmpDaoImpl();
		int rows=empdao.update(emp);
		//判断添加是否成功
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//关闭输出流
		out.close();
	}

	/**
	 * 增加
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 抛出异常
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//编号
		String empNo=request.getParameter("empNo");
		//姓名
		String empName=request.getParameter("empName");
		//性别
		String empSex="";
		int sex=Integer.parseInt(request.getParameter("empSex"));
		if(sex==1){
			empSex="男";
		}else{
			empSex="女";
		}
		//电话
		String empTelphone=request.getParameter("empTelphone");
		//身份证号码
		String empIdcard=request.getParameter("empIdcard");
		//入职时间
		String hireDate=request.getParameter("hireDate");
		//薪水
		double salary=Double.parseDouble(request.getParameter("salary"));
		//补贴
		double commision=Double.parseDouble(request.getParameter("commision"));
		//部门
		String deptNo=request.getParameter("deptNo");
		//上司
		String manager=request.getParameter("manager");		
		//定义一个统计在编人数的条件
		String condition="where deptNo='"+deptNo+"' ";
		//数据封装成对象
		Emp emp=new Emp(empNo, empName, empSex, empTelphone, empIdcard, hireDate,
				salary,commision, deptNo,manager);
		//定义员工数据层操作对象
		EmpDao empDao=new EmpDaoImpl();
		//调用添加的方法
		int rows=empDao.add(emp);
		
		/*添加新员工时将改变部门的在编人数*/
		//调用统计的方法
		int empCount = empDao.count(condition);
		//定义部门名称
		String deptName = "";
		if(deptNo.equals("xz")){
			deptName = "行政";
			empNo="xz-001";
		}else if(deptNo.equals("ba")){
			deptName = "保安";
			empNo="ba-001";
		}else if(deptNo.equals("kf")){
			deptName = "客服";
			empNo="kf-001";
		}else if(deptNo.equals("gc")){
			deptName = "工程";
			empNo="gc-001";
		}else if(deptNo.equals("cw")){
			deptName = "财务";
			empNo="cw-001";
		}
		//封装部门对象
		Dept dept = new Dept(deptNo, deptName, empNo, empCount);
		//定义部门表数据层操作对象
		DeptDao deptDao = new DeptDaoImpl();
		//调用更新的方法
		deptDao.update(dept);
		//判断添加是否成功
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//关闭输出流
		out.close();

	}

	/**
	 *分页显示
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException  抛出对象
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//得到来自请求中的条件数据
		//员工编号
		String empNo=request.getParameter("empNo");
		//员工姓名
		String empName=request.getParameter("empName");
		//部门编号
		String deptNo=request.getParameter("deptNo");
		
		//生成查询条件
		String condition=" where 1=1 ";
		//根据员工编号模糊查询
		if(empNo!=null && !empNo.equals("")){
			condition=condition+"and empNo like '%"+empNo+"%' ";
		}
		//根据员工姓名模糊查询
		if(empName!=null && !empName.equals("")){
			condition=condition+"and empName like '%"+empName+"%' ";
		}
		//根据部门编号模糊查询
		if(deptNo!=null && !deptNo.equals("")){
			condition=condition+"and deptNo like '%"+deptNo+"%' ";
		}

		//接收来自客户端的datagrid组件的传递过来的page和rows参数
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));

		//调用数据层执行分页查询
		EmpDao empdao=new EmpDaoImpl();
		//获得当前页的数据集合
		List<Emp> emplist=empdao.findByPage(rows, page, condition);
				
		//查询出emp表的总记录数
		int totalRows=empdao.count();
		//定义映射集合
		Map<String, Object> mapData = new HashMap<String, Object>();
		//将数据保存到map集合中
		mapData.put("total", totalRows);
		mapData.put("rows", emplist);
		//定义Gson对象
		Gson gson = new Gson();
		//通过Gson对象将Map集合转换成json数据格式
		String jsonData = gson.toJson(mapData);
		//将json数据输出到客户端
		out.println(jsonData);
		//关闭输出流
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
