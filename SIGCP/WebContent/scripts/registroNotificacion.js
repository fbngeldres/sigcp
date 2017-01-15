//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:			idFormulario.	Ej: formularioFiltro, formularioDivisiones.
function filtrar(idFormulario) {
	document.getElementById(idFormulario).submit();
}

//	Funcion procesarFormulario:	Valida que el usuario haya seleccionado una entidad en la tabla,
//						setea el action, y realiza el submit con la operacion pasada como parametro.
//	Parametros: idFormualrio.	Ej: "formularioFiltro", "formularioDivisiones"
//				campoCodigo		name del radio buttom. Ej:	"centroCostosBean.codigo"
//				mensajeEntidad.	Ej: "una Division" o "un Centro de Costos"
//				entidad			Ej: "Division", "CentroCostos"
//				operacion		Siempre en minúscula. Ej: "Aprobar" o "Eliminar"
function procesarFormulario(idFormulario, campoCodigo, mensajeEntidad, entidad, operacion) {
	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar " + mensajeEntidad + " a " + operacion);
		return false;
	}
	document.getElementById(idFormulario).action = operacion + entidad + ".action";
	document.getElementById(idFormulario).submit();
}
function nuevoFormulario(idFormulario, entidad, operacion){
	document.getElementById(idFormulario).action = operacion + entidad + ".action";
	document.getElementById(idFormulario).submit();
}
function bloquearAnno() {
	document.formularioFiltro.valorAnio.value = "";
	document.formularioFiltro.valorAnio.disabled = true;
}
function bloquearMes() {
	document.formularioFiltro.valorMes.value = "";
	document.formularioFiltro.valorMes.disabled = true;
}
function validarAnio() {
	var anio = document.formularioFiltro.valorAnio.value;
	if (anio == "") {
		document.formularioFiltro.valorMes.value = "";
		alert("Primero debe especificar el a\xf1o.");
	}
}
function seleccionar_todo(idFormulario, valor){
for (i=0;i<document.getElementById(idFormulario).elements.length;i++)
      if(document.getElementById(idFormulario).elements[i].type == "checkbox")
         document.getElementById(idFormulario).elements[i].checked=valor
}

		//  Funcion para cargar los componentes de Jquery
		$(function () {
						// Accordion
			$("#accordion").accordion({header:"h3"});
		
		});

		//funcion para envio del formulario desde las diferentes funcionalidades del menu
		function seleccionarUnidad(){
			document.formularioRegistro.action = 'SeleccionarUnidadNegocio.action';
			document.formularioRegistro.submit();
		}
		
		function grabarPlan(){
			document.formularioRegistro.action = 'IngresarPlanAnual.action';
			document.formularioRegistro.submit();
		}
		
		function show_productos() {
			dojo.event.topic.publish("show_productos");
		}
		
		function show_otros_productos(){
			dojo.event.topic.publish("show_otros_productos");
		}
		
		function show_consultas() {
			dojo.event.topic.publish("show_consultas");
		}
		
		function show_conceptos() {
			dojo.event.topic.publish("show_conceptos");
			dojo.event.topic.publish("show_componentes");
			dojo.event.topic.publish("show_operaciones");
		}
		
		function show_indices(){
			dojo.event.topic.publish("show_indices");
		}

		function validarFormularios() {
			var idFormulario = "formularioRegistro";
			var campoCodigo = "produccionEstimada";
			var mensajeEntidad = "el estimado por Operación";
			var cod = document.getElementById(idFormulario).elements[campoCodigo];
			for (var i = 0; i < cod.length; i++){
				if (cod[i].value == null || cod[i].value == "") {
					alert("Debe llenar " + mensajeEntidad + " para poder grabar");
				return false;
				}
			}
			campoCodigo = "dosplanificada";
			mensajeEntidad = "el estimado por Dosificacion de cada Componente";
			cod = document.getElementById(idFormulario).elements[campoCodigo];
			for (var i = 0; i < cod.length; i++){
				if (cod[i].value == null || cod[i].value == "") {
					alert("Debe llenar " + mensajeEntidad + " para poder grabar");
				return false;
				}
			}
			campoCodigo = "tmplanificada";
			mensajeEntidad = "el estimado por TM de cada Componente";
			cod = document.getElementById(idFormulario).elements[campoCodigo];
			for (var i = 0; i < cod.length; i++){
				if (cod[i].value == null || cod[i].value == "") {
					alert("Debe llenar " + mensajeEntidad + " para poder grabar");
				return false;
				}
			}
		document.getElementById(idFormulario).action = "grabarOrdenProduccion.action";
		document.getElementById(idFormulario).submit();
	}		
		