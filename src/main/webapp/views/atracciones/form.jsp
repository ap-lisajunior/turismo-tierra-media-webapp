<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="nombre" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="nombre" name="nombre"
			required value="${atraccion.nombre}">
	</div>
	<div class="mb-3">
		<label for="costo"
			class='col-form-label ${atraccion.errores.get("costo") != null ? "is-invalid" : "" }'>Costo:</label>
		<input class="form-control" type="number" id="costo" name="costo"
			required value="${atraccion.costo}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errores.get("costo")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="tiempo"
			class='col-form-label ${atraccion.errores.get("tiempo") != null ? "is-invalid" : "" }'>Tiempo:</label>
		<input class="form-control" type="number" id="tiempo" name="tiempo"
			required value="${atraccion.tiempo}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errores.get("tiempo")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="cupo"
			class='col-form-label ${atraccion.errores.get("cupo") != null ? "is-invalid" : "" }'>Cupo:</label>
		<input class="form-control" type="number" id="cupo" name="cupo"
			required value="${atraccion.cupo}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errores.get("cupo")}'></c:out>
		</div>
	</div>
		<div class="mb-3">
		<label for="tipo"
			class='col-form-label'>Tipo de Atraccion:</label>
		<select name="tipo">
			<c:forEach items="${tiposatraccion}" var="tipo">
				<option value="${tipo}">${tipo}</option>
			</c:forEach>
		</select>
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
