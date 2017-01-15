<!DOCTYPE html PUBLIC "-//W3C//Dtd height="2" XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/Dtd height="2"/xhtml1-transitional.dtd height="2"">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>CEMENTOS PACASMAYO - Sistema de Gesti&oacute;n y Control
	de Producci&oacute;n</title>

<link href="<%=request.getContextPath()%>/css/SGCP-estilo.css"
	rel="stylesheet" type="text/css" />

</head>

<body onload="iniciarMenu()">

	<div class="content-ppal">

		<!-- INCLUDE HEADER -->
		<jsp:include page="../comun/cabeceraPagina.jsp" flush="true" />

		<!-- INCLUDE MENU -->
		<jsp:include page="../comun/menu.jsp" flush="true" />

		<!--  INICIO CONTENIDO INTERNO -->
		<div id="contenido" class="content-int">
			<div>
				<div class="center">
					<img src="../images/exito.png" width="64" height="64" />
					<h2>La operación ha sido realizada con éxito. Gracias</h2>
					<p>
						<s:if test='paginaListar != null'>
							<s:submit type="input" key="boton.volver" value="    Volver"
								cssClass="buttonvolver"
								onclick="window.location='%{paginaListar}'" theme="simple" />
						</s:if>
						<s:if test='paginaListar == null'>
							<s:submit type="input" key="boton.volver" value="    Volver"
								cssClass="buttonvolver" onclick="javascript:history.back()"
								theme="simple" />
						</s:if>
					</p>
				</div>
			</div>

			<!-- INCLUDE FOOTER -->
			<jsp:include page="../comun/piePagina.jsp" flush="true" />

		</div>
	</div>

</body>
</html>
