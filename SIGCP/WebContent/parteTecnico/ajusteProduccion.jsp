<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<title>CEMENTOS PACASMAYO - Sistema de Gesti&oacute;n y Control
	de Producci&oacute;n</title>

<link href="<%=request.getContextPath()%>/css/SGCP-estilo.css"
	rel="stylesheet" type="text/css" />

<script> var isomorphicDir = "<%=request.getContextPath()%>/ajusteProduccion/sc/"; </script>

<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/ajusteProduccion/ajusteProduccion.nocache.js"></script>
</head>
<body onload="iniciarMenu()">

	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
		style="position: absolute; width: 0; height: 0; border: 0"></iframe>

	<div class="content-ppal">

		<!-- INCLUDE HEADER -->
		<jsp:include page="../comun/cabeceraPagina.jsp" flush="true" />

		<!-- INCLUDE MENU -->
		<jsp:include page="../comun/menu.jsp" flush="true" />

		<!--  INICIO CONTENIDO INTERNO -->
		<div id="contenido" class="content-int">

			<!--  INICIO TITULO-->
			<div class="titulo">
				<s:text name="parteTecnico.ajusteProduccion.titulo.principal"></s:text>
			</div>

			<!--  INICIO BANDA DE ICONOS-->
			<div class="banda-iconos">
				<div>
					<table class="center" cellspacing="0">
						<tr>
							<td>
								<div id="refrescar"></div>
							</td>
							<td>
								<div id="generar"></div>
							</td>
								<s:if test="funGuardarActivo">
							<td>
								<div id="grabar"></div>
							</td>
							</s:if>
							<td>
								<div id="aprobar"></div>
							</td>
							<td>
								<div id="nuevo"></div>
							</td>
							<td>
								<div id="consultar"></div>
							</td>
							<td>
								<div style="visibility: hidden;" id="pruebaSu"></div>
							</td>
							<td>
								<div style="visibility: hidden;" id="prueba"></div>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<!--  INICIO CONTENIDO -->
			<div class="content">
				<div id="gwt">
					<div id="formularioCombos"></div>
					<div id="barraProgreso" style="padding-top: 5px; display: none;">
						<img src="<%=request.getContextPath()%>/images/ajax-loader.gif"></img>
					</div>
					<div id="observaciones" style="padding-top: 5px;"></div>
					<div id="gridBalance" style="width: 700px;"></div>
					<div id="gridsAjustes" style="width: 700px;"></div>
					<div id="gridsAjustesCombustible" style="width: 700px;"></div>

				</div>
			</div>
			<!-- INCLUDE FOOTER -->
			<jsp:include page="../comun/piePagina.jsp" flush="true" />
		</div>
	</div>
</body>
</html>