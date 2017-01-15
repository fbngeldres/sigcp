
function validarCampoRequeridoFormulario(nombreCampo) {
	var valorCampo = "";
	var validado = true;
	if (document.getElementById(nombreCampo) !== null) {
		valorCampo = new String(document.getElementById(nombreCampo).value);
	}
	if (valorCampo == null || valorCampo == "" || valorCampo == "-1") {
		validado = false;
	}
	return validado;
}
function validarCampoFormularioValorCero(nombreCampo) {
	var valorCampo = "";
	var validado = true;
	if (document.getElementById(nombreCampo) !== null) {
		valorCampo = new String(document.getElementById(nombreCampo).value);
	}
	if (parseFloat(valorCampo) == "0" || parseInt(valorCampo) == "0") {
		validado = false;
	}
	return validado;
}
function validarCampoEmail(nombreCampo) {
	var valorCampo = "";
	var validado = true;
//	if (document.getElementById(nombreCampo) !== null) {
//		valorCampo = new String(document.getElementById(nombreCampo).value);
//		var re = "/^(([^<>()[]\\.,;:s@\"]+(.[^<>()[]\\.,;:s@\"]+)*)|(\".+\"))@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}])|(([a-zA-Z-0-9]+.)+[a-zA-Z]{2,}))$/";
//		validado = valorCampo.match(re);
//	}
	return validado;
	if (document.getElementById(nombreCampo) !== null) {
		valorCampo = new String(document.getElementById(nombreCampo).value);
		var atpos = valorCampo.indexOf("@");
		var dotpos = valorCampo.lastIndexOf(".");
		if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length) {
			return false;
		}
	}
	
	return true;
}

function validarCampoFormularioValorMayorACero(nombreCampo) {
	var validado = false;
	try {
		var valorCampo = "";
		if (document.getElementById(nombreCampo) !== null) {
			valorCampo = new String(document.getElementById(nombreCampo).value);
		}
		if (parseFloat(valorCampo) > 0.0) {
			validado = true;
		}

	} catch (e) {
		alert("No se puede validar la cantidad ingresada");
	}

	return validado;
}

