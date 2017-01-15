
function show_ajustesProduccionMes() {
	document.forms[0].ejecutaConsulta.value = "execute";
}
function show_gruposAjuste() {
	dojo.event.topic.publish("listar_grupos");
}
function show_grupoProductos() {
	dojo.event.topic.publish("listar_grupoProductos");
}
function show_productos() {
	dojo.event.topic.publish("listar_productos");
}
function show_parteTecnico() {
	document.forms[0].ejecutaConsulta.value = "execute";
	document.forms[0].productoSeleccionadoTemp.value = document.forms[0].productoSeleccionado.value;
	dojo.event.topic.publish("listar_parteTecnico");
}
function filtrarAjustes(idFormulario) {
	document.getElementById(idFormulario).action = "listarAjusteProduccionMes.action";
	document.getElementById(idFormulario).submit();
}
function showLoading() {
	var pb = document.getElementById("loading");
	pb.innerHTML = "<img style='border: 0px;' alt='Cargando...' src='../images/ajax-loader.gif' width='220px' height='19px'>";
	pb.style.display = "";
}
/**
*Agregado Por Fabian Geldres
**/
function exportar(idFormulario, tipoArchivo, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	if (validacionFormulario()) {
		if (tipoArchivo == "XLS") {
			document.getElementById(idFormulario).action = dirAction + "reportes/exportarParteTecnicoPdf.action";
			$("#xlsPDF").attr("value", "2");
		} else {
			if (tipoArchivo == "PDF") {
				document.getElementById(idFormulario).action = dirAction + "reportes/exportarParteTecnicoPdf.action";
				$("#xlsPDF").attr("value", "1");
			}
		}
		$("#" + idFormulario).get(0).setAttribute("target", "_blank");
		document.getElementById(idFormulario).submit();
	}
}
function validacionFormulario() {
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
	
	// Se valida el campo ANNIO
	if (!validarCampoRequeridoFormulario("valorAnnio")) {
		validadoReq = false;
		campos += "- " + "Seleccione valor A\xf1o" + "\r";
	}
	
	// Se valida el campo Mes
	if (!validarCampoRequeridoFormulario("valorMes")) {
		validadoReq = false;
		campos += "- " + "Seleccione valor Mes" + "\r";
	}
	
	// Se valida el campo Informe
	if (!validarCampoRequeridoFormulario("valorInforme")) {
		validadoReq = false;
		campos += "- " + "Seleccione valor Informe" + "\r";
	}
	
	// Se valida el campo ANNIO
//	if (validarCampoRequeridoFormulario("valorInforme")) {
//		if (document.getElementById("valorInforme").value == "1") {
//			if (!validarCampoRequeridoFormulario("valorProducto")) {
//				validadoReq = false;
//				campos += "- " + "Seleccione producto" + "r";
//			}
//		}
//	}
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		mensaje += campos;
		alert(mensaje);
	}
	return validadoReq;
}
function eliminarSociedadUnidadLinea() {
	document.getElementById("valorUnidadFiltrado").value = "";
	document.getElementById("valorLineaNegocioFiltrado").value = "";
	document.getElementById("valorUnidad").value = "";
	document.getElementById("valorLineaNegocio").value = "";
}
function eliminarUnidadLinea() {
	document.getElementById("valorLineaNegocioFiltrado").value = "";
	document.getElementById("valorLineaNegocio").value = "";
}
function filtrarAjustesProducto(idFormulario) {
	document.getElementById(idFormulario).action = "filtrarAjusteProducto.action";
	document.getElementById(idFormulario).submit();
}
function eliminarAjusteParteTecnico(idFormulario, campoCodigo, mensajeEntidad, preguntaConfirmacion, entidad, operacion) {
	try {
		var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
		if (cod == null || cod == "") {
			alert("Debe seleccionar " + mensajeEntidad + " a " + operacion.toLowerCase());
			return false;
		}
		if (confirm(preguntaConfirmacion)) {
			document.getElementById(idFormulario).action = "formulario" + operacion + entidad + ".action";
			document.getElementById(idFormulario).submit();
			showLoading();
		}
	}
	catch (e) {
		alert(e);
	}
}

