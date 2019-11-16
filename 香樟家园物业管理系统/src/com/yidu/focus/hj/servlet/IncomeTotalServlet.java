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
import com.yidu.focus.hj.dao.IncomeTotalDao;
import com.yidu.focus.hj.dao.impl.IncomeTotalDaoImpl;
import com.yidu.focus.hj.domain.IncomeTotal;

/**
 * 
 * 功能：实现所有收入汇总
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月31日上午8:53:23
 */
@WebServlet("/IncomeTotalServlet")
public class IncomeTotalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomeTotalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应字符集编码
		response.setCharacterEncoding("utf-8");
		//设置响应内容类型
		response.setContentType("text/html");
		//设置请求字符集编码
		request.setCharacterEncoding("utf-8");
		//设置输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String datego = "";
		String datestop="";
		String source = request.getParameter("source");
		datego = request.getParameter("datego");
		datestop = request.getParameter("datestop");
		
		
		String month=request.getParameter("month");
		String condition = " where 1=1 ";
		if(datestop==null || datestop.equals("")){
			datestop="2099-12-30";
		}
		if(source!=null&&!source.equals("")){
			condition=condition+" and source like '%"+source+"%' ";
		}
		if(datego!=null&&!datego.equals("")){
			condition=condition+" and source_date between '"+datego+"' and '"+datestop+"'";
		}
		if(month!=null&&!month.equals("")){
			condition=condition+" and source_date like '%"+month+"%' ";
		}
		
		//接收来自网页的rows和page
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		int page = Integer.parseInt(request.getParameter("page"));
		
		//建立数据层连接
		IncomeTotalDao incomeTotalDao = new IncomeTotalDaoImpl();
		//调用数据层方法执行分页查询并将结果保存到集合中
		List<IncomeTotal> incomeTotalList = incomeTotalDao.findByPage(rows, page, condition);
		int totalRows = incomeTotalDao.count(condition);
		double sum = 0;
		for (IncomeTotal incomeTotal : incomeTotalList) {
			sum = incomeTotal.getMoneySum()+sum;
		}
		//定义映射集合对象
	
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", incomeTotalList);
		List<IncomeTotal> footer = new ArrayList<>();
		IncomeTotal incomeTotal = new IncomeTotal();
		
		incomeTotal.setSource("总计：");
		incomeTotal.setMoney(sum);
		footer.add(incomeTotal);
		mapData.put("footer", footer);
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
