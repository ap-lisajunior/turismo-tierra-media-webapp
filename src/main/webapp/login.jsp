<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
<!-- Custom Theme files -->
<link href="assets/stylesheets/login.css" rel="stylesheet"
	type="text/css" media="all" />
<!-- //Custom Theme files -->
</head>

<body>
	<!-- main -->
	<div class="main-agileinfo slider ">
		<div class="items-group">
			<div class="item agileits-w3layouts">
				<div class="block text main-agileits">
					<span class="circleLight"></span>
					<!-- login form -->
					<div class="login-form loginw3-agile">
						<div class="agile-row">
							<h1>Turismo en la Tierra Media</h1>

							<c:if test="${flash != null}">
								<div class="alert alert-danger">
									<p>
										<c:out value="${flash}" />
									</p>
								</div>
							</c:if>

							<div class="login-agileits-top">
								<form action="login" method="post">
									<p>Usuario</p>
									<input type="text" class="name" name="username" />
									<p>Contraseña</p>
									<input type="password" class="password" name="password" /> <label
										class="anim"> <input type="checkbox" class="checkbox">
										<span> Recordarme ?</span>
									</label> <input type="submit" value="Ingresar">
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="w3lsfooteragileits">
					<p>
						&copy; 2021 Turismo en la Tierra Media. All Rights Reserved |
						Design by <a href="https://github.com/ap-lisajunior"
							target="=_blank">Lisa junior</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- //main -->
</body>
</html>


