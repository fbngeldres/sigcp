//  Funcion para cargar los componentes de Jquery
$(function () {
	$.datepicker.setDefaults($.datepicker.regional["es"]);
		
				// Datepicker
	$("#fechaInicio").datepicker({inline:true, dateFormat:"dd/mm/yy"});
	$("#fechaFin").datepicker({inline:true, dateFormat:"dd/mm/yy"});
	$("#fechaMovimiento").datepicker({inline:true, dateFormat:"dd/mm/yy"});
});


function procesarFormulario(idFormulario, campoCodigo, action) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		var mensaje = document.getElementById('elementoNoSeleccionado').value;
		alert(mensaje);
		return false;
	}
	document.getElementById(idFormulario).action = action;
	document.getElementById(idFormulario).submit();
}

function camposObligatoriosFiltrar(idFormulario) {
	
	var validadoReq = true;
	var campos = "";
		
	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var divisionTitle = "";	
	var sociedadTitle = "";
	var unidadTitle = "";
	var listaMaterialTitle = "";
	var fechaInicioTitle = "";
	if (document.getElementById("mensajeErrorValidacion") !== null)
		mensaje = new String(document.getElementById("mensajeErrorValidacion").value);	
	if (document.getElementById("tituloCampoDivision") !== null) 
		divisionTitle = new String(document.getElementById("tituloCampoDivision").value);
	if (document.getElementById("tituloCampoSociedad") !== null) 
		sociedadTitle = new String(document.getElementById("tituloCampoSociedad").value);
	if (document.getElementById("tituloCampoUnidad") !== null) 
		unidadTitle = new String(document.getElementById("tituloCampoUnidad").value);
	if (document.getElementById("tituloCampoListaMaterial") !== null) 
		listaMaterialTitle = new String(document.getElementById("tituloCampoListaMaterial").value);
	if (document.getElementById("tituloCampoFechaInicio") !== null) 
		fechaInicioTitle = new String(document.getElementById("tituloCampoFechaInicio").value);
		
	// Se realizan las validaciones pertinentes
	// Se valida el campo Division
	if (!validarCampoRequeridoFormulario("valorDivision")) {
		validadoReq = false;
		campos += "- "+divisionTitle+"\r";
	}
	// Se valida el campo Sociedad
	if (!validarCampoRequeridoFormulario("valorSociedad")) {
		validadoReq = false;
		campos += "- "+sociedadTitle+"\r";
	}
	// Se valida el campo Unidad
	if (!validarCampoRequeridoFormulario("valorUnidad")) {
		validadoReq = false;
		campos += "- "+unidadTitle+"\r";
	}
	// Se valida el campo Lista de Materiales
	if (!validarCampoRequeridoFormulario("valorProducto")) {
		validadoReq = false;
		campos += "- "+listaMaterialTitle+"\r";
	}
	// Se valida el campo Fecha Inicio
	if (!validarCampoRequeridoFormulario("fechaInicio")) {
		validadoReq = false;
		campos += "- "+fechaInicioTitle+"\r";
	}
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		$('#valorCargaCompletada').val("CARGA_NO_COMPLETADA");
		mensaje += campos;
		alert(mensaje);
	} else {
		$('#valorCargaCompletada').val("CARGA_COMPLETADA");
		document.formularioFiltro.action='cargarDetalleMovimientos.action';
		document.getElementById(idFormulario).submit();
	}
}

function actualizarCodUnidad() {
	var unidad = document.getElementById('valorUnidad');
	
	if (unidad != null) {
		document.getElementById('codUnidad').value = unidad.value;
	}
}

function actualizarCantidad() {
	
	var peso = document.getElementsByName("factorPeso")[fila].value;
	var viajes = document.getElementsByName("factorPeso")[fila].value;
	var resultado= new Number( 0 );
	if (viajes != "" && viajes != 'undefined' && viajes != "" && peso != 'undefined') {
		resultado = Number(peso * viajes);
		resultado = resultado.toFixed(2);
	}	
	document.getElementsByName("tonIngr")[fila].value = resultado;
}

// Funciones para limpiar los combos de opciones
function eliminarValoresDivision(){
	document.getElementById("formularioFiltro_valorDivisionFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorProductoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAlmacenesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUbicacionesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorTipoDocumentoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value=""; 	
}

function eliminarValoresSociedad(){
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorProductoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAlmacenesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUbicacionesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorTipoDocumentoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value=""; 
}

function eliminarValoresUnidad(){
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorProductoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAlmacenesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUbicacionesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorTipoDocumentoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value=""; 
}

function eliminarValoresLinea(){
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorProductoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAlmacenesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUbicacionesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorTipoDocumentoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value=""; 
}

function eliminarValoresProducto(){
	document.getElementById("formularioFiltro_valorProductoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAlmacenesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUbicacionesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorTipoDocumentoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value="";
}

function eliminarValoresAlmacen(){ 
	document.getElementById("formularioFiltro_valorAlmacenesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUbicacionesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorTipoDocumentoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value="";
}

function eliminarValoresUbicacion(){ 
	document.getElementById("formularioFiltro_valorUbicacionesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorTipoDocumentoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value="";
}

function eliminarValoresTipoDocumento(){ 
	document.getElementById("formularioFiltro_valorTipoDocumentoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value="";
}