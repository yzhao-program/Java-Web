<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Register</title>

	<%-- base tag, css, jQuery --%>
	<%@ include file="/pages/common/head.jsp"%>


	<script type="text/javascript">
		// After loading web page
		$(function () {

			// verify username
			$("#username").blur(function () {

				let username = this.value;

				let usernamePattern = /^\w{5,20}$/;

				if (!usernamePattern.test(username)) {

					$("span.errorMsg").text("Username is invalid. (Need 5-20 words)");

					return;
				}

				$.getJSON("${pageScope.basePath}userServlet","action=ajaxExistsUsername&username=" + username,function (data) {
					if (data.existsUsername) {
						$("span.errorMsg").text("This username already exists.");
					} else {
						$("span.errorMsg").text("This username is valid.");
					}
				});
			});

			// change the code
			$("#code_img").click(function () {

				this.src = "${pageScope.basePath}kaptcha.jpg?d=" + new Date();
			});

			// verify information after clicking on the button
			$("#sub_btn").click(function () {

				let usernameText = $("#username").val();

				let usernamePattern = /^\w{5,20}$/;

				if (!usernamePattern.test(usernameText)) {

					$("span.errorMsg").text("Username is invalid. (Need 5-20 words)");

					return false;
				}


				let passwordText = $("#password").val();

				let passwordPatt = /^\w{5,32}$/;

				if (!passwordPatt.test(passwordText)) {

					$("span.errorMsg").text("Password is invalid. (Need 5-32 words)");

					return false;
				}


				let repwdText = $("#repwd").val();

				if (repwdText != passwordText) {

					$("span.errorMsg").text("These passwords didn't match.");

					return false;
				}


				let emailText = $("#email").val();

				let emailPattern = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;

				if (!emailPattern.test(emailText)) {

					$("span.errorMsg").text("Email is invalid.");

					return false;
				}


				let codeText = $("#code").val();

				codeText = $.trim(codeText);

				if (codeText == null || codeText == "") {
					$("span.errorMsg").text("Code cannot be empty.");

					return false;
				}

				// erase the message when information is valid
				$("span.errorMsg").text("");

			});

		});

	</script>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
</head>
<body>
<div id="header">
	<%@ include file="/pages/common/logo_image.jsp"%>
	<span class="wel_word">&nbsp;REGISTER</span>
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
					<h1>Register</h1>

				</div>
				<div class="msg_cont">
					<b></b>
					<span class="errorMsg">
						${ empty requestScope.msg ? "Please register an account":requestScope.msg }
					</span>
				</div>
				<div class="form">
					<form action="userServlet" method="post">
						<input type="hidden" name="action" value="register">
						<table>
							<tr>
								<td><label>Username: </label></td>
								<td colspan="2">
									<input class="itxt" type="text" placeholder="Please input username"
									   value="${requestScope.username}"
									   autocomplete="off" tabindex="1" name="username" id="username" />
								</td>
							</tr>

							<tr>
								<td><label>Password: </label></td>
								<td colspan="2">
									<input class="itxt" type="password" placeholder="Please input password"
										   autocomplete="off" tabindex="1" name="password" id="password" />
								</td>
							</tr>

							<tr>
								<td><label>Confirm: </label></td>
								<td colspan="2">
									<input class="itxt" type="password" placeholder="Confirm"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
								</td>
							</tr>

							<tr>
								<td><label>Email: </label></td>
								<td colspan="2">
									<input class="itxt" type="text" placeholder="Please input email address"
										   value="${requestScope.email}"
										   autocomplete="off" tabindex="1" name="email" id="email" />
								</td>
							</tr>

							<tr>
								<td><label>Code: </label></td>
								<td>
									<input class="itxt" type="text" name="code" style="width: 80px;" id="code" />
								</td>
								<td>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px; height: 30px;">
								</td>
							</tr>
						</table>
						<br/>
						<br/>
						<input type="submit" value="Register" id="sub_btn" />
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