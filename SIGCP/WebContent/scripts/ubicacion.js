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
		valor = document.formularioFiltro.valorAlmacen.value;
	}
	if (valor == "") {
		if (indice == 1) {
			alert("Debe especificar el C\xf3digo");
		}
		if (indice == 2) {
			alert("Debe especificar el Nombre de la Ubicaci\xf3n");
		}
		if (indice == 3) {
			alert("Debe especificar el Almac\xe9n");
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
		mostrarFiltroAlmacen();
	}
}
function resetValores() {
	document.getElementById("valorText").value = "";
	document.getElementById("filtroAlmacen").value = "";
}
function ocultarFiltros() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroAlmacen").style.display = "none";
}
function mostrarFiltroText() {
	document.getElementById("filtroText").style.display = "";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroAlmacen").style.display = "none";
}
function mostrarFiltroAlmacen() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroAlmacen").style.display = "";
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
		return alfa(e);
	}
}

