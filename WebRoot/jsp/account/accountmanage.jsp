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
<script type="text/javascript"
	src="<%=basePath%>javascript/jquery.min.js"></script>
<!-- 模板表引入 -->

<!-- 主页面 CSS-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>styles/main.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>styles/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>styles/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>styles/check.css">
<!-- 字体图标 css-->
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript">

//	定义一个全局变量
var cid="";//全局变量能够让充值使用
//var allMoney = 0;
var delMoney = 0;
function removeAll(del){
	var self = $(del).parent().eq(0);
	delMoney = self.next("tr").children().eq(0)[0].childNodes[1].value;
	console.log(delMoney);
	self.prev("tr").remove();
	self.next("tr").remove();
	self.remove();
	 //计算总共要支付的钱
	 var len = $(".realMoney").length;
	 var allMoney = 0;
	 for(var i=0 ;i < len;i++){
	  allMoney = allMoney+parseFloat($(".realMoney").eq(i).val());
	 }
	 $("#expense").text(allMoney.toFixed(1)); 
}
	
$(document).ready(function() {
	//获取屏幕的高度
	var high = ($(window).height())*0.4;
	$("#table tbody").height(high);
	
	//
	var myDate = new Date();
	currentTime=myDate.toLocaleDateString();     //获取当前日期
	$("#currentTime").text(currentTime);
	//chrome、firefox兼容下拉框触动
	$("#selname").change(function(){
		btnClick();
		$("#selname option:eq(0)").prop("selected",true);//jquery版本问题有的用prop或attr
	});
	//ff下拉框值改变触发  通过服务名字查服务价格并显示	  				 
	/* $("#selname option").click(function() {	
		btnClick();
	});  */
	
	//下拉框触动函数
	function btnClick(){
		var index = $("#selname option:selected").val();
		var text = $("#selname option:selected").text();
		$.ajax({
			url : "<c:url value='/account/ajaxtest'/>",
			type : "POST",
			dataType : 'json',
			data : {
				"sid" : index
			},
			success : queryFunction,
			error: function() {
				alert("项目加载失败，请正确操作!");
				}
			});
		
		function queryFunction(result) {  
			//alert("step1");
			var json = eval(result); //数组  
			var currUser = json[0].map1;
			$.each(json, function (index, item) {  				   
				var sName = json[index].sName;
				var sId = json[index].sId;  
				var sPrice = json[index].sPrice;
				//追加内容开始
				var str = '<tr>'+
						'<th>项目名称</th>'+
						'<th>价格</th>'+
						'<th>员工</th>'+
						'<th></th>'+
					'</tr>'+
					'<tr class="table-bordered trset">'+
						'<td id="sName" >'+sName+'<i class="hiddenSid" style="display:none">'+sId+'</i></td>'+
						'<td id="sPrice" >'+sPrice+'</td>'+
						'<td>'+
							'<label>员工</label>'+
							'<select class="seluser emp" id="uId" name="belongUser.uId" >'+
							'<option vlaue="default" disabled selected="selected">请选择</option>'+
								//追加option
							'</select>'+ 
							'<br/>'+ 
							'<label>类型：</label><input  class="utName coinput"  value="" type="text" >'+
							'<label>加权系数：</label><input class="utWeight coinput"  value="" type="text">'+
							'<label>提成比例：</label><input class="utScale coinput"  value="" type="text">'+
						'</td>'+
						'<td onclick="removeAll(this)" >删除</td>'+
					'</tr>'+
					'<tr class="qutop">'+
						'<td >'+
							'<label>原价</label><input type="text" name="shouldMoney" class="shouldMoney" value="'+sPrice+'" class="coinput">'+ 
							'<label>现价</label><input type="text" name="realMoney" class="realMoney"  value="" class="coinput">'+
							'<label>折扣</label><input type="text"  class="cDiscount"  value="" class="coinput">'+  
						'</td>'+
						'<td>'+
							'<label>会员余额</label><input  value="" class="cBala coinput">'+ 
						'</td>'+	
					'</tr>';
					$("#tby").append(str);
					//遍历map集合
					for(var key in currUser){
						$(".emp:last").append('<option class="uId" value="'+key+'">'+currUser[key]+'</option>');
					}
					//赋值折扣给后面的表格使用
					if($(".cDiscount:first").val()){
						$(".cDiscount:last").val($(".cDiscount:first").val());
					}
					if($(".cBala:first").val()){
						$(".cBala:last").val($(".cBala:first").val());
					}
				});	
				//追加内容结束 		
			}// queryFunction结束
	}
	//会员 折扣 余额 信息

	 $("#cId").blur(function(){ 
		clientFun();
	}); 
	$("#cId").keydown(function(e) {//给输入框绑定按键事件
            var e = e ||event;
            var currKey = 0;
            currKey=e.keyCode||e.which||e.charCode;
			if (currKey == "13") {
				//调用
				clientFun();
			}
		});
	
	function clientFun(){
		var cId = $("#cId").val();
		cid=cId;
		$.ajax({
			url :  "<c:url value='/account/ajaxClient'/>",
			type : "POST",
			dataType : 'json',
			data : {
				"cId" : cId
			},
			success : clientFunction ,
		    error: function() {
				alert("请正确操作！");
			}
		});
		
		function clientFunction(result) {  
			var json = eval(result); //数组  
			$.each(json, function (index, item) {  
			  var cDiscount = json[index].cDiscount;  
			  var cBala = json[index].cBala;
			  var querycId = json[index].cId;   
			  $("#infotip2").text("");
			  var inputcid = $("#cId").val();
			  if(inputcid==""){
			  	 $("#infotip2").text("");
			  }
			  if(querycId==null&&inputcid!=""){
			  	 $("#infotip2").text("此账号不存在或输入有误！");
			  	 return false;
			  }else{
				//该会员不能打折cDiscount=0；
			     if(!cDiscount){
			     	$(".cDiscount:last").val(1);
			     	$(".cBala:last").val(cBala);
			     }else{
				  	  $(".cDiscount:last").val(cDiscount);
				  	  $(".cBala:last").val(cBala);
			  	 }	
			  }
			  
			});  		
		}
	}  
	
	//chrome、firefox查员工  根据姓名查，将uId存入数据库
	 $("#tby").on("change",".seluser",function(){
	 	var text = $(this).find("option:selected").text();
		userBtn(text);
		$(this).attr("disabled","disabled");
	}); 
	//firefox查员工  根据姓名查，将uId存入数据库
	/*  $("#tby").on("click",".seluser option",function(){
	 	var text =  $(this).text();
		userBtn(text);
	});  */
	//被调用的员工方法
	function userBtn(text){
		//var text =  $(this).text();
		console.log(text);
		$.ajax({
			url : "<c:url value='/account/user'/>",
			type : "POST",
			dataType : 'json',
			data : {
				"uname" : text
			},
			success : queryutFunction,
			error: function() {
				alert("请正确操作！");
				}
			});
		
		function queryutFunction(result) {  
	
			var json = eval(result); //数组  
			console.log(json[0]);
			$.each(json, function (index, item) {  
			   
			  utName = json[index].utName;  
			  utScale = json[index].utScale;  				  
			  utWeight = json[index].utWeight;
			  				 
			  $(".utName:last").val(utName);
			  $(".utScale:last").val(utScale);
			  $(".utWeight:last").val(utWeight);
			  var yuan = $(".shouldMoney:last").val();
			  var sprice = yuan*utWeight;
			  $(".shouldMoney:last").val(sprice.toFixed(1));
			   //显示打折后实付金额，触发事件
			  var clientExist = $("#cId").val();
			  if(clientExist){
				  cDiscount =$(".cDiscount:last").val();
				  shouldMoney =$(".shouldMoney:last").val();
				  realMoney=shouldMoney*cDiscount*utWeight;
			  }else{
			  	  //不是会员不打折；
			  	  $(".cDiscount:last").val(1);
				  shouldMoney =$(".shouldMoney:last").val();
				  realMoney=shouldMoney*utWeight;
			  }
			  $(".realMoney:last").val(realMoney.toFixed(1));
			   //计算总共要支付的钱
			  var len = $(".realMoney").length;
			  var allMoney = 0;
			  for(var i=0 ;i < len;i++){
				  allMoney = allMoney+parseFloat($(".realMoney").eq(i).val());
			  }
			  $("#expense").text(allMoney.toFixed(1)); 
			});  		
		} 
	}
	//结算
	//结算通过ajax提交数据：因为append追加内容在html编译中没有出现，所以只能通过ajax
	$("#infotip").css("display","none");
	$("#check_money").click(function(){
		
		//如果余额不足，系统给与提示,请充值
		var totalCharge = $("#expense").text(); //支付
		var cbala =   $(".cBala:last").val();	//余额

		if(cbala!=null && cbala!="" && totalCharge*1>cbala*1){//string变为数值类型
			$("#infotip").css("display","block");
			return false;
		}
		//将数据封装成对象
		var arr = [];
		var len = $(".realMoney").length;
		//无服务项目
		if(len==0){
			alert("请选择服务项目！");
			return false;
		}
		var cId = $("#cId").val();
		for(var j = 0;j<len;j++){
			var uId = $(".uId").eq(j).val();
			var sId = $(".hiddenSid").eq(j).text();
			var realMoney = $(".realMoney").eq(j).val();
			var shouldMoney = $(".shouldMoney").eq(j).val();
			var row = {};
			row.cId=cId;
			row.uId=uId;
			row.sId=sId;
			row.realMoney=realMoney;
			row.shouldMoney=shouldMoney;
			arr.push(row);
		}
		 	var account = JSON.stringify(arr);
		$.ajax({
			url :  "<c:url value='/account/check'/>",
			type : "POST",
			dataType : 'json',
			data : {
				"doList":account
			},
			success : checkFunction ,
		    error: function() {
				alert("请正确操作！");
			}
		});
		
		function checkFunction(result) {  
			alert("结算成功");
			window.location.href='<c:url value="/account/addaccount"/>';
		}
	});
		  
});
//会员充值
function clientCharge(){
	var str="<c:url value='/client/clientcharge?id='/>"+cid;
	window.open(str,"_self");
}   
 
