<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%-- 静态包含base标签，css样式，jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
		<%-- 静态包含，登录成功之后的画面--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>		
			<c:forEach items="${sessionScope.orders}" var="order">
			<tr>
				<td>${order.orderId}</td>
				<td>${order.createTime}</td>
				<td>${order.price}</td>
				<c:if test="${order.status==0}">
				<td>未发货</td>
				</c:if>
				<c:if test="${order.status==1}">
					<td>已发货</td>
				</c:if>
				<c:if test="${order.status==2}">
					<td>已签收</td>
				</c:if>
				<td><a href="orderServlet?action=queryOrderItemsByOrderId&orderId=${order.orderId}">查看详情</a></td>
			</tr>

			</c:forEach>
		</table>
		
	
	</div>

	<%-- 静态包含页脚部分--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>