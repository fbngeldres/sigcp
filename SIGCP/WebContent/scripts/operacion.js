//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:	idFormulario.	Ej: formularioFiltro, formularioDivisiones.
//				nameCampoValor.	Ej: valor, codigo, nombre, sociedad.
function filtrar(idFormulario) {
	var indice = document.formularioFiltro.filtro.selectedIndex;
	var valor;
	if ((indice == 1) || (indice == 2) || (indice == 3)) {
		valor = document.formularioFiltro.valorText.value;
	}
	if (indice == 4) {
		valor = document.formularioFiltro.valorActividad.value;
	}
	if (indice == 5) {
		valor = document.formularioFiltro.valorRecurso.value;
	}
	if (indice == 6) {
		valor = document.formularioFiltro.valorPuestoTrabajo.value;
	}
	if (indice == 7) {
		valor = document.formularioFiltro.valorHojaRuta.value;
	}
	if (valor == "") {
		if (indice == 1) {
			alert("Debe especificar el C\xf3digo");
		}
		if (indice == 2) {
			alert("Debe especificar el Nombre de la Operaci\xf3n");
		}
		if (indice == 3) {
			alert("Debe especificar la Orden de Ejecuci\xf3n");
		}
		if (indice == 4) {
			alert("Debe especificar la Actividad");
		}
		if (indice == 5) {
			alert("Debe especificar el Recurso");
		}
		if (indice == 6) {
			alert("Debe especificar el Puesto de Trabajo");
		}
		if (indice == 7) {
			alert("Debe especificar la Hoja de Ruta");
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
	if (indice == 1 || indice == 2 || indice == 3) {
		mostrarFiltroText();
	}
	if (indice == 4) {
		mostrarFiltroActividad();
	}
	if (indice == 5) {
		mostrarFiltroRecurso();
	}
	if (indice == 6) {
		mostrarFiltroPuestoTrabajo();
	}
	if (indice == 7) {
		mostrarFiltroHojaRuta();
	}
}
function resetValores() {
	document.getElementById("valorText").value = "";
	document.getElementById("filtroActividad").value = "";
	document.getElementById("filtroRecurso").value = "";
	document.getElementById("filtroPuestoTrabajo").value = "";
	document.getElementById("filtroHojaRuta").value = "";
}
function ocultarFiltros() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroActividad").style.display = "none";
	document.getElementById("filtroRecurso").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroHojaRuta").style.display = "none";
}
function mostrarFiltroText() {
	document.getElementById("filtroText").style.display = "";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroActividad").style.display = "none";
	document.getElementById("filtroRecurso").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroHojaRuta").style.display = "none";
}
function mostrarFiltroActividad() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroActividad").style.display = "";
	document.getElementById("filtroRecurso").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroHojaRuta").style.display = "none";
}
function mostrarFiltroRecurso() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroActividad").style.display = "none";
	document.getElementById("filtroRecurso").style.display = "";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroHojaRuta").style.display = "none";
}
function mostrarFiltroPuestoTrabajo() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroActividad").style.display = "none";
	document.getElementById("filtroRecurso").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "";
	document.getElementById("filtroHojaRuta").style.display = "none";
}
function mostrarFiltroHojaRuta() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroActividad").style.display = "none";
	document.getElementById("filtroRecurso").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroHojaRuta").style.display = "";
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
	if (indice == 1) {
		return numerico(e);
	}
	
	// Si el indice del combo es 2 solo letras
	if (indice == 2) {
		return alfanumerico(e);
	}
	if (indice == 3) {
		return numerico(e);
	}
}

