
function htmlSubmit(idFormulario) {
	document.getElementById(idFormulario).submit();
}
function cambiarActionEXCEL(idFormulario, dirCont) {
	if (camposObligatorios() && validarSeleccionCmbReporte()) {
		var valorReporte = $("#valorReporte").val();
//IF REPORTE ES PARTE DIARIO 
		if (valorReporte == 1) {
			generarReporteDetalleParteDiarioEXCEL(idFormulario, dirCont);
		}
		if (valorReporte == 2) {
			generarReportePorPuestoTrabajoEXCEL(idFormulario, dirCont);
		}
		if (valorReporte == 3) {
			generarReporteStockEXCEL(idFormulario, dirCont);
		}
		if (valorReporte == 4) {
			generarReporteProduccionEXCEL(idFormulario, dirCont);
		}
	}
}
function generarReportePorPuestoTrabajoEXCEL(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearReporteParteDiarioEXCEL.action");
	htmlSubmit(idFormulario);
}
function generarReporteDetalleParteDiarioEXCEL(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearReporteDetalleParteDiarioEXCEL.action");
	htmlSubmit(idFormulario);
}
function generarReporteStockEXCEL(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	document.getElementById("xlsPDF").value = "0";
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearReporteStock.action");
	if (dirCont == 0) {
		$("#" + idFormulario).get(0).setAttribute("target", "_blank");
	} else {
		$("#" + idFormulario).get(0).setAttribute("target", "_self");
	}
	htmlSubmit(idFormulario);
}
function generarReporteProduccionEXCEL(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	document.getElementById("xlsPDF").value = "0";
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearReporteProduccion.action");
	if (dirCont == 0) {
		$("#" + idFormulario).get(0).setAttribute("target", "_blank");
	} else {
		$("#" + idFormulario).get(0).setAttribute("target", "_self");
	}
	htmlSubmit(idFormulario);
}
function generarReportePorPuestoTrabajoPDF(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearReporteParteDiarioPDF.action");
	if (dirCont == 0) {
		$("#" + idFormulario).get(0).setAttribute("target", "_blank");
	} else {
		$("#" + idFormulario).get(0).setAttribute("target", "_self");
	}
	htmlSubmit(idFormulario);
}
function cerrarse() {
	window.close();
}
function elegirReporte(cmbReporte) {
	try {
		if (cmbReporte.value != -1) {
			$("#formularioReporte").fadeIn(10);
		} else {
			$("#formularioReporte").fadeOut(10);
		}
	
	//reporte Parte Diario
		if (cmbReporte.value == 1) {
			$("#lblproceso").fadeIn(10);
			$("#cmbproceso").fadeIn(10);
			$("#lblproducto").fadeIn(10);
			$("#cmbproducto").fadeIn(10);
			$("#lblpuestotrabajo").fadeOut(10);
			$("#cmbpuestotrabajo").fadeOut(10);
		}
	
	//Reporte Puesto de Tabajo
		if (cmbReporte.value == 2) {
			$("#lblproceso").fadeIn(10);
			$("#cmbproceso").fadeIn(10);
			$("#lblproducto").fadeIn(10);
			$("#cmbproducto").fadeIn(10);
			$("#lblpuestotrabajo").fadeIn(10);
			$("#cmbpuestotrabajo").fadeIn(10);
		}
	
	//Reporte Gestion Stock
		if (cmbReporte.value == 3) {
			$("#lblproceso").fadeOut(10);
			$("#cmbproceso").fadeOut(10);
			$("#lblproducto").fadeOut(10);
			$("#cmbproducto").fadeOut(10);
			$("#lblpuestotrabajo").fadeOut(10);
			$("#cmbpuestotrabajo").fadeOut(10);
		}
	
	//Reporte Produccion Diario
		if (cmbReporte.value == 4) {
			$("#lblproceso").fadeOut(10);
			$("#cmbproceso").fadeOut(10);
			$("#lblproducto").fadeOut(10);
			$("#cmbproducto").fadeOut(10);
			$("#lblpuestotrabajo").fadeOut(10);
			$("#cmbpuestotrabajo").fadeOut(10);
		}
	}
	catch (e) {
		alert("error-->" + e);
	}
}
function validarSeleccionCmbReporte() {
	cmbReporte = $("#valorReporte").val();
	if (cmbReporte == -1) {
		alert("Seleccione un Alcance de Reporte");
		return false;
	}
	return true;
}
/* Al hacer Click en PDF */
function cambiarActionPDF(idFormulario, dirCont) {
	if (camposObligatorios() && validarSeleccionCmbReporte()) {
		var valorReporte = $("#valorReporte").val();
		// IF REPORTE ES PARTE DIARIO
		if (valorReporte == 1) {
			generarReporteParteDiario(idFormulario, dirCont);
		} else {
			if (valorReporte == 2) {
				generarReportePorPuestoTrabajoPDF(idFormulario, dirCont);
			} else {
				if (valorReporte == 3) {
					generarReporteStockPDF(idFormulario, dirCont);
				} else {
					if (valorReporte == 4) {
						generarReporteProduccionPDF(idFormulario, dirCont);
					}
				}
			}
		}
	}
}
function generarReporteProduccionPDF(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	document.getElementById("xlsPDF").value = "1";
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearReporteProduccion.action");
	if (dirCont == 0) {
		$("#" + idFormulario).get(0).setAttribute("target", "_blank");
	} else {
		$("#" + idFormulario).get(0).setAttribute("target", "_self");
	}
	htmlSubmit(idFormulario);
}
function generarReporteStockPDF(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	document.getElementById("xlsPDF").value = "1";
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearReporteStock.action");
	if (dirCont == 0) {
		$("#" + idFormulario).get(0).setAttribute("target", "_blank");
	} else {
		$("#" + idFormulario).get(0).setAttribute("target", "_self");
	}
	htmlSubmit(idFormulario);
}
function generarReporteParteDiario(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearReporteDetalleParteDiario.action");
	if (dirCont == 0) {
		$("#" + idFormulario).get(0).setAttribute("target", "_blank");
	} else {
		$("#" + idFormulario).get(0).setAttribute("target", "_self");
	}
	htmlSubmit(idFormulario);
}
function camposObligatorios() {
	var unidad = document.getElementById("valorUnidad");
	var sociedad = document.getElementById("valorSociedad");
	var division = document.getElementById("valorDivision");
	
	//se cambio el orden de validacion entre diviosn y unidad
	if (division === null || division.value === null || division.value === "") {
		alert("Seleccione Division");
		return false;
	}
	if (sociedad === null || sociedad.value === null || sociedad.value === "") {
		alert("Seleccione Sociedad");
		return false;
	}
	if (unidad === null || unidad.value === null || unidad.value === "") {
		alert("Seleccione Unidad");
		return false;
	}
	
	return true;
}

