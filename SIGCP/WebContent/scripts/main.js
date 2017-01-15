var menuColapsado = "false"; 
var cantMenuPadres = 3; 
var idMenuPadres = [1,2,3]; 
var idHijo1 = [12,13,15,16];
var idHijo2 = [21];
var idHijo3 = [31]; 
var hijos = new Array(idHijo1,idHijo2,idHijo3);

function linkSubMenu(id) {
	var menuActual = document.getElementById("subMenu_"+id);
	var i;
	
	for(i = 0; i < idMenuPadres.length; i++) {
		for(j = 0; j < hijos[i].length; j++) {
			document.getElementById("subMenu_"+String(hijos[i][j])).className = "linksmenu";
		}
	}
	menuActual.className = "linkmenuactiv";
}
 
function mostrarOpciones(index) {
	var opciones = document.getElementById("opciones_"+index);
	var menu_padre = document.getElementById("menu_"+index);
	var titulo = document.getElementById("titulo_"+index);
	var flecha = document.getElementById("flecha_"+index);
	var i;
	
	if(opciones.style.display == "none") {
		opciones.style.display = "block";
		menu_padre.className = "fondogrismenudelin";
		titulo.className = "linktitmenu";
		for(i = 0; i < idMenuPadres.length; i++) {
			if(idMenuPadres[i] != index) {
				document.getElementById("titulo_"+idMenuPadres[i]).className = "menu";  // desactiva el titulo de los otros
				document.getElementById("opciones_"+idMenuPadres[i]).style.display = "none"; // cierra los otros menus
				document.getElementById("flecha_"+idMenuPadres[i]).style.display = "none"; //cierra todas flechas
			}
		}
		flecha.style.display = "block"; //muestra flecha
	}
	else {
		opciones.style.display = "none";
		menu_padre.className = "fondoblanco";
		titulo.className = "menu";
		flecha.style.display = "none"; //cierra flecha
	}
}
 
function switchMenu2(){
	if (menuColapsado == "false") { // menu expandido
		menuColapsado = "true";
		
		document.getElementById("tablaMenuColapsado").style.display = "block";
		document.getElementById("tablaMenuExpandido").style.display = "none";
		
		var tablas = parent.Cuerpo.document.getElementsByName("tabgen");
		for(i = 0 ; i < tablas.length; i++)
			tablas[i].width = "987";
		visualizarCuerpo();
	} 
	else {
  	   	menuColapsado = "false";
	    
		document.getElementById("tablaMenuExpandido").style.display = "block";
		document.getElementById("tablaMenuColapsado").style.display = "none";
		visualizarCuerpo();

	}
	
}	
 
function visualizarCuerpo(){	

	var minimo;
	var maximo;
 
	if(parent.Cuerpo.document.getElementsByName("tabPad").length==1){
		/*minimo = 820;
		maximo = 950;*/
	    minimo = 831;
		maximo = 986;
	}
	else{
		/*minimo = 832;
		maximo = 987;	*/
		minimo = 832;
		maximo = 987;	
	}
	
	if (menuColapsado == "false"){
		for(i = 0 ; i < tablas.length; i++)
			tablas[i].width = minimo;
	}	
	else{
		for(i = 0 ; i < tablas.length; i++)
			tablas[i].width = maximo;
	}		
		
}
 
function iniciar() {
	var i;
	
	//Desapareciendo submenus 
	for(i = 0; i < idMenuPadres.length; i++) {
		document.getElementById("opciones_"+idMenuPadres[i]).style.display = "none";
	}
	// tablas de menú
	document.getElementById("tablaMenuExpandido").style.display = "block";
	document.getElementById("tablaMenuColapsado").style.display = "none";
}
 
function cerrarSesion() {
	document.form1.metodo.value = "cerrarSesion";
	document.form1.target = "_parent";
	document.form1.submit();
}