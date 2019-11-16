<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>费用查询</title>
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
				url : "../ArrearageServlet?method=findByName",
				async : true,
				data : "json",
				dataType : "json",
				success : function(data) {					
					$("#name").text(data[0].ownerName);
					var wuyeMoney=data[0].arrearageMoney;
					var carMoney=data[1].carMoney;
					var money=(wuyeMoney+carMoney);
					if(money==0){
						$("#situation").text("当前费用已缴清，祝您生活愉快！");
					}else{
						 $.messager.show({
                             title:'温馨提示',
                             width:200,
                             height:100,
                             timeout:0,
                             msg:'您当前有欠费！！！'
                       });
						$("#situation").append("<h3>您当前欠物业费："+wuyeMoney+"元<br>停车费："+carMoney+"元<br>共计："+money+"元<br>请您及时缴纳，谢谢您的配合！</h3>")
						document.getElementById("tit").style="block";
						document.getElementById("tab").style="block";
					}
				}
			});
				
		});
	</script>
	<script type="text/javascript">
		$(function(){
			$(".btn").click(function(){
				$.ajax({
					type : "post",
					url : "../ArrearageServlet?method=pay",
					async : true,
					data : "json",
					dataType : "json" ,
					success : function(data) {					
						$.messager.show({
	                        title:'温馨提示',
	                        width:200,
	                        height:100,
	                        timeout:0,
	                        msg:'物业费缴纳成功！'
	                    });
						//刷新页面
						window.location.reload();
					}
				});
			});
			$(".btn").click(function(){
				$.ajax({
					type : "post",
					url : "../CarArrearageServlet?method=pay",
					async : true,
					data : "json",
					dataType :"json" ,
					success : function(data) {					
						$.messager.show({
	                        title:'温馨提示',
	                        width:200,
	                        height:100,
	                        timeout:0,
	                        msg:'停车费缴纳成功！'
	                    });
						//刷新页面
						window.location.reload();
					}
				});
			});
		}); 
	</script>

	<script type="text/javascript">
	    window.onload = function(){
	    var myTab = document.getElementById("tab");    
	    var myUl = myTab.getElementsByTagName("ul")[0];
	    var myLi = myUl.getElementsByTagName("li");    
	    var myDiv = myTab.getElementsByTagName("div"); 
	
	    for(var i = 0; i<myLi.length;i++){
	        myLi[i].index = i;
	        myLi[i].onclick = function(){
	            for(var j = 0; j < myLi.length; j++){
	                myLi[j].className="off";
	                myDiv[j].className = "hide";
	            }
	            this.className = "on";
	            myDiv[this.index].className = "show";
	        }
	      }
	    }
    </script>
	<style type="text/css">
       #tab{
	       width:270px; 
	       padding:5px;
	       height:150px;
	       margin:15px;
       }
       #tab ul{
	       list-style:none; 
	       display:;
	       height:30px;
	       border-bottom:2px #C88 solid;
       }
       #tab ul li{
	       cursor:pointer;
	       float:left;
	       list-style:none; 
	       margin:0px 10px; 
	       border:1px solid #BBB; 
		   border-bottom:2px solid #C88;	      
	       
       }
       #tab ul li.on{
	       border-top:2px solid Saddlebrown; 
	       border-bottom:2px solid #FFF;
       }
       .hide{
       	display:none;
       }
       img{
			width:80px;
			height:120px;
			
		}
		.tex{
			margin-left:30px;
		}
		#situation{
			lign-height:20px;
		}
    </style>
	</head>
	<body>
		<div class="tex" id="tit" style="display:none"><h2>尊敬的<span id="name"></span>业主：</h2></div>
		<div class="tex" id="situation"></div>
		<div id = "tab" style="display:none">
	        <ul>
		        <li class="off"><input class="btn" id="wechat" type="button" value="微 信 支付" ></li>
		        <li class="off"><input class="btn" id="zfb" type="button" value="支付宝支付" ></li>
	        </ul>
		    <div id="firstPage" class="hide">
		        <img alt="微信支付" src="../images/wechat.jpg">
		    </div>
		    <div id="secondPage" class="hide">
		        <img alt="支付宝" src="../images/zfb.jpg">    
		    </div>
		</div>
		
	</body>
</html>