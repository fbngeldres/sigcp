// se encarga de sumar los totales horizontales y verticales de la tabla especificada
function sumarTabla(id_tabla) {

try{

	var nro_columnas_info = calcularNroColumnasInfo(id_tabla);
			//alert('DEBUG: sumando tabla - '+id_tabla);
			//alert('DEBUG: info - '+nro_columnas_info);
	var row_headers = 3;
	var tbl = document.getElementById(id_tabla);
	if (tbl != null) {
		var rows = tbl.getElementsByTagName("tr");
		var tamano_vertical = rows.length;
		if (tamano_vertical > 4) {
			var primeraFilaIndex = 3;
			var numeroFilas = tamano_vertical-4;
			var cels = rows[primeraFilaIndex].getElementsByTagName("td");
			var campo;
			var i = 1;
			for (var cel = nro_columnas_info - 1; cel < cels.length - 1; cel++) {
				campo = cels[cel].firstChild;
				if (campo != null && campo.nodeName == "INPUT") {
					sumarColumna(campo, nro_columnas_info, id_tabla);
				}
			}
			// luego se suman el resto de las filas (todos los totales horizontales no calculados)
			for (var rowIndex = primeraFilaIndex; rowIndex < tamano_vertical - 1; rowIndex++) {
				cels = rows[rowIndex].getElementsByTagName("td");
				campo = cels[nro_columnas_info].firstChild;
				if (campo != null && campo.nodeName == "INPUT") {
					sumarFila(campo, nro_columnas_info, id_tabla);
				}
			}
		}
	}
	}catch(e){
	alert("se callo")
alert(e.message);
}
}

function sumarTabla2(id_tabla) {
	var nro_columnas_info = calcularNroColumnasInfo(id_tabla);
			//alert('DEBUG: sumando tabla - '+id_tabla);
			//alert('DEBUG: info - '+nro_columnas_info);
	var row_headers = 3;
	var tbl = document.getElementById(id_tabla);
	if (tbl != null) {
		var rows = tbl.getElementsByTagName("tr");
		var tamano_vertical = rows.length;
		if (tamano_vertical > 4) {
			var primeraFilaIndex = 3;
			var numeroFilas = tamano_vertical-4;
			var cels = rows[primeraFilaIndex].getElementsByTagName("td");
			var campo;
			var i = 1;
			for (var cel = nro_columnas_info - 1; cel < cels.length - 1; cel++) {
				campo = cels[cel].firstChild;
				if (campo != null && campo.nodeName == "INPUT") {
					sumarColumna2(campo, nro_columnas_info, id_tabla);
				}
			}
			// luego se suman el resto de las filas (todos los totales horizontales no calculados)
			for (var rowIndex = primeraFilaIndex; rowIndex < tamano_vertical - 1; rowIndex++) {
				cels = rows[rowIndex].getElementsByTagName("td");
				campo = cels[nro_columnas_info].firstChild;
				if (campo != null && campo.nodeName == "INPUT") {
					sumarFila2(campo, nro_columnas_info, id_tabla);
				}
			}
		}
	}
}

// se encarga de sumar los totales horizontales y verticales de la tabla especificada
function sumarFilas(id_tabla) {
	var nro_columnas_info = calcularNroColumnasInfo(id_tabla);
			//alert('DEBUG: sumando tabla - '+id_tabla);
			//alert('DEBUG: info - '+nro_columnas_info);
	var row_headers = 3;
	var tbl = document.getElementById(id_tabla);
	if (tbl != null) {
		var rows = tbl.getElementsByTagName("tr");
		var tamano_vertical = rows.length;
		if (tamano_vertical > 4) {
			var primeraFilaIndex = 3;
			var numeroFilas = tamano_vertical-4;
			var cels = rows[primeraFilaIndex].getElementsByTagName("td");
			var campo;
			var i = 1;
			// luego se suman el resto de las filas (todos los totales horizontales no calculados)
			for (var rowIndex = primeraFilaIndex; rowIndex < tamano_vertical - 1; rowIndex++) {
				cels = rows[rowIndex].getElementsByTagName("td");
				campo = cels[nro_columnas_info].firstChild;
				if (campo != null && campo.nodeName == "INPUT") {
					sumarFila(campo, nro_columnas_info, id_tabla);
				}
			}
		}
	}
}

