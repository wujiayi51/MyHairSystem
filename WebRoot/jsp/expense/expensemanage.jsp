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
<title>店内开支</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="<%=basePath %>javascript/jquery.min.js"></script>
<!-- 模板表引入 -->

<!-- 主页面 CSS-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/main.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/bootstrap.css">

<!-- 字体图标 css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript">
        $(document).ready(function(){      	
            $(".click").click(function(){
                var ch = $(this).text();//点击控件上的文本，判断用户操作
                if(ch=="添加支出项目"){
                    $("#errorText").text("");
                    $(".tip2").fadeIn(200);
                    return;
                }
                  if(ch=="修改"){
                    $("#errorText").text("");
                    $(".tip3").fadeIn(200);
                    return;
                }
                 if(ch=="删除"){                   
                    $(".tip5").fadeIn(200);
                    return;
                }
                
            });
			//关闭、取消、确定按钮
            $(".tiptop a").click(function(){
                $(".tip3").fadeOut(200);
            });
             $(".tip5 a").click(function(){
                $(".tip5").fadeOut(200);
            });
            $(".cancel").click(function(){
                $(".tip3").fadeOut(100);
            });
			//修改
            $("#xg").click(function(){
                 var upstr=$("#upexpenseTime").val();
                 var upitem=$("#upitem").val();
                 var upitemExpense=$("#upitemExpense").val();
            	if(!isDateString(upstr))
            	{ 
            		alert("请输入正确日期格式，例：2013-03-06");
            	}
            	else if(upitem==""){
            		alert("请填写支出项目!");
            	}
            	else if(upitemExpense==""){
            		alert("请填写支出金额!");
            	}
            	else{
         			 $("#updateExpense_command").submit();
            	}

            });
            $(".tiptop2 a").click(function(){
               $(".tip2").fadeOut(200);
            }); 
            $("#qx").click(function(){
                $(".tip2").fadeOut(100);
            });
            //添加
            $("#tj").click(function(){
                 var str=$("#expenseTime").val();
                 var item=$("#item").val();
                 var itemExpense=$("#itemExpense").val();
            	if(!isDateString(str))
            	{ 
            		alert("请输入正确日期格式，例：2013-03-06");
            	}
            	else if(item==""){
            		alert("请填写支出项目!");
            	}
            	else if(itemExpense==""){
            		alert("请填写支出金额!");
            	}
            	else{
         			$("#expense_command").submit();
            	}
          
                  
               
			});
			
          
        });
        
        function xgExpense(id,item,itemExpense,expenseTime,remark){
    		$(".tip3").fadeIn(200);
    		var str = "<c:url value='/expense/'/>"+id+"/updateExpense";
            $("#updateExpense_command").attr("action",str);
    		$("#upid").val(id);
    		$("#upitem").val(item);
    		$("#upitemExpense").val(itemExpense);
    		$("#upexpenseTime").val(expenseTime);
    		$("#upremark").val(remark);		
    	}
    	
    	function isDateString(sDate){      
			var mp=/\d{4}-\d{2}-\d{2}/; 
			var matchArray = sDate.match(mp);     
			if (matchArray==null) return false;      
			var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31];     
			var iaDate = new Array(3);     
			var year, month, day;             
			iaDate = sDate.split("-");          
			year = parseFloat(iaDate[0])      
			month = parseFloat(iaDate[1])      
			day=parseFloat(iaDate[2])       
			if (year < 1900 || year > 2100) return false;       
			if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;       if (month < 1 || month > 12) return false;       
			if (day < 1 || day > iaMonthDays[month - 1]) return false;  
			return true;   
		}  
    	/* function celExpense(id){
    		   $.ajax({
				url : "<c:url value='/expense/'/>"+id+"/deteleExpense",
				type : "POST",
				
				success : function() {
					 alert("删除成功"); 		
				}
				});  
    	} */

    </script>
script src="<%=basePath %>javascript/a.js">
</script>
</head>

