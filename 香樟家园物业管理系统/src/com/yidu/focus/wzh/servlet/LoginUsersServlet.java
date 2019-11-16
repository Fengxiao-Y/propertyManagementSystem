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
		//设置请求和响应的字符集及内容类型
		//设置请求的字符集
		request.setCharacterEncoding("utf-8");
		//设置响应的字符集和内容类型
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//接收来自客户端的数据
		//后台账号
		String uName=request.getParameter("uName");
		
		//密码
		String uPassword=request.getParameter("uPassword");
		
	
		//建立数据层连接对象
		UsersDao usersdao=new UsersDaoImpl();
		//调用数据层登录的方法
		Users users=usersdao.doLogin(uName, uPassword);
		
		if(users!=null){
			HttpSession session=request.getSession();
			session.setAttribute("users",users);
			
			response.sendRedirect("http://localhost:8080/focus/html-yfx/ownerService.jsp");
		}else{
			out.print("登录失败，请重新登录！");
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
