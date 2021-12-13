<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="partials/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="partials/nav.jsp"></jsp:include>

	<main class="container">
		<div class="bg-light p-4 rounded">
			<h1>
				¡Bienvenido,
				<c:out value="${usuario.nombre}" />
				!<br>
				<br>
			</h1>
			
			<c:if test="${!usuario.esAdmin()}">
				<p>
					<strong>Gracias por elegirnos, le ofrecemos muchas
						opciones para su diversión!
				</p>
				</strong>
				<br>
				<span><i> -Comience apretando el botón aquí debajo para
						ver el listado.</i></span>&#128071;<br>
				<br>
				<a
					style="text-decoration: none; margin-left: 400px; border-style: solid; border-radius: 20px; padding: 5px"
					href="/turismo/productos/index.do">Ir a Comprar </a>
				<br>
				<br>
				
			</c:if>
		</div>

		<c:if test="${usuario.esAdmin()}">
			<div class="bg-light p-4 rounded">
				<a href="productos/index.do"><h2>Listar productos</h2></a>
			</div>
		</c:if>
		<img alt="tierra-media" src="assets/img/welcome.jpg" width="90%" height="70%">
	</main>
	
</body>
</html>