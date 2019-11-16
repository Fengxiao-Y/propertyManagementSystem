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
import com.yidu.focus.hj.dao.RatesDao;
import com.yidu.focus.hj.dao.impl.RatesDaoImpl;
import com.yidu.focus.hj.domain.Rates;

/**
 * 
 * ���ܣ��շѱ�׼
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��31������6:33:38
 */
@WebServlet("/RatesServlet")
public class RatesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������Ӧ��������
		response.setContentType("text/html");
		//������Ӧ�ַ�������
		response.setCharacterEncoding("utf-8");
		//���������ַ�������
		request.setCharacterEncoding("utf-8");
		//����������ҳ������
		String method = request.getParameter("method");
		if(method.equals("findAll")){
			findAll(request,response);
		}else if(method.equals("add")){
			add(request,response);
		}else if(method.equals("update")){
			update(request,response);
		}else if(method.equals("delete")){
			delete(request,response);
		}
	}
	/**
	 * ��ѯ����
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */   
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String itemIdStr = request.getParameter("itemIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] itemIdS = itemIdStr.split(",");
		//�������ݲ��������
		RatesDao ratesDao = new RatesDaoImpl();
		try {
			for(int i=0;i<itemIdS.length;i++){
				String titemId = itemIdS[i];
				int itemId = Integer.parseInt(titemId);
				ratesDao.delete(itemId);
			}
			out.print("success");
		} catch (NumberFormatException e) {
			out.print("failure");
		}
		
		out.close();
		
	}
	/**
	 * �޸�
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		String itemName = request.getParameter("itemName");
		String itemDesc = request.getParameter("itemDesc");
		double ratesMoney = Double.parseDouble(request.getParameter("ratesMoney"));
		//ҵ����
		//����һ���������ݵĶ���
		Rates rates = new Rates(itemId, itemName, itemDesc, ratesMoney);
		//�������ݿ����Ӷ���
		RatesDao ratesDao = new RatesDaoImpl();
		//�����޸ķ��������շ���ֵ
		int rows = ratesDao.update(rates);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
	}
	/**
	 * ���
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out = response.getWriter();
		//����������ҳ������
		String itemName = request.getParameter("itemName");
		String itemDesc = request.getParameter("itemDesc");
		double ratesMoney = Double.parseDouble(request.getParameter("ratesMoney"));
		//ҵ����
		//�������ݲ����Ӷ���
		RatesDao ratesDao = new RatesDaoImpl();
		//�����ݷ�װ�ɶ���
		Rates rates = new Rates(itemName, itemDesc, ratesMoney);
		//�������ݲ���ӷ��������շ���ֵ
		int rows = ratesDao.add(rates);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
	}
	/**
	 * ��ѯ����
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String itemName = request.getParameter("itemName");
		String condition=" where 1=1 ";
		if(itemName!=null && !itemName.equals("")){
			condition=condition+" and itemName like '%"+itemName+"%' ";
		}
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//ҵ����
		//�������ݲ����Ӷ���
		RatesDao ratesDao = new RatesDaoImpl();
		List<Rates> ratesList = ratesDao.findByPage(rows, page, condition);
		int totalRows=ratesDao.count(condition);
		//����ӳ�伯�϶���
		Map<String,Object> mapData=new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", ratesList);
		//����Gson����
		Gson gson=new Gson();
		//ͨ��Gson����Map����ת����json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		//��json����������ͻ���
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
