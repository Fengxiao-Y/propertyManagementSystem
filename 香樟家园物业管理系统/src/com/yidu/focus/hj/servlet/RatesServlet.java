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
import com.yidu.focus.hj.dao.RatesDao;
import com.yidu.focus.hj.dao.impl.RatesDaoImpl;
import com.yidu.focus.hj.domain.Rates;

/**
 * 
 * 功能：收费标准
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月31日下午6:33:38
 */
@WebServlet("/RatesServlet")
public class RatesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatesServlet() {
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
	 * 查询所有
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */   
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String itemIdStr = request.getParameter("itemIdStr");
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] itemIdS = itemIdStr.split(",");
		//创建数据层操作对象
		RatesDao ratesDao = new RatesDaoImpl();
		try {
			for(int i=0;i<itemIdS.length;i++){
				String titemId = itemIdS[i];
				int itemId = Integer.parseInt(titemId);
				ratesDao.delete(itemId);
			}
			out.print("success");
		} catch (NumberFormatException e) {
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
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		String itemName = request.getParameter("itemName");
		String itemDesc = request.getParameter("itemDesc");
		double ratesMoney = Double.parseDouble(request.getParameter("ratesMoney"));
		//业务处理
		//创建一个保存数据的对象
		Rates rates = new Rates(itemId, itemName, itemDesc, ratesMoney);
		//创建数据库连接对象
		RatesDao ratesDao = new RatesDaoImpl();
		//调用修改方法并接收返回值
		int rows = ratesDao.update(rates);
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
		//接收来自网页的数据
		String itemName = request.getParameter("itemName");
		String itemDesc = request.getParameter("itemDesc");
		double ratesMoney = Double.parseDouble(request.getParameter("ratesMoney"));
		//业务处理
		//创建数据层连接对象
		RatesDao ratesDao = new RatesDaoImpl();
		//将数据封装成对象
		Rates rates = new Rates(itemName, itemDesc, ratesMoney);
		//调用数据层添加方法并接收返回值
		int rows = ratesDao.add(rates);
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
		String itemName = request.getParameter("itemName");
		String condition=" where 1=1 ";
		if(itemName!=null && !itemName.equals("")){
			condition=condition+" and itemName like '%"+itemName+"%' ";
		}
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//业务处理
		//创建数据层连接对象
		RatesDao ratesDao = new RatesDaoImpl();
		List<Rates> ratesList = ratesDao.findByPage(rows, page, condition);
		int totalRows=ratesDao.count(condition);
		//定义映射集合对象
		Map<String,Object> mapData=new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", ratesList);
		//定义Gson对象
		Gson gson=new Gson();
		//通过Gson对象将Map集合转换成json数据格式
		String jsonData = gson.toJson(mapData);
		//将json数据输出到客户端
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
