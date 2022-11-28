<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

	<%-- include base tag, css, jquery --%>
	<%@ include file="/pages/common/head.jsp"%>


</head>
<body>
		<div id="header">
			<%@ include file="/pages/common/logo_image.jsp"%>
			<span class="wel_word">&nbsp;LOGIN</span>
			<%@ include file="/pages/common/menu_register_login.jsp"%>
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">Welcome</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>Login</h1>
								<a href="pages/user/register.jsp">Don't have an Account? Go to Register</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
									${ empty requestScope.msg ? "Please input username and password":requestScope.msg }
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="login" />
									<table>
										<tr>
											<td><label>Username: </label></td>
											<td>
												<input class="itxt" type="text" placeholder="Please input username"
													   autocomplete="off" tabindex="1" name="username"
													   value="${requestScope.username}" />
											</td>
										</tr>
										<tr>
											<td><label>Password: </label></td>
											<td>
												<input class="itxt" type="password" placeholder="Please input password"
													   autocomplete="off" tabindex="1" name="password" />
											</td>
										</tr>
									</table>
									<br/>
									<br/>
									<input type="submit" value="Login" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>

		<%-- include footer --%>
		<%@include file="/pages/common/footer.jsp"%>


</body>
</html>