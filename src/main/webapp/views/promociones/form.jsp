<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="nombre" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="nombre" name="nombre"
			required value="${promocion.nombre}">
	</div>
	<c:if test="${tiposatraccion != null}">
		<div class="mb-3">
			<label for="tipoatraccion"
			class='col-form-label'>Tipo de Atraccion:</label>
			<select name="tipoatraccion">
				<c:forEach items="${tiposatraccion}" var="tipoatraccion">
					<option value="${tipoatraccion}">${tipoatraccion}</option>
				</c:forEach>
			</select>
		</div>
	</c:if>
	<c:if test="${tipospromocion != null}">
		<div class="mb-3">
			<label for="tipopromocion"
			class='col-form-label'>Tipo de Promocion:</label>
			<select name="tipopromocion">
				<c:forEach items="${tipospromocion}" var="tipopromocion">
					<option value="${tipopromocion}">${tipopromocion}</option>
				</c:forEach>
			</select>
		</div>
	</c:if>
		<div class="mb-3">
			<label for="atracciones"
				class='col-form-label'>Atracciones:</label>
			<ul>
				<c:forEach items="${atracciones}" var="atraccion">
				<li>
					<input type="checkbox" name="nombreatraccion" value="${atraccion.nombre}">
					<label for="${atraccion.nombre}">${atraccion.nombre}</label>
				</li>
				</c:forEach>
			</ul>
		</div>
		<div class="mb-3">
			<label for="descuento"
				class='col-form-label'>Descuento:</label>
			<input class="form-control" type="number" id="descuento" name="descuento"
				required value="${promocion.descuento}"></input>
		</div>
		<div class="mb-3">
		<label
			class='col-form-label'>Activo:</label>
		<input class="form-check-input" type="radio" id="activo" name="activo"
			required value="true"></input>
			<label class="form-check-label" for="activo">Si</label>
		<input class="form-check-input" type="radio" id="inactivo" name="activo"
			required value="false"></input>
			<label class="form-check-label" for="inactivo">No</label>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
