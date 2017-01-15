//  Funcion para cargar los componentes de Jquery
$(function () {
	$.datepicker.setDefaults($.datepicker.regional["es"]);
		
				// Datepicker
	$("#fechaNotificacion").datepicker({inline:true, dateFormat:"dd/mm/yy"});
	
	// Accordion
			$("#accordion").accordion({header:"h3"});
});

//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:			idFormulario.	Ej: formularioFiltro, formularioDivisiones.