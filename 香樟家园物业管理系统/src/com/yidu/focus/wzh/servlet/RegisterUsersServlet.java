package com.yidu.focus.wzh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.focus.wzh.dao.UsersDao;
import com.yidu.focus.wzh.dao.impl.UsersDaoImpl;
import com.yidu.focus.wzh.domain.Users;


@WebServlet("/RegisterUsersServlet")
public class RegisterUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterUsersServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����������Ӧ���ַ�������������
		//����������ַ���
		request.setCharacterEncoding("utf-8");
		//������Ӧ���ַ�������������
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//�������������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//ҵ������
		String uName=request.getParameter("uName");
		//����
		String uPassword=request.getParameter("uPassword1");
		//������������
		String uPassword2=request.getParameter("uPassword2");
		//�û���
		String uEmail=request.getParameter("uEmail");
		//ע�����
		String uIphone=request.getParameter("uIphone");
		//�������ݲ����Ӷ���
		UsersDao usersdao=new UsersDaoImpl();
		if(uPassword.equals(uPassword2)&&!uPassword.equals("")){
			//�����ݷ�װ��ʵ����
		Users users=new Users(uName, uPassword, uEmail, uIphone);
			//�������ݲ��¼�ķ���
			int rows=usersdao.doRegister(users);
			if(rows!=0){
				out.println("ע��ɹ�");
				//ת������½ҳ����
				response.sendRedirect("http://localhost:8080/focus/html-wzh/UsersLogin.jsp");;
			}else{
				out.println("ע��ʧ��");
				//�ض���ע��ҳ����
				response.sendRedirect("http://localhost:8080/focus/html-wzh/UsersRegister.jsp");
			}
		}else{
			out.println("��¼ʧ��");
		}
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
