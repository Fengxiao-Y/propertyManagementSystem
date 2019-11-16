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
import com.yidu.focus.wzh.dao.UsersDao;
import com.yidu.focus.wzh.dao.impl.UsersDaoImpl;
import com.yidu.focus.wzh.domain.Users;

/**
 * 
 * 功能：前端登录人员 
 * 作者：伍志华
 * 日期：2019年10月31日上午9:15:42
 * 版本：1.0
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsersServlet() {
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
	 * 根据主键删除数据
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 抛出异常
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//获取来自请中所传递过来的用户编号字符串
		String uIdStr=request.getParameter("uIdStr");
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] uIds=uIdStr.split(",");
		//创建数据层操作对象
		UsersDao usersdao=new UsersDaoImpl();
		//由于是批量删除，有可能失败，此处使用异常处理来实现
		try{
			//使用循环进行批量删除
			for(int i=0;i<uIds.length;i++){
				//获取当前子字符串
				String tempId=uIds[i];
				//将字符串编号转换为整型的员工编号
				int uId=Integer.parseInt(tempId);
				//执行删除操作
				usersdao.delete(uId);
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
		//业主编号
		int uId=Integer.parseInt(request.getParameter("uId"));
		//业主姓名
		String uName=request.getParameter("uName");
		//密码
		String uPassword=request.getParameter("uPassword");
		//业主邮箱
		String uEmail=request.getParameter("uEmail");
		//业主号码
		String uIphone=request.getParameter("uIphone");
		//数据封装成对象
		Users users=new Users(uId, uName, uPassword, uEmail, uIphone);
		//调用数据层对象执行添加方法
		UsersDao userdao=new UsersDaoImpl();
		int rows=userdao.update(users);
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
	 * @throws IOException 抛出异常
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//业主姓名
		String uName=request.getParameter("uName");
		//密码
		String uPassword=request.getParameter("uPassword");
		//业主邮箱
		String uEmail=request.getParameter("uEmail");
		//业主号码
		String uIphone=request.getParameter("uIphone");
		//数据封装成对象
		Users users=new Users(uName, uPassword, uEmail, uIphone);
		//调用数据层对象执行添加方法
		UsersDao userdao=new UsersDaoImpl();
		int rows=userdao.doRegister(users);
		//判断添加是否成功
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
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
		//用户编号
		String uIds=request.getParameter("uId");
		//用户名
		String uName=request.getParameter("uName");
		//用户号码
		String uIphone=request.getParameter("uIphone");
		//生成查询条件
		String condition=" where 1=1 ";
		//对条件数据进行判断并组合成查询条件
		//根据用户编号字段值进行判断，生成条件
		if(uIds!=null && !uIds.equals("")){
			//由于deptno是int类型，首先转换类型
			int uId=Integer.parseInt(uIds);
			//添加到条件中
			condition=condition+" and uId="+uId+" ";
		}
		//根据用户名字段值进行判断，生成条件
		if(uName!=null && !uName.equals("")){
			condition=condition+" and uName like '%"+uName+"%' ";
		}
		//根据用户号码字段值进行判断，生成条件
		if(uIphone!=null && !uIphone.equals("")){
			condition=condition+" and uIphone like '%"+uIphone+"%' ";
		}
		//接收来自客户端的datagrid组件的传递过来的page和rows参数
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//调用数据层执行分页查询
		UsersDao userdao=new UsersDaoImpl();
		//获得当前页的数据集合
		List<Users> userList=userdao.findByPage(rows, page,condition);
		//查询出emp表的总记录数
		int totalRows=userdao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		//将数据保存到map集合中
		mapData.put("total", totalRows);
		mapData.put("rows", userList);
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
