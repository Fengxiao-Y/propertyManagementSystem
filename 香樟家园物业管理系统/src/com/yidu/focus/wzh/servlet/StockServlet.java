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
import com.yidu.focus.wzh.dao.ProcurementDao;
import com.yidu.focus.wzh.dao.StockDao;
import com.yidu.focus.wzh.dao.impl.GetGoodsDaoImpl;
import com.yidu.focus.wzh.dao.impl.ProcurementDaoImpl;
import com.yidu.focus.wzh.dao.impl.StockDaoImpl;
import com.yidu.focus.wzh.domain.GetGoods;
import com.yidu.focus.wzh.domain.Procurement;
import com.yidu.focus.wzh.domain.Stock;

/**
 * 
 * 功能：物品库存表 
 * 作者：伍志华
 * 日期：2019年10月31日上午9:17:46
 * 版本：1.0
 */
@WebServlet("/StockServlet")
public class StockServlet extends HttpServlet {

	private static final long serialVersionUID = -8364798491533503116L;

	public StockServlet() {
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
		}else if("ruku".equals(method)){
			this.ruku(request,response);
		}else if("chuku".equals(method)){
			this.chuku(request,response);
		}

	}

	/**
	 * 出库操作
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 抛出异常
	 */
	private void chuku(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//接收物品名称
		String goodsName=request.getParameter("goodsName");
		//接收要领用的物品数量
		int ggNum = Integer.parseInt(request.getParameter("ggNum"));
		//接收领用人的姓名
		String ggName = request.getParameter("ggName");
		//接收领用时间
		String ggTime = request.getParameter("ggTime");
		//接收经办人
		String ggHandli = request.getParameter("ggHandli");
		//创建库存的数据层操作对象
		StockDao stockdao=new StockDaoImpl();
		//调用库存数据层按照物品名查单个的方法
		Stock stock=stockdao.findByName(goodsName);
		//取得对应的库存ID
		int stockId = stock.getStockId();
		//取得对应的库存数量
		int stockNum1=stock.getStockNum();
		//判断要领用的数量小于库存数量吗
		if(ggNum==stockNum1 || ggNum<stockNum1){
			//小于等于
			//创建领用的数据层操作对象
			GetGoodsDao getGoodsDao=new GetGoodsDaoImpl();
			//把领用对象封装在实体类中
			GetGoods getGoods=new GetGoods(goodsName, ggNum, ggName, ggTime, ggHandli);
			//调用领用数据层的添加方法，自动生成领用记录
			int rows = getGoodsDao.add(getGoods);
			if(rows>0){
				//领用成功
				out.print("success");
			}else{
				//领用失败
				out.print("failure");
			}
			//库存数减去领用数，得到领用后的库存数据
			int stockNum=stockNum1-ggNum;
			//把数据封装在实体类中
			stock = new Stock(stockId, goodsName, stockNum);
			//更新库存表的信息
			stockdao.update(stock);
		}else{
			//如果领用的数量大于库存的数量，提示领用失败
			out.print("库存数量不足");
		}
		//关闭输出流
		out.close();

	}

	/**
	 * 入库操作
	 * @param request 请求对象
	 * @param response  响应对象
	 */
	private void ruku(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取网页中传来的采购Id
		String proId=request.getParameter("proIds");
		//创建采购的数据层操作对象
		ProcurementDao procurementDao=new ProcurementDaoImpl();
		//调用采购数据层操作对象按采购编号执行找单个的方法
		Procurement procurement=procurementDao.findById(proId);
		//根据实体类，得到物品名称
		String proGoodsName=procurement.getGoodsName();
		//创建库存的数据层操作对象
		StockDao stockDao=new StockDaoImpl();
		//调用库存数据层操作对象执行查找全部数据的方法
		List<Stock> stockList=stockDao.findAll();
		//设立个判断条件
		boolean flag=false;
		//遍历库存集合
		for(int i=0;i<stockList.size();i++){
			//遍历集合得到库存表的所有物品名称
			String goodsName=stockList.get(i).getGoodsName();
			//判断采购的物品名是否存在在库存表中
			if(goodsName.equals(proGoodsName)){
				//存在
				flag=true;
			}
		}

		if(flag){
			//采购的物品名称存在库存表中
			//根据物品名字找到对应的库存信息
			Stock stock1=stockDao.findByName(proGoodsName);
			//取得库存编号
			int stockId=stock1.getStockId();
			//数量相加得到库存数量
			int stockNum=stock1.getStockNum()+procurement.getProNum();
			//把数据封装到对象中
			Stock stock=new Stock(stockId,stockNum);
			//更新库存信息
			stockDao.update(stock);
		}else{
			//库存表没有采购表的物品名
			//取得物品名称
			String goodsName=proGoodsName;
			//取得库存量
			int stockNum=procurement.getProNum();
			//把数据封装到对象中
			Stock stock=new Stock(goodsName, stockNum);
			//把商品增加到库存表中
			stockDao.add(stock);
		}		
	}

	/**
	 * 更新
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 抛出异常
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出流
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//库存编号
		int stockId=Integer.parseInt(request.getParameter("stockId"));
		//库存商品
		String goodsName=request.getParameter("goodsName");
		//库存量
		int stockNum=Integer.parseInt(request.getParameter("stockNum"));
		//数据封装成对象
		Stock stock=new Stock(stockId, goodsName, stockNum);
		//调用数据层对象执行添加方法
		StockDao stockDao=new StockDaoImpl();
		//定义一个行数变量接收更新后的数据
		int rows=stockDao.update(stock);
		//判断添加是否成功
		if(rows>0){
			//成功
			out.print("success");
		}else{
			//失败
			out.print("failure");
		}
		//关闭输出
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
		//库存编号
		String stockIds=request.getParameter("stockId");
		//物品名称
		String goodsName=request.getParameter("goodsName");
		//生成查询条件
		String condition=" where 1=1 ";
		//对条件数据进行判断并组合成查询条件
		//根据库存编号字段值进行判断，生成条件
		if(stockIds!=null && !stockIds.equals("")){
			//转换数据类型
			int stockId=Integer.parseInt(stockIds);
			//添加到条件中
			condition=condition+" and stockId like '%"+stockId+"%' ";
		}
		//根据物品名称字段值进行判断，生成条件
		if(goodsName!=null && !goodsName.equals("")){
			condition=condition+" and goodsName like '%"+goodsName+"%' ";
		}
		//接收来自客户端的datagrid组件的传递过来的page和rows参数
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//调用数据层执行分页查询
		StockDao stockDao=new StockDaoImpl();
		//获得当前页的数据集合
		List<Stock> stockList=stockDao.findByPage(rows, page,condition);
		//查询出Stock表的总记录数
		int totalRows=stockDao.count(condition);
		//定义映射集合对象
		Map<String, Object> mapData = new HashMap<String, Object>();
		//将数据保存到map集合中
		mapData.put("total", totalRows);
		mapData.put("rows", stockList);
		//定义Gson对象
		Gson gson = new Gson();
		//通过Gson对象将Map集合转换成json数据格式
		String jsonData = gson.toJson(mapData);
		//将json数据输出到客户端
		out.println(jsonData);
		//关闭
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
