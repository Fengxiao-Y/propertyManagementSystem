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
		//设置请求和响应的字符集及内容类型
		//设置请求的字符集
		request.setCharacterEncoding("utf-8");
		//设置响应的字符集和内容类型
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//创建输出流
		PrintWriter out=response.getWriter();
		//接收来自客户端的数据
		//后台账号
		String empName=request.getParameter("empName");
		//密码
		String backPwd=request.getParameter("backPwd");
		//创建后台用户实体对象
		Information information=new Information();
		//建立数据层连接对象
		InformationDao informationdao=new InformationDaoImpl();
		//调用数据层登录的方法
		information=informationdao.doLogin(empName, backPwd);
		HttpSession session=request.getSession();
		session.setAttribute("information", information);
		if(information!=null){
			out.print("登录成功!");
			
			response.sendRedirect("http://localhost:8080/focus/html-yfx/BackIndex.jsp");
			
			
		}else{
			out.print("用户名和密码不正确!");
			response.sendRedirect("BackLogin.jsp");
		}
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
