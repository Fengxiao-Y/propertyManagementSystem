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
 * ���ܣ���Ʒ���� 
 * ���ߣ���־��
 * ���ڣ�2019��10��31������9:17:46
 * �汾��1.0
 */
@WebServlet("/StockServlet")
public class StockServlet extends HttpServlet {

	private static final long serialVersionUID = -8364798491533503116L;

	public StockServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����������Ӧ���ַ�������������
		//����������ַ���
		request.setCharacterEncoding("utf-8");
		//������Ӧ���ַ�������������
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//��ȡ����·���еĲ�������ֵ
		String method=request.getParameter("method");
		//�ж�
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
	 * �������
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �׳��쳣
	 */
	private void chuku(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//������Ʒ����
		String goodsName=request.getParameter("goodsName");
		//����Ҫ���õ���Ʒ����
		int ggNum = Integer.parseInt(request.getParameter("ggNum"));
		//���������˵�����
		String ggName = request.getParameter("ggName");
		//��������ʱ��
		String ggTime = request.getParameter("ggTime");
		//���վ�����
		String ggHandli = request.getParameter("ggHandli");
		//�����������ݲ��������
		StockDao stockdao=new StockDaoImpl();
		//���ÿ�����ݲ㰴����Ʒ���鵥���ķ���
		Stock stock=stockdao.findByName(goodsName);
		//ȡ�ö�Ӧ�Ŀ��ID
		int stockId = stock.getStockId();
		//ȡ�ö�Ӧ�Ŀ������
		int stockNum1=stock.getStockNum();
		//�ж�Ҫ���õ�����С�ڿ��������
		if(ggNum==stockNum1 || ggNum<stockNum1){
			//С�ڵ���
			//�������õ����ݲ��������
			GetGoodsDao getGoodsDao=new GetGoodsDaoImpl();
			//�����ö����װ��ʵ������
			GetGoods getGoods=new GetGoods(goodsName, ggNum, ggName, ggTime, ggHandli);
			//�����������ݲ����ӷ������Զ��������ü�¼
			int rows = getGoodsDao.add(getGoods);
			if(rows>0){
				//���óɹ�
				out.print("success");
			}else{
				//����ʧ��
				out.print("failure");
			}
			//�������ȥ���������õ����ú�Ŀ������
			int stockNum=stockNum1-ggNum;
			//�����ݷ�װ��ʵ������
			stock = new Stock(stockId, goodsName, stockNum);
			//���¿������Ϣ
			stockdao.update(stock);
		}else{
			//������õ��������ڿ�����������ʾ����ʧ��
			out.print("�����������");
		}
		//�ر������
		out.close();

	}

	/**
	 * ������
	 * @param request �������
	 * @param response  ��Ӧ����
	 */
	private void ruku(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ȡ��ҳ�д����Ĳɹ�Id
		String proId=request.getParameter("proIds");
		//�����ɹ������ݲ��������
		ProcurementDao procurementDao=new ProcurementDaoImpl();
		//���òɹ����ݲ�������󰴲ɹ����ִ���ҵ����ķ���
		Procurement procurement=procurementDao.findById(proId);
		//����ʵ���࣬�õ���Ʒ����
		String proGoodsName=procurement.getGoodsName();
		//�����������ݲ��������
		StockDao stockDao=new StockDaoImpl();
		//���ÿ�����ݲ��������ִ�в���ȫ�����ݵķ���
		List<Stock> stockList=stockDao.findAll();
		//�������ж�����
		boolean flag=false;
		//������漯��
		for(int i=0;i<stockList.size();i++){
			//�������ϵõ������������Ʒ����
			String goodsName=stockList.get(i).getGoodsName();
			//�жϲɹ�����Ʒ���Ƿ�����ڿ�����
			if(goodsName.equals(proGoodsName)){
				//����
				flag=true;
			}
		}

		if(flag){
			//�ɹ�����Ʒ���ƴ��ڿ�����
			//������Ʒ�����ҵ���Ӧ�Ŀ����Ϣ
			Stock stock1=stockDao.findByName(proGoodsName);
			//ȡ�ÿ����
			int stockId=stock1.getStockId();
			//������ӵõ��������
			int stockNum=stock1.getStockNum()+procurement.getProNum();
			//�����ݷ�װ��������
			Stock stock=new Stock(stockId,stockNum);
			//���¿����Ϣ
			stockDao.update(stock);
		}else{
			//����û�вɹ������Ʒ��
			//ȡ����Ʒ����
			String goodsName=proGoodsName;
			//ȡ�ÿ����
			int stockNum=procurement.getProNum();
			//�����ݷ�װ��������
			Stock stock=new Stock(goodsName, stockNum);
			//����Ʒ���ӵ�������
			stockDao.add(stock);
		}		
	}

	/**
	 * ����
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �׳��쳣
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//�����
		int stockId=Integer.parseInt(request.getParameter("stockId"));
		//�����Ʒ
		String goodsName=request.getParameter("goodsName");
		//�����
		int stockNum=Integer.parseInt(request.getParameter("stockNum"));
		//���ݷ�װ�ɶ���
		Stock stock=new Stock(stockId, goodsName, stockNum);
		//�������ݲ����ִ����ӷ���
		StockDao stockDao=new StockDaoImpl();
		//����һ�������������ո��º������
		int rows=stockDao.update(stock);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			//�ɹ�
			out.print("success");
		}else{
			//ʧ��
			out.print("failure");
		}
		//�ر����
		out.close();
	}

	/**
	 *��ҳ��ʾ
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException  �׳�����
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������	
		PrintWriter out=response.getWriter();
		//�õ����������е���������
		//�����
		String stockIds=request.getParameter("stockId");
		//��Ʒ����
		String goodsName=request.getParameter("goodsName");
		//���ɲ�ѯ����
		String condition=" where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		//���ݿ�����ֶ�ֵ�����жϣ���������
		if(stockIds!=null && !stockIds.equals("")){
			//ת����������
			int stockId=Integer.parseInt(stockIds);
			//��ӵ�������
			condition=condition+" and stockId like '%"+stockId+"%' ";
		}
		//������Ʒ�����ֶ�ֵ�����жϣ���������
		if(goodsName!=null && !goodsName.equals("")){
			condition=condition+" and goodsName like '%"+goodsName+"%' ";
		}
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//�������ݲ�ִ�з�ҳ��ѯ
		StockDao stockDao=new StockDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<Stock> stockList=stockDao.findByPage(rows, page,condition);
		//��ѯ��Stock����ܼ�¼��
		int totalRows=stockDao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		//�����ݱ��浽map������
		mapData.put("total", totalRows);
		mapData.put("rows", stockList);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		//��json����������ͻ���
		out.println(jsonData);
		//�ر�
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
