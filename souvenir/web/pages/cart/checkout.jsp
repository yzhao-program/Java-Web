<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Out</title>

	<%-- include base tag, css, jquery --%>
	<%@ include file="/pages/common/head.jsp"%>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
		<%@ include file="/pages/common/logo_image.jsp"%>
		<span class="wel_word">&nbsp;Check Out</span>
		<%@ include file="/pages/common/menu_login_success.jsp"%>
	</div>
	
	<div id="main">
		<h1>Thank you for your purchase. Your order Id is: ${sessionScope.orderId}</h1>
	</div>

	<%-- include footer --%>
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>