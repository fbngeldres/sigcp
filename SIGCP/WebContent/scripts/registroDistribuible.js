//  Funcion para cargar los componentes de Jquery
$(function () {
	// Datepicker	
	$.datepicker.setDefaults($.datepicker.regional["es"]);
	$("#fechaRegistro").datepicker({inline:true, dateFormat:"dd/mm/yy"});
	$("#centroEmisor").autocomplete({source:function (request, response) {
		$.ajax({url:"../controlCostos/autocompletarCentrodeCostos.action", data:"valorArea=" + $("#valorArea").val() + "&valorEmisor=" + $("#centroEmisor").val() + "&rand=" + (new Date()).getTime(), dataType:"xml", success:function (xmlResponse) {
			response($("OBJETO", xmlResponse).map(function () {
				return {value:$.trim($("SAP", this).text() + "-" + $("DESCRIPCION", this).text()), id:$("ID", this).text(),codSap:$("SAP", this).text()};
			}).get());
		}});
	}, minLength:2, select:function (event, ui) {
	//alert(ui.item.codSap);
	//alert($("#centroEmisor").val());
		$("#valorEmisorAutocompletar").attr("value", ui.item.id);
		$("#centroEmisorSAPAux").attr("value", ui.item.codSap);
		
		
		update("descEmisor");
		update("valorEstadisticoEmisor");
					//alert("Selected : " + ui.item.value+ " - " +ui.item.id );
	},close:function(event, ui){
//	alert("CLOSE");
//	alert($("#centroEmisorSAPAux").val());
		$("#centroEmisor").attr("value",$("#centroEmisorSAPAux").val());
	} });
	
	
	$("#centroReceptor").autocomplete({source:function (request, response) {
		$.ajax({url:"../controlCostos/autocompletarCentrodeCostosReceptor.action", data:"valorReceptor=" + $("#centroReceptor").val() + "&rand=" + (new Date()).getTime(), dataType:"xml", success:function (xmlResponse) {
			response($("OBJETO", xmlResponse).map(function () {
				return {value:$.trim($("SAP", this).text() + "-" + $("DESCRIPCION", this).text()), id:$("ID", this).text(),codSap:$("SAP", this).text()};
			}).get());
		}});
	}, minLength:2, select:function (event, ui) {
		$("#valorReceptorAutocompletar").attr("value", ui.item.id);
		$("#centroReceptorSAPAux").attr("value", ui.item.codSap);
		update("descReceptor");
	},close:function(event, ui){

		$("#centroReceptor").attr("value",$("#centroReceptorSAPAux").val());
	}});
	
	
});

//	Funcion procesarFormulario:	Valida que el usuario haya seleccionado una entidad en la tabla,
//						setea el action, y realiza el submit con la operacion pasada como parametro.
//	Parametros: idFormualrio.	Ej: "formularioFiltro", "formularioDivisiones"
//				campoCodigo		name del radio buttom. Ej:	"centroCostosBean.codigo"
//				
function procesarFormularioEliminar(idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Centro de Costos u Orden");
		return false;
	}
	document.getElementById(idFormulario).action = "formularioEliminarObjetoCostos.action";
	
		//alert('zzz ' + document.getElementById(idFormulario).action);
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

