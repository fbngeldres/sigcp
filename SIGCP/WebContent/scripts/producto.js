//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:	idFormulario.	Ej: formularioFiltro, formularioDivisiones.
//				nameCampoValor.	Ej: valor, codigo, nombre, sociedad.
function filtrar(idFormulario) {
	var indice = document.formularioFiltro.filtro.selectedIndex;
	var valor;
	if ((indice == 1) || (indice == 2) || (indice == 3) || (indice == 6) || (indice == 7)) {
		valor = document.formularioFiltro.valorText.value;
	}
	if ((indice == 4)) {
		valor = document.formularioFiltro.valorTipoProducto.value;
	}
	if ((indice == 5)) {
		valor = document.formularioFiltro.valorEstadoProducto.value;
	}
	if ((indice == 8)) {
		valor = document.formularioFiltro.valorLineaNegocio.value;
	}
	if (valor == "") {
		if (indice == 1) {
			alert("Debe especificar el C\xf3digo");
		}
		if (indice == 2) {
			alert("Debe especificar el C\xf3digo SAP");
		}
		if (indice == 3) {
			alert("Debe especificar el C\xf3digo SCC");
		}
		if (indice == 4) {
			alert("Debe especificar el Tipo de Producto");
		}
		if (indice == 5) {
			alert("Debe especificar el Estado de Producto");
		}
		if (indice == 6) {
			alert("Debe especificar el nombre de Producto");
		}
		if (indice == 7) {
			alert("Debe especificar las siglas de Producto");
		}
		if (indice == 8) {
			alert("Debe especificar una L\xednea de Negocio");
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
	if (indice == 1 || indice == 2 || indice == 3 || indice == 6 || indice == 7) {
		mostrarFiltroText();
	}
	if (indice == 4) {
		mostrarFiltroTipoProducto();
	}
	if (indice == 5) {
		mostrarFiltroEstadoProducto();
	}
	if (indice == 8) {
		mostrarFiltroLineaNegocio();
	}
}
function resetValores() {
	document.getElementById("valorText").value = "";
	document.getElementById("filtroTipoProducto").value = "";
	document.getElementById("filtroEstadoProducto").value = "";
	document.getElementById("filtroLineaNegocio").value = "";
}
function ocultarFiltros() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroTipoProducto").style.display = "none";
	document.getElementById("filtroEstadoProducto").style.display = "none";
	document.getElementById("filtroLineaNegocio").style.display = "none";
}
function mostrarFiltroText() {
	document.getElementById("filtroText").style.display = "";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroTipoProducto").style.display = "none";
	document.getElementById("filtroEstadoProducto").style.display = "none";
	document.getElementById("filtroLineaNegocio").style.display = "none";
}
function mostrarFiltroTipoProducto() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroTipoProducto").style.display = "";
	document.getElementById("filtroEstadoProducto").style.display = "none";
	document.getElementById("filtroLineaNegocio").style.display = "none";
}
function mostrarFiltroEstadoProducto() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroTipoProducto").style.display = "none";
	document.getElementById("filtroEstadoProducto").style.display = "";
	document.getElementById("filtroLineaNegocio").style.display = "none";
}
function mostrarFiltroLineaNegocio() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroTipoProducto").style.display = "none";
	document.getElementById("filtroEstadoProducto").style.display = "none";
	document.getElementById("filtroLineaNegocio").style.display = "";
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
	if (indice == 1 || indice == 3) {
		return numerico(e);
	}
	
}

