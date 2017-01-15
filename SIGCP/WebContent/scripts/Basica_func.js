/* Funciones de cadena */
function ltrim(s) {   return s.replace(/^\s+/, "");}
function rtrim(s) {   return s.replace(/\s+$/, "");}
function trim(s) {   return rtrim(ltrim(s));}

//------------------------------quita caracteres en blanco en la cadena---------
function trim(psString){ 
    return String(psString).replace(/[\s]/g,""); 
}

//------------------------------Reemplaza espacios en blanco por %-------------
function reempBlanco(original){
  var retorno="";
  for (var i = 0; i < original.length; i ++){
    retorno += (original.charAt(i) == " ") ? "%" : original.charAt(i);
  }
  return retorno;
}

//------------------------------Valida que no empiece con _ -------------
function validaIni_(login){
  var valido= "_";
  if (valido.indexOf(login.substring(0,1),0)!= -1){
      alert("No es permitido '_' al inicio del nombre");
      return true;
  }
  return false;
}

//if (valido.indexOf (strCadena.substring(i,i+1),0) == -1)

//------------------------------elimina caracteres extraños de la cadena--------
function ValidarStringAlfaNumeric(ls_obj, ls_mensage){
    var strCadena = ls_obj.value;
    var valido= "ºABCDEFGHIJKLMNOPQRSTUVWYXZ0123456789abcdefghijklmnopqrstuvwxyz#$%*+-:,.ñÑáéíóúÁÉÍÓÚ&/ ";

    for (i = 0 ; i <= strCadena.length - 1; i++)
    {
	
        if(!vacio(trim(strCadena.substring(i,i+1)))){
            if (valido.indexOf (strCadena.substring(i,i+1),0) == -1)
            {	
                ls_obj.select();
                alert("Hay un caracter no permitido en el campo " + ls_mensage + "\nel caracter no permitido es ( "+strCadena.substring(i,i+1)+" )");
                ls_obj.focus();
                return false;
            }
        }
    }	
    return true;
}


//------------------------------valida si hay caracteres extraños de la cadena-------------
function ValidarFormato(strCadena)
{   var noValido= "*?<>|";
    for (i = 0 ; i <= strCadena.length - 1; i++)
    {   if (noValido.indexOf(strCadena.substring(i,i+1),0) >=0 )
          {	return false;
          }
    }
    return true;
}


//------------------------------elimina caracteres extraños de la cadena-------------
function ValidarString(ls_obj, ls_mensage)
{
    var strCadena = ls_obj.value;
    var valido= "ºABCDEFGHIJKLMNOPQRSTUVWYXZ0123456789abcdefghijklmnopqrstuvwxyz#$%()=*+}{[]-:,.ñÑáéíóúÁÉÍÓÚ!¡¿?&/ ";
    for (i = 0 ; i <= strCadena.length - 1; i++)
    {
        if(!vacio(trim(strCadena.substring(i,i+1)))){
            if (valido.indexOf (strCadena.substring(i,i+1),0) == -1)
            {	
                ls_obj.select();
                alert("Hay un caracter no permitido en el campo " + ls_mensage + "\nel caracter no permitido es ( "+strCadena.substring(i,i+1)+" )");
                ls_obj.focus();
                return false;
            }
		 
        }
    }	
    return true;
}

//------------------------------Verifica si una cadena es vacia-------------
function vacio(ls_cadena) {
    if (ls_cadena == " " || ls_cadena == null || ls_cadena.length == 0)
        return true;
    else return false;
}

//------------------------------Verifica si una cadena solo tiene espacios en blancos -------------
function Blanco(ls_cadena)
{   var i;
    if (vacio(ls_cadena)) return true;

    for (i = 0; i < ls_cadena.length; i++)
    {
	if (ls_cadena.charAt(i) != " ") return false;
    }

    return true;
}

//------------------------------Verifica si una cadena solo tiene espacios en blancos 

function ValidarBlanco(ls_obj,ls_mensage)
{
    if (Blanco(trim( ls_obj.value )))
    {
        alert("Por favor ingrese " + ls_mensage);
        ls_obj.value="";
        ls_obj.focus();
        return false;
    }
    return true;
}



//------------------------------Verifica si un caracter es numerico-------------
function isDigito (ls_car)
{   
    return ((ls_car >="0") && (ls_car<="9"))
}

//------------------------------Verifica si un caracter es alfabetico-------------
function isLetra (ls_car)
{   
    return ( ((ls_car >= "a") && (ls_car <= "z")) ||((ls_car >= "A") && (ls_car <= "Z"))|| ((ls_car >= "á") && (ls_car <= "ú")) || ((ls_car >= "Á") && (ls_car <= "Ú"))||
        (ls_car=="ñ")||(ls_car =="Ñ"))
}

