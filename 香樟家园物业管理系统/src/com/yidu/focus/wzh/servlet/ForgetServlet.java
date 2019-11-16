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
				//设置请求和响应的字符集及内容类型
				//设置请求的字符集
				request.setCharacterEncoding("utf-8");
				//设置响应的字符集和内容类型
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				
				//创建输出流对象
				PrintWriter out=response.getWriter();
				HttpSession session=request.getSession();
				//接收注册号码
				Users users=(Users)session.getAttribute("users");
				String uIphone=users.getuIphone();
				
		
				//密码
				String uPassword=request.getParameter("uPassword1");
				//重新输入密码
				String uPassword2=request.getParameter("uPassword2");
				
				
				
				//建立数据层连接对象
				UsersDao usersdao=new UsersDaoImpl();
				int uId=users.getuId();
				//取出原来的用户名
				String uName=users.getuName();
				
				//取出原来的邮箱
				String uEmail=users.getuEmail();
					//如果两次输入的密码一致,更新密码
					if(uPassword.equals(uPassword2)&&!uPassword.equals("")){
						//把修改好的密码封装在实体类中
						users=new Users(uId,uName, uPassword, uEmail, uIphone);
						//调用修改的方法
						int rows=usersdao.update(users);
						
						if(rows>0){
							out.print("修改成功");
							response.sendRedirect("http://localhost:8080/focus/html-wzh/UsersLogin.jsp");
						}else{
							out.print("修改失败");
							response.sendRedirect("http://localhost:8080/focus/html-wzh/Forget.jsp");
						}
					
					}
					out.close();
				}
				
				
				



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
