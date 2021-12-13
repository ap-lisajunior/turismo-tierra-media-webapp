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
				¡Bienvenido, <c:out value="${usuario.nombre}" />!
			</h1>
		</div>
		<c:if test="${usuario.esAdmin()}">
			<div class="bg-light p-4 rounded">
				<a class="link-dark" href="productos/index.do"><h2>Listar productos</h2></a>
			</div>
		</c:if>
	</main>
</body>
</html>
