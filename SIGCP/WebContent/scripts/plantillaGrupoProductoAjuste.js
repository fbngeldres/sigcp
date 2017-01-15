
function actualizarFiltro(idFormulario, nameCampoValor) {
	try {
		var indice = document.formularioFiltro.filtro.selectedIndex;
		if (indice == 0) {
			ocultarFiltros();
		}
		if (indice == 1) {
			mostrarFiltroGrupoAjsute();
		}
	}
	catch (e) {
		alert(e.message);
	}
}
function ocultarFiltros() {
	document.getElementById("filtroGrupoAjuste").style.display = "none";
}
function mostrarFiltroGrupoAjsute() {
	document.getElementById("filtroGrupoAjuste").style.display = "";
}
function filtrar(idFormulario) {
	document.getElementById(idFormulario).submit();
}
function eliminarGrupoAjusteProducto(idFormulario,campoCodigo) {
	try {
		var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
		if (cod == null || cod == "") {
			alert("Debe seleccionar una Plantilla Consumo a Eliminar");
			return false;
		}
		var operacion = confirm("Esta seguro que desea eliminar el registro seleccionado");
		if (operacion) {
			
			document.getElementById(idFormulario).action = "eliminarGrupoAjusteProducto.action";
			document.getElementById(idFormulario).submit();
		}
	}
	catch (e) {
		alert(e.message);
	}
}

