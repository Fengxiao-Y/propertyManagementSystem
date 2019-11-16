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
import com.yidu.focus.hj.dao.LesseeInformationDao;
import com.yidu.focus.hj.dao.impl.LesseeInformationDaoImpl;
import com.yidu.focus.hj.domain.LesseeInformation;

/**
 * Servlet implementation class OtherServiceIncomeServlet
 */
@WebServlet("/OtherServiceIncomeServlet")
public class OtherServiceIncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherServiceIncomeServlet() {
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

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String leaseContractId = request.getParameter("leaseContractId");
		String houseId = request.getParameter("houseId");
		String lesseeName = request.getParameter("lesseeName");
		String condition = " where 1=1 ";
		if(leaseContractId!=null&&!leaseContractId.equals("")){
			condition=condition+" and leaseContractId like '%"+leaseContractId+"%' ";
		}
		if(houseId!=null&&!houseId.equals("")){
			condition=condition+" and houseId like '%"+houseId+"%' ";
		}
		if(lesseeName!=null&&!lesseeName.equals("")){
			condition=condition+" and lesseeName like '%"+lesseeName+"%' ";
		}
		//接收来自网页的rows和page
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//建立数据层连接
		LesseeInformationDao lesseeInformationDao = new LesseeInformationDaoImpl();
		//调用数据层执行分页查询并将结果保存到集合中
		List<LesseeInformation> lesseeInformationList = lesseeInformationDao.findByPage(rows, page, condition);
		int totalRows = lesseeInformationDao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", lesseeInformationList);
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
