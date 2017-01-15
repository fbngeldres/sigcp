
function actualizarFiltro(idFormulario, nameCampoValor) {
	try {
		var indice = document.formularioFiltro.filtro.selectedIndex;
		if (indice == 0) {
			ocultarFiltros();
		}
		if (indice == 1) {
			mostrarFiltroTipo();
		}
	}
	catch (e) {
		alert(e.message);
	}
}
function ocultarFiltros() {
	document.getElementById("filtroTipo").style.display = "none";
}
function mostrarFiltroTipo() {
	document.getElementById("filtroTipo").style.display = "";
}
function filtrar(idFormulario) {
	document.getElementById(idFormulario).submit();
}
function eliminarFormatoReporteParteTecnico(idFormulario, campoCodigo) {
	try {
		
		var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
		
		if (cod == null || cod == "") {
			alert("Debe seleccionar un registro a Eliminar");
			return false;
		}
		
		var operacion = confirm("Esta seguro que desea eliminar el registro seleccionado");
		if (operacion) {
			var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
			if (cod == null || cod == "") {
				alert("Debe seleccionar un registro a Eliminar");
				return false;
			}
			document.getElementById(idFormulario).action = "eliminarFormatoReporteParteTecnico.action";
			document.getElementById(idFormulario).submit();
		}
		
		/*var operacion = confirm("Esta seguro que desea eliminar el registro seleccionado");
		if (operacion) {
			var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
			if (cod == null || cod == "") {
				alert("Debe seleccionar un registro a Eliminar");
				return false;
			}
			document.getElementById(idFormulario).action = "eliminarFormatoReporteParteTecnico.action";
			document.getElementById(idFormulario).submit();
		}*/
		}
	catch (e) {
		alert(e.message);
	}
}