//Se encarga de sumar todos los elementos de la fila y la columna a la que pertenece el campo
function sumarAllInside(campo, id_tabla) {
	var nro_columnas_info = calcularNroColumnasInfo(id_tabla);
	//sumarFila(campo, nro_columnas_info, id_tabla);
	if (id_tabla != "tabla_conceptos") {
		sumarColumna(campo, nro_columnas_info, id_tabla);
	}
}

function sumarAllInside2(campo, id_tabla) {
	var nro_columnas_info = calcularNroColumnasInfo(id_tabla);
	//sumarFila(campo, nro_columnas_info, id_tabla);
	if (id_tabla != "tabla_conceptos") {
		sumarColumna2(campo, nro_columnas_info, id_tabla);
	}
}

function sumarAll(campo, id_tabla) {
	var nro_columnas_info = calcularNroColumnasInfo(id_tabla);
	if (id_tabla != "tabla_conceptos") {
		sumarColumna(campo, nro_columnas_info, id_tabla);
	}
	if (id_tabla == "tabla_operaciones") {
		validarContraPlanCap(campo, nro_columnas_info, id_tabla);
	}
	sumarFila(campo, nro_columnas_info, id_tabla);
			//TODO: sumarNecesidadComercial();
}

function sumarAll2(campo, id_tabla) {
	var nro_columnas_info = calcularNroColumnasInfo(id_tabla);
	sumarFila2(campo, nro_columnas_info, id_tabla);
	if (id_tabla != "tabla_conceptos") {
		sumarColumna2(campo, nro_columnas_info, id_tabla);
	}
}

function validarContraPlanCap(campo, nro_columnas_info, id_tabla) {
	var cel = campo.parentNode;
	var childs = cel.children;
	var tons = childs[0];
	var dias = childs[1];
	var factord = childs[2]; // toneladas x hora (tasa real produccion)
	var factorh = childs[3]; // horas del puesto trabajo en un dia (plan capacidad horas)
	var max = childs[4]; // maximo en dias
	//alert("valores: Horas que Trabaja un Puesto de Trabajo en 1 Día= " + factorh.value + " , Toneladas x Hora= " + factord.value + ", Max de Días Opera PT= " + max.value);
	if (campo == tons) {
		if (trim(tons.value) != "") {
			tons.value = tons.value.replace(",", "");
			dias.value = dias.value.replace(",", "");
			if (factorh.value != null && factorh.value != 0 && trim(factorh.value) != "" && factord.value != null && factord.value != 0 && trim(factord.value) != "") {
				var temp = Number(tons.value / factorh.value / factord.value);
				//alert("tons = " + tons + " / factorh = " + factorh  + " / factord.value = " + factord);
				//alert("tons.value / factorh.value / factord.value = " + temp);
				dias.value = redondeoSinDecimales(temp);
				tons.value = redondeoSinDecimales(tons.value);
			} else {
				dias.value = 0;
			}
		} else {
			tons.value = 0;
			dias.value = 0;
		}
	}
	if (campo == dias) {
		if (trim(dias.value) != "") {
			tons.value = tons.value.replace(",", "");
			dias.value = dias.value.replace(",", "");
			if (factorh.value != null && factord.value != null) {
				var temp = Number(dias.value * factorh.value * factord.value);
				tons.value = redondeoSinDecimales(temp);
				dias.value = redondeoSinDecimales(dias.value);
				sumarColumna(dias, nro_columnas_info, id_tabla);
			} else {
				tons.value = 0;
			}
		} else {
			tons.value = 0;
			dias.value = 0;
		}
	}
	if (max != null && Number(max.value) > 0 && Number(dias.value) > Number(max.value) || Number(dias.value) > 28) {
		alert("El N\xfamero de D\xedas (" + dias.value + ") sobrepasa el M\xe1ximo permitido " + max.value);
		tons.style.backgroundColor = "#FFCC33";
		dias.style.backgroundColor = "#FFCC33";
	} else {
		tons.style.backgroundColor = "#ffffff";
		dias.style.backgroundColor = "#ffffff";
	}
	
	dias.value = formatoNumero(dias.value);
	tons.value = formatoNumero(tons.value);
}
//Se encarga de totalizar todos los elementos a nivel de la fila del campo
function sumarFila(campo, nro_columnas_info, id_tabla) {
	var fila = campo.parentNode.parentNode.rowIndex;
			//alert('DEBUG: sumando filas - '+fila);
	var tbl = document.getElementById(id_tabla);
	var rows = tbl.getElementsByTagName("tr");
	var cels = rows[fila].getElementsByTagName("td");
	var total = new Number(0);
	for (var cel = nro_columnas_info - 1; cel < cels.length - 1; cel++) {
		var campo = cels[cel].firstChild;
		if (campo != null && campo.value != null) {
			if (trim(campo.value) != "") {
				var temp = campo.value.replace(",", ""); 
				temp = Number(temp);
				if (id_tabla == "tabla_comercializacion" || id_tabla == "tabla_operaciones")
				{
					campo.value = temp.toFixed(0);
					total = Number(total + Number(campo.value));
				}
				else
				{
					campo.value = temp.toFixed(3);
					total = Number(total + Number(campo.value));
				}
				campo.value = formatoNumero(campo.value);
			}
		}
	}
		    //alert('DEBUG: suma= '+total);
   	if (id_tabla == "tabla_comercializacion" || id_tabla == "tabla_operaciones")
   	{
		total = redondeoSinDecimales(total);
	}
	else
	{
		total = redondeo3decimales(total);
	}
	
	total = formatoNumero(total); 

	var newChild = document.createTextNode(total);
	var child = cels[cels.length - 1].firstChild;
	cels[cels.length - 1].replaceChild(newChild, child);
	
}

