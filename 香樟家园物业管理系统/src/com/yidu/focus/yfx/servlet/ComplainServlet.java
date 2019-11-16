package com.yidu.focus.yfx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yidu.focus.wzh.domain.Users;
import com.yidu.focus.yfx.dao.ComplainDao;
import com.yidu.focus.yfx.dao.impl.ComplainDaoImpl;
import com.yidu.focus.yfx.domain.Complain;
/**
 * 
 * ���ܣ�Ͷ�߲�����
 * ���ߣ��Ϸ�Т
 * ���ڣ�2019��10��31������8:50:13
 * �汾��1.0
 */
@WebServlet("/ComplainServlet")
public class ComplainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ComplainServlet() { 
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���������ַ���
		request.setCharacterEncoding("utf-8");
		//������Ӧ��������
		response.setContentType("text/html");
		//������Ӧ�ַ��������ʽ
		response.setCharacterEncoding("utf-8");		
		//���������еĲ���
		String method=request.getParameter("method");
		//�ж������еĲ���
		if("findAll".equals(method)){
			this.findAll(request,response);
		}else if("add".equals(method)){
			this.add(request,response);
		}else if("update".equals(method)){
			this.update(request,response);
		}else if("delete".equals(method)){
			this.delete(request,response);
		}else if("findByName".equals(method)){
			this.findByName(request,response);
		}else if("tousu".equals(method)){
			this.tousu(request,response);
		}		
	}
	
	/**
	 * ҵ���ڿͻ���Ͷ�ߵ�ʵ�ַ���
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣
	 */
	private void tousu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out=response.getWriter();
		//����session����
		HttpSession session=request.getSession();
		//ȡ��session�е�user����
		Users user=(Users) session.getAttribute("users");	
		//�õ���ǰ���û���
		String ownerName=user.getuName();
		//Ͷ������
		String comText=request.getParameter("comText");
		//��ȡ��ǰϵͳʱ��
		Date date = new Date();
		//��ϵͳʱ��ת��Ϊ���ݿ��ʽ
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//��ʱ��ת�����ַ�����ʽ
		String comTime=sdf.format(date);
		//Ĭ��Ա��
		String empName="��С��";
		//Ĭ�ϴ�����
		String comResult="δ����";
		//���ʱ��
		String comEndTime="2099-12-31";
		//���ݷ�װ�ɶ���
		Complain complain=new Complain(ownerName, comText, comTime, empName, comResult, comEndTime);
		//����Ͷ�ߵ����ݲ��������
		ComplainDao complainDao=new ComplainDaoImpl();
		//�������ݲ����ִ����ӷ���,�õ�Ӱ������
		int rows=complainDao.add(complain);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//�ر���
		out.close();
		
	}
	/**
	 * ͨ��ҵ���������ҵ�Ͷ�߼�¼
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣
	 */
	private void findByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out=response.getWriter();
		//����session����
		HttpSession session=request.getSession();
		//ȡ��session�е�user����
		Users user=(Users) session.getAttribute("users");
		//�õ���ǰ���û���
		String ownerName=user.getuName();
		//�������ݲ����
		ComplainDao complainDao=new ComplainDaoImpl();
		//�������ݲ㷽��
		List<Complain> complainList=complainDao.findByName(ownerName);
		//����gson����
		Gson gson=new Gson();
		//���л�
		String compl=gson.toJson(complainList);	
		//���
		out.print(compl);
		//�ر���
		out.close();
		
	}

	/**
	 * ɾ�����ݿ���е�����
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws ServletException servlet�쳣
	 * @throws IOException IO���쳣
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�������������
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ�����Ͷ�߱���ַ���
		String comStr=request.getParameter("comIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] comIds=comStr.split(",");		
		//�������ݲ��������
		ComplainDao complainDao=new ComplainDaoImpl();		
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<comIds.length;i++){
				//��ȡ��ǰ���ַ���
				String temp=comIds[i];
				//���ַ���ת��������
				int comId=Integer.parseInt(temp);
				//ִ��ɾ������
				complainDao.deleteById(comId);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}
		//�ر��������
		out.close();
	}

	/**
	 * �������ݿ���е�����
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws ServletException servlet�쳣
	 * @throws IOException IO���쳣
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�������������
		PrintWriter out=response.getWriter();		
		//�������Կͻ��˵�����
		//Ͷ�߱��
		int comId=Integer.parseInt(request.getParameter("comId"));
		//ҵ������
		String ownerName=request.getParameter("ownerName");
		//Ͷ������
		String comText=request.getParameter("comText");
		//Ͷ��ʱ��
		String comTime=request.getParameter("comTime");
		//������
		String empName=request.getParameter("empName");
		//������
		String comResult=request.getParameter("comResult");
		//�ط�ʱ��
		String comEndTime=request.getParameter("comEndTime");
		//���ݷ�װ�ɶ���
		Complain complain=new Complain(comId, ownerName, comText, comTime, empName, comResult, comEndTime);		
		//����Ͷ�ߵ����ݲ��������
		ComplainDao complainDao=new ComplainDaoImpl();
		//�������ݲ����ִ����ӷ������õ�Ӱ������
		int rows=complainDao.update(complain);
		//�ж��޸��Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//�ر������
		out.close();
	}

	/**
	 * �����ݿ������������
	 * @param request �������
	 * @param response ��Ӧ����
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�������������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//ҵ������
		String ownerName=request.getParameter("ownerName");
		//Ͷ������
		String comText=request.getParameter("comText");		
		//Ͷ��ʱ��
		String comTime=request.getParameter("comTime");
		//������
		String empName=request.getParameter("empName");
		//������
		String comResult=request.getParameter("comResult");
		//�ط�ʱ��
		String comEndTime=request.getParameter("comEndTime");		
		//���ݷ�װ�ɶ���
		Complain complain=new Complain(ownerName, comText, comTime, empName, comResult, comEndTime);
		//����Ͷ�����ݲ��������
		ComplainDao complainDao=new ComplainDaoImpl();
		//�������ݲ����ִ����ӷ���,�õ�Ӱ������
		int rows=complainDao.add(complain);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//�ر���
		out.close();
	}

	/**
	 * ����ȫ��Ͷ�����ݵķ���
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws ServletException servlet�쳣
	 * @throws IOException IO�쳣
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���������
		PrintWriter out=response.getWriter();		
		//�õ����������е���������
		//ҵ������
		String ownerName=request.getParameter("ownerName");
		//Ͷ������
		String comText=request.getParameter("comText");
		//Ͷ��ʱ��
		String comTime=request.getParameter("comTime");	
		//���ɲ�ѯ����
		String condition=" where 1=1 ";
		//����ҵ������
		if(ownerName!=null && !ownerName.equals("")){
			//���ɰ�ҵ������ģ����ѯ������
			condition=condition+" and ownerName like '%"+ownerName+"%' ";
		}
		//����Ͷ������
		if(comText!=null && !comText.equals("")){
			//���ɰ�Ͷ������ģ����ѯ������
			condition=condition+" and comText like '%"+comText+"%' ";
		}
		//����Ͷ��ʱ���ֶ�ֵ�����жϣ���������
		if(comTime!=null && !comTime.equals("")){
			//���ɰ�Ͷ��ʱ��ģ����ѯ������
			condition=condition+" and comTime like '%"+comTime+"%' ";
		}	
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//�������ݲ�ִ�з�ҳ��ѯ
		ComplainDao complainDao=new ComplainDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<Complain> complainList = complainDao.findByPage(rows, page, condition);
		//��ѯ��complain����ܼ�¼��
		int totalRows=complainDao.count(condition);		
		//����ӳ�伯�϶���	
		Map<String, Object> mapData = new HashMap<String, Object>();
		//�����ݱ��浽map������
		mapData.put("total", totalRows);
		mapData.put("rows", complainList);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		//��json����������ͻ���
		out.println(jsonData);
		//�ر���
		out.close();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
