
		var LINK = "_link";
		var CLASS_NAME = "current";
		
		var displayed_tab = null;
		
		(function()
		{
		  if (window.addEventListener)
		  {
		    window.addEventListener("load", setup_tabs, false);    
		  }
		  else
		  {
		    window.attachEvent("onload", setup_tabs);
		  }
		})();
		
		function setup_tabs()
		{
		  var tab_links = document.getElementById("tabs").getElementsByTagName("a");
		  
		  for (var i = 0; i < tab_links.length; i++)
		  {    
		    setup_tab_link(tab_links[i]);
		  }
		}
		
		function setup_tab_link(link)
		{  
		  var num = link.id.indexOf("_");
		  var link_id = link.id.substring(0, num);	
		  link.onclick = function(){display_tab(link_id);};
		}
		
		function hide_all_tabs()
		{
		  var tab_divs = document.getElementsByTagName("div");
		
		  //Only need those div elements that have an id value starting with 'tab'
		  for (var i = 0; i < tab_divs.length; i++)
		  {
		    if (tab_divs[i].id.indexOf("tab") === 0)
		    {
		      change_tab_display(tab_divs[i], "none", tab_divs[i].id, "");
		    }  
		  }  
		}
		
		function display_tab(id)
		{	  
		  if (!displayed_tab)
		  {	    
		    hide_all_tabs();
		  }
		  else
		  {            
		    change_tab_display(displayed_tab, "none", displayed_tab.id, "");
		  }
		
		  //Make the selected tab visible
		  displayed_tab = document.getElementById(id);
		  
		  change_tab_display(displayed_tab, "block", id, CLASS_NAME);
		}
		
		function change_tab_display(tab_content_element, display, tab_link_id, class_name)
		{
		  //Make the selected tab visible or not visible
		  tab_content_element.style.display = display;
		  
		  //Change the class name of the tab link
		  document.getElementById(tab_link_id + LINK).className = class_name;
		}
		
		
		//Codigo nuevo de la planificacion -------------------------------
		// Esta funcion se encarga de evitar la modificacion de los meses dependiendo del mes de corte
		function bloquearMeses(){
			var oInp = this.getElementsByTagName('input');
			for(x=0;x<oInp.length;i++){ 
				if(oInp[x].type=='text'){ 
				oTxt[j]=oInp[x];
				var lastValue = oTxt[oTxt.length-1].value;
				}
			}
		
		}


		//  Funcion para cargar los componentes de Jquery
		$(function () {
						// Accordion
			$("#accordion").accordion({header:"h3"});
		
		});

		//funcion para envio del formulario desde las diferentes funcionalidades del menu
		function seleccionarUnidad(){
			document.formularioRegistro.action = 'SeleccionarUnidadNegocio.action';
			document.formularioRegistro.submit();
		}
		
		function grabarPlan(){
			document.formularioRegistro.action = 'IngresarPlanAnual.action';
			document.formularioRegistro.submit();
		}
		
		function show_productos() {
			dojo.event.topic.publish("show_productos");
		}
		
		function show_otros_productos(){
			dojo.event.topic.publish("show_otros_productos");
		}
		
		function show_consultas() {
			dojo.event.topic.publish("show_consultas");
		}
		
		function show_conceptos() {
			dojo.event.topic.publish("show_conceptos");
			dojo.event.topic.publish("show_componentes");
			dojo.event.topic.publish("show_operaciones");
			dojo.event.topic.publish("show_recursos");
		}
		
		function show_indices(){
			dojo.event.topic.publish("show_indices");
		}
