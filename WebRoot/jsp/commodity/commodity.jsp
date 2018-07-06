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
<!-- 主页面 CSS-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/main.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/style.css">
<!-- 字体图标 css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- 模板表引入 -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/bootstrap.css">
<script type="text/javascript">
	$(document).ready(function() {
		
		$(".click").click(function() {

			var ch = $(this).text();//点击控件上的文本，判断用户操作
			var ids = BBB('ck');
			if (ch == "添加商品") {
				$("#errorText").text("");
				$(".tip2").fadeIn(200);
				return;
			}/*  else if (ch == "批量删除") {
				if (ids == "") {
					alter('请选择要修改的数据！');
				} else {
					//document.getElementById('vk').innerText=ids;
					$("#vk").text(ids);
					$(".tiptop span").text(ch);//类tiptop控件中的嵌套span中的文本修改
					$(".tip").fadeIn(200);
				}

			} */

		});
		//关闭、取消、确定按钮
		$(".tiptop a").click(function() {
			$(".tip3").fadeOut(200);
		});
		$(".cancel").click(function() {
			$(".tip3").fadeOut(100);
		});

		$("#xg").click(function() {
		    var comid = $("#comid").val();
			var comnum = $("#comnum").val();
			if (comid == "") {
				alert("商品名称未填写");
			}else if (comnum == "") {
				alert("商品数量未填写");
			}else{
				$("#update_command").submit();
			}
		});
		$(".tiptop2 a").click(function() {
			$(".tip2").fadeOut(200);
		});
		$(".tiptop3 a").click(function() {
			$(".tip3").fadeOut(200);
		});
		$(".qx").click(function() {
			$(".tip2").fadeOut(100);
		});
		$(".qx").click(function() {
			$(".tip3").fadeOut(100);
		});
		$("#tj").click(function() {
			var s1 = $("#comId").val();
			var s2 = $("#comNum").val();
			alert(s1);
			if (s1 == "") {
				alert("商品名称未填写");
			} else if (s2 == "") {
				alert("商品数量未填写");
			} else {
				$("#commodity_command").submit();
			}
		});

	});
	function xgClient(id, cid, num) {
		$(".tip3").fadeIn(200);
		var str = "<c:url value='/commodity/'/>" + id + "/updatecommodity";
		$("#update_command").attr("action", str);
		$("#id").val(id);
		$("#comid").val(cid);
		$("#comnum").val(num);

	}
 
