package com.yidu.focus.ll.servlet;

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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yidu.focus.hj.dao.HouseInformationDao;
import com.yidu.focus.hj.dao.impl.HouseInformationDaoImpl;
import com.yidu.focus.hj.domain.HouseInformation;
import com.yidu.focus.ll.dao.ParkingInformationDao;
import com.yidu.focus.ll.dao.impl.ParkingInformationDaoImpl;
import com.yidu.focus.ll.domain.ParkingInformation;
import com.yidu.focus.wzh.domain.Users;

/**
 * 
 * 功能：车位信息 
 * 作者：刘李
 * 日期：2019年10月31日上午9:11:19
 * 版本：1.0
 */
@WebServlet("/ParkingInformationServlet")
public class ParkingInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ParkingInformationServlet() {

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
		}else if("findByName".equals(method)){
			this.findByName(request,response);
		}
		
	}

	private void findByName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//定义输出流对象
		PrintWriter out=response.getWriter();
		//定义session对象
		HttpSession session=request.getSession();
		//取出session中的对象
		Users users=(Users)session.getAttribute("users");
		//取出名字
		String ownerName=users.getuName();
		//建立数据层连接
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		//通过业主名字获取到业主信息对象
		HouseInformation houseInformation=houseInformationDao.getHouseInformationByName(ownerName);
		//得到房号
		String houseId=houseInformation.getHouseId();		
		//建立数据层对象
		ParkingInformationDao parkingInformationDao=new ParkingInformationDaoImpl();
		//调用数据层方法获取数据
		ParkingInformation parkingInformation=parkingInformationDao.findByName(houseId);
		//定义一个集合对象
		List<Object> list=new ArrayList<>();
		//将对象保存到集合中
		list.add(houseInformation);
		list.add(parkingInformation);
		//定义Gson对象
		Gson gson=new Gson();
		//序列化对象
		String park=gson.toJson(list);		
		//输出到客户端
		out.print(park);
		//关闭输出流
		out.close();
	}

	/**
	 * 根据主键删除数据
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 抛出异常
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		//获取来自请中所传递过来的序号字符串
		String parkIdStr=request.getParameter("parkIdStr");
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] parkIds=parkIdStr.split(",");
		//创建数据层操作对象
		ParkingInformationDao parkingInformationdao=new ParkingInformationDaoImpl();
		//由于是批量删除，有可能失败，此处使用异常处理来实现
		try{
			//使用循环进行批量删除
			for(int i=0;i<parkIds.length;i++){
				//获取当前子字符串
				String parkId=parkIds[i];
				//执行删除操作
				parkingInformationdao.delete(parkId);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}
		//关闭输出流
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
		String parkId=request.getParameter("parkId");
		//状态
		String parkStatus=request.getParameter("parkStatus");	
		//费用
		double parkMoney=Double.parseDouble(request.getParameter("parkMoney"));
		//房号
		String houseId=request.getParameter("houseId");
		//数据封装成对象
		ParkingInformation parkingInformation=new ParkingInformation(parkId, parkStatus, parkMoney, houseId);
		//调用数据层对象执行添加方法
		ParkingInformationDao parkingInformationdao=new ParkingInformationDaoImpl();
		int rows=parkingInformationdao.update(parkingInformation);
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
	 * 添加
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 抛出异常
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//编号
		String parkId=request.getParameter("parkId");
		//状态
		String parkStatus=request.getParameter("parkStatus");	
		//费用
		double parkMoney=Double.parseDouble(request.getParameter("parkMoney"));
		//房号
		String houseId=request.getParameter("houseId");

		//数据封装成对象
		ParkingInformation parkingInformation=new ParkingInformation(parkId, parkStatus, parkMoney, houseId);

		//调用数据层对象执行添加方法
		ParkingInformationDao parkingInformationdao=new ParkingInformationDaoImpl();
		int rows=parkingInformationdao.add(parkingInformation);
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
		String parkId=request.getParameter("parkId");
		
		//生成查询条件
		String condition=" where 1=1 ";
		//根据编号进行模糊查询
		if(parkId!=null && !parkId.equals("")){
			condition=condition+"and parkId like '%"+parkId+"%' ";
		}		
		//接收来自客户端的datagrid组件的传递过来的page和rows参数
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));

		//调用数据层执行分页查询
		ParkingInformationDao parkingInformationdao=new ParkingInformationDaoImpl();
		//获得当前页的数据集合
		List<ParkingInformation> parkingInformationlist=parkingInformationdao.findByPage(rows, page, condition);

		//查询出ParkingInformation表的总记录数
		int totalRows=parkingInformationdao.count();
		//定义映射集合
		Map<String, Object> mapData = new HashMap<String, Object>();
		//将数据保存到map集合中
		mapData.put("total", totalRows);
		mapData.put("rows", parkingInformationlist);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
