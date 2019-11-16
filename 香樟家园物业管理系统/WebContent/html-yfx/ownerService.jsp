<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>业主服务</title>
		<link rel="icon" href="../images/logo.ico" type="images/x-ico" />
		<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css" /> 
		<!--字体图标库-->
		<link rel="stylesheet" href="../font-awesome/css/font-awesome.min.css">
		<!-- 导入easyui相关的js库文件(函数) -->
		<script type="text/javascript" src="../easyui/jquery.min.js"></script>
		<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script>
		<style type="text/css">
			/*清除元素默认的内边距和外边距*/
			*{                                
			  margin:0px;
			  padding:0px;
			}
			/*清除所有a标签的格式*/
			a{                                
			  text-decoration:none;
			  color:rgb(0,0,0);
			}
			ul li:hover{
				cursor: pointer;
			}
			header{
				height:30px; 
				background-color: rgba(0,0,0,.6);
			}
			.ul-left{
				list-style: none;
				margin-left : 120px;
				font-size: 12px;
			}
			#app-picture{	
				display: none;
				position: absolute;
				top: 30px;
				left:120px;
				z-index: 99;
			}
			#app:hover +#app-picture{
				display: block;
			}
			.ul-left li{
				float:left;
				margin:0px 5px;
			}
			.ul-left li a{
				color: rgb(255,255,255);
				line-height: 30px;
			}
			.ul-right{
				list-style: none;
				margin-right: 120px;
				float: right;
				font-size: 12px;
			}
			#owner{
        		font-weight:bold;
        		margin:0px 5px;
        	}
			.ul-right li{
				float: left;
				margin:0px 5px;
			}
			.ul-right li a{
				color: rgb(255,255,255);
				line-height: 30px;
			}
			.li-header:hover {
				color: rgb(0,151,233);
			}
			
			#nav-top{
				height: 100px; 
				background-color: aliceblue;
			}
			#nav-top img{
				margin :20px 0px 20px 120px;
			}
			#nav-top ul{
				list-style: none;
				float: right; 
				margin-right: 120px; 
				font-weight: bold;
			}
			#nav-top ul li{
				float: left;
				margin:40px 20px;
			}
			#nav-top li a:hover{
				color: rgb(0,151,233);
			}
			
			#nav-right{
				margin: 50px 120px;
				background-color: aliceblue;
				width: 240px;
				border-radius: 20px;
			}
			#nav-right li{
				list-style: none;
				line-height: 50px;
				height: 50px;
			}
			#nav-right li:hover{
				background-color:rgb(0,151,233);
				color: rgb(255,255,255);
			}
			#nav-right .fa{
				width: 50px;
				text-align: center;
				margin:0 30px ;
			}
			
			#tab{
				float: right;
				width: 830px;
				height: 400px;
				margin-top: -450px;
				margin-right: 110px;
			}
			
			footer{
        		font-size: 14px;
        		background: #dddddd;
        		width:100%;
        		height:90px;
        		margin-top: 20px;
        		text-align: center;
        		padding-top: 20px;
        	}
        	footer a:hover{
        		color:#669900;
        	}
        	
		</style>
		
	    <script type="text/javascript">
			/*选项卡*/
			$(function() {
			
				$(".opt").click(function() {
					var title = $(this).text();
					var url = $(this).attr("url");
					var isExt = $('#opt_tabs').tabs('exists', title);
					//判断页签是否已经存在 选项卡,如果不存在则新建选选项卡；存在则显示该选项卡
					if (!isExt) {
						$('#opt_tabs').tabs('add', {
						title : title,
						width : $("#opt_tabs").parent().width(),
						height : "auto",
						content : createContent(url),
						closable : true
					});
					}else{
						$('#opt_tabs').tabs('select', title);
						return;
					}					
				});
				
			});
			
			function createContent(url) {
				var strPath = '<iframe src="' + url + '" scrolling="no" frameborder="0" width="100%" height="100%"></iframe>';
				return strPath;
			}
		</script>
		<script type="text/javascript">	
	        $(function(){
	            var tabs = $('#opt_tabs').tabs().tabs('tabs');
	            for(var i=0; i<tabs.length; i++){
	                tabs[i].panel('options').tab.unbind().bind('mouseenter',{index:i},function(e){
	                    $('#opt_tabs').tabs('select', e.data.index);
	                });
	            }
	        });
		</script>
	    
	</head>
	<body>
		<header>			
			<ul class="ul-left">
				<li id="app"><a class="li-header"  >手机APP</a></li>
				<div id="app-picture"><img src="../images/app.jpg" /></div>
				
			</ul>
			<ul class="ul-right" >
				<li ><a href="" ><i class="fa fa-user-o" aria-hidden="true"></i> 尊敬的<span id="owner">${users.uName }</span>欢迎您回家！</a></li>
				<li ><a href="">|</a></li>
				<li ><a class="li-header" href="../html-wzh/UsersLogin.jsp" >退出登录</a></li>
			</ul>			
		</header>
		<div>
			<div id="nav-top" >
				<img src="../images/LOGO.png" title="logo" alt="logo"/>
				<ul >
					<li ><a href="../html-hxl/index.html">首页</a></li>
					<li ><a href="../html-wzh/update.jsp">修改个人信息</a></li>
					
				</ul>
			</div>
			<nav id="nav-right">
				<ul>
					<li class="opt"  url="Regulatory.html"><i class="fa fa-volume-down fa-2x" aria-hidden="true"></i>小区公告</li>
					<li class="opt"  url="HouseInformation.jsp"><i class="fa fa-home fa-2x" aria-hidden="true"></i>房产信息</li>
					<li class="opt"  url="parkingInformation.jsp"><i class="fa fa-car fa-2x" aria-hidden="true"></i>车位信息</li>
					<li class="opt"  url="showMoney.jsp"><i class="fa fa-jpy fa-2x" aria-hidden="true"></i>缴费提醒</li>
					<li class="opt"  url="feeList.html"><i class="fa fa-database fa-2x" aria-hidden="true"></i>缴费详情</li>					
					<li class="opt"  url="repairs.jsp"><i class="fa fa-wrench fa-2x" aria-hidden="true"></i>房屋报修</li>
					<li class="opt"  url="complain.jsp"><i class="fa fa-envelope fa-2x" aria-hidden="true"></i>投诉建议</li>
					<li class="opt"  url="Rates.html"><i class="fa fa-file-code-o fa-2x" aria-hidden="true"></i>收费标准</li>
				</ul>
			</nav>
		
			
	    	<!-- <div id = "tab">
			    <ul>
			        <li class="on">小区公告</li>
			        <li class="off">房产信息</li>
			        <li class="off">车位信息</li>
			        <li class="off">缴费提醒</li>
			        <li class="off">缴费详情</li>
			        <li class="off">房屋报修</li>
			        <li class="off">投诉建议</li>
			        <li class="off">收费标准</li>
			    </ul>
			    <div id="firstPage"   class ="show"><iframe src="Regulatory.html"></iframe></div>
			    <div id="secondPage"  class ="hide"><iframe src="HouseInformation.jsp"></iframe></div>
			    <div id="thridPage"  class ="hide"><iframe src="parkingInformation.jsp"></iframe></div>
			    <div id="fourthPage"  class ="hide"><iframe src="showMoney.jsp"></iframe></div>
			    <div id="fifthPage"  class ="hide"><iframe src="feeList.html"></iframe></div>
			    <div id="sixthPage"  class ="hide"><iframe src="repairs.jsp"></iframe></div>
			    <div id="seventhPage"  class ="hide"><iframe src="complain.jsp"></iframe></div>
			    <div id="eighthPage"  class ="hide"><iframe src="Rates.html"></iframe></div>
			    
			</div> -->
	    	<div id="tab" data-options="region:'center',title:'数据管理'" style="background-image:url('../images/focus_001.jpg');background-size: 100% 100%">
		    	<!--选项卡 -->
		    	<div class="easyui-tabs" id="opt_tabs" data-options="fit:true,border:false,pill:true,border:true" >
	    		
	    	</div>
	    </div>	
			
		</div>
		<footer>
			<p>
				<a href="#">首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">关于我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">公司新闻</a>
			</p>
			<p>&copy;2014 一度地产集团香樟物业管理有限公司长沙总公司 版权所有</p>
			<p>技术支持：<a href="#">shinelan</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">管理登录</a></p>
		</footer>
	</body>
</html>