//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:	idFormulario.	Ej: formularioFiltro, formularioDivisiones.
//				nameCampoValor.	Ej: valor, codigo, nombre, sociedad.
function filtrar(idFormulario) {
	var indice = document.formularioFiltro.filtro.selectedIndex;
	var valor;
	if ((indice == 1) || (indice == 4) || (indice == 5)) {
		valor = document.formularioFiltro.valorText.value;
	}
	if (indice == 2) {
		valor = document.formularioFiltro.valorPuestoTrabajo.value;
	}
	if (indice == 3) {
		valor = document.formularioFiltro.valorUnidadMedida.value;
	}
	if (valor == "") {
		if (indice == 1) {
			alert("Debe especificar el C\xf3digo");
		}
		if (indice == 2) {
			alert("Debe especificar el Puesto de trabajo");
		}
		if (indice == 3) {
			alert("Debe especificar la unidad de medida");
		}
		if (indice == 4) {
			alert("Debe especificar el a\xd1o");
		}
		if (indice == 5) {
			alert("Debe especificar el Mes");
		}
		return false;
	}
	document.getElementById(idFormulario).submit();
}

			//	Funcion limpiarFiltro:	Limpiar el texto de un filtro
			//	Parametros:	idFormulario.	Ej: formularioFiltro, formularioArea.
			//				nameCampoValor.	Ej: valor, codigo, nombre, sociedad.
function actualizarFiltro(idFormulario, nameCampoValor) {
	var indice = document.formularioFiltro.filtro.selectedIndex;
	if (indice == 0) {
		ocultarFiltros();
	}
	if (indice == 1 || indice == 4 || indice == 5) {
		mostrarFiltroText();
	}
	if (indice == 2) {
		mostrarFiltroPuestoTrabajo();
	}
	if (indice == 3) {
		mostrarFiltroUnidadMedida();
	}
}
function resetValores() {
	document.getElementById("valorText").value = "";
	document.getElementById("filtroPuestoTrabajo").value = "";
	document.getElementById("filtroUnidadMedida").value = "";
}
function ocultarFiltros() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroUnidadMedida").style.display = "none";
}
function mostrarFiltroText() {
	document.getElementById("filtroText").style.display = "";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroUnidadMedida").style.display = "none";
}
function mostrarFiltroPuestoTrabajo() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroPuestoTrabajo").style.display = "";
	document.getElementById("filtroUnidadMedida").style.display = "none";
}
function mostrarFiltroUnidadMedida() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroUnidadMedida").style.display = "";
}
function iniciarFiltro() {
}
function validar(e) {
	var indice = document.formularioFiltro.filtro.selectedIndex;	
				
				// Si el indice del combo es cero no deja escribir en el texto 
	if (indice == 0) {
		alert("No hay criterio de filtro seleccionado");
	}
				
				// Si el indice del combo es 1 solo numeros
	if (indice == 1 || indice == 4 || indice == 5) {
		return numerico(e);
	}
}
function eliminarPlan(idFormulario, campoCodigo, mensajeEntidad, entidad, operacion) {

	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar " + mensajeEntidad + " a " + operacion.toLowerCase());
		return false;
	}
	if (confirm("¿Desea eliminar el registro seleccionado?")) {
		document.getElementById(idFormulario).action = operacion + entidad + ".action";
		document.getElementById(idFormulario).submit();
	}
}

function versionarPlanCapacidad(idFormulario, campoCodigo, mensajeEntidad, entidad, operacion) {

	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar " + mensajeEntidad + " a " + operacion.toLowerCase());
		return false;
	}
	if (confirm("¿Desea versionar el registro seleccionado?")) {
		document.getElementById(idFormulario).action = operacion + entidad + ".action";
		document.getElementById(idFormulario).submit();
	}
}

function aprobarPlanCapacidad(idFormulario, campoCodigo, mensajeEntidad, entidad, operacion) {

	var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar " + mensajeEntidad + " a " + operacion.toLowerCase());
		return false;
	}
	if (confirm("¿Desea aprobar el registro seleccionado?")) {
		document.getElementById(idFormulario).action = operacion + entidad + ".action";
		document.getElementById(idFormulario).submit();
	}
}

