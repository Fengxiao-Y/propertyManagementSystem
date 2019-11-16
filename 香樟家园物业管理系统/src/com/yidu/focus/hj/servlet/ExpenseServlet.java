package com.yidu.focus.hj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yidu.focus.hj.dao.ExpenseDao;
import com.yidu.focus.hj.dao.impl.ExpenseDaoImpl;
import com.yidu.focus.hj.domain.Expense;

/**
 * 
 * ���ܣ�֧������
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��21������10:58:48
 */
@WebServlet("/ExpenseServlet")
public class ExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpenseServlet() {
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
		}else if(method.equals("findTotal")){
			findTotal(request,response);
		}
	}
	/**
	 * ��ѯ�ܼ�
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void findTotal(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String datego = "";
		String datestop="";
		String expenseItem = request.getParameter("expenseItem");
		
		datego = request.getParameter("datego");
		datestop = request.getParameter("datestop");
		
		String month=request.getParameter("month");
		String condition = " where 1=1 ";
		if(datestop==null || datestop.equals("")){
			datestop="2099-12-30";
		}
		if(expenseItem!=null&&!expenseItem.equals("")){
			condition=condition+" and expenseItem like '%"+expenseItem+"%' ";
		}
		if(datego!=null&&!datego.equals("")){
			condition=condition+" and expense_date between '"+datego+"' and '"+datestop+"'";
		}
		if(month!=null&&!month.equals("")){
			condition=condition+" and expense_date like '%"+month+"%' ";
		}
		
		//����������ҳ��rows��page
		int rows = Integer.parseInt(request.getParameter("rows"));
	
		int page = Integer.parseInt(request.getParameter("page"));
		
		//�������ݲ�����
		ExpenseDao expenseDao = new ExpenseDaoImpl();
		//�������ݲ㷽��ִ�з�ҳ��ѯ����������浽������
		List<Expense> expenseList = expenseDao.findTotal(rows, page, condition);
		int totalRows = expenseDao.count(condition);
		double sum = 0;
		for (Expense expense : expenseList) {
			sum=expense.getExpenseMoney()+sum;
		}
		
		//����ӳ�伯�϶���
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", expenseList);
		List<Expense> footer = new ArrayList<>();
		Expense expense = new Expense();
		
		expense.setExpenseItem("�ܼƣ�");
		expense.setExpenseMoney(sum);
		footer.add(expense);
		mapData.put("footer", footer);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����Json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		out.print(jsonData);
		out.close();
		
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
		String expenseIdStr = request.getParameter("expenseIdStr");
		//���ַ�����"��"�ָ�
		String[] expenseIdS = expenseIdStr.split(",");
		//�������ݲ��������
		ExpenseDao expenseDao = new ExpenseDaoImpl();
		try {
			for(int i = 0;i<expenseIdS.length;i++){
				String texpenseId=expenseIdS[i];
				int expenseId = Integer.parseInt(texpenseId);
				expenseDao.delete(expenseId);
				
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
		int expenseId = Integer.parseInt(request.getParameter("expenseId"));
		String expenseItem = request.getParameter("expenseItem");
		double expenseMoney = Double.parseDouble(request.getParameter("expenseMoney"));
		String expense_date = request.getParameter("expense_date");
		//�������ݲ�����
		ExpenseDao expenseDao = new ExpenseDaoImpl();
		//����������󱣴�����
		Expense expense = new Expense(expenseId, expenseItem, expenseMoney, expense_date);
		//������ӷ��������շ���ֵ
		int rows = expenseDao.update(expense);
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
		String expenseItem = request.getParameter("expenseItem");
		double expenseMoney = Double.parseDouble(request.getParameter("expenseMoney"));
		String expense_date = request.getParameter("expense_date");
		//�������ݲ�����
		ExpenseDao expenseDao = new ExpenseDaoImpl();
		//����������󱣴�����
		Expense expense = new Expense(expenseItem, expenseMoney, expense_date);
		//������ӷ��������շ���ֵ
		int rows = expenseDao.add(expense);
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
		String expenseItem = request.getParameter("expenseItem");
		
		String expense_date = request.getParameter("expense_date");
		
		String condition = " where 1=1 ";
		if(expenseItem!=null&&!expenseItem.equals("")){
			condition=condition+" and expenseItem like '%"+expenseItem+"%' ";
		}
		if(expense_date!=null&&!expense_date.equals("")){
			condition=condition+" and expense_date like '%"+expense_date+"%' ";
		}
		//����������ҳ��rows��page
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//�������ݲ�����
		ExpenseDao expenseDao = new ExpenseDaoImpl();
		//�������ݲ�ִ�з�ҳ��ѯ����������浽������
		List<Expense> expensesList = expenseDao.findByPage(rows, page, condition);
		int totalRows = expenseDao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", expensesList);
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