</script>

<script src="<%=basePath%>javascript/a.js"></script>

</head>

<body class="app sidebar-mini rtl" style="overflow:Scroll;overflow-y:hidden">

	<!-- Navbar-->
	<header class="app-header">
		<a class="app-header__logo" href="index.html">XLFD</a>
		<!-- Sidebar toggle button-->
		<a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
			aria-label="Hide Sidebar"></a>
		<!-- 顶部右边目录-->
		<ul class="app-nav">
			<!-- 关键字查询会员信息 -->
			<%-- <form class="app-search" action='<c:url value="/account/queryaccountbill"/>'>
				<input class="app-search__input" type="date" placeholder=" " name="keyword" />
				<button class="app-search__button"><i class="fa fa-search"></i></button>
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
	<div style="margin-top:30px;background-color: #E5E5E5;height:100%;border-radius:15px 0px 0px 0px;position:relative">
		<div class="app-title" style="background-color:transparent;margin-bottom:0px;">

			<ul class="app-breadcrumb breadcrumb" style="font-size:15px;">
				<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i>
				</li>&nbsp;&nbsp;
				<li class="breadcrumb-item"><a href="#">收银管理</a>
				</li>
				<li class="breadcrumb-item"><a href="#">现在收银</a>
				</li>
			</ul>
			<div>
				<a href="javascript:void(0);" onclick="clientCharge()" ><button  type="button" class="btn btn-primary click" target="_self">会员充值</button></a>
			</div>
		</div>
		<!-- 右边内容栏 -->
		<div class="check_form">
			<div class="col-md-12 col-lg-12 table-responsive-xs table-responsive-sm table-responsive-md">
				<div  id="account_command " class="table-responsive">
					<table id="table" class="table  table-light table-hover"
						style="margin-top:20px;font-size:12px;">
						
							<thead>
								<tr class="btn-primary">
									<th>
										<label>服务项目</label> 
										<select id="selname" name="belongServe.sName" style="width:120px;color:grey" >
											<option value="default" selected="selected" disabled>请选择</option>
											<c:forEach items="${serve_list}" var="currServe">
												<option  value="${currServe.sId }" class="sId"><c:out value="${currServe.sName}"/></option>
											</c:forEach>
										</select>
									</th>
									<th>
										<label >会员编号</label><input id="cId" name="cId" type="text"  style="color:grey"><i style="color:#ea2020;" id="infotip2"></i>
									</th>
								</tr>
							</thead>
							
							<tbody id="tby" >
							<!-- 定义模板 -->
							
							
							</tbody>
							
						
					</table>
				</div>
				<div class="pay" id="pay">
					<div style="font-size:20px;float:left;">共计：<br/>
						<label style="font-size:20px">￥<i style="font-size:20px" id="expense"></i></label>
						
					</div>	
					<label id="infotip" style="display:none;color:#ea2020;">余额不足，请充值!</label>					
					<div class="click" id="check_money" ><img src="<%=basePath%>images/pickmoney.png"/></div>	
				</div>
			</div>
		</div>
	</div>
	</main>

	<script src="<%=basePath%>javascript/popper.min.js"></script>
	<script src="<%=basePath%>javascript/bootstrap.min.js"></script>
	<script src="<%=basePath%>javascript/main.js"></script>

</body>
</html>