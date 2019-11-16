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
import com.yidu.focus.hxl.dao.VisitorRegisterDao;
import com.yidu.focus.hxl.dao.impl.VisitorRegisterDaoImpl;
import com.yidu.focus.hxl.domain.VisitorRegister;

/**
 * 
 * 功能：来访登记表 
 * 作者：何稀龙
 * 日期：2019年10月31日上午9:09:11
 * 版本：1.0
 */
@WebServlet("/VisitorRegisterServlet")
public class VisitorRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitorRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		//获取来自请中所传递过来的序号字符串
		String visitorIdStr=request.getParameter("visitorIdStr");
		
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] visitorIds=visitorIdStr.split(",");

		//创建数据层操作对象
		VisitorRegisterDao visitorRegisterdao=new VisitorRegisterDaoImpl();

		//由于是批量删除，有可能失败，此处使用异常处理来实现
		try{
			//使用循环进行批量删除
			for(int i=0;i<visitorIds.length;i++){
				//获取当前子字符串
				String tvisitorIdStr=visitorIds[i];
				//将字符串编号转换为整型的序号
				int visitorId=Integer.parseInt(tvisitorIdStr);
				//执行删除操作
				visitorRegisterdao.deleteById(visitorId);
			}
			out.print("success");

		}catch(Exception e){
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
		PrintWriter out=response.getWriter();	
		//接收来自客户端的数据
		int visitorId=Integer.parseInt(request.getParameter("visitorId"));
		String visitorName=request.getParameter("visitorName");		
		String visitorGender="";
		int sex=Integer.parseInt(request.getParameter("visitorGender"));
		if(sex==1){
			visitorGender="男";
		}else{
			visitorGender="女";
		}
		String visitorIdNum=request.getParameter("visitorTelphone");
		String visitorTelphone=request.getParameter("visitorIdNum");
		String vistiAddress=request.getParameter("vistiAddress");
		String vistiReason=request.getParameter("vistiReason");
		String cometime=request.getParameter("cometime");
		String leaveTime=request.getParameter("leaveTime");
		String empNo=request.getParameter("empNo");
		//数据封装成对象
		VisitorRegister visitorRegister=new VisitorRegister(visitorId,visitorName, visitorGender, 
				    visitorIdNum, visitorTelphone, vistiAddress, vistiReason, cometime, leaveTime, empNo);

		//调用数据层对象执行添加方法
		VisitorRegisterDao visitorRegisterDao=new VisitorRegisterDaoImpl();
		int rows=visitorRegisterDao.update(visitorRegister);
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
		String visitorName=request.getParameter("visitorName");		
		String visitorGender="";
		int sex=Integer.parseInt(request.getParameter("visitorGender"));
		if(sex==1){
			visitorGender="男";
		}else{
			visitorGender="女";
		}
		String visitorIdNum=request.getParameter("visitorTelphone");
		String visitorTelphone=request.getParameter("visitorIdNum");
		String vistiAddress=request.getParameter("vistiAddress");
		String vistiReason=request.getParameter("vistiReason");
		String cometime=request.getParameter("cometime");
		String leaveTime=request.getParameter("leaveTime");
		String empNo=request.getParameter("empNo");
		//数据封装成对象
		VisitorRegister visitorRegister=new VisitorRegister(visitorName, visitorGender, visitorIdNum, visitorTelphone, vistiAddress, vistiReason, cometime, leaveTime, empNo);

		//调用数据层对象执行添加方法
		VisitorRegisterDao visitorRegisterDao=new VisitorRegisterDaoImpl();
		int rows=visitorRegisterDao.add(visitorRegister);
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
		//创建输出流
		PrintWriter out=response.getWriter();
		
		//得到来自请求中的条件数据
		String cometime=request.getParameter("cometime");
		String visitorName=request.getParameter("visitorName");
		String vistiAddress=request.getParameter("vistiAddress");
		//生成查询条件
		//select * from 表名 where 条件 limit n1,n2 
		//where 字段名=? and 字段名=? and 字段名=?
		String condition=" where 1=1 ";
		//对条件数据进行判断并组合成查询条件
		if(cometime!=null && !cometime.equals("")){
			condition=condition+"and cometime like '%"+cometime+"%' ";
		}
		if(visitorName!=null && !visitorName.equals("")){
			condition=condition+"and visitorName like '%"+visitorName+"%' ";
		}
		if(vistiAddress!=null && !vistiAddress.equals("")){
			condition=condition+"and vistiAddress like '%"+vistiAddress+"%' ";
		}
		
		//接收来自客户端的datagrid组件的传递过来的page和rows参数
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));

		//调用数据层执行分页查询
		VisitorRegisterDao visitorRegisterdao=new VisitorRegisterDaoImpl();
		//获得当前页的数据集合
		List<VisitorRegister> visitorRegisterList=visitorRegisterdao.findByPage(rows, page, condition);

		//查询出visitorRegister表的总记录数
		int totalRows=visitorRegisterdao.count();
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", visitorRegisterList);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
