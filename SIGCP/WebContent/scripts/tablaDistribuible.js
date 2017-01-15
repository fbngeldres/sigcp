//  Funcion para cargar los componentes de Jquery
$(function () {
	$("#centroEmisor").autocomplete({source:function (request, response) {
		$.ajax({url:"../controlCostos/autocompletarCentrodeCostos.action", data:"valorArea=" + $("#valorArea").val() + "&valorEmisor=" + $("#centroEmisor").val() + "&rand=" + (new Date()).getTime(), dataType:"xml", success:function (xmlResponse) {
			response($("OBJETO", xmlResponse).map(function () {
				return {value:$.trim($("SAP", this).text() + "-" + $("DESCRIPCION", this).text()), id:$("ID", this).text()};
			}).get());
		}});
	}, minLength:0, select:function (event, ui) {
		$("#valorEmisor").attr("value", ui.item.id);
		update("descEmisor");
	}});
	$("#centroReceptor").autocomplete({source:function (request, response) {
		$.ajax({url:"../controlCostos/autocompletarCentrodeCostosReceptor.action", data:"valorReceptor=" + $("#centroReceptor").val() + "&rand=" + (new Date()).getTime(), dataType:"xml", success:function (xmlResponse) {
			response($("OBJETO", xmlResponse).map(function () {
				return {value:$.trim($("SAP", this).text() + "-" + $("DESCRIPCION", this).text()), id:$("ID", this).text()};
			}).get());
		}});
	}, minLength:2, select:function (event, ui) {
		$("#valorReceptor").attr("value", ui.item.id);
		update("descReceptor");
	}});
});
function cargarCentroEmisor(){
	var codigoCentroEmisor;
	var centroEmisor = $("#centroEmisor").val();
	codigoCentroEmisor = centroEmisor.split('-');
	$("#centroEmisor").val($.trim(codigoCentroEmisor[0]));
}
function cargarCentroReceptor(){
	var codigoCentroReceptor;
	var centroReceptor = $("#centroReceptor").val();
	codigoCentroReceptor = centroReceptor.split('-');
	$("#centroReceptor").val($.trim(codigoCentroReceptor[0]));
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
function procesarFormularioTablaDistribuibleEliminarReceptor(codigo) {
	codigoRec = document.getElementById("codigoReceptor");
	codigoRec.value = codigo;
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
}
function procesarFormularioTablaDistribuibleCargarReceptorModificar(codigoReceptor, codigoTipoDriver) {
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

	if(validar()){
		codigoLineaNeg = document.getElementById("banderaReceptor");
		codigoLineaNeg.value = "0";
		update("listaReceptor");
		limpiar();
		return false;
	}
}
function limpiar() {
	$("#centroReceptor").attr("value", "");
	$("#centroEmisor").attr("value", "");
	$("#valorReceptor").attr("value", "");
	$("#valorEmisor").attr("value", "");
	$("#codigoReceptor").attr("value", "");
	$("#banderaReceptor").attr("value", "");
	$("#valorPuestoTrabajo").attr("value", "");
	$("#valorProducto").attr("value", "");
	$("#valorProceso").attr("value", "");
	$("#valorActividad").attr("value", "");
	$("#valorComponente").attr("value", "");
	update("descEmisor");
	update("descReceptor");
}
function validar() {
	var validadoReq = true;
	
	var campos = "" ;

	
	var mensaje = $("#mensajeErrorValidacion").val();
	

	if (!validarCampoRequeridoFormulario("valorDivision")) {
		validadoReq = false;
		campos += "- " + new String($("#tituloCampoDivision").val()) + "\r";
	}
	if (!validarCampoRequeridoFormulario("valorSociedad")) {
		validadoReq = false;
		campos += "- " + $("#tituloCampoSociedad").val() + "\r";
	}
	if (!validarCampoRequeridoFormulario("valorUnidad")) {
		validadoReq = false;
		campos += "- " + $("#tituloCampoUnidad").val() + "\r";
	}
	if (!validarCampoRequeridoFormulario("valorLineaNegocios")) {
		validadoReq = false;
		campos += "- " + $("#tituloCampoLineaNegocio").val() + "\r";
	}
	if (!validarCampoRequeridoFormulario("valorArea")) {
		validadoReq = false;
		campos += "- " + $("#tituloCampoArea").val() + "\r";
	}
	if (!validarCampoRequeridoFormulario("valorProceso")) {
		validadoReq = false;
		campos += "- " + $("#tituloCampoProceso").val() + "\r";
	}
	if (!validarCampoRequeridoFormulario("valorActividad")) {
		validadoReq = false;
		campos += "- " + $("#tituloCampoActividad").val() + "\r";
	}
	if (!validarCampoRequeridoFormulario("valorProducto")) {
		validadoReq = false;
		campos += "- " + $("#tituloCampoProducto").val() + "\r";
	}
	if (!validarCampoRequeridoFormulario("valorEmisor")) {
		validadoReq = false;
		campos += "- " + $("#tituloCampoCecoEmisor").val() + "\r";
	}
	if (!validarCampoRequeridoFormulario("valorReceptor")) {
		validadoReq = false;
		campos += "- " + $("#tituloCampoCecoReceptor").val() + "\r";
	}
	
	if(!validadoReq){
		mensaje += campos;
		alert(mensaje);
	}
	
	return validadoReq;
}

