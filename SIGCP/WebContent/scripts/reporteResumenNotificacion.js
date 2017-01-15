function camposObligatorios(idFormulario,action) {

	var validadoReq = true;
	var campos = "";
		
	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var divisionTitle = "";	
	var sociedadTitle = "";
	var unidadTitle = "";
	var puestoTrabajoTitle = "";
	var productoTitle = "";
	var anioTitle = "";
	var mesTitle = "";
	if (document.getElementById("mensajeErrorValidacion") !== null)
		mensaje = new String(document.getElementById("mensajeErrorValidacion").value);	
	if (document.getElementById("tituloCampoDivision") !== null) 
		divisionTitle = new String(document.getElementById("tituloCampoDivision").value);
	if (document.getElementById("tituloCampoSociedad") !== null) 
		sociedadTitle = new String(document.getElementById("tituloCampoSociedad").value);
	if (document.getElementById("tituloCampoUnidad") !== null) 
		unidadTitle = new String(document.getElementById("tituloCampoUnidad").value);
	if (document.getElementById("tituloCampoPuestoTrabajo") !== null) 
		puestoTrabajoTitle = new String(document.getElementById("tituloCampoPuestoTrabajo").value);
	if (document.getElementById("tituloCampoProducto") !== null) 
		productoTitle = new String(document.getElementById("tituloCampoProducto").value);
	if (document.getElementById("tituloCampoAnio") !== null) 
		anioTitle = new String(document.getElementById("tituloCampoAnio").value);
	if (document.getElementById("tituloCampoMes") !== null) 
		mesTitle = new String(document.getElementById("tituloCampoMes").value);

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
	// Se valida el campo Puesto de Trabajo
	if (!validarCampoRequeridoFormulario("valorPuestoTrabajo")) {
		validadoReq = false;
		campos += "- "+puestoTrabajoTitle+"\r";
	}
	// Se valida el campo de Mes de Inicio
	if (!validarCampoRequeridoFormulario("fechaInicio")) {
		validadoReq = false;
		campos += "- Debe seleccionar el Mes de Inicio \r";
	}
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		mensaje += campos;
		alert(mensaje);
	} else {
		if (action == "Submit") {
			document.getElementById(idFormulario).submit();
		} else if (action == "Ajax") {
			insertarLoading();
		}
	}
}

// Inserta la barra de Cargando hasta que se genera el Reporte
function insertarLoading() {
	$('#valorCargaCompletada').val("CARGA_COMPLETADA");
	$("#linkGenerarReporteHtmlAjax").html("<img style='border: 0px;' alt='Cargando...' src='../images/ajax-loader.gif' width='220px' height='19px'>");
}	

// Cambia el action del Formulario del Reporte de acuerdo a la accion de exportacion a usar (EXCEL, PDF)
function cambiarActionFormularioReporte(idFormulario,tipoAction){
	var nombreAction = "";
	if(tipoAction == "EXCEL") {
		nombreAction = "generarReporteResumenEXCEL.action";
	} else if(tipoAction == "PDF") {
		nombreAction = "generarReporteResumenPDF.action";
		$('#'+idFormulario).get(0).setAttribute('target', '_blank'); 
	} 
	$('#'+idFormulario).get(0).setAttribute('action', nombreAction); 
}

function htmlSubmit(idFormulario){
	document.getElementById(idFormulario).submit();
}	
	
function cerrarse(){
	window.close();
}

function insertarBotones(Botones) {
	$("head").append("<link>");
	css = $("head").children(":last");
	css.attr({
        rel: "stylesheet",
        type: "text/css",
        href: "../../../css/SGCP-toolTip.css"
	});
	$("span:contains('DivBotones1234Reporte')").parent().html("<div id=\"MasterBotones\"><div>");
	$("#MasterBotones").html(Botones);
	activarToolTip();
}

function activarToolTip() {
	var currentTime = new Date();																		
	var URL = '../../../scripts/tooltip/jquery.tools.min.js?time=1' + currentTime.getSeconds();

	$.getScript(URL , function () {
	// create custom animation algorithm for jQuery called "bouncy"
	$.easing.bouncy = function (x, t, b, c, d) {
	    var s = 1.70158;
	    if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525))+1)*t - s)) + b;
	    return c/2*((t-=2)*t*(((s*=(1.525))+1)*t + s) + 2) + b;
	}

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

	$("#MasterBotones img[title]").tooltip({ offset:[-10,0],position: 'top center',effect: 'bouncy'}); });
	$(window).ready(function() {$.ajax({type: "POST" ,url: "../../borrarTemporalReporteResumen.action",data:"valorCargaCompletada=CARGA_COMPLETADA"}); }); 
}
 