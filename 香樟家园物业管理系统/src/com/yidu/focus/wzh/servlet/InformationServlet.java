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
import com.yidu.focus.wzh.dao.InformationDao;
import com.yidu.focus.wzh.dao.impl.InformationDaoImpl;
import com.yidu.focus.wzh.domain.Information;
/**
 * 
 * 功能：后台管理人员表
 * 作者：伍志华
 * 日期：2019年10月31日下午11:07:16
 * 版本：1.0
 */
@WebServlet("/InformationServlet")
public class InformationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 504930980717468700L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InformationServlet() {
		super();
		// TODO Auto-generated constructor stub
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
	 * 根据主键删除数据
	 * @param request 请求参数
	 * @param response 响应参数
	 * @throws IOException 抛出异常
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//获取来自请中所传递过来的后台账号字符串
		String backIdStr=request.getParameter("backIdStr");
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] backIds=backIdStr.split(",");
		//创建数据层操作对象
		InformationDao informationDao=new InformationDaoImpl();
		//由于是批量删除，有可能失败，此处使用异常处理来实现
		try{
			//使用循环进行批量删除
			for(int i=0;i<backIds.length;i++){
				//获取当前子字符串
				String backId=backIds[i];
				//执行删除操作
				informationDao.delete(backId);
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
	 * @throws IOException  IO异常
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//后台账号
		String backId=request.getParameter("backId");
		//密码
		String backPwd=request.getParameter("backPwd");
		//员工名
		String empName=request.getParameter("empName");
		//职务
		String backPost=request.getParameter("backPost");
		//数据封装成对象
		Information information=new Information(backId, backPwd, empName, backPost);
		//定义书数据层操作对象
		InformationDao informationDao=new InformationDaoImpl();
		//调用数据层对象执行添加方法
		int rows=informationDao.update(information);
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
	 * 增加
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException  IO异常
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//后台账号
		String backId=request.getParameter("backId");
		//密码
		String backPwd=request.getParameter("backPwd");
		//员工名
		String empName=request.getParameter("empName");
		//职务
		String backPost=request.getParameter("backPost");
		//数据封装成对象
		Information information=new Information(backId, backPwd, empName, backPost);
		//定义数据层对象
		InformationDao informationDao=new InformationDaoImpl();
		//调用数据层对象执行添加方法
		int rows=informationDao.doRegister(information);
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
	 * @throws IOException IO异常
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流	
		PrintWriter out=response.getWriter();
		//得到来自请求中的条件数据
		//后台登录账号
		String backId=request.getParameter("backId");
		//员工名
		String empName=request.getParameter("empName");		
		//生成查询条件
		String condition=" where 1=1 ";
		//对条件数据进行判断并组合成查询条件
		//根据后台登录账号段值进行判断，生成条件
		if(backId!=null && !backId.equals("")){
			condition=condition+" and backId like '%"+backId+"%' ";
		}
		//根据员工名字段值进行判断，生成条件
		if(empName!=null && !empName.equals("")){
			condition=condition+" and empName like '%"+empName+"%' ";
		}
		//接收来自客户端的datagrid组件的传递过来的page和rows参数
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//调用数据层执行分页查询
		InformationDao informationdao=new InformationDaoImpl();
		//获得当前页的数据集合
		List<Information> informationList=informationdao.findByPage(rows, page,condition);
		//查询出emp表的总记录数
		int totalRows=informationdao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		//将数据保存到map集合中
		mapData.put("total", totalRows);
		mapData.put("rows", informationList);
		//定义Gson对象
		Gson gson = new Gson();
		//通过Gson对象将Map集合转换成json数据格式
		String jsonData = gson.toJson(mapData);
		//将json数据输出到客户端
		out.println(jsonData);
		//关闭输出流
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
