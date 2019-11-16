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
import com.yidu.focus.hj.dao.IncomeDao;
import com.yidu.focus.hj.dao.impl.IncomeDaoImpl;
import com.yidu.focus.hj.domain.Income;

/**
 * 
 * 功能：实现所有收入汇总
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月31日上午8:53:23
 */
@WebServlet("/IncomeServlet")
public class IncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建响应字符集编码
		response.setCharacterEncoding("utf-8");
		//设置响应内容类型
		response.setContentType("text/html");
		//设置请求字符集编码
		request.setCharacterEncoding("utf-8");
		//接收来自网页数据
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
		String incomeIdStr = request.getParameter("incomeIdStr");
		//将字符串按"，"分割
		String[] incomeIdS = incomeIdStr.split(",");
		//创建数据层操作对象
		IncomeDao incomeDao = new IncomeDaoImpl();
		try {
			for(int i = 0;i<incomeIdS.length;i++){
				String tincomeId=incomeIdS[i];
				int incomeId = Integer.parseInt(tincomeId);
				incomeDao.delete(incomeId);
				
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
		int incomeId = Integer.parseInt(request.getParameter("incomeId"));
		String source = request.getParameter("source");
		double money = Double.parseDouble(request.getParameter("money"));
		String source_date = request.getParameter("source_date");
		//创建数据层连接
		IncomeDao incomeDao = new IncomeDaoImpl();
		//创建收入对象保存数据
		Income income = new Income(incomeId, source, money, source_date);
		//调用添加方法并接收返回值
		int rows = incomeDao.update(income);
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
		String source = request.getParameter("source");
		double money = Double.parseDouble(request.getParameter("money"));
		String source_date = request.getParameter("source_date");
		//创建数据层连接
		
		IncomeDao incomeDao = new IncomeDaoImpl();
		//创建收入对象保存数据
		Income income = new Income(source, money, source_date);
		//调用添加方法并接收返回值
		int rows = incomeDao.add(income);
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
		String source = request.getParameter("source");
		String source_date = request.getParameter("source_date");
		String condition = " where 1=1 ";
		if(source!=null&&!source.equals("")){
			condition=condition+" and source like '%"+source+"%' ";
		}
		if(source_date!=null&&!source_date.equals("")){
			condition=condition+" and source_date like '%"+source_date+"%' ";
		}
		
		//接收来自网页的rows和page
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//建立数据层连接
		IncomeDao incomeDao = new IncomeDaoImpl();
		//调用数据层执行分页查询并将结果保存到集合中
		List<Income> incomeList = incomeDao.findByPage(rows, page, condition);
		int totalRows = incomeDao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", incomeList);
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
