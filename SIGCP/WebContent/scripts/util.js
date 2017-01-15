
var CLEAR_COLOR = "#FFFFFF";
var DARK_COLOR = "#CCFFCC";

function setTitle( strTitle ) {
  var html;
  var appTitle = 'Sistema de Gestion de Inventarios';
  html = "<font face=\"Verdana\" color=\"#FFFFFF\" size=\"2\"><b>";
  html+= appTitle + " - " + strTitle + "</b></font>";

  parent.HeaderFrame.document.getElementById("title").innerHTML = html;
}

var TABLE_STYLE = {
  rowCreator:function(options) {
     
    var row = document.createElement("tr");
    var index = options.rowIndex * 50;
    row.style.backgroundColor =  (alternate) ? DARK_COLOR : CLEAR_COLOR; 
    alternate = !alternate;
    return row;
  },
  cellCreator:function(options) {
    var td = document.createElement("td");
    return td;
  }
} ; 

function validarCampoEvento(evt, value, maxLongitud)
{
    var charCode = (evt.which) ? evt.which : event.keyCode;  
    var keychar = String.fromCharCode(charCode);
    var string = value + keychar;
    return validarCaracteresInvalidosInterno(string) &&  validarLongitudCampo(string, maxLongitud);
}

function deshabilitarBoton(){
	document.getElementById("frm_listarAjusteProduccion").botonAceptar.disabled=true;
}

function validarCaracteresInvalidosInterno(valor) 
{
    var check = /<|>|&#/;    
    return !check.test(valor);
}

function validarLongitudCampo(value, maxLongitud) 
{   
    if (document.selection.type == 'Text')
    {
        return true;
    }
    return value.length <= maxLongitud;
}

