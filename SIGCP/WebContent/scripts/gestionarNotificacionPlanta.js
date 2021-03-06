String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g, "");
};
function procesarFormulario(idFormulario, campoCodigo, action, mensaje) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		var mensaje = document.getElementById("elementoNoSeleccionado").value;
		alert(mensaje);
		return false;
	}
	if (confirm(mensaje)) {
		showLoading();
		document.getElementById(idFormulario).action = action;
		document.getElementById(idFormulario).submit();
	}
}
function btnFiltrarClicked(idFormulario) {
	var validadoReq = true;
	var campos = "";

	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var divisionTitle = "";
	var sociedadTitle = "";
	var unidadTitle = "";
	var lineaNegocioTitle = "";
	var mesTitle = "";
	if (document.getElementById("mensajeErrorValidacion") !== null) {
		mensaje = new String(
				document.getElementById("mensajeErrorValidacion").value);
	}
	if (document.getElementById("tituloCampoDivision") !== null) {
		divisionTitle = new String(document
				.getElementById("tituloCampoDivision").value);
	}
	if (document.getElementById("tituloCampoSociedad") !== null) {
		sociedadTitle = new String(document
				.getElementById("tituloCampoSociedad").value);
	}
	if (document.getElementById("tituloCampoUnidad") !== null) {
		unidadTitle = new String(
				document.getElementById("tituloCampoUnidad").value);
	}
	if (document.getElementById("tituloCampoLineaNegocio") !== null) {
		lineaNegocioTitle = new String(document
				.getElementById("tituloCampoLineaNegocio").value);
	}
	if (document.getElementById("tituloCampoMes") !== null) {
		mesTitle = new String(document.getElementById("tituloCampoMes").value);
	}

	// Se realizan las validaciones pertinentes
	// Se valida el campo Division
	if (!validarCampoRequeridoFormulario("valorDivision")) {
		validadoReq = false;
		campos += "- " + divisionTitle + "\n";
	}
	// Se valida el campo Sociedad
	if (!validarCampoRequeridoFormulario("valorSociedad")) {
		validadoReq = false;
		campos += "- " + sociedadTitle + "\n";
	}
	// Se valida el campo Unidad
	if (!validarCampoRequeridoFormulario("valorUnidad")) {
		validadoReq = false;
		campos += "- " + unidadTitle + "\n";
	}
	// Se valida el campo Linea de Negocio
	if (!validarCampoRequeridoFormulario("valorLineaNegocio")) {
		validadoReq = false;
		campos += "- " + lineaNegocioTitle + "\n";
	}
	// Se valida el campo Mes
	if (!validarCampoRequeridoFormulario("mes")) {
		validadoReq = false;
		campos += "- " + mesTitle + "\n";
	}

	// Se envia el Alert en caso de error o se hace submit del formulario en el
	// caso exitoso
	if (!validadoReq) {
		$("#valorCargaCompletada").val("CARGA_NO_COMPLETADA");
		mensaje += campos;
		alert(mensaje);
	} else {
		$("#valorCargaCompletada").val("CARGA_COMPLETADA");
		insertarCarga("loading");
		document.getElementById(idFormulario).action = "cargarGestionarParteDiario.action";
		document.getElementById(idFormulario).submit();
	}
}
function actualizarCodUnidad() {
	var unidad = document.getElementById("valorUnidad");
	if (unidad != null) {
		document.getElementById("codUnidad").value = unidad.value;
	}
}
function nuevoFormulario(idFormulario, entidad, operacion) {
	document.getElementById(idFormulario).action = operacion + entidad
			+ ".action";
	document.getElementById(idFormulario).submit();
}
function eliminarValoresDivision() {
	var valorDivisionFiltrado = document
			.getElementById("valorDivisionFiltrado");
	if (valorDivisionFiltrado != null) {
		valorDivisionFiltrado.value = "";
	}
	var valorSociedadFiltrado = document
			.getElementById("valorSociedadFiltrado");
	if (valorSociedadFiltrado != null) {
		valorSociedadFiltrado.value = "";
	}
	var valorUnidadFiltrado = document.getElementById("valorUnidadFiltrado");
	if (valorUnidadFiltrado != null) {
		valorUnidadFiltrado.value = "";
	}
	var valorLineaNegocioFiltrado = document
			.getElementById("valorLineaNegocioFiltrado");
	if (valorLineaNegocioFiltrado != null) {
		valorLineaNegocioFiltrado.value = "";
	}
}
function eliminarValoresSociedad() {
	var valorSociedadFiltrado = document
			.getElementById("valorSociedadFiltrado");
	if (valorSociedadFiltrado != null) {
		valorSociedadFiltrado.value = "";
	}
	var valorUnidadFiltrado = document.getElementById("valorUnidadFiltrado");
	if (valorUnidadFiltrado != null) {
		valorUnidadFiltrado.value = "";
	}
	var valorLineaNegocioFiltrado = document
			.getElementById("valorLineaNegocioFiltrado");
	if (valorLineaNegocioFiltrado != null) {
		valorLineaNegocioFiltrado.value = "";
	}
}
function eliminarValoresUnidad() {
	var valorUnidadFiltrado = document.getElementById("valorUnidadFiltrado");
	if (valorUnidadFiltrado != null) {
		valorUnidadFiltrado.value = "";
	}
	var valorLineaNegocioFiltrado = document
			.getElementById("valorLineaNegocioFiltrado");
	if (valorLineaNegocioFiltrado != null) {
		valorLineaNegocioFiltrado.value = "";
	}
}
function eliminarValoresLinea() {
	var valorLineaNegocioFiltrado = document
			.getElementById("valorLineaNegocioFiltrado");
	if (valorLineaNegocioFiltrado != null) {
		valorLineaNegocioFiltrado.value = "";
	}
}
function showLoading() {
	var pb = document.getElementById("loading");
	pb.innerHTML = "<img style='border: 0px;' alt='Cargando...' src='../images/ajax-loader.gif' width='220px' height='19px'>";
	pb.style.display = "";
}
function btnFiltrarPorPuestoTrabajo(idFormulario) {
	try {
		var validadoReq = true;
		var campos = "";

		// Se cargan los mensajes desde el resources.properties
		var mensaje = "";
		var divisionTitle = "";
		var sociedadTitle = "";
		var unidadTitle = "";
		var lineaNegocioTitle = "";
		var mesTitle = "";
		if (document.getElementById("mensajeErrorValidacion") !== null) {
			mensaje = new String(document
					.getElementById("mensajeErrorValidacion").value);
		}
		if (document.getElementById("tituloCampoDivision") !== null) {
			divisionTitle = new String(document
					.getElementById("tituloCampoDivision").value);
		}
		if (document.getElementById("tituloCampoSociedad") !== null) {
			sociedadTitle = new String(document
					.getElementById("tituloCampoSociedad").value);
		}
		if (document.getElementById("tituloCampoUnidad") !== null) {
			unidadTitle = new String(document
					.getElementById("tituloCampoUnidad").value);
		}
		if (document.getElementById("tituloCampoLineaNegocio") !== null) {
			lineaNegocioTitle = new String(document
					.getElementById("tituloCampoLineaNegocio").value);
		}
		if (document.getElementById("tituloCampoMes") !== null) {
			mesTitle = new String(
					document.getElementById("tituloCampoMes").value);
		}

		// Se realizan las validaciones pertinentes
		// Se valida el campo Division
		if (!validarCampoRequeridoFormulario("valorDivision")) {
			validadoReq = false;
			campos += "- " + divisionTitle + "\r";
		}
		// Se valida el campo Sociedad
		if (!validarCampoRequeridoFormulario("valorSociedad")) {
			validadoReq = false;
			campos += "- " + sociedadTitle + "\r";
		}
		// Se valida el campo Unidad
		if (!validarCampoRequeridoFormulario("valorUnidad")) {
			validadoReq = false;
			campos += "- " + unidadTitle + "\r";
		}
		// Se valida el campo Linea de Negocio
		if (!validarCampoRequeridoFormulario("valorLineaNegocio")) {
			validadoReq = false;
			campos += "- " + lineaNegocioTitle + "\r";
		}
		// Se valida el campo Mes
		if (!validarCampoRequeridoFormulario("mes")) {
			validadoReq = false;
			campos += "- " + mesTitle + "\r";
		}

		// if (!validarCampoRequeridoFormulario("valorPuestoTrabajo")) {
		// validadoReq = false;
		// campos += "- " + "Puesto trabajo obligatorio" + "\r";
		// }
		//		

		// Se envia el Alert en caso de error o se hace submit del formulario en
		// el caso exitoso
		if (!validadoReq) {
			$("#valorCargaCompletada").val("CARGA_NO_COMPLETADA");
			mensaje += campos;
			alert(mensaje);
		} else {
			$("#valorCargaCompletada").val("CARGA_COMPLETADA");
			insertarCarga("loading");
			document.getElementById(idFormulario).action = "filtrarParteDiarioPorPuestoTrabajo.action";
			document.getElementById(idFormulario).submit();
		}
	} catch (e) {
		alert("error");
		alert(e.message);
	}
}

function enviarEmail(idFormulario, action, mensaje) {
	var confirmar = confirm(mensaje);
	if (confirmar == true) {
		$("#valorCargaCompletada").val("CARGA_COMPLETADA");
		insertarCarga("loading");
		document.getElementById(idFormulario).action = action;
		document.getElementById(idFormulario).submit();
	} else {
		$("#valorCargaCompletada").val("CARGA_NO_COMPLETADA");
	}

}
