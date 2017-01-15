function cambiarActionEXCEL(idFormulario,dirCont){
	if (camposObligatorios()) {
var valorReporte = $("#valorReporte").val();
generarReporteCubicacionEXCEL(idFormulario,dirCont);

}
}

/*Reporte Cubicacion*/
function generarReporteCubicacionEXCEL(idFormulario, dirCont) {
	var dirAction = "";
	for ( var i = 0; i < dirCont; i++) {
		dirAction += "../";
	}
	$('#' + idFormulario).get(0).setAttribute('action',dirAction + 'crearReporteCubicacionEXCEL.action');
	htmlSubmit(idFormulario)
}




function htmlSubmit(idFormulario){
document.getElementById(idFormulario).submit();
}		

function camposObligatorios() {
	var unidad = document.getElementById('valorUnidad');
	var sociedad = document.getElementById('valorSociedad');
	var division = document.getElementById('valorDivision');


	if (unidad === null || unidad.value === null || unidad.value === ""){
		alert("Seleccione Unidad");
return false;
	} 
	if (sociedad === null || sociedad.value === null || sociedad.value === ""){
		alert("Seleccione Sociedad");
return false;
	} 
	if (division === null || division.value === null || division.value === ""){
		alert("Seleccione Division");
return false;
	} 
	return true;
}	