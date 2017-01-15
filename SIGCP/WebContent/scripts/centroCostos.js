//  Funcion para cargar los componentes de Jquery
$(function() {

	$.datepicker.setDefaults($.datepicker.regional["es"]);
	var name = $("#name"), email = $("#email"), password = $("#password"), allFields = $(
			[]).add(name).add(email).add(password), tips = $("#validateTips");

	// Accordion
	$("#accordion").accordion({
		header : "h3"
	});

	// Tabs
	$("#tabs").tabs();

	// Dialog
	$("#dialog")
			.dialog(
					{
						autoOpen : false,
						width : 600,
						buttons : {
							"Create an account" : function() {
								var bValid = true;
								allFields.removeClass("ui-state-error");
								bValid = bValid
										&& checkLength(name, "username", 3, 16);
								bValid = bValid
										&& checkLength(email, "email", 6, 80);
								bValid = bValid
										&& checkLength(password, "password", 5,
												16);
								bValid = bValid
										&& checkRegexp(name,
												/^[a-z]([0-9a-z_])+$/i,
												"Username may consist of a-z, 0-9, underscores, begin with a letter.");
								// From jquery.validate.js (by joern),
								// contributed by Scott Gonzalez:
								// http://projects.scottsplayground.com/email_address_validation/
								bValid = bValid
										&& checkRegexp(
												email,
												/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
												"eg. ui@jquery.com");
								bValid = bValid
										&& checkRegexp(password,
												/^([0-9a-zA-Z])+$/,
												"Password field only allow : a-z 0-9");
								if (bValid) {
									$("#users tbody").append(
											"<tr>" + "<td>" + name.val()
													+ "</td>" + "<td>"
													+ email.val() + "</td>"
													+ "<td>" + password.val()
													+ "</td>" + "</tr>");
									$(this).dialog("close");
								}
							},
							Cancel : function() {
								$(this).dialog("close");
							}
						},
						close : function() {
							allFields.val("").removeClass("ui-state-error");
						}
					});
	$("#create-user").click(function() {
		$("#dialog").dialog("open");
	}).hover(function() {
		$(this).addClass("ui-state-hover");
	}, function() {
		$(this).removeClass("ui-state-hover");
	}).mousedown(function() {
		$(this).addClass("ui-state-active");
	}).mouseup(function() {
		$(this).removeClass("ui-state-active");
	});

	// Dialog Link
	$("#dialog_link").click(function() {
		$("#dialog").dialog("open");
		return false;
	});

	// Datepicker}
	$("#fechaInicio").datepicker({
		inline : true,
		dateFormat : "dd/mm/yy"
	});

	// Datepicker
	$("#fechaFin").datepicker({
		inline : true,
		dateFormat : "dd/mm/yy"
	});

	// Datepicker
	$("#fechaRegistro").datepicker({
		inline : true,
		dateFormat : "dd/mm/yy"
	});

	// Slider
	$("#slider").slider({
		range : true,
		values : [ 17, 67 ]
	});

	// Progressbar
	$("#progressbar").progressbar({
		value : 20
	});

	// hover states on the static widgets
	$("#dialog_link, ul#icons li").hover(function() {
		$(this).addClass("ui-state-hover");
	}, function() {
		$(this).removeClass("ui-state-hover");
	});
});

// Funcion procesarFormulario: Valida que el usuario haya seleccionado una
// entidad en la tabla,
// setea el action, y realiza el submit con la operacion pasada como parametro.
// Parametros: idFormualrio. Ej: "formularioFiltro", "formularioDivisiones"
// campoCodigo name del radio buttom. Ej: "centroCostosBean.codigo"
//				
function procesarFormularioEliminar(idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Centro de Costos u Orden");
		return false;
	}
	document.getElementById(idFormulario).action = "formularioEliminarObjetoCostos.action";

	// alert('zzz ' + document.getElementById(idFormulario).action);
	document.getElementById(idFormulario).submit();
}
function procesarFormularioTablaDistribuibleEliminar(idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar una Tabla Distribuible");
		return false;
	}
	document.getElementById(idFormulario).action = "formularioEliminarTablaDistribuible.action";
	document.getElementById(idFormulario).submit();
}
function procesarFormularioTablaDistribuibleEliminarReceptor() {
	var cod = getCheckedValue(document.formularioFiltro.codigoReceptor);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Centro de Costos Receptor");
		return false;
	}
	codigoRec = document.getElementById("codigoReceptor");
	codigoRec.value = cod;
	banderaRec = document.getElementById("banderaReceptor");
	banderaRec.value = "1";
}

