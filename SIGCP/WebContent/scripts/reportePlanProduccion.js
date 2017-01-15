
function camposObligatorios(idFormulario) {
	var unidad = document.getElementById("valorUnidad");
	var sociedad = document.getElementById("valorSociedad");
	var division = document.getElementById("valorDivision");
	var lineaNegocio = document.getElementById("valorLineaNegocio");
	var proceso = document.getElementById("valorProceso");
	var producto = document.getElementById("valorProducto");
	var mesInicio = document.getElementById("valorMesInicio");
	var mesFin = document.getElementById("valorMesFin");
	var mensaje = "";
	if (division === null || division.value === null || division.value === "") {
		mensaje += "- Debe seleccionar una Divisi\xf3n. \r";
	}
	if (sociedad === null || sociedad.value === null || sociedad.value === "") {
		mensaje += "- Debe seleccionar una Sociedad. \r";
	}
	if (unidad === null || unidad.value === null || unidad.value === "") {
		mensaje += "- Debe seleccionar una Unidad. \r";
	}
	if (lineaNegocio === null || lineaNegocio.value === null || lineaNegocio.value === "") {
		mensaje += "- Debe seleccionar una L\xednea de Negocio. \r";
	}
	if (proceso === null || proceso.value === null || proceso.value === "") {
		mensaje += "- Debe seleccionar un Proceso. \r";
	}
	if (producto === null || producto.value === null || producto.value === "") {
		mensaje += "- Debe seleccionar un Producto. \r";
	}
	if (mesInicio === null || mesInicio.value === null || mesInicio.value === "") {
		mensaje += "- Debe seleccionar un Mes de Inicio. \r";
	}
	if (mesFin === null || mesFin.value === null || mesFin.value === "") {
		mensaje += "- Debe seleccionar un Mes Fin. \r";
	}
	if (mensaje != "") {
		alert(mensaje);
	} else {
		document.getElementById(idFormulario).submit();
	}
}


function camposObligatoriosHTML(idFormulario) {
	var unidad = document.getElementById("valorUnidad");
	var sociedad = document.getElementById("valorSociedad");
	var division = document.getElementById("valorDivision");
	var lineaNegocio = document.getElementById("valorLineaNegocio");
	var proceso = document.getElementById("valorProceso");
	var producto = document.getElementById("valorProducto");
	var mesInicio = document.getElementById("valorMesInicio");
	var mesFin = document.getElementById("valorMesFin");
	var mensaje = "";
	if (division === null || division.value === null || division.value === "") {
		mensaje += "- Debe seleccionar una Divisi\xf3n. \r";
	}
	if (sociedad === null || sociedad.value === null || sociedad.value === "") {
		mensaje += "- Debe seleccionar una Sociedad. \r";
	}
	if (unidad === null || unidad.value === null || unidad.value === "") {
		mensaje += "- Debe seleccionar una Unidad. \r";
	}
	if (lineaNegocio === null || lineaNegocio.value === null || lineaNegocio.value === "") {
		mensaje += "- Debe seleccionar una L\xednea de Negocio. \r";
	}
	if (proceso === null || proceso.value === null || proceso.value === "") {
		mensaje += "- Debe seleccionar un Proceso. \r";
	}
	if (producto === null || producto.value === null || producto.value === "") {
		mensaje += "- Debe seleccionar un Producto. \r";
	}
	if (mesInicio === null || mesInicio.value === null || mesInicio.value === "") {
		mensaje += "- Debe seleccionar un Mes de Inicio. \r";
	}
	if (mesFin === null || mesFin.value === null || mesFin.value === "") {
		mensaje += "- Debe seleccionar un Mes Fin. \r";
	}
	if (mensaje != "") {
		alert(mensaje);
	} else
	{
		return true; 
	}
}
function llenarDivReporte() {
	alert();
}
function insertarLoading() {
	$("#linkGenerarReporteHtmlAjax").html("<img style='border: 0px;' alt='Cargando...' src='../images/ajax-loader.gif' width='220px' height='19px'>");
}
function cambiarActionEXCEL(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearReportePlanProduccionEXCEL.action");
}
function cambiarActionPDF(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearReportePlanProduccionPDF.action");
	if (dirCont == 0) {
		$("#" + idFormulario).get(0).setAttribute("target", "_blank");
	} else {
		$("#" + idFormulario).get(0).setAttribute("target", "_self");
	}
}
function cambiarActionHTML(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearHTMLReportePlanProduccion.action");
	if (dirCont == 0) {
		$("#" + idFormulario).get(0).setAttribute("target", "_blank");
	} else {
		$("#" + idFormulario).get(0).setAttribute("target", "_self");
	}
}
function htmlSubmit(idFormulario) {
	document.getElementById(idFormulario).submit();
}
function cerrarse() {
	window.close();
}

// create custom animation algorithm for jQuery called "bouncy"
$.easing.bouncy = function (x, t, b, c, d) {
	var s = 1.70158;
	if ((t /= d / 2) < 1) {
		return c / 2 * (t * t * (((s *= (1.525)) + 1) * t - s)) + b;
	}
	return c / 2 * ((t -= 2) * t * (((s *= (1.525)) + 1) * t + s) + 2) + b;
};

// create custom tooltip effect for jQuery Tooltip
$.tools.tooltip.addEffect("bouncy",

	// opening animation
	function(done) {
		this.getTip().animate({top: '+=15'}, 500, 'bouncy', done).show();
	},

	// closing animation
	function(done) {
		this.getTip().animate({top: '-=15'}, 500, 'bouncy', function()  {
			$(this).hide();
			done.call();
		});
	}
);

			