//Se encarga de totalizar todos los elementos a nivel de la columna del campo
function sumarColumna(campo, nro_columnas_info, id_tabla) {
		//indica el numero de filas que componen al header (no se debe modificar sino po cambios de estilo)
	var nro_filas_header = 4;
	var columna = getCellIndex(campo.parentNode);
			//alert('DEBUG: sumando columna - '+columna);
	var tbl = document.getElementById(id_tabla);
	var rows = tbl.getElementsByTagName("tr");
	var total = new Number(0);
	for (var row = nro_filas_header - 1; row < rows.length - 1; row++) {
		var tempRow = rows[row].getElementsByTagName("td");
		var campo = tempRow[columna].firstChild;
		if (campo != null && campo.value != null) {
			if (trim(campo.value) != "") {
				var temp = campo.value.replace(",", ""); 
				temp = Number(temp);
				if (id_tabla == "tabla_comercializacion" || id_tabla == "tabla_operaciones")
				{
					campo.value = temp.toFixed(0);
					total = Number(total + Number(campo.value));
				}
				else
				{
					campo.value = temp.toFixed(3);
					total = Number(total + Number(campo.value));
				}
				campo.value = formatoNumero(campo.value);
			}
		}
	}
   	if (id_tabla == "tabla_comercializacion" || id_tabla == "tabla_operaciones")
   	{
		total = redondeoSinDecimales(total);
	}
	else
	{
		total = redondeo3decimales(total);
		    //alert('DEBUG: suma= '+total);
	}
	
	if(!(id_tabla == "tabla_consumos2" || id_tabla == "tabla_plan")){
	total = formatoNumero(total);
	}
	
	
	var newChild = document.createTextNode(total);
	var tempRow2 = rows[rows.length - 1].getElementsByTagName("td");
	var child = tempRow2[columna - (nro_columnas_info - 1)].firstChild;
	tempRow2[columna - (nro_columnas_info - 1)].replaceChild(newChild, child);
	
}

//Se encarga de totalizar todos los elementos a nivel de la fila del campo
function sumarFila2(campo, nro_columnas_info, id_tabla) {
	var fila = campo.parentNode.parentNode.rowIndex;
	//alert('DEBUG: sumando filas - '+fila);
	var tbl = document.getElementById(id_tabla);
	var rows = tbl.getElementsByTagName("tr");
	var cels = rows[fila].getElementsByTagName("td");
	var total = new Number(0);
	for (var cel = nro_columnas_info - 1; cel < cels.length - 1; cel++) {
		var campo = cels[cel].firstChild;
		if (campo != null && campo.value != null) {
			if (trim(campo.value) != "") {
				
				var temp = campo.value.replace(",", ""); 
				temp = Number(temp);
				campo.value = temp.toFixed(3);
				total = Number(total + Number(campo.value));
				
				//campo.value = formatoNumero(campo.value);
			}
		}
	}
	
   	total = redondeo3decimales(total/12);
//	alert('DEBUG: suma fila= '+total);
	
	//total = formatoNumero(total);
	var newChild = document.createTextNode(total);
	var child = cels[nro_columnas_info - 1].firstChild;
	cels[nro_columnas_info - 1].replaceChild(newChild, child);
}

