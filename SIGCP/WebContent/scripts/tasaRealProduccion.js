//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:	idFormulario.	Ej: formularioFiltro, formularioDivisiones.
//				nameCampoValor.	Ej: valor, codigo, nombre, sociedad.
function filtrar(idFormulario) {
	var indice = document.formularioFiltro.filtro.selectedIndex;
	var valor;
	if ((indice == 1)) {
		valor = document.formularioFiltro.valorText.value;
	}
	if (indice == 2) {
		valor = document.formularioFiltro.valorPuestoTrabajo.value;
	}
	if (indice == 3) {
		valor = document.formularioFiltro.valorUnidadMedida.value;
	}
	if (indice == 4) {
		valor = document.formularioFiltro.valorProceso.value;
	}
	if (indice == 5) {
		valor = document.formularioFiltro.valorProducto.value;
	}
	if (indice == 6) {
		valor = document.formularioFiltro.valorText.value;
	}
	if (valor == "") {
		if (indice == 1) {
			alert("Debe especificar el C\xf3digo");
		}
		if (indice == 2) {
			alert("Debe especificar un Puesto de Trabajo");
		}
		if (indice == 3) {
			alert("Debe especificar una Unidad de Medida");
		}
		if (indice == 4) {
			alert("Debe especificar un Proceso");
		}
		if (indice == 5) {
			alert("Debe especificar un Producto");
		}
		if (indice == 6) {
			alert("Debe especificar un A\xf1o");
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
	if (indice == 1 || indice == 6) {
		mostrarFiltroText();
	}
	if (indice == 2) {
		mostrarFiltroPuestoTrabajo();
	}
	if (indice == 3) {
		mostrarFiltroUnidadMedida();
	}
	if (indice == 4) {
		mostrarFiltroProceso();
	}
	if (indice == 5) {
		mostrarFiltroProducto();
	}
}
function resetValores() {
	document.getElementById("valorText").value = "";
	document.getElementById("filtroPuestoTrabajo").value = "";
	document.getElementById("filtroUnidadMedida").value = "";
	document.getElementById("filtroProceso").value = "";
	document.getElementById("filtroProducto").value = "";
}
function ocultarFiltros() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroUnidadMedida").style.display = "none";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroProducto").style.display = "none";
}
function mostrarFiltroText() {
	document.getElementById("filtroText").style.display = "";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroUnidadMedida").style.display = "none";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroProducto").style.display = "none";
}
function mostrarFiltroPuestoTrabajo() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "";
	document.getElementById("filtroUnidadMedida").style.display = "none";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroProducto").style.display = "none";
}
function mostrarFiltroUnidadMedida() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroUnidadMedida").style.display = "";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroProducto").style.display = "none";
}
function mostrarFiltroProceso() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroUnidadMedida").style.display = "none";
	document.getElementById("filtroProceso").style.display = "";
	document.getElementById("filtroProducto").style.display = "none";
}
function mostrarFiltroProducto() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroUnidadMedida").style.display = "none";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroProducto").style.display = "";
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
	if (indice == 1 || indice == 6) {
		return numerico(e);
	}
}

