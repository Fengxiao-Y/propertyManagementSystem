package com.yidu.focus.ll.servlet;

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
import com.yidu.focus.ll.dao.DeptDao;
import com.yidu.focus.ll.dao.impl.DeptDaoImpl;
import com.yidu.focus.ll.domain.Dept;
/**
 * 
 * ���ܣ����ű� 
 * ���ߣ�����
 * ���ڣ�2019��10��31������9:10:37
 * �汾��1.0
 */
@WebServlet("/DeptServlet")
public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeptServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ����Ĳ��ű���ַ���
		String deptNoStr=request.getParameter("deptNoStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] deptNos=deptNoStr.split(",");
		//�������ݲ��������
		DeptDao deptDao=new DeptDaoImpl();
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<deptNos.length;i++){
				//��ȡ��ǰ���ַ���
				String tdeptNo=deptNos[i];
				//���ַ������ת��Ϊ���͵�Ա�����
				int deptNo=Integer.parseInt(tdeptNo);
				//ִ��ɾ������
				deptDao.delete(deptNo);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}
		//�ر��������
		out.close();

	}


	/**
	 * �޸�
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �׳��쳣
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //�������������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//���ű��
		String deptNo=request.getParameter("deptNo");
		//��������
		String deptName=request.getParameter("deptName");
		//Ա�����
		String empNo=request.getParameter("empNo");
		//�ڱ�����
		int empCount=Integer.parseInt(request.getParameter("empCount"));		
		//���ݷ�װ�ɶ���
		Dept dept=new Dept(deptNo, deptName, empNo, empCount);		
		//�������ݲ����ִ����ӷ���
		DeptDao deptDao=new DeptDaoImpl();
		int rows=deptDao.update(dept);
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
	 * ���
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �׳��쳣
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//���ű��
		String deptNo=request.getParameter("deptNo");
		//���ű��
		String deptName=request.getParameter("deptName");
		//Ա�����
		String empNo=request.getParameter("empNo");
		//�ڱ�����
		int empCount=Integer.parseInt(request.getParameter("empCount"));		
		//���ݷ�װ�ɶ���
		Dept dept=new Dept(deptNo, deptName, empNo, empCount);		
		//�������ݲ����ִ����ӷ���
		DeptDao deptDao=new DeptDaoImpl();
		int rows=deptDao.add(dept);
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
	 * @throws IOException  �׳�����
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//�õ����������е���������
		//��������
		String deptName=request.getParameter("deptName");
		//���ű��
		String empNo=request.getParameter("empNo");
		//���ɲ�ѯ����
		String condition=" where 1=1 ";
		//���ݲ������Ʋ�ѯ
		if(deptName!=null && !deptName.equals("")){
			condition=condition+"and deptName like '%"+deptName+"%' ";
		}
		//����Ա����Ų�ѯ
		if(empNo!=null && !empNo.equals("")){
			condition=condition+"and empNo like '%"+empNo+"%' ";
		}		
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//�������ݲ�ִ�з�ҳ��ѯ
		DeptDao deptdao=new DeptDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<Dept> deptlist=deptdao.findByPage(rows, page, condition);
		//��ѯ��attendance����ܼ�¼��
		int totalRows=deptdao.count();
		//����ӳ�伯��
		Map<String, Object> mapData = new HashMap<String, Object>();
		//�����ݱ��浽map������
		mapData.put("total", totalRows);
		mapData.put("rows", deptlist);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		//��json����������ͻ���
		out.println(jsonData);
		//�ر������
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
