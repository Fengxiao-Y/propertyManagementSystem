<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta charset="UTF-8">
		<title>香樟家园物业</title>
		<link rel="icon" href="../images/logo.ico" type="images/x-ico" />
		<link rel="stylesheet" type="text/css" href="../css/public.css"/>
		<link rel="stylesheet" type="text/css" href="../css/Login.css"/>
	</head>
	<body style="background-color:#ddddff">
		<!-------------------login-------------------------->
		<div class="login">
			<form action="../LoginUsersServlet" method="post">
				<h1><a href="../html-hxl/index.html"><img src="../images/top.jpg"></a>香樟物业</h1>
				<div class="msg-warn hide">公共场所不建议自动登录，以防账号丢失</div>
				<p>用&nbsp;&nbsp;户&nbsp;&nbsp;名:&nbsp;&nbsp;<input  class="shurukuang" type="text" name="uName"  placeholder="请输入用户名" required/></p>
				<p>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;<input type="password" name="uPassword" placeholder="请输入密码" required/></p>
				<p><input type="submit" value="登  录"/></p>
				<p class="txt"><a href="UsersRegister.jsp">免费注册</a><a href="Forget.jsp">忘记密码？</a></p>
			</form>
		</div>
	</body>
</html>