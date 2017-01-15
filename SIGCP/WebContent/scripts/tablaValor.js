function procesaEliminarRegistroEstructuraValor(codigo) {
	var codigoEliminar = document.forms["formularioFiltro"].strCodigoEliminar;
	var operacion = document.forms["formularioFiltro"].strOperacion;
	codigoEliminar.value = codigo;
	operacion.value = "1";
	// alert("Codigo es " + codigoEliminar.value);
	// alert("Operacion es " + operacion.value);
}
function procesaAgregarRegistroEstructuraValor() {
	var operacion = document.forms["formularioFiltro"].strOperacion;
	operacion.value = "2";
}
function setearHabilitarOperacion(id, obj) {
	var accion = "../controlCostos/setearHabilitarOperacion.action";
	var random = "&rand=" + (new Date()).getTime();
	var param = "id=" + id + "&val=" + obj.checked + random;
	$.ajax({
		type : "GET",
		async : false,
		dataType : "xml",
		url : accion,
		data : param,
		success : respuesta
	});
}
function respuesta(xmlObj) {
	var text = "";
	var valor = "";
	$.ajaxSetup({
		cache : false
	});
	$(xmlObj).find("OBJETO").each(function() {
		$(this).find("DESCRIPCION").each(function() {
			alert($(this).text());
		});
	});
}
function limpiarFormulario() {
	document.forms["formularioFiltro"].strCodigoSapConsumo.value = "";
	document.forms["formularioFiltro"].strCodigoSapComponente.value = "";
	document.forms["formularioFiltro"].strCodigoSapProducto.value = "";
	document.forms["formularioFiltro"].intOrden.value = "";
	document.forms["formularioFiltro"].lngOrdenProducto.value = "";
	update("descProducto");
	update("descProductoComponente");
	update("descProductoConsumido");
}
function procesarFormularioTablaValorEliminar(idFormulario) {
	document.getElementById(idFormulario).action = "plantillaEliminarEstructura.action";
	document.getElementById(idFormulario).submit();
}
function procesarEstructuraTablaValorModificar(idFormulario) {
	document.getElementById(idFormulario).action = "modificarEstructuraTablaValor.action";
	document.getElementById(idFormulario).submit();
}
function filtrar(idFormulario) {
	$("#valorCargaCompletada").val("CARGA_COMPLETADA");
	document.getElementById(idFormulario).action = "estructuraListarTablaValor.action";
	document.getElementById(idFormulario).submit();
}

// Tabla Valor
function filtrarTablaValor(idFormulario) {
	// alert("Se ejecuto el filtrarTablaValor " + idFormulario);
	$("#valorCargaCompletada").val("CARGA_COMPLETADA");
	document.getElementById(idFormulario).action = "tablaValor.action";
	document.getElementById(idFormulario).submit();
}
function procesarFormularioEliminarEstructuras(idformulario, idvalidar) {
	var cod = getCheckedValue(document.getElementById(idformulario).elements[idvalidar]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Registro");
		return false;
	}
	document.getElementById(idformulario).action = "formularioEliminarEstructurasTablaValor.action";
	document.getElementById(idformulario).submit();
}
function generarTablaValor(idformulario) {
	$("#valorCargaCompletada").val("CARGA_COMPLETADA");
	document.getElementById(idformulario).action = "validarGeneracionTablaValor.action";
	document.getElementById(idformulario).submit();
}
function popupTablaValor(URL, idFormulario, codigo) {
	URL = URL
			+ "/controlCostos/cargarPlantillaTablaValorpopup.action?codigoSap="
			+ codigo;
	day = new Date();
	id = day.getTime();
	eval("page" + id + " = window.open(URL, '" + id
			+ "', 'width=650,height=500,scrollbars=yes');");
}
function fillComponentesConsumosValores(obj, Codproducto) {
	var accion = "../controlCostos/ajaxFillComponentesConsumosValores.action";
	var random = "&rand=" + (new Date()).getTime();
	

	var param = "id=" + obj.id + "&val=" + obj.value + "&atrib=" + obj.name
			+ "&codPro=" + Codproducto + random;
	var URL = accion + param;
	$.ajax({
		type : "GET",
		async : false,
		dataType : "xml",
		url : accion,
		data : param,
		success : valores
	});
}
function fillValores(obj) {
	var accion = "../controlCostos/ajaxFillvalores.action";
	var random = "&rand=" + (new Date()).getTime();
	var param = "id=" + obj.id + "&val=" + obj.value + "&atrib=" + obj.name
			+ random;
	var URL = accion + param;
	$.ajax({
		type : "GET",
		async : false,
		dataType : "xml",
		url : accion,
		data : param,
		success : valores
	});
}
function valores(xmlObj) {
	try {

		var text = "";
		var valor = "";
		$.ajaxSetup({
			cache : false
		});
		$(xmlObj).find("OPCION").each(function() {
			$(this).find("NOMBRE").each(function() {
				text = $(this).text();
			});
			$(this).find("VALOR").each(function() {
				valor = $(this).text();
			});

			var texfield = $("#f" + text);

			if (texfield != null) {

				$("input[name='" + text + "']").attr("value", valor);

			}
		});
	} catch (e) {
		alert(e.message);
	}
}