</script>
<script src="<%=basePath%>javascript/a.js"></script>
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

			<form  class="app-search" action='<c:url value="/commodity/querycommodity"/>'>
	        	<input class="app-search__input"  placeholder="Search" name="keyword"/>
	        	<button class="app-search__button">
						<i class="fa fa-search"></i>
				</button>
            </form>
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
				<li class="breadcrumb-item"><a href="#">商品管理</a></li>
				<li class="breadcrumb-item"><a href="#">商品列表</a></li>
			</ul>
			
			<div>
				<button type="button" class="btn btn-primary click">添加商品</button>
			</div>
		</div>
		<!-- 右边内容栏 -->
		<div class="">
			<div class="col-md-12 col-lg-12 table-responsive-sm table-responsive-md">

				<table class="table  table-light table-hover" style="margin-top:20px;font-size:12px;">
					<thead>
						<tr class="btn-primary">
							<th><input name="" type="checkbox" value="" onclick="AAA('ck')" /></th>
							<th>商品编号</th>
							<th>商品名称</th>
							<th>数量</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${commodityList}" var="currcommodity">
							<tr>
								<td><input name="ck" type="checkbox" style="margin:0px;" value="${currcommodity.id }" />
								</td>
								<td><c:out value="${currcommodity.id}"></c:out>
								</td>
								<td><c:out value="${currcommodity.comId}"></c:out>
								</td>
								<td><c:out value="${currcommodity.comNum}"></c:out>
								</td>
								<td><a id="up1"
									href="javascript:xgClient('<c:out value="${currcommodity.id}"/>','<c:out value="${currcommodity.comId}"/>','<c:out value="${currcommodity.comNum}"/>')"
									class="tablelink">修改</a> <a id="up1"
									href="<c:url value="/commodity/${currcommodity.id}/deletecommodity"/>" class="tablelink">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<!--翻页页码  -->
				<div class="pagin">
					<div class="message">
						当前显示第&nbsp;<i class="blue"><c:out value="${pageNum }" />/<c:out value="${pageCount }" />
						</i>页
					</div>
					<ul class="paginList">
						<li class="paginItem"><c:choose>
								<c:when test="${pageNum gt 1 }">
									<a href='<c:url value="/commodity/querycommodity?pageNum=${pageNum-1 }" />'><span
										class="pagepre page canpage"></span> </a>
								</c:when>
								<c:otherwise>
									<a href="#"><span class="pagepre page nopage"></span> </a>
								</c:otherwise>
							</c:choose>
						</li>
						<li class="paginItem"><a href="commodity?pageNum="></a>
						</li>
						<li class="paginItem"><c:choose>
								<c:when test="${pageNum lt pageCount }">
									<a href='<c:url value="/commodity/querycommodity?pageNum=${pageNum+1 }" />'><span
										class="pagenxt page canpage"></span> </a>
								</c:when>
								<c:otherwise>
									<a href='#'><span class="pagenxt page nopage"></span> </a>
								</c:otherwise>
							</c:choose>
						</li>
					</ul>
				</div>


				<!-- 提示信息 -->
				<div class="tip">
					<div class="tiptop">
						<span>提示信息</span><a></a>
					</div>

					<div class="tipinfo">
						<span><img src="images/ticon.png" /> </span>
						<div class="tipright">
							<p>是否确认对信息的修改 ？</p>
							<cite id="vk">如果是请点击确定按钮 ，否则请点取消。</cite>
						</div>
					</div>
					<div class="tipbtn">
						<input name="" type="button" class="sure" value="确定" /> &nbsp; <input name="" type="button"
							class="cancel" value="取消" />
					</div>

				</div>
				<!-- 添加商品弹框 -->

				<div class="tip2 form" style="height:300px">
					<div class="tiptop2">
						<span>添加商品</span><a></a>
					</div>
					<div class="formbody">
						<div id="usual1" class="usual">
							<div id="tab1" class="tabson">
								<form id="commodity_command" class="form-group"
									action="<c:url value="/commodity/addcommodity"/>" method="POST">
									<ul class="forminfo">
										<li><label>商品名称<b>*</b> </label> <input id="comId" name="comId" style="width: 518px;"
											class="dfinput form-control" type="text" value="" />
										</li>
										<li><label>商品数量<b>*</b> </label> <input id="comNum" name="comNum"
											style="width: 518px;" class="dfinput form-control" type="text" value="" />
										</li>
										<li><label>&nbsp;</label>

											<button id="tj" type="button" class="btn btn-primary">添加</button>
											<button  type="button" class="btn btn-primary qx">取消</button>
										</li>
									</ul>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 修改会员弹框 -->
			<div class="tip3" style="height:300px">
				<div class="tiptop">
					<span>修改商品</span><a></a>
				</div>
				<div class="formbody">
					<div id="usual1" class="usual">
						<div id="tab1" class="tabson">

							<form id="update_command" action="" method="POST">
								<ul class="forminfo">
									<li style="display:none"><label> 商品编号 <b>*</b> </label> <input id="id" name="id"
										style="width: 518px;" class="dfinput" type="text" value="" />
									</li>
									<li><label> 商品名称 <b>*</b> </label> <input id="comid" name="comId"
										style="width: 518px;" class="dfinput" type="text" value="" />
									</li>
									<li><label> 商品数量 <b>*</b> </label> <input id="comnum" name="comNum"
										style="width: 518px;" class="dfinput" type="text" value="" />
									</li>

									<!-- <li><label style="width: 518px;"><b id="errorText"></b></label></li>
								 -->
									<li><label>&nbsp;</label>
										<button id="xg" class="btn btn-primary  sure">修改</button>
										<button type="button" class="btn btn-primary qx">取消</button>
							</form>
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
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>