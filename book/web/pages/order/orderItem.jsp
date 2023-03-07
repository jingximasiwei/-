<%@ page import="java.math.BigDecimal" %>
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
				<td>书名</td>
				<td>单价</td>
				<td>数量</td>
				<td>总价</td>
			</tr>

			<c:forEach items="${sessionScope.orderItems}" var="orderItems">

			<tr>
				<td>${orderItems.name}</td>
				<td>${orderItems.price}</td>
				<td>${orderItems.count}</td>
				<td>${orderItems.totalPrice}</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="2">订单内总共有${sessionScope.totalCount}件商品</td>
				<td colspan="2">订单总价为${sessionScope.totalPrice}元</td>
			</tr>
		</table>
		
	
	</div>

	<%-- 静态包含页脚部分--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>