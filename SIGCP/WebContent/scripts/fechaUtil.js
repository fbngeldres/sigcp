// Constante que indica la cantidad de dias que dura el programa de produccion semanal,
// Es decir, si dura de sabado a viernes, la constante vale 6 dias.
var DIAS_PROGRAMA = 6;

// Funcion que valida si la fecha pasada como parametro es dia sabado, 
// para el programa de produccion semanals
function validarSabado() { 
	
	FECHA_INICIO = new String(document.formularioFiltro.fechaInicio.value);
	var arreglo = FECHA_INICIO.split("/");	
	var dia = arreglo[0];
	var mes = arreglo[1];
	var anno =  arreglo[2];	
	//alert('dia '+dia);
	//alert('mes '+mes);
	//alert('anno '+anno);

	mes = mes-1;	
	var fechaInicio = new Date(anno,mes,dia);
	//alert('como objeto fecha queda '+fecha);
	
	//Esta variable valida si es dia sabado (dia 6 de la semana)
	var ww = fechaInicio.getDay();
	if(ww == 6){
			document.formularioFiltro.fechaFinRegistro.value='';
			var currentTime = new Date();
			
			if(fechaInicio >= currentTime){
				calcularFechaFin(fechaInicio);
				return true;
			}
			else{
				alert('La fecha de inicio debe ser una fecha mayor\n o igual a la fecha actual.');
				document.formularioFiltro.fechaInicio.value='';
				document.formularioFiltro.fechaFinRegistro.value='';
				return false;
			}
	}
	else{
		alert('Debe selecionar un dia sábado como fecha de inicio.');
		document.formularioFiltro.fechaInicio.value='';
		document.formularioFiltro.fechaFinRegistro.value='';
		return false;		
	}
}

// Funcion que calcula la fecha de fin del programa de produccion
// semanal y la escribe en el campo de texto de fechaFin de la JSP
function calcularFechaFin(fechaInicio) {  	
	
	//Incrementamos 6 dias para que sea viernes el dia de cierre
	var dia = fechaInicio.getDate();
	var mes = fechaInicio.getMonth();
	var anno =  fechaInicio.getFullYear();	
	
	// Variable que cuenta la cantidad de miliseg que dura el programa 
	// de produccion semanal mas la fecha de inicio
	var diasMiliseg = fechaInicio.getTime() + (DIAS_PROGRAMA * 24 * 60 * 60 * 1000);
	
	// Cosntruimos el objeto Date de fechaFin con la cantidad de miliseg
	var fechaFin = new Date(diasMiliseg);
		
	var diaFin = fechaFin.getDate();
	var mesFin = fechaFin.getMonth()+1;
	var annoFin =  fechaFin.getFullYear();
	
	diaFin = diaFin < 10 ? "0" + diaFin : diaFin;
	mesFin = mesFin < 10 ? "0" + mesFin : mesFin;
	
	//alert('diaFin/mesFin/annoFin = ' + diaFin + '/' + mesFin + '/' + annoFin);	

	// Escribimos en el campo de texto la fecha de fin del plan
	document.formularioFiltro.fechaFinRegistro.value = diaFin + '/' + mesFin + '/' + annoFin;
}

// Funcion que valida que la fecha de inicio sea menor que la fecha de fin
// NOTA: para poder reutilizar esta funcion, las fechas se deben llamar asi:
// fechaInicio y fechaFin. El nombre del formulario sera formularioFiltro. 
function validarFechaFin(){

	FECHA_INICIO = new String(document.formularioFiltro.fechaInicio.value);
	FECHA_FIN = new String(document.formularioFiltro.fechaFin.value);
	
	var arregloInicio = FECHA_INICIO.split("/");	
	var diaInicio = arregloInicio[0];
	var mesInicio = arregloInicio[1];
	var annoInicio =  arregloInicio[2];	
	mesInicio = mesInicio - 1;	
	var fechaInicio = new Date(annoInicio,mesInicio,diaInicio);
	
	var arregloFin = FECHA_FIN.split("/");	
	var diaFin = arregloFin[0];
	var mesFin = arregloFin[1];
	var annoFin =  arregloFin[2];	
	mesFin = mesFin - 1;	
	var fechaFin = new Date(annoFin,mesFin,diaFin);
	
	if((fechaInicio > fechaFin) &&  ((fechaInicio != null)&&(fechaFin != null))){
		alert("La fecha de fin debe ser una fecha igual\n o mayor a la fecha de inicio.");
		document.formularioFiltro.fechaFin.value="";		
	}
}



 //Fabian Geldres
 //Formatear Fecha
 // formatea fecha 
// Permite ingresar el sgte formato nnnn-nn-nn
// donde "n" es sólo un número
function  formateaFecha(objeto){
   var  keyAscii = event.keyCode;
    event.keyCode = 0;
   if((objeto.value.length == 3) || (objeto.value.length) == 6 ){
   	  if(keyAscii<48 || keyAscii>57 ){
			 event.keyCode = 0;
	  }else{
			event.keyCode = 45;
			objeto.value = objeto.value+String.fromCharCode(keyAscii);
	}

   }else{
  	  if(keyAscii<48 || keyAscii>57 ){
			 event.keyCode = 0;
	  }else{
			event.keyCode =   keyAscii;	 
	}
  }
}