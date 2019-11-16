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

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateUsersServlet")
public class UpdateUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
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
					//response.sendRedirect("http://localhost:8080/focus/html-yfx/ownerService.jsp");
					//request.getRequestDispatcher("/html-yfx/ownerService.jsp").forward(request, response);
				

				}else{
					//out.print("�޸�ʧ��");
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
