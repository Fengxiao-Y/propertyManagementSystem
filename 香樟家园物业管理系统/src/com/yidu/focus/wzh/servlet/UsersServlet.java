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
import com.yidu.focus.wzh.dao.UsersDao;
import com.yidu.focus.wzh.dao.impl.UsersDaoImpl;
import com.yidu.focus.wzh.domain.Users;

/**
 * 
 * ���ܣ�ǰ�˵�¼��Ա 
 * ���ߣ���־��
 * ���ڣ�2019��10��31������9:15:42
 * �汾��1.0
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsersServlet() {
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
	 * ��������ɾ������
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �׳��쳣
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ������û�����ַ���
		String uIdStr=request.getParameter("uIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] uIds=uIdStr.split(",");
		//�������ݲ��������
		UsersDao usersdao=new UsersDaoImpl();
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<uIds.length;i++){
				//��ȡ��ǰ���ַ���
				String tempId=uIds[i];
				//���ַ������ת��Ϊ���͵�Ա�����
				int uId=Integer.parseInt(tempId);
				//ִ��ɾ������
				usersdao.delete(uId);
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
		//ҵ�����
		int uId=Integer.parseInt(request.getParameter("uId"));
		//ҵ������
		String uName=request.getParameter("uName");
		//����
		String uPassword=request.getParameter("uPassword");
		//ҵ������
		String uEmail=request.getParameter("uEmail");
		//ҵ������
		String uIphone=request.getParameter("uIphone");
		//���ݷ�װ�ɶ���
		Users users=new Users(uId, uName, uPassword, uEmail, uIphone);
		//�������ݲ����ִ����ӷ���
		UsersDao userdao=new UsersDaoImpl();
		int rows=userdao.update(users);
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
		//ҵ������
		String uName=request.getParameter("uName");
		//����
		String uPassword=request.getParameter("uPassword");
		//ҵ������
		String uEmail=request.getParameter("uEmail");
		//ҵ������
		String uIphone=request.getParameter("uIphone");
		//���ݷ�װ�ɶ���
		Users users=new Users(uName, uPassword, uEmail, uIphone);
		//�������ݲ����ִ����ӷ���
		UsersDao userdao=new UsersDaoImpl();
		int rows=userdao.doRegister(users);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
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
		//�û����
		String uIds=request.getParameter("uId");
		//�û���
		String uName=request.getParameter("uName");
		//�û�����
		String uIphone=request.getParameter("uIphone");
		//���ɲ�ѯ����
		String condition=" where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		//�����û�����ֶ�ֵ�����жϣ���������
		if(uIds!=null && !uIds.equals("")){
			//����deptno��int���ͣ�����ת������
			int uId=Integer.parseInt(uIds);
			//��ӵ�������
			condition=condition+" and uId="+uId+" ";
		}
		//�����û����ֶ�ֵ�����жϣ���������
		if(uName!=null && !uName.equals("")){
			condition=condition+" and uName like '%"+uName+"%' ";
		}
		//�����û������ֶ�ֵ�����жϣ���������
		if(uIphone!=null && !uIphone.equals("")){
			condition=condition+" and uIphone like '%"+uIphone+"%' ";
		}
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//�������ݲ�ִ�з�ҳ��ѯ
		UsersDao userdao=new UsersDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<Users> userList=userdao.findByPage(rows, page,condition);
		//��ѯ��emp����ܼ�¼��
		int totalRows=userdao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		//�����ݱ��浽map������
		mapData.put("total", totalRows);
		mapData.put("rows", userList);
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
