//  Funcion para cargar los componentes de Jquery
/*$(function () {
	$.datepicker.setDefaults($.datepicker.regional["es"]);
	// Datepicker
	$("#fechaInicio").datepicker({inline:true, dateFormat:"dd/mm/yy"});
	// Datepicker
	$("#fechaFin").datepicker({inline:true, dateFormat:"dd/mm/yy"});	
});*/

function camposObligatorios(idFormulario) {
	var division = document.getElementById('valorDivision');
	var sociedad = document.getElementById('valorSociedad');
	var unidad = document.getElementById('valorUnidad');
	var fechaInicio = document.formularioFiltro.fechaInicio.value;
	var fechaFin = document.formularioFiltro.fechaFin.value;

	var mensaje = ""; 
	
	if(division === null || division.value === null || division.value === "")
	{
		mensaje += "- Debe ingresar la División. \r"; 
	}
	if(sociedad === null || sociedad.value === null || sociedad.value === "")
	{
		mensaje += "- Debe ingresar la Sociedad. \r"; 
	}
	if(unidad === null || unidad.value === null || unidad.value === "")
	{
		mensaje += "- Debe ingresar la Unidad. \r"; 
	}
	if(fechaInicio === null || fechaInicio.value === null || fechaInicio.value === "")
	{
		mensaje += "- Debe ingresar una Fecha de Inicio. \r"; 
	}
	if(fechaFin === null || fechaFin.value === null || fechaFin.value === "")
	{
		mensaje += "- Debe ingresar una Fecha de Fin. \r"; 
	}
	
	
	if (mensaje != ""){
		alert(mensaje);
		return false;
	}
		
	document.getElementById(idFormulario).submit();
}


function camposObligatoriosRepVarProdHTML(idFormulario) {
	var unidad = document.getElementById("valorUnidad");
	var sociedad = document.getElementById("valorSociedad");
	var division = document.getElementById("valorDivision");
	var fechaInicio = document.formularioFiltro.fechaInicio.value;
	var fechaFin = document.formularioFiltro.fechaFin.value;
	var mensaje = ""; 
	if(division === null || division.value === null || division.value === "")
	{
		mensaje += "- Debe ingresar la Divisi\xf3n. \r"; 
	}
	if(sociedad === null || sociedad.value === null || sociedad.value === "")
	{
		mensaje += "- Debe ingresar la Sociedad \r"; 
	}
	if(unidad === null || unidad.value === null || unidad.value === "")
	{
		mensaje += "- Debe ingresar la Unidad \r"; 
	}
	if(fechaInicio === null || fechaInicio.value === null || fechaInicio.value === "")
	{
		mensaje += "- Debe ingresar una Fecha de Inicio \r"; 
	}
	if(fechaFin === null || fechaFin.value === null || fechaFin.value === "")
	{
		mensaje += "- Debe ingresar una Fecha de Fin \r"; 
	}
	
	if (mensaje != "") {
		alert(mensaje);
	} else
	{
		return true; 
	}
}


function insertarLoading(){
	$('#valorCargaCompletada').val("CARGA_COMPLETADA");
	$("#linkGenerarReporteHtmlAjax").html("<img style='border: 0px;' alt='Cargando...' src='../images/ajax-loader.gif' width='220px' height='19px'>");
}	
		
function cambiarActionEXCEL(idFormulario,dirCont){
 var dirAction="";
 for(var i=0; i<dirCont; i++){
 dirAction +="../";}
	$('#'+idFormulario).get(0).setAttribute('action', dirAction+'generarReporteVariablesProduccionEXCEL.action'); 

}
function cambiarActionPDF(idFormulario,dirCont){
 var dirAction="";
 for(var i=0; i<dirCont; i++){
 dirAction +="../";}
	$('#'+idFormulario).get(0).setAttribute('action',dirAction+'generarReporteVariablesProduccionPDF.action'); 
	if(dirCont==0){
	$('#'+idFormulario).get(0).setAttribute('target', '_blank'); 
	}
	else{$('#'+idFormulario).get(0).setAttribute('target', '_self'); 
	}
}	
function cambiarActionHTML(idFormulario, dirCont) {
	var dirAction = "";
	for (var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	$("#" + idFormulario).get(0).setAttribute("action", dirAction + "crearHTMLReporteVariablesProduccion.action");
	if (dirCont == 0) {
		$("#" + idFormulario).get(0).setAttribute("target", "_blank");
	} else {
		$("#" + idFormulario).get(0).setAttribute("target", "_self");
	}
}
function htmlSubmit(idFormulario){
document.getElementById(idFormulario).submit();
}		
function cerrarse(){
window.close();
}
function insertarBotones(Botones){
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


function activarToolTip(){
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
$(window).ready(function() {$.ajax({type: "POST" ,url: "../../borrarTemporalReporteVariablesProduccion.action",data:"valorCargaCompletada=CARGA_COMPLETADA"}); }); 
}