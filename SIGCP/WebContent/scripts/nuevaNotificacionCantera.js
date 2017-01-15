//  Funcion para cargar los componentes de Jquery
$(function () {
	$.datepicker.setDefaults($.datepicker.regional["es"]);
		
				// Datepicker
	$("#fechaNuevaNotificacion").datepicker({inline:true, dateFormat:"dd/mm/yy"});
	
});

function activarFecha(){
	var campoFecha = document.getElementById("fechaNuevaNotificacion");
	campoFecha.disabled=false;
	var lineaN = document.getElementById("valorLineaNegocio");
	if (lineaN !== null) {
		codigoLineaNeg = document.getElementById("codLinea");
		codigoLineaNeg.value = lineaN.value;
	}
	var textoObserv = document.getElementById("textoObservacion"); 
	textoObserv.value = "";
	update('detalleNotificacionesCantera');
	update('detalleDespachosCantera');
	update('observacionNotificacionCantera');
	update('fechaNotificacionCantera');
}
function actualizarCodUnidad() {
}
