<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%-- 静态包含base标签，css样式，jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("a.deleteItem").click(function (){
				var bookName=$(this).parent().parent().find("td:first").text();
				return confirm("你确定要删除【"+bookName+"】吗？");
			});
			$("#clearItem").click(function (){
				return confirm("你确定要清空购物车吗？");
			});
			$(".updateCount").change(function (){
				var name=$(this).parent().parent().find("td:first").text();
				var id=$(this).attr("bookId");
				var count=this.value;
				if(confirm("你确定要修改"+name+"的数量为"+count+"吗?")){
					if(count!=0){
						location.href = "http://localhost:8080/book/cartServlet?action=updateCount&id="+id+"&count="+count;
					}else{
						location.href = "http://localhost:8080/book/cartServlet?action=delete&id="+id;
					}
				}else {
					this.value=this.defaultValue;
				}
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%-- 静态包含，登录成功之后的画面--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">


		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<%--购物车为空				--%>
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空!跟小伙伴去购物吧!!!</a></td>
				</tr>
			</c:if>
			<c:forEach items="${sessionScope.cart.items}" var="entry" >
			<tr>
				<td>${entry.value.name}</td>
				<td>
					<input type="text" style="width:80px;text-align: center"
						   bookId="${entry.value.id}"
						   class="updateCount"
						   value="${entry.value.count}">
				</td>
				<td>${entry.value.price}</td>
				<td>${entry.value.count*entry.value.price}</td>
				<td><a class="deleteItem" href="cartServlet?action=delete&id=${entry.value.id}">删除</a></td>
			</tr>
			</c:forEach>
			
		</table>

		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span id="clearItem" class="cart_span"><a href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%-- 静态包含页脚部分--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>