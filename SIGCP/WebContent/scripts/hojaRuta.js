//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:	idFormulario.	Ej: formularioFiltro, formularioDivisiones.
//				nameCampoValor.	Ej: valor, codigo, nombre, sociedad.
function filtrar(idFormulario) {
	var indice = document.formularioFiltro.filtro.selectedIndex;
	var valor;
	if ((indice == 1) || (indice == 2)) {
		valor = document.formularioFiltro.valorText.value;
	}
	if (indice == 3) {
		valor = document.formularioFiltro.valorEstado.value;
	}
	if (indice == 4) {
		valor = document.formularioFiltro.valorProceso.value;
	}
	if (indice == 5) {
		valor = document.formularioFiltro.valorUnidad.value;
	}
	if (indice == 6) {
		valor = document.formularioFiltro.valorLineaNegocio.value;
	}
	if (valor == "") {
		if (indice == 1) {
			alert("Debe especificar el C\xf3digo");
		}
		if (indice == 2) {
			alert("Debe especificar el Nombre de la Hoja de Ruta");
		}
		if (indice == 3) {
			alert("Debe especificar el Estado");
		}
		if (indice == 4) {
			alert("Debe especificar el Proceso");
		}
		if (indice == 5) {
			alert("Debe especificar la Unidad");
		}
		if (indice == 6) {
			alert("Debe especificar la L\xednea de Negocio");
		}
		return false;
	}
	document.getElementById(idFormulario).submit();
}

			//	Funcion limpiarFiltro:	Limpiar el texto de un filtro
			//	Parametros:	idFormulario.	Ej: formularioFiltro, formularioDivisiones.
			//				nameCampoValor.	Ej: valor, codigo, nombre, sociedad.
function actualizarFiltro(idFormulario, nameCampoValor) {
	var indice = document.formularioFiltro.filtro.selectedIndex;
	if (indice == 0) {
		ocultarFiltros();
	}
	if ((indice == 1) || (indice == 2)) {
		mostrarFiltroText();
	}
	if (indice == 3) {
		mostrarFiltroEstado();
	}
	if (indice == 4) {
		mostrarFiltroProceso();
	}
	if (indice == 5) {
		mostrarFiltroUnidad();
	}
	if (indice == 6) {
		mostrarFiltroLineaNegocio();
	}
}
function resetValores() {
	document.getElementById("valorText").value = "";
	document.getElementById("valorEstado").value = "";
	document.getElementById("valorProceso").value = "";
	document.getElementById("valorUnidad").value = "";
	document.getElementById("valorLineaNegocio").value = "";
}
function ocultarFiltros() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroEstado").style.display = "none";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroUnidad").style.display = "none";
	document.getElementById("filtroLineaNegocio").style.display = "none";
}
function mostrarFiltroText() {
	document.getElementById("filtroText").style.display = "";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroEstado").style.display = "none";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroUnidad").style.display = "none";
	document.getElementById("filtroLineaNegocio").style.display = "none";
}
function mostrarFiltroEstado() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroEstado").style.display = "";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroUnidad").style.display = "none";
	document.getElementById("filtroLineaNegocio").style.display = "none";
}
function mostrarFiltroProceso() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroEstado").style.display = "none";
	document.getElementById("filtroProceso").style.display = "";
	document.getElementById("filtroUnidad").style.display = "none";
	document.getElementById("filtroLineaNegocio").style.display = "none";
}
function mostrarFiltroUnidad() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroEstado").style.display = "none";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroUnidad").style.display = "";
	document.getElementById("filtroLineaNegocio").style.display = "none";
}
function mostrarFiltroLineaNegocio() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroEstado").style.display = "none";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroUnidad").style.display = "none";
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
				
				// Si el indice del combo es 1 es por codigo o codigo SCC y solo acepta numeros
	if (indice == 1) {
		return numerico(e);
	}				

}