<body class="app sidebar-mini rtl">
	<!-- Navbar-->
	<header class="app-header">
		<a class="app-header__logo" href="index.html">XLFD</a>
		<!-- Sidebar toggle button-->
		<a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
		<!-- 顶部右边目录-->
		<ul class="app-nav">
			<!-- 关键字查询会员信息 -->
			<form class="app-search" action='<c:url value="/client/queryclientgrade"/>'>
				<input class="app-search__input" type="会员查询" placeholder="Search" name="keyword" />
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
		style="box-sizing:border-box; padding: 15px 0px 15px 15px;background-color: #fff;">
	<div style="margin-top:30px;background-color: #E5E5E5;height:100%;border-radius:15px 0px 0px 0px;">
		<div class="app-title" style="background-color:transparent;margin-bottom:0px;">

			<ul class="app-breadcrumb breadcrumb" style="font-size:15px;">
				<li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i>
				</li>&nbsp;&nbsp;
				<li class="breadcrumb-item"><a href="#">财务管理</a>
				</li>
				<li class="breadcrumb-item"><a href="#">支出账单</a>
				</li>
			</ul>
			<div>
				<button type="button" class="btn btn-primary click">添加支出项目</button>
			</div>
		</div>
		<!-- 右边内容栏 -->
		<div class="">
			<div class="col-md-12 col-lg-12 table-responsive-xs table-responsive-sm table-responsive-md">

				<table class="table  table-light table-hover" style="margin-top:20px;font-size:12px;">
					<thead>
						<tr class="btn-primary">
							<th>编号</th>
							<th>支出项目</th>
							<th>支出金额</th>
							<th>支出时间</th>
							<th>备注</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${expense_list}" var="currexpense">
							<tr>
								<td><c:out value="${currexpense.id }"></c:out></td>
								<td><c:out value="${currexpense.item }"></c:out></td>
								<td><c:out value="${currexpense.itemExpense }"></c:out></td>
								<td><c:out value="${currexpense.expenseTime }"></c:out>
								</td>
								<td><c:out value="${currexpense.remark }"></c:out></td>
								<td><a id="up1"
									href="javascript:xgExpense('<c:out value="${currexpense.id}"/>','<c:out value="${currexpense.item}"/>','<c:out value="${currexpense.itemExpense}"/>','<c:out value="${currexpense.expenseTime}"/>','<c:out value="${currexpense.remark}"/>')"
									class="tablelink">修改</a> <a
									href="<c:url value="/expense/${currexpense.id}/deteleExpense"/>" class="tablelink ">删除</a>
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
									<a href='<c:url value="/expense/addexpense?pageNum=${pageNum-1 }" />'><span
										class="pagepre page canpage"></span>
									</a>
								</c:when>
								<c:otherwise>
									<a href="#"><span class="pagepre page nopage"></span>
									</a>
								</c:otherwise>
							</c:choose></li>
						<li class="paginItem"><a href="Expensegrade?pageNum="></a></li>
						<li class="paginItem"><c:choose>
								<c:when test="${pageNum lt pageCount }">
									<a href='<c:url value="/expense/addexpense?pageNum=${pageNum+1 }" />'><span
										class="pagenxt page canpage"></span>
									</a>
								</c:when>
								<c:otherwise>
									<a href='#'><span class="pagenxt page nopage"></span>
									</a>
								</c:otherwise>
							</c:choose></li>
					</ul>
				</div>

				<!-- 添加支出账单 -->
				<div class="tip2 form">
					<div class="tiptop2">
						<span>添加支出项目</span><a></a>
					</div>
					<div class="formbody">
						<div id="usual1" class="usual">
							<div id="tab1" class="tabson">
								<form id="expense_command" class="form-group" action="<c:url value="/expense/addexpense"/>"
									method="POST">
									<ul class="forminfo">
										<li><label>项目名称<b>*</b>
										</label> <input id="item" name="item" style="width: 518px;" class="form-control" type="text"
											value="" /></li>
										<li><label>支出金额<b>*</b>
										</label> <input id="itemExpense" name="itemExpense" style="width: 518px;"
											class="dfinput form-control" type="text" value="" /></li>
										<li><label>支出时间<b>*</b>
										</label> <input id="expenseTime" name="expenseTime" style="width: 518px;"
											class="dfinput form-control" type="text" value="" /></li>
										<li><label>备注<b>*</b>
										</label> <textarea class="form-control" id="remark" name="remark" rows="3" value=""></textarea></li>
										<br />
										<li><label>&nbsp;</label>

											<button id="tj" type="button" class="btn btn-primary">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button id="qx" type="button" class="btn btn-primary">取消</button></li>
									</ul>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- 修改支出账单弹框 -->
				<div class="tip3">
					<div class="tiptop">
						<span>修改支出账单</span><a></a>
					</div>
					<div class="formbody" style="padding-top:0px;">
						<div id="usual1" class="usual">
							<div id="tab1" class="tabson">

								<form id="updateExpense_command" action="" method="POST">
									<ul class="forminfo">
										<li><label>编号<b>*</b>
										</label> <input id="upid" name="id" style="width: 518px;" class="form-control" type="text"
											value="" /></li>
										<li><label>项目名称<b>*</b>
										</label> <input id="upitem" name="item" style="width: 518px;" class="form-control" type="text"
											value="" /></li>
										<li><label>支出金额<b>*</b>
										</label> <input id="upitemExpense" name="itemExpense" style="width: 518px;"
											class="dfinput form-control" type="text" value="" /></li>
										<li><label>支出时间<b>*</b>
										</label> <input id="upexpenseTime" name="expenseTime" style="width: 518px;"
											class="dfinput form-control" type="text" value="" /></li>
										<li><label>备注<b>*</b>
										</label> <textarea class="form-control" id="upremark" name="remark" rows="2" value=""></textarea>
										</li>
										<li>
											<!-- <li><label style="width: 518px;"><b id="errorText"></b></label></li>
							 -->
										<li><label>&nbsp;</label>
											<button id="xg" class="btn btn-primary  sure">修改</button>
											<button class="btn btn-primary  cancel">取消</button>
										</li>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- 是否删除弹框 -->
				<%-- <div class="tip5 " style="position:absolute; top:30%;left:30%;">
    		<div class="tiptop" style="width:300px"><span>删除</span><a></a></div>
			<div  class="celbody">
			 
						<form id="updateExpense_command">
							
							<br/>
							<h4 id="id" name="id">&nbsp;&nbsp; &nbsp;是否确定删除？</h4>
							
							 <br/>
							 <label>&nbsp;</label>
							 
							 <a href="javascript:celExpense('<c:out value="${currexpense.id}"/>')" class="tablelink">是
							 <button  class=" btn btn-primary  sure">是</button>
							 </a>
							 <button  class="btn  btn-primary  cancel">否</button>
							 
					   </form>
			</div>
		</div>  --%>

			</div>
		</div>
	</main>

	
	<script src="<%=basePath%>javascript/popper.min.js"></script>
	<script src="<%=basePath%>javascript/bootstrap.min.js"></script>
	<script src="<%=basePath%>javascript/main.js"></script>
</body>
</html>