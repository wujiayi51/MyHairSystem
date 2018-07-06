<%@ page import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员添加</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="<%=basePath %>javascript/jquery.min.js"></script>
<!-- 主页面 CSS-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/main.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/style.css">
<!-- 时间选择器 -->
<link href="<%=basePath%>styles/bootstrap.css" rel="stylesheet" />
<link href="<%=basePath%>styles/bootstrap-datetimepicker.css"
	rel="stylesheet" />
<script src="<%=basePath%>javascript/bootstrap.min.js"></script>
<script src="<%=basePath%>javascript/moment-with-locals.min.js"></script>
<script src="<%=basePath%>javascript/bootstrap-datetimepicker.js"></script>
<!-- 字体图标 css-->
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- 模板表引入 -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/bootstrap.css">
<style>
	/*员工表的样式*/
	table.txt_center thead tr{
		text-align:center;
	} 
	table.txt_center tbody td{
		height:120px;
		line-height:120px;
		padding-left:20px;
	} 
	table.txt_center tbody td:nth-child(1){
		padding-left:8px;
	}
	table.txt_center tbody td:nth-child(1) input{
		margin:54px 0 0;
		padding-left:0;
		
	} 
	table.txt_center tbody td img{
			width:100px;
			height:120px;
			padding:10px;
	} 
	/*手动输入页码的样式*/
	#autoPage{
	width:20px;
	height:15px;
	background:none;    
    outline:none;    
    border:1px solid #333; 
	text-decoration:none;
	padding-left:5px;
	}
</style>
<script type="text/javascript">
	 $(document).ready(function(){
	 	 //判断员工工号是否存在
        $("#uId").blur(function(){
         var uid=$("#uId").val();
        	$.ajax({
			        type: "post",
			        url: "<c:url value='/user/testuser'/>",
			        processData:true,
			        data: {"uId":uid},
			        success: function(data){ 
			           if(data.result =="success"){
	      					
		      			}else{
		      				$("#uId").val("");
		      				$("#errorText").text("该员工号已存在");
		      				$("#uId").text("");
		      			}
			        },
			  });
        });
       	$("#datetimepicker").datetimepicker({
		    minView: "month", //选择日期后，不会再跳转去选择时分秒 
	    	language:  'zh-CN',
	    	format: 'yyyy-mm-dd',
	    	todayBtn:  1,
	    	autoclose: 1,
			pickerPosition : "top-left"
		});
        	autoPage();
            $(".click").click(function(){
            	
                var ch = $(this).text();//点击控件上的文本，判断用户操作
                if(ch=="添加员工"){
                    $("#errorText").text("");
                    $(".tip2").fadeIn(200);
                    return;
                }
            });
			//关闭、取消、确定按钮
            $(".tiptop a").click(function(){
                $(".tip3").fadeOut(200);
            });
              $(".tiptop a").click(function(){
                $(".tip4").fadeOut(200);
            });
            $(".cancel").click(function(){
                $(".tip3").fadeOut(100);
                $(".tip4").fadeOut(100);
                $(".tip2").fadeOut(100);
            });
			
            $("#xg").click(function(){
   			 	
            	$("#update_command").submit();
            });
            $(".tiptop2 a").click(function(){
               $(".tip2").fadeOut(200);
            }); 
         
            $("#bl").click(function(){
            	$("#leave_command").submit();
            });
            $(".tiptop3 a").click(function(){
               $(".tip3").fadeOut(200);
            }); 
            
            $("#tj").click(function(){
            var uid=$("#uId").val();
            var uname=$("#uName").val();
            var utId=$("#utId").val();
            var userImg=$("#userImg").val();
            if(uid=="" || uname=="" || utId=="" || userImg=="" || uid==""){
           		 alert("请完善信息！");
            } else{
                 $("#user_command").attr("action","<c:url value='/user/adduser'/>");//动态改变action的值
                 $("#user_command").submit();
                 }
			}); 
          
        });
      
        //办理离职
        function blUser(uid,state,leavedate){
        	$(".tip4").fadeIn(200);
        	var str = "<c:url value='/user/'/>"+uid+"/leaveuser";
        	$("#leave_command").attr("action",str);
        	$(".tip4 #uId").val(uid);
        	if(state==1){
    			$(".tip4 #state").attr("checked","checked");
    		}else if(state==0){
    			$(".tip4 #state").attr("checked","checked");
    		}else{
    			$(".tip4 #state").attr("checked","checked");
    		}
    		$(".tip4 #datetimepicker").val(leavedate);
        }
        //修改user
        function xgUser(uid,utName,uname,usex){
    		$(".tip3").fadeIn(200);
    		var str = "<c:url value='/user/'/>"+uid+"/updateuser";
    		$("#update_command").attr("action",str);
    		$(".tip3 #uId").val(uid);
    		$(".tip3 #utId option[value='"+utName+"']").attr("selected","selected");
    		$(".tip3 #uName").val(uname);
    		if(usex=="男"){
    			$(".tip3 #uSex3").attr("checked","checked");
    		}else{
    			$(".tip3 #uSex4").attr("checked","checked");
    		}
	   		 
    	}
        function autoPage(pagecount){
     	   $(this).css({border:"1px"});
     	   $(this).attr("placeholder","请输入");
     	  
     	   $("#autoPage").blur(function(){
     		   var pagenum= $(this).val();
     		   $(this).attr("placeholder",pagenum);
     		   if(pagenum<=0){
     			  
     			   window.location.href = '<c:url value="/user/queryuser?pagenum=1"/>';
     			   return false;
     		   }else if(pagenum >=pagecount){
     			   
     			   window.location.href = '<c:url value="/user/queryuser?pagenum="/>'+pagecount;
     			   return false;
     		   }
     		   window.location.href = '<c:url value="/user/queryuser?pagenum="/>'+pagenum;
     	   }); 
        }
    </script>
    <script src="<%=basePath %>javascript/a.js"></script>
