<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--以前缀是spring开头的文件由uri后面的东西解析--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login - XLFD Admin</title>
<!-- Main CSS-->
<link rel="stylesheet" type="text/css" href="<%=basePath %>styles/main.css">
<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="<%=basePath %>javascript/jquery.min.js"></script>
<script src="<%=basePath %>javascript/bootstrap.min.js"></script>
<script src="<%=basePath %>javascript/main.js"></script>
<script src="<%=basePath %>javascript/plugins/pace.min.js"></script>

<script type="text/javascript">
    $(window).ready(function() {
	   		$("#bossPwd1").blur(function(){
				//调用
				
				ajaxClient();
			});
			//请求客户信息
			$("#bossPwd1").keydown(function(e) {//给输入框绑定按键事件
	            var e = e ||event;
	            var currKey = 0;
	            currKey=e.keyCode||e.which||e.charCode;
				if (currKey == "13") {
					//调用
					ajaxClient();
				}
			});
			function ajaxClient(){
				var bossPwd1= $("#bossPwd1").val();				
				$.ajax({
					url : "<c:url value='testboss'/>",
					type : "POST",
					dataType : 'json',
					data : {
						"bossPwd1" : bossPwd1
					},
					success : function(data) {
						if(data.result =="success"){
		      				alert("success");
		      			}else{
		      				$("#bossPwd1").val();
		      				alert("密码错误");
		      			}
	      				/* window.location.href='<c:url value="/boss/login" />'; */
	      	    	},
				});
		}
		$("#btnLogin").click(function(){
    	
		var bossPwd = $('#bossPwd2').val();
		var bossPwd1 = $('#bossPwd3').val();
		if(bossPwd == bossPwd1){
			 $.ajax({
	      		url : "<c:url value='updateboss'/>",
	      		type:"POST",
	      		dataType:"json",
	      		data:{"bossPwd":bossPwd},
	      		success: function(data){
	      			if(data.result =="success"){
	      				 alert("success");
	      				 window.location.href='<c:url value="/boss/login" />'; 
	      				 
	      			}
	      	    },
	      	 
	       
	      	});
		
		}else{
			alert("密码错误");
			window.location.reload;
		}
		
	 });
});
    </script>
</head>
<body>
	<section class="material-half-bg">
		<div class="cover"></div>
	</section>
	<section class="login-content">
		<div class="logo">
			<h1>XLFD</h1>
		</div>
		<div class="login-box" style="min-height: 435px;">
			<div class="login-form" style="padding:10px 40px 60px 40px;">
				<h3 class="login-head" style="padding-bottom:2px;">
					<img src="<%=basePath%>images/login.jpg" class="img-responsive" style="width:30%;" />
				</h3>
				<div class="form-group">
					<label class="control-label">当前密码</label> <input class="form-control" type="password"
						value="" id="bossPwd1" placeholder="请输入当前密码">
				</div>
				<div class="form-group">
					<label class="control-label">修改密码</label> <input class="form-control" autocomplete="off" type="password"
						name="bossPwd" id="bossPwd2" placeholder="请输入修改密码">
				</div>
				<div class="form-group">
					<label class="control-label">确认密码</label> <input class="form-control" type="password" autocomplete="off"
						id="bossPwd3" placeholder="请再次输入修改密码">
				</div>
				<div class="form-group btn-container">
					<button class="btn btn-primary btn-block" id="btnLogin">
						<i class="fa fa-sign-in fa-lg fa-fw"></i>确认修改
					</button>
				</div>
			</div>
		</div>
	</section>

</body>
</html>