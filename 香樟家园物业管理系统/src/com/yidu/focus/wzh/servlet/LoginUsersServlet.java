package com.yidu.focus.wzh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yidu.focus.wzh.dao.UsersDao;
import com.yidu.focus.wzh.dao.impl.UsersDaoImpl;
import com.yidu.focus.wzh.domain.Users;

/**
 * Servlet implementation class LoginUsersServlet
 */
@WebServlet("/LoginUsersServlet")
public class LoginUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public LoginUsersServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//�����������Ӧ���ַ�������������
		//����������ַ���
		request.setCharacterEncoding("utf-8");
		//������Ӧ���ַ�������������
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//�������Կͻ��˵�����
		//��̨�˺�
		String uName=request.getParameter("uName");
		
		//����
		String uPassword=request.getParameter("uPassword");
		
	
		//�������ݲ����Ӷ���
		UsersDao usersdao=new UsersDaoImpl();
		//�������ݲ��¼�ķ���
		Users users=usersdao.doLogin(uName, uPassword);
		
		if(users!=null){
			HttpSession session=request.getSession();
			session.setAttribute("users",users);
			
			response.sendRedirect("http://localhost:8080/focus/html-yfx/ownerService.jsp");
		}else{
			out.print("��¼ʧ�ܣ������µ�¼��");
			response.sendRedirect("http://localhost:8080/focus/html-wzh/UsersLogin.jsp");
		}
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
