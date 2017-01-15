var contador = 0;

function agregarValores() {
	try {

		var nombrePuestoTrabajo = $("#valorPuestoTrabajo option:selected")
				.html();

		var codigoPuestoTrabajo = $("#valorPuestoTrabajo").val();

		var cantidad = $("#cantidad").val();

		var validacion = validarCamposAgregar(codigoPuestoTrabajo, cantidad);

		if (!validacion) {
			return;
		}
		if (contador == 0) {
			$("#tablaregistro").find("tr:gt(0)").remove();

		}

		var filaNueva = "<tr>"
				+ "<td>"
				+ nombrePuestoTrabajo
				+ "</td>"
				+ "<td>"
				+ cantidad
				+ "</td>"
				+ "<td><a  id='"
				+ contador
				+ "_a' href='#'	onclick='eliminarRegistro(this.id);'><img src='../images/icono_eliminar.gif'></img></a></td>"
				+ "<td  style='display:none;'>" + codigoPuestoTrabajo + "</td>"
				+ "</tr>";
		var cuerpoTabla = $("#bodyRegistro").html();
		cuerpoTabla = cuerpoTabla + filaNueva;
		document.getElementById("bodyRegistro").innerHTML = cuerpoTabla;
		contador++;
		limpiarRegistros();
	} catch (e) {
		alert(e.message);
	}
}
function validarRegistroIngresado(filas) {
	if (filas.length == 0) {
		alert("Ingrese al menos un registro");
		return false;
	}
	return true;
}

function validarCamposGuardar(valorLineaNegocioLong, valorProductoLong,
		valorAnio, valorMes) {

	var validacion = true;
	var mensaje = "Ingrese los siguientes campos: \n";
	if (valorLineaNegocioLong === undefined || valorLineaNegocioLong == "") {
		mensaje += "\n-El campo Linea de Negocio no contiene datos";
		validacion = false;
	}

	if (valorProductoLong === undefined || valorProductoLong == "") {
		mensaje += "\n-El campo Producto no contiene datos";
		validacion = false;
	}

	if (valorAnio === undefined || valorAnio == "") {
		mensaje += "\n-El campo A\u00F1o no contiene datos";
		validacion = false;
	}

	if (valorMes === undefined || valorMes == "") {
		mensaje += "\n-El campo Mes no contiene datos";
		validacion = false;
	}

	if (!validacion) {
		alert(mensaje);
	}

	return validacion;
}

function validarCamposFiltrar(valorLineaNegocioLong, valorAnio, valorMes) {

	var validacion = true;
	var mensaje = "Ingrese los siguientes campos: \n";
	if (valorLineaNegocioLong === undefined || valorLineaNegocioLong == "") {
		mensaje += "\n-El campo Linea de Negocio no contiene datos";
		validacion = false;
	}

	if (valorAnio === undefined || valorAnio == "") {
		mensaje += "\n-El campo A\u00F1o no contiene datos";
		validacion = false;
	}

	if (valorMes === undefined || valorMes == "") {
		mensaje += "\n-El campo Mes no contiene datos";
		validacion = false;
	}

	if (!validacion) {
		alert(mensaje);
	}

	return validacion;
}

function filtrar(idFormulario) {

	var valorLineaNegocioLong = $("#valorLineaNegocio").val();
	var valorAnio = $("#anio").val();
	var valorMes = $("#mes").val();

	var validacion = validarCamposFiltrar(valorLineaNegocioLong, valorAnio,
			valorMes);

	if (!validacion) {
		alert(mensaje);
	}
	if (validacion) {
		document.getElementById(idFormulario).submit();
	}

}

function confirmarCancelarOperacion() {

	var c1 = confirm('Est\u00e1 seguro de cancelar la operaci\u00f3n ? ');
	if (c1 == true) {
		document.location.href = "../manejoMaestros/listarFactorProduccionEnvasado.action";
	} else {

	}
}

