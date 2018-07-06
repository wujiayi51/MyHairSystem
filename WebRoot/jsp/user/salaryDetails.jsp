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
		padding-left:20px;
	} 
	table.txt_center tbody td:nth-child(1){
		padding-left:8px;
	}
	table.txt_center tbody td:nth-child(1) input{
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
	
	b{
	color:red;
	}

</style>
<script type="text/javascript">
        $(document).ready(function(){
        	$("#xgBtn").click(function(){
        		xgSalary();
        		$(".tip3").fadeIn(200);
        		
        	}); 
        	$("#xg").click(function(){
        		$("#update_command").submit();
        		$(".tip3").fadeOut(200);
        	});
        	 $(".tiptop a").click(function(){
                $(".tip3").fadeOut(200);
            });
            $(".cancel").click(function(){
                $(".tip3").fadeOut(100);
            });
        	
        });
        function xgSalary(usid,final_salary,add_des){
    		$(".tip3 #id").val(usid);
    		$(".tip3 #finalSalary").val(final_salary);
    		$(".tip3 #addDes").val(add_des);
    	}
    	function btnnum1(pre){
    		var uid = $("#accountUid").text();
    		var sysdate = $("#sysdate").text();
	    	var str2 ='<c:url value="/salary/querydetails?uid='+uid+'&salarydate='+sysdate+'&pagenum=${pagenum-1 }" />';
	    	$(pre).attr("href",str2);
    	}
    	function btnnum1(next){
    		var uid = $("#accountUid").text();
    		var sysdate = $("#sysdate").text();
	    	var str2 ='<c:url value="/salary/querydetails?uid='+uid+'&salarydate='+sysdate+'&pagenum=${pagenum+1 }" />';
	    	$(next).attr("href",str2);
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
						<li class="breadcrumb-item"><a href="#">财务管理</a></li>
						<li class="breadcrumb-item"><a href="#">工资发放</a></li>
					</ul>
					<div>
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
									<th>姓名</th>
									<th>工资发放月份</th>
									<th>基本工资</th>
									<th>提成比例</th>
									<th>应付工资总额</th>
									<th>会员实付金额之和</th>					
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${salary_List}" var="currSalary">    	
			        		<tr>
				        		 <td><input name="ck" type="checkbox" value="${currSalary.USbelongUser.uId }" /></td>  
						        <td id="accountUid">
						        	<c:out value="${currSalary.USbelongUser.uId }" />
						        </td>
						        <td>
						        	<c:out value="${currSalary.USbelongUser.uName}" />
						        </td>
						         <td id="sysdate">
						        	<c:out value="${currSalary.salaryDate}" />
						        </td>
						        <td>
						        	<c:out value="${currSalary.USbelongUser.belongUserType.utSalary}" />
						        </td>
						         <td>
						        	<c:out value="${currSalary.USbelongUser.belongUserType.utScale}" />
						        </td>
						         <td>
						        	<c:out value="${currSalary.finalSalary}" />
						        </td>
						         <td>
						        	<c:out value="${currSalary.rtMoney}" />
						        </td>
						         <td>
						        	<a id="xgBtn" href="javascript:xgSalary('<c:out value="${currSalary.id }" />','<c:out value="${currSalary.finalSalary}" />','<c:out value="${currSalary.addDes}" />')">修改</a>
						        </td>
						        </tr>
					        </c:forEach>
					        </tbody>
						</table>
						<table id="recording" class="table  table-light table-hover txt_center" style="font-size:12px;">
							<thead>
								<tr>
									该员工本月所有的工作记录<b>*</b>
								</tr>
								<tr class="">
								  <!--   <th><input name="" type="checkbox" value="" onclick="AAA('ck')"/></th> -->
									<th>消费时间</th>
									<th>客户账号</th>
									<th>服务项目</th>
									<th>提供服务的员工号</th>
									<th>应付金额</th>
									<th>实付金额</th>					
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${account_list}" var="currAccount">    	
			        		<tr>
				        		<!-- <td><input name="ck" type="checkbox" /></td>  -->
				        		<td>
						        	<c:out value="${currAccount.payTime}" />
						        </td> 
						         <td>
						         	<c:choose>
						        	<c:when test="${empty currAccount.belongClient.cId}">
						        		非会员
						        	</c:when>
						        	<c:otherwise>
						        		<c:out value="${currAccount.belongClient.cId}" />
						        	</c:otherwise>
						        	</c:choose>
						        </td>
						         <td>
						        	<c:out value="${currAccount.belongServe.sName}" />
						        </td>
						        <td>
						        	<c:out value="${currAccount.belongUser.uId }" />
						        </td>
						         <td>
						        	<c:out value="${currAccount.shouldMoney}" />
						        </td>
						         <td>
						        	<c:out value="${currAccount.realMoney}" />
						        </td>
						        </tr>
					        </c:forEach>
					        </tbody>
						</table>
					<!--翻页页码  -->
						
						<div class="pagin">
						<div class="message">
						当前显示第&nbsp;<i class="blue"><c:out value="${pagenum }" />/<c:out value="${pagecount }" />
						</i>页
						</div>
				        <ul class="paginList">  
					        <li class="paginItem">
					        	<c:choose>
					        	<c:when test="${pagenum gt 1 }">
					        	
					        		<a href="#"onclick=btnnum1(this)><span class="pagepre page canpage"></span></a>
					        	
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
						        	<a href="#" onclick=btnnum1(this)><span class="pagenxt page canpage"></span></a>
						        </c:when>
						        <c:otherwise>
						        	<a href='#'><span class="pagenxt page nopage"></span></a>
						        </c:otherwise>
						    	</c:choose>
						    	
						    </li>
				        </ul>
				        </div>
					<!-- 修改界面 -->
					<div class="tip3 form" style="height:350px">
					 		<div class="tiptop"><span>修改工资</span><a></a></div>
							<div class="formbody">
								<div id="usual1" class="usual">
									<div id="tab1" class="tabson">
										<form  id="update_command" action="<c:url value='/salary/updatesalary'/>" class="form-group" method="POST">
										   	 <ul class="forminfo">
										   	 	<li>
													<input id="id" name="id" type="hidden"/>
												</li>	
												<li>
													<label>修改工资<b>*</b></label><br/>
													<input id="finalSalary" name="finalSalary" style="line-height:30px;width:150px;border:1px solid #ccc;padding-left:10px;"/>&nbsp;&nbsp;元
												</li>
												<li>
													<label>备注<b>*</b></label><br/>
													<textarea id="addDes" name="addDes"  style="line-height:30px;width:200px;border:1px solid #ccc;padding-left:10px;"></textarea>
												</li>
									            <li style="padding-top:30px;">
													<label>&nbsp;</label>			
													<button id="xg" type="button" class="btn btn-primary">修改</button>&nbsp;&nbsp;&nbsp;
													<button  type="button" class="btn btn-primary cancel">取消</button>
												</li>
											</ul>
										</form>		
									</div>
								</div>
							</div>
					    </div>
					   
					
					
					</div>
				</div>
			</div>
		</main>
		
	<script src="<%=basePath%>javascript/jquery-3.2.1.min.js"></script> 
	<script src="<%=basePath%>javascript/popper.min.js"></script> 
	<script src="<%=basePath%>javascript/bootstrap.min.js"></script> 
	<script src="<%=basePath%>javascript/main.js"></script> 
	<script src="<%=basePath%>javascript/plugins/pace.min.js"></script> 
</body>
</html>