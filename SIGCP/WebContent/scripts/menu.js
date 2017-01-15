var menuColapsado = "false"; 

function switchMenu2(){
	if (menuColapsado == "false") { // menu expandido
		comprimirContenido();
		menuColapsado = "true";
		
		document.getElementById("tablaMenuColapsado").style.display = "block";
		document.getElementById("tablaMenuExpandido").style.display = "none";
	} 
	else {
		expandirContenido();
  	   	menuColapsado = "false";
	    
		document.getElementById("tablaMenuExpandido").style.display = "block";
		document.getElementById("tablaMenuColapsado").style.display = "none";
	}
	
}


function expandirContenido(){
 document.getElementById("contenido").className = "content-int";
}

function comprimirContenido(){
 document.getElementById("contenido").className = "content-int2";
}

function iniciarMenu() {
	document.getElementById("tablaMenuExpandido").style.display = "block";
	document.getElementById("tablaMenuColapsado").style.display = "none";
}