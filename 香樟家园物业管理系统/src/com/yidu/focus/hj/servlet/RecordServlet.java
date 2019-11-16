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
import com.yidu.focus.hj.dao.ArrearageDao;
import com.yidu.focus.hj.dao.CarArrearageDao;
import com.yidu.focus.hj.dao.IncomeDao;
import com.yidu.focus.hj.dao.OwnerInformationDao;
import com.yidu.focus.hj.dao.RecordDao;
import com.yidu.focus.hj.dao.impl.ArrearageDaoImpl;
import com.yidu.focus.hj.dao.impl.CarArrearageDaoImpl;
import com.yidu.focus.hj.dao.impl.IncomeDaoImpl;
import com.yidu.focus.hj.dao.impl.OwnerInformationDaoImpl;
import com.yidu.focus.hj.dao.impl.RecordDaoImpl;
import com.yidu.focus.hj.domain.Arrearage;
import com.yidu.focus.hj.domain.CarArrearage;
import com.yidu.focus.hj.domain.Income;
import com.yidu.focus.hj.domain.OwnerInformation;
import com.yidu.focus.hj.domain.Record;
import com.yidu.focus.wzh.domain.Users;

/**
 * Servlet implementation class RecordServlet
 */
@WebServlet("/RecordServlet")
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String String = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordServlet() {
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
		}else if(method.equals("jiaofei")){
			this.jiaofei(request,response);
		}
	}

	private void jiaofei(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出流对象
		PrintWriter out = response.getWriter();
		//定义session对象
		HttpSession session=request.getSession();
		//取出session中的users对象
		Users user=(Users) session.getAttribute("users");
		//取出此时登录的用户名
		String ownerName=user.getuName();
		//定义欠费明细数据层对象
		RecordDao recordDao=new RecordDaoImpl();
		//调用数据层按名字查询的方法
		Record record=(Record) recordDao.getRecordByName(ownerName);
		//得到编号
		int recordId=record.getRecordId();
		//得到电话
		String ownerTelphone=record.getOwnerTelphone();
		//得到类型
		String recordType=record.getRecordType();
		//此项费用清空
		double recordMoney=0.0;
		//获取当前系统时间
		Date date = new Date();
		//将系统时间转化为数据库格式
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String recordDate=sdf.format(date);
		//封装
		Record record1 = new Record(ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		int rows = recordDao.add(record1);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		
		//定义业主信息对象
		OwnerInformationDao ownerInformationDao=new OwnerInformationDaoImpl();
		//调用通过名字查询的方法
		OwnerInformation ownerInformation=ownerInformationDao.findOwnerInformationByName(ownerName);
		//得到房号
		String houseId=ownerInformation.getHouseId();
		//改变状态“未欠费”
		String state="未欠费";
		//欠费金额清0
		double arrearageMoney=recordMoney;
		//保存缴费时间
		String arrearageDate=recordDate;
		//封装
		Arrearage arrearage=new Arrearage(houseId, ownerName, ownerTelphone, state, arrearageMoney, arrearageDate);
		//定义物业欠费对象
		ArrearageDao arreargaDao=new ArrearageDaoImpl();
		//调用修改方法
		int rowswuye=arreargaDao.update(arrearage);
		
		//定义停车费欠费对象
		CarArrearageDao carArreargaDao=new CarArrearageDaoImpl();
		//调用按名字查询的方法
		CarArrearage carArrearage=carArreargaDao.getCarArrearageByName(ownerName);
		//车位编号
		String parkId=carArrearage.getParkId();
		//车位状态
		String parkStatus=carArrearage.getParkStatus();
		//欠费清0
		double carMoney=0.0;
		//缴费时间
		String carDate=recordDate;
		//封装
		CarArrearage carArrearage2=new CarArrearage(parkId, ownerName, ownerTelphone, parkStatus, carMoney, carDate);
		int rowscar=carArreargaDao.update(carArrearage2);
		
		if(rowswuye>0){
			//得到费用来源
			String source="物业费";
			//得到缴费金额
			double money=record.getRecordMoney();
			//来源时间
			String source_date=recordDate;
			//封装
			Income income=new Income(source, money, source_date);
			//定义收入对象
			IncomeDao incomDao=new IncomeDaoImpl();
			//添加到income表中
			incomDao.add(income);
		}else if(rowscar>0){
			//得到费用来源
			String source="停车费";
			//得到缴费金额
			double money=record.getRecordMoney();
			//来源时间
			String source_date=recordDate;
			//封装
			Income income=new Income(source, money, source_date);
			//定义收入对象
			IncomeDao incomDao=new IncomeDaoImpl();
			//添加到income表中
			incomDao.add(income);
		}
		
		
		out.close();
		
	}

	private void findByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出流对象
		PrintWriter out=response.getWriter();
		//定义session对象
		HttpSession session=request.getSession();
		//取出session中的users
		Users user=(Users) session.getAttribute("users");
		//取出此时登录的用户名
		String ownerName=user.getuName();
		//定义数据层对象
		RecordDao recordDao=new RecordDaoImpl();
		//调用数据层方法得到集合
		List<Record> recordList=recordDao.getRecordByName(ownerName);
		//定义Gson对象
		Gson gson = new Gson();
		//通过Gson对象将Map集合转换成Json数据格式
		String reclist = gson.toJson(recordList);
		
		out.print(reclist);
		out.close();
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建是输出流对象
		PrintWriter out = response.getWriter();
		//接收来自网页数据
		String recordIdStr = request.getParameter("recordIdStr");
		//将字符串按"，"分割
		String[] recordIdS = recordIdStr.split(",");
		//创建数据层操作对象
		RecordDao recordDao = new RecordDaoImpl();
		try {
			for(int i = 0;i<recordIdS.length;i++){
				String trecordId=recordIdS[i];
				int recordId = Integer.parseInt(trecordId);
				recordDao.delete(recordId);
				
			}
			out.print("success");
		} catch (Exception e) {
			out.print("failure");
		}
		out.close();
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		int recordId = Integer.parseInt(request.getParameter("recordId"));
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String recordType = request.getParameter("recordType");
		double recordMoney = Double.parseDouble(request.getParameter("recordMoney"));
		String recordDate = request.getParameter("recordDate");
		RecordDao recordDao = new RecordDaoImpl();
		Record record = new Record(recordId, ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		int rows = recordDao.update(record);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String recordType = request.getParameter("recordType");
		double recordMoney = Double.parseDouble(request.getParameter("recordMoney"));
		String recordDate = request.getParameter("recordDate");
		RecordDao recordDao = new RecordDaoImpl();
		Record record = new Record(ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		int rows = recordDao.add(record);
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
		String recordId = request.getParameter("recordId");
		String ownerName = request.getParameter("ownerName");
		String recordType = request.getParameter("recordType");
		String condition = " where 1=1 ";
		//对条件数据进行判断并组合成查询条件
		if(recordId!=null&&!recordId.equals("")){
			condition=condition+" and recordId like '%"+recordId+"%' ";
		}
		if(ownerName!=null&&!ownerName.equals("")){
			condition = condition+" and ownerName like '%"+ownerName+"%' ";
		}
		if(recordType!=null&&!recordType.equals("")){
			condition = condition+" and recordType like '%"+recordType+"%' ";
		}
		//接收来自客户端传递过来的page和rows参数
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//建立数据层连接
		RecordDao recordDao = new RecordDaoImpl();
		//调用数据层执行分页查询并将结果保存到集合中
		List<Record> recordList = recordDao.findByPage(rows, page, condition);
		int totalRows = recordDao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		//将数据保存到map集合中
		mapData.put("total", totalRows);
		mapData.put("rows", recordList);
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
