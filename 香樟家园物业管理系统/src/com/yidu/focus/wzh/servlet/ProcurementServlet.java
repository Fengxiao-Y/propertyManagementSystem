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
import com.yidu.focus.hj.dao.ExpenseDao;
import com.yidu.focus.hj.dao.impl.ExpenseDaoImpl;
import com.yidu.focus.hj.domain.Expense;
import com.yidu.focus.wzh.dao.ProcurementDao;
import com.yidu.focus.wzh.dao.impl.ProcurementDaoImpl;
import com.yidu.focus.wzh.domain.Procurement;

/**
 * 
 * ���ܣ����ϲɹ���
 * ���ߣ���־��
 * ���ڣ�2019��10��31������9:16:29
 * �汾��1.0
 */
@WebServlet("/ProcurementServlet")
public class ProcurementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProcurementServlet() {
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
		}else if("add".equals(method)){
			this.add(request,response);
		}else if("update".equals(method)){
			this.update(request,response);
		}else if("delete".equals(method)){
			this.delete(request,response);
		}
	}

	/**
	 * ɾ�����ݵķ���
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ����Ŀ�����ַ���
		String proIdStr=request.getParameter("proIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] proIds=proIdStr.split(",");
		//�����ɹ����ݲ��������
		ProcurementDao procurementDao=new ProcurementDaoImpl();
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<proIds.length;i++){
				//��ȡ��ǰ���ַ���
				String proId=proIds[i];
				//ִ��ɾ������
				procurementDao.delete(proId);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}
		//�ر��������
		out.close();
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
		//���òɹ����
		String proId=request.getParameter("proId");
		//�ɹ���
		String proName=request.getParameter("proName");
		//��Ʒ����
		String goodsName=request.getParameter("goodsName");
		//�ɹ�ʱ��
		String proTime=request.getParameter("proTime");
		//�ɹ�����
		int proNum=Integer.parseInt(request.getParameter("proNum"));
		//����
		double proPrice=Double.parseDouble(request.getParameter("proPrice"));
		//�ܼ�
		double proTolal=proNum*proPrice;
		//�Ѳɹ����ݷ�װ�ɶ���
		Procurement procurement=new Procurement(proId, proName, goodsName, proTime, proNum, proPrice, proTolal);
		//�������ݲ����ִ����ӷ���
		ProcurementDao procurementDao=new ProcurementDaoImpl();
		int rows= procurementDao.update(procurement);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			//��ӳɹ�
			out.print("success");
		}else{
			//���ʧ��
			out.print("failure");
		}
		//�ر��������
		out.close();
	}

	/**
	 * ����
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �׳��쳣
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//�������Կͻ��˵�����
		//���òɹ����
		String proId=request.getParameter("proId");
		//�ɹ���
		String proName=request.getParameter("proName");
		//��Ʒ����
		String goodsName=request.getParameter("goodsName");
		//�ɹ�ʱ��
		String proTime=request.getParameter("proTime");
		//�ɹ�����
		int proNum=Integer.parseInt(request.getParameter("proNum"));
		//����
		double proPrice=Double.parseDouble(request.getParameter("proPrice"));
		//�ܼ�
		double proTolal=proNum*proPrice;
		//���ݷ�װ�ɶ���
		Procurement procurement=new Procurement(proId, proName, goodsName, proTime, proNum, proPrice, proTolal);
		//���òɹ����ݲ����ִ����ӷ���
		ProcurementDao procurementDao=new ProcurementDaoImpl();
		int rows= procurementDao.add(procurement);		
		//�ɹ�֧��
		String expenseItem = "�ɹ�֧��";
		//֧������
		double expenseMoney = proTolal;
		//֧��ʱ��
		String expense_date = proTime;
		//����֧�����ݲ����
		ExpenseDao expenseDao = new ExpenseDaoImpl();
		//�����ݷ�װ��֧��ʵ������
		Expense expense = new Expense(expenseItem, expenseMoney, expense_date);
		//������ӵķ���
		expenseDao.add(expense);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
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
		String proId=request.getParameter("proId");
		String goodsName=request.getParameter("goodsName");
		//���ɲ�ѯ����
		String condition=" where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		//���ݲɹ�����ֶ�ֵ�����жϣ���������
		if(proId!=null && !proId.equals("")){
			//��ӵ�������
			condition=condition+" and proId like '%"+proId+"%' ";
		}
		//������Ʒ�����ֶ�ֵ�����жϣ���������
		if(goodsName!=null && !goodsName.equals("")){
			condition=condition+" and goodsName like '%"+goodsName+"%' ";
		}
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//�������ݲ�ִ�з�ҳ��ѯ
		ProcurementDao procurementDao=new ProcurementDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<Procurement> procurementList=procurementDao.findByPage(rows, page,condition);
		//��ѯ������ܼ�¼��
		int totalRows=procurementDao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		//�����ݱ��浽map������
		mapData.put("total", totalRows);
		mapData.put("rows", procurementList);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		//��json����������ͻ���
		out.println(jsonData);
		//�ر����
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
