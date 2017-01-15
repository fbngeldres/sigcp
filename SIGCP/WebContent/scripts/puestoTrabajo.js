//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:	idFormulario.	Ej: formularioFiltro, formularioDivisiones.
//				nameCampoValor.	Ej: valor, codigo, nombre, sociedad.
function filtrar(idFormulario) {
	var indice = document.formularioFiltro.filtro.selectedIndex;
	var valor;
	if ((indice >= 1) && (indice <= 5)) {
		valor = document.formularioFiltro.valorText.value;
	}
	if (indice == 6) {
		valor = document.formularioFiltro.valorTipoPuesto.value;
	}
	if (indice == 7) {
		valor = document.formularioFiltro.valorEstadoPuesto.value;
	}
	if (valor == "") {
		if (indice == 1) {
			alert("Debe especificar el C\xf3digo");
		}
		if (indice == 2) {
			alert("Debe especificar el C\xf3digo SCC");
		}
		if (indice == 3) {
			alert("Debe especificar el C\xf3digo SAP");
		}
		if (indice == 4) {
			alert("Debe especificar el nombre de Puesto de Trabajo");
		}
		if (indice == 5) {
			alert("Debe especificar las Siglas");
		}
		if (indice == 6) {
			alert("Debe especificar el Tipo de Puesto de Trabajo");
		}
		if (indice == 7) {
			alert("Debe especificar el Estado");
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
	if (indice >= 1 && indice <= 5) {
		mostrarFiltroText();
	}
	if (indice == 6) {
		mostrarFiltroTipoPuestoTrabajo();
	}
	if (indice == 7) {
		mostrarFiltroEstado();
	}
}
function resetValores() {
	document.getElementById("valorText").value = "";
	document.getElementById("valorTipoPuesto").value = "";
	document.getElementById("valorEstadoPuesto").value = "";
}
function ocultarFiltros() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroTipoPuestoTrabajo").style.display = "none";
	document.getElementById("filtroEstado").style.display = "none";
}
function mostrarFiltroText() {
	document.getElementById("filtroText").style.display = "";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroTipoPuestoTrabajo").style.display = "none";
	document.getElementById("filtroEstado").style.display = "none";
}
function mostrarFiltroTipoPuestoTrabajo() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroTipoPuestoTrabajo").style.display = "";
	document.getElementById("filtroEstado").style.display = "none";
}
function mostrarFiltroEstado() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroTipoPuestoTrabajo").style.display = "none";
	document.getElementById("filtroEstado").style.display = "";
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
	
	// Si el indice del combo es 2 es por codigo o codigo SCC y solo acepta numeros
	if (indice == 2) {
		return numerico(e);
	}
				
	// Si el indice del combo es 2 es por codigo SAP y acepta alfanumericos
	if (indice == 3) {
		return alfanumerico(e);
	}
				
	// Si el indice del combo es 4,5 es por nombre o siglas y solo acepta letras
	if (indice == 4 || indice == 5) {
		return alfanumericos(e);
	}
	// 6 y 7 son combos asi que no hacemos nada con el texto
}

