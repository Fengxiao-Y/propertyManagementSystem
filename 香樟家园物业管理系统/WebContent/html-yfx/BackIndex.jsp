<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>香樟家园物业管理系统后台</title>
		<link rel="icon" href="../images/logo.ico" type="images/x-ico" />
		<!-- 导入easyui样式 -->
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
			/*清除无序列表的格式*/
			ul{                               
			  list-style:none; 
			}
			ul li{
				padding: 10px 30px; 
				font-size: 14px;
			}
			ul li:hover{
				background-color: lightskyblue;
				cursor: pointer;
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
	        function getTime(){
		        var time = new Date();
		        $("#times").html(time.toLocaleString());
	        }
	        $(function(){
	        	setInterval("getTime()",1000);
	        }); 
	    </script>
	</head>
	<body style="width: 100%; height: 100%;" class="easyui-layout">  
	    <div data-options="region:'north'" style="height:84px; background-color:skyblue;">
	    	<div style="width:auto;height:80px;float: left; padding-left: 20px;">
	    		<img  src="../images/XZlogo.png" alt="logo"  title="logo" style="height: 60px;width: 80px;margin-top: 10px;"/>
	    	</div>
	    	<div style="width:auto;height:80px;float: left;font-size: 20px;font-weight:bold ; line-height:40px ;margin-left:10px;">
	    		<span>香樟家园</span><br><span style="font-size:18px;">物业管理系统</span><span style="lign-height:10px;font-size:12px;font-weight:normal;">&nbsp;&nbsp;版本V1.0</span>
	    	</div>
	    	
	    	<div style="width:400px;height:70px;float: right;line-height:70px ;">欢迎<span style="font-weight:bold;margin:0px 5px;">${information.empName }</span>登录系统！
	    		<span id="times"></span>&nbsp;&nbsp;
	    		|&nbsp;&nbsp;
	    		<span><a href="../html-wzh/BackLogin.jsp">退出</a></span>
	    	</div>	
	    </div>
	    <div data-options="region:'west',title:'后台管理'" style="width:200px;">
	    	<!--下拉菜单-->
	    	<div id="acc" class="easyui-accordion" style="width:100%;height:auto;">   
				<!-- <div title="系统管理" style="overflow: auto;" data-options="iconCls:'icon-save'">
					<ul>
						<li class="opt" url=""><i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;登录日志</li>
					</ul>
				</div> -->
				<div title="行政管理" data-options="selected:false,iconCls:'icon-large-shapes'">
					<ul>
						<li class="opt" url="../html-ll/Dept.html"><i class="fa fa-handshake-o" aria-hidden="true"></i>&nbsp;行政部门</li>
						<li class="opt" url="../html-ll/Emp.html"><i class="fa fa-users" aria-hidden="true"></i>&nbsp;员工信息</li>
						<li class="opt" url="../html-ll/Rules.html"><i class="fa fa-file-text-o" aria-hidden="true"></i>&nbsp;规章制度</li>
						<li class="opt" url="../html-hxl/Regulatory.html"><i class="fa fa-file-text-o" aria-hidden="true"></i>&nbsp;公告信息</li>
					</ul>
				</div>
				<div title="基础信息管理" data-options="selected:true,iconCls:'icon-large-shapes'">
					<ul>
						<li class="opt" url="../html-hj/HouseInformationCrud.html"><i class="fa fa-building-o" aria-hidden="true"></i>&nbsp;房屋信息</li>
						<li class="opt" url="../html-hj/OwnerInformationCrud.html"><i class="fa fa-user-circle" aria-hidden="true"></i>&nbsp;业主信息</li>
						<li class="opt" url="../html-hj/LesseeInformationCrud.html"><i class="fa fa-user-circle-o" aria-hidden="true"></i>&nbsp;租户信息</li>
					</ul>
				</div>
				<div title="日常管理" data-options="selected:false,iconCls:'icon-large-shapes'">
					<ul>
						<li class="opt" url="../html-hxl/VisitorRegister.html"><i class="fa fa-file-text-o" aria-hidden="true"></i>&nbsp;来访登记</li>
						<li class="opt" url="../html-yfx/ComplainCrud.html"><i class="fa fa-american-sign-language-interpreting" aria-hidden="true"></i>&nbsp;业主投诉</li>
						<li class="opt" url="../html-yfx/RepairsCrud.html"><i class="fa fa-wrench" aria-hidden="true"></i>&nbsp;业主报修</li>
					</ul>
				</div>
				<div title="工程管理" data-options="selected:false,iconCls:'icon-large-shapes'">
					<ul> 
						<li class="opt" url="../html-hxl/EquipmentInformation.html"><i class="fa fa-wrench" aria-hidden="true"></i>&nbsp;设备基本信息</li>
						<li class="opt" url="../html-hxl/EqCheck.html"><i class="fa fa-wrench" aria-hidden="true"></i>&nbsp;设备设施巡查</li>
						<li class="opt" url="../html-hxl/EqMaintain.html"><i class="fa fa-wrench" aria-hidden="true"></i>&nbsp;设备维修维护</li>
						
					</ul>
				</div>
				<div title="物料管理" data-options="selected:false,iconCls:'icon-large-shapes'">
					<ul>
						<li class="opt" url="../html-wzh/StockCrud.html"><i class="fa fa-th-large" aria-hidden="true"></i>&nbsp;物料库存</li>
						<li class="opt" url="../html-wzh/ProcurementCrud.html"><i class="fa fa-shopping-basket" aria-hidden="true"></i>&nbsp;物料采购</li>
						<li class="opt" url="../html-wzh/GetGoodsCrud.html"><i class="fa fa-file-text-o" aria-hidden="true"></i>&nbsp;物料领用</li>
					</ul>
				</div>
				<div title="停车管理" data-options="selected:false,iconCls:'icon-more'">
					<ul>
						<li class="opt" url="../html-ll/ParkingInformation.html"><i class="fa fa-car" aria-hidden="true"></i>&nbsp;车位信息</li>
						<li class="opt" url="../html-hxl/CarInformation.html"><i class="fa fa-car" aria-hidden="true"></i>&nbsp;车辆信息</li>
						<li class="opt" url="../html-ll/CarTime.html"><i class="fa fa-jpy" aria-hidden="true"></i>&nbsp;临时停车</li>
					</ul>
				</div>
				<div title="财务管理" data-options="selected:false,iconCls:'icon-large-chart'">
					<ul>
						<li class="opt" url="../html-hj/RatesCrud.html"><i class="fa fa-jpy" aria-hidden="true"></i>&nbsp;收费标准</li>
						<li class="opt" url="../html-hj/RecordCrud.html"><i class="fa fa-jpy" aria-hidden="true"></i>&nbsp;缴费明细</li>						
						<li class="opt" url="../html-hj/ArrearageCrud.html"><i class="fa fa-jpy" aria-hidden="true"></i>&nbsp;物业费明细</li>
						<li class="opt" url="../html-hj/CarArrearageCrud.html"><i class="fa fa-jpy" aria-hidden="true"></i>&nbsp;固定停车费明细</li>
						<li class="opt" url="../html-hj/IncomeCrud.html"><i class="fa fa-jpy" aria-hidden="true"></i>&nbsp;收入报表</li>
						<li class="opt" url="../html-hj/IncomeTotalCrud.html"><i class="fa fa-jpy" aria-hidden="true"></i>&nbsp;收入汇总</li>
						<li class="opt" url="../html-hj/ExpenseCrud.html"><i class="fa fa-jpy" aria-hidden="true"></i>&nbsp;支出报表</li>
						<li class="opt" url="../html-hj/ExpenseTotalCrud.html"><i class="fa fa-jpy" aria-hidden="true"></i>&nbsp;支出汇总</li>
					</ul>
				</div>
			</div> 
	    </div>   
	    <div data-options="region:'center',title:'数据管理'" style="background-image: url('../images/tabsbg.jpg'); background-size: 100% 100%">
	    	<!--选项卡 -->
	    	<div class="easyui-tabs" id="opt_tabs" data-options="fit:true,border:false" >
	    		
	    	</div>
	    </div>
	    <div data-options="region:'south'" style="height:50px;text-align: center;line-height: 45px;">&copy;2014 一度地产集团香樟物业管理有限公司长沙总公司 版权所有</div>
	</body>  
</html>