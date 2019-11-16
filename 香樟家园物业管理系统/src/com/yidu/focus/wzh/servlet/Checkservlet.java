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
		//响应格式转码
		response.setContentType("text/html,charset=utf-8");
		//响应编码
		response.setCharacterEncoding("UTF-8");
		//创建输出流
		PrintWriter out=response.getWriter();
		//获取注册号码
		String uIphone=request.getParameter("uIphone");
		//创建用户到层
		UsersDao usersDao=new UsersDaoImpl();
		//建立用户实体类对象
		Users users=new Users();
		//调用方法
		users=usersDao.findByUIphone(uIphone);
		
		HttpSession session=request.getSession();
		session.setAttribute("users", users);
		if(users!=null){
			
			out.println("<font color='green'>号码已被注册!</font>");
		}
		
		out.close();

	}
}
