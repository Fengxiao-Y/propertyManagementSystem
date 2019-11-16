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


@WebServlet("/ForgetServlet")
public class ForgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
   
    public ForgetServlet() {
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
				HttpSession session=request.getSession();
				//����ע�����
				Users users=(Users)session.getAttribute("users");
				String uIphone=users.getuIphone();
				
		
				//����
				String uPassword=request.getParameter("uPassword1");
				//������������
				String uPassword2=request.getParameter("uPassword2");
				
				
				
				//�������ݲ����Ӷ���
				UsersDao usersdao=new UsersDaoImpl();
				int uId=users.getuId();
				//ȡ��ԭ�����û���
				String uName=users.getuName();
				
				//ȡ��ԭ��������
				String uEmail=users.getuEmail();
					//����������������һ��,��������
					if(uPassword.equals(uPassword2)&&!uPassword.equals("")){
						//���޸ĺõ������װ��ʵ������
						users=new Users(uId,uName, uPassword, uEmail, uIphone);
						//�����޸ĵķ���
						int rows=usersdao.update(users);
						
						if(rows>0){
							out.print("�޸ĳɹ�");
							response.sendRedirect("http://localhost:8080/focus/html-wzh/UsersLogin.jsp");
						}else{
							out.print("�޸�ʧ��");
							response.sendRedirect("http://localhost:8080/focus/html-wzh/Forget.jsp");
						}
					
					}
					out.close();
				}
				
				
				



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
