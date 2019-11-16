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

@WebServlet("/LesseeInformationServlet")
public class LesseeInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LesseeInformationServlet() {
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

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		int leaseContractId = Integer.parseInt(request.getParameter("leaseContractId"));
		String houseId = request.getParameter("houseId");
		String lesseeName = request.getParameter("lesseeName");
		String lesseeGender = request.getParameter("lesseeGender");
		String lesseeIdcard = request.getParameter("lesseeIdcard");
		String lesseeTelphone = request.getParameter("lesseeTelphone");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		//创建租户对象
		LesseeInformation lesseeInformation = new LesseeInformation(leaseContractId, houseId, lesseeName, lesseeGender, lesseeIdcard, lesseeTelphone, startTime, endTime);
		//建立数据层连接
		LesseeInformationDao lesseeInformationDao = new LesseeInformationDaoImpl();
		//调用添加方法并接收返回值
		int rows = lesseeInformationDao.update(lesseeInformation);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建是输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String leaseContractIdStr = request.getParameter("leaseContractIdStr");
		//将字符串按"，"分割
		String[] leaseContractIdS = leaseContractIdStr.split(",");
		//创建数据层操作对象
		LesseeInformationDao lesseeInformationDao = new LesseeInformationDaoImpl();
		try {
			for(int i = 0;i<leaseContractIdS.length;i++){
				String tleaseContractId=leaseContractIdS[i];
				int leaseContractId = Integer.parseInt(tleaseContractId);
				lesseeInformationDao.delete(leaseContractId);
				
			}
			out.print("success");
		} catch (Exception e) {
			out.print("failure");
		}
		out.close();
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String houseId = request.getParameter("houseId");
		String lesseeName = request.getParameter("lesseeName");
		String lesseeGender = request.getParameter("lesseeGender");
		String lesseeIdcard = request.getParameter("lesseeIdcard");
		String lesseeTelphone = request.getParameter("lesseeTelphone");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		//创建租户对象
		LesseeInformation lesseeInformation = new LesseeInformation(houseId, lesseeName, lesseeGender, lesseeIdcard, lesseeTelphone, startTime, endTime);
		//建立数据层连接
		LesseeInformationDao lesseeInformationDao = new LesseeInformationDaoImpl();
		//调用添加方法并接收返回值
		int rows = lesseeInformationDao.add(lesseeInformation);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		
		String houseId = request.getParameter("houseId");
		String lesseeName = request.getParameter("lesseeName");
		String condition = " where 1=1 ";
		
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
