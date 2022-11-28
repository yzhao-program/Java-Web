<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Successfully</title>

	<%-- include base tag, css, jquery --%>
	<%@ include file="/pages/common/head.jsp"%>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
			<%@ include file="/pages/common/logo_image.jsp"%>

			<%@ include file="/pages/common/menu_login_success.jsp"%>
		</div>
		
		<div id="main">
		
			<h1>Register Successfully!&nbsp;&nbsp;&nbsp;<a href="index.jsp">Go to the Home Page</a></h1>

		</div>

		<%-- include footer --%>
		<%@include file="/pages/common/footer.jsp"%>

</body>
</html>