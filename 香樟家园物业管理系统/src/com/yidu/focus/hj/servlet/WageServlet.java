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
import com.yidu.focus.hj.dao.WageDao;
import com.yidu.focus.hj.dao.impl.WageDaoImpl;
import com.yidu.focus.hj.domain.Wage;

/**
 * 
 * ���ܣ�����
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��31������6:50:40
 */
@WebServlet("/WageServlet")
public class WageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WageServlet() {
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
	 * ɾ��
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String wageidStr = request.getParameter("wageidStr");
		//���ַ�����"��"�ָ�
		String[] wageidS = wageidStr.split(",");
		//�������ݲ��������
		WageDao wageDao = new WageDaoImpl();
		try {
			for(int i = 0;i<wageidS.length;i++){
				String twageid=wageidS[i];
				int wageid = Integer.parseInt(twageid);
			
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
		int wageid = Integer.parseInt(request.getParameter("wageid"));
		String empName = request.getParameter("empName");
		double salary = Double.parseDouble(request.getParameter("salary"));
		double commision = Double.parseDouble(request.getParameter("commision"));
		double withhold = Double.parseDouble(request.getParameter("withhold"));
		double playMoney = Double.parseDouble(request.getParameter("playMoney"));
		String wageMonth = request.getParameter("wageMonth");
		//�����⻧����
		Wage wage = new Wage(wageid, empName, salary, commision, withhold, playMoney, wageMonth);
		//�������ݲ�����
		WageDao wageDao = new WageDaoImpl();
		//������ӷ��������շ���ֵ
		int rows = wageDao.update(wage);
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
		String empName = request.getParameter("empName");
		double salary = Double.parseDouble(request.getParameter("salary"));
		double commision = Double.parseDouble(request.getParameter("commision"));
		double withhold = Double.parseDouble(request.getParameter("withhold"));
		double playMoney = Double.parseDouble(request.getParameter("playMoney"));
		String wageMonth = request.getParameter("wageMonth");
		//�����⻧����
		Wage wage = new Wage(empName, salary, commision, withhold, playMoney, wageMonth);
		//�������ݲ�����
		WageDao wageDao = new WageDaoImpl();
		//������ӷ��������շ���ֵ
		int rows = wageDao.add(wage);
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
		String empName = request.getParameter("empName");
		String wageMonth = request.getParameter("wageMonth");
		String condition = " where 1=1 ";
		if(empName!=null&&!empName.equals("")){
			condition=condition+" and empName like '%"+empName+"%' ";
		}
		if(wageMonth!=null&&!wageMonth.equals("")){
			condition=condition+" and wageMonth like '%"+wageMonth+"%' ";
		}
		//����������ҳ��rows��page
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//�������ݲ�����
		WageDao wageDao = new WageDaoImpl();
		//�������ݲ�ִ�з�ҳ��ѯ����������浽������
		List<Wage> wageList = wageDao.findByPage(rows, page, condition);
		int totalRows = wageDao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", wageList);
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
