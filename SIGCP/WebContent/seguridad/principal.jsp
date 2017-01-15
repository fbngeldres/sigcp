<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd height="2" XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/Dtd height="2"/xhtml1-transitional.dtd height="2"">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>CEMENTOS PACASMAYO - Sistema de Gestión y Control de
	Producción</title>

<link href="<%=request.getContextPath()%>/css/SGCP-estilo.css"
	rel="stylesheet" type="text/css" />
</head>

<body onload="iniciarMenu();">

	<div class="content-ppal">

		<!-- INCLUDE CABECERA PAGINA -->
		<jsp:include page="../comun/cabeceraPagina.jsp" flush="true" />

		<!-- INCLUDE MENU -->
		<jsp:include page="../comun/menu.jsp" flush="true" />

		<!--  INICIO CONTENIDO INTERNO -->
		<div id="contenido" class="content-int center">


			<!--  INICIO CONTENIDO -->

			<div id="contenido" class="center">

				<div class="content">
					<br /> <br /> <br /> <br /> <br />
					<div>
						<img src="<%=request.getContextPath()%>/images/logo.jpg" />
					</div>
					<div class="tituloBienvenida">
						<p>SISTEMA DE GESTION Y CONTROL DE LA PRODUCCION</p>
					</div>
				</div>
				<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
				<div class="bandaBienvenida"></div>
				<!-- INCLUDE PIE PAGINA -->
				
				<div class="footer">
					<jsp:include page="../comun/piePagina.jsp" flush="true" />
				</div>
			</div>
		</div>
</body>
</html>
