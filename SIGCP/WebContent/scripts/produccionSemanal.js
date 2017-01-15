//  Funcion para cargar los componentes de Jquery
$(function () {
	$.datepicker.setDefaults($.datepicker.regional["es"]);
	var name = $("#name"), email = $("#email"), password = $("#password"), allFields = $([]).add(name).add(email).add(password), tips = $("#validateTips");
	

				// Accordion
	$("#accordion").accordion({header:"h3"});
	
				// Tabs
	$("#tabs").tabs();
	

				// Dialog			
	$("#dialog").dialog({autoOpen:false, width:600, buttons:{"Create an account":function () {
		var bValid = true;
		allFields.removeClass("ui-state-error");
		bValid = bValid && checkLength(name, "username", 3, 16);
		bValid = bValid && checkLength(email, "email", 6, 80);
		bValid = bValid && checkLength(password, "password", 5, 16);
		bValid = bValid && checkRegexp(name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter.");
					// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
		bValid = bValid && checkRegexp(email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com");
		bValid = bValid && checkRegexp(password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9");
		if (bValid) {
			$("#users tbody").append("<tr>" + "<td>" + name.val() + "</td>" + "<td>" + email.val() + "</td>" + "<td>" + password.val() + "</td>" + "</tr>");
			$(this).dialog("close");
		}
	}, Cancel:function () {
		$(this).dialog("close");
	}}, close:function () {
		allFields.val("").removeClass("ui-state-error");
	}});
	$("#create-user").click(function () {
		$("#dialog").dialog("open");
	}).hover(function () {
		$(this).addClass("ui-state-hover");
	}, function () {
		$(this).removeClass("ui-state-hover");
	}).mousedown(function () {
		$(this).addClass("ui-state-active");
	}).mouseup(function () {
		$(this).removeClass("ui-state-active");
	});

				
				// Dialog Link
	$("#dialog_link").click(function () {
		$("#dialog").dialog("open");
		return false;
	});

				// Datepicker
	$("#fechaInicio").datepicker({inline:true, dateFormat:"dd/mm/yy"});
				
				// Datepicker
	$("#fechaFin").datepicker({inline:true, dateFormat:"dd/mm/yy"});
								
				// Slider
	$("#slider").slider({range:true, values:[17, 67]});
				
				// Progressbar
	$("#progressbar").progressbar({value:20});
				
				//hover states on the static widgets
	$("#dialog_link, ul#icons li").hover(function () {
		$(this).addClass("ui-state-hover");
	}, function () {
		$(this).removeClass("ui-state-hover");
	});
});

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
	var lineaNegocioTitle = "";
	var procesoTitle = "";
	var fechaInicioTitle = "";
	var fechaFinTitle = "";
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
	if (document.getElementById("tituloCampoFechaInicio") !== null) 
		fechaInicioTitle = new String(document.getElementById("tituloCampoFechaInicio").value);
	if (document.getElementById("tituloCampoFechaFin") !== null) 
		fechaFinTitle = new String(document.getElementById("tituloCampoFechaFin").value);

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
	// Se valida el campo Fecha Inicio
	if (!validarCampoRequeridoFormulario("fechaInicio")) {
		validadoReq = false;
		campos += "- "+fechaInicioTitle+"\r";
	}
	// Se valida el campo Fecha Fin
	if (!validarCampoRequeridoFormulario("fechaFin")) {
		validadoReq = false;
		campos += "- "+fechaFinTitle+"\r";
	}
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		$('#valorCargaCompletada').val("CARGA_NO_COMPLETADA");
		mensaje += campos;
		alert(mensaje);
	} else {
		$('#valorCargaCompletada').val("CARGA_COMPLETADA");
		//document.getElementById(idFormulario).action = "listarProduccionSemanal.action";
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

function actualizarGraficoEstadistica() {  
	dojo.event.topic.publish("actualizarComparativo");  
}
 
function procesarModificarFormulario(idFormulario, campoCodigo, mensajeEntidad, entidad, operacion) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar " + mensajeEntidad + " a " + operacion);
		return false;
	}
	var url = window.location.toString();
	var nuevoUrl = url.substring(0, url.lastIndexOf("/")+1);
	nuevoUrl = nuevoUrl.concat("modificarProduccionSemanal.action?i="+cod);
	window.location.replace(nuevoUrl);
}

// Funciones para limpiar los combos de opciones
function eliminarValoresDivision(){
	document.getElementById("formularioFiltro_valorDivisionFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value=""; 	
}

function eliminarValoresSociedad(){ 
	document.getElementById("formularioFiltro_valorSociedadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value=""; 	
}

function eliminarValoresUnidad(){
	document.getElementById("formularioFiltro_valorUnidadFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value=""; 
}

function eliminarValoresLinea(){
	document.getElementById("formularioFiltro_valorLineaNegocioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value="";
}

function eliminarValoresProceso(){
	document.getElementById("formularioFiltro_valorProcesoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value="";
}

function eliminarValoresEstado(){ 
	document.getElementById("formularioFiltro_valorEstadoFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value="";
}

function eliminarValoresAnio(){ 
	document.getElementById("formularioFiltro_valorAnioFiltrado").value=""; 
	document.getElementById("formularioFiltro_valorMesFiltrado").value="";
}

function eliminarValoresMes(){ 
	document.getElementById("formularioFiltro_valorMesFiltrado").value="";
}
