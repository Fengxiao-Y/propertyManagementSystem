  package com.yidu.focus.wzh.servlet;

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
import com.yidu.focus.hj.dao.ExpenseDao;
import com.yidu.focus.hj.dao.impl.ExpenseDaoImpl;
import com.yidu.focus.hj.domain.Expense;
import com.yidu.focus.wzh.dao.ProcurementDao;
import com.yidu.focus.wzh.dao.impl.ProcurementDaoImpl;
import com.yidu.focus.wzh.domain.Procurement;

/**
 * 
 * 功能：物料采购表
 * 作者：伍志华
 * 日期：2019年10月31日上午9:16:29
 * 版本：1.0
 */
@WebServlet("/ProcurementServlet")
public class ProcurementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProcurementServlet() {
		super();

	}

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
	 * 删除数据的方法
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException IO异常
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//获取来自请中所传递过来的库存编号字符串
		String proIdStr=request.getParameter("proIdStr");
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] proIds=proIdStr.split(",");
		//创建采购数据层操作对象
		ProcurementDao procurementDao=new ProcurementDaoImpl();
		//由于是批量删除，有可能失败，此处使用异常处理来实现
		try{
			//使用循环进行批量删除
			for(int i=0;i<proIds.length;i++){
				//获取当前子字符串
				String proId=proIds[i];
				//执行删除操作
				procurementDao.delete(proId);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}
		//关闭输出对象
		out.close();
	}

	/**
	 * 更新
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 抛出异常
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//领用采购编号
		String proId=request.getParameter("proId");
		//采购人
		String proName=request.getParameter("proName");
		//商品名称
		String goodsName=request.getParameter("goodsName");
		//采购时间
		String proTime=request.getParameter("proTime");
		//采购数量
		int proNum=Integer.parseInt(request.getParameter("proNum"));
		//单价
		double proPrice=Double.parseDouble(request.getParameter("proPrice"));
		//总价
		double proTolal=proNum*proPrice;
		//把采购数据封装成对象
		Procurement procurement=new Procurement(proId, proName, goodsName, proTime, proNum, proPrice, proTolal);
		//调用数据层对象执行添加方法
		ProcurementDao procurementDao=new ProcurementDaoImpl();
		int rows= procurementDao.update(procurement);
		//判断添加是否成功
		if(rows>0){
			//添加成功
			out.print("success");
		}else{
			//添加失败
			out.print("failure");
		}
		//关闭输出对象
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
		//接收来自客户端的数据
		//领用采购编号
		String proId=request.getParameter("proId");
		//采购人
		String proName=request.getParameter("proName");
		//商品名称
		String goodsName=request.getParameter("goodsName");
		//采购时间
		String proTime=request.getParameter("proTime");
		//采购数量
		int proNum=Integer.parseInt(request.getParameter("proNum"));
		//单价
		double proPrice=Double.parseDouble(request.getParameter("proPrice"));
		//总价
		double proTolal=proNum*proPrice;
		//数据封装成对象
		Procurement procurement=new Procurement(proId, proName, goodsName, proTime, proNum, proPrice, proTolal);
		//调用采购数据层对象执行添加方法
		ProcurementDao procurementDao=new ProcurementDaoImpl();
		int rows= procurementDao.add(procurement);		
		//采购支出
		String expenseItem = "采购支出";
		//支出费用
		double expenseMoney = proTolal;
		//支出时间
		String expense_date = proTime;
		//建立支出数据层对象
		ExpenseDao expenseDao = new ExpenseDaoImpl();
		//把数据封装到支出实体类中
		Expense expense = new Expense(expenseItem, expenseMoney, expense_date);
		//调用添加的方法
		expenseDao.add(expense);
		//判断添加是否成功
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//关闭输出
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
		String proId=request.getParameter("proId");
		String goodsName=request.getParameter("goodsName");
		//生成查询条件
		String condition=" where 1=1 ";
		//对条件数据进行判断并组合成查询条件
		//根据采购编号字段值进行判断，生成条件
		if(proId!=null && !proId.equals("")){
			//添加到条件中
			condition=condition+" and proId like '%"+proId+"%' ";
		}
		//根据物品名称字段值进行判断，生成条件
		if(goodsName!=null && !goodsName.equals("")){
			condition=condition+" and goodsName like '%"+goodsName+"%' ";
		}
		//接收来自客户端的datagrid组件的传递过来的page和rows参数
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//调用数据层执行分页查询
		ProcurementDao procurementDao=new ProcurementDaoImpl();
		//获得当前页的数据集合
		List<Procurement> procurementList=procurementDao.findByPage(rows, page,condition);
		//查询出表的总记录数
		int totalRows=procurementDao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		//将数据保存到map集合中
		mapData.put("total", totalRows);
		mapData.put("rows", procurementList);
		//定义Gson对象
		Gson gson = new Gson();
		//通过Gson对象将Map集合转换成json数据格式
		String jsonData = gson.toJson(mapData);
		//将json数据输出到客户端
		out.println(jsonData);
		//关闭输出
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
