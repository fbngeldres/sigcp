//  Funcion para cargar los componentes de Jquery
$(function () {
	$.datepicker.setDefaults($.datepicker.regional["es"]);
		
				// Datepicker
	$("#fechaNotificacion").datepicker({inline:true, dateFormat:"dd/mm/yy"});
	
});

function camposObligatorios(idFormulario) {

	var validadoReq = true;
	var campos = "";
		
	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var divisionTitle = "";	
	var sociedadTitle = "";
	var unidadTitle = "";
	if (document.getElementById("mensajeErrorValidacion") !== null)
		mensaje = new String(document.getElementById("mensajeErrorValidacion").value);	
	if (document.getElementById("tituloCampoDivision") !== null) 
		divisionTitle = new String(document.getElementById("tituloCampoDivision").value);
	if (document.getElementById("tituloCampoSociedad") !== null) 
		sociedadTitle = new String(document.getElementById("tituloCampoSociedad").value);
	if (document.getElementById("tituloCampoUnidad") !== null) 
		unidadTitle = new String(document.getElementById("tituloCampoUnidad").value);

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
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		$('#valorCargaCompletada').val("CARGA_NO_COMPLETADA");
		mensaje += campos;
		alert(mensaje);
	} else {
		$('#valorCargaCompletada').val("CARGA_COMPLETADA");
		document.getElementById(idFormulario).submit();
	}

}

function actualizarCodUnidad() {
	var unidad = document.getElementById('valorUnidad');
	if (unidad !== null) {
		document.getElementById('codUnidad').value = unidad.value;
	}
}
function verDetalleNotificacionDiariaCantera(){
	
	var cod = getCheckedValue(document.formularioFiltro.notificacionDiariaId);
	
	if(cod === null || cod === ""){		
		var mensaje = document.getElementById('verCampoOblig').value;
		alert(mensaje);		
		return false;
	}
	
	document.formularioFiltro.action = 'consultarNotificacionDiariaCantera.action';
	document.formularioFiltro.submit();
}
function activarFecha(){
}

// Funciones para limpiar los combos de opciones
function eliminarValoresDivision(){
	document.getElementById("formularioFiltro_valorDivisionFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorActividadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorPuestoTrabajoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value=""; 	
}

function eliminarValoresSociedad(){
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorActividadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorPuestoTrabajoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value=""; 
}

function eliminarValoresUnidad(){
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorActividadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorPuestoTrabajoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value=""; 
}

function eliminarValoresLinea(){
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorActividadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorPuestoTrabajoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value="";
}

function eliminarValoresProceso(){
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorActividadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorPuestoTrabajoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value="";
}

function eliminarValoresActividad(){ 
	document.getElementById("formularioFiltro_valorActividadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorPuestoTrabajoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value="";
}

function eliminarValoresPuestos(){ 
	document.getElementById("formularioFiltro_valorPuestoTrabajoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value="";
}

function eliminarValoresEstado(){ 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value="";
}

function eliminarValoresMes(){ 
	document.getElementById("formularioFiltro_valorMesFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value="";
}

function eliminarValoresAnio(){ 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value="";
}
