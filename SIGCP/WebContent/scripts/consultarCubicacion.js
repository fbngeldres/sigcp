//	Funcion procesarFormulario:	Valida que el usuario haya seleccionado una entidad en la tabla,
//						setea el action, y realiza el submit con la operacion pasada como parametro.
//	Parametros: idFormualrio.	Ej: "formularioFiltro", "formularioDivisiones"
//				campoCodigo		name del radio buttom. Ej:	"centroCostosBean.codigo"
//				mensajeEntidad.	Ej: "una Division" o "un Centro de Costos"
//				entidad			Ej: "Division", "CentroCostos"
//				operacion		Siempre en minúscula. Ej: "Aprobar" o "Eliminar"
function procesarFormulario(idFormulario, campoCodigo, mensajeEntidad, entidad, operacion) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar " + mensajeEntidad + " a " + operacion);
		return false;
	}
	document.getElementById(idFormulario).action = operacion + entidad + ".action";
	document.getElementById(idFormulario).submit();
}
function procesarFormularioGWT(idFormulario, campoCodigo, mensajeEntidad, entidad, operacion) {
	var cantidadMarcados = 0;
	var campoCodigoArray = document.getElementById(idFormulario).elements[campoCodigo];
	// Verificamos la cantidad de checks marcados
	if (campoCodigoArray.length != undefined) {
		for (i = 0; i < campoCodigoArray.length; i++) {
			if (campoCodigoArray[i].checked == true) {
				cantidadMarcados += 1;
			}
		}
	}

	// Dado que solo se puede modificar una sola cubicacion, se garantiza que sea asi.
	if (cantidadMarcados > 1) {
		alert("Debe seleccionar una Cubicacin a modificar");
	} else {
		var cod = getCheckedValue(campoCodigoArray);
		if (cod == null || cod == "") {
			alert("Debe seleccionar " + mensajeEntidad + " a " + operacion);
			return false;
		}
		var url = window.location.toString();
		var nuevoUrl = url.substring(0, url.lastIndexOf("/") + 1);
		nuevoUrl = nuevoUrl.concat(operacion + entidad + ".action?i=" + cod);
		window.location.replace(nuevoUrl);
	}
}
function filtrar(idFormulario) {
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
	if (document.getElementById("tituloCampoLineaNegocio") !== null) {
		lineaNegocioTitle = new String(document.getElementById("tituloCampoLineaNegocio").value);
	}
	if (document.getElementById("tituloCampoMes") !== null) {
		mesTitle = new String(document.getElementById("tituloCampoMes").value);
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
	if (!validarCampoRequeridoFormulario("mesCubicacion")) {
		validadoReq = false;
		campos += "- " + mesTitle + "\r";
	}
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		$("#valorCargaCompletada").val("CARGA_NO_COMPLETADA");
		mensaje += campos;
		alert(mensaje);
	} else {
		$("#valorCargaCompletada").val("CARGA_COMPLETADA");
		document.getElementById(idFormulario).action = "listarCubicacionesMes.action";
		document.getElementById(idFormulario).submit();
	}
}
function eliminarValoresDivision() {
	document.getElementById("formularioFiltro_valorDivisionFiltrado").value = "";
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value = "";
	document.getElementById("formularioFiltro_valorProductoFiltrado").value = "";
}
function eliminarValoresSociedad() {
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value = "";
	document.getElementById("formularioFiltro_valorProductoFiltrado").value = "";
}
function eliminarValoresUnidad() {
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value = "";
	document.getElementById("formularioFiltro_valorProductoFiltrado").value = "";
}
function eliminarValoresLinea() {
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value = "";
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value = "";
	document.getElementById("formularioFiltro_valorProductoFiltrado").value = "";
}
function eliminarValoresProceso() {
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value = "";
	document.getElementById("formularioFiltro_valorProductoFiltrado").value = "";
}
function eliminarValoresProducto() {
	document.getElementById("formularioFiltro_valorProductoFiltrado").value = "";
}
function revertir(idFormulario, campoCodigo, mensajeEntidad, entidad, operacion) {

	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar " + mensajeEntidad + " a " + operacion);
		return false;
	}
	if (confirm("Desea "+ operacion + " los registros seleccionados?")) {
		insertarCarga("linkFiltrarCubicacion");
		document.getElementById(idFormulario).action = operacion + entidad + ".action";
		document.getElementById(idFormulario).submit();
	}
}

