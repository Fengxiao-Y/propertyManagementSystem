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
 * Servlet implementation class Checkservlet
 */
@WebServlet("/Checkservlet")
public class Checkservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Checkservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��Ӧ��ʽת��
		response.setContentType("text/html,charset=utf-8");
		//��Ӧ����
		response.setCharacterEncoding("UTF-8");
		//���������
		PrintWriter out=response.getWriter();
		//��ȡע�����
		String uIphone=request.getParameter("uIphone");
		//�����û�����
		UsersDao usersDao=new UsersDaoImpl();
		//�����û�ʵ�������
		Users users=new Users();
		//���÷���
		users=usersDao.findByUIphone(uIphone);
		
		HttpSession session=request.getSession();
		session.setAttribute("users", users);
		if(users!=null){
			
			out.println("<font color='green'>�����ѱ�ע��!</font>");
		}
		
		out.close();

	}
}
