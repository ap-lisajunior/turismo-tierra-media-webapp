<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="/partials/nav.jsp"></jsp:include>

	<main class="container">

		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errores != null}">
						<ul>
							<c:forEach items="${errores}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>

		<div class="bg-light p-4 mb-3 rounded">
			<h1>Estas son las atracciones y promociones de la Tierra Media</h1>
		</div>

<%--		<c:if test="${usuario.esAdmin()}">
			<div class="mb-3">
				<a href="/turismo/atracciones/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nueva Atracción
				</a>
			</div>
		</c:if>
--%>
		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Producto</th>
					<th>Costo</th>
					<th>Duraci&oacute;n</th>
					<th>Cupo</th>
					<th>Compra</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productos}" var="producto">
					<tr>
						<td><strong><c:out value="${producto.nombre}"></c:out></strong>
						<c:if test="${producto.esUnaPromocion()}">
							<p>Esta promocion esta compuesta por las siguientes atracciones: </p>
							<ul>
							<c:forEach items="${producto.getAtracciones()}" var="atraccion">
								<li><c:out value="${atraccion.nombre}"></c:out></li>
							</c:forEach>
							</ul>
						</c:if>
						</td>
						<td><c:out value="${producto.costo}"></c:out></td>
						<td><c:out value="${producto.tiempo}"></c:out></td>
						<c:choose>
							<c:when test="${!producto.esUnaPromocion()}">
								<td><c:out value="${producto.cupo}"></c:out></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
						<td>
						<c:choose>
							<c:when
								test="${usuario.puedeComprar(producto) && producto.tieneCupo() && (!itinerario.getAtracciones().contains(producto) && !itinerario.getPromociones().contains(producto))}">
								<a href="/turismo/productos/comprar.do?nombre=${producto.nombre}"
									class="btn btn-success rounded" role="button">Comprar</a>
							</c:when>
							<c:otherwise>
									<a href="#" class="btn btn-secondary rounded disabled"
										role="button">No se puede comprar</a>
							</c:otherwise>
						</c:choose>
						</td>
<%--						<td><c:if test="${usuario.esAdmin()}">
								<a href="/turismo/atracciones/edit.do?id=${atraccion.nombre}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/turismo/atracciones/delete.do?id=${atraccion.nombre}}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if> </td>
							--%>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</main>

</body>
</html>