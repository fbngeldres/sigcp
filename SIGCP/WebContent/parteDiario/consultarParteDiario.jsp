<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<title>CEMENTOS PACASMAYO - Sistema de Gesti&oacute;n y Control
	de Producci&oacute;n</title>

<link href="<%=request.getContextPath()%>/css/SGCP-estilo.css"
	rel="stylesheet" type="text/css" />

<script> var isomorphicDir = "<%=request.getContextPath()%>/consultarParteDiario/sc/"; </script>

<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/consultarParteDiario/consultarParteDiario.nocache.js"></script>
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
				<s:text name="parteDiario.titulo.consultar"></s:text>
			</div>

			<!--  INICIO BANDA DE ICONOS-->
			<div class="banda-iconos">
				<div>
					<table class="center" cellspacing="0">
						<tr>
							<s:if test="funConsultarActivo">
							<td>
								<div id="filtrar"></div>
							</td>
								</s:if>
						</tr>
					</table>
				</div>
			</div>
			<!--  INICIO CONTENIDO -->
			<div class="content">
				<div id="gwt">
					<div id="formularioCombos"></div>
					</br>
					<div id="barraProgreso" style="padding-top: 5px; display: none;">
						<img src="<%=request.getContextPath()%>/images/ajax-loader.gif"></img>
					</div>
					</br>
					<div id="tablaKardex"></div>
				</div>
			</div>
			<!-- INCLUDE FOOTER -->
			<jsp:include page="../comun/piePagina.jsp" flush="true" />
		</div>
	</div>
</body>
</html>