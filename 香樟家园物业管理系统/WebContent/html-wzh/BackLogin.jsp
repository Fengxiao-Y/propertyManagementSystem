<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>香樟家园管理登陆</title>
		<link rel="icon" href="../images/logo.ico" type="images/x-ico" />
		<link rel="stylesheet" href="../css/style.css">
		<script type="text/javascript" src="../js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>

		<style>
body {
	background-image: url('../images/index.jpg');
	background-position:none;
	background-size: 1920px 1230px;
}

div {
	filter: alpha(Opacity = 80);
	-moz-opacity: 0.5;
	opacity: 0.9;
}
</style>
	</head>
	<body style="background-color:#ddddff">
		<main>
		<form class="form"  action="../LoginBackServlet" method="post" >
			<div class="form__cover"></div>
			<div class="form__loader">
				<div class="spinner active">
					<svg class="spinner__circular" viewBox="25 25 50 50">
					<circle class="spinner__path" cx="50" cy="50" r="20" fill="none"
						stroke-width="4" stroke-miterlimit="10"></circle>
					</svg>
				</div>
			</div>
			<div class="form__content">
				<h1>
					香樟家园管理登陆
				</h1>
				<div class="styled-input">
					<input type="text" class="styled-input__input"
						data-options="required:true" name="empName">
					<div class="styled-input__placeholder">
						<span class="styled-input__placeholder-text">用户名：</span>
					</div>
					<div class="styled-input__circle"></div>
				</div>
				<div class="styled-input">
					<input name="backPwd" type="password" data-options="required:true" class="styled-input__input">
					<div class="styled-input__placeholder">
						<span class="styled-input__placeholder-text">密码：</span>
					</div>
					<div class="styled-input__circle"></div>
				</div>
				<button type="submit" class="styled-button">
					<span class="styled-button__real-text-holder"> <span
						class="styled-button__real-text">登陆</span> <span
						class="styled-button__moving-block face"> <span
							class="styled-button__text-holder"> <span
								class="styled-button__text">登陆</span> </span> </span> <span
						class="styled-button__moving-block back"> <span
							class="styled-button__text-holder"> <span
								class="styled-button__text">登陆</span> </span> </span> </span>
				</button>
				<br />
			</div>
		</form>
		</main>
		<script src="../js/index.js"></script>
	</body>
</html>