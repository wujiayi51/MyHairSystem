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
<title>项目报表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="<%=basePath %>javascript/jquery.min.js"></script>
<!-- 主页面 CSS-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/main.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/style.css">

<!-- 时间选择器 -->
<link href="<%=basePath%>styles/bootstrap.css" rel="stylesheet" />
<link href="<%=basePath%>styles/bootstrap-datetimepicker.css" rel="stylesheet" />
<script src="<%=basePath%>javascript/bootstrap.js"></script>
<script src="<%=basePath%>javascript/moment-with-locals.min.js"></script>
<script src="<%=basePath%>javascript/bootstrap-datetimepicker.js"></script>

<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">
// 路径配置
	require.config({
		paths : {
			echarts : 'http://echarts.baidu.com/build/dist'
		}
	});
	// 使用
 	   require([ 'echarts', 'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
	],drewEcharts);  
	function drewEcharts(ec) {
		// 基于准备好的dom，初始化echarts图表
		
		var myChart = ec.init(document.getElementById('box'));
		
		var obj = document.getElementById("selServe");
		
			var index = obj.selectedIndex;
			
			var utid = obj.options[index].value;

			var start = $("#start").val();
			var end = $("#end").val();
			//var starttime=start.slice(4,6);
			//var endtime=end.slice(4,6);  
			
			
		var option = {
			tooltip : {
				show : true
			},
			title : {
				show : true,
				text : '该项目的总收入变化'
			},
			legend : {
				data : [ '收入' ]
			},
			xAxis : [ {
				name : '月',
				type : 'category',
			    data :
				(function() {
				  
					var arr = [];
					$.ajax({
						type : "post",
						async : false, //同步执行
						url : "monthchart",
						data : {
						
						},
						dataType : "json", //返回数据形式为json
						success : function(result) {
							if (result) {
								for ( var i = 0; i < result.length; i++) {
									console.log(result[i][0]);
									arr.push(result[i][0]);
								}
							}

						},
						error : function(errorMsg) {
							alert("不好意思，图表请求数据失败啦!");
							myChart.hideLoading();
						}
					});
					return arr;
				})()

			} ],
			yAxis : [ {
				type : 'value',
				name : '收入',
			} ],
			series : [ {
				"name" : "收入",
				"type" : 'line',
				
				"data" : (function() {
					var arr = [];
					$.ajax({
						type : "post",
						async : false, //同步执行
						url : "monthchart",
						data : {
						
						},
						dataType : "json", //返回数据形式为json
						success : function(result) {
							if (result) {
								for ( var i = 0; i < result.length; i++) {
									console.log(result[i][1]);
									arr.push(result[i][1]);
								}
							}
						},
						error : function(errorMsg) {
							alert("不好意思，大爷，图表请求数据失败啦!");
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
	
	
</script>
<script type="text/javascript">

	$(document).ready(function() {

        $("#datetimepicker").datetimepicker({
            format: "yyyymm",
            weekStart: 1,
            autoclose: true,
            startView: 3,
            minView: 3,
            forceParse: false, 
        	endDate: new Date()	,		// 窗口可选时间从今天开始 
            pickerPosition: "bottom-left"
        });

         $("#datetimepicker2").datetimepicker({
            format: "yyyymm",
            weekStart: 1,
            autoclose: true,
            startView: 3,
            minView: 3,
            forceParse: false, 
        	endDate: new Date()	,		// 窗口可选时间从今天开始 
            pickerPosition: "bottom-left"
        }); 
         $("#selServe").change(function() {
			var obj = document.getElementById("selServe");
			var index = obj.selectedIndex;
			var utid = obj.options[index].value;
			var start = $("#start").val();
			var end = $("#end").val();
			
			// 路径配置
			require.config({
				paths : {
					echarts : 'http://echarts.baidu.com/build/dist'
				}
			});
			// 使用
		 	 require([ 'echarts', 'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
			],drewEcharts); 
			function drewEcharts(ec) {
				// 基于准备好的dom，初始化echarts图表
				
				var myChart = ec.init(document.getElementById('box'));
				
				var obj = document.getElementById("selServe");
				var index = obj.selectedIndex;
				var utid = obj.options[index].value;
				var start = $("#start").val();
				var end = $("#end").val();
					
				var option = {
					tooltip : {
						show : true
					},
					title : {
						show : true,
						text : '该项目的总收入变化'
					},
					legend : {
						data : [ '收入' ]
					},
					xAxis : [ {
						name : '月',
						type : 'category',
					    data :
						(function() {
						  
							var arr = [];
							$.ajax({
								type : "post",
								async : false, //同步执行
								url : "monthchart",
								data : {
								"startmonth":start,
								"endmonth":end,
								"servename":utid,
								},
								dataType : "json", //返回数据形式为json
								success : function(result) {
									if (result) {
										for ( var i = 0; i < result.length; i++) {
											console.log(result[i][0]);
											arr.push(result[i][0]);
										}
									}
		
								},
								error : function(errorMsg) {
									alert("不好意思，图表请求数据失败啦!");
									myChart.hideLoading();
								}
							});
							return arr;
						})()
		
					} ],
					yAxis : [ {
						type : 'value',
						name : '收入',
					} ],
					series : [ {
						"name" : "收入",
						"type" : 'line',
						"data" : (function() {
							var arr = [];
							$.ajax({
								type : "post",
								async : false, //同步执行
								url : "monthchart",
								data : {
								"startmonth":start,
								"endmonth":end,
								"servename":utid,
								},
								dataType : "json", //返回数据形式为json
								success : function(result) {
									if (result) {
										for ( var i = 0; i < result.length; i++) {
											console.log(result[i][1]);
											arr.push(result[i][1]);
										}
									}
								},
								error : function(errorMsg) {
									alert("不好意思，大爷，图表请求数据失败啦!");
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
			
			/* require([ 'echarts', 'echarts/chart/line' ],drewEcharts); // 使用柱状图就加载bar模块，按需加载 */

		});
	});
  
	
</script>
<script src="<%=basePath %>javascript/a.js"></script>
</head>

<body class="app sidebar-mini rtl">
	<!--  style="overflow:-Scroll;overflow-y:hidden" -->
	<!-- Navbar-->
	<header class="app-header">
		<a class="app-header__logo" href="index.html">XLFD</a>
		<!-- Sidebar toggle button-->
		<a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
		<!-- 顶部右边目录-->
		<ul class="app-nav">
			
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
				<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i>
				</li>&nbsp;&nbsp;
				<li class="breadcrumb-item"><a href="#">报表管理</a></li>
				<li class="breadcrumb-item"><a href="#">项目报表</a></li>
			</ul>
			<div>
				<button type="button" class="btn btn-primary click"
					onclick="window.location.href='<c:url value="/echars/chart" />'">查看年度项目报表</button>
				<!-- <p>追寻风的气息  不顺畅  怎能飘逸 </p> -->
			</div>

		</div>

		<div class='col-sm-3'>
			<div class="form-group">
				<!--指定 date标记-->
				<div class='input-group date' id='datetimepicker'>
					<input id="start" type='text' class="form-control" placeholder="选择起始年月" name="keyword" /> <span
						class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span> </span>
				</div>
			</div>
		</div>
		<div class='col-sm-3'>
			<div class="form-group">
				<!--指定 date标记-->
				<div class='input-group date' id='datetimepicker2'>
					<input id="end" type='text' class="form-control" placeholder="选择终止年月" name="keyword" /> <span
						class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span> </span>
				</div>
			</div>
		</div>
		<div class='col-sm-3'>
			<select id="selServe" class="bs-select form-control " data-live-search="true"
				style="height:35px;">
				<option>请选择服务项目</option>
				<c:forEach items="${sname_list}" var="currSname">
					<option value="${currSname.sId }">
						<c:out value="${currSname.sName }" />
					</option>
				</c:forEach>
			</select>
		</div>



		<!-- 右边内容栏 -->
		<div id="box" style="height: 400px; border: 1px solid #ccc; padding: 10px;"></div>
	</div>
	</main>

	<script src="<%=basePath%>javascript/popper.min.js"></script>
	<script src="<%=basePath%>javascript/main.js"></script>
	
</body>
</html>