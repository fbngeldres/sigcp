
function actualizarFiltro(idFormulario, nameCampoValor) {
	try {
		var indice = document.formularioFiltro.filtro.selectedIndex;
		if (indice == 1) {
			mostrarFiltroTableroControl();
		} else {
			if (indice == 2) {
				mostrarFiltroPuestoTrabajo();
			} else {
				ocultarFiltro();
			}
		}
	}
	catch (e) {
		alert(e.message);
	}
}
function mostrarFiltroTableroControl() {
	document.getElementById("filtroTablero").style.display = "";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
}
function mostrarFiltroPuestoTrabajo() {
	document.getElementById("filtroTablero").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroPuestoTrabajo").style.display = "";
}
function ocultarFiltro() {
	document.getElementById("filtroTablero").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
}
function filtrar(idFormulario) {
	var indice = document.formularioFiltro.filtro.selectedIndex;
	var valor;
	if ((indice == 1)) {
		valor = document.formularioFiltro.valorTablero.value;
	}
	if (indice == 2) {
		valor = document.formularioFiltro.valorPuestoTrabajo.value;
	}
	if (valor == "") {
		$("#valorCargaCompletada").val("CARGA_NO_COMPLETADA");
		if (indice == 1) {
			alert("Debe especificar el Tablero de Control");
		}
		if (indice == 2) {
			alert("Debe especificar el Puesto de Trabajo");
		}
		
		return false;
	} else {
		$("#valorCargaCompletada").val("CARGA_COMPLETADA");
	}
	document.getElementById(idFormulario).submit();
}

