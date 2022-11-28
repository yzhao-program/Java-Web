<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>

	<%-- include base tag, css, jquery --%>
	<%@ include file="/pages/common/head.jsp"%>
	<Script type="text/javascript">
		$(function () {

			$("button.addToCart").click(function () {

				let souvenirId = $(this).attr("souvenirId");
				// location.href = "http://localhost:8080/souvenir/cartServlet?action=addItem&id=" + souvenirId;

				$.getJSON("${pageScope.basePath}cartServlet","action=ajaxAddItem&id=" + souvenirId,function (data) {
					$("#cartTotalCount").text("There are " + data.totalCount + " item(s) in your cart.");
				})
			});
		});
	</Script>

</head>
<body>
	
	<div id="header">
		<%@ include file="/pages/common/logo_image.jsp"%>
		<span class="wel_word">&nbsp;Souvenir Store</span>
		<%@ include file="/pages/common/menu_homepage.jsp"%>
	</div>

	<div id="main">
		<div id="souvenir">
			<div class="souvenir_cond">
				<form action="client/souvenirServlet" method="get">
					<input type="hidden" name="action" value="pageByPrice">
					Price：<input id="min" type="text" name="min" value="${param.min}"> dollar -
						<input id="max" type="text" name="max" value="${param.max}"> dollar
						<input type="submit" value="Search" />
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${empty sessionScope.cart.items}">
					<%--if the cart is empty--%>
					<span style="color: red" id="cartTotalCount">Your Shopping Cart is Empty.</span>
				</c:if>
				<c:if test="${not empty sessionScope.cart.items}">
					<%--if the cart is not empty--%>
					<span style="color: red" id="cartTotalCount">There are ${sessionScope.cart.totalCount} item(s) in your cart.</span>
				</c:if>
			</div>

			<c:forEach items="${requestScope.page.items}" var="souvenir">
			<div class="b_list">
				<div class="img_div">
					<img class="souvenir_img" alt="" src="${souvenir.imgPath}" />
				</div>
				<div class="souvenir_info">
					<div class="souvenir_name">
						<span class="sp1">Name: </span>
						<span class="sp2">${souvenir.name}</span>
					</div>
					<div class="souvenir_price">
						<span class="sp1">Price: </span>
						<span class="sp2">$${souvenir.price}</span>
					</div>
					<div class="souvenir_sales">
						<span class="sp1">Sales:</span>
						<span class="sp2">${souvenir.sales}</span>
					</div>
					<div class="souvenir_add">
						<button souvenirId="${souvenir.id}" class="addToCart">Add into Cart</button>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>

		<%-- include page_nav --%>
		<%@include file="/pages/common/page_nav.jsp"%>

	</div>

	<%-- include footer --%>
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>