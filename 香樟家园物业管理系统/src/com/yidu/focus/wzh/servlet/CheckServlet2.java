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


@WebServlet("/CheckServlet2")
public class CheckServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckServlet2() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				if(users==null){
					out.println("<font color='red'>���벻����!</font>");
				}
				
				out.close();

			}


	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
