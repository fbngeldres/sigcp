			//	Funcion limpiarFiltro:	Limpiar el texto de un filtro
			//	Parametros:	idFormulario.	Ej: formularioFiltro, formularioArea.
			//				nameCampoValor.	Ej: valor, codigo, nombre, sociedad.
function actualizarFiltro(idFormulario, nameCampoValor) {
	try {
		var indice = document.formularioFiltro.filtro.selectedIndex;
		if (indice == 0) {
			ocultarFiltros();
		}
		if (indice == 1) {
			mostrarFiltroSociedad();
		}
		if (indice == 2) {
			mostrarFiltroProceso();
		}
		if (indice == 3) {
			mostrarFiltroPuestoTrabajo();
		}
		if (indice == 4) {
			mostrarFiltroProducto();
		}
	}
	catch (e) {
		alert(e.message);
	}
}
function ocultarFiltros() {
	document.getElementById("filtroSociedad").style.display = "none";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroProducto").style.display = "none";
}
function mostrarFiltroSociedad() {
	document.getElementById("filtroSociedad").style.display = "";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroProducto").style.display = "none";
}
function mostrarFiltroProceso() {
	document.getElementById("filtroSociedad").style.display = "none";
	document.getElementById("filtroProceso").style.display = "";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroProducto").style.display = "none";
}
function mostrarFiltroPuestoTrabajo() {
	document.getElementById("filtroSociedad").style.display = "none";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "";
	document.getElementById("filtroProducto").style.display = "none";
}
function mostrarFiltroProducto() {
	document.getElementById("filtroSociedad").style.display = "none";
	document.getElementById("filtroProceso").style.display = "none";
	document.getElementById("filtroPuestoTrabajo").style.display = "none";
	document.getElementById("filtroProducto").style.display = "";
}
function filtrar(idFormulario) {
//	var indice = document.formularioFiltro.filtro.selectedIndex;
//	var valor;
//	if ((indice == 1) || (indice == 2) || (indice == 3) || (indice == 6) || (indice == 7)) {
//		valor = document.formularioFiltro.valorText.value;
//	}
//	if ((indice == 4)) {
//		valor = document.formularioFiltro.valorTipoProducto.value;
//	}
//	if ((indice == 5)) {
//		valor = document.formularioFiltro.valorEstadoProducto.value;
//	}
//	if ((indice == 8)) {
//		valor = document.formularioFiltro.valorLineaNegocio.value;
//	}
//	if (valor == "") {
//		if (indice == 1) {
//			alert("Debe especificar el Cxf3digo");
//		}
//		if (indice == 2) {
//			alert("Debe especificar el Cxf3digo SAP");
//		}
//		if (indice == 3) {
//			alert("Debe especificar el Cxf3digo SCC");
//		}
//		if (indice == 4) {
//			alert("Debe especificar el Tipo de Producto");
//		}
//		if (indice == 5) {
//			alert("Debe especificar el Estado de Producto");
//		}
//		if (indice == 6) {
//			alert("Debe especificar el nombre de Producto");
//		}
//		if (indice == 7) {
//			alert("Debe especificar las siglas de Producto");
//		}
//		if (indice == 8) {
//			alert("Debe especificar una Lxednea de Negocio");
//		}
//		return false;
//	}
	document.getElementById(idFormulario).submit();
}
function eliminarPlantillaConsumo(idFormulario, campoCodigo, producto, obj, action) {
	try {
		
		var cod = getCheckedValue(document.getElementById(idFormulario).elements[campoCodigo]);
		if (cod == null || cod == "") {
			alert("Debe seleccionar una Plantilla Consumo a Eliminar");
			return false;
		}
		
		var operacion = confirm("Esta seguro que desea eliminar el registro seleccionado");
		if (operacion) {
			
			document.getElementById(idFormulario).action = "eliminarPlantillaConsumo.action";
			document.getElementById(idFormulario).submit();
		}
	}
	catch (e) {
		alert(e.message);
	}
}
function submitPlantillaConsumo(idFormulario) {
	try {
		var campos = "";
		var mensaje = "Debe Seleccionar los siguientes campos obligatorios: \r";
		var centroCostoTitle = "Centro de Costo";
		var divisionTitle = "Division";
		var sociedadTitle = "Sociedad";
		var unidadTitle = "Unidad";
		var lineaNegocioTitle = "Linea negocio";
		var validadoReq = true;
		if (!validarCampoRequeridoFormulario("valorDivision")) {
			validadoReq = false;
			campos += "- " + divisionTitle + "\r";
		}
		if (!validarCampoRequeridoFormulario("valorSociedad")) {
			validadoReq = false;
			campos += "- " + sociedadTitle + "\r";
		}
		if (!validarCampoRequeridoFormulario("valorUnidad")) {
			validadoReq = false;
			campos += "- " + unidadTitle + "\r";
		}
		if (!validarCampoRequeridoFormulario("valorLineaNegocio")) {
			validadoReq = false;
			campos += "- " + lineaNegocioTitle + "\r";
		}
		if (!validarCampoRequeridoFormulario("valorCentroCosto")) {
			validadoReq = false;
			campos += "- " + centroCostoTitle + "\r";
		}
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
		if (!validadoReq) {
			mensaje += campos;
			alert(mensaje);
			return false;
		} else {
			document.getElementById(idFormulario).submit();
		}
	}
	catch (e) {
		alert(e.message);
	}
}

