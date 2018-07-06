<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--以前缀是spring开头的文件由uri后面的东西解析--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">   
    <script src="<%=basePath %>javascript/jquery.min.js"></script>
    <script src="<%=basePath %>javascript/bootstrap.min.js"></script>
    <script src="<%=basePath %>javascript/main.js"></script>
    <script src="<%=basePath %>javascript/plugins/pace.min.js"></script>
  </head>
  <body>
    <section class="material-half-bg">
      <div class="cover"></div>
    </section>
    <section class="login-content">
      <div class="logo">
        <h1>XLFD</h1>
      </div>
      <div class="login-box">
        <div class="login-form"   style="padding:15px 40px 60px 40px;">
          <h3 class="login-head" style="padding-bottom:15px;"><img src="<%=basePath%>images/login.jpg" class="img-responsive" style="width:30%;"/></h3>
          <div class="form-group">
            <label class="control-label">用户名</label>
            	<input class="form-control" type="text" name="bossId" id="bossId" placeholder="Email" autofocus>
          </div>
          <div class="form-group">
            <label class="control-label">密码</label>
            <input class="form-control" type="password"  name="bossPwd" id= "bossPwd" placeholder="Password">
          </div>
        <!--   <div class="form-group">
            <div class="utility">
              <div class="animated-checkbox">
                <label>
                  <input type="checkbox"><span class="label-text">自动登录</span>
                </label>
              </div>
              <p class="semibold-text mb-2"><a href="#" data-toggle="flip">忘记密码 ?</a></p>
            </div>
          </div> -->
          <div class="form-group btn-container" >
            <button class="btn btn-primary btn-block" id="btnLogin"><i class="fa fa-sign-in fa-lg fa-fw"></i>登 录</button>
          </div>
        </div>
      </div>
    </section>
<script>
$(window).ready(function(){

	$("#btnLogin").click(function(){
    	
		var bid = $('#bossId').val();
		var pwd = $('#bossPwd').val();
		 $.ajax({
	      		url:"<c:url value='/boss/login'/>",
	      		type:"POST",
	      		dataType:"json",
	      		data:{"bossId":bid,"bossPwd":pwd},
	      		success: function(data){
	      			if(data.result =="success"){
	      				window.location.href='<c:url value="/account/addaccount" />';
	      			}else{
	      				window.location.href='<c:url value="/boss/login" />';
	      			}
	      	    },
	      	   error: function() {
	             alert("当前网络有问题！");
	           }
	      	});
	 });
	 
});
     
     	
     </script>
    <script type="text/javascript">
      $('.login-content [data-toggle="flip"]').click(function() {
      	$('.login-box').toggleClass('flipped');
      	return false;
      });
    </script>
  </body>
</html>