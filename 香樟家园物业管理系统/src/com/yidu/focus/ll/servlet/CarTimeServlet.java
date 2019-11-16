package com.yidu.focus.ll.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yidu.focus.hj.dao.IncomeDao;
import com.yidu.focus.hj.dao.impl.IncomeDaoImpl;
import com.yidu.focus.hj.domain.Income;
import com.yidu.focus.ll.dao.CarTimeDao;
import com.yidu.focus.ll.dao.impl.CarTimeDaoImpl;
import com.yidu.focus.ll.domain.CarTime;
/**
 * 
 * 功能：临时停车 
 * 作者：刘李
 * 日期：2019年10月31日上午9:10:15
 * 版本：1.0
 */
@WebServlet("/CarTimeServlet")
public class CarTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarTimeServlet() {

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
		}else if("delete".equals(method)){
			this.delete(request,response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * 根据主键删除数据
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 抛出异常
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		//获取来自请中所传递过来的序号字符串
		String NubStr=request.getParameter("NubStr");		
		//将字符串进行按","(逗号)分割的子字符串数组
		String[] Nubs=NubStr.split(",");
		//创建数据层操作对象
		CarTimeDao carTimedao=new CarTimeDaoImpl();
		//由于是批量删除，有可能失败，此处使用异常处理来实现
		try{
			//使用循环进行批量删除
			for(int i=0;i<Nubs.length;i++){
				//获取当前子字符串
				String tNubStr=Nubs[i];
				//将字符串编号转换为整型的序号
				int Nub=Integer.parseInt(tNubStr);
				//执行删除操作
				carTimedao.delete(Nub);
			}
			out.print("success");

		}catch(Exception e){
			out.print("failure");
		}
		//关闭输出流
		out.close();
	}

		
		/**
		 * 添加
		 * @param request 请求对象
		 * @param response 响应对象
		 * @throws IOException 抛出异常
		 */
		private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
			//定义输出流对象
			PrintWriter out=response.getWriter();
			//接收来自客户端的数据
			//车牌号
			String carId=request.getParameter("carId");
			//进入时间
			String goTime=request.getParameter("goTime");
			//离开时间
			String toTime=request.getParameter("toTime");
			//定义金额
			double Money = 0;
			//值班员
			String empNo=request.getParameter("empNo");
			//字符串转换为时间
			Timestamp goTimes = Timestamp.valueOf(goTime);
			Timestamp toTimes = Timestamp.valueOf(toTime);
			//计算时间毫秒数
			long result = toTimes.getTime()-goTimes.getTime();
			//得到分钟数
			result = result/1000;
			//判断，进行阶梯收费
			if(result>60*60*24){
				Money = 40;
			}else if(result>60*60*12){
				Money = 30;
			}else if(result>60*60*4){
				Money = 20;
			}else if(result>60*60){
				Money = 10;
			}else{
				Money=0;
			}
			//数据封装成对象
			CarTime carTime=new CarTime(carId,goTime,toTime,Money,empNo);
			//调用数据层对象执行添加方法
			CarTimeDao carTimeDao=new CarTimeDaoImpl();
			int rows=carTimeDao.add(carTime);
			//判断添加是否成功
			if(rows>0){
				out.print("success");
			
			}else{
				out.print("failure");	
			}
			
			/*将数据同步到收入表中*/
			//定义收入来源
			String source = "临时停车收费";
			//金额
			double money = Money;
			//收费时间
			String source_date = toTime;
			//创建收入表数据层连接对象
			IncomeDao incomeDao = new IncomeDaoImpl();			
			//创建收入对象
			Income income = new Income(source, money, source_date);
			//调用添加的方法
			incomeDao.add(income);
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
			String carId=request.getParameter("carId");		
			//生成查询条件
			String condition=" where 1=1 ";
			//根据车牌号进行模糊查询
			if(carId!=null && !carId.equals("")){
				condition=condition+"and carId like '%"+carId+"%' ";
			}
			//接收来自客户端的datagrid组件的传递过来的page和rows参数
			int rows=Integer.parseInt(request.getParameter("rows"));
			int page=Integer.parseInt(request.getParameter("page"));
			//调用数据层执行分页查询
			CarTimeDao carTimedao=new CarTimeDaoImpl();
			//获得当前页的数据集合
			List<CarTime> carTimeList=carTimedao.findByPage(rows, page, condition);
			//查询出CarTime表的总记录数
			int totalRows=carTimedao.count();
			//定义映射集合对象
			Map<String, Object> mapData = new HashMap<String, Object>();
			//将数据保存到map集合中
			mapData.put("total", totalRows);
			mapData.put("rows", carTimeList);
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
