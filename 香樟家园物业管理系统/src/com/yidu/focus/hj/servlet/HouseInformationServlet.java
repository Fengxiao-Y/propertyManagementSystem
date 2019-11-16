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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yidu.focus.hj.dao.HouseInformationDao;
import com.yidu.focus.hj.dao.LesseeInformationDao;
import com.yidu.focus.hj.dao.OwnerInformationDao;
import com.yidu.focus.hj.dao.impl.HouseInformationDaoImpl;
import com.yidu.focus.hj.dao.impl.LesseeInformationDaoImpl;
import com.yidu.focus.hj.dao.impl.OwnerInformationDaoImpl;
import com.yidu.focus.hj.domain.HouseInformation;
import com.yidu.focus.hj.domain.LesseeInformation;
import com.yidu.focus.hj.domain.OwnerInformation;
import com.yidu.focus.wzh.domain.Users;

/**
 * 
 * 功能：房屋信息
 * 编写者：韩坚
 * 版本：1.0
 * 日期：2019年10月21日上午10:58:48
 */
@WebServlet("/HouseInformationServlet")
public class HouseInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HouseInformationServlet() {
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
		//接收来自网页数据
		String method = request.getParameter("method");
		if(method.equals("findAll")){
			findAll(request,response);
		}else if(method.equals("add")){
			add(request,response);
		}else if(method.equals("update")){
			update(request,response);
		}else if(method.equals("delete")){
			delete(request,response);
		}else if(method.equals("findByName")){
			findByName(request,response);
		}else if(method.equals("lease")){
			lease(request,response);
		}else if(method.equals("alteration")){
			alteration(request,response);
		}
	}
	/**
	 * 产权变更
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void alteration(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//创建输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String name = request.getParameter("name");
		String houseType = request.getParameter("houseType");
		double houseArea = Double.parseDouble(request.getParameter("houseArea"));
		String houseNature = request.getParameter("houseNature");
		String houseState = "已售";
		String houseAddress = request.getParameter("houseAddress");
		String ownerId = request.getParameter("ownerId");
		String ownerSex = request.getParameter("ownerSex");
		String ownerIdcard = request.getParameter("ownerIdcard");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String ownerEmail = request.getParameter("ownerEmail");
		String ownerAddress = request.getParameter("ownerAddress");
		//业务处理
		//创建房屋对象保存数据
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		OwnerInformationDao ownerInformationDao = new OwnerInformationDaoImpl();
		
		HouseInformation houseInformation = new HouseInformation(houseId, ownerName, houseType, houseArea, houseNature, houseState, houseAddress);
		//调用数据层添加方法并接收返回值
		
		//创建房屋对象保存数据
		OwnerInformation ownerInformation = new OwnerInformation(ownerId, houseId, ownerName, ownerSex, ownerIdcard, ownerTelphone, ownerEmail, ownerAddress);
		//调用数据层添加方法并接收返回值
		ownerInformationDao.add(ownerInformation);
		int rows = houseInformationDao.update(houseInformation);
		ownerInformationDao.deleteName(name);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
	}
	/**
	 * 租赁
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void lease(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String houseType = request.getParameter("houseType");
		double houseArea = Double.parseDouble(request.getParameter("houseArea"));
		String houseNature = request.getParameter("houseNature");
		String houseState = "出租";
		String houseAddress = request.getParameter("houseAddress");
		String lesseeName = request.getParameter("lesseeName");
		String lesseeGender = request.getParameter("lesseeGender");
		String lesseeIdcard = request.getParameter("lesseeIdcard");
		String lesseeTelphone = request.getParameter("lesseeTelphone");
		String startTime = request.getParameter("startTime");
		String endTime =request.getParameter("endTime");
		//业务处理
		//创建房屋对象保存数据
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		LesseeInformationDao lesseeInformationDao = new LesseeInformationDaoImpl();
		
		HouseInformation houseInformation = new HouseInformation(houseId, ownerName, houseType, houseArea, houseNature, houseState, houseAddress);
		//调用数据层添加方法并接收返回值
		
		//创建房屋对象保存数据
		LesseeInformation lesseeInformation = new LesseeInformation(houseId, lesseeName, lesseeGender, lesseeIdcard, lesseeTelphone, startTime, endTime);
		
		//调用数据层添加方法并接收返回值
		lesseeInformationDao.add(lesseeInformation);
		int rows = houseInformationDao.update(houseInformation);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}
	/**
	 * 通过名字查询
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException 异常
	 */
	private void findByName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//定义输出流
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
		//定义Gson对象
		Gson gson=new Gson();
		//序列化
		String house=gson.toJson(houseInformation);
		out.print(house);
		
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
		String houseIdStr = request.getParameter("houseIdStr");
		//将字符串按"，"分割
		String[] houseIdS = houseIdStr.split(",");
		//创建数据层操作对象
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		try {
			for(int i = 0;i<houseIdS.length;i++){
				String houseId=houseIdS[i];
				houseInformationDao.delete(houseId);
				
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
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String houseType = request.getParameter("houseType");
		double houseArea = Double.parseDouble(request.getParameter("houseArea"));
		String houseNature = request.getParameter("houseNature");
		String houseState = request.getParameter("houseState");
		String houseAddress = request.getParameter("houseAddress");
		//业务处理
		//建立数据层链接对象
		if(!houseState.equals("出租")){
			LesseeInformationDao lesseeInformationDao = new LesseeInformationDaoImpl();
			lesseeInformationDao.deleteA(houseId);
		}
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		//创建房屋对象保存数据
		HouseInformation houseInformation = new HouseInformation(houseId, ownerName, houseType, houseArea, houseNature, houseState, houseAddress);
		//调用数据层添加方法并接收返回值
		int rows = houseInformationDao.update(houseInformation);
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
		//接收来自网页数据
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String houseType = request.getParameter("houseType");
		double houseArea = Double.parseDouble(request.getParameter("houseArea"));
		String houseNature = request.getParameter("houseNature");
		String houseState = request.getParameter("houseState");
		String houseAddress = request.getParameter("houseAddress");
		//业务处理
		//建立数据层链接对象
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		//创建房屋对象保存数据
		HouseInformation houseInformation = new HouseInformation(houseId, ownerName, houseType, houseArea, houseNature, houseState, houseAddress);
		//调用数据层添加方法并接收返回值
		int rows = houseInformationDao.add(houseInformation);
		//判断返回值
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
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String houseType = request.getParameter("houseType");
		String houseNature = request.getParameter("houseNature");
		String houseState = request.getParameter("houseState");
		String condition = " where 1=1 ";
		//对条件数据进行判断并组合成查询条件
		if(houseId!=null&&!houseId.equals("")){
			condition=condition+" and houseId like '%"+houseId+"%' ";
		}
		if(ownerName!=null&&!ownerName.equals("")){
			condition = condition+" and ownerName like '%"+ownerName+"%' ";
		}
		if(houseType!=null&&!houseType.equals("")){
			condition = condition+" and houseType like '%"+houseType+"%' ";
		}
		if(houseNature!=null&&!houseNature.equals("")){
			condition = condition+" and houseNature like '%"+houseNature+"%' ";
		}
		if(houseState!=null&&!houseState.equals("")){
			condition = condition+" and houseState like '%"+houseState+"%' ";
		}
		//接收来自客户端传递过来的page和rows参数
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//建立数据层连接
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		//调用数据层执行分页查询并将结果保存到集合中
		List<HouseInformation> houseInformationsList = houseInformationDao.findByPage(rows, page, condition);
		int totalRows = houseInformationDao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", houseInformationsList);
		//定义Gson对象
		Gson gson = new Gson();
		//通过Gson对象将Map集合转换成Json数据格式
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
