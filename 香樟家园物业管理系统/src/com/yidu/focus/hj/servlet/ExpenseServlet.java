package com.yidu.focus.hj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yidu.focus.hj.dao.ExpenseDao;
import com.yidu.focus.hj.dao.impl.ExpenseDaoImpl;
import com.yidu.focus.hj.domain.Expense;

/**
 * 
 * 功能：支出汇总
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月21日上午10:58:48
 */
@WebServlet("/ExpenseServlet")
public class ExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpenseServlet() {
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
		}else if(method.equals("findTotal")){
			findTotal(request,response);
		}
	}
	/**
	 * 查询总计
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void findTotal(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//设置输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String datego = "";
		String datestop="";
		String expenseItem = request.getParameter("expenseItem");
		
		datego = request.getParameter("datego");
		datestop = request.getParameter("datestop");
		
		String month=request.getParameter("month");
		String condition = " where 1=1 ";
		if(datestop==null || datestop.equals("")){
			datestop="2099-12-30";
		}
		if(expenseItem!=null&&!expenseItem.equals("")){
			condition=condition+" and expenseItem like '%"+expenseItem+"%' ";
		}
		if(datego!=null&&!datego.equals("")){
			condition=condition+" and expense_date between '"+datego+"' and '"+datestop+"'";
		}
		if(month!=null&&!month.equals("")){
			condition=condition+" and expense_date like '%"+month+"%' ";
		}
		
		//接收来自网页的rows和page
		int rows = Integer.parseInt(request.getParameter("rows"));
	
		int page = Integer.parseInt(request.getParameter("page"));
		
		//建立数据层连接
		ExpenseDao expenseDao = new ExpenseDaoImpl();
		//调用数据层方法执行分页查询并将结果保存到集合中
		List<Expense> expenseList = expenseDao.findTotal(rows, page, condition);
		int totalRows = expenseDao.count(condition);
		double sum = 0;
		for (Expense expense : expenseList) {
			sum=expense.getExpenseMoney()+sum;
		}
		
		//定义映射集合对象
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", expenseList);
		List<Expense> footer = new ArrayList<>();
		Expense expense = new Expense();
		
		expense.setExpenseItem("总计：");
		expense.setExpenseMoney(sum);
		footer.add(expense);
		mapData.put("footer", footer);
		//定义Gson对象
		Gson gson = new Gson();
		//通过Gson对象将Map集合转换成Json数据格式
		String jsonData = gson.toJson(mapData);
		out.print(jsonData);
		out.close();
		
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
		String expenseIdStr = request.getParameter("expenseIdStr");
		//将字符串按"，"分割
		String[] expenseIdS = expenseIdStr.split(",");
		//创建数据层操作对象
		ExpenseDao expenseDao = new ExpenseDaoImpl();
		try {
			for(int i = 0;i<expenseIdS.length;i++){
				String texpenseId=expenseIdS[i];
				int expenseId = Integer.parseInt(texpenseId);
				expenseDao.delete(expenseId);
				
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
		int expenseId = Integer.parseInt(request.getParameter("expenseId"));
		String expenseItem = request.getParameter("expenseItem");
		double expenseMoney = Double.parseDouble(request.getParameter("expenseMoney"));
		String expense_date = request.getParameter("expense_date");
		//创建数据层连接
		ExpenseDao expenseDao = new ExpenseDaoImpl();
		//创建收入对象保存数据
		Expense expense = new Expense(expenseId, expenseItem, expenseMoney, expense_date);
		//调用添加方法并接收返回值
		int rows = expenseDao.update(expense);
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
		String expenseItem = request.getParameter("expenseItem");
		double expenseMoney = Double.parseDouble(request.getParameter("expenseMoney"));
		String expense_date = request.getParameter("expense_date");
		//创建数据层连接
		ExpenseDao expenseDao = new ExpenseDaoImpl();
		//创建收入对象保存数据
		Expense expense = new Expense(expenseItem, expenseMoney, expense_date);
		//调用添加方法并接收返回值
		int rows = expenseDao.add(expense);
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
		String expenseItem = request.getParameter("expenseItem");
		
		String expense_date = request.getParameter("expense_date");
		
		String condition = " where 1=1 ";
		if(expenseItem!=null&&!expenseItem.equals("")){
			condition=condition+" and expenseItem like '%"+expenseItem+"%' ";
		}
		if(expense_date!=null&&!expense_date.equals("")){
			condition=condition+" and expense_date like '%"+expense_date+"%' ";
		}
		//接收来自网页的rows和page
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//建立数据层连接
		ExpenseDao expenseDao = new ExpenseDaoImpl();
		//调用数据层执行分页查询并将结果保存到集合中
		List<Expense> expensesList = expenseDao.findByPage(rows, page, condition);
		int totalRows = expenseDao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", expensesList);
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