function sumarColumna2(campo, nro_columnas_info, id_tabla) {
		//indica el numero de filas que componen al header (no se debe modificar sino po cambios de estilo)
	var nro_filas_header = 4;
	var columna = getCellIndex(campo.parentNode);
			//alert('DEBUG: sumando columna - '+columna);
	var tbl = document.getElementById(id_tabla);
	var rows = tbl.getElementsByTagName("tr");
	var total = new Number(0);
	for (var row = nro_filas_header - 1; row < rows.length - 1; row++) {
		var tempRow = rows[row].getElementsByTagName("td");
		var campo = tempRow[columna].firstChild;
		if (campo != null && campo.value != null) {
			if (trim(campo.value) != "") {
				var temp = campo.value.replace(",", ""); 
				temp = Number(temp);
				
				campo.value = temp.toFixed(3);
				total = Number(total + Number(campo.value));
				
				//campo.value = formatoNumero(campo.value);
			}
		}
	}
   	total = redondeo3decimales(total);
//	alert('DEBUG: suma columna= '+total);
	
	//total = formatoNumero(total);
	
	var newChild = document.createTextNode(total);
	var tempRow2 = rows[rows.length - 1].getElementsByTagName("td");
	var child = tempRow2[columna - (nro_columnas_info - 1)].firstChild;
	tempRow2[columna - (nro_columnas_info - 1)].replaceChild(newChild, child);
}

//limpiar string
function trim(cadena) {
	for (i = 0; i < cadena.length; ) {
		if (cadena.charAt(i) == " ") {
			cadena = cadena.substring(i + 1, cadena.length);
		} else {
			break;
		}
	}
	for (i = cadena.length - 1; i >= 0; i = cadena.length - 1) {
		if (cadena.charAt(i) == " ") {
			cadena = cadena.substring(0, i);
		} else {
			break;
		}
	}
	return cadena;
}

//aplicar redondeo a dos decimales
function redondeo3decimales(numero) {
	var original = parseFloat(numero);
	var result = Math.round(original * 1000) / 1000;
	return result.toFixed(3);
}

//aplicar redondeo a dos decimales
function redondeoSinDecimales(numero) {
	var original = parseFloat(numero);
	var result = Math.round(original * 1000) / 1000;
	return result.toFixed(0);
}

// oculta o muestra una columna de la tabla especificada
function show_hide_column(num_col, col_no, do_show, id_table) {
	var stl;
	if (do_show) {
		stl = "block";
	} else {
		stl = "none";
	}
	var tbl = document.getElementById(id_table);
	var rows = tbl.getElementsByTagName("tr");
	adjust_col_no = ajustarColumna(col_no);
			//alert('DEBUG: adjust_col '+adjust_col_no);
	var cels = rows[2].getElementsByTagName("td");
	cels[col_no].style.display = stl;
	for (var row = 3; row < rows.length; row++) {
		var cels = rows[row].getElementsByTagName("td");
		if (row == rows.length - 1) {
			cels[adjust_col_no - (num_col - 1)].style.display = stl;
		} else {
			cels[adjust_col_no].style.display = stl;
		}
	}
}

// corrige el error de numeros por las columnas de los controles
function ajustarColumna(col_no) {
			//alert('DEBUG: metodo col '+col_no+'  show: '+do_show);
	var adjust_col_no = col_no;
	if (col_no > 8) {
		adjust_col_no = adjust_col_no - 1;
	}
	if (col_no > 15) {
		adjust_col_no = adjust_col_no - 1;
	}
	if (col_no > 22) {
		adjust_col_no = adjust_col_no - 1;
	}
	return adjust_col_no;
}

// oculta todo un trimestre, indicando la columna desde y hasta, de la tabla deseada y el reverse para indicar el orden
function show_hide_group(num_col, from_col, to_col, id_table, reverse) {
	var j = 0;
	// ajuste de columnas de info
	from_col = from_col + num_col;
	to_col = to_col + num_col;
	// parametro que indica el numero de meses que se muestran (no es modificable)
	var num_meses = 3;
	var from = from_col;
	var show = false;
	if (reverse) {
		from = from_col + num_meses;
	}
	for (var i = from; i <= to_col; i++) {
				//alert('i= '+i+' show= '+show+' reverse= '+reverse+' from: '+from+' from-to_col: '+from_col+'-'+to_col);
		show_hide_column(num_col, i, show, id_table);
		j++;
		if (j == num_meses) {
			show = true;
			if (reverse) {
				from = from_col - 1;
				i = from_col - 1;
				to_col = to_col - num_meses;
			}
		}
	}
}
		
		//@return el numero de columnas informativas en el sistema
