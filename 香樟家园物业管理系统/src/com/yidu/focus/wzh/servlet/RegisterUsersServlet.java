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
		//设置请求和响应的字符集及内容类型
		//设置请求的字符集
		request.setCharacterEncoding("utf-8");
		//设置响应的字符集和内容类型
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//创建输出流对象
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//业主姓名
		String uName=request.getParameter("uName");
		//密码
		String uPassword=request.getParameter("uPassword1");
		//重新输入密码
		String uPassword2=request.getParameter("uPassword2");
		//用户名
		String uEmail=request.getParameter("uEmail");
		//注册号码
		String uIphone=request.getParameter("uIphone");
		//建立数据层连接对象
		UsersDao usersdao=new UsersDaoImpl();
		if(uPassword.equals(uPassword2)&&!uPassword.equals("")){
			//把数据封装在实体类
		Users users=new Users(uName, uPassword, uEmail, uIphone);
			//调用数据层登录的方法
			int rows=usersdao.doRegister(users);
			if(rows!=0){
				out.println("注册成功");
				//转发到登陆页面上
				response.sendRedirect("http://localhost:8080/focus/html-wzh/UsersLogin.jsp");;
			}else{
				out.println("注册失败");
				//重定向到注册页面上
				response.sendRedirect("http://localhost:8080/focus/html-wzh/UsersRegister.jsp");
			}
		}else{
			out.println("登录失败");
		}
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
