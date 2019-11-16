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
import com.yidu.focus.hj.dao.OwnerInformationDao;
import com.yidu.focus.hj.dao.impl.OwnerInformationDaoImpl;
import com.yidu.focus.hj.domain.OwnerInformation;

/**
 * 
 * 功能：业主信息
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月21日上午10:58:48
 */
@WebServlet("/OwnerInformationServlet")
public class OwnerInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求字符集编码
		request.setCharacterEncoding("utf-8");
		//设置响应内容类型
		response.setContentType("text/html");
		//设置响应字符集编码
		response.setCharacterEncoding("utf-8");
		//接收来自网页 数据
		String method = request.getParameter("method");
		//判断
		if(method.equals("findAll")){
			this.findAll(request,response);
		}else if(method.equals("add")){
			this.add(request,response);
		}else if(method.equals("update")){
			this.update(request,response);
		}else if(method.equals("delete")){
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
		//创建是输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String ownerIdStr = request.getParameter("ownerIdStr");
		//将字符串按"，"分割
		String[] ownerIdS = ownerIdStr.split(",");
		//创建数据层操作对象
		OwnerInformationDao ownerInformationDao = new OwnerInformationDaoImpl();
		try {
			for(int i = 0;i<ownerIdS.length;i++){
				String ownerId=ownerIdS[i];
				ownerInformationDao.delete(ownerId);
			
			}
			out.print("success");
		} catch (Exception e) {
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
		//创建输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String ownerId = request.getParameter("ownerId");
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String ownerSex = request.getParameter("ownerSex");
		String ownerIdcard = request.getParameter("ownerIdcard");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String ownerEmail = request.getParameter("ownerEmail");
		String ownerAddress = request.getParameter("ownerAddress");
		//业务处理
		//创建业主对象保存数据
		OwnerInformation ownerInformation = new OwnerInformation(ownerId, houseId, ownerName, ownerSex, ownerIdcard, ownerTelphone, ownerEmail, ownerAddress);
		//创建数据库连接对象
		OwnerInformationDao ownerInformationDao = new OwnerInformationDaoImpl();
		//调用添加方法并接收返回值
		int rows = ownerInformationDao.update(ownerInformation);
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
		//创建输出流对象
		PrintWriter out = response.getWriter();
		//接受来自网页数据
		String ownerId = request.getParameter("ownerId");
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String ownerSex = request.getParameter("ownerSex");
		String ownerIdcard = request.getParameter("ownerIdcard");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String ownerEmail = request.getParameter("ownerEmail");
		String ownerAddress = request.getParameter("ownerAddress");
		//业务处理
		//创建一个业主对象
		OwnerInformation ownerInformation = new OwnerInformation(ownerId, houseId, ownerName, ownerSex, ownerIdcard, ownerTelphone, ownerEmail, ownerAddress);
		//创建数据层连接对象
		OwnerInformationDao ownerInformationDao = new OwnerInformationDaoImpl();
		//调用添加方法并接收返回值
		int rows = ownerInformationDao.add(ownerInformation);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
		
	}
	/**
	 * 查询所有
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流对对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String ownerId = request.getParameter("ownerId");
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String condition = " where 1=1 ";
		if(ownerId!=null&&!ownerId.equals("")){
			condition=condition+" and ownerId like '%"+ownerId+"%' ";
		}
		if(houseId!=null&&!houseId.equals("")){
			condition=condition+" and houseId like '%"+houseId+"%' ";
		}
		if(ownerName!=null&&!ownerName.equals("")){
			condition=condition+" and ownerName like '%"+ownerName+"%' ";
		}
		//接收来自网页传递的page和rows参数
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//建立数据层连接对象
		OwnerInformationDao ownerInformationDao = new OwnerInformationDaoImpl();
		//调用数据层执行分页查询并将结果保存到集合 中
		List<OwnerInformation> ownerInformationList = ownerInformationDao.findByPage(rows, page, condition);
		int totalRows = ownerInformationDao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("total", totalRows);
		mapData.put("rows", ownerInformationList);
		Gson gson = new Gson();
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
