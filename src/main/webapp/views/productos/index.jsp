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
					<h1 style="text-align: center">Administracion de atracciones y promociones de la TM</h1>
				</div>
			</c:when>
			<c:otherwise>
				
					<c:if test="${!itinerario.getAtracciones().isEmpty()}">
					<div style="background-color: #EAF4F9; border-style: solid; border-color: #B1DEF3; text-align: center;">
						<h3 style="text-align: center;">

							¡Gracias por su compra! <i> A continuación, podrá
								ver el resumen de su itinerario:</i> <br> <br>

						</h3>
						<h4 style="color: #D69DC1; text-align: center; background-color: #EAF4F9">
							<c:out value="${itinerario}" />
						</h4> <br>
						</div><br>
					</c:if>

			</c:otherwise>
		</c:choose>

		<!-- carousel -->
		<c:forEach items="${productos}" var="producto">
			<c:if
				test="${ usuario.puedeComprar(producto) && producto.tieneCupo() && (!itinerario.getAtracciones().contains(producto) && !itinerario.getPromociones().contains(producto))}">
				<div id="carouselExampleControls"
					class="carousel carousel-dark slide" data-bs-ride="carousel">
			</c:if>
		</c:forEach>
		<div class="carousel-inner">
			<c:forEach items="${productos}" var="producto">
				<c:if
					test="${ usuario.puedeComprar(producto) && producto.tieneCupo() && (!itinerario.getAtracciones().contains(producto) && !itinerario.getPromociones().contains(producto))}">
					<div class="carousel-item active" data-bs-interval="1000">
						<p>&#10084;</p>
					</div>
					<c:forEach items="${productos}" var="producto2">
						<c:if
							test="${ usuario.puedeComprar(producto2) && producto2.tieneCupo() && (!itinerario.getAtracciones().contains(producto2) && !itinerario.getPromociones().contains(producto2))}">
							<div class="carousel-item" data-bs-interval="5000">
								<div>
									<h3 style="text-align: center">${producto2.nombre}</h3>
									<p style="text-align: center">Costo: ${producto2.costo } -
										Duración: ${producto2.tiempo }</p>
									<a
										href="/turismo/productos/comprar.do?nombre=${producto2.nombre}"
										class="btn btn-success rounded" role="button"
										style="margin-left: 600px">Comprar</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<c:forEach items="${productos}" var="producto1">
						<c:if
							test="${ usuario.puedeComprar(producto1) && producto1.tieneCupo() && (!itinerario.getAtracciones().contains(producto1) && !itinerario.getPromociones().contains(producto1))}">
							<div class="carousel-item" data-bs-interval="5000">
								<div>
									<h3 style="text-align: center">${producto1.nombre}</h3>
									<p style="text-align: center">Costo: ${producto1.costo } -
										Duración: ${producto1.tiempo }</p>
									<a
										href="/turismo/productos/comprar.do?nombre=${producto1.nombre}"
										class="btn btn-success rounded" role="button"
										style="margin-left: 600px">Comprar</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<c:forEach items="${productos}" var="producto3">
						<c:if
							test="${ usuario.puedeComprar(producto3) && producto3.tieneCupo() && (!itinerario.getAtracciones().contains(producto3) && !itinerario.getPromociones().contains(producto3))}">
							<div class="carousel-item" data-bs-interval="5000">
								<div>
									<h3 style="text-align: center">${producto3.nombre }</h3>
									<p style="text-align: center">Costo: ${producto3.costo } -
										Duración: ${producto3.tiempo }</p>
									<a
										href="/turismo/productos/comprar.do?nombre=${producto3.nombre}"
										class="btn btn-success rounded" role="button"
										style="margin-left: 600px">Comprar</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleControls" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next " type="button"
			data-bs-target="#carouselExampleControls" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
		</div>
		<br><br>
		<h2 style="color: pink; text-align: center">Listado de las
			atracciones y promociones de la Tierra Media <a
											href="/turismo/atracciones/index.do?"
											class="btn btn-light rounded-1" role="button" style="background-color: pink; margin-left: 20px"> <i > &#8594; Ver Descripción</i></a></h2> 

		<table class="table table-stripped table-hover" >
			<thead >
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
											class="btn btn-light rounded-circle" role="button" > <i
											class="bi bi-pencil-fill" ></i>
										</a>
									</c:when>
									<c:otherwise>
										<a
											href="/turismo/atracciones/editar.do?nombre=${producto.nombre}"
											class="btn btn-light rounded-circle" role="button"> <i
											class="bi bi-pencil-fill"></i>
										</a>
									</c:otherwise>
								</c:choose>

 								 <a href="/turismo/attractions/delete.do?nombre=${producto.nombre}"
									class="btn btn-danger rounded-circle" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</main>
</body>
</html>