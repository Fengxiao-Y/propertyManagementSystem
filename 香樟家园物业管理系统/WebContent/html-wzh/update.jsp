<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户信息</title>
<link rel="icon" href="../images/logo.ico" type="images/x-ico" />
<link rel="stylesheet" type="text/css" href="../css/public.css" />
<link rel="stylesheet" type="text/css" href="../css/Login.css" />
<script src="../js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/cart.js" type="text/javascript" charset="utf-8"></script>
</head>
<script type="text/javascript"> 
			window.onload=function(){
					//获取username的span
					var usernameErrorSpan=document.getElementById("usernameError");
					//给用户名文本框绑定blur事件
					var usernameElt=document.getElementById("uName");
					usernameElt.onblur=function(){
					//获取用户名
					var username=usernameElt.value;
					//取出前后空白
					username=username.trim();	
				
					if(username===""){
						//用户名为空
						usernameErrorSpan.innerText="用户名不能为空";
					}
				//给username文本框获得焦点事件
				usernameElt.onfocus=function(){	
					//清空非法的value
					if(usernameErrorSpan.innerText!=""){
						usernameElt.value="";
					}	
					//清空span
					usernameErrorSpan.innerText="";
				}	
				
				
				
				//获取email的span
				var emailSpan=document.getElementById("emailError");
				//给email绑定blur事件
				var emailElt=document.getElementById("uEmail");
				
				//给email绑定blur事件
				emailElt.onblur=function(){
					//获取email
					var email=emailElt.value;
					//编写email的正则
					var emailRegExp=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
					var ok=emailRegExp.test(email);
					if(ok){
						//正确	
					}else{
						//不正确
						emailSpan.innerText="请填写正确邮箱";
					}
				}
				//给emailElt绑定focus
				emailElt.onfocus=function(){
					if(emailSpan.innerText!=""){
						emailElt.value="";
					}
					emailSpan.innerText="";
					
				}
				
				//获取phone的span
				var phoneErrorSpan=document.getElementById("phoneError");
				//给手机号码绑定blur事件
				var phoneElt=document.getElementById("uIphone");
				phoneElt.onblur=function(){
					//获取电话号码
					var phone=phoneElt.value;
					//编写电话号码正则
					var phoneRegExp=/^[1][3,4,5,7,8][0-9]{9}$/;
					var ok=phoneRegExp.test(phone);
					if(ok){
						//正确
					}else{
						//不正确
						phoneErrorSpan.innerText="请填写正确的手机号码";
					}
				}	
					//给phoneElt绑定focus
					phoneElt.onfocus=function(){
						if(phoneErrorSpan.innerText!=""){
							phoneElt.value="";
						}
						phoneErrorSpan.innerText="";
					}

				
				//给提交按钮绑定鼠标事件
				var submitBtnElt=document.getElementById("submitBtn");
				submitBtn.onclick=function(){
					//触发username和userpwd2及email的blur
					//不需要人工操作，使用js代码触发事件
					usernameElt.focus();
					usernameElt.blur();
					
					userpwd2Elt.focus();
					userpwd2Elt.blur();

					emailElt.focus();
					emailElt.blur();
					//当所有表单项都合法的时候，提交表单
					if(usernameErrorSpan.innerText=="" && phoneErrorSpan.innerText==""
						&& emailSpan.innerText==""){
						//获取表单对象
						var userformElt=document.getElementById("userForm");
						//提交表单
						userformElt.submit();
						
		
		}
			
		</script>

</head>
<body>

	<!-------------------reg-------------------------->
	
	<div class="reg">
		<form action="../UpdateUsersServlet" method="post">
			
			<h1>
				<a href="../html-hxl/index.html"><img src="../images/NEWLOGO.jpg"></a>
			</h1>
			<h2>修改个人信息</h2>
			<table>
				<tr>
				<td></td>
				<td><input type="hidden" name="uId" value="${users.uId}"></td>
				</tr>
				
				<tr>
					<td>注&nbsp;册&nbsp;号&nbsp;码&nbsp;：</td>
					<td><input type="text" name="uIphone" id="uIphone"
						 value="${users.uIphone}"></td>
				</tr>
				<tr>
				<td><span id="phoneError" style="color:red;"></span></td>	
				</tr>
				<tr>
					<td>业&nbsp;主&nbsp;名&nbsp;：</td>
					<td><input readonly="readonly" type="text" name="uName"  id="uName" value="${users.uName}"/></td>
					
				<tr>
				<tr>
				<td><span id="usernameError" style="color:red;"></span></td>	
				</tr>
				 <tr>
					<td>密&nbsp;码&nbsp;：</td>
					<td><input type="password" name="uPassword" id="uPassword1"
						 value="${users.uPassword}"/></td>
				</tr>
			
				<tr>
					<td>注&nbsp;册&nbsp;邮&nbsp;箱：</td>
					<td><input type="text" name="uEmail"  id="uEmail"
						value="${users.uEmail}"></td>
				</tr>
				<tr>
				<td><span id="emailError" style="color:red;"></span></td>	
				</tr>
				
				<tr>
				
					<td colspan=2 id="reg_td"><input type="submit" id="submitBtn" value="修改" >
					</td>
				</tr>
				
			</table>
		</form>


	</div>

</body>
</html>