// Permite ingresar letras y caacteres especiales nada mas en los campos de texto
function alfa(e) { 
    tecla = (document.all) ? e.keyCode : e.which; 
    if ((tecla==8) || (tecla==46)){
        return true; //Tecla de retroceso y supr (borrar) 
    }

    // Solo letras, numeros y los caracteres especiales definidos
    patron = /[\A-Za-z\s\;\,\(\)\:\%\#\@\*\-\_\?\¿\+\=\á\é\í\ó\ú]/;

    te = String.fromCharCode(tecla); 
   
    return patron.test(te);  
}  

// Permite ingresar caracteres alfanumericos en los campos de texto
// permite ingresar caracteres especiales menos las comillas dobles y simples, 
function alfanumerico(e) { 
    tecla = (document.all) ? e.keyCode : e.which; 
    if ((tecla==8) || (tecla==46)){
        return true; //Tecla de retroceso y supr (borrar) 
    }

    // Solo letras, numeros y los caracteres especiales definidos
    patron = /[\d\A-Za-z\s\;\,\(\)\:\%\#\@\*\-\_\?\¿\+\=\á\é\í\ó\ú]/;

    te = String.fromCharCode(tecla); 
   
    return patron.test(te);  
}  

// Permite ingresar caracteres alfanumericos en los campos de texto
// permite ingresar caracteres especiales menos las comillas dobles y simples, 
function monto(e) { 
    tecla = (document.all) ? e.keyCode : e.which; 
    if ((tecla==8) || (tecla==46)){
        return true; //Tecla de retroceso y supr (borrar) 
    }

    // Solo letras, numeros y los caracteres especiales definidos
    patron = /[\d\.\-]/;

    te = String.fromCharCode(tecla); 
   
    return patron.test(te);  
}  

function montoDecimales(e){


return monto(e);
}

//Permite ingresar solodigitos, inhabilita el uso de (ctrl+v)
//(solo valido para IE)
function numerico(e){
    tecla = (document.all) ? e.keyCode : e.which; 
    if (tecla==8){
        return true; //Tecla de retroceso (borrar) 
    }

    // Solo acepta digitos
    patron = /\d/;
    te = String.fromCharCode(tecla); 

    return patron.test(te);  
}

// funcion que cuenta los caracteres escritos en un textarea
// y solo permite solo una cantidad de caracteres pasada como parametro
// recib el nombre del textareay 
function textCounter(field, countfield, maxlimit) {
if (field.value.length > maxlimit) // if too long...trim it!
field.value = field.value.substring(0, maxlimit);
// otherwise, update 'characters left' counter
else 
countfield.value = maxlimit - field.value.length;
}

//---------------------------------------------------------------------
// Cierra la ventana actual y abre una nueva Ventana
//---------------------------------------------------------------------
function cerrar_y_abrirNueva(pagina, ancho, alto) {
    window.close();
    var str = 'width=' + ancho + ',height=' + alto;
    window.open(pagina, "_blank", str); 
}

//---------------------------------------------------------------------
// Oculta/Muestra Reloj de arena
//---------------------------------------------------------------------
function setHourglass( iShow ) {
  self.document.body.style.cursor = ( iShow == 0 ) ? 'default' : 'wait';
}   

//---------------------------------------------------------------------
// Abre una nueva Ventana
//---------------------------------------------------------------------
function abrirVentana(pagina, ancho, alto) {
    var str = 'width=' + ancho + ',height=' + alto;
    window.open(pagina, "_blank", str); 
}

//---------------------------------------------------------------------
// Obtiene el valor seleccionado de un arreglo de radio buttons
//---------------------------------------------------------------------
function getCheckedValue(radioObj) {
	if(!radioObj)
		return "";
	var radioLength = radioObj.length;
	if(radioLength == undefined)
		if(radioObj.checked)
			return radioObj.value;
		else
			return "";
	for(var i = 0; i < radioLength; i++) {
		if(radioObj[i].checked) {
			return radioObj[i].value;
		}
	}
	return "";
}

//----------------------------------------------------------------------------
// Funcion para agregar una opcion a un combo. Debe ser llamada desde un bucle
//---------------------------------------------------------------------------- 
function addOption(valorDia,text,value ){
	var optn = document.createElement("OPTION");
	optn.text = text;
	optn.value = value;
	valorDia.options.add(optn);
}
//----------------------------------------------------------------------------
// Funcion para resetear un combo
//----------------------------------------------------------------------------
function limpiarCombo(combo){	
	if(combo.options != null){
		combo.options.length = 0;
	}
}
//----------------------------------------------------------------------------
// Funcion usada para cargar cualquier componente dinamicamente usando el 
// ajax de strtus2
//----------------------------------------------------------------------------
function update(componente) {  
   dojo.event.topic.publish(componente); 
}

//----------------------------------------------------------------------------
// Funcion usada para ocultar o mostrar componentes
//----------------------------------------------------------------------------

function ocultarComponente(someID){
	document.getElementById(someID).style.visibility = 'hidden';
}

//----------------------------------------------------------------------------
// Funcion usada para mostrar componentes
//----------------------------------------------------------------------------

function mostrarComponente(someID){
	document.getElementById(someID).style.visibility = 'visible';
}

function cleanDisableCombo(someId){
	var combo = document.getElementById(someId);
	if(combo!=null && combo!='undefined'){
		combo.disabled=true;
		limpiarCombo(combo);
	}
}

//------------------------------------------------------------------------------------------------------------
// Funciones usadas para actualizar las columnas en la tabla de la seccion: Registros de Productos en Proceso
//------------------------------------------------------------------------------------------------------------

// Copia el Valor tomado en la columna Almacen (del 1er Registro) y lo copia al resto de los Registros. Igualmente actualiza
// los combos de Ubicacion de cada registro
function updateTableAlmacenColumn(componente,id,cantidadRegistros) {  

   	// Se actualiza el campo #_comboUbicaciones relacionado al Campo que llama esta accion.
   	dojo.event.topic.publish(componente); 
   	// Estas proximas acciones se realizan solo para el combo Almacen para el primer registro de la tabla
   	if (parseInt(id) == 0) {
   		var rowOne = document.getElementById(id+"_formularioRegistros_valorAlamacen");
	   	var valueRowOne = rowOne.value;
	    if (valueRowOne != null) {
		   	for (i=parseInt(id)+1; i<= cantidadRegistros; i=i+1) {
		   		if (document.getElementById(i+"_formularioRegistros_valorAlamacen") != null)
		   			document.getElementById(i+"_formularioRegistros_valorAlamacen").value = valueRowOne;
			   	dojo.event.topic.publish(i+"_comboUbicaciones");
		   	}
	   	}
   	}
}

// Copia el Valor tomado en la columna Ubicacion (del 1er Registro) y lo copia al resto de los Registros.
function updateTableUbicacionColumn(id,cantidadRegistros) {  
   	// Estas proximas acciones se realizan solo para el combo Ubicacion para el primer registro de la tabla
   	if (parseInt(id) == 0) {
	   	var rowOne = document.getElementById("valorUbicacion_"+id);
	   	var valueRowOne = rowOne.value;
	   	for (i=parseInt(id)+1; i<= cantidadRegistros; i=i+1) {
	   		if (document.getElementById("valorUbicacion_"+i) != null)
	   			document.getElementById("valorUbicacion_"+i).value = valueRowOne;
	   	}
   	}
}

//--------------------------------------------------------------------------
// Funcion usada para redonear numeros
//--------------------------------------------------------------------------

function redondeo1decimales(numero)
 {
  var original=parseFloat(numero);
  var result=Math.round(original*100)/100 ;
  return result;
  }

function fn_soloDecimales(e){
  tecla = (document.all) ? e.keyCode : e.which;
  if (tecla==8 || tecla==0) return true; //Tecla de retroceso (para poder borrar)
  patron = /[0-9 ."]/;
  te = String.fromCharCode(tecla);
  return patron.test(te);
}
function fn_soloInt(e){
  tecla = (document.all) ? e.keyCode : e.which;
  if (tecla==8 || tecla==0) return true; //Tecla de retroceso (para poder borrar)
  patron = /[0-9"]/;
  te = String.fromCharCode(tecla);
  return patron.test(te);
}

function jselecCheckBox(idFormulario,campoCodigo) {
	var chk = document.getElementById(idFormulario).elements[campoCodigo];
	var formx = document.getElementById(idFormulario);
	if (chk != undefined) {
		if (chk.length != undefined) {
			for (i = 0; i < chk.length; i++) {
				chk[i].checked = formx.chkPadre.checked;
			}
		} else {
			chk.checked = formx.chkPadre.checked;
		}
	}
}


function esNumero(cadena){
	  if ( isNaN( cadena ) )
	  {
	    return false
	  } else {
	    return true
	  }
	}
	
	function esTeclaDecimal(e) {
		var valid = "0123456789.";
		var key = String.fromCharCode(event.keyCode);
		if (valid.indexOf("" + key) == "-1") return false;
	}	

	
//--------------------------------------------------------------------------
// Funcion usada para insertar la barra de carga de espera.
//--------------------------------------------------------------------------

function insertarCarga(accion){

 if($('#valorCargaCompletada').val() == "CARGA_COMPLETADA"){
 	$('#'+accion).html("<img style='border: 0px;' alt='Cargando...' src='../images/ajax-loader.gif' width='220px' height='19px'>");
 }
}

 function deshabilitarCampos(form){ 

   
		 for(var i = 0; i < form.length; i++) {
			try{
//alert(form.elements[i].type + " _ " + form.elements[i].className);
			  if(form.elements[i].type == "select-one")
			      {
			      form.elements[i].disabled=true;
			      if (form.elements[i].className == "largoControlCostos" || form.elements[i].className == "mediano" ) {
				      form.elements[i].className="largoControlCostosDisabled";
				  }	else  if (form.elements[i].className == "cortoControlCostos" ){
				      form.elements[i].className="cortoControlCostosDisabled";
			  	  }
			     
			  } else if(form.elements[i].type == "button") {
			      form.elements[i].disabled=true;
			      form.elements[i].style.cursor='wait';
			  } else if(form.elements[i].type == "text") {
			  	  form.elements[i].readonly=true;
			      form.elements[i].disabled=true;
				  if (form.elements[i].className == "ingreso") {
				      form.elements[i].className="ingresoDisabled";
				  }	
			      
			  } 	else if(form.elements[i].type == "submit"){
			   form.elements[i].disabled=true;
			      form.elements[i].style.cursor='wait';
			  }	     	         
     	   }catch(myErr){
     	   alert(myErr);
     	   }
	    }	

	   
	    document.body.style.cursor='wait';
	   
   }