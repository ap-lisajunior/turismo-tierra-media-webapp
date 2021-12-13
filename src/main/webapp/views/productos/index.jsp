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
		<c:choose>
			<c:when test="${usuario.esAdmin()}">
				<div class="bg-light p-4 mb-3 rounded">
					<h1>Administracion de atracciones y promociones de la TM</h1>
				</div>
			</c:when>
			<c:otherwise>
				<div class="bg-light p-4 mb-3 rounded">
					<h1>Nuestros Productos</h1>
					<br>
					<h2 style="color: pink; text-align: center">Estas son las
						principales sugerencias para usted</h2>
				</div>
			</c:otherwise>
		</c:choose>
		
		<%-- <div id="carouselExampleSlidesOnly" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img class="d-block w-100"
								src="assets/img/promo.png"
								alt="First slide"> <strong><c:out
									value="${producto.nombre}"></c:out></strong> <a
								href="/turismo/productos/comprar.do?nombre=${producto.nombre}"
								class="btn btn-success rounded" role="button"
								style="margin: 5px">Comprar</a>
						</div>
						<div class="carousel-item">
							<img class="d-block w-100"
								src="assets/img/promo.png"
								alt="Second slide"> <strong><c:out
									value="${producto.nombre}"></c:out></strong> <a
								href="/turismo/productos/comprar.do?nombre=${producto.nombre}"
								class="btn btn-success rounded" role="button"
								style="margin: 5px">Comprar</a>
						</div>
						<div class="carousel-item">
							<img class="d-block w-100"
								src="assets/img/promo.png"
								alt="Third slide"> <strong><c:out
									value="${producto.nombre}"></c:out></strong> <a
								href="/turismo/productos/comprar.do?nombre=${producto.nombre}"
								class="btn btn-success rounded" role="button"
								style="margin: 5px">Comprar</a>
						</div>
					</div> 
				</div> 



segundo carousel

<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- Indicadores -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>

					<!-- Entorno para las diapositivas -->
					<div class="carousel-inner">
						<div class="item active">
							<img
								src="assets/img/promo.png"
								alt="Promo-1" style="width: 100%;"> <strong><c:out
									value="${producto.nombre}"></c:out></strong> <a
								href="/turismo/productos/comprar.do?nombre=${producto.nombre}"
								class="btn btn-success rounded" role="button"
								style="margin: 5px">Comprar</a>
						</div>

						<div class="item">
							<img
								src="assets/img/promo.png"
								alt="Promo-2" style="width: 100%;"> <strong><c:out
									value="${producto.nombre}"></c:out></strong> <a
								href="/turismo/productos/comprar.do?nombre=${producto.nombre}"
								class="btn btn-success rounded" role="button"
								style="margin: 5px">Comprar</a>
						</div>

						<div class="item">
							<img
								src="assets/img/promo.png"
								alt="Promo-3" style="width: 100%;"> <strong><c:out
									value="${producto.nombre}"></c:out></strong> <a
								href="/turismo/productos/comprar.do?nombre=${producto.nombre}"
								class="btn btn-success rounded" role="button"
								style="margin: 5px">Comprar</a>
						</div>

					<!-- Controles izquierda - derecha -->
					<a class="left carousel-control" href="#myCarousel"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left"></span> <span
						class="sr-only">Anterior</span>
					</a> <a class="right carousel-control" href="#myCarousel"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right"></span> <span
						class="sr-only">Siguiente</span>
					</a>
					</div> --%>


		<h2 style="color: pink; text-align: center">Listado de las atracciones y promociones de la Tierra Media</h2>

		<table class="table table-stripped table-hover">
			<thead>
				<tr>
					<th>Producto</th>
					<th>Costo</th>
					<th>Duraci&oacute;n</th>
					<th>Cupo</th>
					<c:if test="${!usuario.esAdmin()}">
						<th>Compra</th>
					</c:if>
					<c:if test="${usuario.esAdmin()}">
						<th>Editar</th>
					</c:if>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productos}" var="producto">
					<tr>
						<td><strong><c:out value="${producto.nombre}"></c:out></strong>
							<c:if test="${producto.esUnaPromocion()}">
								<p>Esta promocion esta compuesta por las siguientes
									atracciones:</p>
								<ul>
									<c:forEach items="${producto.getAtracciones()}" var="atraccion">
										<li><c:out value="${atraccion.nombre}"></c:out></li>
									</c:forEach>
								</ul>
							</c:if></td>
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
						<c:if test="${!usuario.esAdmin()}">
							<td><c:choose>
									<c:when
										test="${ usuario.puedeComprar(producto) && producto.tieneCupo() && (!itinerario.getAtracciones().contains(producto) && !itinerario.getPromociones().contains(producto))}">
										<a
											href="/turismo/productos/comprar.do?nombre=${producto.nombre}"
											class="btn btn-success rounded" role="button">Comprar</a>
									</c:when>
									<c:otherwise>
										<a href="#" class="btn btn-secondary rounded disabled"
											role="button">No se puede comprar</a>
									</c:otherwise>
								</c:choose></td>
						</c:if>
						<td><c:if test="${usuario.esAdmin()}">
								<c:choose>
									<c:when test="${producto.esUnaPromocion()}">
										<a
											href="/turismo/promociones/editar.do?nombre=${producto.nombre}"
											class="btn btn-light rounded-0" role="button"> <i
											class="bi bi-pencil-fill"></i>
										</a>
									</c:when>
									<c:otherwise>
										<a
											href="/turismo/atracciones/editar.do?nombre=${producto.nombre}"
											class="btn btn-light rounded-0" role="button"> <i
											class="bi bi-pencil-fill"></i>
										</a>
									</c:otherwise>
								</c:choose>

								<%-- 								<a href="/turismo/atracciones/delete.do?id=${atraccion.nombre}}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
									--%>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		<c:if test="${!itinerario.getAtracciones().isEmpty()}">
			<h1>

				¡Gracias por su compra! <br> <i> A continuación, podrá ver
					el resumen de su itinerario:</i> <br> <br>

			</h1>
			<h2 style="color: #069; text-align: center">
				<c:out value="${itinerario}" />
			</h2>
		</c:if>
	</main>
<!-- <script type="text/javascript">$('.carousel').carousel({
  interval: 2000
})</script> -->
</body>
</html>