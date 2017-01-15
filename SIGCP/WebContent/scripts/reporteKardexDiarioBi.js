
function cambiarActionEXCEL(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearReporteSI.action");
}
function htmlSubmit(idFormulario) {
	document.getElementById(idFormulario).submit();
}

function camposObligatorios(idFormulario, action) {
	var validadoReq = true;
	var campos = "";

	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	if (document.getElementById("mensajeErrorValidacion") !== null) {
		mensaje = new String(document.getElementById("mensajeErrorValidacion").value);
	}

	

	// Se realizan las validaciones pertinentes
	// Se valida el campo Division
	if (!validarCampoRequeridoFormulario("valorDivision")) {
		validadoReq = false;
		//campos += "- " + divisionTitle + "\r";
		campos += "- Division" + "\r";
	}
	// Se valida el campo Sociedad
	if (!validarCampoRequeridoFormulario("valorSociedad")) {
		validadoReq = false;
		//campos += "- " + sociedadTitle + "\r";
		campos += "- Sociedad" + "\r";
	}
	// Se valida el campo Unidad
	if (!validarCampoRequeridoFormulario("valorUnidad")) {
		validadoReq = false;
		//campos += "- " + unidadTitle + "\r";
		campos += "- Unidad" + "\r";
	}

	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		mensaje += campos;
		alert(mensaje);
	} else {
		if (action == "Submit") {
			htmlSubmit(idFormulario);
		}
	}
}

