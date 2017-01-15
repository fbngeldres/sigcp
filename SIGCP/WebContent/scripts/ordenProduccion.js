function grabarOrdenFormularioIngresarOrden(idFormulario) {

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
		mensaje += campos;
		alert(mensaje);
	} else {
		document.getElementById(idFormulario).action = "grabarOrdenProduccion.action";
		document.getElementById(idFormulario).submit();
	}
}

function grabarOrdenFormularioNuevaOrden(idFormulario) {

	var validadoReq = true;
	var campos = "";
		
	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var capacidadOperativaToneladas = "";	
	var capacidadOperativaDias = "";
	var dosificacionPlan = "";
	if (document.getElementById("mensajeErrorValidacion") !== null)
		mensaje = new String(document.getElementById("mensajeErrorValidacion").value);	
	if (document.getElementById("tituloCampoProdPlanTM") !== null) 
		capacidadOperativaToneladas = new String(document.getElementById("tituloCampoProdPlanTM").value);
	if (document.getElementById("tituloCampoProdPlanDias") !== null) 
		capacidadOperativaDias = new String(document.getElementById("tituloCampoProdPlanDias").value);
	if (document.getElementById("tituloCampoDosificPlan") !== null) 
		dosificacionPlan = new String(document.getElementById("tituloCampoDosificPlan").value);

	// Se realizan las validaciones pertinentes
	// Se valida el campo Operaciones - Produccion Planificada Toneladas Metricas TM
	if (!validarCampoRequeridoFormulario("formularioRegistro_capacidadOperativaToneladas") 
		|| !validarCampoFormularioValorCero("formularioRegistro_capacidadOperativaToneladas")) {
		validadoReq = false;
		campos += "- "+capacidadOperativaToneladas+"\r";
	}
	// Se valida el campo Operaciones - Produccion Planificada Dias
	if (!validarCampoRequeridoFormulario("formularioRegistro_capacidadOperativaDias")
		|| !validarCampoFormularioValorCero("formularioRegistro_capacidadOperativaDias")) {
		validadoReq = false;
		campos += "- "+capacidadOperativaDias+"\r";
	}
	// Se valida el campo Componentes - Dosificacion Planificada
	if (!validarCampoRequeridoFormulario("formularioRegistro_dosificacionPlan")
		|| !validarCampoFormularioValorCero("formularioRegistro_dosificacionPlan")) {
		validadoReq = false;
		campos += "- "+dosificacionPlan+"\r";
	}	
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		mensaje += campos;
		alert(mensaje);
	} else {
		document.getElementById(idFormulario).action = "grabarOrdenProduccion.action";
		document.getElementById(idFormulario).submit();
	}
}


