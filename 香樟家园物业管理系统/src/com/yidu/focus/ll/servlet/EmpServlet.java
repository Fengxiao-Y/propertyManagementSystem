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
import com.yidu.focus.ll.dao.EmpDao;
import com.yidu.focus.ll.dao.impl.DeptDaoImpl;
import com.yidu.focus.ll.dao.impl.EmpDaoImpl;
import com.yidu.focus.ll.domain.Dept;
import com.yidu.focus.ll.domain.Emp;
/**
 * 
 * ���ܣ�Ա���� 
 * ���ߣ�����
 * ���ڣ�2019��10��31������9:10:59
 * �汾��1.0
 */
@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpServlet() {
		super();

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
		//���������
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ�����Ա������ַ���
		String empNoStr=request.getParameter("empNoStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] empNos=empNoStr.split(",");
		//�������ݲ��������
		EmpDao empdao=new EmpDaoImpl();
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<empNos.length;i++){
				//��ȡ��ǰ���ַ���
				String empNo=empNos[i];
				//ִ��ɾ������
				empdao.delete(empNo);
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
		//���������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//���
		String empNo=request.getParameter("empNo");
		//����
		String empName=request.getParameter("empName");
		//�Ա�
		String empSex="";
		int sex=Integer.parseInt(request.getParameter("empSex"));
		if(sex==1){
			empSex="��";
		}else{
			empSex="Ů";
		}
		//�绰
		String empTelphone=request.getParameter("empTelphone");
		//���֤����
		String empIdcard=request.getParameter("empIdcard");
		//��ְʱ��
		String hireDate=request.getParameter("hireDate");
		//нˮ
		double salary=Double.parseDouble(request.getParameter("salary"));
		//����
		double commision=Double.parseDouble(request.getParameter("commision"));
		//����
		String deptNo=request.getParameter("deptNo");
		//��˾
		String manager=request.getParameter("manager");
		//���ݷ�װ�ɶ���
		Emp emp=new Emp(empNo, empName, empSex, empTelphone, empIdcard, hireDate, salary, commision, deptNo, manager);
		//�������ݲ����ִ����ӷ���
		EmpDao empdao=new EmpDaoImpl();
		int rows=empdao.update(emp);
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
	 * @throws IOException �׳��쳣
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//���
		String empNo=request.getParameter("empNo");
		//����
		String empName=request.getParameter("empName");
		//�Ա�
		String empSex="";
		int sex=Integer.parseInt(request.getParameter("empSex"));
		if(sex==1){
			empSex="��";
		}else{
			empSex="Ů";
		}
		//�绰
		String empTelphone=request.getParameter("empTelphone");
		//���֤����
		String empIdcard=request.getParameter("empIdcard");
		//��ְʱ��
		String hireDate=request.getParameter("hireDate");
		//нˮ
		double salary=Double.parseDouble(request.getParameter("salary"));
		//����
		double commision=Double.parseDouble(request.getParameter("commision"));
		//����
		String deptNo=request.getParameter("deptNo");
		//��˾
		String manager=request.getParameter("manager");		
		//����һ��ͳ���ڱ�����������
		String condition="where deptNo='"+deptNo+"' ";
		//���ݷ�װ�ɶ���
		Emp emp=new Emp(empNo, empName, empSex, empTelphone, empIdcard, hireDate,
				salary,commision, deptNo,manager);
		//����Ա�����ݲ��������
		EmpDao empDao=new EmpDaoImpl();
		//������ӵķ���
		int rows=empDao.add(emp);
		
		/*�����Ա��ʱ���ı䲿�ŵ��ڱ�����*/
		//����ͳ�Ƶķ���
		int empCount = empDao.count(condition);
		//���岿������
		String deptName = "";
		if(deptNo.equals("xz")){
			deptName = "����";
			empNo="xz-001";
		}else if(deptNo.equals("ba")){
			deptName = "����";
			empNo="ba-001";
		}else if(deptNo.equals("kf")){
			deptName = "�ͷ�";
			empNo="kf-001";
		}else if(deptNo.equals("gc")){
			deptName = "����";
			empNo="gc-001";
		}else if(deptNo.equals("cw")){
			deptName = "����";
			empNo="cw-001";
		}
		//��װ���Ŷ���
		Dept dept = new Dept(deptNo, deptName, empNo, empCount);
		//���岿�ű����ݲ��������
		DeptDao deptDao = new DeptDaoImpl();
		//���ø��µķ���
		deptDao.update(dept);
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
		//Ա�����
		String empNo=request.getParameter("empNo");
		//Ա������
		String empName=request.getParameter("empName");
		//���ű��
		String deptNo=request.getParameter("deptNo");
		
		//���ɲ�ѯ����
		String condition=" where 1=1 ";
		//����Ա�����ģ����ѯ
		if(empNo!=null && !empNo.equals("")){
			condition=condition+"and empNo like '%"+empNo+"%' ";
		}
		//����Ա������ģ����ѯ
		if(empName!=null && !empName.equals("")){
			condition=condition+"and empName like '%"+empName+"%' ";
		}
		//���ݲ��ű��ģ����ѯ
		if(deptNo!=null && !deptNo.equals("")){
			condition=condition+"and deptNo like '%"+deptNo+"%' ";
		}

		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));

		//�������ݲ�ִ�з�ҳ��ѯ
		EmpDao empdao=new EmpDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<Emp> emplist=empdao.findByPage(rows, page, condition);
				
		//��ѯ��emp����ܼ�¼��
		int totalRows=empdao.count();
		//����ӳ�伯��
		Map<String, Object> mapData = new HashMap<String, Object>();
		//�����ݱ��浽map������
		mapData.put("total", totalRows);
		mapData.put("rows", emplist);
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

		doGet(request, response);
	}

}
