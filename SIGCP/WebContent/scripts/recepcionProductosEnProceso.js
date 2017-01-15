//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:			idFormulario.	Ej: formularioFiltro, formularioDivisiones.
function filtrar(idFormulario) {
	document.getElementById(idFormulario).submit();
}
//--------------------------------------------------------------------------------------------
//	LlenarComboDias:	Funcion que permite llenar un combo de dias con los dias de la semana
//						a partir de una fechaInicio y hasta una fechaFin
//						comboDias: el combo a llenar con la semana
//						fechaInicio: textfield donde se encuentra la fecha de inicio
//---------------------------------------------------------------------------------------------
function llenarComboDias(nombreCombo) {
	FECHA_INICIO = new String(document.formularioFiltro.fechaInicio.value);
	FECHA_FIN = new String(document.formularioFiltro.fechaFinRegistro.value);
	var arregloInicio = FECHA_INICIO.split("/");
	var diaInicio = arregloInicio[0];
	var mesInicio = arregloInicio[1];
	var annoInicio = arregloInicio[2];
	mesInicio = mesInicio - 1;
	var fechaInicio = new Date(annoInicio, mesInicio, diaInicio);
	var diasSiguiente = 0;
	var stringDia = "";	//Este string es una variabla auxiliar para guardar el dia y agregarlo al combo
	limpiarCombo(document.formularioFiltro.valorDia);	
	
	//Agregamos la primera opcion del combo vacia 
	stringDia = "";
	addOption(document.formularioFiltro.valorDia, stringDia, 0);
	for (var i = 0; i < 7; i++) {
		diasSiguiente = fechaInicio.getTime() + ((24 * 60 * 60 * 1000) * i);
	
		// Cosntruimos el objeto Date con la cantidad de
		// miliseg que representan cada dia de la semana
		var fecha = new Date(diasSiguiente);
		var dia = fecha.getDate();
		var mes = fecha.getMonth() + 1;
		var anno = fecha.getFullYear();
		stringDia = dia + "/" + mes + "/" + anno;		
		
		// agregamos la opcion
		addOption(document.formularioFiltro.valorDia, stringDia, i);
	}
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
function validarSabadoRegistrarPrograma() {
	var resultado = validarSabado();
	if (resultado == true) {
		update("comboDias");
		return false;
	}
}
function bloquearAnno() {
	document.formularioFiltro.valorAnio.value = "";
	document.formularioFiltro.valorAnio.disabled = true;
}
function bloquearMes() {
	document.formularioFiltro.valorMes.value = "";
	document.formularioFiltro.valorMes.disabled = true;
}
function bloquearFechaFin() {
	document.formularioFiltro.fechaFin.value = "";
	document.formularioFiltro.fechaFin.disabled = true;
}
function bloquearFechaInicio() {
	document.formularioFiltro.fechaInicio.value = "";
	document.formularioFiltro.fechaInicio.disabled = true;
}
function validarAnio() {
	var anio = document.formularioFiltro.valorAnio.value;
	if (anio == "") {
		document.formularioFiltro.valorMes.value = "";
		alert("Primero debe especificar el a\xf1o.");
	}
}
function generarReporte(idFormulario) {
	var codigoProceso = new String(document.formularioFiltro.valorProceso);
	var FECHA_INICIO = new String(document.formularioFiltro.fechaInicio.value);
	var FECHA_FIN = new String(document.formularioFiltro.fechaFin.value);
	
	if (codigoProceso == 'undefined' ) {
		alert("Debe seleccionar un proceso");
		return false;
	}
	if (FECHA_INICIO == null || FECHA_INICIO == "") {
		alert("Debe seleccionar la fecha de inicio");
		return false;			
		}
	if (FECHA_FIN == null || FECHA_FIN == "") {
		alert("Debe seleccionar la fecha de fin");
		return false;
	}	
	filtrar(idFormulario);
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