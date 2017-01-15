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
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar " + mensajeEntidad + " a " + operacion);
		return false;
	}
	var url = window.location.toString();
	var nuevoUrl = url.substring(0, url.lastIndexOf("/") + 1);
	nuevoUrl = nuevoUrl.concat(operacion + entidad + ".action?i=" + cod);
	window.location.replace(nuevoUrl);
}
function filtrar(idFormulario) {
	//document.getElementById(idFormulario).submit();
	var validadoReq = true;
	var campos = "";
		
	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var divisionTitle = "";
	var sociedadTitle = "";
	var unidadTitle = "";
	var lineaNegocioTitle = "";
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
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		$("#valorCargaCompletada").val("CARGA_NO_COMPLETADA");
		mensaje += campos;
		alert(mensaje);
	} else {
		$("#valorCargaCompletada").val("CARGA_COMPLETADA");
		document.getElementById(idFormulario).action = "listarIngresosProductos.action";
		document.getElementById(idFormulario).submit();
	}
}
function Calcular(campo, fila) {
	var peso = document.getElementsByName("factorPeso")[fila].value;
	var viajes = campo.value;
	var resultado = new Number(0);
	if (viajes != "" && viajes != "undefined") {
		resultado = Number(peso * viajes);
		resultado = resultado.toFixed(2);
	}
	document.getElementsByName("tonIngr")[fila].value = resultado;
	document.getElementsByName("toneladasIngresadas")[fila].value = resultado;
}
$(function () {
	$.datepicker.setDefaults($.datepicker.regional["es"]);
		
				// Datepicker
	$("#fechaRegistro").datepicker({inline:true, dateFormat:"dd/mm/yy"});
});
function chequearArchivo() {
	var ext = document.archivo.userImage.value;
	var ext1 = document.archivo.userImage.value;
	ext = ext.substring(ext.length - 3, ext.length);
	ext = ext.toLowerCase();
	ext1 = ext1.substring(ext1.length - 4, ext1.length);
	ext1 = ext1.toLowerCase();
	if (ext == "") {
		alert("Seleccione un archivo de Excel con formato .csv");
	} else {
		if (ext == "xls" || ext1 == "xlsx") {
			alert("Pase su archivo Excel a formtato .csv");
			return false;
		} else {
			if (ext != "csv") {
				alert("Seleccione un archivo de Excel con formato .csv");
				return false;
			} else {
				return true;
			}
		}
	}
}
function validarTipoArchivo() {
	var ext = document.archivo.userImage.value;
	var ext1 = document.archivo.userImage.value;
	ext = ext.substring(ext.length - 3, ext.length);
	ext = ext.toLowerCase();
	ext1 = ext1.substring(ext1.length - 4, ext1.length);
	ext1 = ext1.toLowerCase();
	if (ext == "") {
		return "stock.error.formatoArchivo.csv";
	} else {
		if (ext == "xls" || ext1 == "xlsx") {
			return "stock.error.formatoArchivo.csv";
		} else {
			if (ext != "csv") {
				return "stock.error.formatoArchivo.csv";
			} else {
				return "";
			}
		}
	}
}
function actualizarCamposRegistroIngresos() {
	document.archivo.divisionTmp.value = document.formularioFiltro.valorDivision.value;
	document.archivo.sociedadTmp.value = document.formularioFiltro.valorSociedad.value;
	document.archivo.unidadTmp.value = document.formularioFiltro.valorUnidad.value;
	document.archivo.lineaTmp.value = document.formularioFiltro.valorLineaNegocio.value;
	document.archivo.errorTipoArchivo.value = validarTipoArchivo();
	return true;
}
function chequearLineaNegociosArchivo(campoCodigo) {
	if (campoCodigo == undefined) {
		alert("Debe seleccionar una Linea de Negocios");
		return false;
	} else {
		if (campoCodigo.value == null || campoCodigo.value == "") {
			alert("Debe seleccionar una Linea de Negocios ");
			return false;
		} else {
	document.archivo.divisionTmp.value = document.formularioFiltro.valorDivision.value;
	document.archivo.sociedadTmp.value = document.formularioFiltro.valorSociedad.value;
	document.archivo.unidadTmp.value = document.formularioFiltro.valorUnidad.value;
	document.archivo.lineaTmp.value = document.formularioFiltro.valorLineaNegocio.value;
	document.archivo.errorTipoArchivo.value = chequearArchivo();
	return true;
		}
	}
}
function chequearFechaArchivo(campoCodigo) {
	if (campoCodigo == undefined) {
		alert("Debe seleccionar una Fecha");
		return false;
	} else {
		if (campoCodigo.value == null || campoCodigo.value == "") {
			alert("Debe seleccionar una Fecha");
			return false;
		} else {
			return true;
		}
	}
	return true;
}
function recoverData() {
	document.formularioFiltro.fecha.value = obtainValuesCommaSeparated(document.getElementsByName("fecha"));
	document.formularioFiltro.valorHora.value = obtainValuesCommaSeparated(document.getElementsByName("valorHora"));
	document.formularioFiltro.valorAlamacen.value = obtainValuesCommaSeparated(document.getElementsByName("valorAlamacen"));
	document.formularioFiltro.valorUbicacion.value = obtainValuesCommaSeparated(document.getElementsByName("valorUbicacion"));
	document.formularioFiltro.factorPeso.value = obtainValuesCommaSeparated(document.getElementsByName("factorPeso"));
	document.formularioFiltro.placaVehiculo.value = obtainValuesCommaSeparated(document.getElementsByName("placaVehiculo"));
	document.formularioFiltro.producto.value = obtainValuesCommaSeparated(document.getElementsByName("producto"));
	document.formularioFiltro.turno.value = obtainValuesCommaSeparated(document.getElementsByName("turno"));
	document.formularioFiltro.numeroViajes.value = obtainValuesCommaSeparated(document.getElementsByName("numeroViajes"));
	document.formularioFiltro.toneladasIngresadas.value = obtainValuesCommaSeparated(document.getElementsByName("tonIngr"));
	document.formularioFiltro.ticket.value = obtainValuesCommaSeparated(document.getElementsByName("ticket_"));
	document.formularioFiltro.notaEntrega.value = obtainValuesCommaSeparated(document.getElementsByName("notaEntrega_"));
	document.formularioFiltro.observacion.value = obtainValuesCommaSeparated(document.getElementsByName("observacion_"));
	if (document.formularioFiltro.codigo != null && document.formularioFiltro.codigo != "undefined") {
		document.formularioFiltro.codigo.value = obtainValuesCommaSeparated(document.getElementsByName("codigo"));
	}
}
function obtainValuesCommaSeparated(objectArray) {
	if (objectArray != null) {
		var valueArray = "";
		var size = objectArray.length;
		for (var i = 0; i < size; i++) {
			if (objectArray[i].value != "") {
				if (i > 0) {
					valueArray += ",";
				}
				valueArray += objectArray[i].value;
			}
		}
		return valueArray;
	}
}
function eliminarValoresDivision() {
	document.getElementById("formularioFiltro_valorDivisionFiltrado").value = "";
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value = "";
}
function eliminarValoresSociedad() {
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value = "";
}
function eliminarValoresUnidad() {
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value = "";
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value = "";
}
function eliminarValoresLinea() {
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value = "";
}