</head>

<body class="app sidebar-mini rtl">
	<!-- Navbar-->
	<header class="app-header">
		<a class="app-header__logo" href="index.html">XLFD</a>
		<!-- Sidebar toggle button-->
		<a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
		<!-- 顶部右边目录-->
		<ul class="app-nav">
			<form  class="app-search" action='<c:url value="/user/queryuser"/>' >
	        	<input class="app-search__input"  placeholder="Search" name="keyword"/>
	        	<button class="app-search__button">
						<i class="fa fa-search"></i>
				</button>
            </form>
	
			<!-- User Menu-->
			<li class="dropdown"><a class="app-nav__item" style="display:block;" href="#"
				data-toggle="dropdown" aria-label="Open Profile Menu"><i style="line-height:45px;"
					class="fa fa-user fa-lg"></i> </a>
				<ul class="dropdown-menu settings-menu dropdown-menu-right">
					<li><a class="dropdown-item" href='<c:url value="/account/addaccount" />'><i class="fa fa-cog fa-md"></i> 首页</a>
					</li>
					<li><a class="dropdown-item" href='<c:url value="/boss/updateboss" />' ><i class="fa fa-user fa-lg"></i>修改密码
					</a>
					</li>
					<li><a class="dropdown-item"  href="<c:url value='/boss/login'/>"><i class="fa fa-sign-out fa-md"></i> 退出登录</a>
					</li>
				</ul>
			</li>
		</ul>
	</header>
	<!-- 滑动目录-->