function calcularNroColumnasInfo(id_table) {
	var tbl = document.getElementById(id_table);
	if (tbl != null) {
		var rows = tbl.getElementsByTagName("tr");
		var cels = rows[1].getElementsByTagName("td");
			    //se le resta la columna de total y la columna del periodo
		return cels.length - 2;
	} else {
		return 0;
	}
}
		
		// Los valores de esta función son todos constantes, sin importar el numero de columnas
function hide_all(id_table, num_col) {
	//alert('hide all col:'+num_col);
	show_hide_group(num_col, 0, 5, id_table, false);
	show_hide_group(num_col, 7, 12, id_table, false);
	show_hide_group(num_col, 14, 19, id_table, false);
	show_hide_group(num_col, 21, 26, id_table, false);
}
		// Los valores de esta función son todos constantes, sin importar el numero de columnas
function show_enemar(id_table) {
	var num_col = calcularNroColumnasInfo(id_table);
	//alert('show col:'+num_col);
	hide_all(id_table, num_col);
	show_hide_group(num_col, 0, 5, id_table, true);
}
		// Los valores de esta función son todos constantes, sin importar el numero de columnas		
function show_abrjun(id_table) {
	var num_col = calcularNroColumnasInfo(id_table);
	hide_all(id_table, num_col);
	show_hide_group(num_col, 7, 12, id_table, true);
}
		// Los valores de esta función son todos constantes, sin importar el numero de columnas
function show_julsep(id_table) {
	var num_col = calcularNroColumnasInfo(id_table);
	hide_all(id_table, num_col);
	show_hide_group(num_col, 14, 19, id_table, true);
}
		// Los valores de esta función son todos constantes, sin importar el numero de columnas
function show_octdic(id_table) {
	var num_col = calcularNroColumnasInfo(id_table);
	hide_all(id_table, num_col);
	show_hide_group(num_col, 21, 26, id_table, true);
}

function formatoNumero(num) { 
	num = num.toString().replace(/$|,/g,''); 
	if(isNaN(num)) 
	num = "0"; 
	sign = (num == (num = Math.abs(num))); 
	num = Math.floor(num*100+0.50000000001); 
	cents = num%100; 
	num = Math.floor(num/100).toString(); 
	if(cents<10) 
	cents = "0" + cents; 
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++) 
	num = num.substring(0,num.length-(4*i+3))+','+ 
	num.substring(num.length-(4*i+3)); 
	return (((sign)?'':'-') + num); 
}

function aplicarFormatoCelda(input){
	var valor = input.value.replace(",", ""); 
	valor = Number(valor).toFixed(3);
	input.value = formatoNumero(valor);
}

function aplicarFormatoCeldaSinDecimales(input){
	var valor = input.value.replace(",", ""); 
	valor = Number(valor).toFixed(0);
	input.value = formatoNumero(valor);
}

function formatearTablaOrden(id_tabla){

	var tbl = document.getElementById(id_tabla);
	if (tbl != null) {
		var rows = tbl.getElementsByTagName("tr");
		var tamano_vertical = rows.length;
		var cantidadFilasBase = 1;
		var columnaInicial = 3;
		if (id_tabla == "tabla_operaciones") {
			cantidadFilasBase = 2;
			columnaInicial = 4;
		}
		if (tamano_vertical > cantidadFilasBase) {
			var primeraFilaIndex = cantidadFilasBase;
			var numeroFilas = tamano_vertical-cantidadFilasBase;
			var cels = rows[primeraFilaIndex].getElementsByTagName("td");
			var campo;
			var i = 1;
			// luego se suman el resto de las filas (todos los totales horizontales no calculados)
			for (var rowIndex = primeraFilaIndex; rowIndex < tamano_vertical; rowIndex++) {
				cels = rows[rowIndex].getElementsByTagName("td");
				
				for (var cel = columnaInicial - 1; cel < cels.length; cel++) {
					campo = cels[cel].firstChild;
					if (campo != null && campo.nodeName == "INPUT") {
						aplicarFormatoCeldaSinDecimales(campo);
					}
				}
			}
		}
	}


}

function getCellIndex(aCell) {
  aRow = aCell.parentNode;
  for (var i = 0; i != aRow.cells.length; i++) {
    if (aRow.cells[i] == aCell) return i;

  }
  return false;
}