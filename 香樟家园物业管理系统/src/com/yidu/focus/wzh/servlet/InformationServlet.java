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
import com.yidu.focus.wzh.dao.InformationDao;
import com.yidu.focus.wzh.dao.impl.InformationDaoImpl;
import com.yidu.focus.wzh.domain.Information;
/**
 * 
 * ���ܣ���̨������Ա��
 * ���ߣ���־��
 * ���ڣ�2019��10��31������11:07:16
 * �汾��1.0
 */
@WebServlet("/InformationServlet")
public class InformationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 504930980717468700L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InformationServlet() {
		super();
		// TODO Auto-generated constructor stub
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
	 * ��������ɾ������
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �׳��쳣
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ����ĺ�̨�˺��ַ���
		String backIdStr=request.getParameter("backIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] backIds=backIdStr.split(",");
		//�������ݲ��������
		InformationDao informationDao=new InformationDaoImpl();
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<backIds.length;i++){
				//��ȡ��ǰ���ַ���
				String backId=backIds[i];
				//ִ��ɾ������
				informationDao.delete(backId);
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
	 * @throws IOException  IO�쳣
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//��̨�˺�
		String backId=request.getParameter("backId");
		//����
		String backPwd=request.getParameter("backPwd");
		//Ա����
		String empName=request.getParameter("empName");
		//ְ��
		String backPost=request.getParameter("backPost");
		//���ݷ�װ�ɶ���
		Information information=new Information(backId, backPwd, empName, backPost);
		//���������ݲ��������
		InformationDao informationDao=new InformationDaoImpl();
		//�������ݲ����ִ����ӷ���
		int rows=informationDao.update(information);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//�ر������
		out.close();
	}

	/**
	 * ����
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException  IO�쳣
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//��̨�˺�
		String backId=request.getParameter("backId");
		//����
		String backPwd=request.getParameter("backPwd");
		//Ա����
		String empName=request.getParameter("empName");
		//ְ��
		String backPost=request.getParameter("backPost");
		//���ݷ�װ�ɶ���
		Information information=new Information(backId, backPwd, empName, backPost);
		//�������ݲ����
		InformationDao informationDao=new InformationDaoImpl();
		//�������ݲ����ִ����ӷ���
		int rows=informationDao.doRegister(information);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//�ر������
		out.close();
	}

	/**
	 *��ҳ��ʾ
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������	
		PrintWriter out=response.getWriter();
		//�õ����������е���������
		//��̨��¼�˺�
		String backId=request.getParameter("backId");
		//Ա����
		String empName=request.getParameter("empName");		
		//���ɲ�ѯ����
		String condition=" where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		//���ݺ�̨��¼�˺Ŷ�ֵ�����жϣ���������
		if(backId!=null && !backId.equals("")){
			condition=condition+" and backId like '%"+backId+"%' ";
		}
		//����Ա�����ֶ�ֵ�����жϣ���������
		if(empName!=null && !empName.equals("")){
			condition=condition+" and empName like '%"+empName+"%' ";
		}
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//�������ݲ�ִ�з�ҳ��ѯ
		InformationDao informationdao=new InformationDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<Information> informationList=informationdao.findByPage(rows, page,condition);
		//��ѯ��emp����ܼ�¼��
		int totalRows=informationdao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		//�����ݱ��浽map������
		mapData.put("total", totalRows);
		mapData.put("rows", informationList);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		//��json����������ͻ���
		out.println(jsonData);
		//�ر������
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