//------------------------------Verifica si un caracter es otro caracter alfabetico-------------
function isOtroCaracter (ls_car)
{   
    return ( (ls_car==" ") || (ls_car==".") || (ls_car=="-") || (ls_car=="_") || (ls_car=="/") || 
        (ls_car=="=") || (ls_car=="'") || (ls_car=="`") || (ls_car=="°") || (ls_car==":") || 
        (ls_car==";") || (ls_car==",") || (ls_car=="&") || (ls_car=="?") || (ls_car=="(") || (ls_car==")") || (ls_car=="\\"))
}

//-------------------------Funcion para validar fecha

function esDigito(sChr){
    var sCod = sChr.charCodeAt(0);
    return ((sCod > 47) && (sCod < 58));
}

function valSep(oTxt){
    var bOk = false;
    // bOk = bOk || ((oTxt.value.charAt(2) == "-") && (oTxt.value.charAt(5) == "-"));
    bOk = bOk || ((oTxt.value.charAt(2) == "/") && (oTxt.value.charAt(5) == "/"));
    return bOk;
}

function finMes(oTxt){
    var nMes = parseInt(oTxt.value.substr(3, 2), 10);
    var nRes = 0;
    switch (nMes){
        case 1: nRes = 31; break;
        case 2: nRes = 29; break;
        case 3: nRes = 31; break;
        case 4: nRes = 30; break;
        case 5: nRes = 31; break;
        case 6: nRes = 30; break;
        case 7: nRes = 31; break;
        case 8: nRes = 31; break;
        case 9: nRes = 30; break;
        case 10: nRes = 31; break;
        case 11: nRes = 30; break;
        case 12: nRes = 31; break;
    }
    return nRes;
}

function valDia(oTxt){
    var bOk = false;
    var nDia = parseInt(oTxt.value.substr(0, 2), 10);
    bOk = bOk || ((nDia >= 1) && (nDia <= finMes(oTxt)));
    return bOk;
}

function valMes(oTxt){
    var bOk = false;
    var nMes = parseInt(oTxt.value.substr(3, 2), 10);
    bOk = bOk || ((nMes >= 1) && (nMes <= 12));
    return bOk;
}

function valAno(oTxt){
    var bOk = true;
    var nAno = oTxt.value.substr(6);
    bOk = bOk && (nAno.length == 4);
    if (bOk){
        for (var i = 0; i < nAno.length; i++){
            bOk = bOk && esDigito(nAno.charAt(i));
        }
    }
    return bOk;
}

function valFecha(oTxt){
    var bOk = true;
    if (oTxt.value != ""){
        bOk = bOk && (valAno(oTxt));
        bOk = bOk && (valMes(oTxt));
        bOk = bOk && (valDia(oTxt));
        bOk = bOk && (valSep(oTxt));
        if (!bOk){
            oTxt.value = "";
            oTxt.focus();
            return false;
        }
        return true;
    }
}

function Comparar_Fecha(String1,String2)
{
    // Si los dias y los meses llegan con un valor menor que 10
    // Se concatena un 0 a cada valor dentro del string
    if (String1.substring(1,2)=="/") {
    String1="0"+String1
    }
    if (String1.substring(4,5)=="/"){
    String1=String1.substring(0,3)+"0"+String1.substring(3,9)
    }

    if (String2.substring(1,2)=="/") {
    String2="0"+String2
    }
    if (String2.substring(4,5)=="/"){
    String2=String2.substring(0,3)+"0"+String2.substring(3,9)
    }

    dia1=String1.substring(0,2);
    mes1=String1.substring(3,5);
    anyo1=String1.substring(6,10);
    dia2=String2.substring(0,2);
    mes2=String2.substring(3,5);
    anyo2=String2.substring(6,10);


    if (dia1 == "08") // parseInt("08") == 10 base octogonal
    dia1 = "8";
    if (dia1 == '09') // parseInt("09") == 11 base octogonal
    dia1 = "9";
    if (mes1 == "08") // parseInt("08") == 10 base octogonal
    mes1 = "8";
    if (mes1 == "09") // parseInt("09") == 11 base octogonal
    mes1 = "9";
    if (dia2 == "08") // parseInt("08") == 10 base octogonal
    dia2 = "8";
    if (dia2 == '09') // parseInt("09") == 11 base octogonal
    dia2 = "9";
    if (mes2 == "08") // parseInt("08") == 10 base octogonal
    mes2 = "8";
    if (mes2 == "09") // parseInt("09") == 11 base octogonal
    mes2 = "9";

    dia1=parseInt(dia1);
    dia2=parseInt(dia2);
    mes1=parseInt(mes1);
    mes2=parseInt(mes2);
    anyo1=parseInt(anyo1);
    anyo2=parseInt(anyo2);

    if (anyo1>anyo2)
    {
    return false;
    }

    if ((anyo1==anyo2) && (mes1>mes2))
    {
    return false;
    }
    if ((anyo1==anyo2) && (mes1==mes2) && (dia1>dia2))
    {
    return false;
    }

    return true;
}