// Eliminar Receptor
function procesarFormularioTablaDistribuibleEliminarReceptorCrear(
		codigoReceptor) {
	codigoRec = document.getElementById("codigoReceptor");
	codigoRec.value = codigoReceptor;
	banderaRec = document.getElementById("banderaReceptor");
	banderaRec.value = "1";
}
function procesarFormularioTablaDistribuibleEliminarReceptorModificar(
		codigoReceptor) {
	codigoRec = document.getElementById("codigoReceptor");
	codigoRec.value = codigoReceptor;
	banderaRec = document.getElementById("banderaReceptor");
	banderaRec.value = "1";
}
function procesarFormularioTablaDistribuibleAgregarReceptorModificar() {
	banderaRec = document.getElementById("banderaReceptor");
	banderaRec.value = "2";
}
function procesarFormularioTablaDistribuibleCargarReceptorModificar(
		codigoReceptor, codigoTipoDriver) {
	var combo = document.forms["formularioFiltro"].valorReceptor;
	var cantidad = combo.length;
	for (i = 0; i < cantidad; i++) {
		if (combo[i].value == codigoReceptor) {
			combo[i].selected = true;
			break;
		}
	}
	combo = document.forms["formularioFiltro"].valorTipoDriver;
	cantidad = combo.length;
	for (i = 0; i < cantidad; i++) {
		if (combo[i].value == codigoTipoDriver) {
			combo[i].selected = true;
			break;
		}
	}
	codigoRec = document.getElementById("codigoReceptor");
	codigoRec.value = codigoReceptor;
}
function procesarFormularioTablaDistribuibleAgregarReceptor() {
	codigoLineaNeg = document.getElementById("banderaReceptor");
	codigoLineaNeg.value = "0";
}
function procesarFormularioRegistroDistribuibleEliminar(idFormulario,
		campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar una Registro Distribuible");
		return false;
	}
	document.getElementById(idFormulario).action = "formularioEliminarRegistroDistribuible.action";
	document.getElementById(idFormulario).submit();
}
function procesarFormularioModificar(idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Centro de Costos u Orden");
		return false;
	}
	document.getElementById(idFormulario).action = "plantillaModificarObjetoCostos.action";
	document.getElementById(idFormulario).submit();
}
function procesarFormularioRegistroDistribuibleLiberar(idFormulario,
		campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Registro Distribuible");
		return false;
	}
	$("#valorCargaCompletada").val("CARGA_COMPLETADA");
	document.getElementById(idFormulario).action = "liberarRegistroDistribuible.action";
	document.getElementById(idFormulario).submit();
}
function preconsolidar(idFormulario) {
	document.getElementById(idFormulario).action = "preconsolidar.action";
	document.getElementById(idFormulario).submit();
}
function emisoresFaltantes(idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar una Area");
		return false;
	}
	document.getElementById(idFormulario).action = "emisoresFaltantes.action";
	document.getElementById(idFormulario).submit();
}
function consolidar(idFormulario) {
	document.getElementById(idFormulario).action = "consolidar.action";
	document.getElementById(idFormulario).submit();
}
function consolidarExcel(idFormulario) {
	document.getElementById(idFormulario).action = "consolidarExcel.action";
	document.getElementById(idFormulario).submit();
}
function ImportarRegistroDistribuibleAutomatico(idFormulario) {
	var resultado = false;
	var validadoReq = true;
	var campos = "";

	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var divisionTitle = "";
	var sociedadTitle = "";
	var unidadTitle = "";
	var areaTitle = "";
	var mesTitle = "";
	var anioTitle = "";
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
	if (document.getElementById("tituloCampoArea") !== null) {
		areaTitle = new String(document.getElementById("tituloCampoArea").value);
	}
	if (document.getElementById("tituloCampoMes") !== null) {
		mesTitle = new String(document.getElementById("tituloCampoMes").value);
	}
	if (document.getElementById("tituloCampoAnio") !== null) {
		anioTitle = new String(document.getElementById("tituloCampoAnio").value);
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
	// Se valida el campo Area
	if (!validarCampoRequeridoFormulario("valorArea")) {
		validadoReq = false;
		campos += "- " + areaTitle + "\r";
	}
	// Se valida el campo Mes
	if (!validarCampoRequeridoFormulario("valorMes")) {
		validadoReq = false;
		campos += "- " + mesTitle + "\r";
	}
	// Se valida el campo Año
	if (!validarCampoRequeridoFormulario("valorAnio")) {
		validadoReq = false;
		campos += "- " + anioTitle + "\r";
	}
	// Se envia el Alert en caso de error o se hace submit del formulario en el
	// caso exitoso
	if (!validadoReq) {
		mensaje += campos;
		alert(mensaje);
	} else {
		$("#valorCargaCompletada").val("CARGA_COMPLETADA");
		insertarCarga("linkImportar");
		var resultado = true;
	}
	return resultado;
}
function ExportarTablaValor(idFormulario) {
	document.getElementById(idFormulario).action = "ExportarTV.action";
	document.getElementById(idFormulario).submit();
}
function generarTValor(idFormulario) {

	// alert('hi32');
	document.getElementById(idFormulario).action = "generarTValor.action";
	document.getElementById(idFormulario).submit();
}
function jsseleccion1arCheckBox(idFormulario) {
	// Si ya se pinto algun check
	var formx = document.getElementById(idFormulario);
	if (formx.codigosRegistrosDistribuiblesArea != undefined) {
		// Si hay mas de un check
		if (formx.codigosRegistrosDistribuiblesArea.length != undefined) {
			for (i = 0; i < formx.codigosRegistrosDistribuiblesArea.length; i++) {
				formx.codigosRegistrosDistribuiblesArea[i].checked = formx.chkPadre.checked;
			}
		} else {
			formx.codigosRegistrosDistribuiblesArea.checked = formx.chkPadre.checked;
		}
	}
}
function procesarFormularioRegistroDistribuibleRevertir(idFormulario,
		campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Registro Distribuible");
		return false;
	}
	$("#valorCargaCompletada").val("CARGA_COMPLETADA");
	document.getElementById(idFormulario).action = "revertirRegistroDistribuible.action";
	document.getElementById(idFormulario).submit();
}
function procesarFormularioRegistroDistribuibleRevercion(idFormulario,
		campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Registro Distribuible");
		return false;
	}
	document.getElementById(idFormulario).action = "plantillaRevesionRegistroDistribuible.action";
	document.getElementById(idFormulario).submit();
}
function chequearAreaObjetoCostos() {
	var tipo = document.getElementById("valorTipo");
	var area = document.getElementById("valorArea");
	if (tipo.value == 1 && area == null) {
		alert("Todo Centro de Costo debe tener un Area");
		return false;
	} else {
		if (tipo.value == 1 && area.value == "") {
			alert("Todo Centro de Costo debe tener un Area");
			return false;
		}
	}
	return true;
}
function procesarFormularioOrden(idFormulario, campoTipo, fechaIni, fechaFin,
		campoArea) {
	if (campoTipo == 2) {
		if (fechaIni == "" || fechaFin == "") {
			alert("Todas las Ordenes deben tener Fecha de Inicio y Fecha Final");
			return false;
		}
	} else {
		if (fechaIni != "" || fechaFin != "") {
			alert("Ningun Centro de Costos tiene Fecha de Inicio o Fecha Final ");
			return false;
		}
		if (campoArea == "" || campoArea == null) {
			alert("Debe seleccionar un Area");
			return false;
		}
	}
	return true;
}
function procesarFormularioModificarRegistroDistribuible(idFormulario,
		campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Registro Distribuible");
		return false;
	}
	document.getElementById(idFormulario).action = "plantillaModificarRegistroDistribuible.action";
	document.getElementById(idFormulario).submit();
}
function procesarFormularioModificarRegistroDistribuibleAdministracion(
		idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Registro Distribuible");
		return false;
	}
	document.getElementById(idFormulario).action = "plantillaModificarAdministracionRegistroDistribuible.action";
	document.getElementById(idFormulario).submit();
}

