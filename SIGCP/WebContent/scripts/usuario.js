//	Funcion filtrar:	Llama a un action de filtro, pasandole como parametro la entidad
//						y el formulario desde el cual se esta llamando.
//	Parametros:	idFormulario.	Ej: formularioFiltro, formularioDivisiones.
//				nameCampoValor.	Ej: valor, codigo, nombre, sociedad.
function filtrar(idFormulario) {
	var indice = document.formularioFiltro.filtro.selectedIndex;
	var valor;
	if ((indice == 1) || (indice == 2) || (indice == 3)) {
		valor = document.formularioFiltro.valor.value;
	} else {
		if (indice == 4) {
			valor = document.formularioFiltro.valorEstado.value;
		} else {
			if (indice == 5) {
				valor = document.formularioFiltro.valorGrupo.value;
			}
		}
	}
	if (valor == "") {
		if (indice == 1) {
			alert("Debe especificar el Login");
		} else {
			if (indice == 2) {
				alert("Debe especificar el Nombre del Usuario");
			} else {
				if (indice == 3) {
					alert("Debe especificar el Apellido del Usuario");
				} else {
					if (indice == 4) {
						alert("Debe especificar el Estado del Usuario");
					} else {
						if (indice == 5) {
							alert("Debe especificar el Grupo del Usuario");
						}
					}
				}
			}
		}
		return false;
	}
	document.getElementById(idFormulario).submit();
}
function actualizarFiltro(nameCampoValor) {
	var indice = document.formularioFiltro.filtro.selectedIndex;
	if (indice == 0) {
		ocultarFiltros();
	} else {
		if ((indice == 1) || (indice == 2) || (indice == 3)) {
			mostrarFiltroText();
		} else {
			if (indice == 4) {
				mostrarFiltroEstado();
			} else {
				if (indice == 5) {
					mostrarFiltroGrupo();
				}
			}
		}
	}
}
function resetValores() {
	document.getElementById("valor").value = "";
	document.getElementById("filtroEstado").value = "";
}
function ocultarFiltros() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "none";
	document.getElementById("filtroEstado").style.display = "none";
	document.getElementById("filtroGrupo").style.display = "none";
}
function mostrarFiltroText() {
	document.getElementById("filtroText").style.display = "";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroEstado").style.display = "none";
	document.getElementById("filtroGrupo").style.display = "none";
}
function mostrarFiltroEstado() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroEstado").style.display = "";
	document.getElementById("filtroGrupo").style.display = "none";
}
function mostrarFiltroGrupo() {
	document.getElementById("filtroText").style.display = "none";
	document.getElementById("labelValor").style.display = "";
	document.getElementById("filtroEstado").style.display = "none";
	document.getElementById("filtroGrupo").style.display = "";
}
function validar(e) {
	var indice = document.formularioFiltro.filtro.selectedIndex;					
	// Si el indice del combo es cero no deja escribir en el texto 
	if (indice == 0) {
		alert("No hay criterio de filtro seleccionado");
	}
}	

// Validaciones Formulario Ingresar Usuario
function volverListado() {
	document.getElementById("ingresarUsuario_action").action = "listarUsuario.action";
	document.getElementById("ingresarUsuario_action").submit();
}
function validarFormularioIngreso() {
	var validadoReq = true;
	var otrasValidaciones = true;
	var campos = "";
	var mensajesErrorOtrasVal = "";
		
	// Se cargan los mensajes desde el resources.properties
	var mensaje = "";
	var mensajeErrorTitle = "";
	var nombresTitle = "";
	var apellidosTitle = "";
	var identificacionTitle = "";
	var correoTitle = "";
	var cargoTitle = "";
	var loginTitle = "";
	var estadoTitle = "";
	var grupoTitle = "";
	var msjErrorCorreo = "";
	if (document.getElementById("mensajeErrorValidacion") !== null) {
		mensaje = new String(document.getElementById("mensajeErrorValidacion").value);
	}
	if (document.getElementById("mensajeErrorOtrasValidaciones") !== null) {
		mensajeErrorTitle = new String(document.getElementById("mensajeErrorOtrasValidaciones").value);
	}
	if (document.getElementById("tituloCampoNombres") !== null) {
		nombresTitle = new String(document.getElementById("tituloCampoNombres").value);
	}
	if (document.getElementById("tituloCampoApellidos") !== null) {
		apellidosTitle = new String(document.getElementById("tituloCampoApellidos").value);
	}
	if (document.getElementById("tituloCampoIdentificacion") !== null) {
		identificacionTitle = new String(document.getElementById("tituloCampoIdentificacion").value);
	}
	if (document.getElementById("tituloCampoCorreo") !== null) {
		correoTitle = new String(document.getElementById("tituloCampoCorreo").value);
	}
	if (document.getElementById("tituloCampoCargo") !== null) {
		cargoTitle = new String(document.getElementById("tituloCampoCargo").value);
	}
	if (document.getElementById("tituloCampoLogin") !== null) {
		loginTitle = new String(document.getElementById("tituloCampoLogin").value);
	}
	if (document.getElementById("tituloCampoEstado") !== null) {
		estadoTitle = new String(document.getElementById("tituloCampoEstado").value);
	}
	if (document.getElementById("tituloCampoGrupo") !== null) {
		grupoTitle = new String(document.getElementById("tituloCampoGrupo").value);
	}
	if (document.getElementById("mensajeErrorValidacionCorreo") !== null) {
		msjErrorCorreo = new String(document.getElementById("mensajeErrorValidacionCorreo").value);
	}
	
	// Se valida el campo Nombre
	if (!validarCampoRequeridoFormulario("valorNombres")) {
		validadoReq = false;
		campos += "- " + nombresTitle + "\r";
	}
	
	// Se valida el campo Apellido
	if (!validarCampoRequeridoFormulario("valorApellidos")) {
		validadoReq = false;
		campos += "- " + apellidosTitle + "\r";
	}
	
	// Se valida el campo Identificacion del Cliente
	if (!validarCampoRequeridoFormulario("valorNumeroIdentificacion")) {
		validadoReq = false;
		campos += "- " + identificacionTitle + "\r";
	}
	
	// Se valida el campo Correo
	if (!validarCampoRequeridoFormulario("valorCorreo")) {
		validadoReq = false;
		campos += "- " + correoTitle + "\r";
	}
	
	// Se valida el campo Cargo
	if (!validarCampoRequeridoFormulario("valorCargo")) {
		validadoReq = false;
		campos += "- " + cargoTitle + "\r";
	}
	
	// Se valida el campo Login
	if (!validarCampoRequeridoFormulario("valorLogin")) {
		validadoReq = false;
		campos += "- " + loginTitle + "\r";
	}
	
	// Se valida el campo Estado
	if (!validarCampoRequeridoFormulario("valorEstadoUsuario")) {
		validadoReq = false;
		campos += "- " + estadoTitle + "\r";
	}
	
	// Se valida el campo Grupo
	if (!validarCampoRequeridoFormulario("valorGrupoUsuario")) {
		validadoReq = false;
		campos += "- " + grupoTitle + "\r";
	}
	
	// OTRAS VALIDACIONES
	if (!validarCampoEmail("valorCorreo")) {
		validadoReq = false;
		campos += "- " + correoTitle + ": Tiene formato incorrecto";
	}
	
	// Se envia el Alert en caso de error o se hace submit del formulario en el caso exitoso
	if (!validadoReq) {
		mensaje += campos;
		alert(mensaje);
	}
	return validadoReq;
}

