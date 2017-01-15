
function filtrarPlanificacion(idFormulario) {
	var validadoReq = true;
	var campos = "";
		
	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var divisionTitle = "";
	var sociedadTitle = "";
	var unidadTitle = "";
	if (document.getElementById("mensajeErrorValidacion") !== null) {
		mensaje = new String(document.getElementById("mensajeErrorValidacion").value);
	}
	if (document.getElementById("tituloCampoDivision") !== null) {
		divisionTitle = new String(document.getElementById("tituloCampoDivision").value);
	}
	if (document.getElementById("tituloCampoSociedad") !== null) {
		sociedadTitle = new String(document.getElementById("tituloCampoSociedad").value);
	}
	if (document.getElementById("tituloCampoUnidad") !== null) {
		unidadTitle = new String(document.getElementById("tituloCampoUnidad").value);
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
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		$('#valorCargaCompletada').val("CARGA_NO_COMPLETADA");
		mensaje += campos;
		alert(mensaje);
	} else {
		$('#valorCargaCompletada').val("CARGA_COMPLETADA");
		document.getElementById(idFormulario).action = "filtrarPlanAnual.action";
		document.getElementById(idFormulario).submit();
	}
}
function editarPlanificacion() {
	var cod = getCheckedValue(document.formularioFiltro.planificacionId);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un plan a modificar.");
		return false;
	}
	document.formularioFiltro.action = "formularioModificarPlanAnual.action";
	document.formularioFiltro.submit();
}
function aprobarPlanificacion() {
	if (confirm("\xbfEst\xe1 seguro que desea Aprobar el Plan Anual?. Este proceso puede tardar varios minutos.")) {
		var cod = getCheckedValue(document.formularioFiltro.planificacionId);
		if (cod == null || cod == "") {
			alert("Debe seleccionar un plan a aprobar.");
			return false;
		}
		$('#valorCargaCompletada').val("CARGA_COMPLETADA");
		document.formularioFiltro.action = "PlanAnualOrdenProduccion.action";
		document.formularioFiltro.submit();
	}
}
function versionarPlanificacion() {
	if (confirm("\xbfEst\xe1 seguro que desea Versionar el Plan Anual?. Este proceso puede tardar varios minutos.")) {
		var cod = getCheckedValue(document.formularioFiltro.planificacionId);
		if (cod == null || cod == "") {
			alert("Debe seleccionar un plan a versionar.");
			return false;
		}
		document.formularioFiltro.action = "formularioVersionarPlanAnual.action";
		document.formularioFiltro.submit();
	}
}
function recalcularPlanificacion() {
	var cod = getCheckedValue(document.formularioFiltro.planificacionId);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un plan para recalcular.");
		return false;
	}
	document.formularioFiltro.action = "recalcularValores.action";
	document.formularioFiltro.submit();
}
function eliminarDivision() {
	var cod = getCheckedValue(document.formularioDivisiones.divisionId);
	if (cod == null || cod == "") {
		alert("Debe seleccionar una divisi\xf3n a eliminar.");
		return false;
	}
	var answer = confirm("Seguro que desea eliminar la divisi\xf3n de c\xf3digo: " + cod + " ?");
	if (!answer) {
		return false;
	}
	document.formularioDivisiones.action = "eliminarDivision.action";
	document.formularioDivisiones.submit();
}
function filtrar() {
	var indice = document.formularioFiltro.filtro.selectedIndex;
	if (indice == 0) {
		alert("Debe seleccionar un criterio de filtrado");
	} else {
		valor = document.formularioFiltro.valor.value;
		if (valor != "") {
			document.formularioFiltro.action = "filtrarDivision.action";
			document.formularioFiltro.submit();
		} else {
			alert("Debe especificar un valor de filtrado");
		}
	}
}
function validar(e) {
	var indice = document.formularioFiltro.filtro.selectedIndex;	
	// Si el indice del combo es cero no deja escribir en el texto 
	if (indice == 0) {
		alert("No hay criterio de filtro seleccionado");
		document.formularioFiltro.valor.value = "";
	}
	// Si el indice del combo es 1 es por codigo y solo acepta numeros
	if (indice == 1) {
		return numerico(e);
	}
	// Si el indice del combo es 2 es por nombre solo acepta letras
	if (indice == 2) {
		return alfa(e);
	}
}
function eliminarValoresDivision() {
	document.getElementById("formularioFiltro_valorDivisionFiltrado").value = "";
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorAnnioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value = "";
}
function eliminarValoresSociedad() {
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorAnnioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value = "";
}
function eliminarValoresUnidad() {
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorAnnioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value = "";
}
function eliminarValoresLineaNegocio() {
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorAnnioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value = "";
}
function eliminarValoresAnio() {
	document.getElementById("formularioFiltro_valorAnnioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value = "";
}
function eliminarValoresEstado() {
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value = "";
}