function guardarNotificarEnvase() {

	try {

		var valorLineaNegocioLong = $("#valorLineaNegocio").val();
		var valorProductoLong = $("#valorProducto").val();
		var valorAnio = $("#anio").val();
		var valorMes = $("#mes").val();

		var filas = $("#bodyRegistro").find("tr");

		var validacionFilas = validarRegistroIngresado(filas);
		if (!validacionFilas) {
			return;
		}

		var validacionCamposObligatorios = validarCamposGuardar(
				valorLineaNegocioLong, valorProductoLong, valorAnio, valorMes);
		if (!validacionCamposObligatorios) {
			return;
		}

		var dataArray = [];
		$.each(filas, function(indiceFila, fila) {

			var columnas = $(fila).find("td");

			var bean = {

				codigoPuestoTrabajo : parseInt(columnas[3].textContent),
				cantidad : parseFloat(columnas[1].textContent),

			};

			dataArray.push(bean);
		});

		var objeto = {
			'factorPuestoTrabajoEnvaseProduccion' : dataArray,
			'valorLineaNegocio' : valorLineaNegocioLong,
			'valorProducto' : valorProductoLong,
			'mes' : valorMes,
			'anio' : valorAnio,

		};

		$
				.ajax({
					url : "guardarFactorProduccionEnvase.action",
					type : "POST",
					contentType : "application/json",
					data : JSON.stringify(objeto),
					dataType : "json",
					success : function(response) {
						if (response.resultadoBean.exitoOperacion) {
							alert(response.resultadoBean.mensajeOperacion);
							document.location.href = "../manejoMaestros/listarFactorProduccionEnvasado.action";
						} else {
							alert(response.resultadoBean.mensajeOperacion);
						}

					}

				});
	} catch (e) {
		alert("Error al guardar notificacion envase " + e.message);
	}
}

function limpiarRegistros() {

	$("#valorPuestoTrabajo").attr('value', "");
	$("#cantidad").attr('value', "");

}
function eliminarRegistro(componente) {
	var confirmar = confirm("Est\u00e1 seguro de Eliminar el registro?");
	if (confirmar) {
		$("#" + componente).parent().parent().remove();
		contador--;
	}

	if (contador == 0) {
		agregarFiltaNoHayRegistro();
	}

}

function agregarFiltaNoHayRegistro() {
	var filaNueva = "<tr><td id='0' colspan='9'>No se encontraron registros</td></tr>";
	var cuerpoTabla = $("#bodyRegistro").html();
	cuerpoTabla = cuerpoTabla + filaNueva;
	document.getElementById("bodyRegistro").innerHTML = cuerpoTabla;

}

function validarCamposAgregar(codigoPuestoTrabajo, cantidad) {

	var validacion = true;
	var mensaje = "Ingrese los siguientes campos: \n";

	if (codigoPuestoTrabajo === undefined || codigoPuestoTrabajo == "") {
		mensaje += "\n-El campo Puesto Trabajo no contiene datos";
		validacion = false;
	}
	if (cantidad === undefined || cantidad == "") {
		mensaje += "\n-El campo Cantidad no contiene datos";
		validacion = false;
	}

	if (!validacion) {
		alert(mensaje);
	}

	return validacion;
}

function modificarNotificarEnvase() {

	try {

		var codigo = $("#codigo").val();

		var filas = $("#bodyRegistro").find("tr");

		var validacionFilas = validarRegistroIngresado(filas);
		if (!validacionFilas) {
			return;
		}

		var dataArray = [];
		$.each(filas, function(indiceFila, fila) {

			var columnas = $(fila).find("td");

			var bean = {

				codigoPuestoTrabajo : parseInt(columnas[3].textContent),
				cantidad : parseFloat(columnas[1].textContent),

			};

			dataArray.push(bean);
		});

		var objeto = {
			'factorPuestoTrabajoEnvaseProduccion' : dataArray,
			'codigo' : codigo,

		};

		$
				.ajax({
					url : "modificarFactorProduccionEnvase.action",
					type : "POST",
					contentType : "application/json",
					data : JSON.stringify(objeto),
					dataType : "json",
					success : function(response) {
						if (response.resultadoBean.exitoOperacion) {
							alert(response.resultadoBean.mensajeOperacion);
							document.location.href = "../manejoMaestros/listarFactorProduccionEnvasado.action";
						} else {
							alert(response.resultadoBean.mensajeOperacion);
						}

					}

				});
	} catch (e) {
		alert("Error al guardar notificacion envase " + e.message);
	}
}