// Eliminar Tabla Valor
function procesarFormularioEliminarTablaValor(idformulario, idvalidar) {
	var cod = getCheckedValue(document.getElementById(idformulario).elements[idvalidar]);
	if (cod == null || cod == "") {
		alert("Debe seleccionar un Registro");
		return false;
	}
	document.getElementById(idformulario).action = "formularioEliminarTablaValor.action";
	document.getElementById(idformulario).submit();
}
function guardarPopupGenerarValor() {
	var confi = confirm("\xbfDeseas Guardar?");
	if (confi) {
		var accion = "../controlCostos/plantillaGuardarPopUpTablaValor.action";
		var random = "&rand=" + (new Date()).getTime();
		var param = "id="
				+ document.forms["formularioFiltro"].strCodigoPlantilla.value
				+ random;
		$.ajax({
			type : "GET",
			url : accion,
			data : param,
			success : function() {
				window.close();
			}
		});
	}
	return false;
}
function cancelarPopupGenerarValor() {
	var accion = "../controlCostos/plantillaCancelarPopUpTablaValor.action";
	var random = "&rand=" + (new Date()).getTime();
	var param = "id="
			+ document.forms["formularioFiltro"].strCodigoPlantilla.value
			+ random;
	$.ajax({
		type : "GET",
		url : accion,
		data : param,
		success : function() {
			window.close();
		}
	});
}
function generarTValor(idFormulario) {
	document.getElementById(idFormulario).action = "generarTValor.action";
	document.getElementById(idFormulario).submit();
}
function reporteTablaValor(idFormulario) {
	document.getElementById(idFormulario).action = "reporteCantidadTablaValor.action";
	document.getElementById(idFormulario).submit();
}
function cantidadesCostoProceso(idFormulario) {
	document.getElementById(idFormulario).action = "calcularReporteCostosProcesos.action";
	document.getElementById(idFormulario).submit();
}
function cambiarSigno(textField) {
	if (textField.value != "") {
		var num = parseFloat(textField.value);
		if (num > 0) {
			textField.value = -num;
		}
	}
}
function getCostoUnidades(objetoTexfield, idResultado) {
	if (objetoTexfield.value != "") {
		var variable1;
		var cost_uni_avg = parseFloat(document.forms["formularioFiltro"].cost_uni_avg.value);
		var cost_cost_avg = parseFloat(document.forms["formularioFiltro"].cost_cost_avg.value);
		variable1 = cost_cost_avg / cost_uni_avg;
		if (parseFloat(variable1) > 0) {
		
			var variable2 = parseFloat(objetoTexfield.value);
			var total = variable1 * variable2;
			total = redondeo1decimales(total);
			$("#" + idResultado).attr("value", total);
		}
	}
}
function getCostoUnidadesCostoUnitario(objetoTexfield, idResultado) {
	if (objetoTexfield.value != "") {
		var variable1;
		var uni_pro = parseFloat(document.forms["formularioFiltro"].uni_pro.value);
		var uni_cos_pro = parseFloat(document.forms["formularioFiltro"].uni_cos_pro.value);
		variable1 = uni_cos_pro / uni_pro;
		if (parseFloat(variable1) > 0) {
			var variable2 = parseFloat(objetoTexfield.value);
			var total = variable1 * variable2;
			total = redondeo1decimales(total);
			$("#" + idResultado).attr("value", total);
		}
	}
}
function AjustarTablaValor() {
	var accion = "../controlCostos/AjusteTablaValor.action";
	var random = "&rand=" + (new Date()).getTime();
	var param = "valorAjuste="
			+ document.forms["formularioFiltro"].valorAjuste.value + random;
	$.ajax({
		type : "GET",
		async : false,
		dataType : "xml",
		url : accion,
		data : param,
		success : valores
	});
}
function actualizarFormulario() {
	var form = document.forms["formularioFiltro"];
	$(form).find(":text").each(function() {
		this.focus();
	});
	$("#contenido").focus();
}
function ejecutarAction(idFormulario, actionForm) {
	document.getElementById(idFormulario).action = actionForm;
	document.getElementById(idFormulario).submit();
}
function setearVentas(consumoInterno) {
	try {

		var form = document.forms["formularioFiltro"];
		$(form)
				.find(":text")
				.each(
						function() {
							if (this.id.indexOf("_venta") >= 0) {
								varr = this.id.split("_");
								if (varr.length > 0) {
									resta = $("#" + varr[0] + "_hidden").val()
											- consumoInterno;
									
									
									$("#" + varr[0]).attr("value", resta);

									var cost_uni_avg = parseFloat(document.forms["formularioFiltro"].cost_uni_avg.value);
									var cost_cost_avg = parseFloat(document.forms["formularioFiltro"].cost_cost_avg.value);
									variable1 = cost_cost_avg / cost_uni_avg;

									ventacosto = $("#" + varr[0] + "_venta");
									// preciopromedio =
									// $("input[name='promedio_precio']").val();
									ventacosto.attr("value", resta * variable1);
								}
							}
						});

	} catch (e) {
		alert("error al modificar ventas " + e.message)
	}
}
function cambiarAction(form) {
	form.action = "tablaValor.action";
}
function reporteConsolidadoTablaValor(idFormulario) {
	document.getElementById(idFormulario).action = "reporteConsolidadoTablaValor.action";
	document.getElementById(idFormulario).submit();
}
function generarReporteConsolidadoTablaValor(idFormulario) {
	document.getElementById(idFormulario).action = "../reportes/generarConsolidadoTablaValor.action";
	document.getElementById(idFormulario).submit();
}
function generarReporteConsolidadoTablaValorPDF(idFormulario) {
	document.getElementById(idFormulario).action = "../reportes/generarConsolidadoTablaValorPdf.action";
	document.getElementById(idFormulario).submit();
}