//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:			idFormulario.	Ej: formularioFiltro, formularioDivisiones.
function filtrar(idFormulario) {
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
		document.getElementById(idFormulario).action = "filtrarOrdenProduccion.action";
		document.getElementById(idFormulario).submit();
	}

}
//--------------------------------------------------------------------------------------------
//	LlenarComboDias:	Funcion que permite llenar un combo de dias con los dias de la semana
//						a partir de una fechaInicio y hasta una fechaFin
//						comboDias: el combo a llenar con la semana
//						fechaInicio: textfield donde se encuentra la fecha de inicio
//---------------------------------------------------------------------------------------------
function llenarComboDias(nombreCombo) {
	FECHA_INICIO = new String(document.formularioFiltro.fechaInicio.value);
	FECHA_FIN = new String(document.formularioFiltro.fechaFinRegistro.value);
	var arregloInicio = FECHA_INICIO.split("/");
	var diaInicio = arregloInicio[0];
	var mesInicio = arregloInicio[1];
	var annoInicio = arregloInicio[2];
	mesInicio = mesInicio - 1;
	var fechaInicio = new Date(annoInicio, mesInicio, diaInicio);
	var diasSiguiente = 0;
	var stringDia = "";	//Este string es una variabla auxiliar para guardar el dia y agregarlo al combo
	limpiarCombo(document.formularioFiltro.valorDia);	
	
	//Agregamos la primera opcion del combo vacia 
	stringDia = "";
	addOption(document.formularioFiltro.valorDia, stringDia, 0);
	for (var i = 0; i < 7; i++) {
		diasSiguiente = fechaInicio.getTime() + ((24 * 60 * 60 * 1000) * i);
	
		// Cosntruimos el objeto Date con la cantidad de
		// miliseg que representan cada dia de la semana
		var fecha = new Date(diasSiguiente);
		var dia = fecha.getDate();
		var mes = fecha.getMonth() + 1;
		var anno = fecha.getFullYear();
		stringDia = dia + "/" + mes + "/" + anno;		
		
		// agregamos la opcion
		addOption(document.formularioFiltro.valorDia, stringDia, i);
	}
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
	if (operacion == "modificar") {
		checkboxes = document.getElementById(idFormulario).elements[campoCodigo];
		var cont = 0;
		for (var x = 0; x < checkboxes.length; x++) {
			if (checkboxes[x].checked) {
				cont = cont + 1;
			}
		}
		if (cont > 1) {
			alert("No debe seleccionar mas " + mensajeEntidad + " para " + operacion);
			return false;
		}
	}
	document.getElementById(idFormulario).action = operacion + entidad + ".action";
	document.getElementById(idFormulario).submit();
}
function nuevoFormulario(idFormulario, entidad, operacion) {
	document.getElementById(idFormulario).action = operacion + entidad + ".action";
	document.getElementById(idFormulario).submit();
}
function validarSabadoRegistrarPrograma() {
	var resultado = validarSabado();
	if (resultado == true) {
		update("comboDias");
		return false;
	}
}
function bloquearAnno() {
	document.formularioFiltro.valorAnio.value = "";
	document.formularioFiltro.valorAnio.disabled = true;
}
function bloquearMes() {
	document.formularioFiltro.valorMes.value = "";
	document.formularioFiltro.valorMes.disabled = true;
}
function bloquearFechaFin() {
	document.formularioFiltro.fechaFin.value = "";
	document.formularioFiltro.fechaFin.disabled = true;
}
function bloquearFechaInicio() {
	document.formularioFiltro.fechaInicio.value = "";
	document.formularioFiltro.fechaInicio.disabled = true;
}
function validarAnio() {
	var anio = document.formularioFiltro.valorAnio.value;
	if (anio == "") {
		document.formularioFiltro.valorMes.value = "";
		alert("Primero debe especificar el a\xf1o.");
	}
}
function generarReporte(idFormulario) {
	var codigoProceso = new String(document.formularioFiltro.valorProceso);
	var FECHA_INICIO = new String(document.formularioFiltro.fechaInicio.value);
	var FECHA_FIN = new String(document.formularioFiltro.fechaFin.value);
	if (codigoProceso == "undefined") {
		alert("Debe seleccionar un proceso");
		return false;
	}
	if (FECHA_INICIO == null || FECHA_INICIO == "") {
		alert("Debe seleccionar la fecha de inicio");
		return false;
	}
	if (FECHA_FIN == null || FECHA_FIN == "") {
		alert("Debe seleccionar la fecha de fin");
		return false;
	}
	filtrar(idFormulario);
}
function seleccionar_todo(idFormulario, valor) {
	for (i = 0; i < document.getElementById(idFormulario).elements.length; i++) {
		if (document.getElementById(idFormulario).elements[i].type == "checkbox") {
			document.getElementById(idFormulario).elements[i].checked = valor;
		}
	}
}

		//  Funcion para cargar los componentes de Jquery
$(function () {
						// Accordion
	$("#accordion").accordion({header:"h3"});
});

		//funcion para envio del formulario desde las diferentes funcionalidades del menu
function seleccionarUnidad() {
	document.formularioRegistro.action = "SeleccionarUnidadNegocio.action";
	document.formularioRegistro.submit();
}
function grabarPlan() {
	document.formularioRegistro.action = "IngresarPlanAnual.action";
	document.formularioRegistro.submit();
}
function show_productos() {
	dojo.event.topic.publish("show_productos");
}
function show_otros_productos() {
	dojo.event.topic.publish("show_otros_productos");
}
function show_consultas() {
	dojo.event.topic.publish("show_consultas");
}
function show_conceptos() {
	dojo.event.topic.publish("show_conceptos");
	dojo.event.topic.publish("show_componentes");
	dojo.event.topic.publish("show_operaciones");
}
function show_indices() {
	dojo.event.topic.publish("show_indices");
}
function validarFormularios() {
	var idFormulario = "formularioRegistro";
	var campoCodigo = "produccionEstimada";
	var mensajeEntidad = "el estimado por Operaci\xf3n";
	var cod = document.getElementById(idFormulario).elements[campoCodigo];
	if (cod == null) {
		alert("Debe tener " + mensajeEntidad + " para poder grabar");
		return false;
	}
	for (var i = 0; i < cod.length; i++) {
		if (cod[i].value == null || cod[i].value == "") {
			alert("Debe llenar " + mensajeEntidad + " para poder grabar");
			return false;
		}
	}
	campoCodigo = "dosplanificada";
	mensajeEntidad = "el estimado por Dosificacion de cada Componente";
	cod = document.getElementById(idFormulario).elements[campoCodigo];
	if (cod == null) {
		alert("Debe tener " + mensajeEntidad + " para poder grabar");
		return false;
	}
	for (var i = 0; i < cod.length; i++) {
		if (cod[i].value == null || cod[i].value == "") {
			alert("Debe llenar " + mensajeEntidad + " para poder grabar");
			return false;
		}
	}
	document.getElementById(idFormulario).action = "grabarOrdenProduccion.action";
	document.getElementById(idFormulario).submit();
}


