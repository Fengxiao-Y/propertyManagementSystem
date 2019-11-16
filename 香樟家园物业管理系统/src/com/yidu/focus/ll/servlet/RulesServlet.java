package com.yidu.focus.ll.servlet;

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
import com.yidu.focus.ll.dao.RulesDao;
import com.yidu.focus.ll.dao.impl.RulesDaoImpl;
import com.yidu.focus.ll.domain.Rules;
/**
 * 
 * 功能：规章制度表 
 * 作者：刘李
 * 日期：2019年10月31日上午9:12:00
 * 版本：1.0
 */
@WebServlet("/RulesServlet")
public class RulesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RulesServlet() {

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
	 * 根据主键删除数据
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 抛出异常
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//获取来自请中所传递过来的文件编号字符串
		String rulesIdStr=request.getParameter("rulesIdStr");
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] rulesIds=rulesIdStr.split(",");

		//创建数据层操作对象
		RulesDao rulesdao=new RulesDaoImpl();

		//由于是批量删除，有可能失败，此处使用异常处理来实现
		try{
			//使用循环进行批量删除
			for(int i=0;i<rulesIds.length;i++){
				//获取当前子字符串
				String rulesId=rulesIds[i];
				//执行删除操作
				rulesdao.delete(rulesId);
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
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 抛出异常
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//编号
		String rulesId=request.getParameter("rulesId");
		//标题
		String rulesTitle=request.getParameter("rulesTitle");
		//主题
		String rulesTheme=request.getParameter("rulesTheme");
		//状态
		String rulesStatus=request.getParameter("rulesStatus");
		//执行时间
		String rulesTime=request.getParameter("rulesTime");

		//数据封装成对象
		Rules rules=new Rules(rulesId, rulesTitle, rulesTheme, rulesStatus, rulesTime);

		//调用数据层对象执行添加方法
		RulesDao rulesdao=new RulesDaoImpl();
		int rows=rulesdao.update(rules);
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
		//定义输出流对象
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//编号
		String rulesId=request.getParameter("rulesId");
		//标题
		String rulesTitle=request.getParameter("rulesTitle");
		//主题
		String rulesTheme=request.getParameter("rulesTheme");
		//状态
		String rulesStatus=request.getParameter("rulesStatus");
		//执行时间
		String rulesTime=request.getParameter("rulesTime");

		//数据封装成对象
		Rules rules=new Rules(rulesId, rulesTitle, rulesTheme, rulesStatus, rulesTime);

		//调用数据层对象执行添加方法
		RulesDao rulesdao=new RulesDaoImpl();
		int rows=rulesdao.add(rules);
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
		//编号
		String rulesId=request.getParameter("rulesId");
		//主题
		String rulesTheme=request.getParameter("rulesTheme");
		//状态
		String rulesStatus=request.getParameter("rulesStatus");

		//生成查询条件
		String condition=" where 1=1 ";
		//根据编号模查询
		if(rulesId!=null && !rulesId.equals("")){
			condition=condition+"and rulesId like '%"+rulesId+"%' ";
		}
		//根据主题模糊查询
		if(rulesTheme!=null && !rulesTheme.equals("")){
			condition=condition+"and rulesTheme like '%"+rulesTheme+"%' ";
		}
		//根据状态模糊查询
		if(rulesStatus!=null && !rulesStatus.equals("")){
			condition=condition+"and rulesStatus like '%"+rulesStatus+"%' ";
		}
		
		//接收来自客户端的datagrid组件的传递过来的page和rows参数
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));


		//调用数据层执行分页查询
		RulesDao rulesdao=new RulesDaoImpl();
		//获得当前页的数据集合
		List<Rules> ruleslist=rulesdao.findByPage(rows, page, condition);
		//查询出rules表的总记录数
		int totalRows=rulesdao.count();
		//定义map集合
		Map<String, Object> mapData = new HashMap<String, Object>();
		//将数据保存到map集合中
		mapData.put("total", totalRows);
		mapData.put("rows", ruleslist);
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

		doGet(request, response);
	}

}