//Eliminar Receptor
function procesarFormularioTablaDistribuibleEliminarReceptorCrear(codigoReceptor) {
	codigoRec = document.getElementById("codigoReceptor");
	codigoRec.value = codigoReceptor;
	banderaRec = document.getElementById("banderaReceptor");
	banderaRec.value = "1";
}
function procesarFormularioTablaDistribuibleEliminarReceptorModificar(codigoReceptor) {
	codigoRec = document.getElementById("codigoReceptor");
	codigoRec.value = codigoReceptor;
	banderaRec = document.getElementById("banderaReceptor");
	banderaRec.value = "1";
}
function procesarFormularioTablaDistribuibleAgregarReceptorModificar() {
	banderaRec = document.getElementById("banderaReceptor");
	banderaRec.value = "2";
	update("listaReceptor");
	limpiarFormularioModificarTablaDistribuible();
}
function limpiarFormularioModificarTablaDistribuible() {
	$("#centroReceptor").attr("value", "");
	$("#valorReceptorAutocompletar").attr("value", "");
	$("#codigoReceptor").attr("value", "");
	$("#cantidadText").attr("value", "0.0");
	var combo;
	combo = document.forms["formularioFiltro"].valorTipoDriver;
	cantidad = combo.length;
	for (i = 0; i < cantidad; i++) {
		if (combo[i].value == "-1") {
			combo[i].selected = true;
			break;
		}
	}
	update("descReceptor");
	update("combosTipoDriver");
	$("#contenido").focus();
	$("#centroReceptor").focus();
}
function procesarFormularioTablaDistribuibleCargarReceptorModificar(codigoReceptor, codigoTipoDriver, nombreReceptor) {
	if(nombreReceptor.split('-').length >1){
	nombreReceptor=nombreReceptor.split('-')[0];
	}
	
	$("#centroReceptor").attr("value", nombreReceptor);
	$("#valorReceptorAutocompletar").attr("value", codigoReceptor);
	var combo;
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
	codigoRec = document.getElementById("valorReceptor");
	codigoRec.value = codigoReceptor;
	update("descReceptor");
	update("combosTipoDriver");
	$("#contenido").focus();
	$("#centroReceptor").focus();
}
function procesarFormularioTablaDistribuibleAgregarReceptor() {
	codigoLineaNeg = document.getElementById("banderaReceptor");
	codigoLineaNeg.value = "0";
	update("listaReceptor");
	limpiarFormularioModificarTablaDistribuible();
}
function procesarFormularioRegistroDistribuibleEliminar(idFormulario, campoCodigo) {
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
function procesarFormularioRegistroDistribuibleLiberar(idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Registro Distribuible");
		return false;
	}
	document.getElementById(idFormulario).action = "liberarRegistroDistribuible.action";
	document.getElementById(idFormulario).submit();
}
function preconsolidar(idFormulario) {
	document.getElementById(idFormulario).action = "preconsolidar.action";
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
function ExportarTablaValor(idFormulario) {
	document.getElementById(idFormulario).action = "ExportarTV.action";
	document.getElementById(idFormulario).submit();
}
function generarTValor(idFormulario) {

//alert('hi32');
	document.getElementById(idFormulario).action = "generarTValor.action";
	document.getElementById(idFormulario).submit();
}
function jsseleccion1arCheckBox(idFormulario) {
//Si ya se pinto algun check
	var formx = document.getElementById(idFormulario);
	if (formx.codigosRegistrosDistribuiblesArea != undefined) {
	    //Si hay mas de un check
		if (formx.codigosRegistrosDistribuiblesArea.length != undefined) {
			for (i = 0; i < formx.codigosRegistrosDistribuiblesArea.length; i++) {
				formx.codigosRegistrosDistribuiblesArea[i].checked = formx.chkPadre.checked;
			}
		} else {
			formx.codigosRegistrosDistribuiblesArea.checked = formx.chkPadre.checked;
		}
	}
}
function procesarFormularioRegistroDistribuibleRevertir(idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Registro Distribuible");
		return false;
	}
	document.getElementById(idFormulario).action = "revertirRegistroDistribuible.action";
	document.getElementById(idFormulario).submit();
}
function procesarFormularioRegistroDistribuibleRevercion(idFormulario, campoCodigo) {
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
function procesarFormularioOrden(idFormulario, campoTipo, fechaIni, fechaFin) {
	if (campoTipo == 2) {
		if (fechaIni == "" || fechaFin == "") {
			alert("Todas las Ordebes deben tener Fecha de Inicio y Fecha Final");
			return false;
		}
	} else {
		if (fechaIni != "" || fechaFin != "") {
			alert("Ningun Centro de Costos tiene Fecha de Inicio o Fecha Final ");
			return false;
		}
	}
	return true;
}
function procesarFormularioModificarRegistroDistribuible(idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Registro Distribuible");
		return false;
	}
	document.getElementById(idFormulario).action = "plantillaModificarRegistroDistribuible.action";
	document.getElementById(idFormulario).submit();
}
function procesarFormularioModificarRegistroDistribuibleAdministracion(idFormulario, campoCodigo) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Registro Distribuible");
		return false;
	}
	document.getElementById(idFormulario).action = "plantillaModificarAdministracionRegistroDistribuible.action";
	document.getElementById(idFormulario).submit();
}
function filtrar(idFormulario) {
	document.getElementById(idFormulario).submit();
}
function chequearArchivo() {
	var ext = document.archivo.userImage.value;
	var ext1 = document.archivo.userImage.value;
	ext = ext.substring(ext.length - 3, ext.length);
	ext = ext.toLowerCase();
	ext1 = ext1.substring(ext1.length - 4, ext1.length);
	ext1 = ext1.toLowerCase();
	if (ext == "") {
		alert("Seleccione un archivo de Excel con formato .xls");
		return false;
	} else {
		if (ext1 == "xlsx") {
			alert("Pase su archivo Excel a formtato .xls");
			return false;
		} else {
			if (ext != "xls") {
				alert("Seleccione un archivo de Excel con formato .xls");
				return false;
			} else {
				return true;
			}
		}
	}
}
function chequearAreaArchivo(campoCodigo) {
	if (campoCodigo == undefined) {
		alert("Debe seleccionar un Area");
		return false;
	} else {
		if (campoCodigo.value == null || campoCodigo.value == "") {
			alert("Debe seleccionar un Area");
			return false;
		} else {
			return true;
		}
	}
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
/*
function redondea(sVal, nDec){ 
	if(sVal == "") {
		return "0.00";
	} else {
		var n = parseFloat(sVal); 
    	var s; 
   		n = Math.round(n * Math.pow(10, nDec)) / Math.pow(10, nDec); 
    	s = String(n) + "." + String(Math.pow(10, nDec)).substr(1); 
    	s = s.substr(0, s.indexOf(".") + nDec + 1); 
    	return s; 
	}
} */
/*
function ponDecimales(nDec){ 
   $("#horaInicial").attr("value", redondea($("#horaInicial").val(), nDec));
   $("#horaFinal").attr("value", redondea($("#horaFinal").val(), nDec));
   $("#cantidadText").text(redondea($("#cantidad").val(), nDec));
   $("#cantidad").attr("value", redondea($("#cantidad").val(), nDec));
} */

function calcularHoras(idTextField, condicion) {
	var hora1;
	var hora2;
	if (condicion == "HM") {
		hora1 = parseFloat($("#horaInicial").val());
		hora2 = parseFloat($("#horaFinal").val());
		
		if(!esNumero(hora1)){
		$("#cantidadText").text(0);
		$("#cantidad").attr("value", 0);
		return;
		}
		if(!esNumero(hora2)){
		$("#cantidadText").text(0);
		$("#cantidad").attr("value", 0);
		return;
		}
		if (hora2 > hora1) {
			var result = hora2 - hora1;
			result = redondeo1decimales(result);
			
			$("#cantidadText").text(result);
			$("#cantidad").attr("value", result);
		}
	} else {
		if (condicion == "HH") {
			hora1 = parseFloat($("#horaInicial").val());
			hora2 = parseFloat($("#horaFinal").val());
			
			if(!esNumero(hora1)){
				if(!esNumero(hora2)){
					if(!esNumero($("#cantidad").val())){
						$("#cantidadText").text(0);
						$("#cantidad").attr("value", 0);
					}
				}else{
					$("#cantidadText").text(hora2);
					$("#cantidad").attr("value", hora2);
				}
				return;
			}
			if(!esNumero(hora2)){
				if(!esNumero(hora1)){
					if(!esNumero($("#cantidad").val())){
						$("#cantidadText").text(0);
						$("#cantidad").attr("value", 0);
					}
				}else{
					$("#cantidadText").text(hora1);
					$("#cantidad").attr("value", hora1);
				}
				return;
			}
			var result = hora2 + hora1;
			result = redondeo1decimales(result);

			$("#cantidadText").text(result);
			$("#cantidad").attr("value", result);
		}
	}
}
function validarRegistro() {
	var validadoReq = true;
	var campos = "";
		
	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var divisionTitle = "";
	var sociedadTitle = "";
	var unidadTitle = "";
	var areaTitle = "";
	var fechaRegistroTitle = "";
	var centroEmisorTitle = "";
	var centroReceptorTitle = "";
	var horaini = "";
	var horafinal = "";
	var cantidad = "";
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
	if (document.getElementById("tituloCampoArea") !== null) {
		areaTitle = new String(document.getElementById("tituloCampoArea").value);
	}
	if (document.getElementById("tituloCampoFechaRegistro") !== null) {
		fechaRegistroTitle = new String(document.getElementById("tituloCampoFechaRegistro").value);
	}
	if (document.getElementById("tituloCampoCecoEmisor") !== null) {
		centroEmisorTitle = new String(document.getElementById("tituloCampoCecoEmisor").value);
	}
	if (document.getElementById("tituloCampoCecoReceptor") !== null) {
		centroReceptorTitle = new String(document.getElementById("tituloCampoCecoReceptor").value);
	}
	if (document.getElementById("tituloCampoCantidad") !== null) {
		cantidad = new String(document.getElementById("tituloCampoCantidad").value);
	}
	switch (parseInt(document.getElementById("valorTipoDriver").value)) {
	  case 1:
		if (document.getElementById("tituloCampoHorometroInicial") !== null) {
			horaini = new String(document.getElementById("tituloCampoHorometroInicial").value);
		}
		if (document.getElementById("tituloCampoHorometroFina") !== null) {
			horafinal = new String(document.getElementById("tituloCampoHorometroFina").value);
		}
		break;
	  case 2:
		if (document.getElementById("tituloCampoHoraHombreInicial") !== null) {
			horaini = new String(document.getElementById("tituloCampoHoraHombreInicial").value);
		}
		if (document.getElementById("tituloCampoHoraHombreFinal") !== null) {
			horafinal = new String(document.getElementById("tituloCampoHoraHombreFinal").value);
		}
	  default:
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
	// Se valida el campo Fecha Registro
	if (!validarCampoRequeridoFormulario("fechaRegistro")) {
		validadoReq = false;
		campos += "- " + fechaRegistroTitle + "\r";
	}
	// Se valida el campo Centro de Costo
	if (!validarCampoRequeridoFormulario("centroEmisor")) {
		validadoReq = false;
		campos += "- " + centroEmisorTitle + "\r";
	}
	// Se valida el campo Centro de Costo u Orden
	if (!validarCampoRequeridoFormulario("centroReceptor")) {
		validadoReq = false;
		campos += "- " + centroReceptorTitle + "\r";
	}

	//Se valida las cantidades
	switch (parseInt(document.getElementById("valorTipoDriver").value)) {
	  case 1:
		if (!validarCampoFormularioValorMayorACero("horaInicial")) {
			validadoReq = false;
			campos += "- " + horaini + "\r";
		}
		if (!validarCampoFormularioValorMayorACero("horaFinal")) {
			validadoReq = false;
			campos += "- " + horafinal + "\r";
		}
		if (!validarCampoFormularioValorMayorACero("cantidad")) {
			validadoReq = false;
			campos += "- " + cantidad + "\r";
		}
		
		break;
	  case 2:
		if (!validarCampoFormularioValorMayorACero("horaInicial")) {
			validadoReq = false;
			campos += "- " + horaini + "\r";
		}
		if (!validarCampoFormularioValorMayorACero("horaFinal")) {
			validadoReq = false;
			campos += "- " + horafinal + "\r";
		}
		if (!validarCampoFormularioValorMayorACero("cantidad")) {
			validadoReq = false;
			campos += "- " + cantidad + "\r";
		}
		
		break;
	  default:
		if (!validarCampoRequeridoFormulario("cantidad")) {
			validadoReq = false;
			campos += "- " + cantidad + "\r";
			break;
		}
		if (!validarCampoFormularioValorMayorACero("cantidad")) {
			validadoReq = false;
			campos += "- " + cantidad + "\r";
		}
	}
	
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		mensaje += campos;
		alert(mensaje);
		return validadoReq;
	}
	return validadoReq;
}
function validar() {
	if (validarRegistro()) {
		procesarFormularioTablaDistribuibleAgregarReceptor();
	}
}

