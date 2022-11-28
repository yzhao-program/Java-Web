<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>

	<%-- include base tag, css, jquery --%>
	<%@ include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {

			$("a.deleteItem").click(function () {
				return confirm("Are you sure to delete [" + $(this).parent().parent().find("td:first").text() +"] ?")
			});

			$("#clearCart").click(function () {
				return confirm("Are you sure to clear shopping cart?");
			});

			$(".updateCount").change(function () {

				let name = $(this).parent().parent().find("td:first").text();
				let id = $(this).attr('souvenirId');

				let count = this.value;
				if (count <= 0) {
					alert("Your input is not valid.");
					this.value = this.defaultValue;
					return;
				}
				if ( confirm("Are you sure to update the number of [" + name + "] to：" + count + " ?") ) {

					location.href = "${pageScope.basePath}cartServlet?action=updateCount&count="+count+"&id="+id;
				} else {
					this.value = this.defaultValue;
				}
			});

		});
	</script>


</head>
<body>
	<div id="header">
		<%@ include file="/pages/common/logo_image.jsp"%>
		<span class="wel_word">&nbsp;Cart</span>
		<%@ include file="/pages/common/menu_cart.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>Name</td>
				<td>Quantity</td>
				<td>Unit Price</td>
				<td>Subtotal Price</td>
				<td>Operations</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<%--if the cart is empty--%>
				<tr>
					<td colspan="5"><a href="index.jsp">Your shopping cart is empty. Go shopping.</a> </td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<%--if the cart is not empty--%>
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" style="width: 80px;"
								   souvenirId="${entry.value.id}"
								   type="text" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<%--Display the contents if the cart is not empty--%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">There are<span class="b_count">${sessionScope.cart.totalCount}</span>item(s) in the cart.</span>
				<span class="cart_span">Total Amount:<span class="b_price">${sessionScope.cart.totalPrice}</span>dollars</span>
				<br/>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">Clear Cart</a></span>
				<span class="cart_span"><a href="client/orderServlet?action=createOrder">Check Out</a></span>
			</div>
		</c:if>
	</div>

	<%-- include footer --%>
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>