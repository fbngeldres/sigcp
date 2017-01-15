
function camposObligatorios(idFormulario, action) {
	var validadoReq = true;
	var campos = "";

	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var divisionTitle = "";
	var sociedadTitle = "";
	var unidadTitle = "";
	var puestoTrabajoTitle = "";
	var productoTitle = "";
	var anioTitle = "";
	var mesTitle = "";
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
		//campos += "- " + divisionTitle + "\r";
		campos += "- Division" + "\r";
	}
	// Se valida el campo Sociedad
	if (!validarCampoRequeridoFormulario("valorSociedad")) {
		validadoReq = false;
		//campos += "- " + sociedadTitle + "\r";
		campos += "- Sociedad" + "\r";
	}
	// Se valida el campo Unidad
	if (!validarCampoRequeridoFormulario("valorUnidad")) {
		validadoReq = false;
		//campos += "- " + unidadTitle + "\r";
		campos += "- Unidad" + "\r";
	}
	
	// Se valida el campo de Mes de Inicio
	if (!validarCampoRequeridoFormulario("fechaInicio")) {
		validadoReq = false;
		campos += "- Debe seleccionar el Mes de Inicio \r";
	}

	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		mensaje += campos;
		alert(mensaje);
	} else {
		if (action == "Submit") {
			htmlSubmit(idFormulario);
		}
	}
}

// Inserta la barra de Cargando hasta que se genera el Reporte
function insertarLoading() {
	$("#valorCargaCompletada").val("CARGA_COMPLETADA");
	$("#linkGenerarReporteHtmlAjax").html("<img style='border: 0px;' alt='Cargando...' src='../images/ajax-loader.gif' width='220px' height='19px'>");
}	

// Cambia el action del Formulario del Reporte de acuerdo a la accion de exportacion a usar (EXCEL, PDF)
function cambiarActionFormularioReporte(idFormulario, tipoAction) {
	try {
		var nombreAction = "";
		if (tipoAction == "EXCEL") {
			nombreAction = "cargarReporteDosificacion.action";
			$("#tipoReporte").val("XLS");
		} else {
			if (tipoAction == "PDF") {
				nombreAction = "cargarReporteDosificacion.action";
				$("#" + idFormulario).get(0).setAttribute("target", "_blank");
				$("#tipoReporte").val("PDF");
			}
		}
			
		
		$("#" + idFormulario).get(0).setAttribute("action", nombreAction);
	}
	catch (e) {
		alert(e.message);
	}
}
function htmlSubmit(idFormulario) {
	document.getElementById(idFormulario).submit();
}
function cerrarse() {
	window.close();
}

