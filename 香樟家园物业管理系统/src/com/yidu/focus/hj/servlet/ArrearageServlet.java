package com.yidu.focus.hj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.yidu.focus.hj.dao.ArrearageDao;
import com.yidu.focus.hj.dao.CarArrearageDao;
import com.yidu.focus.hj.dao.IncomeDao;
import com.yidu.focus.hj.dao.RecordDao;
import com.yidu.focus.hj.dao.impl.ArrearageDaoImpl;
import com.yidu.focus.hj.dao.impl.CarArrearageDaoImpl;
import com.yidu.focus.hj.dao.impl.IncomeDaoImpl;
import com.yidu.focus.hj.dao.impl.RecordDaoImpl;
import com.yidu.focus.hj.domain.Arrearage;
import com.yidu.focus.hj.domain.CarArrearage;
import com.yidu.focus.hj.domain.Income;
import com.yidu.focus.hj.domain.Record;
import com.yidu.focus.wzh.domain.Users;

/**
 * Servlet implementation class ArrearageServlet
 */
@WebServlet("/ArrearageServlet")
public class ArrearageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArrearageServlet() {
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
		}else if(method.equals("findByName")){
			this.findByName(request,response);
		}else if(method.equals("pay")){
			this.pay(request,response);
		}else if(method.equals("jiaofei")){
			this.jiaofei(request,response);
		}
	}


	private void jiaofei(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出流对象
		PrintWriter out = response.getWriter();
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");		
		//改变状态“已交清”
		String state="已交清";
		//欠费金额清0
		double arrearageMoney=0.0;
		//获取当前系统时间
		Date date = new Date();
		//将系统时间转化为数据库格式
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String arrearageDate=sdf.format(date);
		//封装
		Arrearage arrearage=new Arrearage(houseId, ownerName, ownerTelphone, state, arrearageMoney, arrearageDate);
		//定义物业欠费对象
		ArrearageDao arreargaDao=new ArrearageDaoImpl();
		//调用修改方法
		int rows=arreargaDao.update(arrearage);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		
		/*完成物业费的缴纳，向缴费详情记录表添加数据*/
		//定义缴费详情数据层对象
		RecordDao recordDao=new RecordDaoImpl();
		//得到类型
		String recordType="物业费";
		//得到缴费金额
		double recordMoney=Double.parseDouble(request.getParameter("arrearageMoney"));
		//缴费时间
		String recordDate=arrearageDate;
		//封装
		Record record1 = new Record(ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		recordDao.add(record1);
		
		
		/*汇总到收入报表*/
		//得到费用来源
		String source=recordType;
		//得到缴费金额
		double money=recordMoney;
		//来源时间
		String source_date=arrearageDate;
		//封装
		Income income=new Income(source, money, source_date);
		//定义收入对象
		IncomeDao incomDao=new IncomeDaoImpl();
		//添加到income表中
		incomDao.add(income);
		//关闭输出流
		out.close();						
	}
	
	/**
	 * 客户端缴费功能
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException IO异常
	 */
	private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建是输出流对象
		PrintWriter out = response.getWriter();
		//定义session对象
		HttpSession session=request.getSession();
		//取出session中的对象
		Users users=(Users)session.getAttribute("users");
		//取出名字
		String ownerName=users.getuName();
		//定义物业费欠费表数据层连接对象
		ArrearageDao arreargeDao=new ArrearageDaoImpl();
		//调用数据层方法，得到对象
		Arrearage arrearage=arreargeDao.getArrearageByName(ownerName);
		//获取房号
		String houseId=arrearage.getHouseId();
		String ownerTelphone=arrearage.getOwnerTelphone();
		String state="已交清";
		Double arrearageMoney=0.0;
		
		//获取当前系统时间
		Date date = new Date();
		//将系统时间转化为数据库格式
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String arrearageDate=sdf.format(date);
		//封装
		Arrearage arrea = new Arrearage(houseId, ownerName, ownerTelphone, state, arrearageMoney, arrearageDate);
		//调用修改的方法
		int rows = arreargeDao.update(arrea);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		
		//定义欠费明细表的数据层对象
		RecordDao recordDao=new RecordDaoImpl();
		
		String recordType="物业费";
		Double recordMoney=arrearage.getArrearageMoney();
		String recordDate=arrearageDate;
		//封装数据
		Record record=new Record(ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		//调用添加的方法
		recordDao.add(record);
		//调用收入表方法把数据添加进去
		String source = recordType;
		double money = recordMoney;
		String source_date = arrearageDate;
		Income income = new Income(source, money, source_date);
		IncomeDao incomeDao = new IncomeDaoImpl();
		incomeDao.add(income);
		out.close();
		
	}

	private void findByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建是输出流对象
		PrintWriter out = response.getWriter();
		//定义session对象
		HttpSession session=request.getSession();
		//取出session中的对象
		Users users=(Users)session.getAttribute("users");
		//取出名字
		String ownerName=users.getuName();
		//定义物业费欠费表数据层连接对象
		ArrearageDao arreargeDao=new ArrearageDaoImpl();
		//调用数据层方法，得到对象
		Arrearage arrearage=arreargeDao.getArrearageByName(ownerName);
		
		//定义固定停车费数据层对象
		CarArrearageDao carArrearageDao=new CarArrearageDaoImpl();
		//调用按名字查询方法
		CarArrearage carArrearage=carArrearageDao.getCarArrearageByName(ownerName);
		//定义一个集合
		List<Object> list=new ArrayList<>();
		list.add(arrearage);
		list.add(carArrearage);
		//定义Gson对象
		Gson gson=new Gson();
		//序列化
		String arrea=gson.toJson(list);
		out.print(arrea);
		out.close();
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建是输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String houseIdStr = request.getParameter("houseIdStr");
		//将字符串按"，"分割
		String[] houseIdS = houseIdStr.split(",");
		//创建数据层操作对象
		ArrearageDao arrearageDao = new ArrearageDaoImpl();
		try {
			for(int i = 0;i<houseIdS.length;i++){
				String houseId=houseIdS[i];
				arrearageDao.delete(houseId);
				
			}
			out.print("success");
		} catch (Exception e) {
			out.print("failure");
		}
		out.close();
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String state = request.getParameter("state");
		double arrearageMoney = Double.parseDouble(request.getParameter("arrearageMoney"));
		String arrearageDate = request.getParameter("arrearageDate");
		ArrearageDao arrearageDao = new ArrearageDaoImpl();
		Arrearage arrearage = new Arrearage(houseId, ownerName, ownerTelphone, state, arrearageMoney, arrearageDate);
		int rows = arrearageDao.update(arrearage);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String state = request.getParameter("state");
		double arrearageMoney = Double.parseDouble(request.getParameter("arrearageMoney"));
		String arrearageDate = request.getParameter("arrearageDate");
		ArrearageDao arrearageDao = new ArrearageDaoImpl();
		Arrearage arrearage = new Arrearage(houseId, ownerName, ownerTelphone, state, arrearageMoney, arrearageDate);
		int rows = arrearageDao.add(arrearage);
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
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String state = request.getParameter("state");
		String condition = " where 1=1 ";
		//对条件数据进行判断并组合成查询条件
		if(houseId!=null&&!houseId.equals("")){
			condition=condition+" and houseId like '%"+houseId+"%' ";
		}
		if(ownerName!=null&&!ownerName.equals("")){
			condition = condition+" and ownerName like '%"+ownerName+"%' ";
		}
		if(state!=null&&!state.equals("")){
			condition = condition+" and state like '%"+state+"%' ";
		}
		//接收来自客户端传递过来的page和rows参数
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//建立数据层连接
		ArrearageDao arrearageDao = new ArrearageDaoImpl();
		//调用数据层执行分页查询并将结果保存到集合中
		List<Arrearage> arrearageList = arrearageDao.findByPage(rows, page, condition);
		int totalRows = arrearageDao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", arrearageList);
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
