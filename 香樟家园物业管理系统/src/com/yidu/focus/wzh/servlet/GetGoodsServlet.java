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
import com.yidu.focus.wzh.dao.GetGoodsDao;
import com.yidu.focus.wzh.dao.impl.GetGoodsDaoImpl;
import com.yidu.focus.wzh.domain.GetGoods;

/**
 * 
 * 功能：物品领用表 
 * 作者：伍志华
 * 日期：2019年10月31日上午9:17:14
 * 版本：1.0
 */
@WebServlet("/GetGoodsServlet")
public class GetGoodsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 378250551211170613L;

	public GetGoodsServlet() {
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
		}else if("update".equals(method)){
			this.update(request,response);
		}else if("delete".equals(method)){
			this.delete(request,response);
		}

	}

	/**
	 * 删除数据
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException  IO异常
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//获取来自请中所传递过来的用户编号字符串
		String ggIdStr=request.getParameter("ggIdStr");
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] ggIds=ggIdStr.split(",");
		//创建领用数据层操作对象
		GetGoodsDao getGoodsDao=new GetGoodsDaoImpl();
		//由于是批量删除，有可能失败，此处使用异常处理来实现
		try{
			//使用循环进行批量删除
			for(int i=0;i<ggIds.length;i++){
				//获取当前子字符串
				String tempId=ggIds[i];
				//将字符串编号转换为整型的员工编号
				int ggId=Integer.parseInt(tempId);
				//执行删除操作
				getGoodsDao.delete(ggId);
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
	 * @throws IOException IO异常
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//领用记录编号
		int ggId=Integer.parseInt(request.getParameter("ggId"));
		//物品名称
		String goodsName=request.getParameter("goodsName");
		//领用数量
		int ggNum=Integer.parseInt(request.getParameter("ggNum"));
		//领用人
		String ggName=request.getParameter("ggName");
		//领用时间
		String ggTime=request.getParameter("ggTime");
		//经办人
		String ggHandli=request.getParameter("ggHandli");
		//数据封装成对象
		GetGoods getgoods=new GetGoods(ggId, goodsName, ggNum, ggName, ggTime, ggHandli);
		//定义领用数据层操作对象
		GetGoodsDao getgoodsDao=new GetGoodsDaoImpl();
		//调用数据层对象执行添加方法
		int rows=getgoodsDao.update(getgoods);
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
		//物品名称
		String goodsName=request.getParameter("goodsName");
		//领用人
		String ggName=request.getParameter("ggName");

		//生成查询条件
		String condition=" where 1=1 ";
		//对条件数据进行判断并组合成查询条件
		//根据物品名称字段值进行判断，生成条件
		if(goodsName!=null && !goodsName.equals("")){
			condition=condition+" and goodsName like '%"+goodsName+"%' ";
		}
		//根据领用人字段值进行判断，生成条件
		if(ggName!=null && !ggName.equals("")){
			condition=condition+" and ggName like '%"+ggName+"%' ";
		}


		//接收来自客户端的datagrid组件的传递过来的page和rows参数
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));

		//调用领用数据层执行分页查询
		GetGoodsDao getgoodsDao=new GetGoodsDaoImpl();
		//获得当前页的数据集合
		List<GetGoods> getgoodsList=getgoodsDao.findByPage(rows, page,condition);
		//查询出getgoods表的总记录数
		int totalRows=getgoodsDao.count(condition);


		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", getgoodsList);
		//定义Gson对象
		Gson gson = new Gson();
		//通过Gson对象将Map集合转换成json数据格式
		String jsonData = gson.toJson(mapData);
		//将json数据输出到客户端
		out.println(jsonData);
		out.close();

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
