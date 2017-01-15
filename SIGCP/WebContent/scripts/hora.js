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
		valor = document.formularioFiltro.valorTurno.value;
	}
	if (valor == "") {
		if (indice == 1) {
			alert("Debe especificar el C\xf3digo");
		}
		if (indice == 2) {
			alert("Debe especificar la Hora");
		}
		if (indice == 3) {
			alert("Debe especificar el Turno");
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
	if (indice == 1 || indice == 2) {
		mostrarFiltroText();
	}
	if (indice == 3) {
		mostrarFiltroTurno();
	}
}
function resetValores() {
	document.getElementById("valorText").value = "";
	document.getElementById("filtroTurno").value = "";
}
function ocultarTurno() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroTurno").style.display = "none";
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
	if (indice == 1 || indice == 2) {
		return numerico(e);
	}
}

