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

<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/util.js"></script>

</head>

<body onload="iniciarMenu()">

	<div class="content-ppal">

		<!-- INCLUDE HEADER -->
		<jsp:include page="../comun/cabeceraPagina.jsp" flush="true" />

		<!-- INCLUDE MENU -->
		<jsp:include page="../comun/menu.jsp" flush="true" />

		<!--  INICIO CONTENIDO INTERNO -->
		<div id="contenido" class="content-int">

			<!--  INICIO TITULO-->
			<div class="titulo">
				<s:text name="parteTecnico.ajusteProduccionMes.titulo.aprobar"></s:text>
			</div>

			<!--  INICIO BANDA DE ICONOS-->
			<div class="banda-iconos">
				<div>
					<table class="center" cellspacing="0">
						<tr>
						</tr>
						<tr>
						</tr>
					</table>
				</div>
			</div>

			<!--  INICIO CONTENIDO -->
			<div class="content">
				<div class="center">
					<s:form name="frm_listarAjusteProduccion"
						action="aprobarAjuste.action" method="post" theme="simple">

						<s:hidden name="ajuste.codigoAjuste"
							value="%{ajuste.codigoAjuste}" />



						<img src="<%=request.getContextPath()%>/images/confirmar.png"
							width="64" height="64" />
						<h2>
							¿Está seguro que desea aprobar la Producción: "
							<s:text name="%{ajuste.codigoAjuste}" />
							-
							<s:text name="%{ajuste.lineaNegocio}" />
							-
							<s:text name="%{ajuste.mesAnio}" />
							"?
						</h2>
						<p>
							<s:submit key="boton.aceptar" cssClass="buttonaceptar"
								id="botonAceptar" 
								onclick="javascript:deshabilitarCampos( document.getElementsByName('frm_listarAjusteProduccion')[0]);document.getElementsByName('frm_listarAjusteProduccion')[0].submit()" />
							<s:submit key="boton.cancelar" cssClass="buttoncancelar"
								name="redirect-action:consultarAjusteProduccionMes" />
						</p>
					</s:form>
				</div>
			</div>


			<!-- INCLUDE FOOTER -->
			<jsp:include page="../comun/piePagina.jsp" flush="true" />

		</div>
	</div>

</body>
</html>
