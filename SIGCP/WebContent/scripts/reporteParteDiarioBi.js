//  Funcion para cargar los componentes de Jquery

function validarBoxExcel(){

if($('#valorEXCEL').attr('checked')===false){

	$("#valorDivision option[value=]").attr("selected",true);
	$("#valorSociedad option[value=]").attr("selected",true);
	$("#valorUnidad option[value=]").attr("selected",true);
	$("#valorLineaNegocio option[value=]").attr("selected",true);
	
	$('#valorDivision').attr('disabled','true');
	$('#valorSociedad').attr('disabled','true');
	$('#valorUnidad').attr('disabled','true');
	$('#valorLineaNegocio').attr('disabled','true');
	
	}
	else{
	//$('#valorBI').attr('checked',false);
	$('#valorDivision').removeAttr('disabled');
		$('#valorSociedad').removeAttr('disabled');
	$('#valorUnidad').removeAttr('disabled');
	$('#valorLineaNegocio').removeAttr('disabled');

	}
}

function asignarAction(){
$('#valorCargaCompletada').val("CARGA_COMPLETADA");
$("#linkGenerarReporteHtmlAjax").html("<img style='border: 0px;' alt='Cargando...' src='../images/ajax-loader.gif' width='220px' height='19px'>");
}

function cambiarActionEXCEL(idFormulario,dirCont){
 var dirAction="";
 for(var i=0; i<dirCont; i++){
 dirAction +="../";}
	$('#'+idFormulario).get(0).setAttribute('action', dirAction+'crearReporteParteTecnicoEXCELBi.action'); 
}

function cambiarActionPDF(idFormulario,dirCont){
 var dirAction="";
 for(var i=0; i<dirCont; i++){
 dirAction +="../";}
	$('#'+idFormulario).get(0).setAttribute('action',dirAction+'crearReporteParteTecnicoPDFBi.action'); 
	if(dirCont==0){
	$('#'+idFormulario).get(0).setAttribute('target', '_blank'); 
	}
	else{$('#'+idFormulario).get(0).setAttribute('target', '_self'); 
	}
}	

function htmlSubmit(idFormulario){
$("#save").val("false");
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
$(window).ready(function() {$.ajax({type: "POST" ,url: "../../borrarTemporalReporteParteTecnicoBi.action",data:"valorCargaCompletada=CARGA_COMPLETADA"}); }); 
}		
