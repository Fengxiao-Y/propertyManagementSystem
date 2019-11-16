package com.yidu.focus.hj.servlet;

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
import com.yidu.focus.hj.dao.CarArrearageDao;
import com.yidu.focus.hj.dao.IncomeDao;
import com.yidu.focus.hj.dao.RecordDao;
import com.yidu.focus.hj.dao.impl.CarArrearageDaoImpl;
import com.yidu.focus.hj.dao.impl.IncomeDaoImpl;
import com.yidu.focus.hj.dao.impl.RecordDaoImpl;
import com.yidu.focus.hj.domain.CarArrearage;
import com.yidu.focus.hj.domain.Income;
import com.yidu.focus.hj.domain.Record;
import com.yidu.focus.wzh.domain.Users;

/**
 * Servlet implementation class CarArrearageServlet
 */
@WebServlet("/CarArrearageServlet")
public class CarArrearageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarArrearageServlet() {
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
			this.findAll(request,response);
		}else if(method.equals("add")){
			this.add(request,response);
		}else if(method.equals("update")){
			this.update(request,response);
		}else if(method.equals("delete")){
			this.delete(request,response);
		}else if(method.equals("pay")){
			this.pay(request,response);
		}else if(method.equals("jiaofei")){
			this.jiaofei(request,response);
		}
	}

	

	private void jiaofei(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出流对象
		PrintWriter out = response.getWriter();
		String parkId = request.getParameter("parkId");
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");	
		//改变状态“已交清”
		String parkStatus="已交清";
		//欠费金额清0
		double carMoney=0.0;
		//获取当前系统时间
		Date date = new Date();
		//将系统时间转化为数据库格式
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String carDate=sdf.format(date);
		//封装
		CarArrearage carArrearage=new CarArrearage(parkId, ownerName, ownerTelphone, parkStatus, carMoney, carDate);
		//定义停车欠费对象
		CarArrearageDao carArreargaDao=new CarArrearageDaoImpl();
		//调用修改方法
		int rows=carArreargaDao.update(carArrearage);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		
		/*完成物业费的缴纳，向缴费详情记录表添加数据*/
		//定义缴费详情数据层对象
		RecordDao recordDao=new RecordDaoImpl();
		//得到类型
		String recordType="停车费";
		//得到缴费金额
		double recordMoney=Double.parseDouble(request.getParameter("carMoney"));
		//缴费时间
		String recordDate=carDate;
		//封装
		Record record1 = new Record(ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		recordDao.add(record1);
		
		
		/*汇总到收入报表*/
		//得到费用来源
		String source=recordType;
		//得到缴费金额
		double money=recordMoney;
		//来源时间
		String source_date=carDate;
		//封装
		Income income=new Income(source, money, source_date);
		//定义收入对象
		IncomeDao incomDao=new IncomeDaoImpl();
		//添加到income表中
		incomDao.add(income);
		//关闭输出流
		out.close();
		
	}

	private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建是输出流对象
		PrintWriter out = response.getWriter();
		//定义session对象
		HttpSession session=request.getSession();
		//取出session中的对象
		Users users=(Users)session.getAttribute("users");
		//取出名字
		String ownerName=users.getuName();
		//定义停车费欠费表数据层连接对象
		CarArrearageDao carArreargeDao=new CarArrearageDaoImpl();
		//调用数据层方法，得到对象
		CarArrearage carArrearage=carArreargeDao.getCarArrearageByName(ownerName);
		//车位编号
		String parkId=carArrearage.getParkId();
		String ownerTelphone=carArrearage.getOwnerTelphone();
		String parkStatus="已交清";
		Double carMoney=0.0;
		
		//获取当前系统时间
		Date date = new Date();
		//将系统时间转化为数据库格式
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String carDate=sdf.format(date);
		//封装
		CarArrearage carArrea = new CarArrearage(parkId, ownerName, ownerTelphone, parkStatus, carMoney, carDate);
		//调用修改的方法
		int rows = carArreargeDao.update(carArrea);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		
		//定义欠费明细表的数据层对象
		RecordDao recordDao=new RecordDaoImpl();
		//费用类型
		String recordType="停车费";
		//金额
		Double recordMoney=carArrearage.getCarMoney();
		//时间
		String recordDate=carDate;
		//封装数据
		Record record=new Record(ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		//调用添加的方法
		recordDao.add(record);
		//调用收入表的方法
		String source = recordType;
		double money = recordMoney;
		String source_date = recordDate;
		Income income = new Income(source, money, source_date);
		IncomeDao incomeDao = new IncomeDaoImpl();
		incomeDao.add(income);
		out.close();
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建是输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String parkIdStr = request.getParameter("parkIdStr");
		//将字符串按"，"分割
		String[] parkIdS = parkIdStr.split(",");
		//创建数据层操作对象
		CarArrearageDao carArrearageDao = new CarArrearageDaoImpl();
		try {
			for(int i = 0;i<parkIdS.length;i++){
				String parkId=parkIdS[i];
				carArrearageDao.delete(parkId);
				
			}
			out.print("success");
		} catch (Exception e) {
			out.print("failure");
		}
		out.close();
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String parkId = request.getParameter("parkId");
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String parkStatus = request.getParameter("parkStatus");
		double carMoney = Double.parseDouble(request.getParameter("carMoney"));
		String carDate = request.getParameter("carDate");
		CarArrearageDao carArrearageDao = new CarArrearageDaoImpl();
		CarArrearage carArrearage = new CarArrearage(parkId, ownerName, ownerTelphone, parkStatus, carMoney, carDate);
		int rows = carArrearageDao.update(carArrearage);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String parkId = request.getParameter("parkId");
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String parkStatus = request.getParameter("parkStatus");
		double carMoney = Double.parseDouble(request.getParameter("carMoney"));
		String carDate = request.getParameter("carDate");
		CarArrearageDao carArrearageDao = new CarArrearageDaoImpl();
		CarArrearage carArrearage = new CarArrearage(parkId, ownerName, ownerTelphone, parkStatus, carMoney, carDate);
		int rows = carArrearageDao.add(carArrearage);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String parkId = request.getParameter("parkId");
		String ownerName = request.getParameter("ownerName");
		String parkStatus = request.getParameter("parkStatus");
		String condition = " where 1=1 ";
		//对条件数据进行判断并组合成查询条件
		if(parkId!=null&&!parkId.equals("")){
			condition=condition+" and parkId like '%"+parkId+"%' ";
		}
		if(ownerName!=null&&!ownerName.equals("")){
			condition = condition+" and ownerName like '%"+ownerName+"%' ";
		}
		if(parkStatus!=null&&!parkStatus.equals("")){
			condition = condition+" and parkStatus like '%"+parkStatus+"%' ";
		}
		//接收来自客户端传递过来的page和rows参数
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//建立数据层连接
		CarArrearageDao carArrearageDao = new CarArrearageDaoImpl();
		//调用数据层执行分页查询并将结果保存到集合中
		List<CarArrearage> carArrearageList = carArrearageDao.findByPage(rows, page, condition);
		int totalRows = carArrearageDao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", carArrearageList);
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
