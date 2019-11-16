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
import com.yidu.focus.ll.dao.AttendanceDao;
import com.yidu.focus.ll.dao.impl.AttendanceDaoImpl;
import com.yidu.focus.ll.domain.Attendance;
/**
 * 
 * ���ܣ�Ա�����ڱ� 
 * ���ߣ�����
 * ���ڣ�2019��10��31������9:09:51
 * �汾��1.0
 */
@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttendanceServlet() {
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
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ����Ŀ��ڱ���ַ���
		String attendIdStr=request.getParameter("attendIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] attendIds=attendIdStr.split(",");
		//�������ݲ��������
		AttendanceDao attendancedao=new AttendanceDaoImpl();
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<attendIds.length;i++){
				//��ȡ��ǰ���ַ���
				String tattendId=attendIds[i];
				//���ַ������ת��Ϊ���͵�Ա�����
				int attendId=Integer.parseInt(tattendId);
				//ִ��ɾ������
				attendancedao.delete(attendId);
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
		//���
		int attendId=Integer.parseInt(request.getParameter("attendId"));
		//Ա�����
		String empNo=request.getParameter("empNo");
		//�ϰ�ʱ��
		String onWork=request.getParameter("onWork");		
		//�°�ʱ��
		String doWork=request.getParameter("doWork");
		//���ݷ�װ�ɶ���
		Attendance attendance=new Attendance(attendId, empNo, onWork, doWork);
		//�������ݲ����ִ����ӷ���
		AttendanceDao attendanceDao=new AttendanceDaoImpl();
		int rows=attendanceDao.update(attendance);
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
		//Ա�����
		String empNo=request.getParameter("empNo");
		//�ϰ�ʱ��
		String onWork=request.getParameter("onWork");		
		//�°�ʱ��
		String doWork=request.getParameter("doWork");
		//���ݷ�װ�ɶ���
		Attendance attendance=new Attendance(empNo, onWork, doWork);
		//�������ݲ����ִ����ӷ���
		AttendanceDao attendanceDao=new AttendanceDaoImpl();
		int rows=attendanceDao.add(attendance);
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
		String empNo=request.getParameter("empNo");
		//���ɲ�ѯ����
		String condition=" where 1=1 ";
		//����Ա����Ž��в�ѯ
		if(empNo!=null && !empNo.equals("")){
			condition=condition+"and empNo like '%"+empNo+"%' ";
		}
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//�������ݲ�ִ�з�ҳ��ѯ
		AttendanceDao attendancedao=new AttendanceDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<Attendance> attendancelist=attendancedao.findByPage(rows, page, condition);
		//��ѯ��attendance����ܼ�¼��
		int totalRows=attendancedao.count();
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		//�����ݱ��浽map������
		mapData.put("total", totalRows);
		mapData.put("rows", attendancelist);
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
