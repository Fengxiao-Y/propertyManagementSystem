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
		//设置请求和响应的字符集及内容类型
				//设置请求的字符集
				request.setCharacterEncoding("utf-8");
				//设置响应的字符集和内容类型
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				//创建输出流
				PrintWriter out=response.getWriter();

				//接收来自客户端的数据
				//业主编号
				int uId=Integer.parseInt(request.getParameter("uId"));
				
				//业主姓名
				String uName=request.getParameter("uName");
				//密码
				String uPassword=request.getParameter("uPassword");
				//业主邮箱
				String uEmail=request.getParameter("uEmail");
				//业主号码
				String uIphone=request.getParameter("uIphone");
				
				//数据封装成对象
				Users users=new Users(uId, uName, uPassword, uEmail, uIphone);
					
				//调用数据层对象执行添加方法
				UsersDao userdao=new UsersDaoImpl();
				int rows=userdao.update(users);
				
				//判断添加是否成功
				if(rows>0){
					//response.sendRedirect("http://localhost:8080/focus/html-yfx/ownerService.jsp");
					//request.getRequestDispatcher("/html-yfx/ownerService.jsp").forward(request, response);
				

				}else{
					//out.print("修改失败");
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
