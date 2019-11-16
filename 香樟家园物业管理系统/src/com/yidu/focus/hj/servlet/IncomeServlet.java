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
import com.yidu.focus.hj.dao.IncomeDao;
import com.yidu.focus.hj.dao.impl.IncomeDaoImpl;
import com.yidu.focus.hj.domain.Income;

/**
 * 
 * ���ܣ�ʵ�������������
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��31������8:53:23
 */
@WebServlet("/IncomeServlet")
public class IncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������Ӧ�ַ�������
		response.setCharacterEncoding("utf-8");
		//������Ӧ��������
		response.setContentType("text/html");
		//���������ַ�������
		request.setCharacterEncoding("utf-8");
		//����������ҳ����
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
	 * ɾ��
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String incomeIdStr = request.getParameter("incomeIdStr");
		//���ַ�����"��"�ָ�
		String[] incomeIdS = incomeIdStr.split(",");
		//�������ݲ��������
		IncomeDao incomeDao = new IncomeDaoImpl();
		try {
			for(int i = 0;i<incomeIdS.length;i++){
				String tincomeId=incomeIdS[i];
				int incomeId = Integer.parseInt(tincomeId);
				incomeDao.delete(incomeId);
				
			}
			out.print("success");
		} catch (Exception e) {
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
		int incomeId = Integer.parseInt(request.getParameter("incomeId"));
		String source = request.getParameter("source");
		double money = Double.parseDouble(request.getParameter("money"));
		String source_date = request.getParameter("source_date");
		//�������ݲ�����
		IncomeDao incomeDao = new IncomeDaoImpl();
		//����������󱣴�����
		Income income = new Income(incomeId, source, money, source_date);
		//������ӷ��������շ���ֵ
		int rows = incomeDao.update(income);
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
		//����������ҳ����
		String source = request.getParameter("source");
		double money = Double.parseDouble(request.getParameter("money"));
		String source_date = request.getParameter("source_date");
		//�������ݲ�����
		
		IncomeDao incomeDao = new IncomeDaoImpl();
		//����������󱣴�����
		Income income = new Income(source, money, source_date);
		//������ӷ��������շ���ֵ
		int rows = incomeDao.add(income);
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
		String source = request.getParameter("source");
		String source_date = request.getParameter("source_date");
		String condition = " where 1=1 ";
		if(source!=null&&!source.equals("")){
			condition=condition+" and source like '%"+source+"%' ";
		}
		if(source_date!=null&&!source_date.equals("")){
			condition=condition+" and source_date like '%"+source_date+"%' ";
		}
		
		//����������ҳ��rows��page
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//�������ݲ�����
		IncomeDao incomeDao = new IncomeDaoImpl();
		//�������ݲ�ִ�з�ҳ��ѯ����������浽������
		List<Income> incomeList = incomeDao.findByPage(rows, page, condition);
		int totalRows = incomeDao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", incomeList);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����Json���ݸ�ʽ
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
