
function actualizarFiltro(idFormulario, nameCampoValor) {
	try {
		var indice = document.formularioFiltro.filtro.selectedIndex;
		if (indice == 0) {
			ocultarFiltros();
		}
		if (indice == 1) {
			mostrarFiltroProceso();
		}
		if (indice == 2) {
			mostrarFiltroPuestoTrabajo();
		}
	}
	catch (e) {
		alert(e.message);
	}
}
function ocultarFiltros() {
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
}
function mostrarFiltroProceso() {
	document.getElementById("filtroProceso").style.display = "";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
}
function mostrarFiltroPuestoTrabajo() {
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "";
}
function filtrar(idFormulario) {
	document.getElementById(idFormulario).submit();
}
function eliminarEquivalenciaSCCcalidad(idFormulario,campoCodigo) {
	try {
		
		var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
		if (cod == null || cod == "") {
			alert("Debe seleccionar una Plantilla Consumo a Eliminar");
			return false;
		}
		
		var operacion = confirm("Esta seguro que desea eliminar el registro seleccionado");
		if (operacion) {
			document.getElementById(idFormulario).action = "eliminarEquivalenciaSCCVarCalidad.action";
			document.getElementById(idFormulario).submit();
		}
	}
	catch (e) {
		alert(e.message);
	}
}

