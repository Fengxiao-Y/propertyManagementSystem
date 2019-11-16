<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >

<html>
<head lang="en">
<meta charset="utf-8" />
<title>香樟家园</title>
<link rel="icon" href="../images/logo.ico" type="images/x-ico" />
<link rel="stylesheet" type="text/css" href="../css/public.css" />
<link rel="stylesheet" type="text/css" href="../css/proList.css" />
<link rel="stylesheet" type="text/css" href="../css/forget.css" />
<script src="../js/jquery-1.12.4.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/pro.js" type="text/javascript" charset="utf-8"></script>
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
			xmlhttp.open("POST", "../CheckServlet2", true);
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
		 span.innerHTML ="号码格式不对";
	 }
	
	
}

	

		//验证验证码
		function checkYzm(){
			var yanz=document.getElementById("yz").value;
			if(yaz==""){
				var yamSpan=document.getElementById("yzm");
				yamSpan.innerHTML="验证码不能为空";
				return false;
				
			}
		}
		
		//验证密码
		function checkPwd1(){
			
			/*校验密码*/
			var pwd1=document.getElementById("uPassword1");
			
			
		 	
			if(rpValue.value.length<6 || rpValue.length>16){
				var pwdSpan=document.getElementById("errorpwd");
				pwdSpan.innerHTML="输入的密码必须要在6到16位数之间";
				return false;
			}
		
		}
		function checkPwd2(){
			/*校验密码*/
			var pValue=document.getElementById("uPassword1").value;
			/**校验确认密码*/
			var rpValue=document.getElementById("uPassword2").value; 
			if(rpValue!=pValue){
				 var pwdSpan2=document.getElementById("pwdTip");
				pwdSpan2.innerHTML="两次密码不一致";
				return false;
				 
			}
		}
			
			

		

	</script>
</head>
<body>
	<!----------------------------------------order------------------>
	<div class="order cart">
		<!-----------------logo------------------->
		<div class="logo">
			<h1 class="wrapper clearfix">
				<a href="../html-hxl/index.jsp"><img class="fl"
					src="../images/NEWLOGO.jpg"></a>
			</h1>
		</div>
		<div class="forCon">
			<h1>安全设置-找回密码</h1>
			<ul>
				<li class="on"><span>01/</span>输入登录名</li>
				<li><span>02/</span>重置密码</li>
			</ul>

			<div class="formCon">
				<!--步骤1-->
				<form action="../ForgetServlet" method="post" class="one">
					<table>
						<tr>

							<td><label>电&nbsp;话&nbsp;号&nbsp;码:</label></td>
							<td><input type="text" id="uIphone" name="uIphone"  required=""
								placeholder="电话号码" onchange="validateName()"></td>
							<td><span id="uIphoneTip" style="color:red;font-size:12px"></span></td>

						</tr>
						<tr>
							<td><label>验&nbsp;证&nbsp;码:</label></td>
							<td><input type="text" name="yz" id="yz" placeholder="验证码"  required=""
								onblur="return checkYz()" /> <span id="yzm"  style="color:red;font-size:12px"></span>
							</td>
						</tr>
						<tr>
							<td clospan=2><input type="button" value="下一步" class="next">
							</td>
						</tr>
					</table>
				</form>

				<!--步骤2-->
				<form action="../ForgetServlet" method="post" class="two">
					<table>
						<tr>
							<td><label>新&nbsp;密&nbsp;码&nbsp;：</label></td>
							<td><input type="password" name="uPassword1" id="uPassword1"
								placeholder="请输入新密码" required="" onblur="return checkPwd1();"></td>
							<td><span id="errorpwd" style="color:red;font-size:12px"></span></td>
						</tr>
						<tr>
							<td><label>确&nbsp;认&nbsp;密&nbsp;码&nbsp;：</label></td>
							<td><input type="password" name="uPassword2" id="uPassword2"
								placeholder="请确认密码" required="" onblur="return checkPwd2();" /></td>
							<td><span id="pwdTip" style="color:red;font-size:12px"></span></td>
						</tr>
						<tr>
							<td colspan=2><input type="submit" value="重置密码" class="next"></td>
						</tr>
					</table>
				</form>

			</div>
		</div>
	</div>


</body>
</html>
