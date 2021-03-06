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
<title>利润报表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="<%=basePath%>javascript/jquery.min.js"></script>
<!-- 模板表引入 -->

<!-- 主页面 CSS-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/main.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/style.css">

<!-- 时间选择器 -->

<link href="<%=basePath%>styles/bootstrap.css" rel="stylesheet" />
<link href="<%=basePath%>styles/bootstrap-datetimepicker.css" rel="stylesheet" />
<script src="<%=basePath%>javascript/bootstrap.min.js"></script>
<script src="<%=basePath%>javascript/moment-with-locals.min.js"></script>
<script src="<%=basePath%>javascript/bootstrap-datetimepicker.js"></script>
<!-- 字体图标 css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#datetimepicker").datetimepicker({
			format : "yyyy",
			weekStart : 1,
			autoclose : true,
			startView : 4,
			minView : 4,
			forceParse : false,
			endDate : new Date(), // 窗口可选时间从今天开始 
			pickerPosition : "bottom-left"

		});

		$("#selectdate").change(function() {//给输入框绑定按键事件
			var keyword = $("#selectdate").val();
			alert(keyword);
			cx(keyword);
		});
		
		//切换
		$(".").click(function(event) {
			event.preventDefault();
			$(".app").addClass("sidenav-toggled");
		});
	}); 
</script>
<script type="text/javascript">
	function cx(keyword) {
		require.config({
			paths : {
				echarts : 'http://echarts.baidu.com/build/dist'
			}
		});
		// 使用
		require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
		], drewEcharts);
		function drewEcharts(ec) {
			// 基于准备好的dom，初始化echarts图表
			var myChart = ec.init(document.getElementById('box'));

			var option = {
				title : {
					text : '年度总收入、总支出柱状图',
					textStyle : {
						color : '#009688'
					}
				},
				tooltip : {
					show : true
				},
				legend : {
					data : [ '月收入', '月支出' ]
				},
				xAxis : [ {
					type : 'category',
					data : (function() {
						var arr = [];
						$
								.ajax({
									type : "post",
									async : false, //同步执行
									url : "chart",
									data : {
										"keyword" : keyword
									},
									dataType : "json", //返回数据形式为json
									success : function(result) {
										if (result) {
											for ( var i = 0; i < result[0].list2.length; i++) {

												arr.push(result[0].list2[i][1]);
											}
										}

									},
									error : function(errorMsg) {
										alert("图表请求数据失败啦!");
										myChart.hideLoading();
									}
								});
						return arr;
					})()

				} ],
				yAxis : [ {
					type : 'value'
				} ],
				series : [
						{
							"name" : "月收入",
							"type" : "bar",
							"barGap" : "0",
							"data" : (function() {
								var arr = [];
								$
										.ajax({
											type : "post",
											async : false, //同步执行
											url : "chart",
											data : {
												"keyword" : keyword
											},
											dataType : "json", //返回数据形式为json
											success : function(result) {
												if (result) {
													for ( var i = 0; i < result[0].list1.length; i++) {

														/* console.log("第er组的第2个数据"+result[0].list1[i][2]); */
														arr
																.push(result[0].list1[i][2]);
													}
												}
											},
											error : function(errorMsg) {
												alert("图表请求数据失败啦!");
												myChart.hideLoading();
											}
										});
								return arr;
							})()

						},
						{
							"name" : "月支出",
							"type" : "bar",
							"barGap" : "0",

							"data" : (function() {
								var arr = [];
								$
										.ajax({
											type : "post",
											async : false, //同步执行
											url : "chart",
											data : {
												"keyword" : keyword
											},
											dataType : "json", //返回数据形式为json
											success : function(result) {
												if (result) {
													for ( var i = 0; i < result[0].list2.length; i++) {
														/*   console.log(result[0].list2.length+"第yi组的第2个数据"+result[0].list2[i][2]); */
														arr
																.push(result[0].list2[i][2]);
													}
												}
											},
											error : function(errorMsg) {
												alert("图表请求数据失败啦!");
												myChart.hideLoading();
											}
										});
								return arr;
							})()

						} ]

			};

			// 为echarts对象加载数据 
			myChart.setOption(option);
		}
	}
	cx();
</script>
<script src="<%=basePath%>javascript/a.js"></script>
</head>

<body class="app sidebar-mini rtl">

	<header class="app-header">
		<a class="app-header__logo" href="index.html">XLFD</a>
		<!-- Sidebar toggle button-->
		<a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
		<!-- 顶部右边目录-->
		<ul class="app-nav">
			<!-- 关键字查询会员信息 -->
			

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
				<li class="breadcrumb-item"><a href="#">报表管理</a></li>
				<li class="breadcrumb-item"><a href="#">利润报表</a></li>
			</ul>
			<div>
				<button type="button" class="btn btn-primary click"
					onclick="window.location.href='<c:url value="/mchart" />'">查看月份利润报表</button>
				<!-- <p>追寻风的气息  不顺畅  怎能飘逸 </p> -->
			</div>
		</div>
		<!-- 右边内容栏 -->

		<div class='col-sm-3'>
			<div class="form-group">

				<!--指定 date标记-->
				<div class='input-group date' id='datetimepicker'>
					<input type='text' class="form-control" id="selectdate" placeholder="请选择年份" value="" /> <span
						class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span> </span>

				</div>

			</div>
		</div>
		<div id="box" style="height: 400px; padding: 10px;"></div>
	</div>
	</main>


	<script src="<%=basePath%>javascript/popper.min.js"></script>
	<script src="<%=basePath%>javascript/main.js"></script>
</body>
</html>