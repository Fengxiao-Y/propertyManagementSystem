package com.yidu.focus.wzh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yidu.focus.wzh.dao.InformationDao;
import com.yidu.focus.wzh.dao.impl.InformationDaoImpl;
import com.yidu.focus.wzh.domain.Information;


@WebServlet("/LoginBackServlet")
public class LoginBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginBackServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����������Ӧ���ַ�������������
		//����������ַ���
		request.setCharacterEncoding("utf-8");
		//������Ӧ���ַ�������������
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//���������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//��̨�˺�
		String empName=request.getParameter("empName");
		//����
		String backPwd=request.getParameter("backPwd");
		//������̨�û�ʵ�����
		Information information=new Information();
		//�������ݲ����Ӷ���
		InformationDao informationdao=new InformationDaoImpl();
		//�������ݲ��¼�ķ���
		information=informationdao.doLogin(empName, backPwd);
		HttpSession session=request.getSession();
		session.setAttribute("information", information);
		if(information!=null){
			out.print("��¼�ɹ�!");
			
			response.sendRedirect("http://localhost:8080/focus/html-yfx/BackIndex.jsp");
			
			
		}else{
			out.print("�û��������벻��ȷ!");
			response.sendRedirect("BackLogin.jsp");
		}
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
