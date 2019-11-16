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
import com.yidu.focus.hxl.dao.EqMaintainDao;
import com.yidu.focus.hxl.dao.impl.EqMaintainDaoImpl;
import com.yidu.focus.hxl.domain.EqMaintain;

/**
 * 
 * 功能：设备维修表 
 * 作者：何希龙
 * 日期：2019年10月31日上午9:07:36
 * 版本：1.0
 */
@WebServlet("/EqMaintainServlet")
public class EqMaintainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EqMaintainServlet() {
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
		String mainIdStr=request.getParameter("mainIdStr");
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] mainIds=mainIdStr.split(",");
		
		//创建数据层操作对象
		EqMaintainDao eqMaintainDao=new EqMaintainDaoImpl();
		
		//由于是批量删除，有可能失败，此处使用异常处理来实现
		try{
			//使用循环进行批量删除
			for(int i=0;i<mainIds.length;i++){
				//获取当前子字符串
				String mainId=mainIds[i];
				//执行删除操作
				eqMaintainDao.deleteById(mainId);
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
		PrintWriter out=response.getWriter();
		
		//接收来自客户端的数据
		String maintainId=request.getParameter("maintainId");
		String eqId=request.getParameter("eqId");
		String maintainStatues=request.getParameter("maintainStatues");
		String maintainDate=request.getParameter("maintainDate");
		String consumable=request.getParameter("consumable");
		int consumNum=Integer.parseInt(request.getParameter("consumNum"));
		double checkCost=Double.parseDouble(request.getParameter("checkCost"));
		String empNo=request.getParameter("empNo");
		
		//数据封装成对象
		EqMaintain eqMaintain=new EqMaintain(maintainId,eqId, maintainStatues, maintainDate, consumable,consumNum, checkCost, empNo);
		
		//调用数据层对象执行添加方法
		EqMaintainDao eqMaintainDao=new EqMaintainDaoImpl();
		int rows=eqMaintainDao.update(eqMaintain);
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
		String maintainId=request.getParameter("maintainId");
		String eqId=request.getParameter("eqId");
		String maintainStatues=request.getParameter("maintainStatues");
		String maintainDate=request.getParameter("maintainDate");
		String consumable=request.getParameter("consumable");
		
		int consumNum=Integer.parseInt(request.getParameter("consumNum"));
		
		double checkCost=Double.parseDouble(request.getParameter("checkCost"));
		String empNo=request.getParameter("empNo");
		
		//数据封装成对象
		EqMaintain eqMaintain=new EqMaintain(maintainId,eqId, maintainStatues, maintainDate, consumable,consumNum, checkCost, empNo);
		
		//调用数据层对象执行添加方法
		EqMaintainDao eqMaintainDao=new EqMaintainDaoImpl();
		int rows=eqMaintainDao.add(eqMaintain);
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
		
		String eqId=request.getParameter("eqId");
		String maintainStatues=request.getParameter("maintainStatues");
		String maintainDate=request.getParameter("maintainDate");
		String empNo=request.getParameter("empNo");
		
		//生成查询条件
		//select * from 表名 where 条件 limit n1,n2 
		//where 字段名=? and 字段名=? and 字段名=?
		String condition=" where 1=1 ";
		//对条件数据进行判断并组合成查询条件
		if(eqId!=null && !eqId.equals("")){
			condition=condition+" and eqId like '%"+eqId+"%' ";
		}
		//根据字段值进行判断，生成条件
		if(maintainStatues!=null && !maintainStatues.equals("")){
			condition=condition+" and maintainStatues like '%"+maintainStatues+"%' ";
		}
		
		if(maintainDate!=null && !maintainDate.equals("")){
			//添加到条件中
			condition=condition+" and maintainDate like '%"+maintainDate+"%' ";
		}
		
		if(empNo!=null && !empNo.equals("")){
			//添加到条件中
			condition=condition+" and empNo like '%"+empNo+"%' ";
		}
	
		
		
		//接收来自客户端的datagrid组件的传递过来的page和rows参数
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//调用数据层执行分页查询
		EqMaintainDao eqMaintainDao=new EqMaintainDaoImpl();
		//获得当前页的数据集合
		List<EqMaintain> eqMaintainList=eqMaintainDao.findByPage(rows, page,condition);
		//查询出emp表的总记录数
		int totalRows=eqMaintainDao.count(condition);
		
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", eqMaintainList);
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
