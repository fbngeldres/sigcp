//  Funcion para cargar los componentes de Jquery
$(function () {
	$.datepicker.setDefaults($.datepicker.regional["es"]);
	

				// Datepicker
	$("#fechaInicio").datepicker({inline:true, dateFormat:"dd/mm/yy"});
				
				// Datepicker
	$("#fechaFin").datepicker({inline:true, dateFormat:"dd/mm/yy"});
});

//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:			idFormulario.	Ej: formularioFiltro, formularioDivisiones.
function filtrar(idFormulario) {
	document.getElementById(idFormulario).submit();
}

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


function btnFiltrarClicked(idFormulario) {
	
	var validadoReq = true;
	var campos = "";
		
	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var divisionTitle = "";	
	var sociedadTitle = "";
	var unidadTitle = "";
	var lineaNegocioTitle = "";
	var procesoTitle = "";
	var productoTitle = "";
	if (document.getElementById("mensajeErrorValidacion") !== null)
		mensaje = new String(document.getElementById("mensajeErrorValidacion").value);	
	if (document.getElementById("tituloCampoDivision") !== null) 
		divisionTitle = new String(document.getElementById("tituloCampoDivision").value);
	if (document.getElementById("tituloCampoSociedad") !== null) 
		sociedadTitle = new String(document.getElementById("tituloCampoSociedad").value);
	if (document.getElementById("tituloCampoUnidad") !== null) 
		unidadTitle = new String(document.getElementById("tituloCampoUnidad").value);
	if (document.getElementById("tituloCampoLineaNegocio") !== null) 
		lineaNegocioTitle = new String(document.getElementById("tituloCampoLineaNegocio").value);
	if (document.getElementById("tituloCampoProceso") !== null) 
		procesoTitle = new String(document.getElementById("tituloCampoProceso").value);
	if (document.getElementById("tituloCampoProducto") !== null) 
		productoTitle = new String(document.getElementById("tituloCampoProducto").value);

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
	// Se valida el campo Linea de Negocio
	if (!validarCampoRequeridoFormulario("valorLineaNegocio")) {
		validadoReq = false;
		campos += "- "+lineaNegocioTitle+"\r";
	}
	// Se valida el campo Proceso
	if (!validarCampoRequeridoFormulario("valorProceso")) {
		validadoReq = false;
		campos += "- "+procesoTitle+"\r";
	}
	// Se valida el campo Producto
	if (!validarCampoRequeridoFormulario("valorProducto")) {
		validadoReq = false;
		campos += "- "+productoTitle+"\r";
	}
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		$('#valorCargaCompletada').val("CARGA_NO_COMPLETADA");
		mensaje += campos;
		alert(mensaje);
	} else {
		$('#valorCargaCompletada').val("CARGA_COMPLETADA");
		document.getElementById(idFormulario).action = "mostrarDetalleKardexProducto.action";
		document.getElementById(idFormulario).submit();
	}
}

// Funciones para limpiar los combos de opciones
function eliminarValoresDivision(){
	document.getElementById("formularioFiltro_valorDivisionFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value="";  
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value="";
	document.getElementById("formularioFiltro_valorProductoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value=""; 	
}

function eliminarValoresSociedad(){
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value="";  
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value="";
	document.getElementById("formularioFiltro_valorProductoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value=""; 
}

function eliminarValoresUnidad(){
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value="";  
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value="";
	document.getElementById("formularioFiltro_valorProductoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value=""; 
}

function eliminarValoresLinea(){
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value="";  
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value="";
	document.getElementById("formularioFiltro_valorProductoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value=""; 
}

function eliminarValoresProceso(){
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value="";
	document.getElementById("formularioFiltro_valorProductoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value="";
}

function eliminarValoresProducto(){ 
	document.getElementById("formularioFiltro_valorProductoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaInicioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorFechaFinFiltrado").value="";
}