<!-- 滑动目录-->
	<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
	<aside class="app-sidebar">
		<div class="app-sidebar__user">
			<img class="app-sidebar__user-avatar" src="<%=basePath%>images/logo1.jpg" width="45px"
				height="45px" alt="User Image">
			<div>
				<p class="app-sidebar__user-name">小李飞刀</p>
				<p class="app-sidebar__user-designation">美·无止境&nbsp&nbsp&nbsp发·塑今生</p>
			</div>
		</div>
		<ul class="app-menu">
			<li class="treeview"><a class="app-menu__item" href="#" target="_self"
				data-toggle="treeview"> <i class="app-menu__icon fa fa-dashboard"></i> <span
					class="app-menu__label">收银管理</span> <i class="treeview-indicator fa fa-angle-right"></i> </a>
				<ul class="treeview-menu">
					<li><a class="treeview-item" href='<c:url value="/account/addaccount" />' target="_self">
							<i class="icon fa fa-circle-o"></i> 现在收银 </a>
					</li>
					<li><a class="treeview-item" href='<c:url value="/account/queryaccountbill" />'
						target="_self" rel="noopener"> <i class="icon fa fa-circle-o"></i> 每日账单 </a>
					</li>
				</ul></li>
			<li class="treeview"><a class="app-menu__item" href='<c:url value="/serve/queryserve" />'
				target="_self" data-toggle="treeview"> <i class="app-menu__icon fa fa-th-list"></i> <span
					class="app-menu__label">服务项目</span> <i class="treeview-indicator fa fa-angle-right"></i> </a>
				<ul class="treeview-menu">
					<li><a class="treeview-item" href='<c:url value="/serve/queryserve" />' target="_self">
							<i class="icon fa fa-circle-o"></i> 项目列表 </a>
					</li>
				</ul>
			</li>
			<li class="treeview"><a class="app-menu__item"
				href='<c:url value="/commodity/querycommodity" />' target="_self" data-toggle="treeview"><i
					class="app-menu__icon fa fa-edit"></i><span class="app-menu__label">商品管理</span><i
					class="treeview-indicator fa fa-angle-right"></i> </a>
				<ul class="treeview-menu">
					<li><a class="treeview-item" target="_self"
						href='<c:url value="/commodity/querycommodity" />'> <i class="icon fa fa-circle-o"></i>
							商品列表</a>
					</li>
				</ul></li>
			<li class="treeview"><a class="app-menu__item" target="_self"
				href='<c:url value="/client/queryclient" />' data-toggle="treeview"><i
					class="app-menu__icon fa fa-file-text"></i><span class="app-menu__label">会员管理</span><i
					class="treeview-indicator fa fa-angle-right"></i> </a>
				<ul class="treeview-menu">
					<li><a class="treeview-item" href='<c:url value="/client/queryclient" />' target="_self"><i
							class="icon fa fa-circle-o"></i> 会员列表</a>
					</li>
					<li><a class="treeview-item" href='<c:url value="/client/queryclientgrade" />'
						target="_self" rel="noopener"><i class="icon fa fa-circle-o"></i> 会员等级</a>
					</li>
					<li><a class="treeview-item" target="_self" href='<c:url value="/client/clientcharge" />'
						target="_self"> <i class="icon fa fa-circle-o"></i> 会员充值</a>
					</li>
				</ul></li>
			<li class="treeview"><a class="app-menu__item" href='<c:url value="/user/queryuser" />'
				target="_self" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span
					class="app-menu__label">员工管理</span><i class="treeview-indicator fa fa-angle-right"></i> </a>
				<ul class="treeview-menu">
					<li><a class="treeview-item" href='<c:url value="/user/queryuser" />' target="_self"><i
							class="icon fa fa-circle-o"></i> 员工基本信息</a>
					</li>
					<li><a class="treeview-item" href='<c:url value="/usertype/queryusertype" />'
						target="_self" rel="noopener"><i class="icon fa fa-circle-o"></i> 工种管理</a>
					</li>
				</ul></li>
			<li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
					class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">财务管理</span><i
					class="treeview-indicator fa fa-angle-right"></i> </a>
				<ul class="treeview-menu">
					<li><a class="treeview-item" href='<c:url value="/salary/querysalary" />' target="_self"><i
							class="icon fa fa-circle-o"></i> 工资发放</a>
					</li>
					<li><a class="treeview-item" href='<c:url value="/expense/queryexpense" />' target="_self"
						rel="noopener"><i class="icon fa fa-circle-o"></i> 店内开支单</a>
					</li>
					<li><a class="treeview-item" href='<c:url value="/client/querychargerecord" />' target="_self"
						rel="noopener"><i class="icon fa fa-circle-o"></i> 会员充值单</a>
					</li>
				</ul></li>
			<li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i
					class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">报表管理</span><i
					class="treeview-indicator fa fa-angle-right"></i> </a>
				<ul class="treeview-menu">
					<li><a class="treeview-item" href='<c:url value="/yuchart" />' target="_self"><i
							class="icon fa fa-circle-o"></i> 员工报表</a>
					</li>

					<li><a class="treeview-item" href='<c:url value="/chart" />' target="_self" rel="noopener"><i
							class="icon fa fa-circle-o"></i> 利润报表</a>
					</li>

					<li><a class="treeview-item" href='<c:url value="/echars/chart" />' target="_self"
						rel="noopener"><i class="icon fa fa-circle-o"></i> 项目报表</a>
					</li>

				</ul></li>
		</ul>
	</aside>
		<main class="app-content" style="box-sizing:border-box; padding: 30px 0px 15px 15px;background-color: #fff;">
			<div style="margin-top:30px;background-color: #E5E5E5;height:100%;border-radius:15px 0px 0px 0px;">
				<div class="app-title" style="background-color:transparent;margin-bottom:0px;">
					<ul class="app-breadcrumb breadcrumb" style="font-size:15px;">
						<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i>&nbsp;&nbsp;</li>
						<li class="breadcrumb-item"><a href="#">员工管理</a></li>
						<li class="breadcrumb-item"><a href="#">员工信息列表</a></li>
					</ul>
					<div>
						<button  type="button" class="btn btn-primary click" >添加员工</button>
					</div>
			      </div>
				<!-- 右边内容栏 -->
				<div class="">
					<div class="col-md-12 col-lg-12 table-responsive-sm table-responsive-md">
						
						<table class="table  table-light table-hover txt_center" style="margin-top:20px;font-size:12px;">
							<thead>
								<tr class="btn-primary">
								    <th><input name="" type="checkbox" value="" onclick="AAA('ck')"/></th>
									<th>员工工号</th>
									<th>员工类型</th>
									<th>姓名</th>
									<th>性别</th>
									<th><span style="padding-left:40%;">工装照</span></th>
									<th>入职时间</th>					
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${user_list}" var="currUser">    	
			        		<tr>
				        		<td><input name="ck" type="checkbox" value="${currUser.uId }" /></td>  
				        		<td>
						        	<c:out value="${currUser.uId}"></c:out>
						        </td>
						        <td>
						            <c:choose>
								        <c:when test="${currUser.advanceUtname == null}">
								       		<c:out value="${currUser.belongUserType.utName}"></c:out>
								        </c:when>
								        <c:otherwise>
								        	<c:out value="${currUser.advanceUtname}"></c:out>
								        </c:otherwise>
							    	</c:choose>
						        </td>
						        <td>
						        	<c:out value="${currUser.uName}"></c:out>
						        </td> 
						        <td>
						        	<c:out value="${currUser.uSex}"></c:out>
						        </td> 
						        
						        <td>
						        	<span style="padding-left:30%;">
						        		<c:choose>
						        		<c:when test="${ not empty (currUser.imgName)}">
						        			<img src="<c:url value='/upload/userImg/${currUser.imgName}'/>" />
						        		</c:when>
						        		<c:otherwise>
						        		&nbsp;
						        		</c:otherwise>
						        		</c:choose>
						        	</span>
						        </td> 
						        <td>
						        	<fmt:formatDate value="${currUser.hireDate}" pattern="yyyy-MM-dd"/>	  
						        </td>
						        <td>
						        	<a href="javascript:xgUser('<c:out value="${currUser.uId}"/>','<c:out value="${currUser.belongUserType.utName}"/>','<c:out value="${currUser.uName}"/>','<c:out value="${currUser.uSex}"/>')" class="tablelink"> 修改</a>
						        	<a href="javascript:blUser('<c:out value="${currUser.uId}"/>','<c:out value="${currUser.state}"/>','<c:out value="${currUser.leaveDate}"/>')">办理离职</a>
						        	<a href='<c:url value="/user/${currUser.uId}/deleteuser"/>'>删除</a>
						        </td>
						        </tr>
					        </c:forEach>
					        </tbody>
						</table>
						
						<!--翻页页码  -->
						
						<div class="pagin">
						<div class="message">当前显示第&nbsp;&nbsp;<i class="blue"><input id="autoPage" placeholder='<c:out value="${pagenum }"/>' type="text" onClick="autoPage('<c:out value="${pagecount }"/>')" />&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${pagecount }"/></i>页</div>
				        <ul class="paginList">  
					        <li class="paginItem">
					        	<c:choose>
					        	<c:when test="${pagenum gt 1 }">
					        	<a href='<c:url value="/user/queryuser?pagenum=${pagenum-1 }" />'><span class="pagepre page canpage"></span></a>
					        	</c:when>
					        	<c:otherwise>
					        	<a href="#"><span class="pagepre page nopage"></span></a>
					        	</c:otherwise>
					        	</c:choose>
					        </li>    
					        <li class="paginItem">
					        	<a href="#"></a>
					        </li>
					        <li class="paginItem">
					        	<c:choose>
						        <c:when test="${pagenum lt pagecount }">
						        	<a href='<c:url value="/user/queryuser?pagenum=${pagenum+1 }" />'><span class="pagenxt page canpage"></span></a>
						        </c:when>
						        <c:otherwise>
						        	<a href='#'><span class="pagenxt page nopage"></span></a>
						        </c:otherwise>
						    	</c:choose>
						    </li>
				        </ul>
				        </div> 
				        
				        <!-- 添加员工弹框 -->
				      
				         <div class="tip2 form" style="height:500px;top:-60px;">
					 		<div class="tiptop2"><span>添加员工</span><a></a></div>
							<div class="formbody">
								<div id="usual1" class="usual">
									<div id="tab1" class="tabson">
									<form:form  commandName="user_command"   class="form-group"   method="POST" enctype="multipart/form-data">
											<ul class="forminfo">
												<li>
													<label>员工工号<b>*</b></label>
													<input id="uId" name="uId"  style="width: 518px;" class= "form-control" type="text" />
												</li>
												<li>
													<label>姓名<b>*</b></label>
													<input id="uName" name="uName" style="width: 518px;" class="dfinput form-control" type="text" />
												</li>
												<li>
													<label>员工类型<b>*</b></label>
													<select name="belongUserType.utId" id="utId" class="col-sm-4" style="height:30px;">
													<c:forEach items="${usertype_list}" var="currUserType"> 
														<option value="${currUserType.utId }" ><c:out value="${currUserType.utName }"/></option>	
													</c:forEach>
													</select>
												</li>
												<li>
													<form:label path="uSex">性别<b>*</b></form:label>
													<span style="line-height:30px;font-size:14px;">
														<form:radiobutton path="uSex" value="男"  checked="checked"/> 男       &nbsp; &nbsp; &nbsp; &nbsp;
														<form:radiobutton path="uSex" value="女" /> 女
												    </span>			
												</li>
												<li>
													<label>工装照<b>*</b></label>
													 <input id="userImg" name="userImg" style="width: 518px; "  type="file" />
												</li>
												<li><label style="width: 518px;"><b id="errorText"></b> </label></li>
									            <li>
													<label>&nbsp;</label>			
													<button id="tj" type="button" class="btn btn-primary">添加</button>&nbsp;&nbsp;&nbsp;
													<button  type="button" class="btn btn-primary cancel">取消</button>
												</li>
											</ul>
										</form:form>		
									</div>
								</div>
							</div>
					   </div>  
					   <!-- 修改员工弹框 -->
						 <div class="tip3 form" style="height:410px;left:150px;top:-20px;">
					 		<div class="tiptop"><span>修改员工信息</span><a></a></div>
							<div class="formbody">
								<div id="usual1" class="usual">
									<div id="tab1" class="tabson">
										<form:form  commandName="update_command" class="form-group" method="POST" enctype="multipart/form-data">
										   	 <ul class="forminfo">
										   	
												<li>
													<label>员工工号<b>*</b></label>
													<input id="uId" name="uId" disabled style="width: 518px;" class= "form-control" type="text" />
												</li>
												<li>
												 	<label>员工类型<b>*</b></label>
			
													<select name="belongUserType.utName" id="utId" class="col-sm-4" style="height:30px;">
													<c:forEach items="${usertype_list}" var="currUserType"> 
														<option value="${currUserType.utName}" ><c:out value="${currUserType.utName }"/></option>	
													</c:forEach>
													</select>
				                    			</li>
												<li>
													<label>姓名<b>*</b></label>
													<input id="uName" name="uName" style="width: 518px;" class="dfinput form-control" type="text" />
												</li>
												<li>
													<form:label path="uSex">性别<b>*</b></form:label>
													<span style="line-height:35px;font-size:12px;">
														<form:radiobutton path="uSex" value="男"/> 男       &nbsp; &nbsp; &nbsp; &nbsp;
														<form:radiobutton path="uSex" value="女" /> 女
												    </span>
												</li>
									            <li>
													<label>&nbsp;</label>			
													<button id="xg" type="button" class="btn btn-primary">修改</button>&nbsp;&nbsp;&nbsp;
													<button  type="button" class="btn btn-primary cancel">取消</button>
												</li>
											</ul>
										</form:form>		
									</div>
								</div>
							</div>
					    </div>
					    <!--办理离职弹框 -->
						 <div class="tip tip4 form" style="height:360px;left:150px;top:-20px;">
					 		<div class="tiptop"><span>办理离职登记</span><a></a></div>
							<div class="formbody">
								<div id="usual1" class="usual">
									<div id="tab1" class="tabson">
										<form:form  commandName="leave_command" class="form-group" method="POST">
										   	 <ul class="forminfo">
										   	
												<li>
													<label>员工工号<b>*</b></label>
													<input id="uId" name="uId"  style="width: 518px;" class= "form-control" type="text" />
												</li>
												<li>
													<form:label path="state">状态<b>*</b></form:label>
													<span style="line-height:35px;font-size:12px;">
														<form:radiobutton path="state" value="1" />在职       &nbsp; &nbsp; &nbsp; &nbsp;
														<form:radiobutton path="state" value="0" />将要离职
														<form:radiobutton path="state" value="-1" />离职
												    </span>
													<br/>
												</li>
												<li class='input-group date' id='datetimepicker' style="line-height:35px;font-size:12px;">
													<input type='text' class="form-control" name="leaveDate" id="selectdate" placeholder="请选择年份" value="" /> <span
														class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span> </span>
												</li>
												
									            <li>
													<label>&nbsp;</label>			
													<button id="bl" type="button" class="btn btn-primary">办理</button>&nbsp;&nbsp;&nbsp;
													<button  type="button" class="btn btn-primary cancel">取消</button>
												</li>
											</ul>
										</form:form>		
									</div>
								</div>
							</div>
					    </div>
					   
					   
					   
					</div>
				</div>
			</div>
		</main>
		
	
	<script src="<%=basePath%>javascript/popper.min.js"></script> 
	<script src="<%=basePath%>javascript/main.js"></script> 
	<script src="<%=basePath%>javascript/plugins/pace.min.js"></script> 
</body>
</html>