//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:			idFormulario.	Ej: formularioFiltro, formularioDivisiones.
function filtrarPlantillasPT(idFormulario) {
	show_platillasPuestoTrabajos();
	document.getElementById(idFormulario).submit();
}
function filtrarPlantillasProductos(idFormulario) {
	show_platillasProductos();
	document.getElementById(idFormulario).submit();
}
function show_procesos() {
	dojo.event.topic.publish("listar_procesos");
}
function show_puestos() {
	dojo.event.topic.publish("listar_puestos");
}
function show_productos() {
	dojo.event.topic.publish("listar_productos");
}
function show_nombre() {
	dojo.event.topic.publish("obtener_nombre");
}
function show_componentes() {
	dojo.event.topic.publish("listar_componentes");
}
function show_platillasPuestoTrabajos() {
	dojo.event.topic.publish("listar_plantillas");
	document.forms[0].ejecutaConsulta.value = "execute";
	document.forms[0].listarPuestosTrabajo.value = "execute";
	document.forms[0].procesoSeleccionadoTemp.value = document.forms[0].procesoSeleccionado.value;
	document.forms[0].puestoTSeleccionadoTemp.value = document.forms[0].puestoTSeleccionado.value;
	document.forms[0].lineaSeleccionadaTemp.value = document.forms[0].lineaSeleccionada.value;
}
function show_platillasProductos() {
	dojo.event.topic.publish("listar_plantillas");
	document.forms[0].ejecutaConsulta.value = "execute";
	document.forms[0].procesoSeleccionadoTemp.value = document.forms[0].procesoSeleccionado.value;
	document.forms[0].puestoTSeleccionadoTemp.value = document.forms[0].puestoTSeleccionado.value;
	document.forms[0].lineaSeleccionadaTemp.value = document.forms[0].lineaSeleccionada.value;
	document.forms[0].productoSeleccionadoTemp.value = document.forms[0].productoSeleccionado.value;
}
function filtrar(idFormulario) {
	var validadoReq = true;
	var campos = "";
		
	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var lineaNegocioTitle = "";
	var procesoTitle = "";
	if (document.getElementById("mensajeErrorValidacion") !== null) {
		mensaje = new String(document.getElementById("mensajeErrorValidacion").value);
	}
	if (document.getElementById("tituloCampoLineaNegocio") !== null) {
		lineaNegocioTitle = new String(document.getElementById("tituloCampoLineaNegocio").value);
	}
	if (document.getElementById("tituloCampoProceso") !== null) {
		procesoTitle = new String(document.getElementById("tituloCampoProceso").value);
	}

	// Se realizan las validaciones pertinentes
	// Se valida el campo Linea de Negocio
	if (!validarCampoRequeridoFormulario("lineaSeleccionada")) {
		validadoReq = false;
		campos += "- " + lineaNegocioTitle + "\r";
	}
	// Se valida el campo Proceso
	if (!validarCampoRequeridoFormulario("procesoSeleccionado")) {
		validadoReq = false;
		campos += "- " + procesoTitle + "\r";
	}
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		mensaje += campos;
		alert(mensaje);
	} else {
		document.getElementById(idFormulario).action = "filtrarPlantillaProductos.action";
		document.getElementById(idFormulario).submit();
	}
}
function filtrar_porpt(idFormulario) {
	var validadoReq = true;
	var campos = "";
		
	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var lineaNegocioTitle = "";
	var procesoTitle = "";
	if (document.getElementById("mensajeErrorValidacion") !== null) {
		mensaje = new String(document.getElementById("mensajeErrorValidacion").value);
	}
	if (document.getElementById("tituloCampoLineaNegocio") !== null) {
		lineaNegocioTitle = new String(document.getElementById("tituloCampoLineaNegocio").value);
	}
	if (document.getElementById("tituloCampoProceso") !== null) {
		procesoTitle = new String(document.getElementById("tituloCampoProceso").value);
	}

	// Se realizan las validaciones pertinentes
	// Se valida el campo Linea de Negocio
	if (!validarCampoRequeridoFormulario("lineaSeleccionada")) {
		validadoReq = false;
		campos += "- Debe seleccionar una L\xednea de Negocio \r";
	}
	// Se valida el campo Proceso
	if (!validarCampoRequeridoFormulario("procesoSeleccionado")) {
		validadoReq = false;
		campos += "- Debe seleccionar un Proceso \r";
	}
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		mensaje += campos;
		alert(mensaje);
	} else {
		document.getElementById(idFormulario).action = "filtrarPlantillasPuestoTrabajo.action";
		document.getElementById(idFormulario).submit();
	}
}
function cambiarEstadoReporteECSPT(accion) {
	var cod = getCheckedValue(document.getElementById("frm_listarPlantillasPT").elements["plantilla.codigoPlantilla"]);

	if (cod == null || cod == "") {
		alert("Debe seleccionar una Plantilla por Puesto de Trabajo para " + accion);
		return false;
	}
	if (accion == "Activar") {
		document.getElementById("frm_listarPlantillasPT").action = "activarReporteECSPuestoTrabajo.action";
	} else {
		document.getElementById("frm_listarPlantillasPT").action = "inactivarReporteECSPuestoTrabajo.action";
	}
	document.getElementById("frm_listarPlantillasPT").submit();
}

