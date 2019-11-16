package com.yidu.focus.hxl.servlet;

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
import com.yidu.focus.hxl.dao.CarInformationDao;
import com.yidu.focus.hxl.dao.impl.CarInformationDaoImpl;
import com.yidu.focus.hxl.domain.CarInformation;


/**
 * 
 * 功能：车辆信息表 
 * 作者：何希龙
 * 日期：2019年10月31日上午9:06:34
 * 版本：1.0
 */
@WebServlet("/CarInformationServlet")
public class CarInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarInformationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应对象的字符集
		response.setCharacterEncoding("utf-8");
		//设置响应对象的内容类型
		response.setContentType("text/html");
		//设置请求对象的字符集
		request.setCharacterEncoding("utf-8");
		
		String method=request.getParameter("method");
	
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
	 * 删除
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		//获取来自请中所传递过来的员工编号字符串
		//carId,carName,carTelphone,carportId   carInformation
		String carIdStr=request.getParameter("carIdStr");
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] carIds=carIdStr.split(",");
		
		//创建数据层操作对象
		CarInformationDao carInformationDao=new CarInformationDaoImpl();
		
		//由于是批量删除，有可能失败，此处使用异常处理来实现
		try{
			//使用循环进行批量删除
			for(int i=0;i<carIds.length;i++){
				//获取当前子字符串
				String eqId=carIds[i];
				//执行删除操作
				carInformationDao.deleteById(eqId);
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
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//输出流对象
		PrintWriter out=response.getWriter();
		//carId,carName,carTelphone,carportId   carInformation
		//接收来自客户端的数据
		String carId=request.getParameter("carId");
		String carName=request.getParameter("carName");
		String carTelphone=request.getParameter("carTelphone");
		String carportId=request.getParameter("carportId");
		
		//数据封装成对象
		CarInformation carInformation=new CarInformation(carId,carName,carTelphone,carportId);
		
		//调用数据层对象执行添加方法
		CarInformationDao carInformationDao=new CarInformationDaoImpl();
		int rows=carInformationDao.update(carInformation);
		//判断添加是否成功
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
		PrintWriter out=response.getWriter();
	
		//接收来自客户端的数据
		String carId=request.getParameter("carId");
		String carName=request.getParameter("carName");
		String carTelphone=request.getParameter("carTelphone");
		String carportId=request.getParameter("carportId");
		
		//数据封装成对象
		CarInformation carInformation=new CarInformation(carId,carName,carTelphone,carportId);
	
		//调用数据层对象执行添加方法
		CarInformationDao carInformationDao=new CarInformationDaoImpl();
		int rows=carInformationDao.add(carInformation);
		//判断添加是否成功
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		
		out.close();
		
	}
	/**
	 * 查询全部
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		
		//得到来自请求中的条件数据
		String carId=request.getParameter("carId");
		
		String carportId=request.getParameter("carportId");
		
		//生成查询条件
		//select * from 表名 where 条件 limit n1,n2 
		//where 字段名=? and 字段名=? and 字段名=?
		String condition=" where 1=1 ";
		//对条件数据进行判断并组合成查询条件
		if(carId!=null && !carId.equals("")){
			condition=condition+" and carId like '%"+carId+"%' ";
		}
		
		if(carportId!=null && !carportId.equals("")){
			//添加到条件中
			condition=condition+" and carportId like '%"+carportId+"%' ";
		}


		
		//接收来自客户端的datagrid组件的传递过来的page和rows参数
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//调用数据层执行分页查询
		CarInformationDao carInformationDao=new CarInformationDaoImpl();
		//获得当前页的数据集合
		List<CarInformation> carInformationList=carInformationDao.findByPage(rows, page,condition);
		//查询出emp表的总记录数
		int totalRows=carInformationDao.count(condition);
		
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", carInformationList);
		//定义Gson对象
		Gson gson = new Gson();
		//通过Gson对象将Map集合转换成json数据格式
		String jsonData = gson.toJson(mapData);
		//将json数据输出到客户端
		out.println(jsonData);
	
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
