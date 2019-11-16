package com.yidu.focus.yfx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.yidu.focus.wzh.domain.Users;
import com.yidu.focus.yfx.dao.RepairsDao;
import com.yidu.focus.yfx.dao.impl.RepairsDaoImpl;
import com.yidu.focus.yfx.domain.Repairs;
/**
 * 
 * 功能：投诉操作层 
 * 作者：严奉孝
 * 日期：2019年10月31日上午8:51:48
 * 版本：1.0
 */
@WebServlet("/RepairsServlet")
public class RepairsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RepairsServlet() {
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求字符集
		request.setCharacterEncoding("utf-8");
		//设置响应内容类型
		response.setContentType("text/html");
		//设置响应字符集编码格式
		response.setCharacterEncoding("utf-8");
		//接收请求中的参数
		String method=request.getParameter("method");
		
		//判断路径中的参数
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
		}else if("baoxiu".equals(method)){
			this.baoxiu(request,response);
		}
	}
	
	/**
	 * 业主在客户端报修的方法
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException IO异常
	 */
	private void baoxiu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出流对象
		PrintWriter out=response.getWriter();
		//定义session对象
		HttpSession session=request.getSession();
		//取出session中的user对象
		Users user=(Users) session.getAttribute("users");
		//得到当前的用户名
		String ownerName=user.getuName();
		//报修内容
		String repText=request.getParameter("repText");

		//获取当前系统时间
		Date date = new Date();
		//将系统时间转化为数据库格式
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//将时间转化成字符串格式
		String repTime=sdf.format(date);
		//默认员工
		String empName="张宪";
		//默认处理结果
		String repResult="未处理";
		//完成时间
		String repEndTime="2099-12-31";
		
		//数据封装成对象
		Repairs repairs=new Repairs(ownerName, repText, repTime, empName, repResult, repEndTime);
		//调用数据层对象执行添加方法,得到影响行数
		RepairsDao repairsDao=new RepairsDaoImpl();
		int rows=repairsDao.add(repairs);
		//判断添加是否成功
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//关闭流
		out.close();
	}
	
	/**
	 * 通过业主姓名查找到报修记录
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException IO异常
	 */
	private void findByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出流对象
		PrintWriter out=response.getWriter();
		//定义session对象
		HttpSession session=request.getSession();
		//取出session中的user对象
		Users user=(Users) session.getAttribute("users");
		//得到当前的用户名
		String ownerName=user.getuName();
		//定义数据层对象
		RepairsDao repairsDao=new RepairsDaoImpl();
		//调用数据层方法
		List<Repairs> repairsList=repairsDao.findByName(ownerName);
		//定义gson对象
		Gson gson=new Gson();
		//序列化
		String repair=gson.toJson(repairsList);
		//输出
		out.print(repair);
		//关闭流
		out.close();
	}

	/**
	 * 删除数据库表中的数据
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws ServletException servlet异常
	 * @throws IOException IO流异常
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义输出流对象
		PrintWriter out=response.getWriter();
		//获取来自请中所传递过来的投诉编号字符串
		String repStr=request.getParameter("repIdStr");	
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] repIds=repStr.split(",");		
		//创建数据层操作对象
		RepairsDao repairsDao=new RepairsDaoImpl();		
		//由于是批量删除，有可能失败，此处使用异常处理来实现
		try{
			//使用循环进行批量删除
			for(int i=0;i<repIds.length;i++){
				//获取当前子字符串
				String temp=repIds[i];
				//将字符串转换成整型
				int repId=Integer.parseInt(temp);
				//执行删除操作
				repairsDao.deleteById(repId);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}
		//关闭输出对象
		out.close();
	}

	/**
	 * 更新数据库表中的数据
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws ServletException servlet异常
	 * @throws IOException IO流异常
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建输出流对象
		PrintWriter out=response.getWriter();		
		//接收来自客户端的数据
		//报修编号
		int repId=Integer.parseInt(request.getParameter("repId"));
		//业主姓名
		String ownerName=request.getParameter("ownerName");
		//报修内容
		String repText=request.getParameter("repText");
		//报修时间
		String repTime=request.getParameter("repTime");
		//受理人
		String empName=request.getParameter("empName");
		//处理结果
		String repResult=request.getParameter("repResult");
		//回访时间
		String repEndTime=request.getParameter("repEndTime");		
		//数据封装成对象
		Repairs repairs=new Repairs(repId, ownerName, repText, repTime, empName, repResult, repEndTime);		
		//定义报修数据层操作对象
		RepairsDao repairsDao=new RepairsDaoImpl();
		//调用数据层对象执行添加方法，得到影响行数
		int rows=repairsDao.update(repairs);
		//判断修改是否成功
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//关闭输出流
		out.close();
	}

	/**
	 * 向数据库表中增添数据
	 * @param request 请求对象
	 * @param response 响应对象
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建输出流对象
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//业主姓名
		String ownerName=request.getParameter("ownerName");
		//报修内容
		String repText=request.getParameter("repText");
		//报修时间
		String repTime=request.getParameter("repTime");
		//受理人
		String empName=request.getParameter("empName");
		//处理结果
		String repResult=request.getParameter("repResult");
		//回访时间
		String repEndTime=request.getParameter("repEndTime");		
		//数据封装成对象
		Repairs repairs=new Repairs(ownerName, repText, repTime, empName, repResult, repEndTime);
		//定义报修数据层操作对象
		RepairsDao repairsDao=new RepairsDaoImpl();
		//调用数据层对象执行添加方法,得到影响行数
		int rows=repairsDao.add(repairs);
		//判断添加是否成功
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//关闭流
		out.close();
	}
	/**
	 * 查看全部数据
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws ServletException servlet异常
	 * @throws IOException IO流异常
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义输出流
		PrintWriter out=response.getWriter();		
		//得到来自请求中的条件数据
		//业主姓名
		String ownerName=request.getParameter("ownerName");
		//报修内容
		String repText=request.getParameter("repText");
		//报修时间
		String repTime=request.getParameter("repTime");	
		
		//生成查询条件
		String condition=" where 1=1 ";
		//根据业主姓名
		if(ownerName!=null && !ownerName.equals("")){
			//生成根据业主姓名模糊查询的条件
			condition=condition+" and ownerName like '%"+ownerName+"%' ";
		}
		//根据报修内容
		if(repText!=null && !repText.equals("")){
			//生成根据报修内容模糊查询的条件
			condition=condition+" and repText like '%"+repText+"%' ";
		}
		//根据报修时间
		if(repTime!=null && !repTime.equals("")){
			//生成根据报修时间模糊查询的条件
			condition=condition+" and repTime like '%"+repTime+"%' ";
		}
		
		//接收来自客户端的datagrid组件的传递过来的page和rows参数
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//调用数据层执行分页查询
		RepairsDao repairsDao=new RepairsDaoImpl();
		//获得当前页的数据集合
		List<Repairs> repairsList=repairsDao.findByPage(rows, page,condition);
		//查询出complain表的总记录数
		int totalRows=repairsDao.count(condition);
		
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		//将数据保存到map集合中
		mapData.put("total", totalRows);
		mapData.put("rows", repairsList);
		//定义Gson对象
		Gson gson = new Gson();
		//通过Gson对象将Map集合转换成json数据格式
		String jsonData = gson.toJson(mapData);
		//将json数据输出到客户端
		out.println(jsonData);
		//关闭流
		out.close();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