// functio nueva de validacion, agregado
function filtrarTabla(idFormulario, cont) {

	var validadoReq = true;
	var campos = "";

	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var divisionTitle = "";
	var sociedadTitle = "";
	var unidadTitle = "";
	var areaTitle = "";

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

	if (cont == '1') {
		if (document.getElementById("tituloCampoArea") !== null) {
			areaTitle = new String(
					document.getElementById("tituloCampoArea").value);
		}
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

	// Se valida el campo Area
	if (cont == '1') {
		if (!validarCampoRequeridoFormulario("valorArea")) {
			validadoReq = false;
			campos += "- " + areaTitle + "\r";
		}
	}

	// Se envia el Alert en caso de error o se hace submit del formulario en el
	// caso exitoso
	if (!validadoReq) {
		$('#valorCargaCompletada').val("CARGA_NO_COMPLETADA");
		mensaje += campos;
		alert(mensaje);
	} else {
		$('#valorCargaCompletada').val("CARGA_COMPLETADA");
		document.getElementById(idFormulario).submit();
	}

}

function filtrar(idFormulario) {

	$('#valorCargaCompletada').val("CARGA_COMPLETADA");
	document.getElementById(idFormulario).submit();

}
function chequearArchivo() {
	var ext = document.archivo.userImage.value;
	var ext1 = document.archivo.userImage.value;
	ext = ext.substring(ext.length - 3, ext.length);
	ext = ext.toLowerCase();
	ext1 = ext1.substring(ext1.length - 4, ext1.length);
	ext1 = ext1.toLowerCase();
	var resultado = false;
	if (ext == "") {
		alert("Seleccione un archivo de Excel con formato .xls");
		resultado = false;
	} else {
		if (ext1 == "xlsx") {
			alert("Pase su archivo Excel a formtato .xls");
			resultado = false;
		} else {
			if (ext != "xls") {
				alert("Seleccione un archivo de Excel con formato .xls");
				resultado = false;
			} else {
				resultado = true;
			}
		}
	}
	return resultado;
}
function validarSubmit(v1, v2) {
	var resultado = false;
	if (v1 && v2) {
		$("#valorCargaCompletada").val("CARGA_COMPLETADA");
		insertarCarga("linkImportar");
		resultado = true;
	}
	return resultado;
}
function chequearAreaArchivo(campoCodigo) {
	var resultado = false;
	if (campoCodigo == undefined) {
		alert("Debe seleccionar un Area");
		resultado = false;
	} else {
		if (campoCodigo.value == null || campoCodigo.value == "") {
			alert("Debe seleccionar un Area");
			resultado = false;
		} else {
			resultado = true;
		}
	}
	return resultado;
}
function chequearCantidadRegistroDistribuible(campoCodigo) {
	if (campoCodigo.value < 0) {
		alert("La cantidad no puede ser negativa");
		return false;
	} else {
		return true;
	}
}
function chequearBajado(idFormulario, campoCodigo) {
	if (chequearAreaArchivo(campoCodigo)) {
		document.getElementById(idFormulario).action = "cargarArchivoRegistros.action";
		document.getElementById(idFormulario).submit();
		return false;
	} else {
		return false;
	}
}
function chequearConsolidarRegistro(idFormulario, campoCodigo) {
	if (chequearAreaArchivo(campoCodigo)) {
		document.getElementById(idFormulario).action = "consolidarRegistroDistribuibleArea.action";
		document.getElementById(idFormulario).submit();
		return false;
	} else {
		return false;
	}
}
function chequearConsultarTablaValor(idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Grupo Tabla Valor");
		return false;
	}
	document.getElementById(idFormulario).action = "tablaValor.action";
	document.getElementById(idFormulario).submit();
}
function chequearConsultarRegistroValor(idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar una Tabla Valor");
		return false;
	}
	document.getElementById(idFormulario).action = "registrosValor.action";
	document.getElementById(idFormulario).submit();
}
function chequearConsultarConceptoValor(idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Registro Valor");
		return false;
	}
	document.getElementById(idFormulario).action = "conceptoValor.action";
	document.getElementById(idFormulario).submit();
}

function habilitarOdeshabilitarFecha(obj) {

	if (obj.value == '1') {
		document.getElementById('fechaInicio').disabled = true;
		document.getElementById('fechaFin').disabled = true;
	} else if (obj.value == '2') {

		document.getElementById('fechaInicio').disabled = false;
		document.getElementById('fechaFin').disabled = false;
	}
}
