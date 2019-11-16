package com.yidu.focus.hj.servlet;

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
import com.yidu.focus.hj.dao.WageDao;
import com.yidu.focus.hj.dao.impl.WageDaoImpl;
import com.yidu.focus.hj.domain.Wage;

/**
 * 
 * 功能：工资
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月31日下午6:50:40
 */
@WebServlet("/WageServlet")
public class WageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应内容类型
		response.setContentType("text/html");
		//设置响应字符集编码
		response.setCharacterEncoding("utf-8");
		//设置请求字符集编码
		request.setCharacterEncoding("utf-8");
		//接收来自网页的数据
		String method = request.getParameter("method");
		if(method.equals("findAll")){
			findAll(request,response);
		}else if(method.equals("add")){
			add(request,response);
		}else if(method.equals("update")){
			update(request,response);
		}else if(method.equals("delete")){
			delete(request,response);
		}
	}
	/**
	 * 删除
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建是输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String wageidStr = request.getParameter("wageidStr");
		//将字符串按"，"分割
		String[] wageidS = wageidStr.split(",");
		//创建数据层操作对象
		WageDao wageDao = new WageDaoImpl();
		try {
			for(int i = 0;i<wageidS.length;i++){
				String twageid=wageidS[i];
				int wageid = Integer.parseInt(twageid);
			
			}
			out.print("success");
		} catch (Exception e) {
			out.print("failure");
		}
		out.close();
		
	}
	/**
	 * 修改
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		int wageid = Integer.parseInt(request.getParameter("wageid"));
		String empName = request.getParameter("empName");
		double salary = Double.parseDouble(request.getParameter("salary"));
		double commision = Double.parseDouble(request.getParameter("commision"));
		double withhold = Double.parseDouble(request.getParameter("withhold"));
		double playMoney = Double.parseDouble(request.getParameter("playMoney"));
		String wageMonth = request.getParameter("wageMonth");
		//创建租户对象
		Wage wage = new Wage(wageid, empName, salary, commision, withhold, playMoney, wageMonth);
		//建立数据层连接
		WageDao wageDao = new WageDaoImpl();
		//调用添加方法并接收返回值
		int rows = wageDao.update(wage);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}
	/**
	 * 添加
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String empName = request.getParameter("empName");
		double salary = Double.parseDouble(request.getParameter("salary"));
		double commision = Double.parseDouble(request.getParameter("commision"));
		double withhold = Double.parseDouble(request.getParameter("withhold"));
		double playMoney = Double.parseDouble(request.getParameter("playMoney"));
		String wageMonth = request.getParameter("wageMonth");
		//创建租户对象
		Wage wage = new Wage(empName, salary, commision, withhold, playMoney, wageMonth);
		//建立数据层连接
		WageDao wageDao = new WageDaoImpl();
		//调用添加方法并接收返回值
		int rows = wageDao.add(wage);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}
	/**
	 * 查询所有
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String empName = request.getParameter("empName");
		String wageMonth = request.getParameter("wageMonth");
		String condition = " where 1=1 ";
		if(empName!=null&&!empName.equals("")){
			condition=condition+" and empName like '%"+empName+"%' ";
		}
		if(wageMonth!=null&&!wageMonth.equals("")){
			condition=condition+" and wageMonth like '%"+wageMonth+"%' ";
		}
		//接收来自网页的rows和page
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//建立数据层连接
		WageDao wageDao = new WageDaoImpl();
		//调用数据层执行分页查询并将结果保存到集合中
		List<Wage> wageList = wageDao.findByPage(rows, page, condition);
		int totalRows = wageDao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", wageList);
		//定义Gson对象
		Gson gson = new Gson();
		//通过Gson对象将Map集合转换成Json数据格式
		String jsonData = gson.toJson(mapData);
		out.print(jsonData);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
