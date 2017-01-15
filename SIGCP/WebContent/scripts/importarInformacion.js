function ImportarInformacionInterfaz(idFormulario) {
	
	var pregunta = confirm("Se proceder\xE1 a eliminar los registros y volver a cargarlos. Desea Continuar?");
	
	
	if(!pregunta){
		return false;
	}
	var resultado = false;
	var validadoReq = true;
	var campos = "";

	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var divisionTitle = "";
	var sociedadTitle = "";
	var unidadTitle = "";

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
		resultado = true;
	}

	resultadorValidarArchivo = chequearArchivo();

	resultado = resultadorValidarArchivo && resultado;

	if (resultado) {

		$("#valorCargaCompletada").val("CARGA_COMPLETADA");
		insertarCarga("linkImportar");

	}
	return resultado;
}

function chequearArchivo() {

	try {

		var ext = document.formularioCargarArchivo.userImage.value;
		var ext1 = document.formularioCargarArchivo.userImage.value;
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
	} catch (e) {
		alert("Error al validar archivo " + e.message);
	}
	return false;
}

function filtrar(idFormulario) {

	$('#valorCargaCompletada').val("CARGA_COMPLETADA");
	insertarCarga('progressListar');
	document.getElementById(idFormulario).submit();

}