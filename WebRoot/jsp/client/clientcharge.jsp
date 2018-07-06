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
<script type="text/javascript" src="<%=basePath%>javascript/jquery.min.js"></script>
<!-- 模板表引入 -->

<!-- 主页面 CSS-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/main.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/bootstrap.css">

<!-- 字体图标 css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript">
	$(window).ready(function() {
		
		var oInput = document.getElementById("cId");
		oInput.focus();
		
		var url = window.location.search;
	    var loc = url.substring(url.lastIndexOf('=')+1, url.length);
		$("#cId").attr("value",loc);
		//失去焦点 请求客户信息
		$("#cId").blur(function(){
			//调用
			ajaxClient();
		});
		//请求客户信息
		$("#cId").keydown(function(e) {//给输入框绑定按键事件
            var e = e ||event;
            var currKey = 0;
            currKey=e.keyCode||e.which||e.charCode;
			if (currKey == "13") {
				//调用
				ajaxClient();
			}
		});
		$("#qx").click(function(){
			$("#cId").val("");
			$("#cName").val("");
			$("#cBala").val("");
			$("#cState").val("");
			$("#chargeMoney").val("");
		});
		function ajaxClient(){
				var cId = $("#cId").val();				
				$.ajax({
					url : "<c:url value='/client/clientcharge'/>",
					type : "POST",
					dataType : 'json',
					data : {
						"cId" : cId
					},
					success : succFunction,
					error : function() {
						$("#cId").val("");
						$("#cName").val("");
						$("#cBala").val("");
						$("#cState").val("");
						$("#chargeMoney").val("");
						$("#errorText").text("此会员账号输入错误或不存在");
						/* location.reload(); */
					}
				});
				
				function succFunction(result) {
						var json = eval(result); //数组  
						$.each(json, function(index, item) {
						//循环获取数据     
						var cId = json[index].cId; 
						var cName = json[index].cName;
						var cBala = json[index].cBala;
						var cState = json[index].cState;
						$("#cId").val(cId);
						$("#cName").val(cName);
						$("#cBala").val(cBala);
						$("#cState").val(cState);
						$("#errorText").text("");
						});
				}
		
		}
		
		//会员充值
		$("#cz").click(function(){
                var s1 = $("#chargeMoney").val();
                var s2 = $("#cState").val();
                var s3 = $("#cBala").val();
                var s4 = parseInt(s1)+parseInt(s3);
                var s5 = $("#cId").val();
                $("#cbala").attr("value",s4);
                if(s4<=200){
                	$("#cdis").val(0.95);
					$("#cgrade").val("初级会员");
			 
				}else if(s4<=400)
				{
					$("#cdis").val(0.85);
					$("#cgrade").val("中级会员");
				}else if(s4<=600)
				{
					$("#cdis").val(0.75);
					$("#cgrade").val("高级会员");
				}else if(s4<=1000)
				{
					$("#cdis").val(0.65);
					$("#cgrade").val("VIP");
				}else{
					$("#cdis").val(0.5);
					$("#cgrade").val("超级VIP");
				}
                if(s1 == ""){
                    $("#errorText").text("请填写充值金额");
                }
                else if( s2 == 0 ){
                	$("#errorText").text("请激活会员卡");
                }else if(s1.indexOf(' ')!=-1){
                	$("#errorText").text("充值金额包含空格,请重新输入");
                }
				else{
                    $("#charge_command").submit();
                }
			});
	});
</script>

<script src="<%=basePath%>javascript/a.js">
	
</script>
</head>

<body class="app sidebar-mini rtl" style="overflow:-Scroll;overflow-y:hidden">
	<!-- Navbar-->
	<header class="app-header">
		<a class="app-header__logo" href="index.html">XLFD</a>
		<!-- Sidebar toggle button-->
		<a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
		<!-- 顶部右边目录-->
		<ul class="app-nav">
			<!-- 关键字查询会员信息 -->
			<%-- <form class="app-search" action='<c:url value="/client/queryclientgrade"/>'>
				<input class="app-search__input" type="会员查询" placeholder="Search" name="keyword" />
				<button class="app-search__button">
					<i class="fa fa-search"></i>
				</button>
			</form> --%>
			<!--Notification Menu-->

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
	<main class="app-content"
		style="box-sizing:border-box; padding: 30px 0px 15px 15px;background-color: #fff;">
	<div style="margin-top:30px;background-color: #E5E5E5;height:100%;border-radius:15px 0px 0px 0px;">
		<div class="app-title" style="background-color:transparent;margin-bottom:0px;">

			<ul class="app-breadcrumb breadcrumb" style="font-size:15px;">
				<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>&nbsp;&nbsp;
				<li class="breadcrumb-item"><a href="#">会员管理</a></li>
				<li class="breadcrumb-item"><a href="#">会员充值</a></li>
			</ul>
		</div>
		<!-- 右边内容栏 -->
		<div class="box">
			<div class="col-md-12 col-lg-12 table-responsive-xs table-responsive-sm table-responsive-md">
				<form id="charge_command" action="<c:url value="/client/doclientcharge"/>" method="POST">
					<ul class="forminfo" style="margin-top:20px">

						<li><label>会员卡号</label> <input type="text" autocomplete="off" id="cId"
							name="belongClient.cId" class="dfinput" oninput='this.value=this.value.replace(/\D/gi,"")'
							maxlength="11" class="dfinput form-control" placeholder="请输入11位有效手机号"
							style="width: 218px;background-color:#f8f9fa" value="" />
						</li>
						<li><label>会员姓名</label> <input id="cName" class="dfinput"
							style="width: 218px;background-color:#f8f9fa" value="" disabled="disabled" />
						</li>

						<li><label>当前金额</label> <input id="cBala" class="dfinput"
							style="width: 218px;background-color:#f8f9fa" value="" disabled="disabled" />
						</li>
						<li style="display:none"><label>当前状态</label> <input id="cState" class="dfinput"
							style="width: 218px;background-color:#f8f9fa" value="" disabled="disabled" />
						</li>

						<li><label>充值金额</label> <input id="chargeMoney" class="dfinput" name="chargeMoney"
							style="width: 218px;background-color:#f8f9fa" />
						</li>
						<li style="display:none;"><label>充值后余额</label> <input id="cbala" class="dfinput" name="belongClient.cBala"
							style="width: 218px;background-color:#f8f9fa" />
						</li>
						<li style="display:none;"><label>充值后等级</label> <input id="cgrade" class="dfinput"
							name="cgId" style="width: 218px;background-color:#f8f9fa" />
						</li>
						<li style="display:none;"><label>充值后折扣</label> <input id="cdis" class="dfinput"
							name="belongClient.cDiscount" style="width: 218px;background-color:#f8f9fa" />
						</li>
						<li><label style="width: 518px;"><b id="errorText"></b> </label></li>
						<li><label>&nbsp;</label> <input id="cz" class="btn btn-primary  sure" type="button"
							value="充值" style="width:80px;margin-top:20px;background-color:#009688" />
							<input id="qx" class="btn btn-primary  sure" type="button"
							value="取消" style="width:80px;margin-top:20px;background-color:#009688" />
						</li>
					</ul>
				</form>


			</div>
		</div>
	</div>
	</main>

	<script src="<%=basePath%>javascript/popper.min.js"></script>
	<script src="<%=basePath%>javascript/bootstrap.min.js"></script>
	<script src="<%=basePath%>javascript/main.js"></script>
</body>
</html>