<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>车位信息</title>
		<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css" />
		<!-- 导入easyui相关的js库文件(函数) -->
		<script type="text/javascript" src="../easyui/jquery.min.js"></script>
		<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script>
		
		<script type="text/javascript">
		$(function(){
			$.ajax({
				type : "post",
				url : "../ParkingInformationServlet?method=findByName",
				async : true,
				data : "json",
				dataType : "json",
				success : function(park) {					
					$("#name").text(park[0].ownerName);
					$("#parkId").text(park[1].parkId);
					$("#parkStatus").text(park[1].parkStatus);
				
				}
			});
				
		});
	</script>
	</head>
	<body style="width:99%;height:100%">
		<h2 style="lign-height:100px;margin-left:30px">尊敬的<span id="name"></span>业主，您的车位信息：</h2>
		
		<h3 style="lign-height:70px;margin-left:30px">车位编号:<span id="parkId"></span></h3>
		<h3 style="lign-height:70px;margin-left:30px">车位状态:<span id="parkStatus"></span></h3>
			
	</body>
</html>