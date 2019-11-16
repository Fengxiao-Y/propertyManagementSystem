<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>香樟家园用户注册</title>
<link rel="icon" href="../images/logo.ico" type="images/x-ico" />
<link rel="stylesheet" type="text/css" href="../css/public.css" />
<link rel="stylesheet" type="text/css" href="../css/Login.css" />
<script src="../js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/cart.js" type="text/javascript" charset="utf-8"></script>
</head>
<script type="text/javascript">
function isTruePhone(str){
    var reg = /^1[34578]\d{9}$/;
    if(!reg.test(str)){
     return false;
    }
    return true;

}

var xmlhttp;
function validateName() {
	var uIphone = document.getElementById('uIphone').value;
	 var span = document.getElementById("uIphoneTip");
	 if(isTruePhone(uIphone)){
		 xmlhttp = new XMLHttpRequest();
		 xmlhttp.onreadystatechange = judgeName;
			//POST请求方式的代码
			xmlhttp.open("POST", "../Checkservlet", true);
			//POST方式需要自己设置请求头部
			xmlhttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			//POST方式数据的发送：
			xmlhttp.send("uIphone=" + uIphone);
			xmlhttp.send(null);
			function judgeName() {
				if (xmlhttp.readyState == 4) {
					if (xmlhttp.status == 200) {
						var text = xmlhttp.responseText;
						var span = document.getElementById("uIphoneTip");
						span.innerHTML =text;
					} else {
						alert("出错了！");
					}
				}
			}
	 }else{
		 var span = document.getElementById("uIphoneTip");
		 span.innerHTML ="号码格式不对";
	 }
	
	
}

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

//获取密码错误提示的span标签
var pwdErrorSpan=document.getElementById("pwdError");
//获取确认密码框对象
var userpwd2Elt=document.getElementById("uPassword2");
//绑定blur事件
userpwd2Elt.onblur=function(){
	//获取密码和确认密码
	var userpwdElt=document.getElementById("uPassword1");
	var userpwd=userpwdElt.value;
	var userpwd2=userpwd2Elt.value;
	if(userpwd!=userpwd2){
		//密码不一致
		pwdErrorSpan.innerText="密码不一致";
	}else{
		//密码一致
	}
}
//绑定foucs事件
userpwd2Elt.onfocus=function(){
	if(pwdErrorSpan.innerText!=""){
		userpwd2Elt.value="";
	}
	pwdErrorSpan.innerText="";
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
	if(usernameErrorSpan.innerText=="" && emailSpan.innerText==""){
		
		//获取表单对象
		var userformElt=document.getElementById("userForm");
		//提交表单
		userformElt.submit();
		
	}

}
}
	
</script>
</head>
<body>

	<!-------------------reg-------------------------->
	<div class="reg">
		<form action="../RegisterUsersServlet" method="post" id="userForm">
			
			<h1>
				<a href="../html-hxl/index.html"><img src="../images/NEWLOGO.jpg"></a>
			</h1>
			<h2>用户注册</h2>
			<table>
				
				<tr>
					<td>注&nbsp;册&nbsp;号&nbsp;码&nbsp;：</td>
					<td><input type="text" name="uIphone" id="uIphone"
						placeholder="请输入电话" onchange="validateName()" required /></td>
				</tr>
				<tr>
				<td><span id="uIphoneTip" style="color:red;"></span></td>	
				</tr>
				<tr>
					<td>业&nbsp;主&nbsp;名&nbsp;：</td>
					<td><input type="text" name="uName" placeholder="请输入用户名" required=""  id="uName" onsubmit="return checkUname();"/></td>
					
				<tr>
				<tr>
				<td><span id="usernameError" style="color:red;"></span></td>	
				</tr>
					<td>密&nbsp;码&nbsp;：</td>
					<td><input type="password" name="uPassword1" id="uPassword1"
						placeholder="请输入密码" required=""/></td>
				</tr>
			
				<tr>
					<td>确&nbsp;认&nbsp;密&nbsp;码&nbsp;：</td>
					<td>
					<input type="password" name="uPassword2" id="uPassword2"
						placeholder="请确认密码"  required="" />
						
						</td>
				</tr>
				<tr>
				<td><span id="pwdError" style="color:red;"></span></td>	
				</tr>
				
				<tr>
					<td>注&nbsp;册&nbsp;邮&nbsp;箱：</td>
					<td><input type="text" name="uEmail" placeholder="请输入注册邮箱" id="uEmail"
						required></td>
				</tr>
				<tr>
				<td><span id="emailError" style="color:red;"></span></td>	
				</tr>
				
				<tr>
				
					<td colspan=2 id="reg_td"><input type="submit" id="submitBtn" value="注册" >
					</td>
				</tr>
			

				
				<tr class="txtL txt">
					<td colspan=2>完成此注册，即表明您同意了我们的<a href="#"><span style="color:red;">&lt;使用条款和隐私策略&gt;</span></a>
					</td>
				</tr>
				
				
				<tr>
					<td colspan=2><a href="UsersLogin.jsp"><span></span>已有账号登录</a></td>
				</tr>
				
			</table>
		</form>


	</div>

</body>
</html>