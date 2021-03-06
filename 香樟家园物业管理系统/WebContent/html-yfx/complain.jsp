<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>投诉</title>
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
				url : "../ComplainServlet?method=findByName",
				async : true,
				data : "json",
				dataType : "json",				
				success : function(data) {					
					for(i in data){
						$("#name").text(data[i].ownerName);
						$("#compcontent").text(data[i].comText);
						var statue=data[i].comResult;						
						if(statue=="已处理"){
							$("#result").text("您的投诉已处理完成，祝您生活愉快！");
						}else if(statue=="处理中"){
							$("#result").text("您的投诉持续跟进中，请您耐心等待！")
						}else if(statue=="未处理"){
							$("#result").text("已受理您的投诉，请您耐心等待！")
						}else if(statue=="无效投诉"){
							$("#result").text("很抱歉，您的投诉属于无效投诉！")
						}
					}	
				}
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
	     *{
		     padding:0px; 
		     margin:0px;
	     }
	       #tab{
		       width:95%; 
		       padding:5px;
		       height:90%;
		       margin:10px;
	       }
	       #tab ul{
		       list-style:none; 
		       display:;
		       height:30px;
		       line-height:30px; 
		       border-bottom:2px #C88 solid;
		       
	       }
	       #tab ul li{
		       background:#FFF;
		       cursor:pointer;
		       float:left;
		       list-style:none; 
		       height:29px; 
		       line-height:29px;
		       padding:0px 10px; 
		       margin:0px 10px;  
		       border:1px solid #BBB; 
		       border-bottom:2px solid #C88;
		       
	       }
	       #tab ul li.on{
		       border-top:2px solid Saddlebrown; 
		       border-bottom:2px solid #FFF;
	       }
	       #tab div{
		       height:300px;
		       width:100%; 
		       line-height:24px; 
		       padding:1px; 
		       border-top:none;   
		       border:1px solid #336699;
		      
	       }
	       .hide{
	       	   display:none;
	       }
	</style>
	<script type="text/javascript">
		$(function(){
			$("#tijiao").click(function(){
				$.ajax({	
					type : "post",
					url : "../ComplainServlet?method=tousu",
					async : true,
					data : $("#form").serialize(),
					dataType : "json" ,
					success : function(data) {					
						$.messager.show({
	                        title:'温馨提示',
	                        width:200,
	                        height:100,
	                        timeout:0,
	                        msg:'投诉成功！'
	                    });
						//刷新页面
						window.location.reload();
					}
				});
			});	
		});
	</script>
	</head>
	<body>
		<div id = "tab">
	        <ul>
		        <li class="off"><input id="sbmt" type="button"  value="我要投诉"></li>
		        <li class="off"><input id="fdmg" type="button" value="我的投诉"></li>
		       
	        </ul>
		    <div id="firstPage" class="hide">
		        <form id="form" action="">
					投诉内容(限100个汉字)：<br><input name="comText" type="text" style="width:99%;height: 200px;" />
					<input id="tijiao" type="submit" value="提交" style="margin:10px">
					<input type="reset" value="取消" style="margin:10px"> 
				</form>    
		    </div>
		    <div id="secondPage" class="hide">
		        <h2 style="line-height:90px ;margin-left:30px;">尊敬的<span id="name"></span>业主：</h2>
				<h3 style="line-height:50px ;margin-left:30px;">投诉内容：<span id="compcontent"></span></h3>
				<h3 style="line-height:50px ;margin-left:30px;">状态：<span id="result"></span></h3>   
		    </div>
		</div>
			
	</body>
</html>