//  Funcion para cargar los componentes de Jquery
$(function () {
	$.datepicker.setDefaults($.datepicker.regional["es"]);
		
				// Datepicker
	$("#fechaDetalleNotificacion").datepicker({inline:true, dateFormat:"dd/mm/yy"});
	
});

function verificarActividad() {
	var actSeleccionada = document.getElementById("valorActividad");
	if(actSeleccionada.value !== null && actSeleccionada.value !== ""){
		var actCarg = document.getElementById("actividadCarguio");
		if(actSeleccionada.value == actCarg.value){
			document.getElementById("valorCarguio").disabled=false;
		}
		else{
			document.getElementById("valorCarguio").disabled=true;
		}
	}
}

function verificarActividadPerf() {
	var actSeleccionada = document.getElementById("valorActividad");
	if(actSeleccionada.value !== null && actSeleccionada.value !== ""){
		var actPerf = document.getElementById("actPerforacion");
		if(actSeleccionada.value == actPerf.value){
			document.getElementById("DatosPreparacion").style.visibility = 'visible';
		}
		else{
			document.getElementById("DatosPreparacion").style.visibility = 'hidden';
		}
	}
}

function procesarFormulario(idFormulario) {
	document.getElementById(idFormulario).action = "nuevaNotificacionCantera.action";
	document.getElementById(idFormulario).submit();
	return false;
}