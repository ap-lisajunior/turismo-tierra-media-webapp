<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="name" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="name"
			required value="${producto.nombre}">
	</div>
	<div class="mb-3">
		<label for="cost"
			class='col-form-label ${producto.errores.get("costo") != null ? "is-invalid" : "" }'>Costo:</label>
		<input class="form-control" type="number" id="cost" name="cost"
			required value="${producto.costo}"></input>
		<div class="invalid-feedback">
			<c:out value='${producto.errores.get("costo")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="duration"
			class='col-form-label ${producto.errores.get("tiempo") != null ? "is-invalid" : "" }'>Duration:</label>
		<input class="form-control" type="number" id="duration" name="duration"
			required value="${producto.tiempo}"></input>
		<div class="invalid-feedback">
			<c:out value='${producto.errores.get("tiempo")}'></c:out>
		</div>
	</div>
	<div class="mb-3">
		<label for="capacity"
			class='col-form-label ${producto.errores.get("cu") != null ? "is-invalid" : "" }'>Capacity:</label>
		<input class="form-control" type="number" id="capacity" name="capacity"
			required value="${producto.cupo}"></input>
		<div class="invalid-feedback">
			<c:out value='${producto.errores.get("cupo")}'></c:out>
		</div>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
